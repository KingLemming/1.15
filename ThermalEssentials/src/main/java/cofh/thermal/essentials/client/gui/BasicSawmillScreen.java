package cofh.thermal.essentials.client.gui;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.MachineScreenBasic;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.essentials.inventory.container.BasicSawmillContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class BasicSawmillScreen extends MachineScreenBasic<BasicSawmillContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machine/basic_sawmill.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public BasicSawmillScreen(BasicSawmillContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.basic_sawmill"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.basic_sawmill");
        name = "basic_sawmill";
    }

    @Override
    public void init() {

        super.init();

        addElement(ThermalGuiHelper.createDefaultProgress(this, 72, 34, PROG_ARROW_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 44, 44, SCALE_SAW, tile));
    }

}
