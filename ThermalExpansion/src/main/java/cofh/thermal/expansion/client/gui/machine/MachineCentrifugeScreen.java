package cofh.thermal.expansion.client.gui.machine;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.MachineScreenBase;
import cofh.thermal.expansion.inventory.container.machine.MachineCentrifugeContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineCentrifugeScreen extends MachineScreenBase<MachineCentrifugeContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machine/centrifuge.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineCentrifugeScreen(MachineCentrifugeContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_centrifuge"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.machine_centrifuge");
        name = "centrifuge";
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 44, 26, tile));

        addElement(createOutputSlot(this, 107, 26, tile));
        addElement(createOutputSlot(this, 125, 26, tile));
        addElement(createOutputSlot(this, 107, 44, tile));
        addElement(createOutputSlot(this, 125, 44, tile));

        addElement(createMediumOutputFluidStorage(this, 151, 22, tile.getTank(0), tile));

        progressOverlay = addElement(createDefaultFluidProgress(this, 72, 34, PROG_ARROW_FLUID_RIGHT, tile.getRenderFluid(), () -> !tile.getRenderFluid().isEmpty()));
        progress = addElement(createDefaultProgress(this, 72, 34, PROG_ARROW_RIGHT, () -> tile.getRenderFluid().isEmpty()));
        speed = addElement(createDefaultSpeed(this, 44, 44, SCALE_SPIN));
    }

}
