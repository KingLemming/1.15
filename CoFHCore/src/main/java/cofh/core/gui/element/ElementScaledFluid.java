package cofh.core.gui.element;

import cofh.core.gui.IGuiAccess;
import cofh.lib.util.helpers.RenderHelper;
import net.minecraftforge.fluids.FluidStack;

public class ElementScaledFluid extends ElementScaled {

    protected FluidStack fluid;

    public ElementScaledFluid(IGuiAccess gui, int posX, int posY) {

        super(gui, posX, posY);
    }

    public ElementScaledFluid setFluid(FluidStack fluid) {

        this.fluid = fluid;
        return this;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {

        RenderHelper.bindTexture(texture);

        if (drawBackground) {
            drawTexturedModalRect(posX, posY, 0, 0, width, height);
        }
        switch (direction) {
            case TOP:
                // vertical top -> bottom
                RenderHelper.drawFluid(posX, posY, fluid, width, quantity);
                RenderHelper.bindTexture(texture);
                drawTexturedModalRect(posX, posY, width, 0, width, quantity);
                return;
            case BOTTOM:
                // vertical bottom -> top
                RenderHelper.drawFluid(posX, posY + height - quantity, fluid, width, quantity);
                RenderHelper.bindTexture(texture);
                drawTexturedModalRect(posX, posY + height - quantity, width, height - quantity, width, quantity);
                return;
            case LEFT:
                // horizontal left -> right
                RenderHelper.drawFluid(posX, posY, fluid, quantity, height);
                RenderHelper.bindTexture(texture);
                drawTexturedModalRect(posX, posY, width, 0, quantity, height);
                return;
            case RIGHT:
                // horizontal right -> left
                RenderHelper.drawFluid(posX + width - quantity, posY, fluid, quantity, height);
                RenderHelper.bindTexture(texture);
                drawTexturedModalRect(posX + width - quantity, posY, width + width - quantity, 0, quantity, height);
        }
    }

}
