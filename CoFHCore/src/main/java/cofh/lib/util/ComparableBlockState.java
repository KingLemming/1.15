package cofh.lib.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ComparableBlockState {

    private final Block block;
    private final String stateString;

    public ComparableBlockState(BlockState state) {

        this.block = state.getBlock();
        this.stateString = state.toString();
    }

    public ItemStack toItemStack() {

        return block.asItem() != Items.AIR ? new ItemStack(block.asItem()) : ItemStack.EMPTY;
    }

    @Override
    public boolean equals(Object o) {

        return o instanceof ComparableBlockState && stateString.equals(((ComparableBlockState) o).stateString);
    }

    @Override
    public int hashCode() {

        return stateString.hashCode();
    }

}
