package cofh.core.gui.client;

import cofh.core.gui.IGuiAccess;
import cofh.core.gui.element.ElementBase;
import cofh.core.gui.element.tab.TabBase;
import cofh.core.gui.element.tab.TabTracker;
import cofh.lib.inventory.container.slot.SlotFalseCopy;
import cofh.lib.util.helpers.RenderHelper;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
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

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static cofh.lib.util.helpers.StringHelper.localize;

public abstract class ContainerScreenCoFH<T extends Container> extends ContainerScreen implements IGuiAccess {

    protected int mX;
    protected int mY;
    protected int lastIndex = -1;

    protected String name;
    protected String info;
    protected ResourceLocation texture;
    protected PlayerEntity player;

    protected ArrayList<TabBase> tabs = new ArrayList<>();
    protected ArrayList<ElementBase> elements = new ArrayList<>();
    protected List<String> tooltip = new LinkedList<>();

    protected boolean drawTitle = true;
    protected boolean drawInventory = true;
    protected boolean showTooltips = true;

    public ContainerScreenCoFH(Container screenContainer, PlayerInventory inv, ITextComponent titleIn) {

        super(screenContainer, inv, titleIn);

        player = inv.player;
    }

    @Override
    public void init() {

        super.init();
        tabs.clear();
        elements.clear();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTick) {

        updateTabInformation();
        updateElementInformation();

        mX = mouseX - guiLeft;
        mY = mouseY - guiTop;

        renderBackground();
        super.render(mouseX, mouseY, partialTick);
        renderHoveredToolTip(mouseX, mouseY);

        if (showTooltips && getMinecraft().player.inventory.getItemStack().isEmpty()) {
            drawTooltip();
        }
        updateTabs();
        updateElements();
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
        GlStateManager.pushMatrix();
        GlStateManager.translatef(guiLeft, guiTop, 0.0F);

        drawTabs(false);
        drawElements(false);

        GlStateManager.popMatrix();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        // GlStateManager.pushMatrix();
        // GlStateManager.translatef(guiLeft, guiTop, 0.0F);

        if (drawTitle & name != null) {
            getFontRenderer().drawString(localize(name), getCenteredOffset(localize(name)), 6, 0x404040);
        }
        if (drawInventory) {
            getFontRenderer().drawString(localize("container.inventory"), 8, ySize - 96 + 3, 0x404040);
        }
        drawTabs(true);
        drawElements(true);

        // GlStateManager.popMatrix();
    }

