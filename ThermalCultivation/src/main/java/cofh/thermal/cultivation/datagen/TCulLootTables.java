package cofh.thermal.cultivation.datagen;

import cofh.core.datagen.LootTableProviderCoFH;
import cofh.core.registries.DeferredRegisterCoFH;
import cofh.thermal.core.util.loot.TileNBTSync;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraft.world.storage.loot.functions.LimitCount;
import net.minecraft.world.storage.loot.functions.SetCount;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.util.RegistrationHelper.block;
import static cofh.thermal.core.util.RegistrationHelper.seeds;
import static cofh.thermal.cultivation.init.TCulIDs.*;

public class TCulLootTables extends LootTableProviderCoFH {

    public TCulLootTables(DataGenerator dataGeneratorIn) {

        super(dataGeneratorIn);
    }

    @Override
    public String getName() {

        return "Thermal Cultivation: Loot Tables";
    }

    @Override
    protected void addTables() {

        DeferredRegisterCoFH<Block> regBlocks = BLOCKS;
        DeferredRegisterCoFH<Item> regItems = ITEMS;

        createDefaultCropTable(ID_BARLEY);
        createDefaultCropTable(ID_ONION);
        createDefaultCropTable(ID_RADISH);
        createDefaultCropTable(ID_RICE);
        createDefaultCropTable(ID_SADIROOT);
        createDefaultCropTable(ID_SPINACH);

        createDefaultCropTable(ID_BELL_PEPPER);
        createDefaultCropTable(ID_EGGPLANT);
        createDefaultCropTable(ID_GREEN_BEAN);
        createDefaultCropTable(ID_PEANUT);
        createDefaultCropTable(ID_STRAWBERRY);
        createDefaultCropTable(ID_TOMATO);

        createDefaultCropTable(ID_COFFEE);
        createDefaultCropTable(ID_TEA);

        lootTables.put(regBlocks.get(ID_FROST_MELON),
                BlockLootTables.droppingWithSilkTouch(regBlocks.get(ID_FROST_MELON),
                        BlockLootTables.withExplosionDecay(regBlocks.get(ID_FROST_MELON),
                                ItemLootEntry.builder(regItems.get(ID_FROST_MELON_SLICE))
                                        .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 7.0F)))
                                        .acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))
                                        .acceptFunction(LimitCount.func_215911_a(IntClamper.func_215851_b(9))))));

        lootTables.put(regBlocks.get(ID_FROST_MELON_STEM),
                BlockLootTables.droppingByAge(regBlocks.get(ID_FROST_MELON_STEM),
                        regItems.get(seeds(ID_FROST_MELON))));

        lootTables.put(regBlocks.get(ID_FROST_MELON_STEM_ATTACHED),
                BlockLootTables.func_229435_c_(regBlocks.get(ID_FROST_MELON_STEM),
                        regItems.get(seeds(ID_FROST_MELON))));

        lootTables.put(regBlocks.get(ID_PHYTOSOIL), createSimpleDropTable(regBlocks.get(ID_PHYTOSOIL)));
        lootTables.put(regBlocks.get(ID_PHYTOSOIL_TILLED), createSimpleDropTable(regBlocks.get(ID_PHYTOSOIL)));

        lootTables.put(regBlocks.get(block(ID_BARLEY)), createSimpleDropTable(regBlocks.get(block(ID_BARLEY))));
        lootTables.put(regBlocks.get(block(ID_BELL_PEPPER)), createSimpleDropTable(regBlocks.get(block(ID_BELL_PEPPER))));
        lootTables.put(regBlocks.get(block(ID_COFFEE)), createSimpleDropTable(regBlocks.get(block(ID_COFFEE))));
        lootTables.put(regBlocks.get(block(ID_CORN)), createSimpleDropTable(regBlocks.get(block(ID_CORN))));
        lootTables.put(regBlocks.get(block(ID_EGGPLANT)), createSimpleDropTable(regBlocks.get(block(ID_EGGPLANT))));
        lootTables.put(regBlocks.get(block(ID_GREEN_BEAN)), createSimpleDropTable(regBlocks.get(block(ID_GREEN_BEAN))));
        lootTables.put(regBlocks.get(block(ID_HOPS)), createSimpleDropTable(regBlocks.get(block(ID_HOPS))));
        lootTables.put(regBlocks.get(block(ID_ONION)), createSimpleDropTable(regBlocks.get(block(ID_ONION))));
        lootTables.put(regBlocks.get(block(ID_PEANUT)), createSimpleDropTable(regBlocks.get(block(ID_PEANUT))));
        lootTables.put(regBlocks.get(block(ID_RADISH)), createSimpleDropTable(regBlocks.get(block(ID_RADISH))));
        lootTables.put(regBlocks.get(block(ID_RICE)), createSimpleDropTable(regBlocks.get(block(ID_RICE))));
        lootTables.put(regBlocks.get(block(ID_SADIROOT)), createSimpleDropTable(regBlocks.get(block(ID_SADIROOT))));
        lootTables.put(regBlocks.get(block(ID_SPINACH)), createSimpleDropTable(regBlocks.get(block(ID_SPINACH))));
        lootTables.put(regBlocks.get(block(ID_STRAWBERRY)), createSimpleDropTable(regBlocks.get(block(ID_STRAWBERRY))));
        lootTables.put(regBlocks.get(block(ID_TEA)), createSimpleDropTable(regBlocks.get(block(ID_TEA))));
        lootTables.put(regBlocks.get(block(ID_TOMATO)), createSimpleDropTable(regBlocks.get(block(ID_TOMATO))));

        lootTables.put(regBlocks.get(ID_CHOCOLATE_CAKE), createEmptyTable());
        lootTables.put(regBlocks.get(ID_SPICE_CAKE), createEmptyTable());

        lootTables.put(regBlocks.get(ID_DEVICE_SOIL_INFUSER), createSyncDropTable(regBlocks.get(ID_DEVICE_SOIL_INFUSER)));
    }

    protected void createDefaultCropTable(String id) {

        lootTables.put(BLOCKS.get(id), createCropTable(BLOCKS.get(id), ITEMS.get(id), ITEMS.get(seeds(id))));
    }

    protected LootTable.Builder createSyncDropTable(Block block) {

        LootPool.Builder builder = LootPool.builder()
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block)
                        .acceptFunction(TileNBTSync.builder()))
                .acceptCondition(SurvivesExplosion.builder());
        return LootTable.builder().addLootPool(builder);
    }

}
