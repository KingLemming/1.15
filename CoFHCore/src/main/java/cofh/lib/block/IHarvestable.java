package cofh.lib.block;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.minecraft.enchantment.Enchantments.FORTUNE;

public interface IHarvestable {

    boolean canHarvest(BlockState state);

    boolean harvest(World world, BlockPos pos, BlockState state, int fortune);

    default boolean harvest(World world, BlockPos pos, BlockState state, PlayerEntity player) {

        return harvest(world, pos, state, EnchantmentHelper.getEnchantmentLevel(FORTUNE, player.getHeldItemMainhand()));
    }

}
