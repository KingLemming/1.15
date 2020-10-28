package cofh.core.datagen;

import cofh.core.registries.DeferredRegisterCoFH;
import cofh.core.util.FlagManager;
import cofh.core.util.FlagRecipeCondition;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static cofh.core.util.constants.Constants.ID_FORGE;

public class RecipeProviderCoFH extends RecipeProvider implements IConditionBuilder {

    protected final String modid;
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

    @SafeVarargs
    protected final Ingredient fromTags(Tag<Item>... tagsIn) {

        List<Ingredient> ingredients = new ArrayList<>(tagsIn.length);
        for (Tag<Item> tag : tagsIn) {
            ingredients.add(Ingredient.fromTag(tag));
        }
        return new CompoundIngredientCoFH(ingredients);
    }

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
                .build(consumer, this.modid + ":storage/" + storageName);

        ShapelessRecipeBuilder.shapelessRecipe(individual, 9)
                .addIngredient(storage)
                .addCriterion("has_at_least_9_" + individualName, hasItem(MinMaxBounds.IntBound.atLeast(9), individual))
                .addCriterion("has_" + storageName, hasItem(storage))
                .build(consumer, this.modid + ":storage/" + individualName + "_from_block");
    }

    protected void generateStorageRecipes(DeferredRegisterCoFH<Item> reg, Consumer<IFinishedRecipe> consumer, String type) {

        Item block = reg.get(type + "_block");
        Item ingot = reg.get(type + "_ingot");
        Item gem = reg.get(type);
        Item nugget = reg.get(type + "_nugget");

        if (block != null) {
            if (ingot != null) {
                generateStorageRecipes(reg, consumer, block, ingot);
            } else if (gem != null) {
                generateStorageRecipes(reg, consumer, block, gem);
            }
        }
        if (nugget != null) {
            String nuggetName = nugget.getRegistryName().getPath();
            if (ingot != null) {
                generateStorageRecipes(reg, consumer, ingot, nugget);
                String ingotName = ingot.getRegistryName().getPath();
                ShapedRecipeBuilder.shapedRecipe(ingot)
                        .key('#', nugget)
                        .patternLine("###")
                        .patternLine("###")
                        .patternLine("###")
                        .addCriterion("has_at_least_9_" + nuggetName, hasItem(MinMaxBounds.IntBound.atLeast(9), nugget))
                        .build(consumer, this.modid + ":storage/" + ingotName + "_from_nuggets");

                ShapelessRecipeBuilder.shapelessRecipe(nugget, 9)
                        .addIngredient(ingot)
                        .addCriterion("has_at_least_9_" + nuggetName, hasItem(MinMaxBounds.IntBound.atLeast(9), nugget))
                        .addCriterion("has_" + ingotName, hasItem(ingot))
                        .build(consumer, this.modid + ":storage/" + nuggetName + "_from_ingot");
            }
        }
        generateGearRecipe(reg, consumer, type);
    }

    protected void generateGearRecipe(DeferredRegisterCoFH<Item> reg, Consumer<IFinishedRecipe> consumer, String type) {

        Item gear = reg.get(type + "_gear");
        if (gear == null) {
            return;
        }
        Item ingot = reg.get(type + "_ingot");
        Item gem = reg.get(type);

        Tag<Item> ingotTag = forgeTag("ingots/" + type);
        Tag<Item> gemTag = forgeTag("gems/" + type);

        if (ingot != null) {
            ShapedRecipeBuilder.shapedRecipe(gear)
                    .key('#', ingotTag)
                    .key('i', Tags.Items.NUGGETS_IRON)
                    .patternLine(" # ")
                    .patternLine("#i#")
                    .patternLine(" # ")
                    .addCriterion("has_" + ingot.getRegistryName().getPath(), hasItem(ingotTag))
                    .build(consumer, this.modid + ":parts/" + gear.getRegistryName().getPath());
        }
        if (gem != null) {
            ShapedRecipeBuilder.shapedRecipe(gear)
                    .key('#', gemTag)
                    .key('i', Tags.Items.NUGGETS_IRON)
                    .patternLine(" # ")
                    .patternLine("#i#")
                    .patternLine(" # ")
                    .addCriterion("has_" + gem.getRegistryName().getPath(), hasItem(gemTag))
                    .build(consumer, this.modid + ":parts/" + gear.getRegistryName().getPath());
        }
    }

    protected void generateGearRecipe(Consumer<IFinishedRecipe> consumer, Item gear, Item material, Tag<Item> tag) {

        if (gear == null || material == null || tag == null) {
            return;
        }
        ShapedRecipeBuilder.shapedRecipe(gear)
                .key('#', tag)
                .key('i', Tags.Items.NUGGETS_IRON)
                .patternLine(" # ")
                .patternLine("#i#")
                .patternLine(" # ")
                .addCriterion("has_" + material.getRegistryName().getPath(), hasItem(tag))
                .build(consumer, this.modid + ":parts/" + gear.getRegistryName().getPath());
    }

    protected void generateSmeltingAndBlastingRecipes(DeferredRegisterCoFH<Item> reg, Consumer<IFinishedRecipe> consumer, String material, float xp) {

        generateSmeltingAndBlastingRecipes(reg, consumer, material, xp, "smelting");
    }

    protected void generateSmeltingAndBlastingRecipes(DeferredRegisterCoFH<Item> reg, Consumer<IFinishedRecipe> consumer, String material, float xp, String folder) {

        Item ore = reg.get(material + "_ore");
        Item ingot = reg.get(material + "_ingot");
        Item gem = reg.get(material);
        Item nugget = reg.get(material + "_nugget");
        Item dust = reg.get(material + "_dust");

        if (ingot != null) {
            if (dust != null) {
                generateSmeltingAndBlastingRecipes(reg, consumer, dust, ingot, 0, folder, "_dust");
            }
            if (ore != null) {
                generateSmeltingAndBlastingRecipes(reg, consumer, ore, ingot, xp, folder, "_ore");
            }
        } else if (gem != null) {
            if (ore != null) {
                generateSmeltingAndBlastingRecipes(reg, consumer, ore, gem, xp, folder, "_ore");
            }
        }
    }

    protected void generateSmeltingAndBlastingRecipes(DeferredRegisterCoFH<Item> reg, Consumer<IFinishedRecipe> consumer, Item input, Item output, float xp) {

        generateSmeltingAndBlastingRecipes(reg, consumer, input, output, xp, "", "");
    }

    protected void generateSmeltingAndBlastingRecipes(DeferredRegisterCoFH<Item> reg, Consumer<IFinishedRecipe> consumer, Item input, Item output, float xp, String folder) {

        generateSmeltingAndBlastingRecipes(reg, consumer, input, output, xp, folder, "");
    }

    protected void generateSmeltingAndBlastingRecipes(DeferredRegisterCoFH<Item> reg, Consumer<IFinishedRecipe> consumer, Item input, Item output, float xp, String folder, String type) {

        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(input), output, xp, 200)
                .addCriterion("has_" + input.getRegistryName().getPath(), hasItem(input))
                .build(consumer, this.modid + ":" + folder + "/" + output.getRegistryName().getPath() + "_from" + type + "_smelting");

        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(input), output, xp, 100)
                .addCriterion("has_" + input.getRegistryName().getPath(), hasItem(input))
                .build(consumer, this.modid + ":" + folder + "/" + output.getRegistryName().getPath() + "_from" + type + "_blasting");
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

    protected static Tag<Item> forgeTag(String name) {

        return new ItemTags.Wrapper(new ResourceLocation(ID_FORGE, name));
    }
    // endregion

    // region CONDITIONS
    protected ICondition flagEnabled(FlagManager manager, String flag) {

        return new FlagRecipeCondition(manager, flag);
    }
    // endregion

    protected static class CompoundIngredientCoFH extends CompoundIngredient {

        public CompoundIngredientCoFH(List<Ingredient> children) {

            super(children);
        }

    }

}
