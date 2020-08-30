package cofh.lib.datagen;

import cofh.core.util.FeatureManager;
import cofh.core.util.FeatureRecipeCondition;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class RecipeProviderCoFH extends RecipeProvider implements IConditionBuilder {

    private final String modid;
    // private FeatureManager manager;

    public RecipeProviderCoFH(DataGenerator generatorIn, String modid) {

        super(generatorIn);
        this.modid = modid;
    }

    // TODO: Finish adding this to fully support modular features.
    //    protected void generateFlaggedRecipe(ResourceLocation id, Consumer<Consumer<IFinishedRecipe>> callable, String flag, Consumer<IFinishedRecipe> consumer) {
    //
    //        ConditionalRecipe.builder()
    //                .addCondition(
    //                        flagEnabled(manager, flag)
    //                )
    //                .addRecipe(callable)
    //                .build(consumer, id);
    //    }
    //
    //    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
    //
    //        ResourceLocation ID = new ResourceLocation("data_gen_test", "conditional");
    //
    //        ConditionalRecipe.builder()
    //                .addCondition(
    //                        and(
    //                                not(modLoaded("minecraft")),
    //                                itemExists("minecraft", "dirt"),
    //                                FALSE()
    //                        )
    //                )
    //                .addRecipe(
    //                        ShapedRecipeBuilder.shapedRecipe(Blocks.DIAMOND_BLOCK, 64)
    //                                .patternLine("XXX")
    //                                .patternLine("XXX")
    //                                .patternLine("XXX")
    //                                .key('X', Blocks.DIRT)
    //                                .setGroup("")
    //                                .addCriterion("has_dirt", hasItem(Blocks.DIRT)) //Doesn't actually print... TODO: nested/conditional advancements?
    //                                ::build
    //                )
    //                .setAdvancement(ID,
    //                        ConditionalAdvancement.builder()
    //                                .addCondition(
    //                                        and(
    //                                                not(modLoaded("minecraft")),
    //                                                itemExists("minecraft", "dirt"),
    //                                                FALSE()
    //                                        )
    //                                )
    //                                .addAdvancement(
    //                                        Advancement.Builder.builder()
    //                                                .withParentId(new ResourceLocation("minecraft", "root"))
    //                                                .withDisplay(Blocks.DIAMOND_BLOCK,
    //                                                        new StringTextComponent("Dirt2Diamonds"),
    //                                                        new StringTextComponent("The BEST crafting recipe in the game!"),
    //                                                        null, FrameType.TASK, false, false, false
    //                                                )
    //                                                .withRewards(AdvancementRewards.Builder.recipe(ID))
    //                                                .withCriterion("has_dirt", hasItem(Blocks.DIRT))
    //                                )
    //                )
    //                .build(consumer, ID);
    //    }

    // region HELPERS
    protected void generateStorageRecipes(DeferredRegisterCoFH<Item> reg, Consumer<IFinishedRecipe> consumer, Item storage, Item individual) {

        String storageName = storage.getRegistryName().getPath();
        String individualName = individual.getRegistryName().getPath();

        ShapedRecipeBuilder.shapedRecipe(storage)
                .key('#', individual)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("has_at_least_9_" + individualName, hasItem(MinMaxBounds.IntBound.atLeast(9), individual))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(individual, 9)
                .addIngredient(storage)
                .addCriterion("has_at_least_9_" + individualName, hasItem(MinMaxBounds.IntBound.atLeast(9), individual))
                .addCriterion("has_" + storageName, hasItem(storage))
                .build(consumer, this.modid + ":" + individualName + "_from_block");

    }

    protected void generateStorageRecipes(DeferredRegisterCoFH<Item> reg, Consumer<IFinishedRecipe> consumer, String type) {

        Item block = reg.get(type + "_block");
        Item ingot = reg.get(type + "_ingot");
        Item gem = reg.get(type + "_gem");
        Item nugget = reg.get(type + "_nugget");

        Item gear = reg.get(type + "_gear");

        if (block != null) {
            String blockName = block.getRegistryName().getPath();

            if (ingot != null) {
                String ingotName = ingot.getRegistryName().getPath();

                ShapedRecipeBuilder.shapedRecipe(block)
                        .key('#', ingot)
                        .patternLine("###")
                        .patternLine("###")
                        .patternLine("###")
                        .addCriterion("has_at_least_9_" + ingotName, hasItem(MinMaxBounds.IntBound.atLeast(9), ingot))
                        .build(consumer);

                ShapelessRecipeBuilder.shapelessRecipe(ingot, 9)
                        .addIngredient(block)
                        .addCriterion("has_at_least_9_" + ingotName, hasItem(MinMaxBounds.IntBound.atLeast(9), ingot))
                        .addCriterion("has_" + blockName, hasItem(block))
                        .build(consumer, this.modid + ":" + ingotName + "_from_block");

            } else if (gem != null) {
                String gemName = gem.getRegistryName().getPath();

                ShapedRecipeBuilder.shapedRecipe(block)
                        .key('#', gem)
                        .patternLine("###")
                        .patternLine("###")
                        .patternLine("###")
                        .addCriterion("has_at_least_9_" + gemName, hasItem(MinMaxBounds.IntBound.atLeast(9), gem))
                        .build(consumer);

                ShapelessRecipeBuilder.shapelessRecipe(gem, 9)
                        .addIngredient(block)
                        .addCriterion("has_at_least_9_" + gemName, hasItem(MinMaxBounds.IntBound.atLeast(9), gem))
                        .addCriterion("has_" + blockName, hasItem(block))
                        .build(consumer, this.modid + ":" + gemName + "_from_block");
            }
        }

        if (nugget != null) {
            String nuggetName = nugget.getRegistryName().getPath();

            if (ingot != null) {
                String ingotName = ingot.getRegistryName().getPath();

                ShapedRecipeBuilder.shapedRecipe(ingot)
                        .key('#', nugget)
                        .patternLine("###")
                        .patternLine("###")
                        .patternLine("###")
                        .addCriterion("has_at_least_9_" + nuggetName, hasItem(MinMaxBounds.IntBound.atLeast(9), nugget))
                        .build(consumer, this.modid + ":" + ingotName + "_from_nuggets");

                ShapelessRecipeBuilder.shapelessRecipe(nugget, 9)
                        .addIngredient(ingot)
                        .addCriterion("has_at_least_9_" + nuggetName, hasItem(MinMaxBounds.IntBound.atLeast(9), nugget))
                        .addCriterion("has_" + ingotName, hasItem(ingot))
                        .build(consumer, this.modid + ":" + nuggetName + "_from_ingot");
            }
            //            else if (gem != null) {
            //                String gemName = gem.getRegistryName().getPath();
            //
            //                ShapedRecipeBuilder.shapedRecipe(gem)
            //                        .key('#', nugget)
            //                        .patternLine("###")
            //                        .patternLine("###")
            //                        .patternLine("###")
            //                        .addCriterion("has_at_least_9_" + nuggetName, hasItem(MinMaxBounds.IntBound.atLeast(9), nugget))
            //                        .build(consumer, this.modid + ":" + gemName + "_from_nuggets");
            //
            //                ShapelessRecipeBuilder.shapelessRecipe(nugget, 9)
            //                        .addIngredient(gem)
            //                        .addCriterion("has_at_least_9_" + nuggetName, hasItem(MinMaxBounds.IntBound.atLeast(9), nugget))
            //                        .addCriterion("has_" + gemName, hasItem(gem))
            //                        .build(consumer, this.modid + ":" + nuggetName + "_from_gem");
            //            }
        }

        if (gear != null) {
            if (ingot != null) {
                ShapedRecipeBuilder.shapedRecipe(gear)
                        .key('#', ingot)
                        .key('i', Tags.Items.NUGGETS_IRON)
                        .patternLine(" # ")
                        .patternLine("#i#")
                        .patternLine(" # ")
                        .addCriterion("has_" + ingot.getRegistryName().getPath(), hasItem(ingot))
                        .build(consumer);
            }
            if (gem != null) {
                ShapedRecipeBuilder.shapedRecipe(gear)
                        .key('#', gem)
                        .key('i', Tags.Items.NUGGETS_IRON)
                        .patternLine(" # ")
                        .patternLine("#i#")
                        .patternLine(" # ")
                        .addCriterion("has_" + gem.getRegistryName().getPath(), hasItem(gem))
                        .build(consumer);
            }
        }
    }

    protected void generateGearRecipes(DeferredRegisterCoFH<Item> reg, Consumer<IFinishedRecipe> consumer, String type) {

        Item ingot = reg.get(type + "_ingot");
        Item gem = reg.get(type + "_gem");
        Item gear = reg.get(type + "_gear");

        if (gear == null) {
            return;
        }
        if (ingot != null) {
            ShapedRecipeBuilder.shapedRecipe(gear)
                    .key('#', ingot)
                    .key('i', Items.IRON_NUGGET)
                    .patternLine(" # ")
                    .patternLine("#i#")
                    .patternLine(" # ")
                    .addCriterion("has_" + ingot.getRegistryName().getPath(), hasItem(ingot))
                    .build(consumer);
        }
        if (gem != null) {
            ShapedRecipeBuilder.shapedRecipe(gear)
                    .key('#', gem)
                    .key('i', Items.IRON_NUGGET)
                    .patternLine(" # ")
                    .patternLine("#i#")
                    .patternLine(" # ")
                    .addCriterion("has_" + gem.getRegistryName().getPath(), hasItem(gem))
                    .build(consumer);
        }
    }

    protected void generateSmeltingAndBlastingRecipes(DeferredRegisterCoFH<Item> reg, Consumer<IFinishedRecipe> consumer, String type, float xp) {

        Item ore = reg.get(type + "_ore");
        Item ingot = reg.get(type + "_ingot");
        Item gem = reg.get(type + "_gem");
        Item nugget = reg.get(type + "_nugget");
        Item dust = reg.get(type + "_dust");

        if (ingot != null) {
            String ingotName = ingot.getRegistryName().getPath();

            if (dust != null) {
                String dustName = dust.getRegistryName().getPath();

                CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(dust), ingot, 0, 200)
                        .addCriterion("has_" + dustName, hasItem(dust))
                        .build(consumer, this.modid + ":" + ingotName + "_from_dust_smelting");

                CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(dust), ingot, 0, 100)
                        .addCriterion("has_" + dustName, hasItem(dust))
                        .build(consumer, this.modid + ":" + ingotName + "_from_dust_blasting");
            }

            if (ore != null) {
                String oreName = ore.getRegistryName().getPath();

                CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ore), ingot, xp, 200)
                        .addCriterion("has_" + oreName, hasItem(ore))
                        .build(consumer, this.modid + ":" + ingotName + "_from_ore_smelting");

                CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ore), ingot, xp, 100)
                        .addCriterion("has_" + oreName, hasItem(ore))
                        .build(consumer, this.modid + ":" + ingotName + "_from_ore_blasting");
            }
        } else if (gem != null) {
            String gemName = gem.getRegistryName().getPath();

            if (ore != null) {
                String oreName = ore.getRegistryName().getPath();

                CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ore), gem, xp, 200)
                        .addCriterion("has_" + oreName, hasItem(ore))
                        .build(consumer, gemName + "_from_ore_smelting");

                CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ore), gem, xp, 100)
                        .addCriterion("has_" + oreName, hasItem(ore))
                        .build(consumer, gemName + "_from_ore_blasting");
            }
        }
    }

    protected void generateSmeltingAndBlastingRecipes(DeferredRegisterCoFH<Item> reg, Consumer<IFinishedRecipe> consumer, Item input, Item output, float xp) {

        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(input), output, xp, 200)
                .addCriterion("has_" + input.getRegistryName().getPath(), hasItem(input))
                .build(consumer, this.modid + ":" + output.getRegistryName().getPath() + "_from_smelting");

        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(input), output, xp, 100)
                .addCriterion("has_" + input.getRegistryName().getPath(), hasItem(input))
                .build(consumer, this.modid + ":" + output.getRegistryName().getPath() + "_from_blasting");
    }

    @Override
    public EnterBlockTrigger.Instance enteredBlock(Block blockIn) {

        return new EnterBlockTrigger.Instance(blockIn, StatePropertiesPredicate.EMPTY);
    }

    @Override
    public InventoryChangeTrigger.Instance hasItem(IItemProvider itemIn) {

        return this.hasItem(ItemPredicate.Builder.create().item(itemIn).build());
    }

    @Override
    public InventoryChangeTrigger.Instance hasItem(Tag<Item> tagIn) {

        return this.hasItem(ItemPredicate.Builder.create().tag(tagIn).build());
    }

    @Override
    public InventoryChangeTrigger.Instance hasItem(ItemPredicate... predicates) {

        return new InventoryChangeTrigger.Instance(MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, predicates);
    }

    // TODO: Change if Mojang implements some better defaults...
    public InventoryChangeTrigger.Instance hasItem(MinMaxBounds.IntBound amount, IItemProvider itemIn) {

        return this.hasItem(new ItemPredicate(null, itemIn.asItem(), amount, MinMaxBounds.IntBound.UNBOUNDED, EnchantmentPredicate.field_226534_b_, EnchantmentPredicate.field_226534_b_, null, NBTPredicate.ANY)); // ItemPredicate.Builder.create().item(itemIn).count(amount).build());
    }
    // endregion

    // region CONDITIONS
    protected ICondition flagEnabled(FeatureManager manager, String flag) {

        return new FeatureRecipeCondition(manager, flag);
    }
    // endregion
}
