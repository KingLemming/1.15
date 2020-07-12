package cofh.thermal.core.data;

import cofh.lib.util.references.CoFHTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.data.ItemTagsProvider;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.TCoreReferences.*;

public class TCoreTags {

    public static class Block extends BlockTagsProvider {

        public Block(DataGenerator dataGeneratorIn) {

            super(dataGeneratorIn);
        }

        @Override
        public String getName() {

            return "Thermal Core: Block Tags";
        }

        @Override
        protected void registerTags() {

            getBuilder(CoFHTags.Blocks.ORES_APATITE).add(BLOCKS.get(ID_APATITE_ORE));
            getBuilder(CoFHTags.Blocks.ORES_CINNABAR).add(BLOCKS.get(ID_CINNABAR_ORE));
            getBuilder(CoFHTags.Blocks.ORES_COPPER).add(BLOCKS.get(ID_COPPER_ORE));
            getBuilder(CoFHTags.Blocks.ORES_LEAD).add(BLOCKS.get(ID_LEAD_ORE));
            getBuilder(CoFHTags.Blocks.ORES_NICKEL).add(BLOCKS.get(ID_NICKEL_ORE));
            getBuilder(CoFHTags.Blocks.ORES_NITER).add(BLOCKS.get(ID_NITER_ORE));
            getBuilder(CoFHTags.Blocks.ORES_RUBY).add(BLOCKS.get(ID_RUBY_ORE));
            getBuilder(CoFHTags.Blocks.ORES_SAPPHIRE).add(BLOCKS.get(ID_SAPPHIRE_ORE));
            getBuilder(CoFHTags.Blocks.ORES_SILVER).add(BLOCKS.get(ID_SILVER_ORE));
            getBuilder(CoFHTags.Blocks.ORES_SULFUR).add(BLOCKS.get(ID_SULFUR_ORE));

            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_APATITE).add(BLOCKS.get(ID_APATITE_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_BAMBOO).add(BLOCKS.get(ID_BAMBOO_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_CHARCOAL).add(BLOCKS.get(ID_CHARCOAL_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_CINNABAR).add(BLOCKS.get(ID_CINNABAR_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_CONSTANTAN).add(BLOCKS.get(ID_CONSTANTAN_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_COPPER).add(BLOCKS.get(ID_COPPER_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_ELECTRUM).add(BLOCKS.get(ID_ELECTRUM_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_ENDERIUM).add(BLOCKS.get(ID_ENDERIUM_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_INVAR).add(BLOCKS.get(ID_INVAR_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_LEAD).add(BLOCKS.get(ID_LEAD_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_LUMIUM).add(BLOCKS.get(ID_LUMIUM_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_NICKEL).add(BLOCKS.get(ID_NICKEL_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_NITER).add(BLOCKS.get(ID_NITER_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_RUBY).add(BLOCKS.get(ID_RUBY_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_SAPPHIRE).add(BLOCKS.get(ID_SAPPHIRE_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_SIGNALUM).add(BLOCKS.get(ID_SIGNALUM_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_SILVER).add(BLOCKS.get(ID_SILVER_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_SUGAR_CANE).add(BLOCKS.get(ID_SUGAR_CANE_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_SULFUR).add(BLOCKS.get(ID_SULFUR_BLOCK));
        }

    }

    public static class Item extends ItemTagsProvider {

        public Item(DataGenerator dataGeneratorIn) {

            super(dataGeneratorIn);
        }

        @Override
        public String getName() {

            return "Thermal Core: Item Tags";
        }

        @Override
        protected void registerTags() {

            copy(CoFHTags.Blocks.ORES_APATITE, CoFHTags.Items.ORES_APATITE);
            copy(CoFHTags.Blocks.ORES_CINNABAR, CoFHTags.Items.ORES_CINNABAR);
            copy(CoFHTags.Blocks.ORES_COPPER, CoFHTags.Items.ORES_COPPER);
            copy(CoFHTags.Blocks.ORES_LEAD, CoFHTags.Items.ORES_LEAD);
            copy(CoFHTags.Blocks.ORES_NICKEL, CoFHTags.Items.ORES_NICKEL);
            copy(CoFHTags.Blocks.ORES_NITER, CoFHTags.Items.ORES_NITER);
            copy(CoFHTags.Blocks.ORES_RUBY, CoFHTags.Items.ORES_RUBY);
            copy(CoFHTags.Blocks.ORES_SAPPHIRE, CoFHTags.Items.ORES_SAPPHIRE);
            copy(CoFHTags.Blocks.ORES_SILVER, CoFHTags.Items.ORES_SILVER);
            copy(CoFHTags.Blocks.ORES_SULFUR, CoFHTags.Items.ORES_SULFUR);

            copy(CoFHTags.Blocks.STORAGE_BLOCKS_APATITE, CoFHTags.Items.STORAGE_BLOCKS_APATITE);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_BAMBOO, CoFHTags.Items.STORAGE_BLOCKS_BAMBOO);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_CHARCOAL, CoFHTags.Items.STORAGE_BLOCKS_CHARCOAL);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_CINNABAR, CoFHTags.Items.STORAGE_BLOCKS_CINNABAR);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_CONSTANTAN, CoFHTags.Items.STORAGE_BLOCKS_CONSTANTAN);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_COPPER, CoFHTags.Items.STORAGE_BLOCKS_COPPER);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_ELECTRUM, CoFHTags.Items.STORAGE_BLOCKS_ELECTRUM);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_ENDERIUM, CoFHTags.Items.STORAGE_BLOCKS_ENDERIUM);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_INVAR, CoFHTags.Items.STORAGE_BLOCKS_INVAR);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_LEAD, CoFHTags.Items.STORAGE_BLOCKS_LEAD);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_LUMIUM, CoFHTags.Items.STORAGE_BLOCKS_LUMIUM);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_NICKEL, CoFHTags.Items.STORAGE_BLOCKS_NICKEL);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_NITER, CoFHTags.Items.STORAGE_BLOCKS_NITER);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_RUBY, CoFHTags.Items.STORAGE_BLOCKS_RUBY);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_SAPPHIRE, CoFHTags.Items.STORAGE_BLOCKS_SAPPHIRE);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_SIGNALUM, CoFHTags.Items.STORAGE_BLOCKS_SIGNALUM);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_SILVER, CoFHTags.Items.STORAGE_BLOCKS_SILVER);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_SUGAR_CANE, CoFHTags.Items.STORAGE_BLOCKS_SUGAR_CANE);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_SULFUR, CoFHTags.Items.STORAGE_BLOCKS_SULFUR);

