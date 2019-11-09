package cofh.ensorcellation.event;

import cofh.ensorcellation.enchantment.*;
import cofh.ensorcellation.enchantment.nyi.SmashingEnchantment;
import cofh.ensorcellation.enchantment.nyi.SmeltingEnchantment;
import cofh.ensorcellation.enchantment.override.FrostWalkerEnchantmentImp;
import cofh.lib.util.constants.Tags;
import cofh.lib.util.helpers.MathHelper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static cofh.lib.util.Utils.getHeldEnchantmentLevel;
import static cofh.lib.util.constants.Constants.DAMAGE_PLAYER;
import static cofh.lib.util.constants.Constants.UUID_REACH_DISTANCE;
import static cofh.lib.util.references.EnsorcellationReferences.*;
import static net.minecraft.enchantment.EnchantmentHelper.getMaxEnchantmentLevel;
import static net.minecraft.enchantment.Enchantments.EFFICIENCY;
import static net.minecraft.enchantment.Enchantments.FROST_WALKER;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.ADDITION;
import static net.minecraft.item.Items.*;

public class CommonEventsEnsorc {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(CommonEventsEnsorc.class);
        registered = true;
    }

    private CommonEventsEnsorc() {

    }

    // region LIVING EVENTS
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void handleLivingAttackEvent(LivingAttackEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        DamageSource source = event.getSource();
        Entity attacker = source.getTrueSource();
        // MAGIC EDGE
        if (attacker instanceof LivingEntity) {
            int encMagicEdge = getHeldEnchantmentLevel((LivingEntity) attacker, MAGIC_EDGE);
            if (encMagicEdge > 0 && !source.isMagicDamage() && source.damageType.equals(DAMAGE_PLAYER)) {
                event.setCanceled(true);
                entity.attackEntityFrom(event.getSource().setDamageBypassesArmor().setMagicDamage(), event.getAmount() + MagicEdgeEnchantment.getExtraDamage(encMagicEdge));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void handleLivingDamageEvent(LivingDamageEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        DamageSource source = event.getSource();
        Entity attacker = source.getTrueSource();

        if (attacker instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) attacker;
            // CURSE OF MERCY
            int encMercy = getHeldEnchantmentLevel(living, CURSE_MERCY);
            if (encMercy > 0 && event.getAmount() > entity.getHealth()) {
                event.setAmount(Math.max(0.0F, entity.getHealth() - 1.0F));
            }
        }
    }

    @SubscribeEvent
    public static void handleLivingDeathEvent(LivingDeathEvent event) {

        if (event.isCanceled()) {
            return;
        }
        DamageSource source = event.getSource();
        Entity attacker = source.getTrueSource();

        if (attacker instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) attacker;
            // LEECH
            int encLeech = getHeldEnchantmentLevel(living, LEECH);
            if (encLeech > 0) {
                (living).heal(encLeech);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void handleLivingDropsEvent(LivingDropsEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        DamageSource source = event.getSource();
        Entity attacker = source.getTrueSource();
        if (!(attacker instanceof PlayerEntity) || !event.isRecentlyHit()) {
            return;
        }
        PlayerEntity player = (PlayerEntity) attacker;
        // HUNTER
        int encHunter = getHeldEnchantmentLevel(player, HUNTER);
        if (encHunter > 0 && entity instanceof AnimalEntity) {
            LootTable loottable = entity.world.getServer().getLootTableManager().getLootTableFromLocation(entity.getLootTableResourceLocation());
            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) entity.world)).withRandom(entity.world.rand).withParameter(LootParameters.THIS_ENTITY, entity).withParameter(LootParameters.POSITION, new BlockPos(entity)).withParameter(LootParameters.DAMAGE_SOURCE, source).withNullableParameter(LootParameters.KILLER_ENTITY, source.getTrueSource()).withNullableParameter(LootParameters.DIRECT_KILLER_ENTITY, source.getImmediateSource());
            lootcontext$builder = lootcontext$builder.withParameter(LootParameters.LAST_DAMAGE_PLAYER, player).withLuck(player.getLuck());
            loottable.generate(lootcontext$builder.build(LootParameterSets.ENTITY));

            for (int i = 0; i < encHunter; ++i) {
                if (player.getRNG().nextInt(100) < HunterEnchantment.chance) {
                    for (ItemStack stack : loottable.generate(lootcontext$builder.build(LootParameterSets.ENTITY))) {
                        ItemEntity drop = new ItemEntity(entity.world, entity.posX, entity.posY, entity.posZ, stack);
                        event.getDrops().add(drop);
                    }
                }
            }
        }
        // OUTLAW
        int encDamageVillager = getHeldEnchantmentLevel(player, DAMAGE_VILLAGER);
        if (encDamageVillager > 0 && DamageVillagerEnchantment.validTarget(entity)) {
            int emeraldDrop = MathHelper.nextInt(0, encDamageVillager);
            if (emeraldDrop > 0) {
                ItemStack stack = new ItemStack(EMERALD, emeraldDrop);
                ItemEntity drop = new ItemEntity(entity.world, entity.posX, entity.posY, entity.posZ, stack);
                event.getDrops().add(drop);
            }
        }
        // VORPAL
        int encVorpal = getHeldEnchantmentLevel(player, VORPAL);
        if (encVorpal > 0) {
            ItemStack itemSkull = ItemStack.EMPTY;
            if (entity.world.rand.nextInt(100) < VorpalEnchantment.headBase + VorpalEnchantment.headLevel * encVorpal) {
                if (entity instanceof ServerPlayerEntity) {
                    PlayerEntity target = (ServerPlayerEntity) event.getEntity();
                    itemSkull = new ItemStack(PLAYER_HEAD);
                    CompoundNBT tag = new CompoundNBT();
                    tag.putString(Tags.TAG_SKULL_OWNER, target.getName().getString());
                    itemSkull.setTag(tag);
                } else if (entity instanceof SkeletonEntity) {
                    itemSkull = new ItemStack(SKELETON_SKULL);
                } else if (entity instanceof WitherSkeletonEntity) {
                    itemSkull = new ItemStack(WITHER_SKELETON_SKULL);
                } else if (entity instanceof ZombieEntity) {
                    itemSkull = new ItemStack(ZOMBIE_HEAD);
                } else if (entity instanceof CreeperEntity) {
                    itemSkull = new ItemStack(CREEPER_HEAD);
                }
            }
            if (itemSkull.isEmpty()) {
                return;
            }
            ItemEntity drop = new ItemEntity(entity.world, entity.posX, entity.posY, entity.posZ, itemSkull);
            drop.setPickupDelay(10);
            event.getDrops().add(drop);
        }
    }

    @SubscribeEvent
    public static void handleLivingExperienceDropEvent(LivingExperienceDropEvent event) {

        if (event.isCanceled()) {
            return;
        }
        PlayerEntity player = event.getAttackingPlayer();

        if (player != null) {
            // CURSE OF FOOLISHNESS
            int encFool = getHeldEnchantmentLevel(player, CURSE_FOOL);
            if (encFool > 0) {
                event.setDroppedExperience(0);
                event.setCanceled(true);
                return;
            }
            // EXP BOOST
            int encExpBoost = getHeldEnchantmentLevel(player, EXP_BOOST);
            if (encExpBoost > 0) {
                event.setDroppedExperience(ExpBoostEnchantment.getExp(event.getDroppedExperience(), encExpBoost, player.world.rand));
            }
        }
    }

    @SubscribeEvent
    public static void handleLivingHurtEvent(LivingHurtEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        DamageSource source = event.getSource();
        Entity attacker = source.getTrueSource();

        if (!(attacker instanceof LivingEntity)) {
            return;
        }
        LivingEntity living = (LivingEntity) attacker;

        int encDamageEnder = getHeldEnchantmentLevel(living, DAMAGE_ENDER);
        if (encDamageEnder > 0 && DamageEnderEnchantment.validTarget(entity)) {
            event.setAmount(event.getAmount() + DamageEnderEnchantment.getExtraDamage(encDamageEnder));
        }
        // TODO: Revisit if Ravagers and Witches are reclassified in future.
        int encDamageIllager = getHeldEnchantmentLevel(living, DAMAGE_ILLAGER);
        if (encDamageIllager > 0 && DamageIllagerEnchantment.validTarget(entity)) {
            event.setAmount(event.getAmount() + DamageIllagerEnchantment.getExtraDamage(encDamageIllager));
        }
        int encDamageVillager = getHeldEnchantmentLevel(living, DAMAGE_VILLAGER);
        if (encDamageVillager > 0 && DamageVillagerEnchantment.validTarget(entity)) {
            event.setAmount(event.getAmount() + DamageVillagerEnchantment.getExtraDamage(encDamageVillager));
        }
        int encCavalier = getHeldEnchantmentLevel(living, CAVALIER);
        if (encCavalier > 0 && living.getRidingEntity() != null) {
            event.setAmount(event.getAmount() * (1 + CavalierEnchantment.damageMult * MathHelper.nextInt(1, encCavalier)));
        }
        int encFrostAspect = getHeldEnchantmentLevel(living, FROST_ASPECT);
        if (encFrostAspect > 0) {
            FrostAspectEnchantment.onHit(entity, encFrostAspect);
            // Target check is for additional damage, not effect in general.
            if (FrostAspectEnchantment.validTarget(entity)) {
                event.setAmount(event.getAmount() + FrostAspectEnchantment.getExtraDamage(encFrostAspect));
            }
        }
        int encMagicEdge = getHeldEnchantmentLevel(living, MAGIC_EDGE);
        if (encMagicEdge > 0 && source.isMagicDamage()) {
            MagicEdgeEnchantment.onHit(entity, encMagicEdge);
        }
        int encVorpal = getHeldEnchantmentLevel(living, VORPAL);
        if (encVorpal > 0 && entity.world.rand.nextInt(100) < VorpalEnchantment.critBase + VorpalEnchantment.critLevel * encVorpal) {
            event.setAmount(event.getAmount() * VorpalEnchantment.critDamage);
            VorpalEnchantment.onHit(entity, encVorpal);
        }
    }

    @SubscribeEvent
    public static void handleLivingUpdateEvent(LivingUpdateEvent event) {

        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        // FROST WALKER
        int encFrostWalker = getMaxEnchantmentLevel(FROST_WALKER, entity);
        if (encFrostWalker > 0) {
            FrostWalkerEnchantment.freezeNearby(entity, entity.world, new BlockPos(entity), encFrostWalker);
            FrostWalkerEnchantmentImp.freezeNearby(entity, entity.world, new BlockPos(entity), encFrostWalker);
        }
        if (entity instanceof PlayerEntity) {
            // REACH
            int encReach = getHeldEnchantmentLevel(entity, REACH);
            if (encReach > 0) {
                Multimap<String, AttributeModifier> attributes = HashMultimap.create();
                attributes.put(PlayerEntity.REACH_DISTANCE.getName(), new AttributeModifier(UUID_REACH_DISTANCE, ID_REACH, encReach, ADDITION).setSaved(false));
                entity.getAttributes().applyAttributeModifiers(attributes);
            } else {
                Multimap<String, AttributeModifier> attributes = HashMultimap.create();
                attributes.put(PlayerEntity.REACH_DISTANCE.getName(), new AttributeModifier(UUID_REACH_DISTANCE, ID_REACH, encReach, ADDITION).setSaved(false));
                entity.getAttributes().removeAttributeModifiers(attributes);
            }
        }
    }

    @SubscribeEvent
    public static void handleItemUseFinish(LivingEntityUseItemEvent.Finish event) {

        LivingEntity entity = event.getEntityLiving();
        if (!(entity instanceof PlayerEntity) || entity instanceof FakePlayer) {
            return;
        }
        Food food = event.getItem().getItem().getFood();
        if (food != null) {
            // GOURMAND
            int encGourmand = getMaxEnchantmentLevel(GOURMAND, entity);
            if (encGourmand > 0 && food != null) {
                int foodLevel = food.getHealing();
                float foodSaturation = food.getSaturation();

                FoodStats playerStats = ((PlayerEntity) entity).getFoodStats();
                int playerFood = playerStats.getFoodLevel();

                playerStats.addStats(foodLevel + encGourmand, foodSaturation + encGourmand * 0.1F);
                playerStats.setFoodLevel(Math.min(playerFood + encGourmand, 20));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void handleItemFishedEvent(ItemFishedEvent event) {

        if (event.isCanceled()) {
            return;
        }
        FishingBobberEntity hook = event.getHookEntity();
        PlayerEntity player = hook.angler;
        if (player == null) {
            return;
        }
        // ANGLER
        int encAngler = getHeldEnchantmentLevel(player, ANGLER);
        if (encAngler > 0) {
            ItemStack fishingRod = player.getHeldItemMainhand();

            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) hook.world)).withParameter(LootParameters.POSITION, new BlockPos(hook)).withParameter(LootParameters.TOOL, fishingRod).withRandom(hook.world.rand).withLuck((float) hook.luck + hook.angler.getLuck());
            lootcontext$builder.withParameter(LootParameters.KILLER_ENTITY, hook.angler).withParameter(LootParameters.THIS_ENTITY, hook);
            LootTable loottable = hook.world.getServer().getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING);
            List<ItemStack> list = loottable.generate(lootcontext$builder.build(LootParameterSets.FISHING));

            for (int i = 0; i < encAngler; ++i) {
                if (player.getRNG().nextInt(100) < AnglerEnchantment.chance) {
                    list.addAll(loottable.generate(lootcontext$builder.build(LootParameterSets.FISHING)));
                }
            }
            for (ItemStack stack : list) {
                ItemEntity drop = new ItemEntity(hook.world, hook.posX, hook.posY, hook.posZ, stack);
                double d0 = player.posX - hook.posX;
                double d1 = player.posY - hook.posY;
                double d2 = player.posZ - hook.posZ;
                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                drop.setMotion(d0 * 0.1D, d1 * 0.1D + Math.sqrt(d3) * 0.08D, d2 * 0.1D);
                hook.world.addEntity(drop);
                if (stack.getItem().isIn(ItemTags.FISHES)) {
                    player.addStat(Stats.FISH_CAUGHT, 1);
                }
            }
        }
        // EXP BOOST
        int encExpBoost = getHeldEnchantmentLevel(player, EXP_BOOST);
        if (encExpBoost > 0) {
            hook.world.addEntity(new ExperienceOrbEntity(player.world, player.posX, player.posY + 0.5D, player.posZ + 0.5D, ExpBoostEnchantment.getExp(0, encExpBoost, player.world.rand)));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void handlePlayerPickupXpEvent(PlayerPickupXpEvent event) {

        PlayerEntity player = event.getPlayer();
        ExperienceOrbEntity orb = event.getOrb();

        // CURSE OF FOOLISHNESS
        int encFool = getHeldEnchantmentLevel(player, CURSE_FOOL);
        if (encFool > 0) {
            orb.xpValue = 0;
            orb.remove();
            event.setCanceled(true);
        }
    }
    // endregion

    // region BLOCK BREAKING
    @SubscribeEvent
    public static void handleBlockBreakEvent(BlockEvent.BreakEvent event) {

        if (event.isCanceled()) {
            return;
        }
        PlayerEntity player = event.getPlayer();
        if (player == null) {
            return;
        }
        if (event.getExpToDrop() > 0) {
            // CURSE OF FOOLISHNESS
            int encFool = getHeldEnchantmentLevel(player, CURSE_FOOL);
            if (encFool > 0) {
                event.setExpToDrop(0);
                return;
            }
            // EXP BOOST
            int encExpBoost = getHeldEnchantmentLevel(player, EXP_BOOST);
            if (encExpBoost > 0) {
                event.setExpToDrop(ExpBoostEnchantment.getExp(event.getExpToDrop(), encExpBoost, player.world.rand));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void handleBreakSpeedEvent(PlayerEvent.BreakSpeed event) {

        if (event.isCanceled()) {
            return;
        }
        PlayerEntity player = event.getPlayer();
        // AIR AFFINITY
        int encAirAffinity = getMaxEnchantmentLevel(AIR_AFFINITY, player);
        if (encAirAffinity > 0 && !player.onGround) {
            event.setNewSpeed(Math.max(event.getOriginalSpeed(), event.getNewSpeed() * 5.0F));
        }
        // EXCAVATING
        int encExcavating = getHeldEnchantmentLevel(player, EXCAVATING);
        if (encExcavating > 0) {
            if (!player.isSneaking()) {
                event.setNewSpeed(event.getNewSpeed() / 1 + encExcavating);
            }
            int encEfficiency = getHeldEnchantmentLevel(player, EFFICIENCY);
            if (encEfficiency > 1) {
                event.setNewSpeed(event.getNewSpeed() / encEfficiency);
            }
        }
    }
    // endregion

    @SubscribeEvent(priority = EventPriority.LOW)
    // TODO: Event does not fire yet.
    public void handleHarvestDropsEvent(BlockEvent.HarvestDropsEvent event) {

        // SMASHING / SMELTING / PROSPECTING
        PlayerEntity player = event.getHarvester();
        if (player == null || event.isSilkTouching()) {
            return;
        }
        ItemStack tool = player.getHeldItemMainhand();
        int encSmashing = getHeldEnchantmentLevel(player, SMASHING);
        int encSmelting = getHeldEnchantmentLevel(player, SMELTING);

        List<ItemStack> drops = event.getDrops();

        drops.replaceAll(stack -> {
            if (stack.isEmpty()) {
                return stack; // Nope, processing on this sometimes results in...results.
            }
            ItemStack result = stack;
            if (encSmashing > 0) {
                ItemStack smashed = SmashingEnchantment.getItemStack(player.world, result);
                if (!smashed.isEmpty()) {
                    result = smashed;
                    tool.damageItem(1, player, (consumer) -> consumer.sendBreakAnimation(EquipmentSlotType.MAINHAND));
                }
            }
            if (encSmelting > 0) {
                ItemStack smelted = SmeltingEnchantment.getItemStack(player.world, result);
                if (!smelted.isEmpty()) {
                    result = smelted;
                    tool.damageItem(1, player, (consumer) -> consumer.sendBreakAnimation(EquipmentSlotType.MAINHAND));
                }
            }
            return result;
        });
    }

    @SubscribeEvent
    public static void handleTickEndEvent(TickEvent.ServerTickEvent event) {

        if (event.phase == TickEvent.Phase.END) {
            FireRebukeEnchantment.setFireToMobs();
        }
    }

}
