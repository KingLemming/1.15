package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.util.Utils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Map;
import java.util.Random;

import static cofh.lib.util.constants.Constants.ARMOR_SLOTS;
import static cofh.lib.util.references.EnsorcellationReferences.*;
import static net.minecraft.enchantment.Enchantments.THORNS;

public class DisplacementEnchantment extends EnchantmentCoFH {

    public static int chance = 20;

    public DisplacementEnchantment() {

        super(Rarity.RARE, EnchantmentType.ARMOR_CHEST, ARMOR_SLOTS);
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 5 + 10 * (level - 1);
    }

    @Override
    protected int maxDelegate(int level) {

        return super.getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof ArmorItem || item instanceof HorseArmorItem || item.isShield(stack, null) || supportsEnchantment(stack));
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != THORNS && ench != FIRE_REBUKE && ench != FROST_REBUKE;
    }

    // region HELPERS
    @Override
    public void onUserHurt(LivingEntity user, Entity attacker, int level) {

        if (!(attacker instanceof LivingEntity)) {
            return;
        }
        Map.Entry<EquipmentSlotType, ItemStack> stack = EnchantmentHelper.func_222189_b(DISPLACEMENT, user);
        if (shouldHit(level, user.getRNG())) {
            onHit(user, attacker, level);
            if (stack != null) {
                (stack.getValue()).damageItem(2, user, (consumer) -> consumer.sendBreakAnimation(stack.getKey()));
            }
        }
    }

    public static void onHit(LivingEntity user, Entity attacker, int level) {

        if (!(attacker instanceof LivingEntity)) {
            return;
        }
        Random rand = user.getRNG();
        int radius = 8 * (2 ^ level);
        int bound = radius * 2 + 1;
        BlockPos pos = new BlockPos(attacker.posX, attacker.posY, attacker.posZ);
        BlockPos randPos = pos.add(-radius + rand.nextInt(bound), rand.nextInt(8), -radius + rand.nextInt(bound));
        if (attacker.world instanceof ServerWorld && Utils.teleportEntityTo(attacker, randPos)) {
            for (int j = 0; j < 3 * level; ++j) {
                Utils.spawnParticles(attacker.world, ParticleTypes.PORTAL, attacker.posX + rand.nextDouble(), attacker.posY + 1.0D + rand.nextDouble(), attacker.posZ + rand.nextDouble(), 1, 0, 0, 0, 0);
                Utils.spawnParticles(attacker.world, ParticleTypes.PORTAL, randPos.getX() + rand.nextDouble(), randPos.getY() + 1.0D + rand.nextDouble(), randPos.getZ() + rand.nextDouble(), 1, 0, 0, 0, 0);
            }
        }
    }

    public static boolean shouldHit(int level, Random rand) {

        return rand.nextInt(100) < chance * level;
    }
    // endregion
}
