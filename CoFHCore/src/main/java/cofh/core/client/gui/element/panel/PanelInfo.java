package cofh.core.client.gui.element.panel;

import cofh.core.client.gui.IGuiAccess;
import cofh.core.client.gui.CoreTextures;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PanelInfo extends PanelScrolledText {

    public static int defaultSide = LEFT;
    public static int defaultHeaderColor = 0xe1c92f;
    public static int defaultSubHeaderColor = 0xaaafb8;
    public static int defaultTextColor = 0xffffff;
    public static int defaultBackgroundColor = 0x555555;

    public PanelInfo(IGuiAccess gui, String info) {

        this(gui, defaultSide, info);
    }

    public PanelInfo(IGuiAccess gui, int side, String info) {

        super(gui, side, info);

        headerColor = defaultHeaderColor;
        subheaderColor = defaultSubHeaderColor;
        textColor = defaultTextColor;
        backgroundColor = defaultBackgroundColor;

        this.setVisible(() -> !info.isEmpty());
    }

    @Override
    public TextureAtlasSprite getIcon() {

        return CoreTextures.ICON_INFORMATION;
    }

    @Override
    public ITextComponent getTitle() {

        return new TranslationTextComponent("info.cofh.information");
    }

}
