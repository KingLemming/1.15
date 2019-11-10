package cofh.ensorcellation.enchantment.override;

import cofh.lib.enchantment.EnchantmentOverride;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;

import static cofh.lib.util.constants.Constants.ID_ENSORCELLATION;
import static cofh.lib.util.references.CoreReferences.GLOSSED_MAGMA;

public class FrostWalkerEnchantmentImp extends EnchantmentOverride {

    private static boolean freezeLava = true;

    public FrostWalkerEnchantmentImp() {

        super(Rarity.RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[]{EquipmentSlotType.FEET});
        maxLevel = 2;
        treasure = true;
    }

    @Override
    public int getMinEnchantability(int level) {

        return level * 10;
    }

    @Override
    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 15;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        if (!enable) {
            return super.canApplyAtEnchantingTable(stack);
        }
        Item item = stack.getItem();
        return enable && (type != null && type.canEnchantItem(item) || item instanceof HorseArmorItem || supportsEnchantment(stack));
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != Enchantments.DEPTH_STRIDER;
    }

    // region HELPERS
    public static void freezeNearby(LivingEntity living, World worldIn, BlockPos pos, int level) {

        if (!freezeLava) {
            return;
        }
        if (living.onGround) {
            BlockState blockstate = GLOSSED_MAGMA.getDefaultState();
            float f = (float) Math.min(16, 2 + level);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-f, -1.0D, -f), pos.add(f, -1.0D, f))) {
                if (blockpos.withinDistance(living.getPositionVec(), f)) {
                    blockpos$mutableblockpos.setPos(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = worldIn.getBlockState(blockpos$mutableblockpos);
                    if (blockstate1.isAir(worldIn, blockpos$mutableblockpos)) {
                        BlockState blockstate2 = worldIn.getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.LAVA && blockstate2.get(FlowingFluidBlock.LEVEL) == 0;
                        if (blockstate2.getMaterial() == Material.LAVA && isFull && blockstate.isValidPosition(worldIn, blockpos) && worldIn.func_217350_a(blockstate, blockpos, ISelectionContext.dummy()) && !ForgeEventFactory.onBlockPlace(living, new BlockSnapshot(worldIn, blockpos, blockstate2), Direction.UP)) {
                            worldIn.setBlockState(blockpos, blockstate);
                            worldIn.getPendingBlockTicks().scheduleTick(blockpos, GLOSSED_MAGMA, MathHelper.nextInt(living.getRNG(), 60, 120));
                        }
                    }
                }
            }
        }
    }

    public void setFreezeLava(boolean freezeLava) {

        FrostWalkerEnchantmentImp.freezeLava = freezeLava;
        name = "enchantment." + (freezeLava ? ID_ENSORCELLATION + ".frost_walker" : "minecraft.frost_walker");
    }
    // endregion
}