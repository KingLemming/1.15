package cofh.lib.capability;

import cofh.lib.util.helpers.ArcheryHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Implement this interface as a capability for a Bow Item which should be compatible with CoFH's improved archery handling.
 * You can get really clever and add your own custom bow behavior by overriding the "fireArrow" method. By default, this method is a passthrough for CoFH's vanilla handling.
 *
 * @author King Lemming
 */
public interface IArcheryBowItem extends IArcheryItem {

    /**
     * This method handles the entire shooting process - these parameters are essentially passed in from the onArrowLoosed event.
     *
     * @param bow     ItemStack representing the bow.
     * @param ammo    ItemStack representing the ammo.
     * @param shooter Player holding the bow.
     * @param charge  Amount of time the bow has been drawn.
     * @param world   World object, used to spawn the arrow.
     * @return TRUE if an arrow was actually fired.
     */
    default boolean fireArrow(ItemStack bow, ItemStack ammo, PlayerEntity shooter, int charge, World world) {

        return ArcheryHelper.fireArrow(bow, ammo, shooter, charge, world);
    }

    /**
     * Determine if the bow should handle arrow entity creation, based on both the bow and the ammo to be fired.
     *
     * @param bow  ItemStack representing the bow.
     * @param ammo ItemStack representing the ammo.
     * @return TRUE if the bow should handle creation of the arrow entity.
     */
    default boolean handleArrowCreation(ItemStack bow, ItemStack ammo) {

        return false;
    }

}
