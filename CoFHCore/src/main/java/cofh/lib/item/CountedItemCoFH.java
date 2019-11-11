package cofh.lib.item;

import net.minecraft.util.ResourceLocation;

public class CountedItemCoFH extends ItemCoFH {

    public CountedItemCoFH(Properties builder) {

        super(builder);

        this.addPropertyOverride(new ResourceLocation("count"), (stack, world, living) -> ((float) stack.getCount()) / stack.getMaxStackSize());
    }

}
