package cofh.thermal.core.data;

import cofh.lib.data.LootTableProviderCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraft.world.storage.loot.functions.SetCount;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.ThermalReferences.*;

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

        lootTables.put(BLOCKS.get(ID_SIGNALUM_BLOCK), createSelfDropTable(BLOCKS.get(ID_SIGNALUM_BLOCK)));
        lootTables.put(BLOCKS.get(ID_LUMIUM_BLOCK), createSelfDropTable(BLOCKS.get(ID_LUMIUM_BLOCK)));
        lootTables.put(BLOCKS.get(ID_ENDERIUM_BLOCK), createSelfDropTable(BLOCKS.get(ID_ENDERIUM_BLOCK)));

        lootTables.put(BLOCKS.get(ID_SULFUR_ORE), BlockLootTables.droppingWithSilkTouch(BLOCKS.get(ID_SULFUR_ORE), BlockLootTables.withExplosionDecay(BLOCKS.get(ID_SULFUR_ORE), ItemLootEntry.builder(ITEMS.get(ID_SULFUR_DUST)).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 5.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
        lootTables.put(BLOCKS.get(ID_NITER_ORE), BlockLootTables.droppingWithSilkTouch(BLOCKS.get(ID_NITER_ORE), BlockLootTables.withExplosionDecay(BLOCKS.get(ID_NITER_ORE), ItemLootEntry.builder(ITEMS.get(ID_NITER_DUST)).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 5.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));

    }

}
