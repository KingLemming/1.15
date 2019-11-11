package cofh.lib.util.helpers;

import cofh.lib.capability.IArcheryAmmoItem;
import cofh.lib.capability.IArcheryBowItem;
import cofh.lib.util.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

import static cofh.lib.capability.CapabilityArchery.*;
import static cofh.lib.util.constants.Constants.MAX_ENCHANT_LEVEL;
import static cofh.lib.util.references.EnsorcellationReferences.TRUESHOT;
import static cofh.lib.util.references.EnsorcellationReferences.VOLLEY;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.enchantment.Enchantments.*;

public final class ArcheryHelper {

    private ArcheryHelper() {

    }

    public static boolean validBow(ItemStack stack) {

        return stack.getItem() == Items.BOW || stack.getCapability(BOW_ITEM_CAPABILITY).isPresent();
    }

    public static boolean isArrow(ItemStack stack) {

        return stack.getItem() instanceof ArrowItem;
    }

    /**
     * Basically the "default" Archery behavior.
     */
    public static boolean fireArrow(ItemStack bow, ItemStack ammo, PlayerEntity shooter, int charge, World world) {

        IArcheryBowItem bowObj = bow.getCapability(BOW_ITEM_CAPABILITY).orElse(DEFAULT_BOW_CAPABILITY);
        IArcheryAmmoItem ammoObj = ammo.getCapability(AMMO_ITEM_CAPABILITY).orElse(DEFAULT_AMMO_CAPABILITY);

        boolean infinite = shooter.abilities.isCreativeMode || (isArrow(ammo) && ((ArrowItem) ammo.getItem()).isInfinite(ammo, bow, shooter)) || ammo.isEmpty() && getEnchantmentLevel(INFINITY, bow) > 0;

        if (!ammo.isEmpty() || infinite) {
            if (ammo.isEmpty()) {
                ammo = new ItemStack(Items.ARROW);
            }
            float arrowVelocity = BowItem.getArrowVelocity(charge);

            float accuracyMod = bowObj.getAccuracyModifier(bow, ammo, shooter) * ammoObj.getAccuracyModifier(bow, ammo, shooter);
            float damageMod = bowObj.getDamageModifier(bow, ammo, shooter) * ammoObj.getDamageModifier(bow, ammo, shooter);
            float velocityMod = bowObj.getVelocityModifier(bow, ammo, shooter) * ammoObj.getVelocityModifier(bow, ammo, shooter);

            if (arrowVelocity >= 0.1F) {
                if (Utils.isServerWorld(world)) {
                    int encVolley = MathHelper.clamp(getEnchantmentLevel(VOLLEY, bow), 0, MAX_ENCHANT_LEVEL);
                    int encTrueshot = getEnchantmentLevel(TRUESHOT, bow);
                    int encPunch = getEnchantmentLevel(PUNCH, bow);
                    int encPower = getEnchantmentLevel(POWER, bow);
                    int encFlame = getEnchantmentLevel(FLAME, bow);

                    if (encTrueshot > 0) {
                        accuracyMod *= (1.5F / (1 + encTrueshot));
                        damageMod *= (1.0F + 0.25F * encTrueshot);
                        arrowVelocity = MathHelper.clamp(0.1F, arrowVelocity + 0.10F * encTrueshot, 1.75F);
                    }
                    for (int shot = 0; shot <= encVolley; shot++) {
                        AbstractArrowEntity arrow = createArrow(world, bow, ammo, shooter);
                        arrow.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0.0F, arrowVelocity * 3.0F * velocityMod, accuracyMod * (1 + shot * 2));
                        arrow.setDamage(arrow.getDamage() * damageMod);

                        if (arrowVelocity >= 1.0F) {
                            arrow.setIsCritical(true);
                        }
                        if (encTrueshot > 0) {
                            arrow.setPierceLevel((byte) encTrueshot);
                        }
                        if (encPower > 0) {
                            arrow.setDamage(arrow.getDamage() + (double) encPower * 0.5D + 0.5D);
                        }
                        if (encPunch > 0) {
                            arrow.setKnockbackStrength(encPunch);
                        }
                        if (encFlame > 0) {
                            arrow.setFire(100);
                        }
                        if (infinite || shot > 0) {
                            arrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        }
                        world.addEntity(arrow);
                    }
                    bowObj.onArrowLoosed(bow, ammo, shooter);
                }
                world.playSound(null, shooter.posX, shooter.posY, shooter.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.rand.nextFloat() * 0.4F + 1.2F) + arrowVelocity * 0.5F);

                if (!infinite && !shooter.abilities.isCreativeMode) {
                    ammoObj.onArrowLoosed(bow, ammo, shooter);
                    if (ammo.isEmpty()) {
                        shooter.inventory.deleteStack(ammo);
                    }
                }
                shooter.addStat(Stats.ITEM_USED.get(bow.getItem()));
            }
            return true;
        }
        return false;
    }

    public static AbstractArrowEntity createDefaultArrow(World world, ItemStack ammo, PlayerEntity shooter) {

        return isArrow(ammo) ? ((ArrowItem) ammo.getItem()).createArrow(world, ammo, shooter) : ((ArrowItem) Items.ARROW).createArrow(world, ammo, shooter);
    }

    public static AbstractArrowEntity createArrow(World world, ItemStack bow, ItemStack ammo, PlayerEntity shooter) {

        LazyOptional<IArcheryBowItem> bowCap = bow.getCapability(BOW_ITEM_CAPABILITY);
        LazyOptional<IArcheryAmmoItem> ammoCap = ammo.getCapability(AMMO_ITEM_CAPABILITY);

        if (bowCap.map(cap -> cap.handleArrowCreation(bow, ammo)).orElse(false) && ammoCap.map(cap -> cap.deferArrowCreation(bow, ammo)).orElse(true)) {
            return bowCap.map(cap -> cap.createArrowEntity(world, bow, ammo, shooter)).orElse(createDefaultArrow(world, ammo, shooter));
        }
        return ammoCap.map(cap -> cap.createArrowEntity(world, bow, ammo, shooter)).orElse(createDefaultArrow(world, ammo, shooter));
    }

    public static ItemStack findAmmo(PlayerEntity shooter) {

        ItemStack offHand = shooter.getHeldItemOffhand();
        ItemStack mainHand = shooter.getHeldItemMainhand();

        if (offHand.getCapability(AMMO_ITEM_CAPABILITY).map(cap -> !cap.isEmpty(offHand, shooter)).orElse(false) || isArrow(offHand)) {
            return offHand;
        }
        if (mainHand.getCapability(AMMO_ITEM_CAPABILITY).map(cap -> !cap.isEmpty(offHand, shooter)).orElse(false) || isArrow(mainHand)) {
            return mainHand;
        }
        for (ItemStack slot : shooter.inventory.mainInventory) {
            if (slot.getCapability(AMMO_ITEM_CAPABILITY).map(cap -> !cap.isEmpty(slot, shooter)).orElse(false) || isArrow(slot)) {
                return slot;
            }
        }
        return ItemStack.EMPTY;
    }

}
