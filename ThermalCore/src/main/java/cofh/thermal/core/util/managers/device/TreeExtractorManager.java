package cofh.thermal.core.util.managers.device;

import cofh.core.inventory.FalseIInventory;
import cofh.core.util.ComparableItemStack;
import cofh.thermal.core.init.TCoreRecipeTypes;
import cofh.thermal.core.util.managers.AbstractManager;
import cofh.thermal.core.util.recipes.ThermalBoost;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.Pair;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import static cofh.thermal.core.ThermalCore.FLUIDS;
import static cofh.thermal.core.init.TCoreIDs.*;

public class TreeExtractorManager extends AbstractManager {

    private static final TreeExtractorManager INSTANCE = new TreeExtractorManager();

    protected Map<ComparableItemStack, FluidStack> itemMap = new Object2ObjectOpenHashMap<>();
    protected Map<ComparableItemStack, Pair<Float, Integer>> boostMap = new Object2ObjectOpenHashMap<>();

    protected IdentityHashMap<BlockState, FluidStack> trunkMap = new IdentityHashMap<>();
    protected SetMultimap<BlockState, BlockState> leafMap = HashMultimap.create();

    protected TreeExtractorManager() {

        super(8);
    }

    public static TreeExtractorManager instance() {

        return INSTANCE;
    }

    protected void clear() {

        itemMap.clear();
        boostMap.clear();

        trunkMap.clear();
        leafMap.clear();
    }

    // region WORLD
    public Set<BlockState> getMatchingLeaves(BlockState trunk) {

        return leafMap.get(trunk);
    }

    public boolean validTrunk(BlockState state) {

        return trunkMap.containsKey(state);
    }

    public FluidStack getFluid(BlockState trunk) {

        return validTrunk(trunk) ? trunkMap.get(trunk) : FluidStack.EMPTY;
    }

    public boolean addTrunkMapping(BlockState trunk, FluidStack stack) {

        if (stack.isEmpty() || trunk == null || trunk.getBlock() == Blocks.AIR) {
            return false;
        }
        trunkMap.put(trunk, stack);
        return true;
    }

    public boolean addLeafMapping(BlockState trunk, BlockState leaf) {

        if (trunk == null || trunk.getBlock() == Blocks.AIR || leaf == null || leaf.getBlock() == Blocks.AIR) {
            return false;
        }
        leafMap.put(trunk, leaf);
        return true;
    }
    // endregion

    // region BOOSTS
    public boolean validBoost(ItemStack item) {

        return boostMap.containsKey(convert(item));
    }

    public void addBoost(ThermalBoost boost) {

        for (ItemStack ingredient : boost.getIngredient().getMatchingStacks()) {
            boostMap.put(convert(ingredient), Pair.of(boost.getBoostMult(), boost.getBoostCycles()));
        }
    }

    public float getBoostMultiplier(ItemStack item) {

        return validBoost(item) ? boostMap.get(convert(item)).getLeft() : 1.0F;
    }

    public int getBoostCycles(ItemStack item) {

        return validBoost(item) ? boostMap.get(convert(item)).getRight() : 0;
    }
    // endregion

    // region IManager
    @Override
    public void config() {

    }

    @Override
    public void refresh(RecipeManager recipeManager) {

        clear();
        initialize();

        Map<ResourceLocation, IRecipe<FalseIInventory>> boosts = recipeManager.getRecipes(TCoreRecipeTypes.BOOST_TREE_EXTRACTOR);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : boosts.entrySet()) {
            addBoost((ThermalBoost) entry.getValue());
        }
    }
    // endregion

    // TODO: Data-pack this.
    // region TEMPORARY
    private void initialize() {

        FluidStack latex = new FluidStack(FLUIDS.get(ID_FLUID_LATEX), 50);
        FluidStack resin = new FluidStack(FLUIDS.get(ID_FLUID_RESIN), 50);
        FluidStack sap = new FluidStack(FLUIDS.get(ID_FLUID_SAP), 50);

        addDefaultMappings(Blocks.OAK_LOG, Blocks.OAK_LEAVES, new FluidStack(resin, 25));
        addDefaultMappings(Blocks.SPRUCE_LOG, Blocks.SPRUCE_LEAVES, new FluidStack(resin, 50));
        addDefaultMappings(Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES, new FluidStack(resin, 25));
        addDefaultMappings(Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, new FluidStack(latex, 50));
        addDefaultMappings(Blocks.ACACIA_LOG, Blocks.ACACIA_LEAVES, new FluidStack(resin, 50));
        addDefaultMappings(Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LEAVES, new FluidStack(sap, 25));
    }

    private void addDefaultMappings(Block trunk, Block leaf, FluidStack stack) {

        if (trunk instanceof RotatedPillarBlock && leaf instanceof LeavesBlock) {
            BlockState state = trunk.getDefaultState();
            addTrunkMapping(state, stack);
            for (int i = 1; i <= 7; ++i) {
                addLeafMapping(state, leaf.getDefaultState().with(LeavesBlock.DISTANCE, i).with(LeavesBlock.PERSISTENT, false));
            }
        }
    }
    // endregion
}
