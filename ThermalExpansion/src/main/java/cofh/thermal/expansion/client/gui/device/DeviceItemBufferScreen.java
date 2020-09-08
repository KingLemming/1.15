package cofh.thermal.expansion.client.gui.device;

import cofh.core.client.gui.element.ElementBase;
import cofh.core.client.gui.element.ElementButton;
import cofh.core.client.gui.element.ElementTexture;
import cofh.core.client.gui.element.panel.PanelConfiguration;
import cofh.core.network.packet.server.TileConfigPacket;
import cofh.core.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.core.client.gui.ThermalScreenBase;
import cofh.thermal.expansion.inventory.container.device.DeviceItemBufferContainer;
import cofh.thermal.expansion.tileentity.device.DeviceItemBufferTile;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.Collections;

import static cofh.core.util.GuiHelper.*;
import static cofh.core.util.constants.Constants.ID_COFH_CORE;
import static cofh.core.util.constants.Constants.ID_THERMAL;
import static cofh.core.util.helpers.SoundHelper.playClickSound;
import static cofh.core.util.helpers.StringHelper.format;
import static cofh.core.util.helpers.StringHelper.localize;

public class DeviceItemBufferScreen extends ThermalScreenBase<DeviceItemBufferContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/devices/item_buffer.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public static final String TEX_INCREMENT = ID_COFH_CORE + ":textures/gui/elements/button_increment.png";
    public static final String TEX_DECREMENT = ID_COFH_CORE + ":textures/gui/elements/button_decrement.png";

    protected DeviceItemBufferTile tile;

    public DeviceItemBufferScreen(DeviceItemBufferContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.device_item_buffer"));
        tile = container.tile;
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.device_item_buffer");
    }

    @Override
    public void init() {

        super.init();

        addPanel(new PanelConfiguration(this, tile, tile, () -> tile.getFacing())
                .addConditionals(ThermalGuiHelper.createDefaultMachineConfigs(this, name, tile)));

        addElement(new ElementTexture(this, 24, 16)
                .setSize(20, 20)
                .setTexture(INFO_INPUT, 20, 20));
        addElement(new ElementTexture(this, 132, 16)
                .setSize(20, 20)
                .setTexture(INFO_OUTPUT, 20, 20));

        addButtons();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        String input = format(tile.amountInput);
        String output = format(tile.amountOutput);

        getFontRenderer().drawString(input, getCenteredOffset(input, 34), 42, 0x404040);
        getFontRenderer().drawString(output, getCenteredOffset(output, 142), 42, 0x404040);

        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    // region ELEMENTS
    @Override
    public boolean handleElementButtonClick(String buttonName, int mouseButton) {

        int change;
        float pitch;

        if (hasShiftDown()) {
            change = 64;
            pitch = 0.9F;
            if (mouseButton == 1) {
                change = 32;
                pitch = 0.8F;
            }
        } else if (hasControlDown()) {
            change = 4;
            pitch = 0.5F;
            if (mouseButton == 1) {
                change = 1;
                pitch = 0.4F;
            }
        } else {
            change = 16;
            pitch = 0.7F;
            if (mouseButton == 1) {
                change = 8;
                pitch = 0.6F;
            }
        }
        int curInput = tile.amountInput;
        int curOutput = tile.amountOutput;

        switch (buttonName) {
            case "DecInput":
                tile.amountInput -= change;
                pitch -= 0.1F;
                break;
            case "IncInput":
                tile.amountInput += change;
                pitch += 0.1F;
                break;
            case "DecOutput":
                tile.amountOutput -= change;
                pitch -= 0.1F;
                break;
            case "IncOutput":
                tile.amountOutput += change;
                pitch += 0.1F;
                break;
        }
        playClickSound(pitch);
        TileConfigPacket.sendToServer(tile);

        tile.amountInput = curInput;
        tile.amountOutput = curOutput;
        return true;
    }

    protected void addButtons() {

        ElementBase decInput = new ElementButton(this, 19, 56)
                .setTooltipFactory((element, mouseX, mouseY) -> {

                    if (element.enabled()) {
                        int change = 16;
                        int change2 = 8;
                        if (hasShiftDown()) {
                            change = 64;
                            change2 = 32;
                        } else if (hasControlDown()) {
                            change = 4;
                            change2 = 1;
                        }
                        return Collections.singletonList(new StringTextComponent(
                                localize("info.cofh.decrease_by")
                                        + " " + format(change)
                                        + "/" + format(change2)));
                    }
                    return Collections.emptyList();
                })
                .setName("DecInput")
                .setSize(14, 14)
                .setTexture(TEX_DECREMENT, 42, 14)
                .setEnabled(() -> tile.amountInput > 0);

        ElementBase incInput = new ElementButton(this, 35, 56)
                .setTooltipFactory((element, mouseX, mouseY) -> {

                    if (element.enabled()) {
                        int change = 16;
                        int change2 = 8;
                        if (hasShiftDown()) {
                            change = 64;
                            change2 = 32;
                        } else if (hasControlDown()) {
                            change = 4;
                            change2 = 1;
                        }
                        return Collections.singletonList(new StringTextComponent(
                                localize("info.cofh.increase_by")
                                        + " " + format(change)
                                        + "/" + format(change2)));
                    }
                    return Collections.emptyList();
                })
                .setName("IncInput")
                .setSize(14, 14)
                .setTexture(TEX_INCREMENT, 42, 14)
                .setEnabled(() -> tile.amountInput < DeviceItemBufferTile.XFER_MAX);

        ElementBase decOutput = new ElementButton(this, 127, 56)
                .setTooltipFactory((element, mouseX, mouseY) -> {

                    if (element.enabled()) {
                        int change = 16;
                        int change2 = 8;
                        if (hasShiftDown()) {
                            change = 64;
                            change2 = 32;
                        } else if (hasControlDown()) {
                            change = 4;
                            change2 = 1;
                        }
                        return Collections.singletonList(new StringTextComponent(
                                localize("info.cofh.decrease_by")
                                        + " " + format(change)
                                        + "/" + format(change2)));
                    }
                    return Collections.emptyList();
                })
                .setName("DecOutput")
                .setSize(14, 14)
                .setTexture(TEX_DECREMENT, 42, 14)
                .setEnabled(() -> tile.amountOutput > 0);

        ElementBase incOutput = new ElementButton(this, 143, 56)
                .setTooltipFactory((element, mouseX, mouseY) -> {

                    if (element.enabled()) {
                        int change = 16;
                        int change2 = 8;
                        if (hasShiftDown()) {
                            change = 64;
                            change2 = 32;
                        } else if (hasControlDown()) {
                            change = 4;
                            change2 = 1;
                        }
                        return Collections.singletonList(new StringTextComponent(
                                localize("info.cofh.increase_by")
                                        + " " + format(change)
                                        + "/" + format(change2)));
                    }
                    return Collections.emptyList();
                })
                .setName("IncOutput")
                .setSize(14, 14)
                .setTexture(TEX_INCREMENT, 42, 14)
                .setEnabled(() -> tile.amountOutput < DeviceItemBufferTile.XFER_MAX);

        addElement(decInput);
        addElement(incInput);
        addElement(decOutput);
        addElement(incOutput);
    }
    // endregion
}
