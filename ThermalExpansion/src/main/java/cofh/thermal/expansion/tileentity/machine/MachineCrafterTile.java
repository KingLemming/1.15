package cofh.thermal.expansion.tileentity.machine;

import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.FalseCraftingInventory;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.tileentity.MachineTileReconfigurableProcess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipe;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.thermal.core.common.ThermalConfig.machineAugments;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CRAFTER_TILE;

// TODO: Finish. This one is going to be *completely* different.
public class MachineCrafterTile extends MachineTileReconfigurableProcess {

    protected FalseCraftingInventory craftMatrix = new FalseCraftingInventory(3, 3);
    protected CraftResultInventory craftResult = new CraftResultInventory();
    protected IRecipe<CraftingInventory> craftRecipe;
    protected boolean hasRecipeChanges;
    protected boolean usingTank;

    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();
    protected ItemStorageCoFH resultSlot = new ItemStorageCoFH();

    protected FluidStorageCoFH inputTank = new FluidStorageCoFH(TANK_MEDIUM);

    public MachineCrafterTile() {

        super(MACHINE_CRAFTER_TILE);

        inventory.addSlots(INPUT, 18);
        inventory.addSlot(outputSlot, OUTPUT);
        inventory.addSlots(INTERNAL, 9);
        inventory.addSlot(resultSlot, INTERNAL);
        inventory.addSlot(chargeSlot, INTERNAL);

        tankInv.addTank(inputTank, OUTPUT);

        addAugmentSlots(machineAugments);
        initHandlers();
    }

    protected void setRecipe() {

        //        for (int i = 0; i < 9; ++i) {
        //            craftMatrix.setInventorySlotContents(i, inventory.getInternalSlots().get(i).getItemStack());
        //        }
        //        Optional<ICraftingRecipe> possibleRecipe = world.getServer().getRecipeManager().getRecipe(IRecipeType.CRAFTING, craftMatrix, world);
        //        if (possibleRecipe.isPresent()) {
        //            ICraftingRecipe craftingRecipe = possibleRecipe.get();
        //
        //
        ////            if (p_217066_4_.canUseRecipe(p_217066_1_, serverplayerentity, craftingRecipe)) {
        ////                itemstack = craftingRecipe.getCraftingResult(p_217066_3_);
        ////            }
        //        } else {
        //
        //        }
        //
        //        IRecipe newRecipe = CraftingManager.findMatchingRecipe(craftMatrix, world);
        //
        //        ItemStack stack = ItemStack.EMPTY;
        //
        //        if (newRecipe != null) {
        //            stack = newRecipe.getCraftingResult(craftMatrix);
        //            craftRecipe = CrafterRecipe.getRecipe(newRecipe, this);
        //
        //            if (craftRecipe == null) {
        //                newRecipe = null;
        //                stack = ItemStack.EMPTY;
        //                usingTank = false;
        //            }
        //        } else {
        //            craftRecipe = null;
        //            usingTank = false;
        //        }
        //        if (craftRecipe == null) {
        //            if (isActive) {
        //                processOff();
        //            }
        //        }
        //        resultSlot.setItemStack(stack);
        //        craftResult.setRecipeUsed(newRecipe);
        //        craftResult.setInventorySlotContents(0, resultSlot.getItemStack());
        //        clearRecipeChanges();
    }

    public void clearRecipeChanges() {

        hasRecipeChanges = false;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {

        return null;
    }

}
