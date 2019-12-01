package cofh.core.event;

import cofh.core.init.CoreConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;

import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.enchantment.EnchantmentHelper.getMaxEnchantmentLevel;
import static net.minecraft.enchantment.Enchantments.FEATHER_FALLING;
import static net.minecraft.enchantment.Enchantments.MENDING;

public class CoreCommonEvents {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(CoreCommonEvents.class);
        registered = true;
    }

    private CoreCommonEvents() {

    }

    @SubscribeEvent
    public static void handleFarmlandTrampleEvent(BlockEvent.FarmlandTrampleEvent event) {

        if (event.isCanceled()) {
            return;
        }
        if (!CoreConfig.improvedFeatherFalling) {
            return;
        }
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity) {
            int encFeatherFalling = getMaxEnchantmentLevel(FEATHER_FALLING, (LivingEntity) entity);
            if (encFeatherFalling > 0) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void handleItemFishedEvent(ItemFishedEvent event) {

        if (event.isCanceled()) {
            return;
        }
        if (!CoreConfig.enableFishingExhaustion) {
            return;
        }
        PlayerEntity player = event.getHookEntity().angler;
        if (player == null || player instanceof FakePlayer) {
            return;
        }
        player.addExhaustion(CoreConfig.amountFishingExhaustion);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void handlePickupXpEvent(PlayerXpEvent.PickupXp event) {

        if (event.isCanceled()) {
            return;
        }
        if (!CoreConfig.improvedMending) {
            return;
        }
        PlayerEntity player = event.getPlayer();
        ExperienceOrbEntity orb = event.getOrb();

        player.xpCooldown = 2;
        player.onItemPickup(orb, 1);
        Map.Entry<EquipmentSlotType, ItemStack> entry = getMostDamagedItem(player);
        if (entry != null) {
            ItemStack itemstack = entry.getValue();
            if (!itemstack.isEmpty() && itemstack.isDamaged()) {
                int i = Math.min((int) (orb.xpValue * itemstack.getXpRepairRatio()), itemstack.getDamage());
                orb.xpValue -= durabilityToXp(i);
                itemstack.setDamage(itemstack.getDamage() - i);
            }
        }
        if (orb.xpValue > 0) {
            player.giveExperiencePoints(orb.xpValue);
        }
        orb.remove();
        event.setCanceled(true);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void handleSaplingGrowTreeEvent(SaplingGrowTreeEvent event) {

        if (event.isCanceled()) {
            return;
        }
        if (!CoreConfig.enableSaplingGrowthMod) {
            return;
        }
        if (event.getRand().nextInt(CoreConfig.amountSaplingGrowthMod) != 0) {
            event.setResult(Event.Result.DENY);
        }
    }

    //    @SubscribeEvent(priority = EventPriority.HIGH)
    //    public static void handleLivingDropsEvent(LivingDropsEvent event) {
    //
    //        if (event.isCanceled()) {
    //            return;
    //        }
    //        LivingEntity entity = event.getEntityLiving();
    //        DamageSource source = event.getSource();
    //        Entity attacker = source.getTrueSource();
    //        if (!(attacker instanceof PlayerEntity) || !event.isRecentlyHit()) {
    //            return;
    //        }
    //        PlayerEntity player = (PlayerEntity) attacker;
    //        // HUNTER
    //        int encHunter = getHeldEnchantmentLevel(player, HUNTER);
    //        if (encHunter > 0 && entity instanceof AnimalEntity) {
    //            LootTable loottable = entity.world.getServer().getLootTableManager().getLootTableFromLocation(entity.getLootTableResourceLocation());
    //            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) entity.world)).withRandom(entity.world.rand).withParameter(LootParameters.THIS_ENTITY, entity).withParameter(LootParameters.POSITION, new BlockPos(entity)).withParameter(LootParameters.DAMAGE_SOURCE, source).withNullableParameter(LootParameters.KILLER_ENTITY, source.getTrueSource()).withNullableParameter(LootParameters.DIRECT_KILLER_ENTITY, source.getImmediateSource());
    //            lootcontext$builder = lootcontext$builder.withParameter(LootParameters.LAST_DAMAGE_PLAYER, player).withLuck(player.getLuck());
    //            loottable.generate(lootcontext$builder.build(LootParameterSets.ENTITY));
    //
    //            for (int i = 0; i < encHunter; ++i) {
    //                if (player.getRNG().nextInt(100) < HunterEnchantment.chance) {
    //                    for (ItemStack stack : loottable.generate(lootcontext$builder.build(LootParameterSets.ENTITY))) {
    //                        ItemEntity drop = new ItemEntity(entity.world, entity.posX, entity.posY, entity.posZ, stack);
    //                        event.getDrops().add(drop);
    //                    }
    //                }
    //            }
    //        }
    //        // OUTLAW
    //        int encDamageVillager = getHeldEnchantmentLevel(player, DAMAGE_VILLAGER);
    //        if (encDamageVillager > 0 && DamageVillagerEnchantment.validTarget(entity)) {
    //            int emeraldDrop = MathHelper.nextInt(0, encDamageVillager);
    //            if (emeraldDrop > 0) {
    //                ItemStack stack = new ItemStack(EMERALD, emeraldDrop);
    //                ItemEntity drop = new ItemEntity(entity.world, entity.posX, entity.posY, entity.posZ, stack);
    //                event.getDrops().add(drop);
    //            }
    //        }
    //        // VORPAL
    //        int encVorpal = getHeldEnchantmentLevel(player, VORPAL);
    //        if (encVorpal > 0) {
    //            ItemStack itemSkull = ItemStack.EMPTY;
    //            if (entity.world.rand.nextInt(100) < VorpalEnchantment.headBase + VorpalEnchantment.headLevel * encVorpal) {
    //                if (entity instanceof ServerPlayerEntity) {
    //                    PlayerEntity target = (ServerPlayerEntity) event.getEntity();
    //                    itemSkull = new ItemStack(PLAYER_HEAD);
    //                    CompoundNBT tag = new CompoundNBT();
    //                    tag.putString(Tags.TAG_SKULL_OWNER, target.getName().getString());
    //                    itemSkull.setTag(tag);
    //                } else if (entity instanceof SkeletonEntity) {
    //                    itemSkull = new ItemStack(SKELETON_SKULL);
    //                } else if (entity instanceof WitherSkeletonEntity) {
    //                    itemSkull = new ItemStack(WITHER_SKELETON_SKULL);
    //                } else if (entity instanceof ZombieEntity) {
    //                    itemSkull = new ItemStack(ZOMBIE_HEAD);
    //                } else if (entity instanceof CreeperEntity) {
    //                    itemSkull = new ItemStack(CREEPER_HEAD);
    //                }
    //            }
    //            if (itemSkull.isEmpty()) {
    //                return;
    //            }
    //            ItemEntity drop = new ItemEntity(entity.world, entity.posX, entity.posY, entity.posZ, itemSkull);
    //            drop.setPickupDelay(10);
    //            event.getDrops().add(drop);
    //        }
    //    }

    // region HELPERS
    private static Map.Entry<EquipmentSlotType, ItemStack> getMostDamagedItem(PlayerEntity player) {

        Map<EquipmentSlotType, ItemStack> map = MENDING.getEntityEquipment(player);
        Map.Entry<EquipmentSlotType, ItemStack> mostDamaged = null;
        if (map.isEmpty()) {
            return null;
        }
        double durability = 0.0D;

        for (Map.Entry<EquipmentSlotType, ItemStack> entry : map.entrySet()) {
            ItemStack stack = entry.getValue();
            if (!stack.isEmpty() && getEnchantmentLevel(MENDING, stack) > 0) {
                if (calcDurabilityRatio(stack) > durability) {
                    mostDamaged = entry;
                    durability = calcDurabilityRatio(stack);
                }
            }
        }
        return mostDamaged;
    }

    private static int durabilityToXp(int durability) {

        return durability / 2;
    }

    private static int xpToDurability(int xp) {

        return xp * 2;
    }

    private static double calcDurabilityRatio(ItemStack stack) {

        return (double) stack.getDamage() / stack.getMaxDamage();
    }
    // endregion
}
