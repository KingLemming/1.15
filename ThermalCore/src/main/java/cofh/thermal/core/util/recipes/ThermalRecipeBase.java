package cofh.thermal.core.util.recipes;

import cofh.lib.inventory.FalseIInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * This class really just serves as a way to ride on Mojang's automated recipe syncing and datapack functionality.
 * Nothing in Thermal actually uses any of this for logic whatsoever. It's part of a shim layer, nothing more.
 */
public abstract class ThermalRecipeBase implements IRecipe<FalseIInventory> {

    protected final ResourceLocation id;

    protected ThermalRecipeBase(ResourceLocation id) {

        this.id = id;
    }

    // region IRecipe
    @Override
    public boolean matches(FalseIInventory inv, World worldIn) {

        return true;
    }

    @Override
    public ItemStack getCraftingResult(FalseIInventory inv) {

        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {

        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {

        return ItemStack.EMPTY;
    }

    @Override
    public boolean isDynamic() {

        return true;
    }

    @Override
    public ResourceLocation getId() {

        return id;
    }

    @Override
    public abstract IRecipeSerializer<?> getSerializer();

    @Override
    public abstract IRecipeType<?> getType();
    // endregion
}
