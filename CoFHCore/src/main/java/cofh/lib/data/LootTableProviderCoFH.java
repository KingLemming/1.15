package cofh.lib.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.BlockStateProperty;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.MatchTool;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import net.minecraft.world.storage.loot.functions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static cofh.lib.util.constants.Constants.AGE;

public abstract class LootTableProviderCoFH extends LootTableProvider {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
    private final DataGenerator generator;

    public LootTableProviderCoFH(DataGenerator dataGeneratorIn) {

        super(dataGeneratorIn);
        this.generator = dataGeneratorIn;
    }

    protected abstract void addTables();

    protected LootTable.Builder createSilkTouchTable(String name, Block block, Item lootItem, float min, float max, int bonus) {

        LootPool.Builder builder = LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(block)
                        .acceptCondition(MatchTool.builder(ItemPredicate.Builder.create()
                                .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))))), ItemLootEntry.builder(lootItem)
                        .acceptFunction(SetCount.builder(new RandomValueRange(min, max)))
                        .acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE, bonus))
                        .acceptFunction(ExplosionDecay.builder())));
        return LootTable.builder().addLootPool(builder);
    }

    protected LootTable.Builder createSilkTouchOreTable(Block block, Item lootItem) {

        LootPool.Builder builder = LootPool.builder()
                .rolls(ConstantRange.of(1))
                .addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(block)
                        .acceptCondition(MatchTool.builder(ItemPredicate.Builder.create()
                                .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))))), ItemLootEntry.builder(lootItem)
                        .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))
                        .acceptFunction(ExplosionDecay.builder())));
        return LootTable.builder().addLootPool(builder);
    }

    protected LootTable.Builder createCropTable(Block block, Item crop, Item seed, IntegerProperty ageProp, int age) {

        ILootCondition.IBuilder harvestAge = BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(ageProp, age));
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .addEntry(ItemLootEntry.builder(crop)
                                .acceptCondition(harvestAge)
                                .alternatively(ItemLootEntry.builder(seed))))
                .addLootPool(LootPool.builder()
                        .acceptCondition(harvestAge)
                        .addEntry(ItemLootEntry.builder(seed)
                                // These are Mojang's numbers. No idea.
                                .acceptFunction(ApplyBonus.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286F, 3))))
                .acceptFunction(ExplosionDecay.builder());
    }

    protected LootTable.Builder createCropTable(Block block, Item crop, Item seed) {

        return createCropTable(block, crop, seed, AGE, 7);
    }

    protected LootTable.Builder createTuberTable(Block block, Item crop, IntegerProperty ageProp, int age) {

        ILootCondition.IBuilder harvestAge = BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(ageProp, age));
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .addEntry(ItemLootEntry.builder(crop)))
                .addLootPool(LootPool.builder()
                        .acceptCondition(harvestAge)
                        .addEntry(ItemLootEntry.builder(crop)
                                .acceptFunction(ApplyBonus.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286F, 3)))
                        .acceptFunction(ExplosionDecay.builder()));
    }

    protected LootTable.Builder createSimpleDropTable(Block block) {

        LootPool.Builder builder = LootPool.builder()
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block))
                .acceptCondition(SurvivesExplosion.builder());
        return LootTable.builder().addLootPool(builder);
    }

    protected LootTable.Builder createEmptyTable() {

        return LootTable.builder();
    }

    protected LootTable.Builder createStandardTileTable(String name, Block block) {

        LootPool.Builder builder = LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block)
                        .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
                        .acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY)
                                .addOperation("Info", "BlockEntityTag.Info", CopyNbt.Action.REPLACE)
                                .addOperation("Items", "BlockEntityTag.Items", CopyNbt.Action.REPLACE)
                                .addOperation("Energy", "BlockEntityTag.Energy", CopyNbt.Action.REPLACE))
                        .acceptFunction(SetContents.builder()
                                .addLootEntry(DynamicLootEntry.func_216162_a(new ResourceLocation("minecraft", "contents")))));
        return LootTable.builder().addLootPool(builder);
    }

    @Override
    public void act(DirectoryCache cache) {

        addTables();

        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
        }
        writeTables(cache, tables);
    }

    private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {

        Path outputFolder = this.generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
            } catch (IOException e) {
                LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

}

