package cofh.thermal.expansion.gui.client.dynamo;

import cofh.core.gui.element.ElementScaled;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.gui.client.DynamoScreenBase;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoLapidaryContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class DynamoLapidaryScreen extends DynamoScreenBase<DynamoLapidaryContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/dynamo/lapidary.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public DynamoLapidaryScreen(DynamoLapidaryContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.dynamo_lapidary"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.dynamo_lapidary");
    }

    @Override
    public void init() {

        super.init();

        duration = (ElementScaled) addElement(new ElementScaled(this, 115, 35).setSize(16, SPEED).setTexture(SCALE_FLAME_GREEN, 32, 16));
    }

}
