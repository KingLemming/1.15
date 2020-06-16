package cofh.thermal.core.tileentity;

import cofh.core.network.packet.client.TileStatePacket;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.Utils;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.thermal.core.util.recipes.internal.IMachineRecipe;
import cofh.thermal.core.util.recipes.internal.IRecipeCatalyst;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static cofh.lib.util.constants.Constants.BASE_CHANCE;
import static cofh.lib.util.constants.NBTTags.*;
import static cofh.lib.util.helpers.FluidHelper.fluidsEqual;
import static cofh.lib.util.helpers.ItemHelper.cloneStack;
import static cofh.lib.util.helpers.ItemHelper.itemsEqualWithTags;

public abstract class MachineTileBasicProcess extends MachineTileBasic implements ITickableTileEntity {

    protected IMachineRecipe curRecipe;
    protected IRecipeCatalyst curCatalyst;
    protected List<Integer> itemInputCounts = new ArrayList<>();
    protected List<Integer> fluidInputCounts = new ArrayList<>();

    protected int process;
    protected int processMax;
    protected int fuel;
    protected int fuelMax;
    protected int fuelTicks;

    protected static final int ENERGY_USE = 10;

    public static int ticksPerDrain = 64;
    public static int drainAmount = 10;

    public MachineTileBasicProcess(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
    }

    @Override
    public void tick() {

        boolean curActive = isActive;

        if (isActive) {
            processTick();
            if (canProcessFinish()) {
                processFinish();
                if (!canProcessStart()) {
                    processOff();
                } else {
                    processStart();
                }
            } else if (!hasFuel() && !reFuel()) {
                processOff();
            }
        } else {
            if (timeCheckQuarter() && canProcessStart()) {
                processStart();
                processTick();
                isActive = true;
            }
        }
        consumeFuel();
        updateActiveState(curActive);
    }

    // region PROCESS
    protected boolean canProcessStart() {

        if (!validateInputs()) {
            return false;
        }
        return validateOutputs() && (hasFuel() || reFuel());
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
        process -= ENERGY_USE;
        return ENERGY_USE;
    }

    protected boolean hasFuel() {

        return fuel > 0;
    }

    protected boolean reFuel() {

        ItemStack fuelStack = fuelSlot.getItemStack();
        if (fuel <= 0 && !fuelStack.isEmpty() && EnergyHelper.validFurnaceFuel(fuelStack)) {
            fuel = fuelMax = EnergyHelper.getEnergyFurnaceFuel(fuelStack);
            fuelSlot.consume();
        }
        return hasFuel();
    }

    protected void consumeFuel() {

        if (fuel >= 0) {
            fuel -= ENERGY_USE;
        }
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
    @Override
    public int getScaledDuration(int scale) {

        if (fuelMax <= 0 || fuel <= 0) {
            return 0;
        }
        return scale * fuel / fuelMax;
    }

    @Override
    public int getScaledProgress(int scale) {

        if (!isActive || processMax <= 0 || process <= 0) {
            return 0;
        }
        return scale * (processMax - process) / processMax;
    }

    @Override
    public int getScaledSpeed(int scale) {

        if (!isActive || fuelMax <= 0 || fuel <= 0) {
            return 0;
        }
        return scale;
    }
    // endregion

    // region NETWORK
    @Override
    public PacketBuffer getGuiPacket(PacketBuffer buffer) {

        super.getGuiPacket(buffer);

        buffer.writeInt(processMax);
        buffer.writeInt(process);
        buffer.writeInt(fuelMax);
        buffer.writeInt(fuel);

        return buffer;
    }

    @Override
    public void handleGuiPacket(PacketBuffer buffer) {

        super.handleGuiPacket(buffer);

        processMax = buffer.readInt();
        process = buffer.readInt();
        fuelMax = buffer.readInt();
        fuel = buffer.readInt();
    }
    // endregion

    // region NBT
    @Override
    public void read(CompoundNBT nbt) {

        super.read(nbt);

        processMax = nbt.getInt(TAG_PROCESS_MAX);
        process = nbt.getInt(TAG_PROCESS);
        fuelMax = nbt.getInt(TAG_FUEL_MAX);
        fuel = nbt.getInt(TAG_FUEL);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {

        super.write(nbt);

        nbt.putInt(TAG_PROCESS_MAX, processMax);
        nbt.putInt(TAG_PROCESS, process);
        nbt.putInt(TAG_FUEL_MAX, fuelMax);
        nbt.putInt(TAG_FUEL, fuel);

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
        super.onInventoryChange(slot);
    }
    // endregion
}
