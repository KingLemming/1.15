package cofh.core.gui.element.panel;

import cofh.core.gui.IGuiAccess;
import cofh.core.gui.element.ElementBase;
import cofh.lib.util.helpers.RenderHelper;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.ArrayList;

import static cofh.lib.util.constants.Constants.PATH_ELEMENTS;

public abstract class PanelBase extends ElementBase {

    public static final ResourceLocation DEFAULT_TEXTURE_LEFT = new ResourceLocation(PATH_ELEMENTS + "panel_left.png");
    public static final ResourceLocation DEFAULT_TEXTURE_RIGHT = new ResourceLocation(PATH_ELEMENTS + "panel_right.png");

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    public static int panelExpandSpeed = 8;

    public boolean open;
    public boolean fullyOpen;
    public int side;

    protected int headerColor = 0xe1c92f;
    protected int subheaderColor = 0xaaafb8;
    protected int textColor = 0x000000;
    protected int backgroundColor = 0xffffff;

    protected int offsetX = 0;
    protected int offsetY = 0;

    protected int shiftX = 0;
    protected int shiftY = 0;

    protected int mX = 0;
    protected int mY = 0;

    protected int minWidth = 22;
    protected int maxWidth = 100;

    protected int minHeight = 22;
    protected int maxHeight = 92;

    protected ArrayList<ElementBase> elements = new ArrayList<>();

    public PanelBase(IGuiAccess gui) {

        this(gui, RIGHT);
    }

    public PanelBase(IGuiAccess gui, int sideIn) {

        super(gui);
        side = sideIn;
        width = minWidth;
        height = minHeight;
        texture = side == LEFT ? DEFAULT_TEXTURE_LEFT : DEFAULT_TEXTURE_RIGHT;
    }

    public PanelBase setOffsets(int offsetX, int offsetY) {

        posX -= this.offsetX;
        posY -= this.offsetY;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        posX += this.offsetX;
        posY += this.offsetY;

        return this;
    }

    @Override
    public PanelBase setPosition(int posX, int posY) {

        this.posX = posX + offsetX;
        this.posY = posY + offsetY;
        return this;
    }

    public boolean intersectsWith(double mouseX, double mouseY, int shiftX, int shiftY) {

        shiftX += offsetX;
        shiftY += offsetY;

        if (side == LEFT) {
            return mouseX <= shiftX && mouseX >= shiftX - width && mouseY >= shiftY && mouseY <= shiftY + height;
        } else {
            return mouseX >= shiftX && mouseX <= shiftX + width && mouseY >= shiftY && mouseY <= shiftY + height;
        }
    }

    protected void drawPanelIcon(TextureAtlasSprite iconName) {

        gui.drawIcon(iconName, sideOffset(), 3);
    }

    protected void drawForeground() {

    }

