package cofh.thermal.core.client.gui;

import cofh.core.client.gui.element.panel.PanelConfiguration;
import cofh.thermal.core.tileentity.MachineTileReconfigurable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.createDefaultEnergyStorage;
import static cofh.core.util.GuiHelper.setClearable;

public class MachineScreenReconfigurable<T extends Container> extends ThermalScreenBase<T> {

    protected MachineTileReconfigurable tile;

    public MachineScreenReconfigurable(T container, PlayerInventory inv, MachineTileReconfigurable tile, ITextComponent titleIn) {

        super(container, inv, tile, titleIn);
        this.tile = tile;
    }

    @Override
    public void init() {

        super.init();

        addPanel(new PanelConfiguration(this, tile, tile, () -> tile.getFacing())
                .addConditionals(ThermalGuiHelper.createDefaultMachineConfigs(this, name, tile)));

        if (tile.getEnergyStorage().getMaxEnergyStored() > 0) {
            addElement(setClearable(createDefaultEnergyStorage(this, 8, 8, tile.getEnergyStorage()), tile, 0));
        }
    }

}
