package cofh.thermal.core.tileentity;

import cofh.core.network.packet.client.TileControlPacket;
import cofh.core.network.packet.client.TileStatePacket;
import cofh.lib.energy.EnergyStorageCoFH;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.fluid.ManagedTankInv;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.inventory.ManagedItemInv;
import cofh.lib.tileentity.TileCoFH;
import cofh.lib.util.StorageGroup;
import cofh.lib.util.TimeTracker;
import cofh.lib.util.Utils;
import cofh.lib.util.control.IRedstoneControllableTile;
import cofh.lib.util.control.ISecurableTile;
import cofh.lib.util.control.RedstoneControlModule;
import cofh.lib.util.control.SecurityControlModule;
import cofh.thermal.core.util.IThermalInventory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.StorageGroup.INTERNAL;
import static cofh.lib.util.constants.Constants.ACTIVE;
import static cofh.lib.util.constants.NBTTags.*;

public abstract class ThermalTileBase extends TileCoFH implements ISecurableTile, IRedstoneControllableTile, INamedContainerProvider, IThermalInventory {

    protected TimeTracker timeTracker = new TimeTracker();
    protected ManagedItemInv inventory = new ManagedItemInv(this, TAG_ITEM_INV);
    protected ManagedTankInv tankInv = new ManagedTankInv(this, TAG_TANK_INV);
    protected EnergyStorageCoFH energyStorage = new EnergyStorageCoFH(0);

    protected SecurityControlModule securityControl = new SecurityControlModule(this);
    protected RedstoneControlModule redstoneControl = new RedstoneControlModule(this);

    protected List<ItemStorageCoFH> augments = new ArrayList<>();
    protected Set<String> augmentTypes = new HashSet<>();

    public boolean isActive;
    public boolean wasActive;

    public ThermalTileBase(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
    }

    // TODO: Does this need to exist?
    //    @Override
    //    public void remove() {
    //
    //        super.remove();
    //        energyCap.invalidate();
    //        itemCap.invalidate();
    //        fluidCap.invalidate();
    //    }

    // region HELPERS
    @Override
    public int invSize() {

        return inventory.getSlots();
    }

    @Override
    public int augSize() {

        return augments.size();
    }

    public ManagedItemInv getItemInv() {

        return inventory;
    }

    public ManagedTankInv getTankInv() {

        return tankInv;
    }

    protected void initHandlers() {

        inventory.initHandlers();
        tankInv.initHandlers();
    }

    protected void updateAugmentState() {

    }

    protected void updateActiveState(boolean curActive) {

        // TODO: Config time delay
        if (!wasActive && curActive != isActive || wasActive && (timeTracker.hasDelayPassed(world, 40) || timeTracker.notSet())) {
            wasActive = false;
            world.setBlockState(pos, getBlockState().with(ACTIVE, isActive));
            TileStatePacket.sendToClient(this);
        }
    }

    @Override
    public List<? extends ItemStorageCoFH> inputSlots() {

        return inventory.getInputSlots();
    }

    @Override
    public List<? extends FluidStorageCoFH> inputTanks() {

        return tankInv.getInputTanks();
    }

    protected List<? extends ItemStorageCoFH> outputSlots() {

        return inventory.getOutputSlots();
    }

    protected List<? extends FluidStorageCoFH> outputTanks() {

        return tankInv.getOutputTanks();
    }

    protected List<? extends ItemStorageCoFH> internalSlots() {

        return inventory.getInternalSlots();
    }

    protected List<? extends FluidStorageCoFH> internalTanks() {

        return tankInv.getInternalTanks();
    }

    @Override
    public void neighborChanged(Block blockIn, BlockPos fromPos) {

        if (world != null && redstoneControl.isControllable()) {
            redstoneControl.setPower(world.getRedstonePowerFromNeighbors(pos));
        }
    }

    @Override
    public void onPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

        super.onPlacedBy(worldIn, pos, state, placer, stack);

