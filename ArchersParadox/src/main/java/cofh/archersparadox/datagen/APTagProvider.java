package cofh.archersparadox.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.ItemTags;

import static cofh.archersparadox.init.APReferences.*;

public class APTagProvider {

    public static class Item extends ItemTagsProvider {

        public Item(DataGenerator dataGeneratorIn) {

            super(dataGeneratorIn);
        }

        @Override
        public String getName() {

            return "Archer's Paradox: Item Tags";
        }

        @Override
        protected void registerTags() {

            getBuilder(ItemTags.ARROWS).add(
                    BLAZE_ARROW_ITEM,
                    CHALLENGE_ARROW_ITEM,
                    DIAMOND_ARROW_ITEM,
                    DISPLACEMENT_ARROW_ITEM,
                    ENDER_ARROW_ITEM,
                    EXPLOSIVE_ARROW_ITEM,
                    FROST_ARROW_ITEM,
                    GLOWSTONE_ARROW_ITEM,
                    LIGHTNING_ARROW_ITEM,
                    PHANTASMAL_ARROW_ITEM,
                    PRISMARINE_ARROW_ITEM,
                    QUARTZ_ARROW_ITEM,
                    REDSTONE_ARROW_ITEM,
                    SHULKER_ARROW_ITEM,
                    SLIME_ARROW_ITEM,
                    SPORE_ARROW_ITEM,
                    TRAINING_ARROW_ITEM,
                    VERDANT_ARROW_ITEM
            );
        }

    }

}