            getBuilder(CoFHTags.Items.COINS_CONSTANTAN).add(ITEMS.get("constantan_coin"));
            getBuilder(CoFHTags.Items.COINS_COPPER).add(ITEMS.get("copper_coin"));
            getBuilder(CoFHTags.Items.COINS_ELECTRUM).add(ITEMS.get("electrum_coin"));
            getBuilder(CoFHTags.Items.COINS_ENDERIUM).add(ITEMS.get("enderium_coin"));
            getBuilder(CoFHTags.Items.COINS_GOLD).add(ITEMS.get("gold_coin"));
            getBuilder(CoFHTags.Items.COINS_INVAR).add(ITEMS.get("invar_coin"));
            getBuilder(CoFHTags.Items.COINS_IRON).add(ITEMS.get("iron_coin"));
            getBuilder(CoFHTags.Items.COINS_LEAD).add(ITEMS.get("lead_coin"));
            getBuilder(CoFHTags.Items.COINS_LUMIUM).add(ITEMS.get("lumium_coin"));
            getBuilder(CoFHTags.Items.COINS_NICKEL).add(ITEMS.get("nickel_coin"));
            getBuilder(CoFHTags.Items.COINS_SIGNALUM).add(ITEMS.get("signalum_coin"));
            getBuilder(CoFHTags.Items.COINS_SILVER).add(ITEMS.get("silver_coin"));

            getBuilder(CoFHTags.Items.DUSTS_CONSTANTAN).add(ITEMS.get("constantan_dust"));
            getBuilder(CoFHTags.Items.DUSTS_COPPER).add(ITEMS.get("copper_dust"));
            getBuilder(CoFHTags.Items.DUSTS_ELECTRUM).add(ITEMS.get("electrum_dust"));
            getBuilder(CoFHTags.Items.DUSTS_ENDERIUM).add(ITEMS.get("enderium_dust"));
            getBuilder(CoFHTags.Items.DUSTS_GOLD).add(ITEMS.get("gold_dust"));
            getBuilder(CoFHTags.Items.DUSTS_INVAR).add(ITEMS.get("invar_dust"));
            getBuilder(CoFHTags.Items.DUSTS_IRON).add(ITEMS.get("iron_dust"));
            getBuilder(CoFHTags.Items.DUSTS_LEAD).add(ITEMS.get("lead_dust"));
            getBuilder(CoFHTags.Items.DUSTS_LUMIUM).add(ITEMS.get("lumium_dust"));
            getBuilder(CoFHTags.Items.DUSTS_NICKEL).add(ITEMS.get("nickel_dust"));
            getBuilder(CoFHTags.Items.DUSTS_RUBY).add(ITEMS.get("ruby_dust"));
            getBuilder(CoFHTags.Items.DUSTS_SAPPHIRE).add(ITEMS.get("sapphire_dust"));
            getBuilder(CoFHTags.Items.DUSTS_SIGNALUM).add(ITEMS.get("signalum_dust"));
            getBuilder(CoFHTags.Items.DUSTS_SILVER).add(ITEMS.get("silver_dust"));

