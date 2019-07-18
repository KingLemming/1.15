package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.util.Utils;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.util.FakePlayer;

import java.util.Random;

import static cofh.ensorcellment.init.ConfigEnsorc.COMMON_CONFIG;
import static cofh.lib.util.Constants.MAX_ENCHANT_LEVEL;

public class DisplacementEnchantment extends EnchantmentCoFH {

    protected static int chance = 20;

    public DisplacementEnchantment(String id) {

        super(id, Rarity.RARE, EnchantmentType.ARMOR, EquipmentSlotType.values());
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {

        return 5 + 10 * (enchantmentLevel - 1);
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

        if (level <= 0 || !(attacker instanceof LivingEntity) || attacker instanceof FakePlayer) {
            return;
        }
        Random rand = user.getRNG();
        if (shouldHit(level, rand)) {
            teleportEntity(level, rand, attacker);
        }
    }

    public static boolean teleportEntity(int level, Random rand, Entity attacker) {

        if (!(attacker instanceof LivingEntity) || attacker instanceof FakePlayer) {
            return false;
        }
        int radius = 8 * (2 ^ level);
        int bound = radius * 2 + 1;
        BlockPos pos = new BlockPos(attacker.posX, attacker.posY, attacker.posZ);
        BlockPos randPos = pos.add(-radius + rand.nextInt(bound), rand.nextInt(8), -radius + rand.nextInt(bound));
        Utils.teleportEntityTo(attacker, randPos);
        return true;
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

        COMMON_CONFIG.push("Enchantment");
        COMMON_CONFIG.push("Displacement");

        String comment = "If TRUE, the Displacement Enchantment is available for Armor, Shields, and Horse Armor.";
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
