package cofh.thermal.expansion.gui.client.machine;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.gui.client.MachineScreenBase;
import cofh.thermal.expansion.inventory.container.machine.MachineSawmillContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineSawmillScreen extends MachineScreenBase<MachineSawmillContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machine/sawmill.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineSawmillScreen(MachineSawmillContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_sawmill"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.machine_sawmill");
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 44, 26, tile));

        addElement(createOutputSlot(this, 107, 26, tile));
        addElement(createOutputSlot(this, 125, 26, tile));
        addElement(createOutputSlot(this, 107, 44, tile));
        addElement(createOutputSlot(this, 125, 44, tile));

        progress = addElement(createDefaultProgress(this, 72, 34, PROG_ARROW_RIGHT));
        speed = addElement(createDefaultSpeed(this, 44, 44, SCALE_SAW));
    }

}
