package cofh.thermal.core.tileentity;

import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.tileentity.TileCoFH;
import cofh.lib.util.control.IReconfigurableTile;
import cofh.lib.util.control.ITransferControllableTile;
import cofh.lib.util.control.ReconfigControlModule;
import cofh.lib.util.control.TransferControlModule;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.FluidHelper;
import cofh.lib.util.helpers.InventoryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.EmptyFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.EmptyHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.Constants.DIRECTIONS;
import static cofh.lib.util.constants.Constants.FACING_HORIZONTAL;
import static cofh.lib.util.constants.NBTTags.*;
import static cofh.lib.util.helpers.BlockHelper.*;

public abstract class MachineTileReconfigurable extends ThermalTileBase implements IReconfigurableTile, ITransferControllableTile {

    public static final ModelProperty<byte[]> SIDES = new ModelProperty<>();
    // public static final ModelProperty<FluidStack> FLUID = new ModelProperty<>();

    protected FluidStack renderFluid = FluidStack.EMPTY;
    protected ItemStorageCoFH chargeSlot = new ItemStorageCoFH(EnergyHelper::hasEnergyHandlerCap);

    protected int inputTracker;
    protected int outputTracker;

    protected ReconfigControlModule reconfigControl = new ReconfigControlModule(this);
    protected TransferControlModule transferControl = new TransferControlModule(this);

    public MachineTileReconfigurable(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
    }

    @Override
    public TileCoFH worldContext(BlockState state, IBlockReader world) {

        reconfigControl.setFacing(state.get(FACING_HORIZONTAL));
        updateSidedHandlers();

        return this;
    }

    @Override
    public void updateContainingBlockInfo() {

        super.updateContainingBlockInfo();
        updateSideCache();
    }

    // TODO: Does this need to exist?
    @Override
    public void remove() {

        super.remove();
        for (LazyOptional<?> handler : sidedItemCaps) {
            handler.invalidate();
        }
        for (LazyOptional<?> handler : sidedFluidCaps) {
            handler.invalidate();
        }
    }

    @Override
    public void neighborChanged(Block blockIn, BlockPos fromPos) {

        super.neighborChanged(blockIn, fromPos);
        // TODO: Handle caching of neighbor caps?
    }

    @Override
    public FluidStack getRenderFluid() {

        return renderFluid;
    }

    @Override
    protected void updateAugmentState() {

        // TODO: Finish
        //        augmentTypes.clear();
        //        for (ItemStorageCoFH slot : augments) {
        //            slot.getItemStack()
        //        }
    }

    protected void updateSideCache() {

        Direction prevFacing = getFacing();
        Direction curFacing = getBlockState().get(FACING_HORIZONTAL);

        if (prevFacing != curFacing) {
            reconfigControl.setFacing(curFacing);

            int iPrev = prevFacing.getIndex();
            int iFace = curFacing.getIndex();
            SideConfig[] sides = new SideConfig[6];

            if (iPrev == SIDE_RIGHT[iFace]) {
                for (int i = 0; i < 6; ++i) {
                    sides[i] = reconfigControl.getSideConfig()[ROTATE_CLOCK_Y[i]];
                }
            } else if (iPrev == SIDE_LEFT[iFace]) {
                for (int i = 0; i < 6; ++i) {
                    sides[i] = reconfigControl.getSideConfig()[ROTATE_COUNTER_Y[i]];
                }
            } else if (iPrev == SIDE_OPPOSITE[iFace]) {
                for (int i = 0; i < 6; ++i) {
                    sides[i] = reconfigControl.getSideConfig()[INVERT_AROUND_Y[i]];
                }
            }
            reconfigControl.setSideConfig(sides);
        }
        updateSidedHandlers();
    }

    public void chargeEnergy() {

        if (!chargeSlot.isEmpty()) {
            chargeSlot.getItemStack().getCapability(CapabilityEnergy.ENERGY, null).ifPresent(p -> energyStorage.receiveEnergy(p.extractEnergy(Math.min(energyStorage.getMaxReceive(), energyStorage.getSpace()), false), false));
        }
    }

