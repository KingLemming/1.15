package cofh.thermal.locomotion.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.BlockTags;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.init.ThermalReferences.*;

public class TLocTags {

    public static class Block extends BlockTagsProvider {

        public Block(DataGenerator dataGeneratorIn) {

            super(dataGeneratorIn);
        }

        @Override
        public String getName() {

            return "Thermal Locomotion: Block Tags";
        }

        @Override
        protected void registerTags() {

            // @formatter:off
            getBuilder(BlockTags.RAILS).add(
                    BLOCKS.get(ID_CROSSOVER_RAIL),

                    BLOCKS.get(ID_PRISMARINE_RAIL),
                    BLOCKS.get(ID_PRISMARINE_CROSSOVER_RAIL),
                    BLOCKS.get(ID_PRISMARINE_POWERED_RAIL),
                    BLOCKS.get(ID_PRISMARINE_ACTIVATOR_RAIL),
                    BLOCKS.get(ID_PRISMARINE_DETECTOR_RAIL),

                    BLOCKS.get(ID_LUMIUM_RAIL),
                    BLOCKS.get(ID_LUMIUM_CROSSOVER_RAIL),
                    BLOCKS.get(ID_LUMIUM_POWERED_RAIL),
                    BLOCKS.get(ID_LUMIUM_ACTIVATOR_RAIL),
                    BLOCKS.get(ID_LUMIUM_DETECTOR_RAIL)
            );
            // @formatter:on
        }

    }

    public static class Item extends ItemTagsProvider {

        public Item(DataGenerator dataGeneratorIn) {

            super(dataGeneratorIn);
        }

        @Override
        public String getName() {

            return "Thermal Locomotion: Item Tags";
        }

        @Override
        protected void registerTags() {

        }

    }

    public static class Fluid extends FluidTagsProvider {

        public Fluid(DataGenerator dataGeneratorIn) {

            super(dataGeneratorIn);
        }

        @Override
        public String getName() {

            return "Thermal Locomotion: Fluid Tags";
        }

        @Override
        protected void registerTags() {

        }

    }

}
