package cofh.thermal.cultivation.data;

import cofh.lib.data.LootTableProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.IntClamper;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraft.world.storage.loot.functions.LimitCount;
import net.minecraft.world.storage.loot.functions.SetCount;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.util.RegistrationHelper.seeds;
import static cofh.thermal.cultivation.init.TCulReferences.*;

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

        registerDefaultCropTable(ID_BARLEY);
        registerDefaultCropTable(ID_ONION);
        registerDefaultCropTable(ID_RADISH);
        registerDefaultCropTable(ID_RICE);
        registerDefaultCropTable(ID_SADIROOT);
        registerDefaultCropTable(ID_SPINACH);

        registerDefaultCropTable(ID_BELL_PEPPER);
        registerDefaultCropTable(ID_EGGPLANT);
        registerDefaultCropTable(ID_GREEN_BEAN);
        registerDefaultCropTable(ID_PEANUT);
        registerDefaultCropTable(ID_STRAWBERRY);
        registerDefaultCropTable(ID_TOMATO);

        registerDefaultCropTable(ID_COFFEE);
        registerDefaultCropTable(ID_TEA);

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
        lootTables.put(regBlocks.get(ID_PHYTOSOIL_CHARGED), createSimpleDropTable(regBlocks.get(ID_PHYTOSOIL)));
    }

    protected void registerDefaultCropTable(String id) {

        lootTables.put(BLOCKS.get(id), createCropTable(BLOCKS.get(id), ITEMS.get(id), ITEMS.get(seeds(id))));
    }

}
