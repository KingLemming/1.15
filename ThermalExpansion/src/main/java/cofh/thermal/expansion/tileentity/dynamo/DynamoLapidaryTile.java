package cofh.thermal.expansion.tileentity.dynamo;

import cofh.core.inventory.ItemStorageCoFH;
import cofh.thermal.core.tileentity.DynamoTileBase;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoLapidaryContainer;
import cofh.thermal.expansion.util.managers.dynamo.LapidaryFuelManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cofh.core.util.StorageGroup.INPUT;
import static cofh.core.util.constants.Constants.ID_MINECRAFT;
import static cofh.thermal.core.common.ThermalConfig.dynamoAugments;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_LAPIDARY_TILE;

public class DynamoLapidaryTile extends DynamoTileBase {

    protected static final ResourceLocation UNDERLAY_TEXTURE = new ResourceLocation(ID_MINECRAFT, "block/nether_portal");

    protected ItemStorageCoFH fuelSlot = new ItemStorageCoFH(LapidaryFuelManager.instance()::validFuel);

    public DynamoLapidaryTile() {

        super(DYNAMO_LAPIDARY_TILE);

        inventory.addSlot(fuelSlot, INPUT);

        addAugmentSlots(dynamoAugments);
        initHandlers();
    }

    // region PROCESS
    @Override
    protected boolean canProcessStart() {

        return LapidaryFuelManager.instance().getEnergy(fuelSlot.getItemStack()) > 0;
    }

    @Override
    protected void processStart() {

        fuel += fuelMax = Math.round(LapidaryFuelManager.instance().getEnergy(fuelSlot.getItemStack()) * energyMod);
        fuelSlot.consume();
    }
    // endregion

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new DynamoLapidaryContainer(i, world, pos, inventory, player);
    }

    @Nonnull
    @Override
    public IModelData getModelData() {

        return new ModelDataMap.Builder()
                .withInitial(UNDERLAY, UNDERLAY_TEXTURE)
                .build();
    }

}
