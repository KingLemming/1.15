package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.server.ServerWorld;

public class VorpalEnchantment extends EnchantmentCoFH {

    public static int critBase = 5;
    public static int critLevel = 5;
    public static int critDamage = 5;
    public static int headBase = 10;
    public static int headLevel = 10;

    public VorpalEnchantment() {

        super(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 15 + (level - 1) * 9;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof SwordItem || item instanceof AxeItem || supportsEnchantment(stack));
    }

    // region HELPERS
    public static void onHit(LivingEntity entity, int level) {

        entity.world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, SoundCategory.PLAYERS, 1.0F, 1.0F);
        for (int i = 0; i < 2 * level; ++i) {
            ((ServerWorld) entity.world).spawnParticle(ParticleTypes.CRIT, entity.posX + entity.world.rand.nextDouble(), entity.posY + 1.0D + entity.world.rand.nextDouble(), entity.posZ + entity.world.rand.nextDouble(), 1, 0, 0, 0, 0);
            ((ServerWorld) entity.world).spawnParticle(ParticleTypes.SWEEP_ATTACK, entity.posX + entity.world.rand.nextDouble(), entity.posY + 1.0D + entity.world.rand.nextDouble(), entity.posZ + entity.world.rand.nextDouble(), 1, 0, 0, 0, 0);
        }
    }
    // endregion

}
