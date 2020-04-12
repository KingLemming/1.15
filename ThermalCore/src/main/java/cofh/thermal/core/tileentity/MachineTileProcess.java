package cofh.thermal.core.tileentity;

import cofh.core.network.packet.client.TileStatePacket;
import cofh.lib.energy.EnergyStorageCoFH;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.Utils;
import cofh.thermal.core.util.recipes.internal.IMachineRecipe;
import cofh.thermal.core.util.recipes.internal.IRecipeCatalyst;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static cofh.lib.util.constants.Constants.BASE_CHANCE;
import static cofh.lib.util.constants.NBTTags.TAG_PROCESS;
import static cofh.lib.util.constants.NBTTags.TAG_PROCESS_MAX;
import static cofh.lib.util.helpers.FluidHelper.fluidsEqual;
import static cofh.lib.util.helpers.ItemHelper.cloneStack;
import static cofh.lib.util.helpers.ItemHelper.itemsEqualWithTags;

public abstract class MachineTileProcess extends MachineTileReconfigurable {

    protected IMachineRecipe curRecipe;
    protected IRecipeCatalyst curCatalyst;
    protected List<Integer> itemInputCounts = new ArrayList<>();
    protected List<Integer> fluidInputCounts = new ArrayList<>();

    protected int process;
    protected int processMax;

    protected int energyUse = 20;

    public MachineTileProcess(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
        energyStorage = new EnergyStorageCoFH(20000);
    }

    @Override
    public void tick() {

        // TODO: TESTING ONLY
        energyStorage.modify(100);

        boolean curActive = isActive;

        if (isActive) {
            processTick();
            if (canProcessFinish()) {
                processFinish();
                transferOutput();
                transferInput();
                if (!redstoneControl.getState() || !canProcessStart()) {
                    processOff();
                } else {
                    processStart();
                }
            } else if (energyStorage.isEmpty()) {
                processOff();
            }
        } else if (redstoneControl.getState()) {
            if (timeCheck()) {
                transferOutput();
                transferInput();
            }
            if (timeCheckQuarter() && canProcessStart()) {
                processStart();
                processTick();
                isActive = true;
            }
        }
        updateActiveState(curActive);
        chargeEnergy();
    }

    // region PROCESS
    protected boolean canProcessStart() {

        if (energyStorage.isEmpty()) {
            return false;
        }
        if (!validateInputs()) {
            return false;
        }
        return validateOutputs();
    }

    protected boolean canProcessFinish() {

        return process <= 0;
    }

    protected void processStart() {

        processMax = curRecipe.getEnergy(this);
        process = processMax;

        if (cacheRenderFluid()) {
            TileStatePacket.sendToClient(this);
        }
    }

    protected void processFinish() {

        if (!validateInputs()) {
            processOff();
            return;
        }
        resolveOutputs();
        resolveInputs();
        markDirty();
        energyStorage.modify(-process);
    }

    protected void processOff() {

        process = 0;
        isActive = false;
        wasActive = true;
        clearRecipe();
        if (world != null) {
            timeTracker.markTime(world);
        }
    }

    protected int processTick() {

        if (process <= 0) {
            return 0;
        }
        int energy = Math.min(energyStorage.getEnergyStored(), energyUse);
        energyStorage.modify(-energy);
        process -= energy;
        return energy;
    }
    // endregion

    // region HELPERS
    protected boolean cacheRecipe() {

        return true;
    }

    protected void clearRecipe() {

        curRecipe = null;
        curCatalyst = null;
        itemInputCounts = new ArrayList<>();
        fluidInputCounts = new ArrayList<>();
    }

    protected boolean validateInputs() {

        if (!cacheRecipe()) {
            return false;
        }
        List<? extends ItemStorageCoFH> slotInputs = inputSlots();
        for (int i = 0; i < slotInputs.size() && i < itemInputCounts.size(); ++i) {
            int inputCount = itemInputCounts.get(i);
            if (inputCount > 0 && slotInputs.get(i).getItemStack().getCount() < inputCount) {
                return false;
            }
        }
        List<? extends FluidStorageCoFH> tankInputs = inputTanks();
        for (int i = 0; i < tankInputs.size() && i < fluidInputCounts.size(); ++i) {
            int inputCount = fluidInputCounts.get(i);
            FluidStack input = tankInputs.get(i).getFluidStack();
            if (inputCount > 0 && (input.isEmpty() || input.getAmount() < inputCount)) {
                return false;
            }
        }
        return true;
    }

