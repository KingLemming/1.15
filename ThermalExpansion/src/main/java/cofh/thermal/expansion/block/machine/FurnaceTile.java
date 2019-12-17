package cofh.thermal.expansion.block.machine;

import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.block.machine.MachineTileProcess;
import cofh.thermal.expansion.util.managers.machine.FurnaceRecipeManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.INPUT;
import static cofh.lib.util.StorageGroup.OUTPUT;
import static cofh.lib.util.helpers.ItemHelper.itemsIdentical;

public class FurnaceTile extends MachineTileProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(FurnaceRecipeManager.instance()::validRecipe);
    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();

    public FurnaceTile() {

        // TODO: Furnace Type
        super(null);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(outputSlot, OUTPUT);
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = FurnaceRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
        }
        return curRecipe != null;
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return null;
    }

    // region OPTIMIZATION
    @Override
    protected boolean validateInputs() {

        if (!cacheRecipe()) {
            return false;
        }
        return inputSlot.getCount() >= itemInputCounts.get(0);
    }

    @Override
    protected boolean validateOutputs() {

        ItemStack output = outputSlot.getItemStack();
        if (output.isEmpty()) {
            return true;
        }
        ItemStack recipeOutput = curRecipe.getOutputItems(this).get(0);
        return itemsIdentical(output, recipeOutput) && output.getCount() < recipeOutput.getMaxStackSize();
    }
    // endregion
}
