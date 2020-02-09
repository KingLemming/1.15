package cofh.thermal.expansion.tileentity;

import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.tileentity.MachineTileProcess;
import cofh.thermal.expansion.inventory.container.MachineFurnaceContainer;
import cofh.thermal.expansion.util.managers.machine.FurnaceRecipeManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.INPUT;
import static cofh.lib.util.StorageGroup.OUTPUT;
import static cofh.lib.util.helpers.ItemHelper.itemsEqualWithTags;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_FURNACE_TILE;

public class MachineFurnaceTile extends MachineTileProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(FurnaceRecipeManager.instance()::validRecipe);
    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();

    public MachineFurnaceTile() {

        super(MACHINE_FURNACE_TILE);

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

        return new MachineFurnaceContainer(i, world, pos, inventory, player);
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
        return itemsEqualWithTags(output, recipeOutput) && output.getCount() < recipeOutput.getMaxStackSize();
    }
    // endregion

    // TODO: Determine WHY.
    @Override
    public void tick() {

        energyStorage.modify(10);

        boolean curActive = isActive;

        if (isActive) {
            processTick();
            if (canProcessFinish()) {
                processFinish();
                transferOutput();
                transferInput();
                if (!redstoneControl.getState() || !canProcessStart()) {
                    processOff();
                } else {
                    processStart();
                }
            } else if (energyStorage.isEmpty()) {
                processOff();
            }
        } else if (redstoneControl.getState()) {
            if (timeCheck()) {
                transferOutput();
                transferInput();
            }
            if (timeCheckQuarter() && canProcessStart()) {
                processStart();
                processTick();
                isActive = true;
            }
        }
        updateActiveState(curActive);
        // chargeEnergy();
    }

    @Override
    public ITextComponent getDisplayName() {

        return new StringTextComponent(getType().getRegistryName().getPath());
    }

}
