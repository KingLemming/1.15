package cofh.lib.inventory.container.slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Slot which displays an item.
 */
public class SlotViewOnly extends SlotCoFH {

    public SlotViewOnly(IInventory inventoryIn, int index, int xPosition, int yPosition) {

        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {

        return false;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean isEnabled() {

        return false;
    }

    @Override
    public boolean canTakeStack(PlayerEntity player) {

        return false;
    }

}
