package cofh.core.gui.element.tab;

import cofh.core.gui.IGuiAccess;
import cofh.lib.util.control.ISecurable;
import cofh.lib.util.helpers.RenderHelper;
import cofh.lib.util.helpers.SoundHelper;
import com.mojang.blaze3d.platform.GlStateManager;

import java.util.List;
import java.util.UUID;

import static cofh.core.gui.TexturesCoFH.*;
import static cofh.lib.util.control.ISecurable.AccessMode.*;
import static cofh.lib.util.helpers.StringHelper.YELLOW;
import static cofh.lib.util.helpers.StringHelper.localize;

public class TabSecurity extends TabBase {

    public static int defaultSide = LEFT;
    public static int defaultHeaderColor = 0xe1c92f;
    public static int defaultSubHeaderColor = 0xaaafb8;
    public static int defaultTextColor = 0x000000;
    public static int defaultBackgroundColor = 0x888888;

    private ISecurable mySecurable;
    private UUID myPlayer;

    public TabSecurity(IGuiAccess gui, ISecurable securable, UUID playerID) {

        this(gui, defaultSide, securable, playerID);
    }

    public TabSecurity(IGuiAccess gui, int side, ISecurable securable, UUID playerID) {

        super(gui, side);

        headerColor = defaultHeaderColor;
        subheaderColor = defaultSubHeaderColor;
        textColor = defaultTextColor;
        backgroundColor = defaultBackgroundColor;

        maxHeight = 92;
        maxWidth = 112;
        mySecurable = securable;
        myPlayer = playerID;

        this.setVisible(mySecurable::isSecurable);
    }

    @Override
    protected void drawForeground() {

        switch (mySecurable.getAccess()) {
            case PUBLIC:
                drawTabIcon(ICON_ACCESS_PUBLIC);
                break;
            case FRIENDS:
                drawTabIcon(ICON_ACCESS_FRIENDS);
                break;
            case PRIVATE:
                drawTabIcon(ICON_ACCESS_PRIVATE);
                break;
        }
        if (!fullyOpen) {
            return;
        }
        getFontRenderer().drawStringWithShadow(localize("info.cofh.security"), sideOffset() + 18, 6, headerColor);
        getFontRenderer().drawStringWithShadow(localize("info.cofh.access") + ":", sideOffset() + 6, 42, subheaderColor);

        gui.drawIcon(ICON_BUTTON, 28, 20);
        gui.drawIcon(ICON_BUTTON, 48, 20);
        gui.drawIcon(ICON_BUTTON, 68, 20);

        switch (mySecurable.getAccess()) {
            case PUBLIC:
                gui.drawIcon(ICON_BUTTON_HIGHLIGHT, 28, 20);
                getFontRenderer().drawString(localize("info.cofh.access_public"), sideOffset() + 14, 54, textColor);
                break;
            case FRIENDS:
                gui.drawIcon(ICON_BUTTON_HIGHLIGHT, 48, 20);
                getFontRenderer().drawString(localize("info.cofh.access_friends"), sideOffset() + 14, 54, textColor);
                break;
            case PRIVATE:
                gui.drawIcon(ICON_BUTTON_HIGHLIGHT, 68, 20);
                getFontRenderer().drawString(localize("info.cofh.access_private"), sideOffset() + 14, 54, textColor);
                break;
        }
        gui.drawIcon(ICON_ACCESS_PUBLIC, 28, 20);
        gui.drawIcon(ICON_ACCESS_FRIENDS, 48, 20);
        gui.drawIcon(ICON_ACCESS_PRIVATE, 68, 20);

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
        GlStateManager.color4f(colorR, colorG, colorB, 1.0F);
        gui.drawTexturedModalRect(24, 16, 16, 20, 64, 24);
        RenderHelper.resetColor();
    }

    @Override
    public void addTooltip(List<String> tooltip, int mouseX, int mouseY) {

        if (!fullyOpen) {
            tooltip.add(localize("info.cofh.owner") + ": " + mySecurable.getOwnerName());
            switch (mySecurable.getAccess()) {
                case PUBLIC:
                    tooltip.add(YELLOW + localize("info.cofh.access_public"));
                    break;
                case FRIENDS:
                    tooltip.add(YELLOW + localize("info.cofh.access_friends"));
                    break;
                case PRIVATE:
                    tooltip.add(YELLOW + localize("info.cofh.access_private"));
                    break;
                default:
            }
            return;
        }
        int x = mouseX - this.posX();
        int y = mouseY - this.posY;

        if (28 <= x && x < 44 && 20 <= y && y < 36) {
            tooltip.add(localize("info.cofh.access_public"));
        } else if (48 <= x && x < 64 && 20 <= y && y < 36) {
            tooltip.add(localize("info.cofh.access_friends"));
        } else if (68 <= x && x < 84 && 20 <= y && y < 36) {
            tooltip.add(localize("info.cofh.access_private"));
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

        if (x < 24 || x >= 88 || y < 16 || y >= 40) {
            return false;
        }
        if (28 <= x && x < 44 && 20 <= y && y < 36) {
            if (mySecurable.getAccess() != PUBLIC) {
                mySecurable.setAccess(PUBLIC);
                SoundHelper.playClickSound(0.4F);
            }
        } else if (48 <= x && x < 64 && 20 <= y && y < 36) {
            if (mySecurable.getAccess() != FRIENDS) {
                mySecurable.setAccess(FRIENDS);
                SoundHelper.playClickSound(0.6F);
            }
        } else if (68 <= x && x < 84 && 20 <= y && y < 36) {
            if (mySecurable.getAccess() != PRIVATE) {
                mySecurable.setAccess(PRIVATE);
                SoundHelper.playClickSound(0.8F);
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
