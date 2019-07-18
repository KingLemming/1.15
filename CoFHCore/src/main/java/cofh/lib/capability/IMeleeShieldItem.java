package cofh.lib.capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IMeleeShieldItem {

    /**
     * Callback for when a block is performed with the shield. Used for example, if energy should be drained.
     *
     * @param shield ItemStack representing the shield.
     * @param player Player holding the shield.
     * @param source Source of the damage. May not be a living entity; could be a projectile.
     */
    default void onBlock(ItemStack shield, PlayerEntity player, Entity source) {

    }

}
