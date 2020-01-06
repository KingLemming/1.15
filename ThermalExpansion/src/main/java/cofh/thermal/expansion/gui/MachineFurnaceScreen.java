package cofh.thermal.expansion.gui;

import cofh.core.gui.element.ElementScaled;
import cofh.core.gui.element.ElementScaled.StartDirection;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.gui.MachineScreenBase;
import cofh.thermal.core.tileentity.MachineTileBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineFurnaceScreen<T extends Container> extends MachineScreenBase {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machine/furnace.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineFurnaceScreen(T screenContainer, PlayerInventory inv, MachineTileBase tile) {

        super(screenContainer, inv, tile, StringHelper.getTextComponent("tile.thermal.machine_furnace"));
        texture = TEXTURE;
        // TODO: Info
        // generateTabInfo("tab.thermal.machine_furnace")
    }

    @Override
    public void init() {

        super.init();

        progress = (ElementScaled) addElement(new ElementScaled(this, 79, 34).setDirection(StartDirection.LEFT).setSize(PROGRESS, 16).setTexture(PROG_ARROW_RIGHT, 64, 16));
        speed = (ElementScaled) addElement(new ElementScaled(this, 53, 44).setSize(16, SPEED).setTexture(SCALE_FLAME, 32, 16));
    }

}
