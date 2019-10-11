package cofh.lib.capability;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public interface IAreaEffectItem {

    default ImmutableList<BlockPos> getAreaEffectBlocks(ItemStack stack, BlockPos pos, PlayerEntity player) {

        return ImmutableList.copyOf(new ArrayList<>());
    }

    default float getReachDistance(ItemStack stack) {

        return 4.5F;
    }

}
