package cofh.lib.item;

import cofh.lib.util.helpers.SecurityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeItem;

import javax.annotation.Nullable;
import java.util.function.BooleanSupplier;

/**
 * Hacky default interface to reduce boilerplate. :)
 */
public interface ICoFHItem extends IForgeItem {

    BooleanSupplier SHOW = () -> true;
    BooleanSupplier HIDE = () -> false;

    default boolean isCreative(ItemStack stack) {

        return false;
    }

    @Override
    default boolean hasCustomEntity(ItemStack stack) {

        return SecurityHelper.hasSecurity(stack);
    }

    @Override
    @Nullable
    default Entity createEntity(World world, Entity location, ItemStack stack) {

        if (SecurityHelper.hasSecurity(stack)) {
            location.setInvulnerable(true);
            ((ItemEntity) location).lifespan = Integer.MAX_VALUE;
        }
        return null;
    }

}
