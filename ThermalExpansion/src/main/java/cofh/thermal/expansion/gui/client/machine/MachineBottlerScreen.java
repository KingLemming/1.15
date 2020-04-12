package cofh.thermal.expansion.gui.client.machine;

import cofh.core.gui.element.ElementScaled;
import cofh.core.gui.element.ElementScaled.StartDirection;
import cofh.core.gui.element.ElementScaledFluid;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.gui.client.MachineScreenBase;
import cofh.thermal.expansion.inventory.container.machine.MachineBottlerContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineBottlerScreen extends MachineScreenBase<MachineBottlerContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machine/bottler.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineBottlerScreen(MachineBottlerContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_bottler"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.machine_bottler");
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 62, 26, tile));

        addElement(createLargeOutputSlot(this, 125, 35, tile));

        addElement(createMediumInputFluidStorage(this, 34, 22, tile.getTank(0), tile));

        progressOverlay = (ElementScaledFluid) addElement(new ElementScaledFluid(this, 88, 34).setFluid(tile.getRenderFluid()).setDirection(StartDirection.LEFT).setSize(PROGRESS, 16).setTexture(PROG_ARROW_FLUID_RIGHT, 64, 16).setVisible(() -> !tile.getRenderFluid().isEmpty()));
        progress = (ElementScaled) addElement(new ElementScaled(this, 88, 34).setDirection(StartDirection.LEFT).setSize(PROGRESS, 16).setTexture(PROG_ARROW_RIGHT, 64, 16).setVisible(() -> tile.getRenderFluid().isEmpty()));
        speed = (ElementScaled) addElement(new ElementScaled(this, 62, 44).setSize(16, SPEED).setTexture(SCALE_BUBBLE, 32, 16));
    }

}
