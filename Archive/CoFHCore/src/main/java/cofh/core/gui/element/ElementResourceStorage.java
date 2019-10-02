package cofh.core.gui.element;

import cofh.core.gui.IGuiAccess;
import cofh.lib.util.IResourceStorage;
import cofh.lib.util.helpers.MathHelper;
import cofh.lib.util.helpers.RenderHelper;
import net.minecraft.util.ResourceLocation;

import java.util.List;

import static cofh.lib.util.helpers.StringHelper.format;
import static cofh.lib.util.helpers.StringHelper.localize;

public abstract class ElementResourceStorage extends ElementBase {

    protected ResourceLocation overlayTexture;

    protected IResourceStorage storage;
    protected boolean infinite;
    protected int minDisplay;

    public ElementResourceStorage(IGuiAccess gui, int posX, int posY, IResourceStorage storage) {

        super(gui, posX, posY);
        this.storage = storage;
    }

    public final ElementResourceStorage setOverlayTexture(String texture) {

        this.overlayTexture = new ResourceLocation(texture);
        return this;
    }

    public ElementResourceStorage setInfinite(boolean infinite) {

        this.infinite = infinite;
        return this;
    }

    public ElementResourceStorage setMinDisplay(int minDisplay) {

        this.minDisplay = minDisplay;
        return this;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {

        drawStorage();
        drawResource();
        drawOverlay();
    }

    @Override
    public void addTooltip(List<String> tooltip, int mouseX, int mouseY) {

        if (infinite) {
            tooltip.add(localize("info.cofh.infinite"));
        } else {
            tooltip.add(format(storage.getStored()) + " / " + format(storage.getCapacity()) + " " + storage.getUnit());
        }
    }

    protected int getScaled(int scale) {

        if (storage.getCapacity() <= 0 || infinite) {
            return scale;
        }
        double fraction = (double) storage.getStored() * scale / storage.getCapacity();
        return minDisplay > 0 && storage.getStored() > 0 ? Math.max(minDisplay, MathHelper.round(fraction)) : MathHelper.round(fraction);
    }

    protected void drawStorage() {

        RenderHelper.bindTexture(texture);
        drawTexturedModalRect(posX, posY, 0, 0, width, height);
    }

    protected abstract void drawResource();

    protected void drawOverlay() {

        if (overlayTexture != null) {
            RenderHelper.bindTexture(overlayTexture);
            drawTexturedModalRect(posX, posY, 0, 0, width, height);
        }
    }

}
