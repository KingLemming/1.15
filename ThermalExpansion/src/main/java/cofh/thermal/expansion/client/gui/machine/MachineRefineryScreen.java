package cofh.thermal.expansion.client.gui.machine;

import cofh.core.util.GuiHelper;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.MachineScreenBase;
import cofh.thermal.expansion.inventory.container.machine.MachineRefineryContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineRefineryScreen extends MachineScreenBase<MachineRefineryContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machine/refinery.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineRefineryScreen(MachineRefineryContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_refinery"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.machine_refinery");
        name = "refinery";
    }

    @Override
    public void init() {

        super.init();

        addElement(createLargeOutputSlot(this, 107, 35, tile));

        addElement(GuiHelper.createSmallInputFluidStorage(this, 34, 17, tile.getTank(0), tile));

        addElement(GuiHelper.createMediumOutputFluidStorage(this, 133, 22, tile.getTank(1), tile));
        addElement(GuiHelper.createMediumOutputFluidStorage(this, 151, 22, tile.getTank(2), tile));

        progressOverlay = addElement(createDefaultFluidProgress(this, 65, 35, PROG_DROP_RIGHT, tile.getRenderFluid()));
        speed = addElement(createDefaultSpeed(this, 35, 53, SCALE_FLAME));
    }

}
