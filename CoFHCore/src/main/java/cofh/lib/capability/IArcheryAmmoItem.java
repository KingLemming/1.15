package cofh.lib.capability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

/**
 * Implement this interface as a capability for a Quiver item which should be compatible with CoFH's improved archery handling.
 *
 * @author King Lemming
 */
public interface IArcheryAmmoItem extends IArcheryItem {

    /**
     * Determine if the quiver allows its ammunition to be modified by the bow.
     *
     * @param bow  ItemStack representing the bow.
     * @param ammo ItemStack representing the ammo.
     * @return TRUE if the ammo will defer arrow creation to the bow.
     */
    default boolean deferArrowCreation(ItemStack bow, ItemStack ammo) {

        return true;
    }

    boolean isEmpty(ItemStack ammo, PlayerEntity shooter);

}
