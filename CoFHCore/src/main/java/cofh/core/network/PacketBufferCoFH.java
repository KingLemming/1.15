package cofh.core.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fluids.FluidStack;

/**
 * Wraps standard packet buffer to provide additional read/write options.
 *
 * @author King Lemming
 */
public class PacketBufferCoFH extends PacketBuffer {

    public PacketBufferCoFH(ByteBuf wrapped) {

        super(wrapped);
    }

    public void writeFluidStack(FluidStack stack) {

        if (stack.isEmpty()) {
            writeBoolean(false);
        } else {
            writeBoolean(true);
            stack.writeToPacket(this);
        }
    }

    public FluidStack readFluidStack() {

        if (!readBoolean()) {
            return FluidStack.EMPTY;
        }
        return FluidStack.readFromPacket(this);
    }

}
