package cofh.thermal.core.item;

import cofh.core.item.ItemCoFH;
import cofh.thermal.core.entity.projectile.BlitzProjectileEntity;
import cofh.thermal.core.init.TCoreSounds;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class LightningChargeItem extends ItemCoFH {

    public LightningChargeItem(Properties builder) {

        super(builder);

        DispenserBlock.registerDispenseBehavior(this, DISPENSER_BEHAVIOR);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getPos().offset(context.getFace());

        //        if (player != null && (!world.isBlockModifiable(player, pos) || !player.canPlayerEdit(pos, context.getFace(), context.getItem()))) {
        //            return ActionResultType.FAIL;
        //        }
        if (world.canSeeSky(pos)) {
            if (world instanceof ServerWorld) {
                LightningBoltEntity bolt = new LightningBoltEntity(world, (double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D, false);
                bolt.setCaster(player instanceof ServerPlayerEntity ? (ServerPlayerEntity) player : null);
                ((ServerWorld) world).addLightningBolt(bolt);
            }
            // playUseSound(world, pos);
            context.getItem().shrink(1);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    private void playUseSound(World worldIn, BlockPos pos) {

        worldIn.playSound(null, pos, TCoreSounds.SOUND_BLITZ_SHOOT, SoundCategory.BLOCKS, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
    }

    // region DISPENSER BEHAVIOR
    private static final IDispenseItemBehavior DISPENSER_BEHAVIOR = new DefaultDispenseItemBehavior() {

        @Override
        public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {

            Direction direction = source.getBlockState().get(DispenserBlock.FACING);
            IPosition iposition = DispenserBlock.getDispensePosition(source);
            double d0 = iposition.getX() + (double) ((float) direction.getXOffset() * 0.3F);
            double d1 = iposition.getY() + (double) ((float) direction.getYOffset() * 0.3F);
            double d2 = iposition.getZ() + (double) ((float) direction.getZOffset() * 0.3F);
            World world = source.getWorld();
            Random random = world.rand;
            double d3 = random.nextGaussian() * 0.05D + (double) direction.getXOffset();
            double d4 = random.nextGaussian() * 0.05D + (double) direction.getYOffset();
            double d5 = random.nextGaussian() * 0.05D + (double) direction.getZOffset();
            world.addEntity(Util.make(() -> new BlitzProjectileEntity(d0, d1, d2, d3, d4, d5, world)));
            stack.shrink(1);
            return stack;
        }

        @Override
        protected void playDispenseSound(IBlockSource source) {

            source.getWorld().playEvent(1018, source.getBlockPos(), 0);
        }
    };
    // endregion
}
