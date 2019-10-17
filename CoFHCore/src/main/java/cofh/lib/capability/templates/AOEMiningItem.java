package cofh.lib.capability.templates;

import cofh.lib.capability.IAOEItem;
import cofh.lib.util.helpers.AOEHelper;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cofh.lib.capability.CapabilityAOE.AOE_ITEM_CAPABILITY;
import static cofh.lib.util.references.EnsorcellationReferences.EXCAVATING;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;

public class AOEMiningItem implements IAOEItem, ICapabilityProvider {

    private final LazyOptional<IAOEItem> holder = LazyOptional.of(() -> this);

    private final int radius;
    private final int depth;
    private final Type type;

    public enum Type {
        EXCAVATOR, HAMMER, SICKLE
    }

    public AOEMiningItem(int radius, int depth, Type type) {

        this.radius = radius;
        this.depth = depth;
        this.type = type;
    }

    public AOEMiningItem(int radius, Type type) {

        this(radius, 1, type);
    }

    @Override
    public ImmutableList<BlockPos> getAOEBlocks(ItemStack stack, BlockPos pos, PlayerEntity player) {

        if (type == Type.SICKLE) {
            return AOEHelper.getAOEBlocksMiningArea(stack, pos, player, radius, depth);
        }
        return AOEHelper.getAOEBlocksMiningRadius(stack, pos, player, radius + getEnchantmentLevel(EXCAVATING, stack));
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable Direction side) {

        return AOE_ITEM_CAPABILITY.orEmpty(cap, this.holder);
    }

}