    @Nonnull
    @Override
    public IModelData getModelData() {

        return new ModelDataMap.Builder()
                .withInitial(SIDES, reconfigControl().getRawSideConfig())
                // .withInitial(FLUID, renderFluid)
                .build();
    }

    // region HELPERS
    protected boolean cacheRenderFluid() {

        return false;
    }

    protected void transferInput() {

        if (!transferControl.getTransferIn()) {
            return;
        }
        int newTracker = inputTracker;
        boolean updateTracker = false;

        for (int i = inputTracker + 1; i <= inputTracker + 6; ++i) {
            Direction side = DIRECTIONS[i % 6];
            if (reconfigControl.getSideConfig(side).isInput()) {
                for (ItemStorageCoFH slot : inputSlots()) {
                    if (slot.getSpace() > 0) {
                        InventoryHelper.extractFromAdjacent(this, slot, slot.getSpace(), side);
                    }
                }
                for (FluidStorageCoFH tank : inputTanks()) {
                    if (tank.getSpace() > 0) {
                        FluidHelper.extractFromAdjacent(this, tank, side);
                    }
                }
                if (!updateTracker) {
                    newTracker = side.ordinal();
                    updateTracker = true;
                }
            }
        }
        inputTracker = newTracker;
    }

    protected void transferOutput() {

        if (!transferControl.getTransferOut()) {
            return;
        }
        int newTracker = outputTracker;
        boolean updateTracker = false;

        for (int i = outputTracker + 1; i <= outputTracker + 6; ++i) {
            Direction side = DIRECTIONS[i % 6];
            if (reconfigControl.getSideConfig(side).isOutput()) {
                for (ItemStorageCoFH slot : outputSlots()) {
                    InventoryHelper.insertIntoAdjacent(this, slot, 64, side);
                }
                for (FluidStorageCoFH tank : outputTanks()) {
                    FluidHelper.insertIntoAdjacent(this, tank, side);
                }
                if (!updateTracker) {
                    newTracker = side.ordinal();
                    updateTracker = true;
                }
            }
        }
        outputTracker = newTracker;
    }
    // endregion

    // region NETWORK
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {

        super.onDataPacket(net, pkt);
        ModelDataManager.requestModelDataRefresh(this);
    }

    @Override
    public PacketBuffer getControlPacket(PacketBuffer buffer) {

        super.getControlPacket(buffer);

        reconfigControl.writeToBuffer(buffer);
        transferControl.writeToBuffer(buffer);

        buffer.writeFluidStack(renderFluid);

        return buffer;
    }

    @Override
    public PacketBuffer getGuiPacket(PacketBuffer buffer) {

        super.getGuiPacket(buffer);

        buffer.writeFluidStack(renderFluid);

        return buffer;
    }

    @Override
    public PacketBuffer getStatePacket(PacketBuffer buffer) {

        super.getStatePacket(buffer);

        buffer.writeFluidStack(renderFluid);

        return buffer;
    }

    @Override
    public void handleControlPacket(PacketBuffer buffer) {

        super.handleControlPacket(buffer);

        reconfigControl.readFromBuffer(buffer);
        transferControl.readFromBuffer(buffer);

        renderFluid = buffer.readFluidStack();
        ModelDataManager.requestModelDataRefresh(this);
    }

    @Override
    public void handleGuiPacket(PacketBuffer buffer) {

        super.handleGuiPacket(buffer);

        renderFluid = buffer.readFluidStack();
    }

    @Override
    public void handleStatePacket(PacketBuffer buffer) {

        super.handleStatePacket(buffer);

        renderFluid = buffer.readFluidStack();
        ModelDataManager.requestModelDataRefresh(this);
    }
    // endregion

