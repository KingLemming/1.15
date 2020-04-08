package cofh.core.gui.element;

import cofh.core.gui.IGuiAccess;
import cofh.lib.util.helpers.RenderHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class ElementButton extends ElementButtonBase {

    private int sheetX;
    private int sheetY;
    private int hoverX;
    private int hoverY;
    private int disabledX = 0;
    private int disabledY = 0;

    private boolean managedClicks;
    private String tooltip;

    public ElementButton(IGuiAccess gui, int posX, int posY, int sizeX, int sizeY, int sheetX, int sheetY, int hoverX, int hoverY, String texture) {

        super(gui, posX, posY, sizeX, sizeY);

        setGuiManagedClicks(false);
        setTexture(texture, texW, texH);

        this.sheetX = sheetX;
        this.sheetY = sheetY;
        this.hoverX = hoverX;
        this.hoverY = hoverY;
    }

    public ElementButton(IGuiAccess gui, int posX, int posY, int sizeX, int sizeY, int sheetX, int sheetY, int hoverX, int hoverY, int disabledX, int disabledY, String texture) {

        this(gui, posX, posY, sizeX, sizeY, sheetX, sheetY, hoverX, hoverY, texture);
        this.disabledX = disabledX;
        this.disabledY = disabledY;
    }

    public ElementButton(IGuiAccess gui, int posX, int posY, String name, int sheetX, int sheetY, int hoverX, int hoverY, int sizeX, int sizeY, String texture) {

        super(gui, posX, posY, sizeX, sizeY);

        setGuiManagedClicks(true);
        setTexture(texture, texW, texH);
        setName(name);

        this.sheetX = sheetX;
        this.sheetY = sheetY;
        this.hoverX = hoverX;
        this.hoverY = hoverY;
    }

    public ElementButton(IGuiAccess gui, int posX, int posY, String name, int sheetX, int sheetY, int hoverX, int hoverY, int disabledX, int disabledY, int sizeX, int sizeY, String texture) {

        this(gui, posX, posY, name, sheetX, sheetY, hoverX, hoverY, sizeX, sizeY, texture);
        this.disabledX = disabledX;
        this.disabledY = disabledY;
    }

    public ElementButton setGuiManagedClicks(boolean managed) {

        this.managedClicks = managed;
        return this;
    }

    public ElementButton clearToolTip() {

        this.tooltip = null;
        return this;
    }

    public ElementButton setToolTip(String tooltip) {

        this.tooltip = tooltip;
        return this;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {

        RenderHelper.resetColor();
        RenderHelper.bindTexture(texture);
        if (enabled()) {
            if (intersectsWith(mouseX, mouseY)) {
                drawTexturedModalRect(posX, posY, hoverX, hoverY, width, height);
            } else {
                drawTexturedModalRect(posX, posY, sheetX, sheetY, width, height);
            }
        } else {
            drawTexturedModalRect(posX, posY, disabledX, disabledY, width, height);
        }
    }

    @Override
    public void addTooltip(List<ITextComponent> tooltipList, int mouseX, int mouseY) {

        if (this.tooltip != null) {
            tooltipList.add(new TranslationTextComponent(this.tooltip));
        }
    }

    @Override
    public boolean mouseClicked(double x, double y, int mouseButton) {

        if (!managedClicks) {
            return super.mouseClicked(x, y, mouseButton);
        }
        if (enabled()) {
            gui.handleElementButtonClick(name(), mouseButton);
            return true;
        }
        return false;
    }

    public void setSheetX(int pos) {

        sheetX = pos;
    }

    public void setSheetY(int pos) {

        sheetY = pos;
    }

    public void setHoverX(int pos) {

        hoverX = pos;
    }

    public void setHoverY(int pos) {

        hoverY = pos;
    }

    public void setActive() {

        setEnabled(true);
    }

    public void setDisabled() {

        setEnabled(false);
    }

}
