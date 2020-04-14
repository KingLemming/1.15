package cofh.thermal.expansion.client.gui.machine;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.MachineScreenBase;
import cofh.thermal.expansion.inventory.container.machine.MachineBrewerContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineBrewerScreen extends MachineScreenBase<MachineBrewerContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machine/brewer.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineBrewerScreen(MachineBrewerContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_brewer"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.machine_brewer");
        name = "brewer";
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 80, 34, tile));

        addElement(createMediumInputFluidStorage(this, 34, 22, tile.getTank(0), tile));

        addElement(createLargeOutputFluidStorage(this, 151, 8, tile.getTank(1), tile));

        progressOverlay = addElement(createDefaultFluidProgress(this, 112, 34, PROG_DROP_RIGHT, tile.getRenderFluid()));
        speed = addElement(createDefaultSpeed(this, 57, 34, SCALE_ALCHEMY));
    }

}