    // region NBT
    @Override
    public void read(CompoundNBT nbt) {

        super.read(nbt);

        reconfigControl.setFacing(Direction.byIndex(nbt.getByte(TAG_FACING)));
        reconfigControl.read(nbt.getCompound(TAG_SIDE_CONFIG));
        transferControl.read(nbt.getCompound(TAG_TRANSFER));

        inputTracker = nbt.getInt(TAG_TRACK_IN);
        outputTracker = nbt.getInt(TAG_TRACK_OUT);

        renderFluid = FluidStack.loadFluidStackFromNBT(nbt.getCompound(TAG_RENDER_FLUID));

        updateSidedHandlers();
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {

        super.write(nbt);

        nbt.putByte(TAG_FACING, (byte) reconfigControl.getFacing().getIndex());
        nbt.put(TAG_SIDE_CONFIG, reconfigControl.write(new CompoundNBT()));
        nbt.put(TAG_TRANSFER, transferControl.write(new CompoundNBT()));

        nbt.putInt(TAG_TRACK_IN, inputTracker);
        nbt.putInt(TAG_TRACK_OUT, outputTracker);

        if (!renderFluid.isEmpty()) {
            nbt.put(TAG_RENDER_FLUID, renderFluid.writeToNBT(new CompoundNBT()));
        }
        return nbt;
    }
    // endregion

    // region MODULES
    @Override
    public ReconfigControlModule reconfigControl() {

        return reconfigControl;
    }

    @Override
    public TransferControlModule transferControl() {

        return transferControl;
    }
    // endregion

    // region ITileCallback
    @Override
    public void onControlUpdate() {

        updateSidedHandlers();

        super.onControlUpdate();
    }
    // endregion

    // region CAPABILITIES
    protected final LazyOptional<?>[] sidedItemCaps = new LazyOptional<?>[]{
            LazyOptional.empty(),
            LazyOptional.empty(),
            LazyOptional.empty(),
            LazyOptional.empty(),
            LazyOptional.empty(),
            LazyOptional.empty()
    };
    protected final LazyOptional<?>[] sidedFluidCaps = new LazyOptional<?>[]{
            LazyOptional.empty(),
            LazyOptional.empty(),
            LazyOptional.empty(),
            LazyOptional.empty(),
            LazyOptional.empty(),
            LazyOptional.empty()
    };

    protected void updateSidedHandlers() {

        // ITEMS
        for (int i = 0; i < 6; ++i) {
            sidedItemCaps[i].invalidate();

            IItemHandler handler;
            switch (reconfigControl.getSideConfig(i)) {
                case SIDE_NONE:
                    handler = EmptyHandler.INSTANCE;
                    break;
                case SIDE_INPUT:
                    handler = inventory.getHandler(INPUT);
                    break;
                case SIDE_OUTPUT:
                    handler = inventory.getHandler(OUTPUT);
                    break;
                default:
                    handler = inventory.getHandler(ACCESSIBLE);
            }
            sidedItemCaps[i] = LazyOptional.of(() -> handler).cast();
        }

        // FLUID
        for (int i = 0; i < 6; ++i) {
            sidedFluidCaps[i].invalidate();

            IFluidHandler handler;
            switch (reconfigControl.getSideConfig(i)) {
                case SIDE_NONE:
                    handler = EmptyFluidHandler.INSTANCE;
                    break;
                case SIDE_INPUT:
                    handler = tankInv.getHandler(INPUT);
                    break;
                case SIDE_OUTPUT:
                    handler = tankInv.getHandler(OUTPUT);
                    break;
                default:
                    handler = tankInv.getHandler(ACCESSIBLE);
            }
            sidedFluidCaps[i] = LazyOptional.of(() -> handler).cast();
        }
    }

    @Override
    protected <T> LazyOptional<T> getItemHandlerCapability(@Nullable Direction side) {

        if (side == null) {
            return super.getItemHandlerCapability(side);
        }
        return sidedItemCaps[side.ordinal()].cast();
    }

    @Override
    protected <T> LazyOptional<T> getFluidHandlerCapability(@Nullable Direction side) {

        if (side == null) {
            return super.getFluidHandlerCapability(side);
        }
        return sidedFluidCaps[side.ordinal()].cast();
    }
    // endregion
}
