package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.util.Utils;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;

import java.util.Random;

import static cofh.lib.util.constants.Constants.ARMOR_SLOTS;

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
        ((ServerWorld) attacker.world).spawnParticle(ParticleTypes.PORTAL, attacker.posX + attacker.world.rand.nextDouble(), attacker.posY + 1.0D + attacker.world.rand.nextDouble(), attacker.posZ + attacker.world.rand.nextDouble(), 4 * level, 0, 0, 0, 0);
        return true;
    }

    public static boolean shouldHit(int level, Random rand) {

        return rand.nextInt(100) < chance * level;
    }
    // endregion
}
