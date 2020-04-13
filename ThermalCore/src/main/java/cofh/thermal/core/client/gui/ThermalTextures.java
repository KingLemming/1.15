package cofh.thermal.core.client.gui;

import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class ThermalTextures {

    private ThermalTextures() {

    }

    private static String BLOCK_ATLAS = "minecraft:textures/atlas/blocks.png";

    public static void preStitch(TextureStitchEvent.Pre event) {

        if (!event.getMap().getTextureLocation().toString().equals(BLOCK_ATLAS)) {
            return;
        }
        event.addSprite(new ResourceLocation(CONFIG_ + "machine_config_input"));
        event.addSprite(new ResourceLocation(CONFIG_ + "machine_config_output"));
        event.addSprite(new ResourceLocation(CONFIG_ + "machine_config_both"));
        event.addSprite(new ResourceLocation(CONFIG_ + "machine_config_accessible"));
    }

    public static void postStitch(TextureStitchEvent.Post event) {

        if (!event.getMap().getTextureLocation().toString().equals(BLOCK_ATLAS)) {
            return;
        }
        AtlasTexture map = event.getMap();

        MACHINE_CONFIG_INPUT = map.getSprite(new ResourceLocation(CONFIG_ + "machine_config_input"));
        MACHINE_CONFIG_OUTPUT = map.getSprite(new ResourceLocation(CONFIG_ + "machine_config_output"));
        MACHINE_CONFIG_BOTH = map.getSprite(new ResourceLocation(CONFIG_ + "machine_config_both"));
        MACHINE_CONFIG_ACCESSIBLE = map.getSprite(new ResourceLocation(CONFIG_ + "machine_config_accessible"));
    }

    // region CONFIG
    private static final String CONFIG_ = ID_THERMAL + ":block/config/";

    public static TextureAtlasSprite MACHINE_CONFIG_INPUT;
    public static TextureAtlasSprite MACHINE_CONFIG_OUTPUT;
    public static TextureAtlasSprite MACHINE_CONFIG_BOTH;
    public static TextureAtlasSprite MACHINE_CONFIG_ACCESSIBLE;
    // endregion
}
