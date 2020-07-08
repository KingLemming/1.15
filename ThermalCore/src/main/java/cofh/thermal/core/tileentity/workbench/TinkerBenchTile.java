package cofh.thermal.core.tileentity.workbench;

import cofh.core.network.packet.bidirectional.TileMiscPacket;
import cofh.lib.energy.EnergyStorageCoFH;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.AugmentableHelper;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.FluidHelper;
import cofh.thermal.core.inventory.container.workbench.TinkerBenchContainer;
import cofh.thermal.core.tileentity.ThermalTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.INPUT;
import static cofh.lib.util.StorageGroup.INTERNAL;
import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.lib.util.constants.NBTTags.TAG_MODE;
import static cofh.thermal.core.common.ThermalConfig.workbenchAugments;
import static cofh.thermal.core.init.TCoreReferences.TINKER_BENCH_TILE;

public class TinkerBenchTile extends ThermalTileBase implements ITickableTileEntity {

    protected ItemStorageCoFH tinkerSlot = new ItemStorageCoFH(1, item -> AugmentableHelper.isAugmentableItem(item) || EnergyHelper.hasEnergyHandlerCap(item) || FluidHelper.hasFluidHandlerCap(item));
    protected ItemStorageCoFH chargeSlot = new ItemStorageCoFH(1, EnergyHelper::hasEnergyHandlerCap);
    protected ItemStorageCoFH tankSlot = new ItemStorageCoFH(1, FluidHelper::hasFluidHandlerCap);

    protected FluidStorageCoFH tank = new FluidStorageCoFH(TANK_MEDIUM);

    protected static final int BASE_ENERGY = 200000;
    protected static final int BASE_TRANSFER = 1000;

    protected static final byte REPLENISH = 0;
    protected static final byte TINKER = 1;

    protected byte mode;
    protected boolean pause;

    // TODO: Cap caching? Premature optimization possibly; revisit.
    //    protected LazyOptional<?> tinkerEnergyCap = LazyOptional.empty();
    //    protected LazyOptional<?> tinkerFluidCap = LazyOptional.empty();
    //    protected LazyOptional<?> chargeEnergyCap = LazyOptional.empty();
    //    protected LazyOptional<?> tankFluidCap = LazyOptional.empty();

    public TinkerBenchTile() {

        super(TINKER_BENCH_TILE);

        energyStorage = new EnergyStorageCoFH(BASE_ENERGY, BASE_TRANSFER);

        inventory.addSlot(tinkerSlot, INTERNAL);
        inventory.addSlot(chargeSlot, INTERNAL);
        inventory.addSlot(tankSlot, INTERNAL);

        tankInv.addTank(tank, INPUT);

        addAugmentSlots(workbenchAugments);
        initHandlers();
    }

    @Override
    public void tick() {

        if (redstoneControl.getState()) {
            chargeEnergy();
            fillFluid();
        }
    }

    public void setPause(boolean pause) {

        this.pause = pause;
    }

    public void toggleMode() {

        ++mode;
        mode %= 2;
        TileMiscPacket.sendToServer(this);
        ++mode;

    }

    protected void chargeEnergy() {

        if (!chargeSlot.isEmpty()) {
            int maxTransfer = Math.min(energyStorage.getMaxReceive(), energyStorage.getSpace());
            chargeSlot.getItemStack()
                    .getCapability(CapabilityEnergy.ENERGY, null)
                    .ifPresent(c -> energyStorage.receiveEnergy(c.extractEnergy(maxTransfer, false), false));
        }
        if (!tinkerSlot.isEmpty() && mode == REPLENISH && !pause) {
            int maxTransfer = Math.min(energyStorage.getMaxExtract(), energyStorage.getEnergyStored());
            tinkerSlot.getItemStack()
                    .getCapability(CapabilityEnergy.ENERGY, null)
                    .ifPresent(c -> energyStorage.extractEnergy(c.receiveEnergy(maxTransfer, false), false));
        }
    }

    protected void fillFluid() {

        if (!tankSlot.isEmpty()) {
            tankSlot.getItemStack()
                    .getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                    .ifPresent(c -> {
                        c.drain(tank.fill(c.getFluidInTank(0), IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
                        tankSlot.setItemStack(c.getContainer());
                    });
        }
        if (!tinkerSlot.isEmpty() && mode == REPLENISH && !pause) {
            tinkerSlot.getItemStack()
                    .getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                    .ifPresent(c -> {
                        tank.drain(c.fill(tank.getFluidStack(), IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
                        tinkerSlot.setItemStack(c.getContainer());
                    });
        }
    }

    // region NETWORK
    @Override
    public PacketBuffer getGuiPacket(PacketBuffer buffer) {

        super.getGuiPacket(buffer);

        buffer.writeByte(mode);

        return buffer;
    }

    @Override
    public PacketBuffer getMiscPacket(PacketBuffer buffer) {

        super.getMiscPacket(buffer);

        buffer.writeByte(mode);

        return buffer;
    }

    @Override
    public void handleGuiPacket(PacketBuffer buffer) {

        super.handleGuiPacket(buffer);

        mode = buffer.readByte();
    }

    @Override
    public void handleMiscPacket(PacketBuffer buffer) {

        super.handleMiscPacket(buffer);

        mode = buffer.readByte();
    }
    // endregion

    // region NBT
    @Override
    public void read(CompoundNBT nbt) {

        super.read(nbt);

        mode = nbt.getByte(TAG_MODE);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {

        super.write(nbt);

        nbt.putByte(TAG_MODE, mode);

        return nbt;
    }
    // endregion

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new TinkerBenchContainer(i, world, pos, inventory, player);
    }

}
