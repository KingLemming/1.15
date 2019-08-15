package cofh.ensorcellment.enchantment.override;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Map;
import java.util.Random;

import static cofh.ensorcellment.init.ConfigEnsorc.COMMON_CONFIG;
import static cofh.lib.util.constants.Constants.MAX_ENCHANT_LEVEL;

public class ThornsEnchantmentImp extends EnchantmentCoFH {

    private static int chance = 15;

    public ThornsEnchantmentImp(String id) {

        super(id, Enchantment.Rarity.VERY_RARE, EnchantmentType.ARMOR, EquipmentSlotType.values());
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {

        return 10 + 20 * (enchantmentLevel - 1);
    }

    public int getMaxEnchantability(int enchantmentLevel) {

        return super.getMinEnchantability(enchantmentLevel) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof ArmorItem || item instanceof HorseArmorItem || item.isShield(stack, null) || supportsEnchantment(stack));
    }

    // region HELPERS
    @Override
    public void onUserHurt(LivingEntity user, Entity attacker, int level) {

        Random rand = user.getRNG();
        Map.Entry<EquipmentSlotType, ItemStack> stack = EnchantmentHelper.func_222189_b(Enchantments.THORNS, user);
        if (shouldHit(level, rand)) {
            if (attacker != null) {
                attacker.attackEntityFrom(DamageSource.causeThornsDamage(user), (float) ThornsEnchantment.getDamage(level, rand));
            }
            if (stack != null) {
                (stack.getValue()).damageItem(3, user, (consumer) -> consumer.sendBreakAnimation(stack.getKey()));
            }
        } else if (stack != null) {
            (stack.getValue()).damageItem(1, user, (consumer) -> consumer.sendBreakAnimation(stack.getKey()));
        }

    }

    public static boolean shouldHit(int level, Random rand) {

        return rand.nextInt(100) < chance * level;
    }
    // endregion

    // region IConfigSupport
    private ForgeConfigSpec.BooleanValue cfgEnable;
    private ForgeConfigSpec.IntValue cfgLevel;
    private ForgeConfigSpec.IntValue cfgChance;

    @Override
    public void genConfig() {

        COMMON_CONFIG.push("Override");
        COMMON_CONFIG.push("Thorns");

        String comment = "If TRUE, the Thorns Enchantment is replaced with a more configurable version which works on more items, such as Shields and Horse Armor.";
        cfgEnable = COMMON_CONFIG.comment(comment).define("Enable", true);

        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        cfgLevel = COMMON_CONFIG.comment(comment).defineInRange("Max Level", maxLevel, 1, MAX_ENCHANT_LEVEL);

        comment = "Adjust this value to set the chance per level of the Enchantment firing (in percentage).";
        cfgChance = COMMON_CONFIG.comment(comment).defineInRange("Effect Chance", chance, 1, 100);

        COMMON_CONFIG.pop(2);
    }

    @Override
    public void refreshConfig() {

        enable = cfgEnable.get();
        maxLevel = cfgLevel.get();
        chance = cfgChance.get();
    }
    // endregion
}
