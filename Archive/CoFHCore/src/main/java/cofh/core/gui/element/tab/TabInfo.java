package cofh.core.gui.element.tab;

import cofh.core.gui.IGuiAccess;
import cofh.core.gui.TexturesCoFH;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

import static cofh.lib.util.helpers.StringHelper.localize;

public class TabInfo extends TabScrolledText {

    public static int defaultSide = LEFT;
    public static int defaultHeaderColor = 0xe1c92f;
    public static int defaultSubHeaderColor = 0xaaafb8;
    public static int defaultTextColor = 0xffffff;
    public static int defaultBackgroundColor = 0x555555;

    public TabInfo(IGuiAccess gui, String info) {

        this(gui, defaultSide, info);
    }

    public TabInfo(IGuiAccess gui, int side, String info) {

        super(gui, side, info);

        headerColor = defaultHeaderColor;
        subheaderColor = defaultSubHeaderColor;
        textColor = defaultTextColor;
        backgroundColor = defaultBackgroundColor;

        this.setVisible(!info.isEmpty());
    }

    @Override
    public TextureAtlasSprite getIcon() {

        return TexturesCoFH.ICON_INFORMATION;
    }

    @Override
    public String getTitle() {

        return localize("info.cofh.information");
    }

}
