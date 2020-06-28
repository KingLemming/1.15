package cofh.core.client.gui.element;

import cofh.core.client.gui.IGuiAccess;
import cofh.lib.util.helpers.RenderHelper;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

public class ElementFluid extends ElementBase {

    public FluidStack fluid;

    public ElementFluid(IGuiAccess gui, int posX, int posY) {

        super(gui, posX, posY);
    }

    public ElementFluid setFluid(FluidStack stack) {

        this.fluid = stack;
        return this;
    }

    public ElementFluid setFluid(Fluid fluid) {

        this.fluid = new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME);
        return this;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {

        RenderHelper.drawFluid(posX(), posY(), fluid, width, height);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {

    }

}
