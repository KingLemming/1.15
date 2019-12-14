package cofh.thermal.core.block;

import cofh.core.network.packet.client.TileControlPacket;
import cofh.core.network.packet.client.TileStatePacket;
import cofh.lib.block.TileCoFH;
import cofh.lib.energy.EnergyStorageCoFH;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.fluid.ManagedTankInv;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.inventory.ManagedItemInv;
import cofh.lib.util.StorageGroup;
import cofh.lib.util.TimeTracker;
import cofh.lib.util.control.IRedstoneControllableTile;
import cofh.lib.util.control.ISecurableTile;
import cofh.lib.util.control.RedstoneControlModule;
import cofh.lib.util.control.SecurityControlModule;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static cofh.lib.util.constants.Constants.ACTIVE;
import static cofh.lib.util.constants.Tags.*;

public abstract class AbstractTileBase extends TileCoFH implements ISecurableTile, IRedstoneControllableTile, INamedContainerProvider {

    protected TimeTracker timeTracker = new TimeTracker();
    protected ManagedItemInv inventory = new ManagedItemInv(this, TAG_ITEM_INV);
    protected ManagedTankInv tankInv = new ManagedTankInv(this, TAG_TANK_INV);
    protected EnergyStorageCoFH energyStorage = new EnergyStorageCoFH(0);

    protected SecurityControlModule securityControl = new SecurityControlModule(this);
    protected RedstoneControlModule redstoneControl = new RedstoneControlModule(this);

    public boolean isActive;
    public boolean wasActive;

    public AbstractTileBase(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
    }

    // region HELPERS
    public int invSize() {

        return inventory.getSlots();
    }

    public ManagedItemInv getInventory() {

        return inventory;
    }

    protected void updateActiveState(boolean curActive) {

        // TODO: Config time
        if (!wasActive && curActive != isActive || wasActive && (timeTracker.hasDelayPassed(world, 40) || timeTracker.notSet())) {
            wasActive = false;
            world.setBlockState(pos, getBlockState().with(ACTIVE, isActive));
            TileStatePacket.sendToClient(this);
        }
    }

    protected List<? extends ItemStorageCoFH> getInputSlots() {

        return inventory.getInputSlots();
    }

    protected List<? extends FluidStorageCoFH> getInputTanks() {

        return tankInv.getInputTanks();
    }

    protected List<? extends ItemStorageCoFH> getOutputSlots() {

        return inventory.getOutputSlots();
    }

    protected List<? extends FluidStorageCoFH> getOutputTanks() {

        return tankInv.getOutputTanks();
    }

    protected List<? extends ItemStorageCoFH> getInternalSlots() {

        return inventory.getInternalSlots();
    }

    @Override
    protected void neighborChanged() {

        if (world != null) {
            redstoneControl.setPower(world.getRedstonePowerFromNeighbors(pos));
        }
    }
    // endregion

    // region GUI
    public ItemStorageCoFH getSlot(int slot) {

        return inventory.getSlot(slot);
    }

    public FluidStorageCoFH getTank(int tank) {

        return tankInv.getTank(tank);
    }

    public EnergyStorageCoFH getEnergyStorage() {

        return energyStorage;
    }

    public int getScaledDuration(int scale) {

        return isActive ? scale : 0;
    }

    public int getScaledProgress(int scale) {

        return isActive ? scale : 0;
    }

    public int getScaledSpeed(int scale) {

        return isActive ? scale : 0;
    }
    // endregion

    // region NETWORK
    @Override
    public PacketBuffer getControlPacket(PacketBuffer buffer) {

        super.getControlPacket(buffer);

        securityControl.writeToBuffer(buffer);
        redstoneControl.writeToBuffer(buffer);

        return buffer;
    }

    @Override
    public PacketBuffer getGuiPacket(PacketBuffer buffer) {

        super.getGuiPacket(buffer);

        buffer.writeBoolean(isActive);
        buffer.writeInt(energyStorage.getMaxEnergyStored());
        buffer.writeInt(energyStorage.getEnergyStored());

        for (int i = 0; i < tankInv.getTanks(); i++) {
            // TODO: Forge
            // buffer.writeFluidStack(tankInv.get(i));
        }
        return buffer;
    }

    @Override
    public PacketBuffer getStatePacket(PacketBuffer buffer) {

        super.getControlPacket(buffer);

        buffer.writeBoolean(isActive);

        return buffer;
    }

    @Override
    public void handleControlPacket(PacketBuffer buffer) {

        super.handleControlPacket(buffer);

        securityControl.readFromBuffer(buffer);
        redstoneControl.readFromBuffer(buffer);
    }

    @Override
    public void handleGuiPacket(PacketBuffer buffer) {

        super.handleGuiPacket(buffer);

        isActive = buffer.readBoolean();
        energyStorage.setCapacity(buffer.readInt());
        energyStorage.setEnergyStored(buffer.readInt());

        for (int i = 0; i < tankInv.getTanks(); i++) {
            // TODO: Forge
            // tankInv.set(i, buffer.readFluidStack());
        }
    }

    @Override
    public void handleStatePacket(PacketBuffer buffer) {

        super.handleControlPacket(buffer);

        isActive = buffer.readBoolean();
    }
    // endregion

    // region NBT
    @Override
    public void read(CompoundNBT nbt) {

        super.read(nbt);

        isActive = nbt.getBoolean(TAG_ACTIVE);
        wasActive = nbt.getBoolean(TAG_ACTIVE_TRACK);

        inventory.readFromNBT(nbt);
        tankInv.readFromNBT(nbt);
        energyStorage.readFromNBT(nbt);

        securityControl.read(nbt.getCompound(TAG_SECURITY));
        redstoneControl.read(nbt.getCompound(TAG_REDSTONE));
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {

        super.write(nbt);

        nbt.putBoolean(TAG_ACTIVE, isActive);
        nbt.putBoolean(TAG_ACTIVE_TRACK, wasActive);

        inventory.writeToNBT(nbt);
        tankInv.writeToNBT(nbt);
        energyStorage.writeToNBT(nbt);

        nbt.put(TAG_SECURITY, securityControl.write(new CompoundNBT()));
        nbt.put(TAG_REDSTONE, redstoneControl.write(new CompoundNBT()));

        return nbt;
    }
    // endregion

    // region INamedContainerProvider
    @Override
    public ITextComponent getDisplayName() {

        return new StringTextComponent(getType().getRegistryName().getPath());
    }
    // endregion

    // region ITileCallback
    @Override
    public void onControlUpdate() {

        TileControlPacket.sendToClient(this);
    }
    // endregion

    // region MODULES
    @Override
    public SecurityControlModule securityControl() {

        return securityControl;
    }

    @Override
    public RedstoneControlModule redstoneControl() {

        return redstoneControl;
    }
    // endregion

    // region CAPABILITIES
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if (cap == CapabilityEnergy.ENERGY && energyStorage.getMaxEnergyStored() > 0) {
            return LazyOptional.of(() -> energyStorage).cast();
        }
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && inventory.hasSlots()) {
            return LazyOptional.of(() -> inventory.getHandler(StorageGroup.ACCESSIBLE)).cast();
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && tankInv.hasTanks()) {
            return LazyOptional.of(() -> tankInv.getHandler(StorageGroup.ACCESSIBLE)).cast();
        }
        return super.getCapability(cap, side);
    }
    // endregion
}
