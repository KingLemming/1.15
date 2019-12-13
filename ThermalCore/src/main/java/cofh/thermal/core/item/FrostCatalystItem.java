package cofh.thermal.core.item;

import cofh.lib.item.ItemCoFH;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class FrostCatalystItem extends ItemCoFH {

    public FrostCatalystItem(Properties builder) {

        super(builder);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        return new ActionResult<>(ActionResultType.PASS, playerIn.getHeldItem(handIn));
    }

}
