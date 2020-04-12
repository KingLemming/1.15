package cofh.thermal.core.gui.client;

import cofh.core.gui.element.ElementScaled;
import cofh.core.util.GuiHelper;
import cofh.thermal.core.tileentity.DynamoTileBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.DURATION;

public class DynamoScreenBase<T extends Container> extends ThermalScreenBase<T> {

    protected DynamoTileBase tile;

    protected ElementScaled duration;

    public DynamoScreenBase(T container, PlayerInventory inv, DynamoTileBase tile, ITextComponent titleIn) {

        super(container, inv, tile, titleIn);
        this.tile = tile;
    }

    @Override
    public void init() {

        super.init();

        if (tile.getEnergyStorage().getMaxEnergyStored() > 0) {
            addElement(GuiHelper.createDefaultEnergyStorage(this, 80, 18, tile.getEnergyStorage()));
        }
    }

    @Override
    protected void updateElementInformation() {

        super.updateElementInformation();

        if (duration != null) {
            duration.setQuantity(tile.getScaledDuration(DURATION));
        }
    }

}
