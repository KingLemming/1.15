package cofh.core.client.gui.element.panel;

import cofh.core.client.gui.IGuiAccess;
import cofh.core.client.gui.CoreTextures;
import cofh.lib.util.control.IRedstoneControllable;
import cofh.lib.util.helpers.RenderHelper;
import cofh.lib.util.helpers.SoundHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

import static cofh.core.client.gui.CoreTextures.*;
import static cofh.lib.util.control.IRedstoneControllable.ControlMode.*;
import static cofh.lib.util.helpers.StringHelper.localize;

public class PanelRedstoneControl extends PanelBase {

    public static int defaultSide = RIGHT;
    public static int defaultHeaderColor = 0xe1c92f;
    public static int defaultSubHeaderColor = 0xaaafb8;
    public static int defaultTextColor = 0x000000;
    public static int defaultBackgroundColor = 0xd0230a;

    private IRedstoneControllable myRSControllable;

    public PanelRedstoneControl(IGuiAccess gui, IRedstoneControllable rsControllable) {

        this(gui, defaultSide, rsControllable);
    }

    public PanelRedstoneControl(IGuiAccess gui, int side, IRedstoneControllable rsControllable) {

        super(gui, side);

        headerColor = defaultHeaderColor;
        subheaderColor = defaultSubHeaderColor;
        textColor = defaultTextColor;
        backgroundColor = defaultBackgroundColor;

        maxHeight = 92;
        maxWidth = 112;
        myRSControllable = rsControllable;

        this.setVisible(myRSControllable::isControllable);
    }

    // TODO: Fully support new Redstone Control system.
    @Override
    protected void drawForeground() {

        drawPanelIcon(CoreTextures.ICON_REDSTONE_ON);
        if (!fullyOpen) {
            return;
        }
        getFontRenderer().drawStringWithShadow(localize("info.cofh.redstone_control"), sideOffset() + 18, 6, headerColor);
        getFontRenderer().drawStringWithShadow(localize("info.cofh.control_status") + ":", sideOffset() + 6, 42, subheaderColor);
        getFontRenderer().drawStringWithShadow(localize("info.cofh.signal_required") + ":", sideOffset() + 6, 66, subheaderColor);

        gui.drawIcon(ICON_BUTTON, 28, 20);
        gui.drawIcon(ICON_BUTTON, 48, 20);
        gui.drawIcon(ICON_BUTTON, 68, 20);

        switch (myRSControllable.getMode()) {
            case DISABLED:
                gui.drawIcon(CoreTextures.ICON_BUTTON_HIGHLIGHT, 28, 20);
                getFontRenderer().drawString(localize("info.cofh.disabled"), sideOffset() + 14, 54, textColor);
                getFontRenderer().drawString(localize("info.cofh.ignored"), sideOffset() + 14, 78, textColor);
                break;
            case LOW:
                gui.drawIcon(CoreTextures.ICON_BUTTON_HIGHLIGHT, 48, 20);
                getFontRenderer().drawString(localize("info.cofh.enabled"), sideOffset() + 14, 54, textColor);
                getFontRenderer().drawString(localize("info.cofh.low"), sideOffset() + 14, 78, textColor);
                break;
            case HIGH:
                gui.drawIcon(CoreTextures.ICON_BUTTON_HIGHLIGHT, 68, 20);
                getFontRenderer().drawString(localize("info.cofh.enabled"), sideOffset() + 14, 54, textColor);
                getFontRenderer().drawString(localize("info.cofh.high"), sideOffset() + 14, 78, textColor);
                break;
            default:
        }
        gui.drawIcon(ICON_REDSTONE_OFF, 28, 20);
        gui.drawIcon(ICON_RS_TORCH_OFF, 48, 20);
        gui.drawIcon(ICON_RS_TORCH_ON, 68, 20);

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
        gui.drawTexturedModalRect(24, 16, 16, 20, 64, 24);
        RenderHelper.resetColor();
    }

    @Override
    public void addTooltip(List<ITextComponent> tooltipList, int mouseX, int mouseY) {

        if (!fullyOpen) {
            tooltipList.add(new TranslationTextComponent("info.cofh.redstone_control"));
            switch (myRSControllable.getMode()) {
                case DISABLED:
                    tooltipList.add(new TranslationTextComponent("info.cofh.disabled").applyTextStyle(TextFormatting.YELLOW));
                    break;
                case LOW:
                    tooltipList.add(new TranslationTextComponent("info.cofh.low").applyTextStyle(TextFormatting.YELLOW));
                    break;
                case HIGH:
                    tooltipList.add(new TranslationTextComponent("info.cofh.high").applyTextStyle(TextFormatting.YELLOW));
                    break;
                default:
            }
            return;
        }
        int x = mouseX - this.posX();
        int y = mouseY - this.posY;

        if (28 <= x && x < 44 && 20 <= y && y < 36) {
            tooltipList.add(new TranslationTextComponent("info.cofh.ignored"));
        } else if (48 <= x && x < 64 && 20 <= y && y < 36) {
            tooltipList.add(new TranslationTextComponent("info.cofh.low"));
        } else if (68 <= x && x < 84 && 20 <= y && y < 36) {
            tooltipList.add(new TranslationTextComponent("info.cofh.high"));
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

        if (!fullyOpen) {
            return false;
        }
        double x = mouseX - this.posX();
        double y = mouseY - this.posY;

        if (x < 24 || x >= 88 || y < 16 || y >= 40) {
            return false;
        }
        if (28 <= x && x < 44 && 20 <= y && y < 36) {
            if (myRSControllable.getMode() != DISABLED) {
                myRSControllable.setControl(0, DISABLED);
                SoundHelper.playClickSound(0.4F);
            }
        } else if (48 <= x && x < 64 && 20 <= y && y < 36) {
            if (myRSControllable.getMode() != LOW) {
                myRSControllable.setControl(0, LOW);
                SoundHelper.playClickSound(0.6F);
            }
        } else if (68 <= x && x < 84 && 20 <= y && y < 36) {
            if (myRSControllable.getMode() != HIGH) {
                myRSControllable.setControl(0, HIGH);
                SoundHelper.playClickSound(0.8F);
            }
        }
        return true;
    }

}