    protected boolean validateOutputs() {

        // ITEMS
        List<? extends ItemStorageCoFH> slotOutputs = outputSlots();
        List<ItemStack> recipeOutputItems = curRecipe.getOutputItems(this);
        boolean[] used = new boolean[outputSlots().size()];
        for (ItemStack recipeOutput : recipeOutputItems) {
            boolean matched = false;
            for (int i = 0; i < slotOutputs.size(); ++i) {
                if (used[i]) {
                    continue;
                }
                ItemStack output = slotOutputs.get(i).getItemStack();
                if (output.getCount() >= output.getMaxStackSize()) {
                    continue;
                }
                if (itemsEqualWithTags(output, recipeOutput)) {
                    used[i] = true;
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                for (int i = 0; i < slotOutputs.size(); ++i) {
                    if (used[i]) {
                        continue;
                    }
                    if (slotOutputs.get(i).isEmpty()) {
                        used[i] = true;
                        matched = true;
                        break;
                    }
                }
            }
            if (!matched) {
                return false;
            }
        }
        // FLUIDS
        List<? extends FluidStorageCoFH> tankOutputs = outputTanks();
        List<FluidStack> recipeOutputFluids = curRecipe.getOutputFluids(this);
        used = new boolean[outputTanks().size()];
        for (FluidStack recipeOutput : recipeOutputFluids) {
            boolean matched = false;
            for (int i = 0; i < tankOutputs.size(); ++i) {
                if (used[i] || tankOutputs.get(i).getSpace() <= 0) {
                    continue;
                }
                FluidStack output = tankOutputs.get(i).getFluidStack();
                if (fluidsEqual(output, recipeOutput)) {
                    used[i] = true;
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                for (int i = 0; i < tankOutputs.size(); ++i) {
                    if (used[i]) {
                        continue;
                    }
                    if (tankOutputs.get(i).isEmpty()) {
                        used[i] = true;
                        matched = true;
                        break;
                    }
                }
            }
            if (!matched) {
                return false;
            }
        }
        return true;
    }

    protected void resolveOutputs() {

        List<ItemStack> recipeOutputItems = curRecipe.getOutputItems(this);
        List<FluidStack> recipeOutputFluids = curRecipe.getOutputFluids(this);
        List<Float> recipeOutputChances = curRecipe.getOutputItemChances(this);

        // Output Items
        for (int i = 0; i < recipeOutputItems.size(); ++i) {
            ItemStack recipeOutput = recipeOutputItems.get(i);
            float chance = recipeOutputChances.get(i);
            int outputCount = chance <= BASE_CHANCE ? recipeOutput.getCount() : (int) chance;
            while (world.rand.nextFloat() < chance) {
                boolean matched = false;
                for (ItemStorageCoFH slot : outputSlots()) {
                    ItemStack output = slot.getItemStack();
                    if (itemsEqualWithTags(output, recipeOutput) && output.getCount() < output.getMaxStackSize()) {
                        output.grow(outputCount);
                        matched = true;
                        break;
                    }
                }
                if (!matched) {
                    for (ItemStorageCoFH slot : outputSlots()) {
                        if (slot.isEmpty()) {
                            slot.setItemStack(cloneStack(recipeOutput, outputCount));
                            break;
                        }
                    }
                }
                chance -= BASE_CHANCE * outputCount;
                outputCount = 1;
            }
        }
        // Output Fluids
        for (FluidStack recipeOutput : recipeOutputFluids) {
            boolean matched = false;
            for (FluidStorageCoFH tank : outputTanks()) {
                FluidStack output = tank.getFluidStack();
                if (fluidsEqual(output, recipeOutput)) {
                    output.setAmount(output.getAmount() + recipeOutput.getAmount());
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                for (FluidStorageCoFH tank : outputTanks()) {
                    if (tank.isEmpty()) {
                        tank.setFluidStack(recipeOutput.copy());
                        break;
                    }
                }
            }
        }
    }

    protected void resolveInputs() {

        // Input Items
        for (int i = 0; i < itemInputCounts.size(); ++i) {
            inputSlots().get(i).modify(-itemInputCounts.get(i));
        }
        // Input Fluids
        for (int i = 0; i < fluidInputCounts.size(); ++i) {
            inputTanks().get(i).modify(-fluidInputCounts.get(i));
        }
    }
    // endregion

    // region GUI
    public int getScaledProgress(int scale) {

        if (!isActive || processMax <= 0 || process <= 0) {
            return 0;
        }
        return scale * (processMax - process) / processMax;
    }

    public int getScaledSpeed(int scale) {

        // TODO: Fix
        if (!isActive) {
            return 0;
        }
        return scale;
        //		double power = energyStorage.getEnergyStored() / energyConfig.energyRamp;
        //		power = MathHelper.clip(power, energyConfig.minPower, energyConfig.maxPower);
        //		return MathHelper.round(scale * power / energyConfig.maxPower);
    }
    // endregion

    // region NETWORK
    @Override
    public PacketBuffer getGuiPacket(PacketBuffer buffer) {

        super.getGuiPacket(buffer);

        buffer.writeInt(processMax);
        buffer.writeInt(process);

        return buffer;
    }

    @Override
    public void handleGuiPacket(PacketBuffer buffer) {

        super.handleGuiPacket(buffer);

        processMax = buffer.readInt();
        process = buffer.readInt();
    }
    // endregion

    // region NBT
    @Override
    public void read(CompoundNBT nbt) {

        super.read(nbt);

        processMax = nbt.getInt(TAG_PROCESS_MAX);
        process = nbt.getInt(TAG_PROCESS);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {

        super.write(nbt);

        nbt.putInt(TAG_PROCESS_MAX, processMax);
        nbt.putInt(TAG_PROCESS, process);

        return nbt;
    }
    // endregion

    // region ITileCallback
    @Override
    public void onInventoryChange(int slot) {

        if (Utils.isServerWorld(world) && slot < inventory.getInputSlots().size()) {
            if (isActive) {
                IMachineRecipe tempRecipe = curRecipe;
                IRecipeCatalyst tempCatalyst = curCatalyst;
                if (!validateInputs() || tempRecipe != curRecipe || tempCatalyst != curCatalyst) {
                    processOff();
                }
            }
        }
    }
    // endregion
}
