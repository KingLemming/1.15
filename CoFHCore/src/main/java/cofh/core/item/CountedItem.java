package cofh.core.item;

import net.minecraft.util.ResourceLocation;

public class CountedItem extends ItemCoFH {

    public CountedItem(Properties builder) {

        super(builder);

        this.addPropertyOverride(new ResourceLocation("count"), (stack, world, living) -> ((float) stack.getCount()) / stack.getMaxStackSize());
    }

}
