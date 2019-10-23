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

public class AOEItem implements IAOEItem, ICapabilityProvider {

    private final LazyOptional<IAOEItem> holder = LazyOptional.of(() -> this);

    private final boolean canMineBlocks;

    public AOEItem(boolean canMineBlocks) {

        this.canMineBlocks = canMineBlocks;
    }

    @Override
    public boolean canMineBlocks() {

        return canMineBlocks;
    }

    @Override
    public ImmutableList<BlockPos> getAOEBlocks(ItemStack stack, BlockPos pos, PlayerEntity player) {

        return AOEHelper.getAOEBlocks(stack, pos, player);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable Direction side) {

        return AOE_ITEM_CAPABILITY.orEmpty(cap, this.holder);
    }

}
