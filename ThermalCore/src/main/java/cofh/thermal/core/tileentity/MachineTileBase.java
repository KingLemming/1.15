package cofh.thermal.core.tileentity;

import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.control.IReconfigurableTile;
import cofh.lib.util.control.ITransferControllableTile;
import cofh.lib.util.control.ReconfigControlModule;
import cofh.lib.util.control.TransferControlModule;
import cofh.lib.util.helpers.FluidHelper;
import cofh.lib.util.helpers.InventoryHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.EmptyFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.EmptyHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.Constants.*;
import static cofh.lib.util.constants.Tags.*;
import static cofh.lib.util.control.IReconfigurable.SideConfig.SIDE_INPUT;
import static cofh.lib.util.control.IReconfigurable.SideConfig.SIDE_OUTPUT;
import static cofh.lib.util.helpers.BlockHelper.*;

public abstract class MachineTileBase extends ThermalTileBase implements ITickableTileEntity, ITransferControllableTile, IReconfigurableTile {

    protected Direction facing;
    protected FluidStack renderFluid;

    protected int inputTracker;
    protected int outputTracker;

    protected ReconfigControlModule reconfigControl = new ReconfigControlModule(this);
    protected TransferControlModule transferControl = new TransferControlModule(this);

    public MachineTileBase(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);

        reconfigControl.setSideConfig(new SideConfig[]{SIDE_OUTPUT, SIDE_OUTPUT, SIDE_INPUT, SIDE_INPUT, SIDE_INPUT, SIDE_INPUT});
    }

    @Override
    public void updateContainingBlockInfo() {

        super.updateContainingBlockInfo();
        updateFacing();
    }

    @Override
    public Direction getFacing() {

        if (facing == null) {
            updateFacing();
        }
        return facing;
    }

    protected boolean allow6WayFacing() {

        return false;
    }

    protected void updateFacing() {

        Direction prevFacing = facing;

        if (allow6WayFacing()) {
            facing = getBlockState().get(FACING_ALL);
            if (prevFacing == null || facing == prevFacing) {
                return;
            }
            int iPrev = prevFacing.ordinal();
            int iFace = facing.ordinal();
            SideConfig[] sides = new SideConfig[6];

            // TODO: 6-way facing logic.
            //			if (iPrev == SIDE_RIGHT[iFace]) {
            //				for (int i = 0; i < 6; i++) {
            //					sides[i] = reconfigControl.getSideConfig()[ROTATE_CLOCK_Y[i]];
            //				}
            //			} else if (iPrev == SIDE_LEFT[iFace]) {
            //				for (int i = 0; i < 6; i++) {
            //					sides[i] = reconfigControl.getSideConfig()[ROTATE_COUNTER_Y[i]];
            //				}
            //			} else if (iPrev == SIDE_OPPOSITE[iFace]) {
            //				for (int i = 0; i < 6; i++) {
            //					sides[i] = reconfigControl.getSideConfig()[INVERT_AROUND_Y[i]];
            //				}
            //			}
            reconfigControl.setSideConfig(sides);
        } else {
            facing = getBlockState().get(FACING_HORIZONTAL);
            if (prevFacing == null || facing == prevFacing) {
                return;
            }
            int iPrev = prevFacing.ordinal();
            int iFace = facing.ordinal();
            SideConfig[] sides = new SideConfig[6];

            if (iPrev == SIDE_RIGHT[iFace]) {
                for (int i = 0; i < 6; i++) {
                    sides[i] = reconfigControl.getSideConfig()[ROTATE_CLOCK_Y[i]];
                }
            } else if (iPrev == SIDE_LEFT[iFace]) {
                for (int i = 0; i < 6; i++) {
                    sides[i] = reconfigControl.getSideConfig()[ROTATE_COUNTER_Y[i]];
                }
            } else if (iPrev == SIDE_OPPOSITE[iFace]) {
                for (int i = 0; i < 6; i++) {
                    sides[i] = reconfigControl.getSideConfig()[INVERT_AROUND_Y[i]];
                }
            }
            reconfigControl.setSideConfig(sides);
        }
    }

    // region HELPERS
    protected boolean cacheRenderFluid() {

        return false;
    }

    public FluidStack getRenderFluid() {

        return renderFluid;
    }

    protected void transferInput() {

        if (!transferControl.getTransferIn()) {
            return;
        }
        int newTracker = inputTracker;
        boolean updateTracker = false;

        for (int i = inputTracker + 1; i <= inputTracker + 6; i++) {
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

        for (int i = outputTracker + 1; i <= outputTracker + 6; i++) {
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

        super.getControlPacket(buffer);

        buffer.writeFluidStack(renderFluid);

        return buffer;
    }

    @Override
    public void handleControlPacket(PacketBuffer buffer) {

        super.handleControlPacket(buffer);

        reconfigControl.readFromBuffer(buffer);
        transferControl.readFromBuffer(buffer);

        renderFluid = buffer.readFluidStack();
    }

    @Override
    public void handleGuiPacket(PacketBuffer buffer) {

        super.handleGuiPacket(buffer);

        renderFluid = buffer.readFluidStack();
    }

    @Override
    public void handleStatePacket(PacketBuffer buffer) {

        super.handleControlPacket(buffer);

        renderFluid = buffer.readFluidStack();
    }
    // endregion

    // region NBT
    @Override
    public void read(CompoundNBT nbt) {

        super.read(nbt);

        reconfigControl.read(nbt.getCompound(TAG_SIDE_CONFIG));
        transferControl.read(nbt.getCompound(TAG_TRANSFER));

        inputTracker = nbt.getInt(TAG_TRACK_IN);
        outputTracker = nbt.getInt(TAG_TRACK_OUT);

        renderFluid = FluidStack.loadFluidStackFromNBT(nbt.getCompound(TAG_RENDER_FLUID));
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {

        super.write(nbt);

        nbt.put(TAG_SIDE_CONFIG, reconfigControl.write(new CompoundNBT()));
        nbt.put(TAG_TRANSFER, transferControl.write(new CompoundNBT()));

        nbt.putInt(TAG_TRACK_IN, inputTracker);
        nbt.putInt(TAG_TRACK_OUT, outputTracker);

        if (renderFluid != null) {
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

    // region CAPABILITIES
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && inventory.hasSlots()) {
            IItemHandler handler;
            switch (reconfigControl.getSideConfig(facing)) {
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
            return LazyOptional.of(() -> handler).cast();
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && tankInv.hasTanks()) {
            IFluidHandler handler;
            switch (reconfigControl.getSideConfig(facing)) {
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
            return LazyOptional.of(() -> handler).cast();
        }
        return super.getCapability(cap, side);
    }
    // endregion
}
