package cofh.core.init;

import cofh.lib.item.DyeableHorseArmorItemCoFH;
import cofh.lib.item.HorseArmorItemCoFH;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import static cofh.core.CoFHCore.ITEMS;

public class ItemsCore {

    private ItemsCore() {

    }

    private static boolean overrideHorseArmor;

    public static void registerHorseArmorOverrides() {

        if (overrideHorseArmor) {
            return;
        }
        overrideHorseArmor = true;

        ITEMS.register("minecraft:iron_horse_armor", () -> new HorseArmorItemCoFH(5, "iron", (new Item.Properties()).maxStackSize(1).group(ItemGroup.MISC)).setEnchantability(9));
        ITEMS.register("minecraft:gold_horse_armor", () -> new HorseArmorItemCoFH(7, "gold", (new Item.Properties()).maxStackSize(1).group(ItemGroup.MISC)).setEnchantability(25));
        ITEMS.register("minecraft:diamond_horse_armor", () -> new HorseArmorItemCoFH(11, "diamond", (new Item.Properties()).maxStackSize(1).group(ItemGroup.MISC)).setEnchantability(10));
        ITEMS.register("minecraft:leather_horse_armor", () -> new DyeableHorseArmorItemCoFH(3, "leather", (new Item.Properties()).maxStackSize(1).group(ItemGroup.MISC)).setEnchantability(15));
    }

    public static void registerPotionOverrides() {

    }

}
