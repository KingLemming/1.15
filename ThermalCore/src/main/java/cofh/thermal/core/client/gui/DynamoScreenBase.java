package cofh.thermal.core.client.gui;

import cofh.lib.inventory.container.ContainerCoFH;
import cofh.thermal.core.tileentity.DynamoTileBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class DynamoScreenBase<T extends ContainerCoFH> extends ThermalScreenBase<T> {

    protected DynamoTileBase tile;

    public DynamoScreenBase(T container, PlayerInventory inv, DynamoTileBase tile, ITextComponent titleIn) {

        super(container, inv, tile, titleIn);
        this.tile = tile;
    }

    @Override
    public void init() {

        super.init();

        addPanel(ThermalGuiHelper.createDefaultEnergyProducerPanel(this, tile));

        //        if (tile.getEnergyStorage().getMaxEnergyStored() > 0) {
        //            addElement(setClearable(createDefaultEnergyStorage(this, 125, 22, tile.getEnergyStorage()), tile, 0));
        //        }
    }

}
