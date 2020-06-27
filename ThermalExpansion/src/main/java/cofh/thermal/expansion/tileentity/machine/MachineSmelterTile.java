package cofh.thermal.expansion.tileentity.machine;

import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.MathHelper;
import cofh.thermal.core.tileentity.MachineTileReconfigurableProcess;
import cofh.thermal.expansion.inventory.container.machine.MachineSmelterContainer;
import cofh.thermal.expansion.util.managers.machine.SmelterRecipeManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

import javax.annotation.Nullable;
import java.util.List;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.helpers.ItemHelper.itemsEqual;
import static cofh.thermal.core.common.ThermalConfig.machineAugments;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_SMELTER_TILE;

public class MachineSmelterTile extends MachineTileReconfigurableProcess {

    protected ItemStorageCoFH[] inputSlots = new ItemStorageCoFH[3];
    protected ItemStorageCoFH catalystSlot = new ItemStorageCoFH(SmelterRecipeManager.instance()::validCatalyst);

    public MachineSmelterTile() {

        super(MACHINE_SMELTER_TILE);

        inputSlots[0] = new ItemStorageCoFH((item) -> SmelterRecipeManager.instance().validItem(item) && !itemsEqual(item, inputSlots[1].getItemStack()) && !itemsEqual(item, inputSlots[2].getItemStack()));
        inputSlots[1] = new ItemStorageCoFH((item) -> SmelterRecipeManager.instance().validItem(item) && !itemsEqual(item, inputSlots[0].getItemStack()) && !itemsEqual(item, inputSlots[2].getItemStack()));
        inputSlots[2] = new ItemStorageCoFH((item) -> SmelterRecipeManager.instance().validItem(item) && !itemsEqual(item, inputSlots[0].getItemStack()) && !itemsEqual(item, inputSlots[1].getItemStack()));

        for (int i = 0; i < 3; ++i) {
            inventory.addSlot(inputSlots[i], INPUT);
        }
        inventory.addSlot(catalystSlot, CATALYST);
        inventory.addSlots(OUTPUT, 4);
        inventory.addSlot(chargeSlot, INTERNAL);

        addAugmentSlots(machineAugments);
        initHandlers();
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = SmelterRecipeManager.instance().getRecipe(this);
        curCatalyst = SmelterRecipeManager.instance().getCatalyst(catalystSlot);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
        }
        return curRecipe != null;
    }

    @Override
    protected void resolveInputs() {

        // Input Items
        for (int i = 0; i < 3; ++i) {
            inputSlots[i].modify(-itemInputCounts.get(0));
        }
        int decrement = itemInputCounts.size() > 3 ? itemInputCounts.get(3) : 0;
        if (decrement > 0) {
            if (catalystSlot.getItemStack().isDamageable()) {
                if (catalystSlot.getItemStack().attemptDamageItem(decrement, MathHelper.RANDOM, null)) {
                    catalystSlot.modify(-1);
                }
            } else {
                catalystSlot.modify(-decrement);
            }
        }
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new MachineSmelterContainer(i, world, pos, inventory, player);
    }

    // region OPTIMIZATION
    @Override
    protected boolean validateInputs() {

        if (!cacheRecipe()) {
            return false;
        }
        List<? extends ItemStorageCoFH> slotInputs = inputSlots();
        for (int i = 0; i < slotInputs.size() && i < itemInputCounts.size(); ++i) {
            int inputCount = itemInputCounts.get(i);
            if (slotInputs.get(i).getItemStack().getCount() < inputCount) {
                return false;
            }
        }
        return true;
    }
    // endregion
}
