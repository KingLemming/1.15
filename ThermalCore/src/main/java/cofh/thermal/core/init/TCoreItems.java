package cofh.thermal.core.init;

import cofh.lib.item.ArmorMaterialCoFH;
import cofh.thermal.core.common.ThermalItemGroups;
import cofh.thermal.core.item.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;

import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.common.ThermalItemGroups.THERMAL_TOOLS;
import static cofh.thermal.core.init.TCoreReferences.*;
import static cofh.thermal.core.util.RegistrationHelper.*;

public class TCoreItems {

    private TCoreItems() {

    }

    public static void register() {

        registerVanillaItems();
        registerThermalItems();
        registerTools();
    }

    private static void registerVanillaItems() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        registerMetalSet("iron", group, true);
        registerMetalSet("gold", group, true);

        registerGemSet("diamond", group, true);
        registerGemSet("emerald", group, true);
    }

    private static void registerThermalItems() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        //        registerItem("cinnabar_dust", group);
        //        registerItem("niter_dust", group);
        //        registerItem("sulfur_dust", group);
        //        registerItem("obsidian_dust", group);
        registerItem("wood_dust", group);

        registerItem("apatite", group);
        registerItem("cinnabar", group);
        registerItem("niter", group);
        registerItem("sulfur", group);

        Rarity rarity = Rarity.UNCOMMON;

        registerMetalSet("signalum", group, rarity);
        registerMetalSet("lumium", group, rarity);
        registerMetalSet("enderium", group, rarity);
    }

    private static void registerTools() {

        ItemGroup group = ThermalItemGroups.THERMAL_TOOLS;

        registerItem("wrench", () -> new WrenchItem(new Item.Properties().maxStackSize(1).group(group)));
        registerItem("lock", () -> new LockItem(new Item.Properties().group(group)));
        registerItem("phytogro", () -> new FertilizerItem(new Item.Properties().group(group)).setRadius(2));
    }

    public static void registerBeekeeperArmor() {

        if (registerBeekeeperArmor) {
            return;
        }
        registerBeekeeperArmor = true;

        ItemGroup group = THERMAL_TOOLS;

        ITEMS.register(ID_BEEKEEPER_HELMET, () -> new BeekeeperArmorItem(BEEKEEPER, EquipmentSlotType.HEAD, new Item.Properties().group(group)));
        ITEMS.register(ID_BEEKEEPER_CHESTPLATE, () -> new BeekeeperArmorItem(BEEKEEPER, EquipmentSlotType.CHEST, new Item.Properties().group(group)));
        ITEMS.register(ID_BEEKEEPER_LEGGINGS, () -> new BeekeeperArmorItem(BEEKEEPER, EquipmentSlotType.LEGS, new Item.Properties().group(group)));
        ITEMS.register(ID_BEEKEEPER_BOOTS, () -> new BeekeeperArmorItem(BEEKEEPER, EquipmentSlotType.FEET, new Item.Properties().group(group)));
    }

    public static void registerHazmatArmor() {

        if (registerHazmatArmor) {
            return;
        }
        registerHazmatArmor = true;

        ItemGroup group = THERMAL_TOOLS;

        ITEMS.register(ID_HAZMAT_HELMET, () -> new HazmatArmorItem(HAZMAT, EquipmentSlotType.HEAD, new Item.Properties().group(group)));
        ITEMS.register(ID_HAZMAT_CHESTPLATE, () -> new HazmatArmorItem(HAZMAT, EquipmentSlotType.CHEST, new Item.Properties().group(group)));
        ITEMS.register(ID_HAZMAT_LEGGINGS, () -> new HazmatArmorItem(HAZMAT, EquipmentSlotType.LEGS, new Item.Properties().group(group)));
        ITEMS.register(ID_HAZMAT_BOOTS, () -> new HazmatArmorItem(HAZMAT, EquipmentSlotType.FEET, new Item.Properties().group(group)));
    }

    private static boolean registerBeekeeperArmor;
    private static boolean registerHazmatArmor;

    public static final ArmorMaterialCoFH BEEKEEPER = new ArmorMaterialCoFH("thermal:beekeeper", 3, new int[]{1, 1, 1, 1}, 18, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, 0.0F, () -> Ingredient.fromItems(Items.STRING));
    public static final ArmorMaterialCoFH HAZMAT = new ArmorMaterialCoFH("thermal:hazmat", 4, new int[]{1, 2, 3, 1}, 18, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(Items.STRING));

}
