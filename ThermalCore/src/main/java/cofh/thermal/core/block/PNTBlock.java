package cofh.thermal.core.block;

import cofh.thermal.core.entity.item.PNTEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.TNTBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class PNTBlock extends TNTBlock {

    public PNTBlock(Properties properties) {

        super(properties);
    }

    @Override
    public void catchFire(BlockState state, World world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {

        if (!world.isRemote) {
            PNTEntity pntentity = new PNTEntity(world, (double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D);
            world.addEntity(pntentity);
            world.playSound(null, pntentity.getPosX(), pntentity.getPosY(), pntentity.getPosZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

}
