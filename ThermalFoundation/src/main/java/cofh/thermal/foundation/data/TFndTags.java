package cofh.thermal.foundation.data;

import cofh.lib.util.references.CoFHTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.data.ItemTagsProvider;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.foundation.init.TFndReferences.*;

public class TFndTags {

    public static class Block extends BlockTagsProvider {

        public Block(DataGenerator dataGeneratorIn) {

            super(dataGeneratorIn);
        }

        @Override
        public String getName() {

            return "Thermal Foundation: Block Tags";
        }

        @Override
        protected void registerTags() {

            getBuilder(CoFHTags.Blocks.ORES_COPPER).add(BLOCKS.get(ID_COPPER_ORE));
            getBuilder(CoFHTags.Blocks.ORES_TIN).add(BLOCKS.get(ID_TIN_ORE));
            getBuilder(CoFHTags.Blocks.ORES_SILVER).add(BLOCKS.get(ID_SILVER_ORE));
            getBuilder(CoFHTags.Blocks.ORES_LEAD).add(BLOCKS.get(ID_LEAD_ORE));
            getBuilder(CoFHTags.Blocks.ORES_NICKEL).add(BLOCKS.get(ID_NICKEL_ORE));
            getBuilder(CoFHTags.Blocks.ORES_PLATINUM).add(BLOCKS.get(ID_PLATINUM_ORE));

            getBuilder(CoFHTags.Blocks.ORES_RUBY).add(BLOCKS.get(ID_RUBY_ORE));
            getBuilder(CoFHTags.Blocks.ORES_SAPPHIRE).add(BLOCKS.get(ID_SAPPHIRE_ORE));

            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_COPPER).add(BLOCKS.get(ID_COPPER_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_TIN).add(BLOCKS.get(ID_TIN_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_SILVER).add(BLOCKS.get(ID_SILVER_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_LEAD).add(BLOCKS.get(ID_LEAD_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_NICKEL).add(BLOCKS.get(ID_NICKEL_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_PLATINUM).add(BLOCKS.get(ID_PLATINUM_BLOCK));

            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_BRONZE).add(BLOCKS.get(ID_BRONZE_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_ELECTRUM).add(BLOCKS.get(ID_ELECTRUM_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_INVAR).add(BLOCKS.get(ID_INVAR_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_CONSTANTAN).add(BLOCKS.get(ID_CONSTANTAN_BLOCK));

            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_RUBY).add(BLOCKS.get(ID_RUBY_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_SAPPHIRE).add(BLOCKS.get(ID_SAPPHIRE_BLOCK));
        }

    }

    public static class Item extends ItemTagsProvider {

        public Item(DataGenerator dataGeneratorIn) {

            super(dataGeneratorIn);
        }

        @Override
        public String getName() {

            return "Thermal Foundation: Item Tags";
        }

        @Override
        protected void registerTags() {

            copy(CoFHTags.Blocks.ORES_COPPER, CoFHTags.Items.ORES_COPPER);
            copy(CoFHTags.Blocks.ORES_TIN, CoFHTags.Items.ORES_TIN);
            copy(CoFHTags.Blocks.ORES_SILVER, CoFHTags.Items.ORES_SILVER);
            copy(CoFHTags.Blocks.ORES_LEAD, CoFHTags.Items.ORES_LEAD);
            copy(CoFHTags.Blocks.ORES_NICKEL, CoFHTags.Items.ORES_NICKEL);
            copy(CoFHTags.Blocks.ORES_PLATINUM, CoFHTags.Items.ORES_PLATINUM);

            copy(CoFHTags.Blocks.ORES_RUBY, CoFHTags.Items.ORES_RUBY);
            copy(CoFHTags.Blocks.ORES_SAPPHIRE, CoFHTags.Items.ORES_SAPPHIRE);

            copy(CoFHTags.Blocks.STORAGE_BLOCKS_COPPER, CoFHTags.Items.STORAGE_BLOCKS_COPPER);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_TIN, CoFHTags.Items.STORAGE_BLOCKS_TIN);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_SILVER, CoFHTags.Items.STORAGE_BLOCKS_SILVER);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_LEAD, CoFHTags.Items.STORAGE_BLOCKS_LEAD);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_NICKEL, CoFHTags.Items.STORAGE_BLOCKS_NICKEL);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_PLATINUM, CoFHTags.Items.STORAGE_BLOCKS_PLATINUM);

            copy(CoFHTags.Blocks.STORAGE_BLOCKS_BRONZE, CoFHTags.Items.STORAGE_BLOCKS_BRONZE);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_ELECTRUM, CoFHTags.Items.STORAGE_BLOCKS_ELECTRUM);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_INVAR, CoFHTags.Items.STORAGE_BLOCKS_INVAR);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_CONSTANTAN, CoFHTags.Items.STORAGE_BLOCKS_CONSTANTAN);

