package cofh.lib.capability;

import cofh.lib.util.helpers.ArcheryHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IArcheryItem {

    /**
     * Return the total accuracy multiplier for this item - LOWER IS BETTER - 1.0F is default.
     * Note that weapons and ammo can BOTH have this!
     *
     * @param bow     ItemStack representing the bow.
     * @param ammo    ItemStack representing the ammo.
     * @param shooter Player holding the bow.
     * @return Total accuracy modifier - 0.5F means arrows will shoot with half as much variance.
     */
    default float getAccuracyModifier(ItemStack bow, ItemStack ammo, PlayerEntity shooter) {

        return 1.0F;
    }

    /**
     * Return the total damage multiplier for this item - 1.0F is default.
     * Note that weapons and ammo can BOTH have this!
     *
     * @param bow     ItemStack representing the bow.
     * @param ammo    ItemStack representing the ammo.
     * @param shooter Player holding the bow.
     * @return Total damage modifier - 1.1F would be 10% more.
     */
    default float getDamageModifier(ItemStack bow, ItemStack ammo, PlayerEntity shooter) {

        return 1.0F;
    }

    /**
     * Return the total arrow speed multiplier for this item - 1.0F is default.
     * Note that weapons and ammo can BOTH have this!
     *
     * @param bow     ItemStack representing the bow.
     * @param ammo    ItemStack representing the ammo.
     * @param shooter Player holding the bow.
     * @return Total speed modifier - as an example, 1.1F would be 10% more.
     */
    default float getVelocityModifier(ItemStack bow, ItemStack ammo, PlayerEntity shooter) {

        return 1.0F;
    }

    /**
     * Callback for when an arrow is loosed. Used for example, if energy should be drained.
     *
     * @param weapon  ItemStack representing the weapon.
     * @param ammo    ItemStack representing the ammo.
     * @param shooter Player holding the bow.
     */
    default void onArrowLoosed(ItemStack weapon, ItemStack ammo, PlayerEntity shooter) {

    }

    /**
     * Handle creation of a custom arrow entity - this method actually spawns the arrow!
     *
     * @param world   World where the arrow will spawn.
     * @param weapon  ItemStack representing the weapon.
     * @param ammo    ItemStack representing the ammo.
     * @param shooter Player holding the bow.
     * @return Custom arrow entity which was spawned in the world.
     */
    default AbstractArrowEntity createArrowEntity(World world, ItemStack weapon, ItemStack ammo, PlayerEntity shooter) {

        return ArcheryHelper.createDefaultArrow(world, ammo, shooter);
    }

}
