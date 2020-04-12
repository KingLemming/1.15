package cofh.thermal.cultivation.client.gui;

import cofh.core.util.GuiHelper;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalScreenBase;
import cofh.thermal.cultivation.inventory.container.MachineHiveExtractorContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.generateTabInfo;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineHiveExtractorScreen extends ThermalScreenBase<MachineHiveExtractorContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machine/hive_extractor.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineHiveExtractorScreen(MachineHiveExtractorContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_hive_extractor"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.machine_hive_extractor");
    }

    @Override
    public void init() {

        super.init();

        addElement(GuiHelper.createMediumFluidStorage(this, 116, 22, tile.getTank(0)));
    }

}
