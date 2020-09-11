package cofh.thermal.core.datagen;

import cofh.core.util.references.CoFHTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.data.ItemTagsProvider;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.TCoreIDs.*;
import static net.minecraftforge.common.Tags.Blocks.ORES;
import static net.minecraftforge.common.Tags.Blocks.STORAGE_BLOCKS;
import static net.minecraftforge.common.Tags.Items.*;

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

            getBuilder(CoFHTags.Blocks.HARDENED_GLASS).add(
                    BLOCKS.get(ID_OBSIDIAN_GLASS),
                    BLOCKS.get(ID_SIGNALUM_GLASS),
                    BLOCKS.get(ID_LUMIUM_GLASS),
                    BLOCKS.get(ID_ENDERIUM_GLASS));

            getBuilder(CoFHTags.Blocks.ROCKWOOL).add(
                    BLOCKS.get(ID_WHITE_ROCKWOOL),
                    BLOCKS.get(ID_ORANGE_ROCKWOOL),
                    BLOCKS.get(ID_MAGENTA_ROCKWOOL),
                    BLOCKS.get(ID_LIGHT_BLUE_ROCKWOOL),
                    BLOCKS.get(ID_YELLOW_ROCKWOOL),
                    BLOCKS.get(ID_LIME_ROCKWOOL),
                    BLOCKS.get(ID_PINK_ROCKWOOL),
                    BLOCKS.get(ID_GRAY_ROCKWOOL),
                    BLOCKS.get(ID_LIGHT_GRAY_ROCKWOOL),
                    BLOCKS.get(ID_CYAN_ROCKWOOL),
                    BLOCKS.get(ID_PURPLE_ROCKWOOL),
                    BLOCKS.get(ID_BLUE_ROCKWOOL),
                    BLOCKS.get(ID_BROWN_ROCKWOOL),
                    BLOCKS.get(ID_GREEN_ROCKWOOL),
                    BLOCKS.get(ID_RED_ROCKWOOL),
                    BLOCKS.get(ID_BLACK_ROCKWOOL));

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
            getBuilder(CoFHTags.Blocks.ORES_TIN).add(BLOCKS.get(ID_TIN_ORE));

            getBuilder(ORES).add(
                    CoFHTags.Blocks.ORES_APATITE,
                    CoFHTags.Blocks.ORES_CINNABAR,
                    CoFHTags.Blocks.ORES_COPPER,
                    CoFHTags.Blocks.ORES_LEAD,
                    CoFHTags.Blocks.ORES_NICKEL,
                    CoFHTags.Blocks.ORES_NITER,
                    CoFHTags.Blocks.ORES_RUBY,
                    CoFHTags.Blocks.ORES_SAPPHIRE,
                    CoFHTags.Blocks.ORES_SILVER,
                    CoFHTags.Blocks.ORES_SULFUR,
                    CoFHTags.Blocks.ORES_TIN
            );

            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_APATITE).add(BLOCKS.get(ID_APATITE_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_BAMBOO).add(BLOCKS.get(ID_BAMBOO_BLOCK));
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_BRONZE).add(BLOCKS.get(ID_BRONZE_BLOCK));
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
            getBuilder(CoFHTags.Blocks.STORAGE_BLOCKS_TIN).add(BLOCKS.get(ID_TIN_BLOCK));

            getBuilder(STORAGE_BLOCKS).add(
                    CoFHTags.Blocks.STORAGE_BLOCKS_APATITE,
                    CoFHTags.Blocks.STORAGE_BLOCKS_BAMBOO,
                    CoFHTags.Blocks.STORAGE_BLOCKS_BRONZE,
                    CoFHTags.Blocks.STORAGE_BLOCKS_CHARCOAL,
                    CoFHTags.Blocks.STORAGE_BLOCKS_CINNABAR,
                    CoFHTags.Blocks.STORAGE_BLOCKS_CONSTANTAN,
                    CoFHTags.Blocks.STORAGE_BLOCKS_COPPER,
                    CoFHTags.Blocks.STORAGE_BLOCKS_ELECTRUM,
                    CoFHTags.Blocks.STORAGE_BLOCKS_ENDERIUM,
                    CoFHTags.Blocks.STORAGE_BLOCKS_INVAR,
                    CoFHTags.Blocks.STORAGE_BLOCKS_LEAD,
                    CoFHTags.Blocks.STORAGE_BLOCKS_LUMIUM,
                    CoFHTags.Blocks.STORAGE_BLOCKS_NICKEL,
                    CoFHTags.Blocks.STORAGE_BLOCKS_NITER,
                    CoFHTags.Blocks.STORAGE_BLOCKS_RUBY,
                    CoFHTags.Blocks.STORAGE_BLOCKS_SAPPHIRE,
                    CoFHTags.Blocks.STORAGE_BLOCKS_SIGNALUM,
                    CoFHTags.Blocks.STORAGE_BLOCKS_SILVER,
                    CoFHTags.Blocks.STORAGE_BLOCKS_SUGAR_CANE,
                    CoFHTags.Blocks.STORAGE_BLOCKS_SULFUR,
                    CoFHTags.Blocks.STORAGE_BLOCKS_TIN
            );
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

            copy(CoFHTags.Blocks.HARDENED_GLASS, CoFHTags.Items.HARDENED_GLASS);
            copy(CoFHTags.Blocks.ROCKWOOL, CoFHTags.Items.ROCKWOOL);

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
            copy(CoFHTags.Blocks.ORES_TIN, CoFHTags.Items.ORES_TIN);

            copy(CoFHTags.Blocks.STORAGE_BLOCKS_APATITE, CoFHTags.Items.STORAGE_BLOCKS_APATITE);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_BAMBOO, CoFHTags.Items.STORAGE_BLOCKS_BAMBOO);
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_BRONZE, CoFHTags.Items.STORAGE_BLOCKS_BRONZE);
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
            copy(CoFHTags.Blocks.STORAGE_BLOCKS_TIN, CoFHTags.Items.STORAGE_BLOCKS_TIN);

            getBuilder(CoFHTags.Items.COINS_BRONZE).add(ITEMS.get("bronze_coin"));
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
            getBuilder(CoFHTags.Items.COINS_TIN).add(ITEMS.get("tin_coin"));

            getBuilder(CoFHTags.Items.COINS).add(
                    CoFHTags.Items.COINS_BRONZE,
                    CoFHTags.Items.COINS_CONSTANTAN,
                    CoFHTags.Items.COINS_COPPER,
                    CoFHTags.Items.COINS_ELECTRUM,
                    CoFHTags.Items.COINS_ENDERIUM,
                    CoFHTags.Items.COINS_INVAR,
                    CoFHTags.Items.COINS_LEAD,
                    CoFHTags.Items.COINS_LUMIUM,
                    CoFHTags.Items.COINS_NICKEL,
                    CoFHTags.Items.COINS_SIGNALUM,
                    CoFHTags.Items.COINS_SILVER,
                    CoFHTags.Items.COINS_TIN
            );

            getBuilder(CoFHTags.Items.DUSTS_BRONZE).add(ITEMS.get("bronze_dust"));
            getBuilder(CoFHTags.Items.DUSTS_CONSTANTAN).add(ITEMS.get("constantan_dust"));
            getBuilder(CoFHTags.Items.DUSTS_COPPER).add(ITEMS.get("copper_dust"));
            getBuilder(CoFHTags.Items.DUSTS_DIAMOND).add(ITEMS.get("diamond_dust"));
            getBuilder(CoFHTags.Items.DUSTS_ELECTRUM).add(ITEMS.get("electrum_dust"));
            getBuilder(CoFHTags.Items.DUSTS_EMERALD).add(ITEMS.get("emerald_dust"));
            getBuilder(CoFHTags.Items.DUSTS_ENDERIUM).add(ITEMS.get("enderium_dust"));
            getBuilder(CoFHTags.Items.DUSTS_GOLD).add(ITEMS.get("gold_dust"));
            getBuilder(CoFHTags.Items.DUSTS_INVAR).add(ITEMS.get("invar_dust"));
            getBuilder(CoFHTags.Items.DUSTS_IRON).add(ITEMS.get("iron_dust"));
            getBuilder(CoFHTags.Items.DUSTS_LAPIS).add(ITEMS.get("lapis_dust"));
            getBuilder(CoFHTags.Items.DUSTS_LEAD).add(ITEMS.get("lead_dust"));
            getBuilder(CoFHTags.Items.DUSTS_LUMIUM).add(ITEMS.get("lumium_dust"));
            getBuilder(CoFHTags.Items.DUSTS_NICKEL).add(ITEMS.get("nickel_dust"));
            getBuilder(CoFHTags.Items.DUSTS_QUARTZ).add(ITEMS.get("quartz_dust"));
            getBuilder(CoFHTags.Items.DUSTS_RUBY).add(ITEMS.get("ruby_dust"));
            getBuilder(CoFHTags.Items.DUSTS_SAPPHIRE).add(ITEMS.get("sapphire_dust"));
            getBuilder(CoFHTags.Items.DUSTS_SIGNALUM).add(ITEMS.get("signalum_dust"));
            getBuilder(CoFHTags.Items.DUSTS_SILVER).add(ITEMS.get("silver_dust"));
            getBuilder(CoFHTags.Items.DUSTS_TIN).add(ITEMS.get("tin_dust"));

            getBuilder(DUSTS).add(
                    //CoFHTags.Items.DUSTS_APATITE,
                    CoFHTags.Items.DUSTS_BRONZE,
                    //CoFHTags.Items.DUSTS_CINNABAR,
                    CoFHTags.Items.DUSTS_CONSTANTAN,
                    CoFHTags.Items.DUSTS_COPPER,
                    CoFHTags.Items.DUSTS_ELECTRUM,
                    CoFHTags.Items.DUSTS_ENDERIUM,
                    CoFHTags.Items.DUSTS_INVAR,
                    CoFHTags.Items.DUSTS_LEAD,
                    CoFHTags.Items.DUSTS_LUMIUM,
                    CoFHTags.Items.DUSTS_NICKEL,
                    //CoFHTags.Items.DUSTS_NITER,
                    CoFHTags.Items.DUSTS_RUBY,
                    CoFHTags.Items.DUSTS_SAPPHIRE,
                    CoFHTags.Items.DUSTS_SIGNALUM,
                    CoFHTags.Items.DUSTS_SILVER,
                    //CoFHTags.Items.DUSTS_SULFUR,
                    CoFHTags.Items.DUSTS_TIN
            );

            getBuilder(CoFHTags.Items.GEARS_BRONZE).add(ITEMS.get("bronze_gear"));
            getBuilder(CoFHTags.Items.GEARS_CONSTANTAN).add(ITEMS.get("constantan_gear"));
            getBuilder(CoFHTags.Items.GEARS_COPPER).add(ITEMS.get("copper_gear"));
            getBuilder(CoFHTags.Items.GEARS_DIAMOND).add(ITEMS.get("diamond_gear"));
            getBuilder(CoFHTags.Items.GEARS_ELECTRUM).add(ITEMS.get("electrum_gear"));
            getBuilder(CoFHTags.Items.GEARS_EMERALD).add(ITEMS.get("emerald_gear"));
            getBuilder(CoFHTags.Items.GEARS_ENDERIUM).add(ITEMS.get("enderium_gear"));
            getBuilder(CoFHTags.Items.GEARS_GOLD).add(ITEMS.get("gold_gear"));
            getBuilder(CoFHTags.Items.GEARS_INVAR).add(ITEMS.get("invar_gear"));
            getBuilder(CoFHTags.Items.GEARS_IRON).add(ITEMS.get("iron_gear"));
            getBuilder(CoFHTags.Items.GEARS_LAPIS).add(ITEMS.get("lapis_gear"));
            getBuilder(CoFHTags.Items.GEARS_LEAD).add(ITEMS.get("lead_gear"));
            getBuilder(CoFHTags.Items.GEARS_LUMIUM).add(ITEMS.get("lumium_gear"));
            getBuilder(CoFHTags.Items.GEARS_NICKEL).add(ITEMS.get("nickel_gear"));
            getBuilder(CoFHTags.Items.GEARS_QUARTZ).add(ITEMS.get("quartz_gear"));
            getBuilder(CoFHTags.Items.GEARS_RUBY).add(ITEMS.get("ruby_gear"));
            getBuilder(CoFHTags.Items.GEARS_SAPPHIRE).add(ITEMS.get("sapphire_gear"));
            getBuilder(CoFHTags.Items.GEARS_SIGNALUM).add(ITEMS.get("signalum_gear"));
            getBuilder(CoFHTags.Items.GEARS_SILVER).add(ITEMS.get("silver_gear"));
            getBuilder(CoFHTags.Items.GEARS_TIN).add(ITEMS.get("tin_gear"));

            getBuilder(CoFHTags.Items.GEARS).add(
                    CoFHTags.Items.GEARS_BRONZE,
                    CoFHTags.Items.GEARS_CONSTANTAN,
                    CoFHTags.Items.GEARS_COPPER,
                    CoFHTags.Items.GEARS_ELECTRUM,
                    CoFHTags.Items.GEARS_ENDERIUM,
                    CoFHTags.Items.GEARS_INVAR,
                    CoFHTags.Items.GEARS_LEAD,
                    CoFHTags.Items.GEARS_LUMIUM,
                    CoFHTags.Items.GEARS_NICKEL,
                    CoFHTags.Items.GEARS_RUBY,
                    CoFHTags.Items.GEARS_SAPPHIRE,
                    CoFHTags.Items.GEARS_SIGNALUM,
                    CoFHTags.Items.GEARS_SILVER,
                    CoFHTags.Items.GEARS_TIN
            );

            getBuilder(CoFHTags.Items.GEMS_APATITE).add(ITEMS.get("apatite"));
            getBuilder(CoFHTags.Items.GEMS_CINNABAR).add(ITEMS.get("cinnabar"));
            getBuilder(CoFHTags.Items.GEMS_NITER).add(ITEMS.get("niter"));
            getBuilder(CoFHTags.Items.GEMS_RUBY).add(ITEMS.get("ruby"));
            getBuilder(CoFHTags.Items.GEMS_SAPPHIRE).add(ITEMS.get("sapphire"));
            getBuilder(CoFHTags.Items.GEMS_SULFUR).add(ITEMS.get("sulfur"));

            getBuilder(GEMS).add(
                    CoFHTags.Items.GEMS_APATITE,
                    CoFHTags.Items.GEMS_CINNABAR,
                    CoFHTags.Items.GEMS_NITER,
                    CoFHTags.Items.GEMS_RUBY,
                    CoFHTags.Items.GEMS_SAPPHIRE,
                    CoFHTags.Items.GEMS_SULFUR
            );

            getBuilder(CoFHTags.Items.INGOTS_BRONZE).add(ITEMS.get("bronze_ingot"));
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
            getBuilder(CoFHTags.Items.INGOTS_TIN).add(ITEMS.get("tin_ingot"));

            getBuilder(INGOTS).add(
                    CoFHTags.Items.INGOTS_BRONZE,
                    CoFHTags.Items.INGOTS_CONSTANTAN,
                    CoFHTags.Items.INGOTS_COPPER,
                    CoFHTags.Items.INGOTS_ELECTRUM,
                    CoFHTags.Items.INGOTS_ENDERIUM,
                    CoFHTags.Items.INGOTS_INVAR,
                    CoFHTags.Items.INGOTS_LEAD,
                    CoFHTags.Items.INGOTS_LUMIUM,
                    CoFHTags.Items.INGOTS_NICKEL,
                    CoFHTags.Items.INGOTS_SIGNALUM,
                    CoFHTags.Items.INGOTS_SILVER,
                    CoFHTags.Items.INGOTS_TIN
            );

            getBuilder(CoFHTags.Items.NUGGETS_BRONZE).add(ITEMS.get("bronze_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_CONSTANTAN).add(ITEMS.get("constantan_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_COPPER).add(ITEMS.get("copper_nugget"));
            // getBuilder(CoFHTags.Items.NUGGETS_DIAMOND).add(ITEMS.get("diamond_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_ELECTRUM).add(ITEMS.get("electrum_nugget"));
            // getBuilder(CoFHTags.Items.NUGGETS_EMERALD).add(ITEMS.get("emerald_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_ENDERIUM).add(ITEMS.get("enderium_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_INVAR).add(ITEMS.get("invar_nugget"));
            // getBuilder(CoFHTags.Items.NUGGETS_LAPIS).add(ITEMS.get("lapis_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_LEAD).add(ITEMS.get("lead_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_LUMIUM).add(ITEMS.get("lumium_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_NICKEL).add(ITEMS.get("nickel_nugget"));
            // getBuilder(CoFHTags.Items.NUGGETS_QUARTZ).add(ITEMS.get("quartz_nugget"));
            // getBuilder(CoFHTags.Items.NUGGETS_RUBY).add(ITEMS.get("ruby_nugget"));
            // getBuilder(CoFHTags.Items.NUGGETS_SAPPHIRE).add(ITEMS.get("sapphire_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_SIGNALUM).add(ITEMS.get("signalum_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_SILVER).add(ITEMS.get("silver_nugget"));
            getBuilder(CoFHTags.Items.NUGGETS_TIN).add(ITEMS.get("tin_nugget"));

            getBuilder(NUGGETS).add(
                    CoFHTags.Items.NUGGETS_BRONZE,
                    CoFHTags.Items.NUGGETS_CONSTANTAN,
                    CoFHTags.Items.NUGGETS_COPPER,
                    CoFHTags.Items.NUGGETS_ELECTRUM,
                    CoFHTags.Items.NUGGETS_ENDERIUM,
                    CoFHTags.Items.NUGGETS_INVAR,
                    CoFHTags.Items.NUGGETS_LEAD,
                    CoFHTags.Items.NUGGETS_LUMIUM,
                    CoFHTags.Items.NUGGETS_NICKEL,
                    CoFHTags.Items.NUGGETS_SIGNALUM,
                    CoFHTags.Items.NUGGETS_SILVER,
                    CoFHTags.Items.NUGGETS_TIN
            );

            getBuilder(CoFHTags.Items.PLATES_BRONZE).add(ITEMS.get("bronze_plate"));
            getBuilder(CoFHTags.Items.PLATES_CONSTANTAN).add(ITEMS.get("constantan_plate"));
            getBuilder(CoFHTags.Items.PLATES_COPPER).add(ITEMS.get("copper_plate"));
            // getBuilder(CoFHTags.Items.PLATES_DIAMOND).add(ITEMS.get("diamond_plate"));
            getBuilder(CoFHTags.Items.PLATES_ELECTRUM).add(ITEMS.get("electrum_plate"));
            // getBuilder(CoFHTags.Items.PLATES_EMERALD).add(ITEMS.get("emerald_plate"));
            getBuilder(CoFHTags.Items.PLATES_ENDERIUM).add(ITEMS.get("enderium_plate"));
            getBuilder(CoFHTags.Items.PLATES_GOLD).add(ITEMS.get("gold_plate"));
            getBuilder(CoFHTags.Items.PLATES_INVAR).add(ITEMS.get("invar_plate"));
            getBuilder(CoFHTags.Items.PLATES_IRON).add(ITEMS.get("iron_plate"));
            // getBuilder(CoFHTags.Items.PLATES_LAPIS).add(ITEMS.get("lapis_plate"));
            getBuilder(CoFHTags.Items.PLATES_LEAD).add(ITEMS.get("lead_plate"));
            getBuilder(CoFHTags.Items.PLATES_LUMIUM).add(ITEMS.get("lumium_plate"));
            getBuilder(CoFHTags.Items.PLATES_NICKEL).add(ITEMS.get("nickel_plate"));
            // getBuilder(CoFHTags.Items.PLATES_QUARTZ).add(ITEMS.get("quartz_plate"));
            // getBuilder(CoFHTags.Items.PLATES_RUBY).add(ITEMS.get("ruby_plate"));
            // getBuilder(CoFHTags.Items.PLATES_SAPPHIRE).add(ITEMS.get("sapphire_plate"));
            getBuilder(CoFHTags.Items.PLATES_SIGNALUM).add(ITEMS.get("signalum_plate"));
            getBuilder(CoFHTags.Items.PLATES_SILVER).add(ITEMS.get("silver_plate"));
            getBuilder(CoFHTags.Items.PLATES_TIN).add(ITEMS.get("tin_plate"));

            getBuilder(CoFHTags.Items.PLATES).add(
                    CoFHTags.Items.PLATES_BRONZE,
                    CoFHTags.Items.PLATES_CONSTANTAN,
                    CoFHTags.Items.PLATES_COPPER,
                    CoFHTags.Items.PLATES_ELECTRUM,
                    CoFHTags.Items.PLATES_ENDERIUM,
                    CoFHTags.Items.PLATES_INVAR,
                    CoFHTags.Items.PLATES_LEAD,
                    CoFHTags.Items.PLATES_LUMIUM,
                    CoFHTags.Items.PLATES_NICKEL,
                    CoFHTags.Items.PLATES_SIGNALUM,
                    CoFHTags.Items.PLATES_SILVER,
                    CoFHTags.Items.PLATES_TIN
            );

            getBuilder(CoFHTags.Items.TOOLS_WRENCH).add(ITEMS.get("wrench"));

            getBuilder(CoFHTags.Items.SAWDUST).add(ITEMS.get("sawdust"));

            //            getBuilder(CoFHTags.Items.DUSTS_NITER).add(ITEMS.get("niter_dust"));
            //            getBuilder(CoFHTags.Items.DUSTS_SULFUR).add(ITEMS.get("sulfur_dust"));
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
