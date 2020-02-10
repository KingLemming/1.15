package cofh.core.gui;

import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;

public class TexturesCoFH {

    private TexturesCoFH() {

    }

    public static void preStitch(TextureStitchEvent.Pre event) {

        if (!event.getMap().getBasePath().equals("textures")) {
            return;
        }
        event.addSprite(new ResourceLocation(ICONS_ + "icon_access_public"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_access_team"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_access_friends"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_access_private"));

        event.addSprite(new ResourceLocation(ICONS_ + "icon_accept"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_accept_inactive"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_augment"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_button"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_button_highlight"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_button_inactive"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_cancel"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_cancel_inactive"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_config"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_energy"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_nope"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_information"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_steam"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_tutorial"));

        event.addSprite(new ResourceLocation(ICONS_ + "icon_input"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_output"));

        event.addSprite(new ResourceLocation(ICONS_ + "icon_redstone_off"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_redstone_on"));

        event.addSprite(new ResourceLocation(ICONS_ + "icon_rs_torch_off"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_rs_torch_on"));

        event.addSprite(new ResourceLocation(ICONS_ + "icon_arrow_down"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_arrow_down_inactive"));

        event.addSprite(new ResourceLocation(ICONS_ + "icon_arrow_up"));
        event.addSprite(new ResourceLocation(ICONS_ + "icon_arrow_up_inactive"));
    }

    public static void postStitch(TextureStitchEvent.Post event) {

        if (!event.getMap().getBasePath().equals("textures")) {
            return;
        }
        AtlasTexture map = event.getMap();

        ICON_ACCESS_PUBLIC = map.getAtlasSprite(ICONS_ + "icon_access_public");
        ICON_ACCESS_PRIVATE = map.getAtlasSprite(ICONS_ + "icon_access_private");
        ICON_ACCESS_FRIENDS = map.getAtlasSprite(ICONS_ + "icon_access_friends");
        ICON_ACCESS_TEAM = map.getAtlasSprite(ICONS_ + "icon_access_team");

        ICON_ACCEPT = map.getAtlasSprite(ICONS_ + "icon_accept");
        ICON_ACCEPT_INACTIVE = map.getAtlasSprite(ICONS_ + "icon_accept_inactive");
        ICON_AUGMENT = map.getAtlasSprite(ICONS_ + "icon_augment");
        ICON_BUTTON = map.getAtlasSprite(ICONS_ + "icon_button");
        ICON_BUTTON_HIGHLIGHT = map.getAtlasSprite(ICONS_ + "icon_button_highlight");
        ICON_BUTTON_INACTIVE = map.getAtlasSprite(ICONS_ + "icon_button_inactive");
        ICON_CANCEL = map.getAtlasSprite(ICONS_ + "icon_cancel");
        ICON_CANCEL_INACTIVE = map.getAtlasSprite(ICONS_ + "icon_cancel_inactive");
        ICON_CONFIG = map.getAtlasSprite(ICONS_ + "icon_config");
        ICON_ENERGY = map.getAtlasSprite(ICONS_ + "icon_energy");
        ICON_NOPE = map.getAtlasSprite(ICONS_ + "icon_nope");
        ICON_INFORMATION = map.getAtlasSprite(ICONS_ + "icon_information");
        ICON_STEAM = map.getAtlasSprite(ICONS_ + "icon_steam");
        ICON_TUTORIAL = map.getAtlasSprite(ICONS_ + "icon_tutorial");

        ICON_INPUT = map.getAtlasSprite(ICONS_ + "icon_input");
        ICON_OUTPUT = map.getAtlasSprite(ICONS_ + "icon_output");

        ICON_REDSTONE_OFF = map.getAtlasSprite(ICONS_ + "icon_redstone_off");
        ICON_REDSTONE_ON = map.getAtlasSprite(ICONS_ + "icon_redstone_on");

        ICON_RS_TORCH_OFF = map.getAtlasSprite(ICONS_ + "icon_rs_torch_off");
        ICON_RS_TORCH_ON = map.getAtlasSprite(ICONS_ + "icon_rs_torch_on");

        ICON_ARROW_DOWN = map.getAtlasSprite(ICONS_ + "icon_arrow_down");
        ICON_ARROW_DOWN_INACTIVE = map.getAtlasSprite(ICONS_ + "icon_arrow_down_inactive");

        ICON_ARROW_UP = map.getAtlasSprite(ICONS_ + "icon_arrow_up");
        ICON_ARROW_UP_INACTIVE = map.getAtlasSprite(ICONS_ + "icon_arrow_up_inactive");
    }

    // region ICONS
    private static final String ICONS_ = "cofh_core:gui/icons/";

    public static TextureAtlasSprite ICON_ACCESS_PUBLIC;
    public static TextureAtlasSprite ICON_ACCESS_PRIVATE;
    public static TextureAtlasSprite ICON_ACCESS_FRIENDS;
    public static TextureAtlasSprite ICON_ACCESS_TEAM;

    public static TextureAtlasSprite ICON_ACCEPT;
    public static TextureAtlasSprite ICON_ACCEPT_INACTIVE;
    public static TextureAtlasSprite ICON_AUGMENT;
    public static TextureAtlasSprite ICON_BUTTON;
    public static TextureAtlasSprite ICON_BUTTON_HIGHLIGHT;
    public static TextureAtlasSprite ICON_BUTTON_INACTIVE;
    public static TextureAtlasSprite ICON_CANCEL;
    public static TextureAtlasSprite ICON_CANCEL_INACTIVE;
    public static TextureAtlasSprite ICON_CONFIG;
    public static TextureAtlasSprite ICON_ENERGY;
    public static TextureAtlasSprite ICON_NOPE;
    public static TextureAtlasSprite ICON_INFORMATION;
    public static TextureAtlasSprite ICON_STEAM;
    public static TextureAtlasSprite ICON_TUTORIAL;

    public static TextureAtlasSprite ICON_INPUT;
    public static TextureAtlasSprite ICON_OUTPUT;

    public static TextureAtlasSprite ICON_REDSTONE_OFF;
    public static TextureAtlasSprite ICON_REDSTONE_ON;

    public static TextureAtlasSprite ICON_RS_TORCH_OFF;
    public static TextureAtlasSprite ICON_RS_TORCH_ON;

    public static TextureAtlasSprite ICON_ARROW_DOWN;
    public static TextureAtlasSprite ICON_ARROW_DOWN_INACTIVE;

    public static TextureAtlasSprite ICON_ARROW_UP;
    public static TextureAtlasSprite ICON_ARROW_UP_INACTIVE;
    // endregion
}
