package cofh.thermal.core.client.gui;

import cofh.core.client.gui.IGuiAccess;
import cofh.core.client.gui.element.ElementConditionalLayered;
import cofh.core.client.gui.element.ElementScaled;
import cofh.core.client.gui.element.ElementScaledFluid;
import cofh.core.util.GuiHelper;
import cofh.lib.util.helpers.BlockHelper;
import cofh.thermal.core.tileentity.MachineTileReconfigurable;
import cofh.thermal.core.tileentity.ThermalTileBase;
import net.minecraft.util.ResourceLocation;

import static cofh.core.client.gui.element.ElementBase.TRUE;
import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.lib.util.control.IReconfigurable.SideConfig.*;
import static cofh.lib.util.helpers.RenderHelper.textureExists;
import static cofh.thermal.core.client.gui.ThermalTextures.*;
import static net.minecraft.util.Direction.DOWN;
import static net.minecraft.util.Direction.UP;

public class ThermalGuiHelper {

    private ThermalGuiHelper() {

    }

    // region MACHINE CONFIG
    public static ElementConditionalLayered createDefaultMachineConfigTop(IGuiAccess gui, String machine, MachineTileReconfigurable tile) {

        String specificTexture = ID_THERMAL + ":block/machines/machine_" + machine + "_top";
        return (ElementConditionalLayered) new ElementConditionalLayered(gui)
                .addSprite(textureExists(new ResourceLocation(specificTexture)) ? specificTexture : "thermal:block/machines/machine_top", TRUE)
                .addSprite(MACHINE_CONFIG_INPUT, () -> tile.reconfigControl().getSideConfig(UP) == SIDE_INPUT)
                .addSprite(MACHINE_CONFIG_OUTPUT, () -> tile.reconfigControl().getSideConfig(UP) == SIDE_OUTPUT)
                .addSprite(MACHINE_CONFIG_BOTH, () -> tile.reconfigControl().getSideConfig(UP) == SIDE_BOTH)
                .addSprite(MACHINE_CONFIG_ACCESSIBLE, () -> tile.reconfigControl().getSideConfig(UP) == SIDE_ACCESSIBLE)
                .setSize(16, 16);
    }

    public static ElementConditionalLayered createDefaultMachineConfigBottom(IGuiAccess gui, String machine, MachineTileReconfigurable tile) {

        String specificTexture = ID_THERMAL + ":block/machines/machine_" + machine + "_bottom";
        return (ElementConditionalLayered) new ElementConditionalLayered(gui)
                .addSprite(textureExists(new ResourceLocation(specificTexture)) ? specificTexture : "thermal:block/machines/machine_bottom", TRUE)
                .addSprite(MACHINE_CONFIG_INPUT, () -> tile.reconfigControl().getSideConfig(DOWN) == SIDE_INPUT)
                .addSprite(MACHINE_CONFIG_OUTPUT, () -> tile.reconfigControl().getSideConfig(DOWN) == SIDE_OUTPUT)
                .addSprite(MACHINE_CONFIG_BOTH, () -> tile.reconfigControl().getSideConfig(DOWN) == SIDE_BOTH)
                .addSprite(MACHINE_CONFIG_ACCESSIBLE, () -> tile.reconfigControl().getSideConfig(DOWN) == SIDE_ACCESSIBLE)
                .setSize(16, 16);
    }

    public static ElementConditionalLayered createDefaultMachineConfigLeft(IGuiAccess gui, String machine, MachineTileReconfigurable tile) {

        String specificTexture = ID_THERMAL + ":block/machines/machine_" + machine + "_side";
        return (ElementConditionalLayered) new ElementConditionalLayered(gui)
                .addSprite(textureExists(new ResourceLocation(specificTexture)) ? specificTexture : "thermal:block/machines/machine_side", TRUE)
                .addSprite(MACHINE_CONFIG_INPUT, () -> tile.reconfigControl().getSideConfig(BlockHelper.left(tile.getFacing())) == SIDE_INPUT)
                .addSprite(MACHINE_CONFIG_OUTPUT, () -> tile.reconfigControl().getSideConfig(BlockHelper.left(tile.getFacing())) == SIDE_OUTPUT)
                .addSprite(MACHINE_CONFIG_BOTH, () -> tile.reconfigControl().getSideConfig(BlockHelper.left(tile.getFacing())) == SIDE_BOTH)
                .addSprite(MACHINE_CONFIG_ACCESSIBLE, () -> tile.reconfigControl().getSideConfig(BlockHelper.left(tile.getFacing())) == SIDE_ACCESSIBLE)
                .setSize(16, 16);
    }

