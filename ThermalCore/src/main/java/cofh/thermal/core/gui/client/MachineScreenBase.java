package cofh.thermal.core.gui.client;

import cofh.core.gui.client.ContainerScreenCoFH;
import cofh.core.gui.element.ElementScaled;
import cofh.core.gui.element.ElementScaledFluid;
import cofh.core.gui.element.panel.PanelInfo;
import cofh.core.gui.element.panel.PanelRedstoneControl;
import cofh.core.gui.element.panel.PanelSecurity;
import cofh.core.util.GuiHelper;
import cofh.lib.util.helpers.SecurityHelper;
import cofh.thermal.core.tileentity.MachineTileBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.PROGRESS;
import static cofh.core.util.GuiHelper.SPEED;

public class MachineScreenBase<T extends Container> extends ContainerScreenCoFH<T> {

    protected MachineTileBase tile;

    protected ElementScaledFluid progressOverlay;
    protected ElementScaled progress;
    protected ElementScaled speed;

    public MachineScreenBase(T container, PlayerInventory inv, MachineTileBase tile, ITextComponent titleIn) {

        super(container, inv, titleIn);
        this.tile = tile;
    }

    @Override
    public void init() {

        super.init();

        if (info != null && !info.isEmpty()) {
            addPanel(new PanelInfo(this, info));
        }
        addPanel(new PanelSecurity(this, tile, SecurityHelper.getID(player)));
        addPanel(new PanelRedstoneControl(this, tile));
        // addPanel(new PanelConfiguration(this, tile, tile));

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
