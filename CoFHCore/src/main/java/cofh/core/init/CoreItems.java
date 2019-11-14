package cofh.core.init;

import cofh.lib.item.override.DyeableHorseArmorItemCoFH;
import cofh.lib.item.override.HorseArmorItemCoFH;
import cofh.lib.item.override.ShieldItemCoFH;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import static cofh.core.CoFHCore.ITEMS;

public class CoreItems {

    private CoreItems() {

    }

    private static boolean overrideHorseArmor;
    private static boolean overrideShield;

    public static void register() {

    }

    public static void registerHorseArmorOverrides() {

        if (overrideHorseArmor) {
            return;
        }
        overrideHorseArmor = true;

        ITEMS.register("minecraft:iron_horse_armor", () -> new HorseArmorItemCoFH(5, "iron", (new Item.Properties()).maxStackSize(1).group(ItemGroup.MISC)).setEnchantability(9));
        ITEMS.register("minecraft:golden_horse_armor", () -> new HorseArmorItemCoFH(7, "gold", (new Item.Properties()).maxStackSize(1).group(ItemGroup.MISC)).setEnchantability(25));
        ITEMS.register("minecraft:diamond_horse_armor", () -> new HorseArmorItemCoFH(11, "diamond", (new Item.Properties()).maxStackSize(1).group(ItemGroup.MISC)).setEnchantability(10));
        ITEMS.register("minecraft:leather_horse_armor", () -> new DyeableHorseArmorItemCoFH(3, "leather", (new Item.Properties()).maxStackSize(1).group(ItemGroup.MISC)).setEnchantability(15));
    }

    public static void registerShieldOverride() {

        if (overrideShield) {
            return;
        }
        overrideShield = true;

        ITEMS.register("minecraft:shield", () -> new ShieldItemCoFH((new Item.Properties()).maxDamage(336).group(ItemGroup.COMBAT)).setEnchantability(15));
    }

}
