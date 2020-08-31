package cofh.omgourd.init;

import cofh.core.block.CarvedPumpkinBlockCoFH;
import cofh.core.item.BlockItemCoFH;
import cofh.omgourd.OMGourd;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import static cofh.omgourd.OMGourd.BLOCKS;
import static cofh.omgourd.OMGourd.ITEMS;
import static net.minecraft.block.Block.Properties.create;

public class OMGBlocks {

    private OMGBlocks() {

    }

    public static void register() {

        for (int i = 1; i <= 24; ++i) {
            BLOCKS.register("carved_pumpkin_" + i, () -> new CarvedPumpkinBlockCoFH(create(Material.GOURD, MaterialColor.ADOBE).hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
            BLOCKS.register("jack_o_lantern_" + i, () -> new CarvedPumpkinBlockCoFH(create(Material.GOURD, MaterialColor.ADOBE).hardnessAndResistance(1.0F).sound(SoundType.WOOD).lightValue(15)));

            int j = i;
            ITEMS.register("carved_pumpkin_" + j, () -> new BlockItemCoFH(BLOCKS.get("carved_pumpkin_" + j), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setDisplayGroup(() -> OMGourd.itemGroup));
            ITEMS.register("jack_o_lantern_" + j, () -> new BlockItemCoFH(BLOCKS.get("jack_o_lantern_" + j), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setDisplayGroup(() -> OMGourd.itemGroup));
        }
        BLOCKS.register("minecraft:carved_pumpkin", () -> new CarvedPumpkinBlockCoFH(create(Material.GOURD, MaterialColor.ADOBE).hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
        BLOCKS.register("minecraft:jack_o_lantern", () -> new CarvedPumpkinBlockCoFH(create(Material.GOURD, MaterialColor.ADOBE).hardnessAndResistance(1.0F).sound(SoundType.WOOD).lightValue(15)));

        ITEMS.register("minecraft:carved_pumpkin", () -> new BlockItem(BLOCKS.get("minecraft:carved_pumpkin"), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
        ITEMS.register("minecraft:jack_o_lantern", () -> new BlockItem(BLOCKS.get("minecraft:jack_o_lantern"), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    }

    public static void setup() {

        CarvedPumpkinBlockCoFH pumpkin;
        CarvedPumpkinBlockCoFH lantern;

        // NEXT
        for (int i = 2; i <= 23; ++i) {
            pumpkin = (CarvedPumpkinBlockCoFH) BLOCKS.get("carved_pumpkin_" + i);
            pumpkin.setCarvePrev(BLOCKS.getSup("carved_pumpkin_" + (i - 1)));
            pumpkin.setCarveNext(BLOCKS.getSup("carved_pumpkin_" + (i + 1)));

            lantern = (CarvedPumpkinBlockCoFH) BLOCKS.get("jack_o_lantern_" + i);
            lantern.setCarvePrev(BLOCKS.getSup("jack_o_lantern_" + (i - 1)));
            lantern.setCarveNext(BLOCKS.getSup("jack_o_lantern_" + (i + 1)));
        }
        // ENDS
        pumpkin = (CarvedPumpkinBlockCoFH) BLOCKS.get("carved_pumpkin_" + 1);
        pumpkin.setCarvePrev(BLOCKS.getSup("minecraft:carved_pumpkin"));
        pumpkin.setCarveNext(BLOCKS.getSup("carved_pumpkin_" + 2));

        lantern = (CarvedPumpkinBlockCoFH) BLOCKS.get("jack_o_lantern_" + 1);
        lantern.setCarvePrev(BLOCKS.getSup("minecraft:jack_o_lantern"));
        lantern.setCarveNext(BLOCKS.getSup("jack_o_lantern_" + 2));

        pumpkin = (CarvedPumpkinBlockCoFH) BLOCKS.get("carved_pumpkin_" + 24);
        pumpkin.setCarvePrev(BLOCKS.getSup("carved_pumpkin_" + 23));
        pumpkin.setCarveNext(BLOCKS.getSup("minecraft:carved_pumpkin"));

        lantern = (CarvedPumpkinBlockCoFH) BLOCKS.get("jack_o_lantern_" + 24);
        lantern.setCarvePrev(BLOCKS.getSup("jack_o_lantern_" + 23));
        lantern.setCarveNext(BLOCKS.getSup("minecraft:jack_o_lantern"));

        // VANILLA
        pumpkin = (CarvedPumpkinBlockCoFH) BLOCKS.get("minecraft:carved_pumpkin");
        pumpkin.setCarvePrev(BLOCKS.getSup("carved_pumpkin_" + 24));
        pumpkin.setCarveNext(BLOCKS.getSup("carved_pumpkin_" + 1));

        lantern = (CarvedPumpkinBlockCoFH) BLOCKS.get("minecraft:jack_o_lantern");
        lantern.setCarvePrev(BLOCKS.getSup("jack_o_lantern_" + 24));
        lantern.setCarveNext(BLOCKS.getSup("jack_o_lantern_" + 1));
    }

}