            getBuilder(CoFHTags.Items.GEARS_CONSTANTAN).add(ITEMS.get("constantan_gear"));
            getBuilder(CoFHTags.Items.GEARS_COPPER).add(ITEMS.get("copper_gear"));
            getBuilder(CoFHTags.Items.GEARS_ELECTRUM).add(ITEMS.get("electrum_gear"));
            getBuilder(CoFHTags.Items.GEARS_ENDERIUM).add(ITEMS.get("enderium_gear"));
            getBuilder(CoFHTags.Items.GEARS_GOLD).add(ITEMS.get("gold_gear"));
            getBuilder(CoFHTags.Items.GEARS_INVAR).add(ITEMS.get("invar_gear"));
            getBuilder(CoFHTags.Items.GEARS_IRON).add(ITEMS.get("iron_gear"));
            getBuilder(CoFHTags.Items.GEARS_LEAD).add(ITEMS.get("lead_gear"));
            getBuilder(CoFHTags.Items.GEARS_LUMIUM).add(ITEMS.get("lumium_gear"));
            getBuilder(CoFHTags.Items.GEARS_NICKEL).add(ITEMS.get("nickel_gear"));
            getBuilder(CoFHTags.Items.GEARS_RUBY).add(ITEMS.get("ruby_gear"));
            getBuilder(CoFHTags.Items.GEARS_SAPPHIRE).add(ITEMS.get("sapphire_gear"));
            getBuilder(CoFHTags.Items.GEARS_SIGNALUM).add(ITEMS.get("signalum_gear"));
            getBuilder(CoFHTags.Items.GEARS_SILVER).add(ITEMS.get("silver_gear"));

            getBuilder(CoFHTags.Items.GEMS_RUBY).add(ITEMS.get("ruby_gem"));
            getBuilder(CoFHTags.Items.GEMS_SAPPHIRE).add(ITEMS.get("sapphire_gem"));

            getBuilder(CoFHTags.Items.INGOTS_CONSTANTAN).add(ITEMS.get("constantan_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_COPPER).add(ITEMS.get("copper_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_ELECTRUM).add(ITEMS.get("electrum_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_ENDERIUM).add(ITEMS.get("enderium_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_INVAR).add(ITEMS.get("invar_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_LEAD).add(ITEMS.get("lead_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_LUMIUM).add(ITEMS.get("lumium_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_NICKEL).add(ITEMS.get("nickel_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_SIGNALUM).add(ITEMS.get("signalum_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_SILVER).add(ITEMS.get("silver_ingot"));

            getBuilder(CoFHTags.Items.NUGGETS_CONSTANTAN).add(ITEMS.get("constantan_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_COPPER).add(ITEMS.get("copper_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_ELECTRUM).add(ITEMS.get("electrum_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_ENDERIUM).add(ITEMS.get("enderium_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_INVAR).add(ITEMS.get("invar_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_LEAD).add(ITEMS.get("lead_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_LUMIUM).add(ITEMS.get("lumium_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_NICKEL).add(ITEMS.get("nickel_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_SIGNALUM).add(ITEMS.get("signalum_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_SILVER).add(ITEMS.get("silver_nugget"));

            getBuilder(CoFHTags.Items.PLATES_CONSTANTAN).add(ITEMS.get("constantan_plate"));
            getBuilder(CoFHTags.Items.PLATES_COPPER).add(ITEMS.get("copper_plate"));
            getBuilder(CoFHTags.Items.PLATES_ELECTRUM).add(ITEMS.get("electrum_plate"));
            getBuilder(CoFHTags.Items.PLATES_ENDERIUM).add(ITEMS.get("enderium_plate"));
            getBuilder(CoFHTags.Items.PLATES_GOLD).add(ITEMS.get("gold_plate"));
            getBuilder(CoFHTags.Items.PLATES_INVAR).add(ITEMS.get("invar_plate"));
            getBuilder(CoFHTags.Items.PLATES_IRON).add(ITEMS.get("iron_plate"));
            getBuilder(CoFHTags.Items.PLATES_LEAD).add(ITEMS.get("lead_plate"));
            getBuilder(CoFHTags.Items.PLATES_LUMIUM).add(ITEMS.get("lumium_plate"));
            getBuilder(CoFHTags.Items.PLATES_NICKEL).add(ITEMS.get("nickel_plate"));
            getBuilder(CoFHTags.Items.PLATES_SIGNALUM).add(ITEMS.get("signalum_plate"));
            getBuilder(CoFHTags.Items.PLATES_SILVER).add(ITEMS.get("silver_plate"));

            getBuilder(CoFHTags.Items.TOOLS_WRENCH).add(ITEMS.get("wrench"));
        }

    }

    public static class Fluid extends FluidTagsProvider {

        public Fluid(DataGenerator dataGeneratorIn) {

            super(dataGeneratorIn);
        }

        @Override
        public String getName() {

            return "Thermal Core: Fluid Tags";
        }

        @Override
        protected void registerTags() {

        }

    }

}