            copy(CoFHTags.Blocks.STORAGE_BLOCKS_RUBY, CoFHTags.Items.STORAGE_BLOCKS_RUBY);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_SAPPHIRE, CoFHTags.Items.STORAGE_BLOCKS_SAPPHIRE);

            getBuilder(CoFHTags.Items.COINS_COPPER).add(ITEMS.get("copper_coin"));
            getBuilder(CoFHTags.Items.COINS_TIN).add(ITEMS.get("tin_coin"));
            getBuilder(CoFHTags.Items.COINS_SILVER).add(ITEMS.get("silver_coin"));
            getBuilder(CoFHTags.Items.COINS_LEAD).add(ITEMS.get("lead_coin"));
            getBuilder(CoFHTags.Items.COINS_NICKEL).add(ITEMS.get("nickel_coin"));
            getBuilder(CoFHTags.Items.COINS_PLATINUM).add(ITEMS.get("platinum_coin"));

            getBuilder(CoFHTags.Items.COINS_BRONZE).add(ITEMS.get("bronze_coin"));
            getBuilder(CoFHTags.Items.COINS_ELECTRUM).add(ITEMS.get("electrum_coin"));
            getBuilder(CoFHTags.Items.COINS_INVAR).add(ITEMS.get("invar_coin"));
            getBuilder(CoFHTags.Items.COINS_CONSTANTAN).add(ITEMS.get("constantan_coin"));

            getBuilder(CoFHTags.Items.DUSTS_COPPER).add(ITEMS.get("copper_dust"));
            getBuilder(CoFHTags.Items.DUSTS_TIN).add(ITEMS.get("tin_dust"));
            getBuilder(CoFHTags.Items.DUSTS_SILVER).add(ITEMS.get("silver_dust"));
            getBuilder(CoFHTags.Items.DUSTS_LEAD).add(ITEMS.get("lead_dust"));
            getBuilder(CoFHTags.Items.DUSTS_NICKEL).add(ITEMS.get("nickel_dust"));
            getBuilder(CoFHTags.Items.DUSTS_PLATINUM).add(ITEMS.get("platinum_dust"));

            getBuilder(CoFHTags.Items.DUSTS_BRONZE).add(ITEMS.get("bronze_dust"));
            getBuilder(CoFHTags.Items.DUSTS_ELECTRUM).add(ITEMS.get("electrum_dust"));
            getBuilder(CoFHTags.Items.DUSTS_INVAR).add(ITEMS.get("invar_dust"));
            getBuilder(CoFHTags.Items.DUSTS_CONSTANTAN).add(ITEMS.get("constantan_dust"));

            getBuilder(CoFHTags.Items.GEARS_COPPER).add(ITEMS.get("copper_gear"));
            getBuilder(CoFHTags.Items.GEARS_TIN).add(ITEMS.get("tin_gear"));
            getBuilder(CoFHTags.Items.GEARS_SILVER).add(ITEMS.get("silver_gear"));
            getBuilder(CoFHTags.Items.GEARS_LEAD).add(ITEMS.get("lead_gear"));
            getBuilder(CoFHTags.Items.GEARS_NICKEL).add(ITEMS.get("nickel_gear"));
            getBuilder(CoFHTags.Items.GEARS_PLATINUM).add(ITEMS.get("platinum_gear"));

            getBuilder(CoFHTags.Items.GEARS_BRONZE).add(ITEMS.get("bronze_gear"));
            getBuilder(CoFHTags.Items.GEARS_ELECTRUM).add(ITEMS.get("electrum_gear"));
            getBuilder(CoFHTags.Items.GEARS_INVAR).add(ITEMS.get("invar_gear"));
            getBuilder(CoFHTags.Items.GEARS_CONSTANTAN).add(ITEMS.get("constantan_gear"));

            getBuilder(CoFHTags.Items.INGOTS_COPPER).add(ITEMS.get("copper_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_TIN).add(ITEMS.get("tin_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_SILVER).add(ITEMS.get("silver_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_LEAD).add(ITEMS.get("lead_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_NICKEL).add(ITEMS.get("nickel_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_PLATINUM).add(ITEMS.get("platinum_ingot"));

            getBuilder(CoFHTags.Items.INGOTS_BRONZE).add(ITEMS.get("bronze_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_ELECTRUM).add(ITEMS.get("electrum_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_INVAR).add(ITEMS.get("invar_ingot"));
            getBuilder(CoFHTags.Items.INGOTS_CONSTANTAN).add(ITEMS.get("constantan_ingot"));

            getBuilder(CoFHTags.Items.NUGGETS_COPPER).add(ITEMS.get("copper_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_TIN).add(ITEMS.get("tin_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_SILVER).add(ITEMS.get("silver_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_LEAD).add(ITEMS.get("lead_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_NICKEL).add(ITEMS.get("nickel_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_PLATINUM).add(ITEMS.get("platinum_nugget"));

            getBuilder(CoFHTags.Items.NUGGETS_BRONZE).add(ITEMS.get("bronze_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_ELECTRUM).add(ITEMS.get("electrum_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_INVAR).add(ITEMS.get("invar_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_CONSTANTAN).add(ITEMS.get("constantan_nugget"));

            getBuilder(CoFHTags.Items.PLATES_COPPER).add(ITEMS.get("copper_plate"));
            getBuilder(CoFHTags.Items.PLATES_TIN).add(ITEMS.get("tin_plate"));
            getBuilder(CoFHTags.Items.PLATES_SILVER).add(ITEMS.get("silver_plate"));
            getBuilder(CoFHTags.Items.PLATES_LEAD).add(ITEMS.get("lead_plate"));
            getBuilder(CoFHTags.Items.PLATES_NICKEL).add(ITEMS.get("nickel_plate"));
            getBuilder(CoFHTags.Items.PLATES_PLATINUM).add(ITEMS.get("platinum_plate"));

            getBuilder(CoFHTags.Items.PLATES_BRONZE).add(ITEMS.get("bronze_plate"));
            getBuilder(CoFHTags.Items.PLATES_ELECTRUM).add(ITEMS.get("electrum_plate"));
            getBuilder(CoFHTags.Items.PLATES_INVAR).add(ITEMS.get("invar_plate"));
            getBuilder(CoFHTags.Items.PLATES_CONSTANTAN).add(ITEMS.get("constantan_plate"));

            getBuilder(CoFHTags.Items.DUSTS_RUBY).add(ITEMS.get("ruby_dust"));
            getBuilder(CoFHTags.Items.DUSTS_SAPPHIRE).add(ITEMS.get("sapphire_dust"));

            getBuilder(CoFHTags.Items.GEARS_RUBY).add(ITEMS.get("ruby_gear"));
            getBuilder(CoFHTags.Items.GEARS_SAPPHIRE).add(ITEMS.get("sapphire_gear"));

            getBuilder(CoFHTags.Items.GEMS_RUBY).add(ITEMS.get("ruby_gem"));
            getBuilder(CoFHTags.Items.GEMS_SAPPHIRE).add(ITEMS.get("sapphire_gem"));

            //            getBuilder(CoFHTags.Items.NUGGETS_RUBY).add(ITEMS.get("ruby_nugget"));
            //            getBuilder(CoFHTags.Items.NUGGETS_SAPPHIRE).add(ITEMS.get("sapphire_nugget"));
            //
            //            getBuilder(CoFHTags.Items.PLATES_RUBY).add(ITEMS.get("ruby_plate"));
            //            getBuilder(CoFHTags.Items.PLATES_SAPPHIRE).add(ITEMS.get("sapphire_plate"));
        }

    }

    public static class Fluid extends FluidTagsProvider {

        public Fluid(DataGenerator dataGeneratorIn) {

            super(dataGeneratorIn);
        }

        @Override
        public String getName() {

            return "Thermal Foundation: Fluid Tags";
        }

        @Override
        protected void registerTags() {

        }

    }

}
