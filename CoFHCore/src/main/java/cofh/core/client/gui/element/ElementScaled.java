package cofh.core.client.gui.element;

import cofh.core.client.gui.IGuiAccess;
import cofh.lib.util.helpers.RenderHelper;

public class ElementScaled extends ElementBase {

    protected int quantity;
    protected boolean drawBackground = true;
    protected StartDirection direction = StartDirection.BOTTOM;

    public ElementScaled(IGuiAccess gui, int posX, int posY) {

        super(gui, posX, posY);
    }

    public ElementScaled drawBackground(boolean drawBackground) {

        this.drawBackground = drawBackground;
        return this;
    }

    public ElementScaled setDirection(StartDirection direction) {

        this.direction = direction;
        return this;
    }

    public ElementScaled setQuantity(int quantity) {

        this.quantity = quantity;
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
                drawTexturedModalRect(posX, posY, width, 0, width, quantity);
                return;
            case BOTTOM:
                // vertical bottom -> top
                drawTexturedModalRect(posX, posY + height - quantity, width, height - quantity, width, quantity);
                return;
            case LEFT:
                // horizontal left -> right
                drawTexturedModalRect(posX, posY, width, 0, quantity, height);
                return;
            case RIGHT:
                // horizontal right -> left
                drawTexturedModalRect(posX + width - quantity, posY, width + width - quantity, 0, quantity, height);
        }
    }

    public enum StartDirection {
        TOP, BOTTOM, LEFT, RIGHT
    }

}
