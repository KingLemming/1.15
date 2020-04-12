package cofh.thermal.expansion.gui.client.dynamo;

import cofh.core.gui.element.ElementScaled;
import cofh.core.util.GuiHelper;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.gui.client.DynamoScreenBase;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoMagmaticContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class DynamoMagmaticScreen extends DynamoScreenBase<DynamoMagmaticContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/dynamo/magmatic.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public DynamoMagmaticScreen(DynamoMagmaticContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.dynamo_magmatic"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.dynamo_magmatic");
    }

    @Override
    public void init() {

        super.init();

        addElement(GuiHelper.createMediumFluidStorage(this, 34, 22, tile.getTank(0)));
        duration = (ElementScaled) addElement(new ElementScaled(this, 115, 35).setSize(16, SPEED).setTexture(SCALE_FLAME_GREEN, 32, 16));
    }

}
