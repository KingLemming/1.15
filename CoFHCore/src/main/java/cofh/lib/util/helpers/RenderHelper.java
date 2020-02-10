package cofh.lib.util.helpers;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

/**
 * Contains various helper functions to assist with rendering.
 *
 * @author King Lemming
 */
public final class RenderHelper {

    public static final double RENDER_OFFSET = 1.0D / 512.0D;
    public static final ResourceLocation MC_BLOCK_SHEET = new ResourceLocation("textures/atlas/blocks.png");
    public static final ResourceLocation MC_FONT_DEFAULT = new ResourceLocation("textures/font/ascii.png");
    public static final ResourceLocation MC_FONT_SGA = new ResourceLocation("textures/font/ascii_sga.png");
    public static final ResourceLocation MC_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    private RenderHelper() {

    }

    // region ACCESSORS
    public static TextureManager engine() {

        return Minecraft.getInstance().getTextureManager();
    }

    public static AtlasTexture textureMap() {

        return Minecraft.getInstance().getTextureMap();
    }

    public static Tessellator tessellator() {

        return Tessellator.getInstance();
    }

    public static ItemRenderer renderItem() {

        return Minecraft.getInstance().getItemRenderer();
    }
    // endregion

    // region SHEETS
    public static void setBlockTextureSheet() {

        bindTexture(MC_BLOCK_SHEET);
    }

    public static void setDefaultFontTextureSheet() {

        bindTexture(MC_FONT_DEFAULT);
    }

    public static void setSGAFontTextureSheet() {

        bindTexture(MC_FONT_SGA);
    }
    // endregion

    // region DRAW METHODS
    public static void drawFluid(int x, int y, FluidStack fluid, int width, int height) {

        if (fluid.isEmpty()) {
            return;
        }
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        int color = fluid.getFluid().getAttributes().getColor(fluid);
        setBlockTextureSheet();
        setGLColorFromInt(color);
        drawTiledTexture(x, y, getTexture(fluid.getFluid().getAttributes().getStill(fluid)), width, height);
        GL11.glPopMatrix();
    }

    public static void drawIcon(TextureAtlasSprite icon, double z) {

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(0, 16, z).tex(icon.getMinU(), icon.getMaxV());
        buffer.pos(16, 16, z).tex(icon.getMaxU(), icon.getMaxV());
        buffer.pos(16, 0, z).tex(icon.getMaxU(), icon.getMinV());
        buffer.pos(0, 0, z).tex(icon.getMinU(), icon.getMinV());
        tessellator().draw();

    }

    public static void drawIcon(double x, double y, double z, TextureAtlasSprite icon, int width, int height) {

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(x, y + height, z).tex(icon.getMinU(), icon.getMaxV());
        buffer.pos(x + width, y + height, z).tex(icon.getMaxU(), icon.getMaxV());
        buffer.pos(x + width, y, z).tex(icon.getMaxU(), icon.getMinV());
        buffer.pos(x, y, z).tex(icon.getMinU(), icon.getMinV());
        tessellator().draw();
    }

    public static void drawTiledTexture(int x, int y, TextureAtlasSprite icon, int width, int height) {

        int drawHeight;
        int drawWidth;

        for (int i = 0; i < width; i += 16) {
            for (int j = 0; j < height; j += 16) {
                drawWidth = Math.min(width - i, 16);
                drawHeight = Math.min(height - j, 16);
                drawScaledTexturedModalRectFromSprite(x + i, y + j, icon, drawWidth, drawHeight);
            }
        }
        resetColor();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void drawScaledTexturedModalRectFromSprite(int x, int y, TextureAtlasSprite icon, int width, int height) {

        if (icon == null) {
            return;
        }
        double minU = icon.getMinU();
        double maxU = icon.getMaxU();
        double minV = icon.getMinV();
        double maxV = icon.getMaxV();

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(x, y + height, 0).tex(minU, minV + (maxV - minV) * height / 16F).endVertex();
        buffer.pos(x + width, y + height, 0).tex(minU + (maxU - minU) * width / 16F, minV + (maxV - minV) * height / 16F).endVertex();
        buffer.pos(x + width, y, 0).tex(minU + (maxU - minU) * width / 16F, minV).endVertex();
        buffer.pos(x, y, 0).tex(minU, minV).endVertex();
        Tessellator.getInstance().draw();
    }

    public static void drawStencil(int xStart, int yStart, int xEnd, int yEnd, int flag) {

        GlStateManager.disableTexture();
        GL11.glStencilFunc(GL11.GL_ALWAYS, flag, flag);
        GL11.glStencilOp(GL11.GL_ZERO, GL11.GL_ZERO, GL11.GL_REPLACE);
        GL11.glStencilMask(flag);
        GlStateManager.colorMask(false, false, false, false);
        GlStateManager.depthMask(false);
        GL11.glClearStencil(0);
        GlStateManager.clear(GL11.GL_STENCIL_BUFFER_BIT, false);

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        buffer.pos(xStart, yEnd, 0).endVertex();
        buffer.pos(xEnd, yEnd, 0).endVertex();
        buffer.pos(xEnd, yStart, 0).endVertex();
        buffer.pos(xStart, yStart, 0).endVertex();
        Tessellator.getInstance().draw();

        GlStateManager.enableTexture();
        GL11.glStencilFunc(GL11.GL_EQUAL, flag, flag);
        GL11.glStencilMask(0);
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.depthMask(true);
    }
    // endregion

    // region PASSTHROUGHS
    public static void disableStandardItemLighting() {

        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
    }

    public static void enableStandardItemLighting() {

        net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
    }

    public static void enableGUIStandardItemLighting() {

        net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
    }
    // endregion

    // region TEXTURE GETTERS
    public static TextureAtlasSprite getTexture(String location) {

        return textureMap().getAtlasSprite(location);
    }

    public static TextureAtlasSprite getTexture(ResourceLocation location) {

        return getTexture(location.toString());
    }

    public static TextureAtlasSprite getFluidTexture(Fluid fluid) {

        return getTexture(fluid.getAttributes().getStillTexture());
    }

    public static TextureAtlasSprite getFluidTexture(FluidStack fluid) {

        return getTexture(fluid.getFluid().getAttributes().getStill(fluid));
    }
    // endregion

    public static void setGLColorFromInt(int color) {

        float red = (float) (color >> 16 & 255) / 255.0F;
        float green = (float) (color >> 8 & 255) / 255.0F;
        float blue = (float) (color & 255) / 255.0F;
        GlStateManager.color4f(red, green, blue, 1.0F);
    }

    public static void resetColor() {

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void bindTexture(ResourceLocation texture) {

        engine().bindTexture(texture);
    }

}
