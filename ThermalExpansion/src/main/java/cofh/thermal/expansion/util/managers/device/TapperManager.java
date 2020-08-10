package cofh.thermal.expansion.util.managers.device;

import cofh.lib.util.ComparableItemStack;
import cofh.thermal.core.util.managers.IManager;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraftforge.fluids.FluidStack;

import java.util.Map;

public class TapperManager implements IManager {

    protected Map<ComparableItemStack, FluidStack> itemMap = new Object2ObjectOpenHashMap<>();

    protected Map<BlockState, FluidStack> trunkMap = new Object2ObjectOpenHashMap<>();
    protected SetMultimap<BlockState, BlockState> leafMap = HashMultimap.create();

    // region IManager
    @Override
    public void config() {

    }

    @Override
    public void refresh(RecipeManager recipeManager) {

    }
    // endregion
}
