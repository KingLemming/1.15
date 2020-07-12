package cofh.thermal.expansion.client.gui.device;

import cofh.core.client.gui.element.panel.PanelConfiguration;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.core.client.gui.ThermalScreenBase;
import cofh.thermal.expansion.inventory.container.device.DeviceItemBufferContainer;
import cofh.thermal.expansion.tileentity.device.DeviceItemBufferTile;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.generateTabInfo;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class DeviceItemBufferScreen extends ThermalScreenBase<DeviceItemBufferContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/devices/item_buffer.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    protected DeviceItemBufferTile tile;

    public DeviceItemBufferScreen(DeviceItemBufferContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.device_item_buffer"));
        tile = container.tile;
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.device_item_buffer");
    }

    @Override
    public void init() {

        super.init();

        addPanel(new PanelConfiguration(this, tile, tile, () -> tile.getFacing())
                .addConditionals(ThermalGuiHelper.createDefaultMachineConfigs(this, name, tile)));
    }

}
