package cofh.thermal.expansion.client.gui.dynamo;

import cofh.core.client.gui.element.ElementScaled;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.DynamoScreenBase;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoNumismaticContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class DynamoNumismaticScreen extends DynamoScreenBase<DynamoNumismaticContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/dynamo/numismatic.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public DynamoNumismaticScreen(DynamoNumismaticContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.dynamo_numismatic"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.dynamo_numismatic");
    }

    @Override
    public void init() {

        super.init();

        duration = (ElementScaled) addElement(new ElementScaled(this, 115, 35).setSize(16, SPEED).setTexture(SCALE_FLAME_GREEN, 32, 16));
    }

}
