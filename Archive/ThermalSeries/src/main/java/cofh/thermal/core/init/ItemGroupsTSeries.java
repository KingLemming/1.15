package cofh.thermal.core.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static cofh.lib.util.constants.Constants.ID_THERMAL_SERIES;

public class ItemGroupsTSeries {

    private ItemGroupsTSeries() {

    }

    public static final ItemGroup THERMAL_BLOCKS = new ItemGroup(-1, ID_THERMAL_SERIES + ".blocks") {

        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {

            return new ItemStack(Items.REDSTONE);
        }
    };

    public static final ItemGroup THERMAL_ITEMS = new ItemGroup(-1, ID_THERMAL_SERIES + ".items") {

        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {

            return new ItemStack(Items.REDSTONE);
        }
    };

    public static final ItemGroup THERMAL_TOOLS = new ItemGroup(-1, ID_THERMAL_SERIES + ".tools") {

        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {

            return new ItemStack(Items.REDSTONE);
        }
    };

    public static ItemGroup THERMAL_MISC;

}
