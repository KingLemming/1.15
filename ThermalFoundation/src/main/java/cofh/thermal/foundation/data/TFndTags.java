package cofh.thermal.foundation.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.data.ItemTagsProvider;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.ThermalReferences.*;
import static cofh.thermal.core.init.ThermalTags.Blocks.*;
import static cofh.thermal.core.init.ThermalTags.Items.*;

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

            getBuilder(ORES_COPPER).add(BLOCKS.get(ID_COPPER_ORE));
            getBuilder(ORES_TIN).add(BLOCKS.get(ID_TIN_ORE));
            getBuilder(ORES_SILVER).add(BLOCKS.get(ID_SILVER_ORE));
            getBuilder(ORES_LEAD).add(BLOCKS.get(ID_LEAD_ORE));
            getBuilder(ORES_NICKEL).add(BLOCKS.get(ID_NICKEL_ORE));
            getBuilder(ORES_PLATINUM).add(BLOCKS.get(ID_PLATINUM_ORE));

            getBuilder(ORES_RUBY).add(BLOCKS.get(ID_RUBY_ORE));
            getBuilder(ORES_SAPPHIRE).add(BLOCKS.get(ID_SAPPHIRE_ORE));

            getBuilder(STORAGE_BLOCKS_COPPER).add(BLOCKS.get(ID_COPPER_BLOCK));
            getBuilder(STORAGE_BLOCKS_TIN).add(BLOCKS.get(ID_TIN_BLOCK));
            getBuilder(STORAGE_BLOCKS_SILVER).add(BLOCKS.get(ID_SILVER_BLOCK));
            getBuilder(STORAGE_BLOCKS_LEAD).add(BLOCKS.get(ID_LEAD_BLOCK));
            getBuilder(STORAGE_BLOCKS_NICKEL).add(BLOCKS.get(ID_NICKEL_BLOCK));
            getBuilder(STORAGE_BLOCKS_PLATINUM).add(BLOCKS.get(ID_PLATINUM_BLOCK));

            getBuilder(STORAGE_BLOCKS_BRONZE).add(BLOCKS.get(ID_BRONZE_BLOCK));
            getBuilder(STORAGE_BLOCKS_ELECTRUM).add(BLOCKS.get(ID_ELECTRUM_BLOCK));
            getBuilder(STORAGE_BLOCKS_INVAR).add(BLOCKS.get(ID_INVAR_BLOCK));
            getBuilder(STORAGE_BLOCKS_CONSTANTAN).add(BLOCKS.get(ID_CONSTANTAN_BLOCK));

            getBuilder(STORAGE_BLOCKS_RUBY).add(BLOCKS.get(ID_RUBY_BLOCK));
            getBuilder(STORAGE_BLOCKS_SAPPHIRE).add(BLOCKS.get(ID_SAPPHIRE_BLOCK));
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

            getBuilder(COINS_COPPER).add(ITEMS.get("copper_coin"));
            getBuilder(COINS_TIN).add(ITEMS.get("tin_coin"));
            getBuilder(COINS_SILVER).add(ITEMS.get("silver_coin"));
            getBuilder(COINS_LEAD).add(ITEMS.get("lead_coin"));
            getBuilder(COINS_NICKEL).add(ITEMS.get("nickel_coin"));
            getBuilder(COINS_PLATINUM).add(ITEMS.get("platinum_coin"));

            getBuilder(COINS_BRONZE).add(ITEMS.get("bronze_coin"));
            getBuilder(COINS_ELECTRUM).add(ITEMS.get("electrum_coin"));
            getBuilder(COINS_INVAR).add(ITEMS.get("invar_coin"));
            getBuilder(COINS_CONSTANTAN).add(ITEMS.get("constantan_coin"));

            getBuilder(GEARS_COPPER).add(ITEMS.get("copper_gear"));
            getBuilder(GEARS_TIN).add(ITEMS.get("tin_gear"));
            getBuilder(GEARS_SILVER).add(ITEMS.get("silver_gear"));
            getBuilder(GEARS_LEAD).add(ITEMS.get("lead_gear"));
            getBuilder(GEARS_NICKEL).add(ITEMS.get("nickel_gear"));
            getBuilder(GEARS_PLATINUM).add(ITEMS.get("platinum_gear"));

            getBuilder(GEARS_BRONZE).add(ITEMS.get("bronze_gear"));
            getBuilder(GEARS_ELECTRUM).add(ITEMS.get("electrum_gear"));
            getBuilder(GEARS_INVAR).add(ITEMS.get("invar_gear"));
            getBuilder(GEARS_CONSTANTAN).add(ITEMS.get("constantan_gear"));

            getBuilder(INGOTS_COPPER).add(ITEMS.get("copper_ingot"));
            getBuilder(INGOTS_TIN).add(ITEMS.get("tin_ingot"));
            getBuilder(INGOTS_SILVER).add(ITEMS.get("silver_ingot"));
            getBuilder(INGOTS_LEAD).add(ITEMS.get("lead_ingot"));
            getBuilder(INGOTS_NICKEL).add(ITEMS.get("nickel_ingot"));
            getBuilder(INGOTS_PLATINUM).add(ITEMS.get("platinum_ingot"));

            getBuilder(INGOTS_BRONZE).add(ITEMS.get("bronze_ingot"));
            getBuilder(INGOTS_ELECTRUM).add(ITEMS.get("electrum_ingot"));
            getBuilder(INGOTS_INVAR).add(ITEMS.get("invar_ingot"));
            getBuilder(INGOTS_CONSTANTAN).add(ITEMS.get("constantan_ingot"));

            getBuilder(NUGGETS_COPPER).add(ITEMS.get("copper_nugget"));
            getBuilder(NUGGETS_TIN).add(ITEMS.get("tin_nugget"));
            getBuilder(NUGGETS_SILVER).add(ITEMS.get("silver_nugget"));
            getBuilder(NUGGETS_LEAD).add(ITEMS.get("lead_nugget"));
            getBuilder(NUGGETS_NICKEL).add(ITEMS.get("nickel_nugget"));
            getBuilder(NUGGETS_PLATINUM).add(ITEMS.get("platinum_nugget"));

            getBuilder(NUGGETS_BRONZE).add(ITEMS.get("bronze_nugget"));
            getBuilder(NUGGETS_ELECTRUM).add(ITEMS.get("electrum_nugget"));
            getBuilder(NUGGETS_INVAR).add(ITEMS.get("invar_nugget"));
            getBuilder(NUGGETS_CONSTANTAN).add(ITEMS.get("constantan_nugget"));

            getBuilder(PLATES_COPPER).add(ITEMS.get("copper_plate"));
            getBuilder(PLATES_TIN).add(ITEMS.get("tin_plate"));
            getBuilder(PLATES_SILVER).add(ITEMS.get("silver_plate"));
            getBuilder(PLATES_LEAD).add(ITEMS.get("lead_plate"));
            getBuilder(PLATES_NICKEL).add(ITEMS.get("nickel_plate"));
            getBuilder(PLATES_PLATINUM).add(ITEMS.get("platinum_plate"));

            getBuilder(PLATES_BRONZE).add(ITEMS.get("bronze_plate"));
            getBuilder(PLATES_ELECTRUM).add(ITEMS.get("electrum_plate"));
            getBuilder(PLATES_INVAR).add(ITEMS.get("invar_plate"));
            getBuilder(PLATES_CONSTANTAN).add(ITEMS.get("constantan_plate"));

            getBuilder(GEARS_RUBY).add(ITEMS.get("ruby_gear"));
            getBuilder(GEARS_SAPPHIRE).add(ITEMS.get("sapphire_gear"));

            getBuilder(GEMS_RUBY).add(ITEMS.get("ruby_gem"));
            getBuilder(GEMS_SAPPHIRE).add(ITEMS.get("sapphire_gem"));

            getBuilder(NUGGETS_RUBY).add(ITEMS.get("ruby_nugget"));
            getBuilder(NUGGETS_SAPPHIRE).add(ITEMS.get("sapphire_nugget"));

            getBuilder(PLATES_RUBY).add(ITEMS.get("ruby_plate"));
            getBuilder(PLATES_SAPPHIRE).add(ITEMS.get("sapphire_plate"));
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
