package cofh.thermal.core.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.data.ItemTagsProvider;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.ThermalReferences.*;
import static cofh.thermal.core.init.ThermalTags.Blocks.*;
import static cofh.thermal.core.init.ThermalTags.Items.*;

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

            getBuilder(STORAGE_BLOCKS_SIGNALUM).add(BLOCKS.get(ID_SIGNALUM_BLOCK));
            getBuilder(STORAGE_BLOCKS_LUMIUM).add(BLOCKS.get(ID_LUMIUM_BLOCK));
            getBuilder(STORAGE_BLOCKS_ENDERIUM).add(BLOCKS.get(ID_ENDERIUM_BLOCK));
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

            getBuilder(COINS_SIGNALUM).add(ITEMS.get("signalum_coin"));
            getBuilder(COINS_LUMIUM).add(ITEMS.get("lumium_coin"));
            getBuilder(COINS_ENDERIUM).add(ITEMS.get("enderium_coin"));

            getBuilder(GEARS_SIGNALUM).add(ITEMS.get("signalum_gear"));
            getBuilder(GEARS_LUMIUM).add(ITEMS.get("lumium_gear"));
            getBuilder(GEARS_ENDERIUM).add(ITEMS.get("enderium_gear"));

            getBuilder(INGOTS_SIGNALUM).add(ITEMS.get("signalum_ingot"));
            getBuilder(INGOTS_LUMIUM).add(ITEMS.get("lumium_ingot"));
            getBuilder(INGOTS_ENDERIUM).add(ITEMS.get("enderium_ingot"));

            getBuilder(NUGGETS_SIGNALUM).add(ITEMS.get("signalum_nugget"));
            getBuilder(NUGGETS_LUMIUM).add(ITEMS.get("lumium_nugget"));
            getBuilder(NUGGETS_ENDERIUM).add(ITEMS.get("enderium_nugget"));

            getBuilder(PLATES_SIGNALUM).add(ITEMS.get("signalum_plate"));
            getBuilder(PLATES_LUMIUM).add(ITEMS.get("lumium_plate"));
            getBuilder(PLATES_ENDERIUM).add(ITEMS.get("enderium_plate"));
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
