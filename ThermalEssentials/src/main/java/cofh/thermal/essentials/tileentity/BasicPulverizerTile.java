package cofh.thermal.essentials.tileentity;

import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.thermal.core.tileentity.MachineTileBasicProcess;
import cofh.thermal.core.util.managers.machine.PulverizerRecipeManager;
import cofh.thermal.essentials.inventory.container.BasicPulverizerContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;

public class BasicPulverizerTile extends MachineTileBasicProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(PulverizerRecipeManager.instance()::validRecipe);
    protected ItemStorageCoFH fuelSlot = new ItemStorageCoFH(EnergyHelper::validFurnaceFuel);

    public BasicPulverizerTile(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(fuelSlot, CATALYST);
        inventory.addSlots(OUTPUT, 4);
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = PulverizerRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
        }
        return curRecipe != null;
    }

    @Override
    protected void resolveInputs() {

        // Input Items
        inputSlot.modify(-itemInputCounts.get(0));
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new BasicPulverizerContainer(i, world, pos, inventory, player);
    }

    // region OPTIMIZATION
    @Override
    protected boolean validateInputs() {

        if (!cacheRecipe()) {
            return false;
        }
        return inputSlot.getCount() >= itemInputCounts.get(0);
    }
    // endregion

}
