package cofh.thermal.expansion.client.gui.dynamo;

import cofh.core.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.DynamoScreenBase;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoNumismaticContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.core.util.constants.Constants.ID_THERMAL;

public class DynamoNumismaticScreen extends DynamoScreenBase<DynamoNumismaticContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/dynamos/numismatic.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public DynamoNumismaticScreen(DynamoNumismaticContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.dynamo_numismatic"));
        texture = TEXTURE;
        info = appendLine(generatePanelInfo("info.thermal.dynamo_numismatic"), "info.thermal.dynamo.throttle");
    }

    @Override
    public void init() {

        super.init();

        addElement(ThermalGuiHelper.createDefaultDuration(this, 80, 35, SCALE_FLAME_GREEN, tile));
    }

}
