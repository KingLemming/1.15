package cofh.core.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;

public class CoreTags {

    public static class Item extends ItemTagsProvider {

        public Item(DataGenerator dataGeneratorIn) {

            super(dataGeneratorIn);
        }

        @Override
        public String getName() {

            return "CoFH Core: Item Tags";
        }

        @Override
        protected void registerTags() {

            // @formatter:off

            // @formatter:on
        }

    }

}