    public static ElementConditionalLayered createDefaultMachineConfigRight(IGuiAccess gui, String machine, MachineTileReconfigurable tile) {

        String specificTexture = ID_THERMAL + ":block/machines/machine_" + machine + "_side";
        return (ElementConditionalLayered) new ElementConditionalLayered(gui)
                .addSprite(textureExists(new ResourceLocation(specificTexture)) ? specificTexture : "thermal:block/machines/machine_side", TRUE)
                .addSprite(MACHINE_CONFIG_INPUT, () -> tile.reconfigControl().getSideConfig(BlockHelper.right(tile.getFacing())) == SIDE_INPUT)
                .addSprite(MACHINE_CONFIG_OUTPUT, () -> tile.reconfigControl().getSideConfig(BlockHelper.right(tile.getFacing())) == SIDE_OUTPUT)
                .addSprite(MACHINE_CONFIG_BOTH, () -> tile.reconfigControl().getSideConfig(BlockHelper.right(tile.getFacing())) == SIDE_BOTH)
                .addSprite(MACHINE_CONFIG_ACCESSIBLE, () -> tile.reconfigControl().getSideConfig(BlockHelper.right(tile.getFacing())) == SIDE_ACCESSIBLE)
                .setSize(16, 16);
    }

    public static ElementConditionalLayered createDefaultMachineConfigFace(IGuiAccess gui, String machine, MachineTileReconfigurable tile) {

        String texture = ID_THERMAL + ":block/machines/machine_" + machine;
        String activeTexture = ID_THERMAL + ":block/machines/machine_" + machine + "_active";
        return (ElementConditionalLayered) new ElementConditionalLayered(gui)
                .addSprite(textureExists(new ResourceLocation(texture)) ? texture : "thermal:block/machines/machine_side", () -> !tile.isActive)
                .addSprite(textureExists(new ResourceLocation(activeTexture)) ? activeTexture : "thermal:block/machines/machine_side", () -> tile.isActive)
                .setSize(16, 16);
    }

    public static ElementConditionalLayered createDefaultMachineConfigBack(IGuiAccess gui, String machine, MachineTileReconfigurable tile) {

        String specificTexture = ID_THERMAL + ":block/machines/machine_" + machine + "_side";
        return (ElementConditionalLayered) new ElementConditionalLayered(gui)
                .addSprite(textureExists(new ResourceLocation(specificTexture)) ? specificTexture : "thermal:block/machines/machine_side", TRUE)
                .addSprite(MACHINE_CONFIG_INPUT, () -> tile.reconfigControl().getSideConfig(BlockHelper.opposite(tile.getFacing())) == SIDE_INPUT)
                .addSprite(MACHINE_CONFIG_OUTPUT, () -> tile.reconfigControl().getSideConfig(BlockHelper.opposite(tile.getFacing())) == SIDE_OUTPUT)
                .addSprite(MACHINE_CONFIG_BOTH, () -> tile.reconfigControl().getSideConfig(BlockHelper.opposite(tile.getFacing())) == SIDE_BOTH)
                .addSprite(MACHINE_CONFIG_ACCESSIBLE, () -> tile.reconfigControl().getSideConfig(BlockHelper.opposite(tile.getFacing())) == SIDE_ACCESSIBLE)
                .setSize(16, 16);
    }

    public static ElementConditionalLayered[] createDefaultMachineConfigs(IGuiAccess gui, String machine, MachineTileReconfigurable tile) {

        return new ElementConditionalLayered[]{
                createDefaultMachineConfigTop(gui, machine, tile),
                createDefaultMachineConfigLeft(gui, machine, tile),
                createDefaultMachineConfigFace(gui, machine, tile),
                createDefaultMachineConfigRight(gui, machine, tile),
                createDefaultMachineConfigBottom(gui, machine, tile),
                createDefaultMachineConfigBack(gui, machine, tile)
        };
    }
    // endregion

    // region COMMON UX
    public static ElementScaled createDefaultProgress(IGuiAccess gui, int posX, int posY, String texture, ThermalTileBase tile) {

        return GuiHelper.createDefaultProgress(gui, posX, posY, texture, tile::getScaledProgress, () -> tile.getRenderFluid().isEmpty());
    }

    public static ElementScaledFluid createDefaultFluidProgress(IGuiAccess gui, int posX, int posY, String texture, ThermalTileBase tile) {

        return GuiHelper.createDefaultFluidProgress(gui, posX, posY, texture, tile::getScaledProgress, tile::getRenderFluid, () -> !tile.getRenderFluid().isEmpty());
    }

    public static ElementScaled createDefaultSpeed(IGuiAccess gui, int posX, int posY, String texture, ThermalTileBase tile) {

        return GuiHelper.createDefaultSpeed(gui, posX, posY, texture, tile::getScaledSpeed);
    }

    public static ElementScaled createDefaultDuration(IGuiAccess gui, int posX, int posY, String texture, ThermalTileBase tile) {

        return GuiHelper.createDefaultDuration(gui, posX, posY, texture, tile::getScaledDuration);
    }
    // endregion
}
