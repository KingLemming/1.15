package cofh.thermal.core.client.gui;

import cofh.core.client.gui.element.ElementScaled;
import cofh.core.client.gui.element.ElementScaledFluid;
import cofh.core.client.gui.element.panel.PanelConfiguration;
import cofh.core.util.GuiHelper;
import cofh.thermal.core.tileentity.MachineTileReconfigurable;
import cofh.thermal.core.util.ThermalGuiHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.PROGRESS;
import static cofh.core.util.GuiHelper.SPEED;

public class MachineScreenBase<T extends Container> extends ThermalScreenBase<T> {

    protected MachineTileReconfigurable tile;

    protected ElementScaledFluid progressOverlay;
    protected ElementScaled progress;
    protected ElementScaled speed;

    public MachineScreenBase(T container, PlayerInventory inv, MachineTileReconfigurable tile, ITextComponent titleIn) {

        super(container, inv, tile, titleIn);
        this.tile = tile;
    }

    @Override
    public void init() {

        super.init();

        addPanel(new PanelConfiguration(this, tile, tile, () -> tile.getFacing())
                .addConditionals(ThermalGuiHelper.createDefaultMachineConfigs(this, name, tile)));

        if (tile.getEnergyStorage().getMaxEnergyStored() > 0) {
            addElement(GuiHelper.createDefaultEnergyStorage(this, 8, 8, tile.getEnergyStorage()));
        }
    }

    @Override
    protected void updateElementInformation() {

        super.updateElementInformation();

        if (progress != null) {
            progress.setQuantity(tile.getScaledProgress(PROGRESS));
        }
        if (progressOverlay != null) {
            progressOverlay.setQuantity(tile.getScaledProgress(PROGRESS));
            progressOverlay.setFluid(tile.getRenderFluid());
        }
        if (speed != null) {
            speed.setQuantity(tile.getScaledSpeed(SPEED));
        }
    }

}
