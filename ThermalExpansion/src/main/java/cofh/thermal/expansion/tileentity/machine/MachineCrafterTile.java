package cofh.thermal.expansion.tileentity.machine;

import cofh.core.fluid.FluidStorageCoFH;
import cofh.core.inventory.FalseCraftingInventory;
import cofh.core.inventory.ItemStorageCoFH;
import cofh.core.util.Utils;
import cofh.thermal.core.tileentity.MachineTileProcess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;
import java.util.Optional;

import static cofh.core.util.StorageGroup.*;
import static cofh.core.util.constants.Constants.TANK_MEDIUM;
import static cofh.thermal.core.common.ThermalConfig.machineAugments;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CRAFTER_TILE;

// TODO: Finish. This one is going to be *completely* different.
public class MachineCrafterTile extends MachineTileProcess {

    protected FalseCraftingInventory craftMatrix = new FalseCraftingInventory(3, 3);
    protected CraftResultInventory craftResult = new CraftResultInventory();
    protected ICraftingRecipe craftRecipe;
    protected boolean hasRecipeChanges;

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

        if (world == null || Utils.isClientWorld(world)) {
            return;
        }
        for (int i = 0; i < 9; ++i) {
            craftMatrix.setInventorySlotContents(i, inventory.getInternalSlots().get(i).getItemStack());
        }
        Optional<ICraftingRecipe> possibleRecipe = world.getRecipeManager().getRecipe(IRecipeType.CRAFTING, craftMatrix, world);
        if (possibleRecipe.isPresent()) {
            craftRecipe = possibleRecipe.get();
            craftResult.setInventorySlotContents(0, craftRecipe.getCraftingResult(craftMatrix));
        } else {
            craftRecipe = null;
            craftResult.setInventorySlotContents(0, ItemStack.EMPTY);
            if (isActive) {
                processOff();
            }
        }
        craftResult.setRecipeUsed(craftRecipe);
        resultSlot.setItemStack(craftResult.getStackInSlot(0));
        clearRecipeChanges();
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
