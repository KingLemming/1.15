package cofh.core.client.gui.element.panel;

import cofh.core.client.gui.CoreTextures;
import cofh.core.client.gui.IGuiAccess;
import cofh.core.client.gui.element.ElementSlot;
import cofh.core.util.GuiHelper;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.util.helpers.RenderHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static cofh.core.util.GuiHelper.SLOT_SIZE;
import static cofh.lib.util.helpers.StringHelper.localize;

public class PanelAugmentation extends PanelBase {

    public static int defaultSide = RIGHT;
    public static int defaultHeaderColor = 0xe1c92f;
    public static int defaultSubHeaderColor = 0xaaafb8;
    public static int defaultTextColor = 0x000000;
    public static int defaultBackgroundColor = 0x089e4c;

    // public static ResourceLocation GRID_TEXTURE = new ResourceLocation(PATH_ELEMENTS + "panel_grid.png");

    private int slotsBorderX1 = 18;
    private int slotsBorderX2 = slotsBorderX1 + 60;
    private int slotsBorderY1 = 20;
    private int slotsBorderY2 = slotsBorderY1 + 42;

    private final List<SlotCoFH> augmentSlots;
    private final List<ElementSlot> slotBackground = new ArrayList<>();

    public PanelAugmentation(IGuiAccess gui, List<SlotCoFH> augmentSlots) {

        this(gui, defaultSide, augmentSlots);
    }

    protected PanelAugmentation(IGuiAccess gui, int sideIn, @Nonnull List<SlotCoFH> augmentSlots) {

        super(gui, sideIn);

        headerColor = defaultHeaderColor;
        subheaderColor = defaultSubHeaderColor;
        textColor = defaultTextColor;
        backgroundColor = defaultBackgroundColor;

        maxHeight = 92;
        maxWidth = 102;

        this.augmentSlots = augmentSlots;

        for (SlotCoFH slot : this.augmentSlots) {
            slot.setEnabled(() -> fullyOpen);
        }
        int numAugments = this.augmentSlots.size();

        for (int i = 0; i < numAugments; ++i) {
            ElementSlot slot = GuiHelper.createSlot(gui, 0, 0);
            slotBackground.add(slot);
            addElement(slot);
        }
        switch (numAugments) {
            case 4:
                slotsBorderX1 += 9;
                slotsBorderX2 -= 9;
            case 5:
            case 6:
                break;
            case 7:
            case 8:
            case 9:
                slotsBorderY2 += 18;
                break;
            default:
                slotsBorderX1 += 9 * (3 - numAugments);
                slotsBorderX2 = slotsBorderX1 + 18 * numAugments + 6;
                slotsBorderY1 += 9;
                slotsBorderY2 -= 9;
        }
        this.setVisible(this.augmentSlots.size() > 0);
    }

    @Override
    protected void drawForeground() {

        drawPanelIcon(CoreTextures.ICON_AUGMENT);
        if (!fullyOpen) {
            return;
        }
        getFontRenderer().drawStringWithShadow(localize("info.cofh.augmentation"), sideOffset() + 18, 6, headerColor);

        RenderHelper.resetColor();
    }

    @Override
    protected void drawBackground() {

        super.drawBackground();

        if (!fullyOpen) {
            return;
        }
        float colorR = (backgroundColor >> 16 & 255) / 255.0F * 0.6F;
        float colorG = (backgroundColor >> 8 & 255) / 255.0F * 0.6F;
        float colorB = (backgroundColor & 255) / 255.0F * 0.6F;
        RenderSystem.color4f(colorR, colorG, colorB, 1.0F);
        gui.drawTexturedModalRect(sideOffset() + slotsBorderX1, slotsBorderY1, 16, 20, slotsBorderX2 - slotsBorderX1, slotsBorderY2 - slotsBorderY1);
        RenderHelper.resetColor();
    }

