package cofh.lib.capability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public interface IShieldItem {

    /**
     * Callback for when a block is performed with the shield. Used for example, if energy should be drained.
     *
     * @param shield ItemStack representing the shield.
     * @param entity Entity holding the shield.
     * @param source Immediate source of the damage. True source may be an entity.
     */
    default void onBlock(ItemStack shield, LivingEntity entity, DamageSource source) {

    }

}
