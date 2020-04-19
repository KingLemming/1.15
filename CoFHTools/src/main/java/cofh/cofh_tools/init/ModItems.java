package cofh.cofh_tools.init;

import cofh.lib.item.*;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.item.*;

import static cofh.cofh_tools.CoFHTools.ITEMS;

public class ModItems {

    private ModItems() {

    }

    public static void register() {

        registerStandardToolSet("copper", ItemTier.IRON, ItemGroup.TOOLS, ItemGroup.COMBAT);
        registerStandardToolSet("tin", ItemTier.IRON, ItemGroup.TOOLS, ItemGroup.COMBAT);
        registerStandardToolSet("silver", ItemTier.IRON, ItemGroup.TOOLS, ItemGroup.COMBAT);
        registerStandardToolSet("lead", ItemTier.IRON, ItemGroup.TOOLS, ItemGroup.COMBAT);
        registerStandardToolSet("nickel", ItemTier.IRON, ItemGroup.TOOLS, ItemGroup.COMBAT);
        registerStandardToolSet("platinum", ItemTier.IRON, ItemGroup.TOOLS, ItemGroup.COMBAT);

        registerStandardToolSet("bronze", ItemTier.IRON, ItemGroup.TOOLS, ItemGroup.COMBAT);
        registerStandardToolSet("electrum", ItemTier.IRON, ItemGroup.TOOLS, ItemGroup.COMBAT);
        registerStandardToolSet("invar", ItemTier.IRON, ItemGroup.TOOLS, ItemGroup.COMBAT);
        registerStandardToolSet("constantan", ItemTier.IRON, ItemGroup.TOOLS, ItemGroup.COMBAT);

    }

    // region HELPERS
    private static void registerStandardToolSet(String prefix, IItemTier tier, ItemGroup toolGroup, ItemGroup combatGroup) {

        ITEMS.register(prefix + "_shovel", () -> new ShovelItem(tier, 1.5F, -3.0F, new Item.Properties().group(toolGroup)));
        ITEMS.register(prefix + "_pickaxe", () -> new PickaxeItem(tier, 1, -2.8F, new Item.Properties().group(toolGroup)));
        ITEMS.register(prefix + "_axe", () -> new AxeItem(tier, tier.getAttackDamage() > 0 ? 8.0F - tier.getAttackDamage() : 6.0F, MathHelper.clamp(-3.7F + tier.getEfficiency() / 10, -3.2F, -3.0F), new Item.Properties().group(toolGroup)));
        ITEMS.register(prefix + "_hoe", () -> new HoeItem(tier, -3.0F + tier.getHarvestLevel(), new Item.Properties().group(toolGroup)));

        ITEMS.register(prefix + "_sword", () -> new SwordItem(tier, 3, -2.4F, new Item.Properties().group(combatGroup)));
    }

    private static void registerExtraToolSet(String prefix, IItemTier tier, ItemGroup toolGroup) {

        ITEMS.register(prefix + "_excavator", () -> new ExcavatorItem(tier, new Item.Properties().group(toolGroup)));
        ITEMS.register(prefix + "_hammer", () -> new HammerItem(tier, new Item.Properties().group(toolGroup)));
        ITEMS.register(prefix + "_sickle", () -> new SickleItem(tier, new Item.Properties().group(toolGroup)));
    }

    private static void registerSpecial(String prefix, IItemTier tier, ItemGroup toolGroup) {

        ITEMS.register(prefix + "_shears", () -> new ShearsItemCoFH(tier, new Item.Properties().group(toolGroup)));
        ITEMS.register(prefix + "_bow", () -> new BowItemCoFH(tier, new Item.Properties().group(toolGroup)));
        ITEMS.register(prefix + "_fishing_rod", () -> new FishingRodItemCoFH(tier, new Item.Properties().group(toolGroup)));
    }
    // endregion
}
