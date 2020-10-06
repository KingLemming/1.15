package cofh.redstonearsenal.init;

import cofh.redstonearsenal.item.*;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.item.crafting.Ingredient;

import static cofh.redstonearsenal.RedstoneArsenal.ITEMS;

public class RSAItems {

    private RSAItems() {

    }

    public static void register() {

        ItemGroup group = ItemGroup.COMBAT;
        Rarity rarity = Rarity.UNCOMMON;

        int energy = 320000;
        int xfer = 4000;

        //        ITEMS.register("flux_infused_gem", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        //        ITEMS.register("flux_infused_ingot", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        //        ITEMS.register("flux_infused_nugget", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        //        ITEMS.register("flux_infused_dust", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        //        ITEMS.register("flux_infused_gear", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        //        ITEMS.register("flux_infused_plate", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));
        //        ITEMS.register("flux_infused_coin", () -> new ItemCoFH(new Item.Properties().group(group).rarity(rarity)));

        ITEMS.register("flux_sword", () -> new FluxSwordItem(MATERIAL_FLUX_METAL, 3, -2.4F, new Item.Properties().maxStackSize(1).group(group), energy, xfer));
        ITEMS.register("flux_shovel", () -> new FluxShovelItem(MATERIAL_FLUX_METAL, 1.5F, -3.0F, new Item.Properties().maxStackSize(1).group(group), energy, xfer));
        ITEMS.register("flux_pickaxe", () -> new FluxPickaxeItem(MATERIAL_FLUX_METAL, 1, -2.8F, new Item.Properties().maxStackSize(1).group(group), energy, xfer));
        ITEMS.register("flux_axe", () -> new FluxAxeItem(MATERIAL_FLUX_METAL, 6.0F, -3.0F, new Item.Properties().maxStackSize(1).group(group), energy, xfer));
        ITEMS.register("flux_hoe", () -> new FluxHoeItem(MATERIAL_FLUX_METAL, -1.0F, new Item.Properties().maxStackSize(1).group(group), energy, xfer));
    }

    public static final IItemTier MATERIAL_FLUX_METAL = new IItemTier() {

        @Override
        public int getMaxUses() {

            return 0;
        }

        @Override
        public float getEfficiency() {

            return 8.0F;
        }

        @Override
        public float getAttackDamage() {

            return 1.0F;
        }

        @Override
        public int getHarvestLevel() {

            return 4;
        }

        @Override
        public int getEnchantability() {

            return 18;
        }

        @Override
        public Ingredient getRepairMaterial() {

            return Ingredient.EMPTY;
        }
    };

}
