package cofh.lib.item;

import cofh.lib.capability.templates.AOEMiningItem;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.Set;

import static cofh.lib.util.constants.ToolTypes.SICKLE;

public class SickleItem extends ToolItem {

    public static final Set<Block> EFFECTIVE_BLOCKS = ImmutableSet.of();
    public static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(Material.LEAVES, Material.PLANTS, Material.TALL_PLANTS, Material.WEB);

    private static float DEFAULT_ATTACK_DAMAGE = 2.5F;
    private static float DEFAULT_ATTACK_SPEED = -2.6F;
    private static int DEFAULT_BASE_RADIUS = 2;
    private static int DEFAULT_BASE_HEIGHT = 0;

    private int radius;
    private int height;

    public SickleItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, int radius, int height, Properties builder) {

        super(attackDamageIn, attackSpeedIn, tier, EFFECTIVE_BLOCKS, builder.addToolType(SICKLE, tier.getHarvestLevel()));
        this.radius = radius;
        this.height = height;
    }

    public SickleItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {

        this(tier, attackDamageIn, attackSpeedIn, DEFAULT_BASE_RADIUS, DEFAULT_BASE_HEIGHT, builder.addToolType(SICKLE, tier.getHarvestLevel()));
    }

    public SickleItem(IItemTier tier, float attackDamageIn, Properties builder) {

        this(tier, attackDamageIn, DEFAULT_ATTACK_SPEED, DEFAULT_BASE_RADIUS, DEFAULT_BASE_HEIGHT, builder.addToolType(SICKLE, tier.getHarvestLevel()));
    }

    public SickleItem(IItemTier tier, Properties builder) {

        this(tier, DEFAULT_ATTACK_DAMAGE, DEFAULT_ATTACK_SPEED, DEFAULT_BASE_RADIUS, DEFAULT_BASE_HEIGHT, builder.addToolType(SICKLE, tier.getHarvestLevel()));
    }

    @Override
    public boolean canHarvestBlock(BlockState state) {

        if (state.getHarvestTool() == SICKLE) {
            return getTier().getHarvestLevel() >= state.getHarvestLevel();
        }
        return EFFECTIVE_MATERIALS.contains(state.getMaterial());
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {

        Block block = state.getBlock();
        if (block == Blocks.COBWEB) {
            return 15.0F;
        } else {
            return EFFECTIVE_MATERIALS.contains(state.getMaterial()) ? this.efficiency : 1.0F;
        }
    }

    @Nullable
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {

        return new AOEMiningItem(radius, height, AOEMiningItem.Type.SICKLE);
    }

}
