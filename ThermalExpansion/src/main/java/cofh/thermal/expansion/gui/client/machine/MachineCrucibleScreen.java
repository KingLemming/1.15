package cofh.thermal.expansion.gui.client.machine;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.gui.client.MachineScreenBase;
import cofh.thermal.expansion.inventory.container.machine.MachineCrucibleContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineCrucibleScreen extends MachineScreenBase<MachineCrucibleContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machine/crucible.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineCrucibleScreen(MachineCrucibleContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_crucible"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.machine_crucible");
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 53, 26, tile));

        addElement(createLargeOutputFluidStorage(this, 151, 8, tile.getTank(0), tile));

        progressOverlay = addElement(createDefaultFluidProgress(this, 103, 34, PROG_DROP_RIGHT, tile.getRenderFluid()));
        speed = addElement(createDefaultSpeed(this, 53, 44, SCALE_FLAME));
    }

}
