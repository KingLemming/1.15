package cofh.core.inventory;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public interface IItemStackAccess {

    @Nonnull
    ItemStack getItemStack();

    int getCount();

    boolean isEmpty();

}