    // region ELEMENTS
    public void drawTooltip() {

        TabBase tab = getTabAtPosition(mX, mY);

        if (tab != null) {
            tab.addTooltip(tooltip, mX, mY);
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
     * Draws the Tabs for this GUI. Open / close animation is part of this.
     */
    protected void drawTabs(boolean foreground) {

        int yPosRight = 4;
        int yPosLeft = 4;

        if (foreground) {
            for (TabBase tab : tabs) {
                tab.updateSize();
                if (!tab.visible()) {
                    continue;
                }
                if (tab.side == TabBase.LEFT) {
                    tab.drawForeground(mX, mY);
                    yPosLeft += tab.height();
                } else {
                    tab.drawForeground(mX, mY);
                    yPosRight += tab.height();
                }
            }
        } else {
            for (TabBase tab : tabs) {
                tab.updateSize();
                if (!tab.visible()) {
                    continue;
                }
                if (tab.side == TabBase.LEFT) {
                    tab.setPosition(0, yPosLeft);
                    tab.drawBackground(mX, mY);
                    yPosLeft += tab.height();
                } else {
                    tab.setPosition(xSize, yPosRight);
                    tab.drawBackground(mX, mY);
                    yPosRight += tab.height();
                }
            }
        }
    }

    protected ElementBase addElement(ElementBase element) {

        elements.add(element);
        return element;
    }

    protected TabBase addTab(TabBase tab) {

        int yOffset = 4;
        for (TabBase tab1 : tabs) {
            if (tab1.side == tab.side && tab1.visible()) {
                yOffset += tab1.height();
            }
        }
        tab.setPosition(tab.side == TabBase.LEFT ? 0 : xSize, yOffset);
        tabs.add(tab);

        if (TabTracker.getOpenedLeftTab() != null && tab.getClass().equals(TabTracker.getOpenedLeftTab())) {
            tab.setFullyOpen();
        } else if (TabTracker.getOpenedRightTab() != null && tab.getClass().equals(TabTracker.getOpenedRightTab())) {
            tab.setFullyOpen();
        }
        return tab;
    }

    private ElementBase getElementAtPosition(int mouseX, int mouseY) {

        for (int i = elements.size(); --i > 0; ) {
            ElementBase element = elements.get(i);
            if (element.intersectsWith(mouseX, mouseY)) {
                return element;
            }
        }
        return null;
    }

    private TabBase getTabAtPosition(double mouseX, double mouseY) {

        int xShift = 0;
        int yShift = 4;

        // LEFT SIDE
        for (TabBase tab : tabs) {
            if (!tab.visible() || tab.side == TabBase.RIGHT) {
                continue;
            }
            tab.setShift(xShift, yShift);
            if (tab.intersectsWith(mouseX, mouseY, xShift, yShift)) {
                return tab;
            }
            yShift += tab.height();
        }

        xShift = xSize;
        yShift = 4;
        // RIGHT SIDE
        for (TabBase tab : tabs) {
            if (!tab.visible() || tab.side == TabBase.LEFT) {
                continue;
            }
            tab.setShift(xShift, yShift);
            if (tab.intersectsWith(mouseX, mouseY, xShift, yShift)) {
                return tab;
            }
            yShift += tab.height();
        }
        return null;
    }

    private void updateElements() {

        for (int i = elements.size(); --i > 0; ) {
            ElementBase c = elements.get(i);
            if (c.visible() && c.enabled()) {
                c.update(mX, mY);
            }
        }
    }

    private void updateTabs() {

        for (int i = tabs.size(); --i > 0; ) {
            TabBase c = tabs.get(i);
            if (c.visible() && c.enabled()) {
                c.update(mX, mY);
            }
        }
    }

    protected void updateElementInformation() {

    }

    protected void updateTabInformation() {

    }

    public List<Rectangle> getTabBounds() {

        List<Rectangle> tabBounds = new ArrayList<>();

        for (TabBase c : tabs) {
            tabBounds.add(c.getBoundsOnScreen());
        }
        return tabBounds;
    }
    // endregion

    // region CALLBACKS
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

        mouseX -= guiLeft;
        mouseY -= guiTop;

        for (int i = elements.size(); --i > 0; ) {
            ElementBase c = elements.get(i);
            if (!c.visible() || !c.enabled() || !c.intersectsWith(mouseX, mouseY)) {
                continue;
            }
            if (c.mouseClicked(mouseX, mouseY, mouseButton)) {
                return true;
            }
        }
        TabBase tab = getTabAtPosition(mouseX, mouseY);
        if (tab != null) {
            if (!tab.mouseClicked(mouseX, mouseY, mouseButton)) {
                for (int i = tabs.size(); --i > 0; ) {
                    TabBase other = tabs.get(i);
                    if (other != tab && other.open && other.side == tab.side) {
                        other.toggleOpen();
                    }
                }
                tab.toggleOpen();
                return true;
            }
        }
        mouseX += guiLeft;
        mouseY += guiTop;

        // If a tab is open, expand GUI size to support slot actions.
        if (tab != null) {
            switch (tab.side) {
                case TabBase.LEFT:
                    guiLeft -= tab.width();
                    break;
                case TabBase.RIGHT:
                    xSize += tab.width();
                    break;
            }
        }
        boolean ret = super.mouseClicked(mouseX, mouseY, mouseButton);

        // Re-adjust GUI size after click has happened.
        if (tab != null) {
            switch (tab.side) {
                case TabBase.LEFT:
                    guiLeft += tab.width();
                    break;
                case TabBase.RIGHT:
                    xSize -= tab.width();
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
            for (int i = elements.size(); --i > 0; ) {
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
    protected boolean func_195363_d(int keyCode, int scanCode) {

        for (int i = elements.size(); --i > 0; ) {
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
    protected boolean mouseWheel(int mouseX, int mouseY, int wheelMovement) {

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

    protected void drawTooltipHoveringText(List<String> tooltip, int x, int y, FontRenderer font) {

        if (tooltip == null || tooltip.isEmpty()) {
            return;
        }
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableLighting();
        GlStateManager.disableDepthTest();
        int k = 0;

        for (String s : tooltip) {
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
        this.blitOffset = 300;
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

        for (int k2 = 0; k2 < tooltip.size(); ++k2) {
            String s1 = tooltip.get(k2);
            font.drawStringWithShadow(s1, i1, j1, -1);

            if (k2 == 0) {
                j1 += 2;
            }
            j1 += 10;
        }
        this.blitOffset = 0;
        itemRenderer.zLevel = 0.0F;
        GlStateManager.enableLighting();
        GlStateManager.enableDepthTest();
        GlStateManager.enableRescaleNormal();
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
        blit(x, y, 16, 16, blitOffset, icon);
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
        GlStateManager.disableTexture();
        GlStateManager.color4f(r, g, b, a);

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        buffer.pos(x1, y2, blitOffset).endVertex();
        buffer.pos(x2, y2, blitOffset).endVertex();
        buffer.pos(x2, y1, blitOffset).endVertex();
        buffer.pos(x1, y1, blitOffset).endVertex();
        Tessellator.getInstance().draw();
        GlStateManager.enableTexture();
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
        GlStateManager.enableBlend();
        GlStateManager.disableTexture();
        GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color4f(r, g, b, a);

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        buffer.pos(x1, y2, blitOffset).endVertex();
        buffer.pos(x2, y2, blitOffset).endVertex();
        buffer.pos(x2, y1, blitOffset).endVertex();
        buffer.pos(x1, y1, blitOffset).endVertex();
        Tessellator.getInstance().draw();
        GlStateManager.enableTexture();
        GlStateManager.disableBlend();
    }

    @Override
    public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height, float texW, float texH) {

        float texU = 1 / texW;
        float texV = 1 / texH;
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(x, y + height, blitOffset).tex((u) * texU, (v + height) * texV).endVertex();
        buffer.pos(x + width, y + height, blitOffset).tex((u + width) * texU, (v + height) * texV).endVertex();
        buffer.pos(x + width, y, blitOffset).tex((u + width) * texU, (v) * texV).endVertex();
        buffer.pos(x, y, blitOffset).tex((u) * texU, (v) * texV).endVertex();
        Tessellator.getInstance().draw();
    }
    // endregion
}
