package cofh.thermal.core.tileentity;

import cofh.core.energy.EnergyStorageCoFH;
import cofh.core.fluid.FluidStorageCoFH;
import cofh.core.fluid.ManagedTankInv;
import cofh.core.inventory.ItemStorageCoFH;
import cofh.core.inventory.ManagedItemInv;
import cofh.core.network.packet.client.TileControlPacket;
import cofh.core.network.packet.client.TileRedstonePacket;
import cofh.core.network.packet.client.TileStatePacket;
import cofh.core.tileentity.TileCoFH;
import cofh.core.util.StorageGroup;
import cofh.core.util.TimeTracker;
import cofh.core.util.Utils;
import cofh.core.util.control.IRedstoneControllableTile;
import cofh.core.util.control.ISecurableTile;
import cofh.core.util.control.RedstoneControlModule;
import cofh.core.util.control.SecurityControlModule;
import cofh.core.util.helpers.AugmentDataHelper;
import cofh.core.util.helpers.MathHelper;
import cofh.thermal.core.common.ThermalConfig;
import cofh.thermal.core.util.IThermalInventory;
import cofh.thermal.core.util.loot.TileNBTSync;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.model.data.ModelProperty;
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
import static cofh.core.util.StorageGroup.INTERNAL;
import static cofh.core.util.constants.Constants.*;
import static cofh.core.util.constants.NBTTags.*;
import static net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND;

public abstract class ThermalTileBase extends TileCoFH implements ISecurableTile, IRedstoneControllableTile, INamedContainerProvider, IThermalInventory {

    public static final ModelProperty<byte[]> SIDES = new ModelProperty<>();
    public static final ModelProperty<FluidStack> FLUID = new ModelProperty<>();
    public static final ModelProperty<ResourceLocation> UNDERLAY = new ModelProperty<>();

    protected static final int MIN_PROCESS_TICK = 5;
    protected static final int BASE_PROCESS_TICK = 20;
    protected static final int BASE_ENERGY = 20000;

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
    protected FluidStack renderFluid = FluidStack.EMPTY;

    public ThermalTileBase(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
    }

    // region BASE PARAMETERS
    protected int getBaseEnergyStorage() {

        return BASE_ENERGY;
    }

    protected int getBaseEnergyXfer() {

        return BASE_PROCESS_TICK * 4;
    }

    protected int getBaseProcessTick() {

        return BASE_PROCESS_TICK;
    }

    protected int getMinProcessTick() {

        return MIN_PROCESS_TICK;
    }
    // endregion

    // TODO: Does this need to exist?
    @Override
    public void remove() {

        super.remove();
        energyCap.invalidate();
        itemCap.invalidate();
        fluidCap.invalidate();
    }

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

    protected void updateActiveState(boolean curActive) {

        // TODO: Config time delay
        if (!wasActive && curActive != isActive || wasActive && (timeTracker.hasDelayPassed(world, 40) || timeTracker.notSet())) {
            wasActive = false;
            world.setBlockState(pos, getBlockState().with(ACTIVE, isActive));
            TileStatePacket.sendToClient(this);
        }
    }