    @Override
    public void addTooltip(List<ITextComponent> tooltipList, int mouseX, int mouseY) {

        if (!fullyOpen) {
            tooltipList.add(new TranslationTextComponent("info.cofh.augmentation"));
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

        if (!fullyOpen) {
            return false;
        }
        double x = mouseX - this.posX();
        double y = mouseY - this.posY;

        return x >= slotsBorderX1 + sideOffset() && x < slotsBorderX2 + sideOffset() && y >= slotsBorderY1 && y < slotsBorderY2;
    }

    @Override
    public void setFullyOpen() {

        int numAugments = augmentSlots.size();
        int borderOffset = 4;

        switch (numAugments) {
            case 4:
                for (int i = 0; i < numAugments; ++i) {
                    augmentSlots.get(i).xPos = posXOffset() + slotsBorderX1 + borderOffset + SLOT_SIZE * (i % 2);
                    augmentSlots.get(i).yPos = posY + slotsBorderY1 + borderOffset + SLOT_SIZE * (i / 2);
                }
                break;
            case 5:
                for (int i = 0; i < numAugments; ++i) {
                    augmentSlots.get(i).xPos = posXOffset() + slotsBorderX1 + borderOffset + SLOT_SIZE * (i % 3) + 9 * (i / 3);
                    augmentSlots.get(i).yPos = posY + slotsBorderY1 + borderOffset + SLOT_SIZE * (i / 3);
                }
                break;
            case 7:
                for (int i = 0; i < 2; ++i) {
                    augmentSlots.get(i).xPos = posXOffset() + slotsBorderX1 + borderOffset + 9 + SLOT_SIZE * (i);
                    augmentSlots.get(i).yPos = posY + slotsBorderY1 + borderOffset;
                }
                for (int i = 2; i < 5; ++i) {
                    augmentSlots.get(i).xPos = posXOffset() + slotsBorderX1 + borderOffset + SLOT_SIZE * (i - 2);
                    augmentSlots.get(i).yPos = posY + slotsBorderY1 + borderOffset + SLOT_SIZE;
                }
                for (int i = 5; i < numAugments; ++i) {
                    augmentSlots.get(i).xPos = posXOffset() + slotsBorderX1 + borderOffset + 9 + SLOT_SIZE * (i - 5);
                    augmentSlots.get(i).yPos = posY + slotsBorderY1 + borderOffset + SLOT_SIZE * 2;
                }
                break;
            case 8:
                for (int i = 0; i < 3; ++i) {
                    augmentSlots.get(i).xPos = posXOffset() + slotsBorderX1 + borderOffset + SLOT_SIZE * i;
                    augmentSlots.get(i).yPos = posY + slotsBorderY1 + borderOffset;
                }
                for (int i = 3; i < 5; ++i) {
                    augmentSlots.get(i).xPos = posXOffset() + slotsBorderX1 + borderOffset + 9 + SLOT_SIZE * (i - 3);
                    augmentSlots.get(i).yPos = posY + slotsBorderY1 + borderOffset + SLOT_SIZE;
                }
                for (int i = 5; i < numAugments; ++i) {
                    augmentSlots.get(i).xPos = posXOffset() + slotsBorderX1 + borderOffset + SLOT_SIZE * (i - 5);
                    augmentSlots.get(i).yPos = posY + slotsBorderY1 + borderOffset + SLOT_SIZE * 2;
                }
                break;
            default:
                for (int i = 0; i < numAugments; ++i) {
                    augmentSlots.get(i).xPos = posXOffset() + slotsBorderX1 + borderOffset + SLOT_SIZE * (i % 3);
                    augmentSlots.get(i).yPos = posY + slotsBorderY1 + borderOffset + SLOT_SIZE * (i / 3);
                }
        }
        for (int i = 0; i < numAugments; ++i) {
            slotBackground.get(i).setPosition(augmentSlots.get(i).xPos - posX - 1, augmentSlots.get(i).yPos - posY - 1);
        }
        super.setFullyOpen();
    }

}
