package cofh.core.gui.element.panel;

import cofh.core.gui.IGuiAccess;
import cofh.lib.util.control.ISecurable;
import cofh.lib.util.helpers.RenderHelper;
import cofh.lib.util.helpers.SoundHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;
import java.util.UUID;

import static cofh.core.gui.TexturesCoFH.*;
import static cofh.lib.util.control.ISecurable.AccessMode.*;
import static cofh.lib.util.helpers.StringHelper.localize;

public class PanelSecurity extends PanelBase {

    public static int defaultSide = LEFT;
    public static int defaultHeaderColor = 0xe1c92f;
    public static int defaultSubHeaderColor = 0xaaafb8;
    public static int defaultTextColor = 0x000000;
    public static int defaultBackgroundColor = 0x888888;

    private ISecurable mySecurable;
    private UUID myPlayer;

    public PanelSecurity(IGuiAccess gui, ISecurable securable, UUID playerID) {

        this(gui, defaultSide, securable, playerID);
    }

    public PanelSecurity(IGuiAccess gui, int side, ISecurable securable, UUID playerID) {

        super(gui, side);

        headerColor = defaultHeaderColor;
        subheaderColor = defaultSubHeaderColor;
        textColor = defaultTextColor;
        backgroundColor = defaultBackgroundColor;

        maxHeight = 92;
        maxWidth = 112;
        mySecurable = securable;
        myPlayer = playerID;

        this.setVisible(mySecurable::hasSecurity);
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
        gui.drawTexturedModalRect(34, 18, 16, 20, 44, 44);
        RenderHelper.resetColor();
    }

    @Override
    protected void drawForeground() {

        switch (mySecurable.getAccess()) {
            case PUBLIC:
                drawPanelIcon(ICON_ACCESS_PUBLIC);
                break;
            case PRIVATE:
                drawPanelIcon(ICON_ACCESS_PRIVATE);
                break;
            case FRIENDS:
                drawPanelIcon(ICON_ACCESS_FRIENDS);
                break;
            case TEAM:
                drawPanelIcon(ICON_ACCESS_TEAM);
                break;
        }
        if (!fullyOpen) {
            return;
        }
        getFontRenderer().drawStringWithShadow(localize("info.cofh.security"), sideOffset() + 18, 6, headerColor);
        getFontRenderer().drawStringWithShadow(localize("info.cofh.access") + ":", sideOffset() + 6, 66, subheaderColor);

        gui.drawIcon(ICON_BUTTON, 38, 22);
        gui.drawIcon(ICON_BUTTON, 58, 22);
        gui.drawIcon(ICON_BUTTON, 38, 42);
        gui.drawIcon(ICON_BUTTON, 58, 42);

        switch (mySecurable.getAccess()) {
            case PUBLIC:
                gui.drawIcon(ICON_BUTTON_HIGHLIGHT, 38, 22);
                getFontRenderer().drawString(localize("info.cofh.access_public"), sideOffset() + 14, 78, textColor);
                break;
            case PRIVATE:
                gui.drawIcon(ICON_BUTTON_HIGHLIGHT, 58, 22);
                getFontRenderer().drawString(localize("info.cofh.access_private"), sideOffset() + 14, 78, textColor);
                break;
            case FRIENDS:
                gui.drawIcon(ICON_BUTTON_HIGHLIGHT, 38, 42);
                getFontRenderer().drawString(localize("info.cofh.access_friends"), sideOffset() + 14, 78, textColor);
                break;
            case TEAM:
                gui.drawIcon(ICON_BUTTON_HIGHLIGHT, 58, 42);
                getFontRenderer().drawString(localize("info.cofh.access_team"), sideOffset() + 14, 78, textColor);
                break;
        }
        gui.drawIcon(ICON_ACCESS_PUBLIC, 38, 22);
        gui.drawIcon(ICON_ACCESS_PRIVATE, 58, 22);
        gui.drawIcon(ICON_ACCESS_FRIENDS, 38, 42);
        gui.drawIcon(ICON_ACCESS_TEAM, 58, 42);

        RenderHelper.resetColor();
    }

    @Override
    public void addTooltip(List<ITextComponent> tooltipList, int mouseX, int mouseY) {

        if (!fullyOpen) {
            tooltipList.add(new TranslationTextComponent("info.cofh.owner").appendSibling(new StringTextComponent(": " + mySecurable.getOwnerName())));
            switch (mySecurable.getAccess()) {
                case PUBLIC:
                    tooltipList.add(new TranslationTextComponent("info.cofh.access_public").applyTextStyle(TextFormatting.YELLOW));
                    break;
                case PRIVATE:
                    tooltipList.add(new TranslationTextComponent("info.cofh.access_private").applyTextStyle(TextFormatting.YELLOW));
                    break;
                case FRIENDS:
                    tooltipList.add(new TranslationTextComponent("info.cofh.access_friends").applyTextStyle(TextFormatting.YELLOW));
                    break;
                case TEAM:
                    tooltipList.add(new TranslationTextComponent("info.cofh.access_team").applyTextStyle(TextFormatting.YELLOW));
                    break;
                default:
            }
            return;
        }
        int x = mouseX - this.posX();
        int y = mouseY - this.posY;

        if (38 <= x && x < 54 && 22 <= y && y < 38) {
            tooltipList.add(new TranslationTextComponent("info.cofh.access_public"));
        } else if (58 <= x && x < 74 && 22 <= y && y < 38) {
            tooltipList.add(new TranslationTextComponent("info.cofh.access_private"));
        } else if (38 <= x && x < 54 && 42 <= y && y < 58) {
            tooltipList.add(new TranslationTextComponent("info.cofh.access_friends"));
        } else if (58 <= x && x < 74 && 42 <= y && y < 58) {
            tooltipList.add(new TranslationTextComponent("info.cofh.access_team"));
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

        if (!myPlayer.equals(mySecurable.getOwner().getId())) {
            return true;
        }
        if (!fullyOpen) {
            return false;
        }
        double x = mouseX - this.posX();
        double y = mouseY - this.posY;

        if (x < 34 || x >= 78 || y < 18 || y >= 62) {
            return false;
        }
        if (38 <= x && x < 54 && 22 <= y && y < 38) {
            if (mySecurable.getAccess() != PUBLIC) {
                mySecurable.setAccess(PUBLIC);
                SoundHelper.playClickSound(0.5F);
            }
        } else if (58 <= x && x < 74 && 22 <= y && y < 38) {
            if (mySecurable.getAccess() != PRIVATE) {
                mySecurable.setAccess(PRIVATE);
                SoundHelper.playClickSound(0.8F);
            }
        } else if (38 <= x && x < 54 && 42 <= y && y < 58) {
            if (mySecurable.getAccess() != FRIENDS) {
                mySecurable.setAccess(FRIENDS);
                SoundHelper.playClickSound(0.6F);
            }
        } else if (58 <= x && x < 74 && 42 <= y && y < 58) {
            if (mySecurable.getAccess() != TEAM) {
                mySecurable.setAccess(TEAM);
                SoundHelper.playClickSound(0.7F);
            }
        }
        return true;
    }

    @Override
    public void setFullyOpen() {

        if (!myPlayer.equals(mySecurable.getOwner().getId())) {
            return;
        }
        super.setFullyOpen();
    }

}
