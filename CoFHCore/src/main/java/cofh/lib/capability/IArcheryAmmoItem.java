package cofh.lib.capability;

import cofh.lib.util.helpers.ArcheryHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Implement this interface as a capability for a Quiver item which should be compatible with CoFH's improved archery handling.
 *
 * @author King Lemming
 */
public interface IArcheryAmmoItem extends IArcheryItem {

    /**
     * Handle creation of a custom arrow entity - this method actually spawns the arrow!
     *
     * @param world   World where the arrow will spawn.
     * @param ammo    ItemStack representing the ammo.
     * @param shooter Player holding the bow.
     * @return Custom arrow entity which was spawned in the world.
     */
    default AbstractArrowEntity createArrowEntity(World world, ItemStack ammo, PlayerEntity shooter) {

        return ArcheryHelper.createDefaultArrow(world, ammo, shooter);
    }

    default ItemStack getAmmo(ItemStack ammo, PlayerEntity shooter) {

        return ammo;
    }

    default boolean isEmpty(ItemStack ammo, PlayerEntity shooter) {

        return ammo.isEmpty();
    }

    default boolean isInfinite(ItemStack ammo, ItemStack weapon, PlayerEntity shooter) {

        int enchant = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, weapon);
        return enchant > 0 && ammo.getItem().getClass() == ArrowItem.class;
    }

}
