package cofh.thermal.expansion.client.gui.dynamo;

import cofh.core.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.DynamoScreenBase;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoStirlingContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.SCALE_FLAME;
import static cofh.core.util.GuiHelper.generateTabInfo;
import static cofh.core.util.constants.Constants.ID_THERMAL;

public class DynamoStirlingScreen extends DynamoScreenBase<DynamoStirlingContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/dynamos/stirling.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public DynamoStirlingScreen(DynamoStirlingContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.dynamo_stirling"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.dynamo_stirling");
    }

    @Override
    public void init() {

        super.init();

        addElement(ThermalGuiHelper.createDefaultDuration(this, 80, 35, SCALE_FLAME, tile));
    }

}
