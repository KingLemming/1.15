package cofh.core.gui.element;

import cofh.core.gui.IGuiAccess;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.util.helpers.FluidHelper;
import cofh.lib.util.helpers.RenderHelper;
import cofh.lib.util.helpers.StringHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

import java.util.List;

public class ElementFluidStorage extends ElementResourceStorage {

    protected FluidStorageCoFH tank;
    protected TextureAtlasSprite fluidTexture;

    public ElementFluidStorage(IGuiAccess gui, int posX, int posY, FluidStorageCoFH storage) {

        super(gui, posX, posY, storage);
        this.tank = storage;
    }

    public ElementFluidStorage setFluidTexture(TextureAtlasSprite fluidTexture) {

        this.fluidTexture = fluidTexture;
        return this;
    }

    @Override
    protected void drawResource() {

        int resourceHeight = height - 2;
        int resourceWidth = width - 2;

        int amount = getScaled(resourceHeight);
        boolean invert = FluidHelper.density(tank.getFluidStack()) < 0;

        if (fluidTexture != null) {
            RenderHelper.setBlockTextureSheet();
            RenderHelper.drawTiledTexture(posX + 1, posY + 1 + (invert ? 0 : resourceHeight - amount), fluidTexture, resourceWidth, amount);
        } else {
            RenderHelper.drawFluid(posX + 1, posY + 1 + (invert ? 0 : resourceHeight - amount), tank.getFluidStack(), resourceWidth, amount);
        }
    }

    @Override
    public void addTooltip(List<String> tooltip, int mouseX, int mouseY) {

        if (tank.getAmount() > 0) {
            tooltip.add(StringHelper.getFluidName(tank.getFluidStack()));
            if (FluidHelper.hasPotionTag(tank.getFluidStack())) {
                // TODO: Fix
                // FluidHelper.addPotionTooltip(tank.getFluidStack(), tooltip);
            }
        }
        super.addTooltip(tooltip, mouseX, mouseY);
    }

}
