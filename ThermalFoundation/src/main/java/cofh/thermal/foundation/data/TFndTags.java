package cofh.thermal.foundation.data;

import cofh.thermal.core.init.ThermalTags;
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

            getBuilder(ThermalTags.Blocks.ORES_COPPER).add(BLOCKS.get(ID_COPPER_ORE));
            getBuilder(ThermalTags.Blocks.ORES_TIN).add(BLOCKS.get(ID_TIN_ORE));
            getBuilder(ThermalTags.Blocks.ORES_SILVER).add(BLOCKS.get(ID_SILVER_ORE));
            getBuilder(ThermalTags.Blocks.ORES_LEAD).add(BLOCKS.get(ID_LEAD_ORE));
            getBuilder(ThermalTags.Blocks.ORES_NICKEL).add(BLOCKS.get(ID_NICKEL_ORE));
            getBuilder(ThermalTags.Blocks.ORES_PLATINUM).add(BLOCKS.get(ID_PLATINUM_ORE));

            getBuilder(ThermalTags.Blocks.ORES_RUBY).add(BLOCKS.get(ID_RUBY_ORE));
            getBuilder(ThermalTags.Blocks.ORES_SAPPHIRE).add(BLOCKS.get(ID_SAPPHIRE_ORE));

            getBuilder(ThermalTags.Blocks.STORAGE_BLOCKS_COPPER).add(BLOCKS.get(ID_COPPER_BLOCK));
            getBuilder(ThermalTags.Blocks.STORAGE_BLOCKS_TIN).add(BLOCKS.get(ID_TIN_BLOCK));
            getBuilder(ThermalTags.Blocks.STORAGE_BLOCKS_SILVER).add(BLOCKS.get(ID_SILVER_BLOCK));
            getBuilder(ThermalTags.Blocks.STORAGE_BLOCKS_LEAD).add(BLOCKS.get(ID_LEAD_BLOCK));
            getBuilder(ThermalTags.Blocks.STORAGE_BLOCKS_NICKEL).add(BLOCKS.get(ID_NICKEL_BLOCK));
            getBuilder(ThermalTags.Blocks.STORAGE_BLOCKS_PLATINUM).add(BLOCKS.get(ID_PLATINUM_BLOCK));

            getBuilder(ThermalTags.Blocks.STORAGE_BLOCKS_BRONZE).add(BLOCKS.get(ID_BRONZE_BLOCK));
            getBuilder(ThermalTags.Blocks.STORAGE_BLOCKS_ELECTRUM).add(BLOCKS.get(ID_ELECTRUM_BLOCK));
            getBuilder(ThermalTags.Blocks.STORAGE_BLOCKS_INVAR).add(BLOCKS.get(ID_INVAR_BLOCK));
            getBuilder(ThermalTags.Blocks.STORAGE_BLOCKS_CONSTANTAN).add(BLOCKS.get(ID_CONSTANTAN_BLOCK));

            getBuilder(ThermalTags.Blocks.STORAGE_BLOCKS_RUBY).add(BLOCKS.get(ID_RUBY_BLOCK));
            getBuilder(ThermalTags.Blocks.STORAGE_BLOCKS_SAPPHIRE).add(BLOCKS.get(ID_SAPPHIRE_BLOCK));
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

            copy(ThermalTags.Blocks.ORES_COPPER, ThermalTags.Items.ORES_COPPER);
            copy(ThermalTags.Blocks.ORES_TIN, ThermalTags.Items.ORES_TIN);
            copy(ThermalTags.Blocks.ORES_SILVER, ThermalTags.Items.ORES_SILVER);
            copy(ThermalTags.Blocks.ORES_LEAD, ThermalTags.Items.ORES_LEAD);
            copy(ThermalTags.Blocks.ORES_NICKEL, ThermalTags.Items.ORES_NICKEL);
            copy(ThermalTags.Blocks.ORES_PLATINUM, ThermalTags.Items.ORES_PLATINUM);

            copy(ThermalTags.Blocks.ORES_RUBY, ThermalTags.Items.ORES_RUBY);
            copy(ThermalTags.Blocks.ORES_SAPPHIRE, ThermalTags.Items.ORES_SAPPHIRE);

            copy(ThermalTags.Blocks.STORAGE_BLOCKS_COPPER, ThermalTags.Items.STORAGE_BLOCKS_COPPER);
            copy(ThermalTags.Blocks.STORAGE_BLOCKS_TIN, ThermalTags.Items.STORAGE_BLOCKS_TIN);
            copy(ThermalTags.Blocks.STORAGE_BLOCKS_SILVER, ThermalTags.Items.STORAGE_BLOCKS_SILVER);
            copy(ThermalTags.Blocks.STORAGE_BLOCKS_LEAD, ThermalTags.Items.STORAGE_BLOCKS_LEAD);
            copy(ThermalTags.Blocks.STORAGE_BLOCKS_NICKEL, ThermalTags.Items.STORAGE_BLOCKS_NICKEL);
            copy(ThermalTags.Blocks.STORAGE_BLOCKS_PLATINUM, ThermalTags.Items.STORAGE_BLOCKS_PLATINUM);

            copy(ThermalTags.Blocks.STORAGE_BLOCKS_BRONZE, ThermalTags.Items.STORAGE_BLOCKS_BRONZE);
            copy(ThermalTags.Blocks.STORAGE_BLOCKS_ELECTRUM, ThermalTags.Items.STORAGE_BLOCKS_ELECTRUM);
            copy(ThermalTags.Blocks.STORAGE_BLOCKS_INVAR, ThermalTags.Items.STORAGE_BLOCKS_INVAR);
            copy(ThermalTags.Blocks.STORAGE_BLOCKS_CONSTANTAN, ThermalTags.Items.STORAGE_BLOCKS_CONSTANTAN);

            copy(ThermalTags.Blocks.STORAGE_BLOCKS_RUBY, ThermalTags.Items.STORAGE_BLOCKS_RUBY);
            copy(ThermalTags.Blocks.STORAGE_BLOCKS_SAPPHIRE, ThermalTags.Items.STORAGE_BLOCKS_SAPPHIRE);

            getBuilder(ThermalTags.Items.COINS_COPPER).add(ITEMS.get("copper_coin"));
            getBuilder(ThermalTags.Items.COINS_TIN).add(ITEMS.get("tin_coin"));
            getBuilder(ThermalTags.Items.COINS_SILVER).add(ITEMS.get("silver_coin"));
            getBuilder(ThermalTags.Items.COINS_LEAD).add(ITEMS.get("lead_coin"));
            getBuilder(ThermalTags.Items.COINS_NICKEL).add(ITEMS.get("nickel_coin"));
            getBuilder(ThermalTags.Items.COINS_PLATINUM).add(ITEMS.get("platinum_coin"));

            getBuilder(ThermalTags.Items.COINS_BRONZE).add(ITEMS.get("bronze_coin"));
            getBuilder(ThermalTags.Items.COINS_ELECTRUM).add(ITEMS.get("electrum_coin"));
            getBuilder(ThermalTags.Items.COINS_INVAR).add(ITEMS.get("invar_coin"));
            getBuilder(ThermalTags.Items.COINS_CONSTANTAN).add(ITEMS.get("constantan_coin"));

            getBuilder(ThermalTags.Items.GEARS_COPPER).add(ITEMS.get("copper_gear"));
            getBuilder(ThermalTags.Items.GEARS_TIN).add(ITEMS.get("tin_gear"));
            getBuilder(ThermalTags.Items.GEARS_SILVER).add(ITEMS.get("silver_gear"));
            getBuilder(ThermalTags.Items.GEARS_LEAD).add(ITEMS.get("lead_gear"));
            getBuilder(ThermalTags.Items.GEARS_NICKEL).add(ITEMS.get("nickel_gear"));
            getBuilder(ThermalTags.Items.GEARS_PLATINUM).add(ITEMS.get("platinum_gear"));

            getBuilder(ThermalTags.Items.GEARS_BRONZE).add(ITEMS.get("bronze_gear"));
            getBuilder(ThermalTags.Items.GEARS_ELECTRUM).add(ITEMS.get("electrum_gear"));
            getBuilder(ThermalTags.Items.GEARS_INVAR).add(ITEMS.get("invar_gear"));
            getBuilder(ThermalTags.Items.GEARS_CONSTANTAN).add(ITEMS.get("constantan_gear"));

            getBuilder(ThermalTags.Items.INGOTS_COPPER).add(ITEMS.get("copper_ingot"));
            getBuilder(ThermalTags.Items.INGOTS_TIN).add(ITEMS.get("tin_ingot"));
            getBuilder(ThermalTags.Items.INGOTS_SILVER).add(ITEMS.get("silver_ingot"));
            getBuilder(ThermalTags.Items.INGOTS_LEAD).add(ITEMS.get("lead_ingot"));
            getBuilder(ThermalTags.Items.INGOTS_NICKEL).add(ITEMS.get("nickel_ingot"));
            getBuilder(ThermalTags.Items.INGOTS_PLATINUM).add(ITEMS.get("platinum_ingot"));

            getBuilder(ThermalTags.Items.INGOTS_BRONZE).add(ITEMS.get("bronze_ingot"));
            getBuilder(ThermalTags.Items.INGOTS_ELECTRUM).add(ITEMS.get("electrum_ingot"));
            getBuilder(ThermalTags.Items.INGOTS_INVAR).add(ITEMS.get("invar_ingot"));
            getBuilder(ThermalTags.Items.INGOTS_CONSTANTAN).add(ITEMS.get("constantan_ingot"));

            getBuilder(ThermalTags.Items.NUGGETS_COPPER).add(ITEMS.get("copper_nugget"));
            getBuilder(ThermalTags.Items.NUGGETS_TIN).add(ITEMS.get("tin_nugget"));
            getBuilder(ThermalTags.Items.NUGGETS_SILVER).add(ITEMS.get("silver_nugget"));
            getBuilder(ThermalTags.Items.NUGGETS_LEAD).add(ITEMS.get("lead_nugget"));
            getBuilder(ThermalTags.Items.NUGGETS_NICKEL).add(ITEMS.get("nickel_nugget"));
            getBuilder(ThermalTags.Items.NUGGETS_PLATINUM).add(ITEMS.get("platinum_nugget"));

            getBuilder(ThermalTags.Items.NUGGETS_BRONZE).add(ITEMS.get("bronze_nugget"));
            getBuilder(ThermalTags.Items.NUGGETS_ELECTRUM).add(ITEMS.get("electrum_nugget"));
            getBuilder(ThermalTags.Items.NUGGETS_INVAR).add(ITEMS.get("invar_nugget"));
            getBuilder(ThermalTags.Items.NUGGETS_CONSTANTAN).add(ITEMS.get("constantan_nugget"));

            getBuilder(ThermalTags.Items.PLATES_COPPER).add(ITEMS.get("copper_plate"));
            getBuilder(ThermalTags.Items.PLATES_TIN).add(ITEMS.get("tin_plate"));
            getBuilder(ThermalTags.Items.PLATES_SILVER).add(ITEMS.get("silver_plate"));
            getBuilder(ThermalTags.Items.PLATES_LEAD).add(ITEMS.get("lead_plate"));
            getBuilder(ThermalTags.Items.PLATES_NICKEL).add(ITEMS.get("nickel_plate"));
            getBuilder(ThermalTags.Items.PLATES_PLATINUM).add(ITEMS.get("platinum_plate"));

            getBuilder(ThermalTags.Items.PLATES_BRONZE).add(ITEMS.get("bronze_plate"));
            getBuilder(ThermalTags.Items.PLATES_ELECTRUM).add(ITEMS.get("electrum_plate"));
            getBuilder(ThermalTags.Items.PLATES_INVAR).add(ITEMS.get("invar_plate"));
            getBuilder(ThermalTags.Items.PLATES_CONSTANTAN).add(ITEMS.get("constantan_plate"));

            getBuilder(ThermalTags.Items.GEARS_RUBY).add(ITEMS.get("ruby_gear"));
            getBuilder(ThermalTags.Items.GEARS_SAPPHIRE).add(ITEMS.get("sapphire_gear"));

            getBuilder(ThermalTags.Items.GEMS_RUBY).add(ITEMS.get("ruby_gem"));
            getBuilder(ThermalTags.Items.GEMS_SAPPHIRE).add(ITEMS.get("sapphire_gem"));

            getBuilder(ThermalTags.Items.NUGGETS_RUBY).add(ITEMS.get("ruby_nugget"));
            getBuilder(ThermalTags.Items.NUGGETS_SAPPHIRE).add(ITEMS.get("sapphire_nugget"));

            getBuilder(ThermalTags.Items.PLATES_RUBY).add(ITEMS.get("ruby_plate"));
            getBuilder(ThermalTags.Items.PLATES_SAPPHIRE).add(ITEMS.get("sapphire_plate"));
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
