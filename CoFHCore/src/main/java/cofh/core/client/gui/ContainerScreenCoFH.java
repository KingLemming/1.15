package cofh.core.client.gui;

import cofh.core.client.gui.element.ElementBase;
import cofh.core.client.gui.element.panel.PanelBase;
import cofh.core.client.gui.element.panel.PanelTracker;
import cofh.lib.inventory.container.slot.SlotFalseCopy;
import cofh.lib.util.helpers.RenderHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static cofh.lib.util.helpers.StringHelper.localize;

public class ContainerScreenCoFH<T extends Container> extends ContainerScreen<T> implements IGuiAccess {

    protected int mX;
    protected int mY;
    protected int lastIndex = -1;

    protected String name;
    protected String info;
    protected ResourceLocation texture;
    protected PlayerEntity player;

    protected boolean drawTitle = true;
    protected boolean drawInventory = true;
    protected boolean showTooltips = true;

    private final ArrayList<PanelBase> panels = new ArrayList<>();
    private final ArrayList<ElementBase> elements = new ArrayList<>();
    private final List<ITextComponent> tooltip = new LinkedList<>();

    public ContainerScreenCoFH(T container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, titleIn);
        player = inv.player;
    }

    @Override
    public void init() {

        super.init();
        panels.clear();
        elements.clear();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTick) {

        mX = mouseX - guiLeft;
        mY = mouseY - guiTop;

        updatePanels();
        updateElements();

        renderBackground();
        super.render(mouseX, mouseY, partialTick);
        renderHoveredToolTip(mouseX, mouseY);

        if (showTooltips && getMinecraft().player.inventory.getItemStack().isEmpty()) {
            drawTooltip();
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        RenderHelper.resetColor();
        RenderHelper.bindTexture(texture);

        if (xSize > 256 || ySize > 256) {
            drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize, 512, 512);
        } else {
            drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        }
        RenderSystem.pushMatrix();
        RenderSystem.translatef(guiLeft, guiTop, 0.0F);

        drawPanels(false);
        drawElements(false);

        RenderSystem.popMatrix();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        if (drawTitle & title != null) {
            getFontRenderer().drawString(localize(title.getFormattedText()), getCenteredOffset(localize(title.getFormattedText())), 6, 0x404040);
        }
        if (drawInventory) {
            getFontRenderer().drawString(localize("container.inventory"), 8, ySize - 96 + 3, 0x404040);
        }
        drawPanels(true);
        drawElements(true);
    }

    // region ELEMENTS
    public void drawTooltip() {

        PanelBase panel = getPanelAtPosition(mX, mY);

        if (panel != null) {
            panel.addTooltip(tooltip, mX, mY);
        }
        ElementBase element = getElementAtPosition(mX, mY);

        if (element != null && element.visible()) {
            element.addTooltip(tooltip, mX, mY);
        }
        drawTooltipHoveringText(tooltip, mX + guiLeft, mY + guiTop, font);
        tooltip.clear();
    }

    /**
     * Draws the Elements for this GUI.
     */
    protected void drawElements(boolean foreground) {

        if (foreground) {
            for (ElementBase c : elements) {
                if (c.visible()) {
                    c.drawForeground(mX, mY);
                }
            }
        } else {
            for (ElementBase c : elements) {
                if (c.visible()) {
                    c.drawBackground(mX, mY);
                }
            }
        }
    }

    /**
     * Draws the Panels for this GUI. Open / close animation is part of this.
     */
    protected void drawPanels(boolean foreground) {

        int yPosRight = 4;
        int yPosLeft = 4;

        if (foreground) {
            for (PanelBase panel : panels) {
                panel.updateSize();
                if (!panel.visible()) {
                    continue;
                }
                if (panel.side == PanelBase.LEFT) {
                    panel.drawForeground(mX, mY);
                    yPosLeft += panel.height();
                } else {
                    panel.drawForeground(mX, mY);
                    yPosRight += panel.height();
                }
            }
        } else {
            for (PanelBase panel : panels) {
                panel.updateSize();
                if (!panel.visible()) {
                    continue;
                }
                if (panel.side == PanelBase.LEFT) {
                    panel.setPosition(0, yPosLeft);
                    panel.drawBackground(mX, mY);
                    yPosLeft += panel.height();
                } else {
                    panel.setPosition(xSize, yPosRight);
                    panel.drawBackground(mX, mY);
                    yPosRight += panel.height();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected <T> T addElement(ElementBase element) {

        elements.add(element);
        return (T) element;
    }

    public final void addElements(ElementBase... c) {

        elements.addAll(Arrays.asList(c));
    }

    @SuppressWarnings("unchecked")
    protected <T> T addPanel(PanelBase panel) {

        int yOffset = 4;
        for (PanelBase panel1 : panels) {
            if (panel1.side == panel.side && panel1.visible()) {
                yOffset += panel1.height();
            }
        }
        panel.setPosition(panel.side == PanelBase.LEFT ? 0 : xSize, yOffset);
        panels.add(panel);

        if (PanelTracker.getOpenedLeft() != null && panel.getClass().equals(PanelTracker.getOpenedLeft())) {
            panel.setFullyOpen();
        } else if (PanelTracker.getOpenedRight() != null && panel.getClass().equals(PanelTracker.getOpenedRight())) {
            panel.setFullyOpen();
        }
        return (T) panel;
    }

    private ElementBase getElementAtPosition(int mouseX, int mouseY) {

        for (int i = elements.size(); i-- > 0; ) {
            ElementBase element = elements.get(i);
            if (element.intersectsWith(mouseX, mouseY)) {
                return element;
            }
        }
        return null;
    }

    private PanelBase getPanelAtPosition(double mouseX, double mouseY) {

        int xShift = 0;
        int yShift = 4;

        // LEFT SIDE
        for (PanelBase panel : panels) {
            if (!panel.visible() || panel.side == PanelBase.RIGHT) {
                continue;
            }
            panel.setShift(xShift, yShift);
            if (panel.intersectsWith(mouseX, mouseY, xShift, yShift)) {
                return panel;
            }
            yShift += panel.height();
        }

        xShift = xSize;
        yShift = 4;
        // RIGHT SIDE
        for (PanelBase panel : panels) {
            if (!panel.visible() || panel.side == PanelBase.LEFT) {
                continue;
            }
            panel.setShift(xShift, yShift);
            if (panel.intersectsWith(mouseX, mouseY, xShift, yShift)) {
                return panel;
            }
            yShift += panel.height();
        }
        return null;
    }

    private void updateElements() {

        for (int i = elements.size(); i-- > 0; ) {
            ElementBase c = elements.get(i);
            if (c.visible() && c.enabled()) {
                c.update(mX, mY);
            }
        }
    }

    private void updatePanels() {

        for (int i = panels.size(); i-- > 0; ) {
            PanelBase c = panels.get(i);
            if (c.visible() && c.enabled()) {
                c.update(mX, mY);
            }
        }
    }

    public List<Rectangle2d> getPanelBounds() {

        List<Rectangle2d> panelBounds = new ArrayList<>();

        for (PanelBase c : panels) {
            panelBounds.add(c.getBoundsOnScreen());
        }
        return panelBounds;
    }
    // endregion

    // region CALLBACKS
    @Override
    protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {

        return super.hasClickedOutside(mouseX, mouseY, guiLeftIn, guiTopIn, mouseButton) && getPanelAtPosition(mouseX - guiLeftIn, mouseY - guiTopIn) == null;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

        mouseX -= guiLeft;
        mouseY -= guiTop;

        for (int i = elements.size(); i-- > 0; ) {
            ElementBase c = elements.get(i);
            if (!c.visible() || !c.enabled() || !c.intersectsWith(mouseX, mouseY)) {
                continue;
            }
            if (c.mouseClicked(mouseX, mouseY, mouseButton)) {
                return true;
            }
        }
        PanelBase panel = getPanelAtPosition(mouseX, mouseY);
        if (panel != null) {
            if (!panel.mouseClicked(mouseX, mouseY, mouseButton)) {
                for (int i = panels.size(); i-- > 0; ) {
                    PanelBase other = panels.get(i);
                    if (other != panel && other.open && other.side == panel.side) {
                        other.toggleOpen();
                    }
                }
                panel.toggleOpen();
                return true;
            }
        }
        mouseX += guiLeft;
        mouseY += guiTop;

        // If a panel is open, expand GUI size to support slot actions.
        if (panel != null) {
            switch (panel.side) {
                case PanelBase.LEFT:
                    guiLeft -= panel.width();
                    break;
                case PanelBase.RIGHT:
                    xSize += panel.width();
                    break;
            }
        }
        boolean ret = super.mouseClicked(mouseX, mouseY, mouseButton);

        // Re-adjust GUI size after click has happened.
        if (panel != null) {
            switch (panel.side) {
                case PanelBase.LEFT:
                    guiLeft += panel.width();
                    break;
                case PanelBase.RIGHT:
                    xSize -= panel.width();
                    break;
            }
        }
        return ret;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {

        mouseX -= guiLeft;
        mouseY -= guiTop;

        if (mouseButton >= 0 && mouseButton <= 2) { // 0:left, 1:right, 2: middle
            for (int i = elements.size(); i-- > 0; ) {
                ElementBase c = elements.get(i);
                if (!c.visible() || !c.enabled()) { // no bounds checking on mouseUp events
                    continue;
                }
                c.mouseReleased(mouseX, mouseY);
            }
        }
        mouseX += guiLeft;
        mouseY += guiTop;

        return super.mouseReleased(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int mouseButton, double unused_1, double unused_2) {

        Slot slot = getSlotAtPosition(mX, mY);
        ItemStack itemstack = getMinecraft().player.inventory.getItemStack();

        if (this.dragSplitting && slot != null && !itemstack.isEmpty() && slot instanceof SlotFalseCopy) {
            if (lastIndex != slot.slotNumber) {
                lastIndex = slot.slotNumber;
                this.handleMouseClick(slot, slot.slotNumber, 0, ClickType.PICKUP);
            }
            return true;
        }
        lastIndex = -1;
        return super.mouseDragged(mouseX, mouseY, mouseButton, unused_1, unused_2);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double movement) {

        if (movement != 0) {
            for (int i = elements.size(); i-- > 0; ) {
                ElementBase c = elements.get(i);
                if (!c.visible() || !c.enabled() || !c.intersectsWith(mX, mY)) {
                    continue;
                }
                if (c.mouseWheel(mX, mY, movement)) {
                    return true;
                }
            }
            PanelBase panel = getPanelAtPosition(mX, mY);
            if (panel != null && panel.mouseWheel(mX, mY, movement)) {
                return true;
            }
            return mouseWheel(mX, mY, movement);
        }
        return false;
    }

    @Override
    protected boolean func_195363_d(int keyCode, int scanCode) {

        for (int i = elements.size(); i-- > 0; ) {
            ElementBase c = elements.get(i);
            if (!c.visible() || !c.enabled()) {
                continue;
            }
            if (c.keyTyped(keyCode, scanCode)) {
                return true;
            }
        }
        return super.func_195363_d(keyCode, scanCode);
    }
    // endregion

    // region HELPERS
    protected boolean mouseWheel(double mouseX, double mouseY, double movement) {

        return false;
    }

    protected Slot getSlotAtPosition(int xCoord, int yCoord) {

        for (int k = 0; k < this.container.inventorySlots.size(); ++k) {
            Slot slot = this.container.inventorySlots.get(k);
            if (this.isMouseOverSlot(slot, xCoord, yCoord)) {
                return slot;
            }
        }
        return null;
    }

    protected boolean isMouseOverSlot(Slot theSlot, int xCoord, int yCoord) {

        return this.isPointInRegion(theSlot.xPos, theSlot.yPos, 16, 16, xCoord, yCoord);
    }

    protected int getCenteredOffset(String string) {

        return this.getCenteredOffset(string, xSize / 2);
    }

    protected int getCenteredOffset(String string, int xPos) {

        return ((xPos * 2) - font.getStringWidth(string)) / 2;
    }

    protected void drawTooltipHoveringText(List<ITextComponent> tooltip, int x, int y, FontRenderer font) {

        if (tooltip == null || tooltip.isEmpty()) {
            return;
        }
        List<String> stringTooltip = new ArrayList<>(tooltip.size());
        for (ITextComponent textComponent : tooltip) {
            stringTooltip.add(textComponent.getFormattedText());
        }

        RenderSystem.disableRescaleNormal();
        RenderSystem.disableDepthTest();
        int k = 0;

        for (String s : stringTooltip) {
            int l = font.getStringWidth(s);

            if (l > k) {
                k = l;
            }
        }
        int i1 = x + 12;
        int j1 = y - 12;
        int k1 = 8;

        if (tooltip.size() > 1) {
            k1 += 2 + (tooltip.size() - 1) * 10;
        }
        if (i1 + k > this.width) {
            i1 -= 28 + k;
        }
        if (j1 + k1 + 6 > this.height) {
            j1 = this.height - k1 - 6;
        }
        this.setBlitOffset(300);
        itemRenderer.zLevel = 300.0F;
        int l1 = -267386864;
        this.fillGradient(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
        this.fillGradient(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
        this.fillGradient(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
        this.fillGradient(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
        this.fillGradient(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
        int i2 = 1347420415;
        int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
        this.fillGradient(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
        this.fillGradient(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
        this.fillGradient(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
        this.fillGradient(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);

        MatrixStack matrixstack = new MatrixStack();
        IRenderTypeBuffer.Impl irendertypebuffer$impl = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
        matrixstack.translate(0.0D, 0.0D, this.itemRenderer.zLevel);
        Matrix4f matrix4f = matrixstack.getLast().getMatrix();

        for (int k2 = 0; k2 < tooltip.size(); ++k2) {
            String s1 = stringTooltip.get(k2);
            if (s1 != null) {
                font.renderString(s1, i1, j1, -1, true, matrix4f, irendertypebuffer$impl, false, 0, 15728880);
            }
            //font.drawStringWithShadow(s1, i1, j1, -1);

            if (k2 == 0) {
                j1 += 2;
            }
            j1 += 10;
        }
        irendertypebuffer$impl.finish();
        this.setBlitOffset(0);
        itemRenderer.zLevel = 0.0F;
        RenderSystem.enableDepthTest();
        RenderSystem.enableRescaleNormal();
    }
    // endregion

    // region IGuiAccess
    @Override
    public FontRenderer getFontRenderer() {

        return font;
    }

    @Override
    public void handleElementButtonClick(String buttonName, int mouseButton) {

    }

    @Override
    public void drawIcon(TextureAtlasSprite icon, int x, int y) {

        RenderHelper.setBlockTextureSheet();
        RenderHelper.resetColor();
        blit(x, y, this.getBlitOffset(), 16, 16, icon);
    }

    @Override
    public void drawIcon(ResourceLocation location, int x, int y) {

        drawIcon(RenderHelper.getTexture(location), x, y);
    }

    @Override
    public void drawSizedRect(int x1, int y1, int x2, int y2, int color) {

        int temp;

        if (x1 < x2) {
            temp = x1;
            x1 = x2;
            x2 = temp;
        }
        if (y1 < y2) {
            temp = y1;
            y1 = y2;
            y2 = temp;
        }

        float a = (color >> 24 & 255) / 255.0F;
        float r = (color >> 16 & 255) / 255.0F;
        float g = (color >> 8 & 255) / 255.0F;
        float b = (color & 255) / 255.0F;
        RenderSystem.disableTexture();
        RenderSystem.color4f(r, g, b, a);

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        buffer.pos(x1, y2, this.getBlitOffset()).endVertex();
        buffer.pos(x2, y2, this.getBlitOffset()).endVertex();
        buffer.pos(x2, y1, this.getBlitOffset()).endVertex();
        buffer.pos(x1, y1, this.getBlitOffset()).endVertex();
        Tessellator.getInstance().draw();
        RenderSystem.enableTexture();
    }

    @Override
    public void drawColoredModalRect(int x1, int y1, int x2, int y2, int color) {

        int temp;
        if (x1 < x2) {
            temp = x1;
            x1 = x2;
            x2 = temp;
        }
        if (y1 < y2) {
            temp = y1;
            y1 = y2;
            y2 = temp;
        }
        float a = (color >> 24 & 255) / 255.0F;
        float r = (color >> 16 & 255) / 255.0F;
        float g = (color >> 8 & 255) / 255.0F;
        float b = (color & 255) / 255.0F;
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.param, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.param);
        RenderSystem.color4f(r, g, b, a);

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        buffer.pos(x1, y2, this.getBlitOffset()).endVertex();
        buffer.pos(x2, y2, this.getBlitOffset()).endVertex();
        buffer.pos(x2, y1, this.getBlitOffset()).endVertex();
        buffer.pos(x1, y1, this.getBlitOffset()).endVertex();
        Tessellator.getInstance().draw();
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    @Override
    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {

        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x + 0, (y + height), this.getBlitOffset()).tex(((float) (textureX + 0) * 0.00390625F), ((float) (textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.pos((x + width), (y + height), this.getBlitOffset()).tex(((float) (textureX + width) * 0.00390625F), ((float) (textureY + height) * 0.00390625F)).endVertex();
        bufferbuilder.pos((x + width), (y + 0), this.getBlitOffset()).tex(((float) (textureX + width) * 0.00390625F), ((float) (textureY + 0) * 0.00390625F)).endVertex();
        bufferbuilder.pos((x + 0), (y + 0), this.getBlitOffset()).tex(((float) (textureX + 0) * 0.00390625F), ((float) (textureY + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
    }

    @Override
    public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height, float texW, float texH) {

        float texU = 1 / texW;
        float texV = 1 / texH;
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(x, y + height, this.getBlitOffset()).tex((u) * texU, (v + height) * texV).endVertex();
        buffer.pos(x + width, y + height, this.getBlitOffset()).tex((u + width) * texU, (v + height) * texV).endVertex();
        buffer.pos(x + width, y, this.getBlitOffset()).tex((u + width) * texU, (v) * texV).endVertex();
        buffer.pos(x, y, this.getBlitOffset()).tex((u) * texU, (v) * texV).endVertex();
        Tessellator.getInstance().draw();
    }
    // endregion
}
