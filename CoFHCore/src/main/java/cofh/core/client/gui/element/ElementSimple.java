package cofh.core.client.gui.element;

import cofh.core.client.gui.IGuiAccess;
import cofh.lib.util.helpers.RenderHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

/**
 * Basic element which can render an arbitrary texture and may have a tooltip.
 *
 * @author King Lemming
 */
public class ElementSimple extends ElementBase {

    protected int texU = 0;
    protected int texV = 0;
    protected String tooltip;

    public ElementSimple(IGuiAccess gui, int posX, int posY) {

        super(gui, posX, posY);
    }

    public ElementSimple setTextureOffsets(int u, int v) {

        texU = u;
        texV = v;
        return this;
    }

    public ElementSimple clearToolTip() {

        this.tooltip = null;
        return this;
    }

    public ElementSimple setToolTip(String tooltip) {

        this.tooltip = tooltip;
        return this;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {

        RenderHelper.bindTexture(texture);
        drawTexturedModalRect(posX, posY, texU, texV, width, height);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {

    }

    @Override
    public void addTooltip(List<ITextComponent> tooltipList, int mouseX, int mouseY) {

        if (tooltip != null) {
            tooltipList.add(new TranslationTextComponent(tooltip));
        }
    }

}