    protected void drawBackground() {

        float colorR = (backgroundColor >> 16 & 255) / 255.0F;
        float colorG = (backgroundColor >> 8 & 255) / 255.0F;
        float colorB = (backgroundColor & 255) / 255.0F;

        GlStateManager.color4f(colorR, colorG, colorB, 1.0F);

        RenderHelper.bindTexture(texture);

        gui.drawTexturedModalRect(0, 4, 0, 256 - height + 4, 4, height - 4);
        gui.drawTexturedModalRect(4, 0, 256 - width + 4, 0, width - 4, 4);
        gui.drawTexturedModalRect(0, 0, 0, 0, 4, 4);
        gui.drawTexturedModalRect(4, 4, 256 - width + 4, 256 - height + 4, width - 4, height - 4);

        RenderHelper.resetColor();
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {

        mouseX -= this.posX();
        mouseY -= this.posY;

        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.posX(), this.posY, 0.0F);

        drawBackground();

        for (ElementBase element : elements) {
            if (element.visible()) {
                element.drawBackground(mouseX, mouseY);
            }
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {

        mouseX -= this.posX();
        mouseY -= this.posY;

        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.posX(), this.posY, 0.0F);

        drawForeground();

        for (ElementBase element : elements) {
            if (element.visible()) {
                element.drawForeground(mouseX, mouseY);
            }
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void update(int mouseX, int mouseY) {

        super.update(mouseX, mouseY);

        mX = mouseX - posX();
        mY = mouseY - posY();

        updateElements();
    }

    public void setShift(int x, int y) {

        updateElements();

        shiftX = x + offsetX;
        shiftY = y + offsetY;
    }

    public void updateSize() {

        if (open && width < maxWidth) {
            width += panelExpandSpeed;
        } else if (!open && width > minWidth) {
            width -= panelExpandSpeed;
        }
        if (width > maxWidth) {
            width = maxWidth;
        } else if (width < minWidth) {
            width = minWidth;
        }
        if (open && height < maxHeight) {
            height += panelExpandSpeed;
        } else if (!open && height > minHeight) {
            height -= panelExpandSpeed;
        }
        if (height > maxHeight) {
            height = maxHeight;
        } else if (height < minHeight) {
            height = minHeight;
        }
        if (!fullyOpen && open && width == maxWidth && height == maxHeight) {
            setFullyOpen();
        }
    }

    public void setFullyOpen() {

        open = true;
        fullyOpen = true;
        width = maxWidth;
        height = maxHeight;

        for (ElementBase element : elements) {
            element.setVisible(fullyOpen);
        }
    }

    public void toggleOpen() {

        if (open) {
            open = false;
            fullyOpen = false;
            if (side == LEFT) {
                PanelTracker.setOpenedLeft(null);
            } else {
                PanelTracker.setOpenedRight(null);
            }
        } else {
            open = true;
            if (side == LEFT) {
                PanelTracker.setOpenedLeft(this.getClass());
            } else {
                PanelTracker.setOpenedRight(this.getClass());
            }
        }
        for (ElementBase element : elements) {
            element.setVisible(fullyOpen);
        }
    }

    protected final void updateElements() {

        for (int i = elements.size(); i-- > 0; ) {
            ElementBase c = elements.get(i);
            if (c.visible() && c.enabled()) {
                c.update(mX, mY);
            }
        }
    }

    public final Rectangle getBoundsOnScreen() {

        return visible() ? new Rectangle(posX() + guiLeft(), posY + guiTop(), width, height) : new Rectangle(posX() + guiLeft(), posY + guiTop(), 0, 0);
    }

    public final ElementBase addElement(ElementBase c) {

        elements.add(c);
        return c;
    }

    // region HELPERS
    @Override
    public int posX() {

        return side == LEFT ? posX - width : posX;
    }

    /**
     * Corrects for shadowing differences in panels to ensure that they always look nice - used in font rendering, typically.
     */
    protected int posXOffset() {

        return posX() + sideOffset();
    }

    protected int sideOffset() {

        return (side == LEFT ? 4 : 2);
    }
    // endregion

    // region CALLBACKS
    public boolean keyTyped(int keyCode, int scanCode) {

        for (int i = elements.size(); i-- > 0; ) {
            ElementBase c = elements.get(i);
            if (!c.visible() || !c.enabled()) {
                continue;
            }
            if (c.keyTyped(keyCode, scanCode)) {
                return true;
            }
        }
        return super.keyTyped(keyCode, scanCode);
    }

    /**
     * @return Whether the panel should stay open or not.
     */
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

        mouseX -= this.posX();
        mouseY -= this.posY;

        boolean shouldStayOpen = false;

        for (int i = elements.size(); i-- > 0; ) {
            ElementBase c = elements.get(i);
            if (!c.visible() || !c.enabled() || !c.intersectsWith(mouseX, mouseY)) {
                continue;
            }
            shouldStayOpen = true;

            if (c.mouseClicked(mouseX, mouseY, mouseButton)) {
                return true;
            }
        }
        return shouldStayOpen;
    }

    public void mouseReleased(double mouseX, double mouseY) {

        mouseX -= this.posX();
        mouseY -= this.posY;

        for (int i = elements.size(); i-- > 0; ) {
            ElementBase c = elements.get(i);
            if (!c.visible() || !c.enabled()) { // no bounds checking on mouseUp events
                continue;
            }
            c.mouseReleased(mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseWheel(double mouseX, double mouseY, int movement) {

        mouseX -= this.posX();
        mouseY -= this.posY;

        if (movement != 0) {
            for (int i = elements.size(); i-- > 0; ) {
                ElementBase c = elements.get(i);
                if (!c.visible() || !c.enabled() || !c.intersectsWith(mouseX, mouseY)) {
                    continue;
                }
                if (c.mouseWheel(mouseX, mouseY, movement)) {
                    return true;
                }
            }
        }
        return true;
    }
    // endregion
}
