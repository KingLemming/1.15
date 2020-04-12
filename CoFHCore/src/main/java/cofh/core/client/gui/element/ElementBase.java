package cofh.core.client.gui.element;

import cofh.core.client.gui.IGuiAccess;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.List;
import java.util.function.BooleanSupplier;

public abstract class ElementBase {

    public static final BooleanSupplier TRUE = () -> true;
    public static final BooleanSupplier FALSE = () -> false;

    protected BooleanSupplier enabled = TRUE;
    protected BooleanSupplier visible = TRUE;

    protected final IGuiAccess gui;
    protected ResourceLocation texture;
    protected String name = "";

    protected int posX;
    protected int posY;

    protected int width;
    protected int height;

    protected int texW = 256;
    protected int texH = 256;

    public ElementBase(IGuiAccess gui) {

        this.gui = gui;
    }

    public ElementBase(IGuiAccess gui, int posX, int posY) {

        this.gui = gui;
        this.posX = posX;
        this.posY = posY;
    }

    public ElementBase(IGuiAccess gui, int posX, int posY, int width, int height) {

        this.gui = gui;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public void drawBackground(int mouseX, int mouseY) {

    }

    public void drawForeground(int mouseX, int mouseY) {

    }

    public void addTooltip(List<ITextComponent> tooltipList, int mouseX, int mouseY) {

    }

    public void update(int mouseX, int mouseY) {

    }

    public boolean intersectsWith(double mouseX, double mouseY) {

        return mouseX >= this.posX && mouseX < this.posX + this.width && mouseY >= this.posY && mouseY < this.posY + this.height;
    }

    // region SETTERS / GETTERS
    public final ElementBase setEnabled(boolean enabled) {

        this.enabled = enabled ? TRUE : FALSE;
        return this;
    }

    public final ElementBase setVisible(boolean visible) {

        this.visible = visible ? TRUE : FALSE;
        return this;
    }

    public final ElementBase setEnabled(BooleanSupplier enabled) {

        this.enabled = enabled;
        return this;
    }

    public final ElementBase setVisible(BooleanSupplier visible) {

        this.visible = visible;
        return this;
    }

    public ElementBase setPosition(int posX, int posY) {

        this.posX = posX;
        this.posY = posY;
        return this;
    }

    public final ElementBase setSize(int width, int height) {

        this.width = width;
        this.height = height;
        return this;
    }

    public final ElementBase setTexture(String texture, int texW, int texH) {

        this.texture = new ResourceLocation(texture);
        this.texW = texW;
        this.texH = texH;
        return this;
    }

    public final ElementBase setName(String name) {

        this.name = name;
        return this;
    }

    public final boolean enabled() {

        return enabled.getAsBoolean();
    }

    public final boolean visible() {

        return visible.getAsBoolean();
    }

    public final String name() {

        return name;
    }

    /**
     * This method is relative to the frame of reference - GUI or Panel.
     */
    public int posX() {

        return posX;
    }

    /**
     * This method is relative to the frame of reference - GUI or Panel.
     */
    public int posY() {

        return posY;
    }

    public int width() {

        return width;
    }

    public int height() {

        return height;
    }

    public final int guiTop() {

        return gui.getGuiTop();
    }

    public final int guiLeft() {

        return gui.getGuiLeft();
    }
    // endregion

    // region HELPERS
    public void drawCenteredString(String text, int x, int y, int color) {

        gui.getFontRenderer().drawStringWithShadow(text, x - (gui.getFontRenderer().getStringWidth(text) >> 1), y, color);
    }

    public void drawSizedRect(int x, int y, int width, int height, int color) {

        gui.drawSizedRect(x, y, width, height, color);
    }

    public void drawColoredModalRect(int x, int y, int width, int height, int color) {

        gui.drawColoredModalRect(x, y, width, height, color);
    }

    public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height) {

        gui.drawTexturedModalRect(x, y, u, v, width, height, texW, texH);
    }

    public FontRenderer getFontRenderer() {

        return gui.getFontRenderer();
    }
    // endregion

    // region CALLBACKS
    public boolean keyTyped(int keyCode, int scanCode) {

        return false;
    }

    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

        return false;
    }

    public void mouseReleased(double mouseX, double mouseY) {

    }

    public boolean mouseWheel(double mouseX, double mouseY, double movement) {

        return false;
    }
    // endregion
}
