package cofh.core.init;

import cofh.lib.item.ItemCoFH;
import cofh.lib.item.ShearsItemCoFH;
import cofh.lib.item.override.DyeableHorseArmorItemCoFH;
import cofh.lib.item.override.HorseArmorItemCoFH;
import cofh.lib.item.override.ShieldItemCoFH;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import static cofh.core.CoFHCore.ITEMS;
import static cofh.lib.util.references.CoreReferences.ID_ECTOPLASM;

public class CoreItems {

    private CoreItems() {

    }

    public static void register() {

        ITEMS.register(ID_ECTOPLASM, () -> new ItemCoFH(new Item.Properties().group(ItemGroup.BREWING)));
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

    public static void registerShearsOverride() {

        if (overrideShears) {
            return;
        }
        overrideShears = true;

        ITEMS.register("minecraft:shears", () -> new ShearsItemCoFH((new Item.Properties()).maxDamage(238).group(ItemGroup.TOOLS)));
    }

    public static void registerShieldOverride() {

        if (overrideShield) {
            return;
        }
        overrideShield = true;

        ITEMS.register("minecraft:shield", () -> new ShieldItemCoFH((new Item.Properties()).maxDamage(336).group(ItemGroup.COMBAT)).setEnchantability(15));
    }

    private static boolean overrideHorseArmor;
    private static boolean overrideShears;
    private static boolean overrideShield;

}
