package cofh.lib.capability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IArcheryItem {

    /**
     * Callback for when an arrow is loosed. Used for example, if energy should be drained.
     *
     * @param weapon  ItemStack representing the weapon.
     * @param ammo    ItemStack representing the ammo.
     * @param shooter Player holding the weapon.
     */
    default void onArrowLoosed(ItemStack weapon, ItemStack ammo, PlayerEntity shooter) {

    }

}
