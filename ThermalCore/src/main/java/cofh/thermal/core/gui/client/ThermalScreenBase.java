package cofh.thermal.core.gui.client;

import cofh.core.gui.client.ContainerScreenCoFH;
import cofh.core.gui.element.panel.PanelInfo;
import cofh.core.gui.element.panel.PanelRedstoneControl;
import cofh.core.gui.element.panel.PanelSecurity;
import cofh.lib.util.helpers.SecurityHelper;
import cofh.thermal.core.tileentity.ThermalTileBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

public class ThermalScreenBase<T extends Container> extends ContainerScreenCoFH<T> {

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
        addPanel(new PanelRedstoneControl(this, tile));
    }

}
