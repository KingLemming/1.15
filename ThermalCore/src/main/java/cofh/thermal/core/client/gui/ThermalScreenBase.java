package cofh.thermal.core.client.gui;

import cofh.core.client.gui.ContainerScreenCoFH;
import cofh.core.client.gui.element.panel.PanelAugmentation;
import cofh.core.client.gui.element.panel.PanelInfo;
import cofh.core.client.gui.element.panel.PanelRedstoneControl;
import cofh.core.client.gui.element.panel.PanelSecurity;
import cofh.lib.inventory.container.ContainerCoFH;
import cofh.lib.util.helpers.SecurityHelper;
import cofh.thermal.core.tileentity.ThermalTileBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class ThermalScreenBase<T extends ContainerCoFH> extends ContainerScreenCoFH<T> {

    protected ThermalTileBase tile;

    public ThermalScreenBase(T container, PlayerInventory inv, ThermalTileBase tile, ITextComponent titleIn) {

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

        if (container.getAugmentSlots().size() > 0) {
            addPanel(new PanelAugmentation(this, container::getNumAugmentSlots, container.getAugmentSlots()));
        }
        addPanel(new PanelRedstoneControl(this, tile));
    }

}
