package cofh.thermal.core.item;

import cofh.thermal.core.entity.projectile.FertilizerThrownEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

import static cofh.lib.util.helpers.ItemHelper.cloneStack;

public class FertilizerThrownItem extends FertilizerItem {

    public FertilizerThrownItem(Properties builder) {

        super(builder);

        this.addPropertyOverride(new ResourceLocation("thrown"), (stack, world, living) -> (stack.getDamage() > 0 ? 1.0F : 0.0F));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        ItemStack stack = playerIn.getHeldItem(handIn);
        worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isRemote) {
            FertilizerThrownEntity grenade = new FertilizerThrownEntity(worldIn, playerIn);
            ItemStack throwStack = cloneStack(stack, 1);
            throwStack.setDamage(1);
            grenade.setItem(throwStack);
            grenade.setRadius(1 + radius);
            grenade.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0.5F);
            worldIn.addEntity(grenade);
        }
        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            stack.shrink(1);
        }
        return ActionResult.resultSuccess(stack);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        return ActionResultType.PASS;
    }

}
