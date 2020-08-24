package cofh.thermal.core.datagen;

import cofh.lib.datagen.LootTableProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraft.world.storage.loot.functions.SetCount;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.TCoreReferences.*;

public class TCoreLootTables extends LootTableProviderCoFH {

    public TCoreLootTables(DataGenerator dataGeneratorIn) {

        super(dataGeneratorIn);
    }

    @Override
    public String getName() {

        return "Thermal Core: Loot Tables";
    }

    @Override
    protected void addTables() {

        DeferredRegisterCoFH<Block> regBlocks = BLOCKS;
        DeferredRegisterCoFH<Item> regItems = ITEMS;

        lootTables.put(regBlocks.get(ID_BAMBOO_BLOCK), createSimpleDropTable(regBlocks.get(ID_BAMBOO_BLOCK)));
        lootTables.put(regBlocks.get(ID_CHARCOAL_BLOCK), createSimpleDropTable(regBlocks.get(ID_CHARCOAL_BLOCK)));
        lootTables.put(regBlocks.get(ID_GUNPOWDER_BLOCK), createSimpleDropTable(regBlocks.get(ID_GUNPOWDER_BLOCK)));
        lootTables.put(regBlocks.get(ID_SUGAR_CANE_BLOCK), createSimpleDropTable(regBlocks.get(ID_SUGAR_CANE_BLOCK)));

        lootTables.put(regBlocks.get(ID_APPLE_BLOCK), createSimpleDropTable(regBlocks.get(ID_APPLE_BLOCK)));
        lootTables.put(regBlocks.get(ID_BEETROOT_BLOCK), createSimpleDropTable(regBlocks.get(ID_BEETROOT_BLOCK)));
        lootTables.put(regBlocks.get(ID_CARROT_BLOCK), createSimpleDropTable(regBlocks.get(ID_CARROT_BLOCK)));
        lootTables.put(regBlocks.get(ID_POTATO_BLOCK), createSimpleDropTable(regBlocks.get(ID_POTATO_BLOCK)));

        lootTables.put(regBlocks.get(ID_APATITE_BLOCK), createSimpleDropTable(regBlocks.get(ID_APATITE_BLOCK)));
        lootTables.put(regBlocks.get(ID_CINNABAR_BLOCK), createSimpleDropTable(regBlocks.get(ID_CINNABAR_BLOCK)));
        lootTables.put(regBlocks.get(ID_NITER_BLOCK), createSimpleDropTable(regBlocks.get(ID_NITER_BLOCK)));
        lootTables.put(regBlocks.get(ID_SULFUR_BLOCK), createSimpleDropTable(regBlocks.get(ID_SULFUR_BLOCK)));

        lootTables.put(regBlocks.get(ID_COPPER_ORE), createSimpleDropTable(regBlocks.get(ID_COPPER_ORE)));
        lootTables.put(regBlocks.get(ID_LEAD_ORE), createSimpleDropTable(regBlocks.get(ID_LEAD_ORE)));
        lootTables.put(regBlocks.get(ID_NICKEL_ORE), createSimpleDropTable(regBlocks.get(ID_NICKEL_ORE)));
        lootTables.put(regBlocks.get(ID_SILVER_ORE), createSimpleDropTable(regBlocks.get(ID_SILVER_ORE)));
        lootTables.put(regBlocks.get(ID_TIN_ORE), createSimpleDropTable(regBlocks.get(ID_TIN_ORE)));

        lootTables.put(regBlocks.get(ID_RUBY_ORE), createSilkTouchOreTable(regBlocks.get(ID_RUBY_ORE), regItems.get("ruby_gem")));
        lootTables.put(regBlocks.get(ID_SAPPHIRE_ORE), createSilkTouchOreTable(regBlocks.get(ID_SAPPHIRE_ORE), regItems.get("sapphire_gem")));

        lootTables.put(regBlocks.get(ID_COPPER_BLOCK), createSimpleDropTable(regBlocks.get(ID_COPPER_BLOCK)));
        lootTables.put(regBlocks.get(ID_LEAD_BLOCK), createSimpleDropTable(regBlocks.get(ID_LEAD_BLOCK)));
        lootTables.put(regBlocks.get(ID_NICKEL_BLOCK), createSimpleDropTable(regBlocks.get(ID_NICKEL_BLOCK)));
        lootTables.put(regBlocks.get(ID_SILVER_BLOCK), createSimpleDropTable(regBlocks.get(ID_SILVER_BLOCK)));
        lootTables.put(regBlocks.get(ID_TIN_BLOCK), createSimpleDropTable(regBlocks.get(ID_TIN_BLOCK)));

        lootTables.put(regBlocks.get(ID_BRONZE_BLOCK), createSimpleDropTable(regBlocks.get(ID_BRONZE_BLOCK)));
        lootTables.put(regBlocks.get(ID_CONSTANTAN_BLOCK), createSimpleDropTable(regBlocks.get(ID_CONSTANTAN_BLOCK)));
        lootTables.put(regBlocks.get(ID_ELECTRUM_BLOCK), createSimpleDropTable(regBlocks.get(ID_ELECTRUM_BLOCK)));
        lootTables.put(regBlocks.get(ID_INVAR_BLOCK), createSimpleDropTable(regBlocks.get(ID_INVAR_BLOCK)));

        lootTables.put(regBlocks.get(ID_RUBY_BLOCK), createSimpleDropTable(regBlocks.get(ID_RUBY_BLOCK)));
        lootTables.put(regBlocks.get(ID_SAPPHIRE_BLOCK), createSimpleDropTable(regBlocks.get(ID_SAPPHIRE_BLOCK)));

        lootTables.put(regBlocks.get(ID_SAWDUST_BLOCK), createSimpleDropTable(regBlocks.get(ID_SAWDUST_BLOCK)));
        lootTables.put(regBlocks.get(ID_ROSIN_BLOCK), createSimpleDropTable(regBlocks.get(ID_ROSIN_BLOCK)));
        lootTables.put(regBlocks.get(ID_RUBBER_BLOCK), createSimpleDropTable(regBlocks.get(ID_RUBBER_BLOCK)));
        lootTables.put(regBlocks.get(ID_CURED_RUBBER_BLOCK), createSimpleDropTable(regBlocks.get(ID_CURED_RUBBER_BLOCK)));
        lootTables.put(regBlocks.get(ID_SLAG_BLOCK), createSimpleDropTable(regBlocks.get(ID_SLAG_BLOCK)));
        lootTables.put(regBlocks.get(ID_RICH_SLAG_BLOCK), createSimpleDropTable(regBlocks.get(ID_RICH_SLAG_BLOCK)));

        lootTables.put(regBlocks.get(ID_ENDERIUM_BLOCK), createSimpleDropTable(regBlocks.get(ID_ENDERIUM_BLOCK)));
        lootTables.put(regBlocks.get(ID_LUMIUM_BLOCK), createSimpleDropTable(regBlocks.get(ID_LUMIUM_BLOCK)));
        lootTables.put(regBlocks.get(ID_SIGNALUM_BLOCK), createSimpleDropTable(regBlocks.get(ID_SIGNALUM_BLOCK)));

        lootTables.put(regBlocks.get(ID_ENDERIUM_GLASS), BlockLootTables.onlyWithSilkTouch(regBlocks.get(ID_ENDERIUM_GLASS)));
        lootTables.put(regBlocks.get(ID_LUMIUM_GLASS), BlockLootTables.onlyWithSilkTouch(regBlocks.get(ID_LUMIUM_GLASS)));
        lootTables.put(regBlocks.get(ID_SIGNALUM_GLASS), BlockLootTables.onlyWithSilkTouch(regBlocks.get(ID_SIGNALUM_GLASS)));

        lootTables.put(regBlocks.get(ID_PHYTO_TNT), createSimpleDropTable(regBlocks.get(ID_PHYTO_TNT)));

        lootTables.put(regBlocks.get(ID_APATITE_ORE), BlockLootTables.droppingWithSilkTouch(regBlocks.get(ID_APATITE_ORE), BlockLootTables.withExplosionDecay(regBlocks.get(ID_APATITE_ORE), ItemLootEntry.builder(regItems.get("apatite"))
                .acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F)))
                .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));
        lootTables.put(regBlocks.get(ID_CINNABAR_ORE), BlockLootTables.droppingWithSilkTouch(regBlocks.get(ID_CINNABAR_ORE), BlockLootTables.withExplosionDecay(regBlocks.get(ID_CINNABAR_ORE), ItemLootEntry.builder(regItems.get("cinnabar"))
                .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F)))
                .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));
        lootTables.put(regBlocks.get(ID_NITER_ORE), BlockLootTables.droppingWithSilkTouch(regBlocks.get(ID_NITER_ORE), BlockLootTables.withExplosionDecay(regBlocks.get(ID_NITER_ORE), ItemLootEntry.builder(regItems.get("niter"))
                .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 5.0F)))
                .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));
        lootTables.put(regBlocks.get(ID_SULFUR_ORE), BlockLootTables.droppingWithSilkTouch(regBlocks.get(ID_SULFUR_ORE), BlockLootTables.withExplosionDecay(regBlocks.get(ID_SULFUR_ORE), ItemLootEntry.builder(regItems.get("sulfur"))
                .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 5.0F)))
                .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));

    }

}
