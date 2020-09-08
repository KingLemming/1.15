package cofh.thermal.core.client.gui;

import cofh.core.client.gui.element.ElementEnergyStorage;
import cofh.core.inventory.container.ContainerCoFH;
import cofh.thermal.core.tileentity.DynamoTileBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

import static cofh.core.util.GuiHelper.setClearable;
import static cofh.core.util.constants.Constants.PATH_ELEMENTS;
import static cofh.core.util.helpers.StringHelper.DF0;

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

        if (tile.getEnergyStorage().getMaxEnergyStored() > 0) {
            ElementEnergyStorage throttle = (ElementEnergyStorage) new ElementEnergyStorage(this, 125, 22, tile.getEnergyStorage()) {

                @Override
                public void addTooltip(List<ITextComponent> tooltipList, int mouseX, int mouseY) {

                    tooltipList.add(new TranslationTextComponent("info.cofh.output")
                            .appendSibling(new StringTextComponent(": " + DF0.format(100 * (double) tile.getCurSpeed() / tile.getMaxSpeed()) + "%")));

                    //                    if (infinite) {
                    //                        tooltipList.add(new TranslationTextComponent("info.cofh.infinite"));
                    //                    } else {
                    //                        tooltipList.add(new StringTextComponent(format(storage.getStored()) + " / " + format(storage.getCapacity()) + " " + storage.getUnit()));
                    //                    }
                    //        if (advancedTooltips.getAsBoolean()) {
                    //            tooltipList.add(new TranslationTextComponent("info.cofh.clear_storage"));
                    //        }
                }
            }
                    .setSize(16, 42)
                    .setTexture(PATH_ELEMENTS + "storage_energy.png", 32, 64);
            addElement(setClearable(throttle, tile, 0));
        }
    }

}
