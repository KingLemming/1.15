package cofh.ensorcellment.enchantment.nyi;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class SmashingEnchantment extends EnchantmentCoFH {

    public SmashingEnchantment() {

        super(Rarity.RARE, EnchantmentType.DIGGER, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMinEnchantability(int level) {

        return 15;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        // TODO: Fix
        //        if (smashList.isEmpty()) {
        //            return false;
        //        }
        Item item = stack.getItem();
        return enable && (item.getToolTypes(stack).contains(ToolType.PICKAXE) || supportsEnchantment(stack));
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != Enchantments.SILK_TOUCH;
    }

    // TODO: Fix
    public static ItemStack getItemStack(World world, ItemStack stack) {

        //        SmashConversion result = smashList.get(getOreName(stack));
        //        if (result == null) {
        //            return ItemStack.EMPTY;
        //        }
        //        ItemStack ret = result.toItemStack();
        //        return ret.isEmpty() ? ItemStack.EMPTY : cloneStack(ret, ret.getCount() * stack.getCount());
        return stack.copy();
    }

    // region CONVERSION
    //    public static final int ORE_MULTIPLIER = 2;
    //    public static final int ORE_MULTIPLIER_SPECIAL = 3;
    //    public static Map<String, SmashConversion> smashList = new Object2ObjectOpenHashMap<>();
    //
    //    public static class SmashConversion {
    //
    //        final String ore;
    //        final int count;
    //
    //        SmashConversion(String ore, int count) {
    //
    //            this.ore = ore;
    //            this.count = count;
    //        }
    //
    //        ItemStack toItemStack() {
    //
    //            return getOre(ore, count);
    //        }
    //
    //    }
    //
    //
    //    public static void initialize() {
    //
    //        /* GENERAL SCAN */
    //        {
    //            String oreSuffix;
    //            for (String oreName : OreDictionary.getOreNames()) {
    //                if (oreName.startsWith(PREFIX_ORE) || oreName.startsWith(PREFIX_GEM)) {
    //                    oreSuffix = oreName.substring(3);
    //                    addConversions(oreSuffix);
    //                } else if (oreName.startsWith(PREFIX_DUST)) {
    //                    oreSuffix = oreName.substring(4);
    //                    addConversions(oreSuffix);
    //                }
    //            }
    //        }
    //    }
    //
    //    private static void addConversions(String oreSuffix) {
    //
    //        if (oreSuffix == null || oreSuffix.isEmpty()) {
    //            return;
    //        }
    //        oreSuffix = titleCase(oreSuffix);
    //
    //        String oreName = PREFIX_ORE + oreSuffix;
    //        String gemName = PREFIX_GEM + oreSuffix;
    //        String dustName = PREFIX_DUST + oreSuffix;
    //
    //        String oreNetherName = "oreNether" + oreSuffix;
    //        String oreEndName = "oreEnd" + oreSuffix;
    //
    //        if (oreNameExists(gemName)) {
    //            addConversion(oreName, gemName, ORE_MULTIPLIER);
    //            addConversion(oreNetherName, gemName, ORE_MULTIPLIER_SPECIAL);
    //            addConversion(oreEndName, gemName, ORE_MULTIPLIER_SPECIAL);
    //        } else if (oreNameExists(dustName)) {
    //            addConversion(oreName, dustName, ORE_MULTIPLIER);
    //            addConversion(oreNetherName, dustName, ORE_MULTIPLIER_SPECIAL);
    //            addConversion(oreEndName, dustName, ORE_MULTIPLIER_SPECIAL);
    //        }
    //    }
    //
    //    private static boolean addConversion(String oreName, String resultName, int count) {
    //
    //        if (oreName.isEmpty() || resultName.isEmpty() || count <= 0 || smashList.containsKey(oreName)) {
    //            return false;
    //        }
    //        smashList.put(oreName, new SmashConversion(resultName, count));
    //        return true;
    //    }
    // endregion
}
