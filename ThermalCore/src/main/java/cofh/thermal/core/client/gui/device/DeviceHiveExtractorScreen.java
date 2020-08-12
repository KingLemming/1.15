package cofh.thermal.core.client.gui.device;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalScreenBase;
import cofh.thermal.core.inventory.container.device.DeviceHiveExtractorContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class DeviceHiveExtractorScreen extends ThermalScreenBase<DeviceHiveExtractorContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/devices/hive_extractor.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public DeviceHiveExtractorScreen(DeviceHiveExtractorContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.device_hive_extractor"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.device_hive_extractor");
    }

    @Override
    public void init() {

        super.init();

        addElement(setClearable(createMediumFluidStorage(this, 116, 22, tile.getTank(0)), tile, 0));
    }

}