    protected boolean cacheRenderFluid() {

        return false;
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
            TileRedstonePacket.sendToClient(this);
        }
    }

    @Override
    public void onPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

        super.onPlacedBy(worldIn, pos, state, placer, stack);

        updateAugmentState();
        onControlUpdate();
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState) {

        if (!ThermalConfig.keepItems.get()) {
            for (int i = 0; i < invSize() - augSize(); ++i) {
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(i));
            }
        }
        if (!ThermalConfig.keepAugments.get()) {
            for (int i = invSize() - augSize(); i < invSize(); ++i) {
                Utils.dropItemStackIntoWorldWithRandomness(inventory.getStackInSlot(i), worldIn, pos);
            }
        }
        super.onReplaced(state, worldIn, pos, newState);
    }

    public ItemStack createItemStackTag(ItemStack stack) {

        return TileNBTSync.applyToStack(stack, this);
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

        return renderFluid;
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

    public int getCurSpeed() {

        return -1;
    }

    public int getMaxSpeed() {

        return -1;
    }

    public double getEfficiency() {

        return -1.0D;
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

        buffer.writeFluidStack(renderFluid);

        return buffer;
    }

    @Override
    public PacketBuffer getGuiPacket(PacketBuffer buffer) {

        super.getGuiPacket(buffer);

        buffer.writeBoolean(isActive);
        buffer.writeFluidStack(renderFluid);
        buffer.writeInt(energyStorage.getMaxEnergyStored());
        buffer.writeInt(energyStorage.getEnergyStored());

        for (int i = 0; i < tankInv.getTanks(); ++i) {
            buffer.writeFluidStack(tankInv.get(i));
        }
        return buffer;
    }

    @Override
    public PacketBuffer getRedstonePacket(PacketBuffer buffer) {

        super.getRedstonePacket(buffer);

        buffer.writeInt(redstoneControl.getPower());

        return buffer;
    }

    @Override
    public PacketBuffer getStatePacket(PacketBuffer buffer) {

        super.getStatePacket(buffer);

        buffer.writeBoolean(isActive);
        buffer.writeFluidStack(renderFluid);

        return buffer;
    }

    @Override
    public void handleControlPacket(PacketBuffer buffer) {

        super.handleControlPacket(buffer);

        securityControl.readFromBuffer(buffer);
        redstoneControl.readFromBuffer(buffer);

        renderFluid = buffer.readFluidStack();
    }

    @Override
    public void handleGuiPacket(PacketBuffer buffer) {

        super.handleGuiPacket(buffer);

        isActive = buffer.readBoolean();
        renderFluid = buffer.readFluidStack();
        energyStorage.setCapacity(buffer.readInt());
        energyStorage.setEnergyStored(buffer.readInt());

        for (int i = 0; i < tankInv.getTanks(); ++i) {
            tankInv.set(i, buffer.readFluidStack());
        }
    }

    @Override
    public void handleRedstonePacket(PacketBuffer buffer) {

        super.handleRedstonePacket(buffer);

        redstoneControl.setPower(buffer.readInt());
    }

    @Override
    public void handleStatePacket(PacketBuffer buffer) {

        super.handleStatePacket(buffer);

        isActive = buffer.readBoolean();
        renderFluid = buffer.readFluidStack();
    }
    // endregion

    // region NBT
    @Override
    public void read(CompoundNBT nbt) {

        super.read(nbt);

        isActive = nbt.getBoolean(TAG_ACTIVE);
        wasActive = nbt.getBoolean(TAG_ACTIVE_TRACK);

        inventory.read(nbt);

        if (nbt.contains(TAG_AUGMENTS)) {
            inventory.readSlotsUnordered(nbt.getList(TAG_AUGMENTS, TAG_COMPOUND), invSize() - augSize());
        }
        updateAugmentState();

        tankInv.read(nbt);
        energyStorage.read(nbt);

        securityControl.read(nbt);
        redstoneControl.read(nbt);

        renderFluid = FluidStack.loadFluidStackFromNBT(nbt.getCompound(TAG_RENDER_FLUID));
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {

        super.write(nbt);

        nbt.putBoolean(TAG_ACTIVE, isActive);
        nbt.putBoolean(TAG_ACTIVE_TRACK, wasActive);

        inventory.write(nbt);
        tankInv.write(nbt);
        energyStorage.write(nbt);

        securityControl.write(nbt);
        redstoneControl.write(nbt);

        if (!renderFluid.isEmpty()) {
            nbt.put(TAG_RENDER_FLUID, renderFluid.writeToNBT(new CompoundNBT()));
        }
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
        if (slot >= invSize() - augSize()) {
            updateAugmentState();
        }
    }

    @Override
    public void onControlUpdate() {

        TileControlPacket.sendToClient(this);
    }
    // endregion

    // region IConveyableData
    @Override
    public void readConveyableData(PlayerEntity player, CompoundNBT tag) {

        redstoneControl.read(tag);

        onControlUpdate();
    }

    @Override
    public void writeConveyableData(PlayerEntity player, CompoundNBT tag) {

        redstoneControl.write(tag);
    }
    // endregion

    // region AUGMENTS
    protected float baseMod = 1.0F;
    protected float energyStorageMod = 1.0F;
    protected float energyXferMod = 1.0F;
    protected float fluidStorageMod = 1.0F;

    /**
     * This should be called AFTER all other slots have been added.
     * Augment slots are added to the INTERNAL inventory category.
     *
     * @param numAugments Number of augment slots to add.
     */
    protected void addAugmentSlots(int numAugments) {

        for (int i = 0; i < numAugments; ++i) {
            ItemStorageCoFH slot = new ItemStorageCoFH(1, AugmentDataHelper::hasAugmentData);
            augments.add(slot);
            inventory.addSlot(slot, INTERNAL);
        }
        ((ArrayList<ItemStorageCoFH>) augments).trimToSize();
    }

    protected void updateAugmentState() {

        resetAttributes();
        for (ItemStorageCoFH slot : augments) {
            ItemStack augment = slot.getItemStack();
            CompoundNBT augmentData = AugmentDataHelper.getAugmentData(augment);
            if (augmentData == null) {
                continue;
            }
            augmentTypes.add(AugmentDataHelper.getAugmentType(augment));
            setAttributesFromAugment(augmentData);
        }
        finalizeAttributes();
    }

    protected void resetAttributes() {

        baseMod = 1.0F;
        energyStorageMod = 1.0F;
        energyXferMod = 1.0F;
        fluidStorageMod = 1.0F;
    }

    protected void setAttributesFromAugment(CompoundNBT augmentData) {

        baseMod = Math.max(getAttributeMod(augmentData, TAG_AUGMENT_BASE_MOD), baseMod);
        energyStorageMod = Math.max(getAttributeMod(augmentData, TAG_AUGMENT_ENERGY_STORAGE), energyStorageMod);
        energyXferMod = Math.max(getAttributeMod(augmentData, TAG_AUGMENT_ENERGY_XFER), energyXferMod);
        fluidStorageMod = Math.max(getAttributeMod(augmentData, TAG_AUGMENT_FLUID_STORAGE), fluidStorageMod);
    }

    protected void finalizeAttributes() {

        float scaleMin = AUG_SCALE_MIN;
        float scaleMax = AUG_SCALE_MAX;

        energyStorageMod = MathHelper.clamp(energyStorageMod, scaleMin, scaleMax);
        energyXferMod = MathHelper.clamp(energyXferMod, scaleMin, scaleMax);
        fluidStorageMod = MathHelper.clamp(fluidStorageMod, scaleMin, scaleMax);

        energyStorage.applyModifiers(getEnergyStorageMod(), getEnergyXferMod());
        for (int i = 0; i < tankInv.getTanks(); ++i) {
            tankInv.getTank(i).applyModifiers(getFluidStorageMod());
        }
    }

    protected float getEnergyStorageMod() {

        return energyStorageMod * baseMod;
    }

    protected float getEnergyXferMod() {

        return energyXferMod * baseMod;
    }

    protected float getFluidStorageMod() {

        return fluidStorageMod * baseMod;
    }

    protected float getAttributeMod(CompoundNBT augmentData, String key) {

        return augmentData.getFloat(key);
    }

    protected float getAttributeModWithDefault(CompoundNBT augmentData, String key, float defaultValue) {

        return augmentData.contains(key) ? augmentData.getFloat(key) : defaultValue;
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
