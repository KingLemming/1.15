package cofh.thermal.core.util.managers.device;

import cofh.lib.util.ComparableItemStack;
import cofh.thermal.core.util.managers.IManager;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraftforge.fluids.FluidStack;

import java.util.Map;

public class TreeExtractorManager implements IManager {

    protected Map<ComparableItemStack, FluidStack> itemMap = new Object2ObjectOpenHashMap<>();

    protected Map<BlockState, FluidStack> trunkMap = new Object2ObjectOpenHashMap<>();
    protected SetMultimap<BlockState, BlockState> leafMap = HashMultimap.create();

    // TODO: Proper Catalyzing
    //    protected Object2FloatMap<IItemProvider> fertilizerMap = new Object2FloatOpenHashMap<>();
    //
    //    public float getFertilizerMultiplier(ItemStack stack) {
    //
    //        return fertilizerMap.getFloat(stack.getItem());
    //    }
    //
    //    public void addFertilizer(ItemStack fertilizer, float multiplier) {
    //
    //        fertilizerMap.put(fertilizer.getItem(), multiplier);
    //    }

    // region IManager
    @Override
    public void config() {

    }

    @Override
    public void refresh(RecipeManager recipeManager) {

    }
    // endregion
}
