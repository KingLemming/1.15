package cofh.thermal.expansion.gui.client;

import cofh.core.gui.element.ElementScaled;
import cofh.core.gui.element.ElementScaled.StartDirection;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.gui.client.MachineScreenBase;
import cofh.thermal.expansion.inventory.container.MachinePulverizerContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachinePulverizerScreen extends MachineScreenBase<MachinePulverizerContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machine/pulverizer.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachinePulverizerScreen(MachinePulverizerContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_pulverizer"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.machine_pulverizer");
    }

    @Override
    public void init() {

        super.init();

        progress = (ElementScaled) addElement(new ElementScaled(this, 72, 25).setDirection(StartDirection.LEFT).setSize(PROGRESS, 16).setTexture(PROG_ARROW_RIGHT, 64, 16));
        speed = (ElementScaled) addElement(new ElementScaled(this, 44, 35).setSize(16, SPEED).setTexture(SCALE_CRUSH, 32, 16));
    }

}
