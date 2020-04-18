package cofh.thermal.expansion.data;

import cofh.thermal.core.data.ThermalTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.data.ItemTagsProvider;

import static cofh.thermal.core.ThermalCore.ITEMS;

public class TExpTags {

    public static class Block extends BlockTagsProvider {

        public Block(DataGenerator dataGeneratorIn) {

            super(dataGeneratorIn);
        }

        @Override
        public String getName() {

            return "Thermal Expansion: Block Tags";
        }

        @Override
        protected void registerTags() {

        }

    }

    public static class Item extends ItemTagsProvider {

        public Item(DataGenerator dataGeneratorIn) {

            super(dataGeneratorIn);
        }

        @Override
        public String getName() {

            return "Thermal Expansion: Item Tags";
        }

        @Override
        protected void registerTags() {

            getBuilder(ThermalTags.Items.MACHINE_DIES).add(ITEMS.get("press_coin_die"));
            getBuilder(ThermalTags.Items.MACHINE_DIES).add(ITEMS.get("press_gear_die"));
        }

    }

    public static class Fluid extends FluidTagsProvider {

        public Fluid(DataGenerator dataGeneratorIn) {

            super(dataGeneratorIn);
        }

        @Override
        public String getName() {

            return "Thermal Expansion: Fluid Tags";
        }

        @Override
        protected void registerTags() {

        }

    }

}