        System.out.println("BLOCK PLACED - TIME TO INSTALL AUGMENTS");
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {

        System.out.println("REPLACEMENT CALL - DROP INVENTORY");

        super.onReplaced(state, worldIn, pos, newState, isMoving);
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

    public FluidStack getRenderFluid() {

        return FluidStack.EMPTY;
    }

    public int getScaledDuration() {

        return getScaledDuration(DURATION);
    }

    public int getScaledDuration(int scale) {

        return isActive ? scale : 0;
    }

    public int getScaledProgress() {

        return getScaledProgress(PROGRESS);
    }

    public int getScaledProgress(int scale) {

        return isActive ? scale : 0;
    }

    public int getScaledSpeed() {

        return getScaledSpeed(SPEED);
    }

    public int getScaledSpeed(int scale) {

        return isActive ? scale : 0;
    }

    @Override
    public boolean clearEnergy(int coil) {

        if (isActive) {
            return false;
        }
        return energyStorage.clear();
    }

    @Override
    public boolean clearSlot(int slot) {

        if (isActive || slot >= inventory.getSlots()) {
            return false;
        }
        return inventory.getSlot(slot).clear();
    }

    @Override
    public boolean clearTank(int tank) {

        if (isActive || tank >= tankInv.getTanks()) {
            return false;
        }
        return tankInv.getTank(tank).clear();
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

        for (int i = 0; i < tankInv.getTanks(); ++i) {
            buffer.writeFluidStack(tankInv.get(i));
        }
        return buffer;
    }

    @Override
    public PacketBuffer getStatePacket(PacketBuffer buffer) {

        super.getStatePacket(buffer);

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

        for (int i = 0; i < tankInv.getTanks(); ++i) {
            tankInv.set(i, buffer.readFluidStack());
        }
    }

    @Override
    public void handleStatePacket(PacketBuffer buffer) {

        super.handleStatePacket(buffer);

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
        updateAugmentState();

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
    public void onInventoryChange(int slot) {

        /* Implicit requirement here that augments always come LAST in slot order.
        This isn't a bad assumption/rule though, as it's a solid way to handle it.*/
        if (Utils.isServerWorld(world) && slot >= invSize() - augSize()) {
            updateAugmentState();
        }
    }

    @Override
    public void onControlUpdate() {

        TileControlPacket.sendToClient(this);
    }
    // endregion

    // region AUGMENTS

    /**
     * This should be called AFTER all other slots have been added.
     * Augment slots are added to the INTERNAL inventory category.
     *
     * @param numAugments Number of augment slots to add.
     */
    protected void addAugmentSlots(int numAugments) {

        for (int i = 0; i < numAugments; ++i) {
            ItemStorageCoFH slot = new ItemStorageCoFH();
            augments.add(slot);
            inventory.addSlot(slot, INTERNAL);
        }
        ((ArrayList<ItemStorageCoFH>) augments).trimToSize();
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
    protected LazyOptional<?> energyCap = LazyOptional.empty();
    protected LazyOptional<?> itemCap = LazyOptional.empty();
    protected LazyOptional<?> fluidCap = LazyOptional.empty();

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if (cap == CapabilityEnergy.ENERGY && energyStorage.getMaxEnergyStored() > 0) {
            return getEnergyCapability(side);
        }
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && inventory.hasSlots()) {
            return getItemHandlerCapability(side);
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && tankInv.hasTanks()) {
            return getFluidHandlerCapability(side);
        }
        return super.getCapability(cap, side);
    }

    protected <T> LazyOptional<T> getEnergyCapability(@Nullable Direction side) {

        if (!energyCap.isPresent()) {
            energyCap = LazyOptional.of(() -> energyStorage);
        }
        return energyCap.cast();
    }

    protected <T> LazyOptional<T> getItemHandlerCapability(@Nullable Direction side) {

        if (!itemCap.isPresent()) {
            itemCap = LazyOptional.of(() -> inventory.getHandler(StorageGroup.ACCESSIBLE));
        }
        return itemCap.cast();
    }

    protected <T> LazyOptional<T> getFluidHandlerCapability(@Nullable Direction side) {

        if (!fluidCap.isPresent()) {
            fluidCap = LazyOptional.of(() -> tankInv.getHandler(StorageGroup.ACCESSIBLE));
        }
        return fluidCap.cast();
    }
    // endregion
}
