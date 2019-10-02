package cofh.core.gui.element;

import cofh.core.gui.IGuiAccess;
import cofh.lib.util.helpers.RenderHelper;
import cofh.lib.util.helpers.StringHelper;

import java.util.List;

/**
 * Basic element which can render an arbitrary texture and may have a tooltip.
 *
 * @author King Lemming
 */
public class ElementSimple extends ElementBase {

    protected int texU = 0;
    protected int texV = 0;
    protected String info;

    public ElementSimple(IGuiAccess gui, int posX, int posY) {

        super(gui, posX, posY);
    }

    public ElementSimple setTextureOffsets(int u, int v) {

        texU = u;
        texV = v;
        return this;
    }

    public ElementSimple clearToolTip() {

        this.info = null;
        return this;
    }

    public ElementSimple setToolTip(String tooltip) {

        this.info = tooltip;
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
    public void addTooltip(List<String> tooltip, int mouseX, int mouseY) {

        if (info != null) {
            tooltip.add(StringHelper.localize(info));
        }
    }

}
