package cofh.thermal.core.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.fluids.FluidStack;

import static cofh.lib.util.constants.NBTTags.TAG_RENDER_FLUID;

public abstract class MachineTileBasic extends ThermalTileBase {

    protected Direction facing;
    protected FluidStack renderFluid = FluidStack.EMPTY;

    public MachineTileBasic(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
        securityControl.setEnabled(false);
        redstoneControl.setEnabled(false);
    }

    // region HELPERS
    protected boolean cacheRenderFluid() {

        return false;
    }

    public FluidStack getRenderFluid() {

        return renderFluid;
    }
    // endregion

    // region NETWORK
    @Override
    public PacketBuffer getControlPacket(PacketBuffer buffer) {

        super.getControlPacket(buffer);

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

        renderFluid = FluidStack.loadFluidStackFromNBT(nbt.getCompound(TAG_RENDER_FLUID));
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {

        super.write(nbt);

        if (!renderFluid.isEmpty()) {
            nbt.put(TAG_RENDER_FLUID, renderFluid.writeToNBT(new CompoundNBT()));
        }
        return nbt;
    }
    // endregion
}
