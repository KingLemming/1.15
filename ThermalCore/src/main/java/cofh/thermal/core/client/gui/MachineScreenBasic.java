package cofh.thermal.core.client.gui;

import cofh.thermal.core.tileentity.MachineTileBasic;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

public class MachineScreenBasic<T extends Container> extends ThermalScreenBase<T> {

    protected MachineTileBasic tile;

    public MachineScreenBasic(T container, PlayerInventory inv, MachineTileBasic tile, ITextComponent titleIn) {

        super(container, inv, tile, titleIn);
        this.tile = tile;
    }

}
