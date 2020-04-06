package cofh.thermal.core.util.recipes.internal;

import net.minecraft.item.ItemStack;

public class SimpleItemFuel extends AbstractFuel {

    public SimpleItemFuel(ItemStack input, int energy) {

        super(energy);
        this.inputItems.add(input);
    }

}
