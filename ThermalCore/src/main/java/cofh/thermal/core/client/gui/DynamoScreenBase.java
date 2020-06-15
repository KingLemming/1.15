package cofh.thermal.core.client.gui;

import cofh.thermal.core.tileentity.DynamoTileBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.createDefaultEnergyStorage;
import static cofh.core.util.GuiHelper.setClearable;

public class DynamoScreenBase<T extends Container> extends ThermalScreenBase<T> {

    protected DynamoTileBase tile;

    public DynamoScreenBase(T container, PlayerInventory inv, DynamoTileBase tile, ITextComponent titleIn) {

        super(container, inv, tile, titleIn);
        this.tile = tile;
    }

    @Override
    public void init() {

        super.init();

        if (tile.getEnergyStorage().getMaxEnergyStored() > 0) {
            addElement(setClearable(createDefaultEnergyStorage(this, 80, 18, tile.getEnergyStorage()), tile, 0));
        }
    }

}
