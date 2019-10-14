package cofh.ensorcellment.event;

import cofh.ensorcellment.enchantment.*;
import cofh.ensorcellment.enchantment.nyi.SmashingEnchantment;
import cofh.ensorcellment.enchantment.nyi.SmeltingEnchantment;
import cofh.ensorcellment.enchantment.override.FrostWalkerEnchantmentImp;
import cofh.ensorcellment.enchantment.override.MendingEnchantmentAlt;
import cofh.ensorcellment.enchantment.override.ProtectionEnchantmentImp;
import cofh.ensorcellment.enchantment.override.ThornsEnchantmentImp;
import cofh.ensorcellment.init.ConfigEnsorc;
import cofh.lib.util.Utils;
import cofh.lib.util.constants.Tags;
import cofh.lib.util.helpers.MathHelper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Iterator;
import java.util.List;

import static cofh.lib.util.Utils.*;
import static cofh.lib.util.constants.Constants.*;
import static cofh.lib.util.modhelpers.EnsorcellmentHelper.*;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.enchantment.EnchantmentHelper.getMaxEnchantmentLevel;
import static net.minecraft.enchantment.Enchantments.*;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.ADDITION;
import static net.minecraft.entity.ai.attributes.AttributeModifier.Operation.MULTIPLY_TOTAL;
import static net.minecraft.item.Items.*;
import static net.minecraft.world.GameRules.KEEP_INVENTORY;

public class CommonEventsEnsorc {

    private static final CommonEventsEnsorc INSTANCE = new CommonEventsEnsorc();
    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        registered = true;
    }

    private CommonEventsEnsorc() {

    }

    // region LIVING EVENTS
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void handleLivingAttackEvent(LivingAttackEvent event) {

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
                return;
            }
        }
        // SHIELD
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            ItemStack stack = player.getActiveItemStack();

            if (canBlockDamageSource(player, source)) {
                // THORNS
                int encThorns = getEnchantmentLevel(THORNS, stack);
                if (ThornsEnchantmentImp.shouldHit(encThorns, MathHelper.RANDOM) && attacker != null) {
                    attacker.attackEntityFrom(DamageSource.causeThornsDamage(entity), ThornsEnchantment.getDamage(encThorns, MathHelper.RANDOM));
                    if (MathHelper.RANDOM.nextInt(1 + encThorns) == 0) {
                        player.getCooldownTracker().setCooldown(stack.getItem(), 40);
                        player.resetActiveHand();
                        player.world.setEntityState(player, (byte) 30);
                    }
                }
                // FIRE REBUKE
                int encFireRebuke = getEnchantmentLevel(FIRE_REBUKE, stack);
                if (encFireRebuke > 0) {
                    FireRebukeEnchantment.onHit(entity, attacker, encFireRebuke);
                    if (MathHelper.RANDOM.nextInt(1 + encFireRebuke) == 0) {
                        player.getCooldownTracker().setCooldown(stack.getItem(), 40);
                        player.resetActiveHand();
                        player.world.setEntityState(player, (byte) 30);
                    }
                }
                // FROST REBUKE
                int encFrostRebuke = getEnchantmentLevel(FROST_REBUKE, stack);
                if (encFrostRebuke > 0) {
                    FrostRebukeEnchantment.onHit(entity, attacker, encFireRebuke);
                    if (MathHelper.RANDOM.nextInt(1 + encFrostRebuke) == 0) {
                        player.getCooldownTracker().setCooldown(stack.getItem(), 40);
                        player.resetActiveHand();
                        player.world.setEntityState(player, (byte) 30);
                    }
                }
                // DISPLACEMENT
                int encDisplacement = getEnchantmentLevel(DISPLACEMENT, stack);
                if (DisplacementEnchantment.shouldHit(encDisplacement, MathHelper.RANDOM) && attacker != null) {
                    DisplacementEnchantment.teleportEntity(encDisplacement, MathHelper.RANDOM, attacker);
                    if (MathHelper.RANDOM.nextInt(1 + encDisplacement) == 0) {
                        player.getCooldownTracker().setCooldown(stack.getItem(), 40);
                        player.resetActiveHand();
                        player.world.setEntityState(player, (byte) 30);
                    }
                    event.setCanceled(true);
                }
                return;
            }
        }
        if (entity instanceof HorseEntity) {
            ItemStack armor = ((HorseEntity) entity).horseChest.getStackInSlot(1);
            if (!armor.isEmpty()) {
                // FROST WALKER
                int encFrostWalker = getEnchantmentLevel(FROST_WALKER, armor);
                if (event.getSource().equals(DamageSource.HOT_FLOOR) && encFrostWalker > 0) {
                    event.setCanceled(true);
                }
                // DISPLACEMENT
                int encDisplacement = getEnchantmentLevel(DISPLACEMENT, armor);
                if (DisplacementEnchantment.shouldHit(encDisplacement, MathHelper.RANDOM) && attacker != null) {
                    DisplacementEnchantment.teleportEntity(encDisplacement, MathHelper.RANDOM, attacker);
                    event.setCanceled(true);
                }
            }
        }
        // If not a horse...
        else {
            // DISPLACEMENT
            int encDisplacement = getMaxEnchantmentLevel(DISPLACEMENT, entity);
            if (DisplacementEnchantment.shouldHit(encDisplacement, MathHelper.RANDOM) && attacker != null) {
                DisplacementEnchantment.teleportEntity(encDisplacement, MathHelper.RANDOM, attacker);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void handleLivingDamageEvent(LivingDamageEvent event) {

        LivingEntity entity = event.getEntityLiving();
        Entity attacker = event.getSource().getTrueSource();
        float damage = event.getAmount();

        // MERCY
        if (attacker instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) attacker;
            int encMercy = getHeldEnchantmentLevel(living, CURSE_MERCY);
            if (encMercy > 0 && damage > entity.getHealth()) {
                event.setAmount(Math.max(0.0F, entity.getHealth() - 1.0F));
            }
        }
    }

    @SubscribeEvent
    public void handleLivingDeathEvent(LivingDeathEvent event) {

        Entity entity = event.getEntity();
        Entity attacker = event.getSource().getTrueSource();

        if (attacker instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) attacker;

            int encLeech = getHeldEnchantmentLevel(living, LEECH);
            int encExpBoost = getHeldEnchantmentLevel(living, EXP_BOOST);
            if (encLeech > 0) {
                (living).heal(encLeech);
            }
            if (encExpBoost > 0 && living instanceof PlayerEntity) {
                entity.world.addEntity(new ExperienceOrbEntity(entity.world, entity.posX, entity.posY + 0.5D, entity.posZ, encExpBoost + entity.world.rand.nextInt(1 + encExpBoost * ExpBoostEnchantment.experience)));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void handleLivingDropsEvent(LivingDropsEvent event) {

        DamageSource dmgSource = event.getSource();
        Entity source = dmgSource.getTrueSource();
        if (!(source instanceof PlayerEntity) || !event.isRecentlyHit()) {
            return;
        }
        PlayerEntity player = (PlayerEntity) source;
        Entity entity = event.getEntity();

        // HUNTER
        int encHunter = getHeldEnchantmentLevel(player, HUNTER);
        if (encHunter > 0 && entity instanceof AnimalEntity) {
            LootTable loottable = entity.world.getServer().getLootTableManager().getLootTableFromLocation(((AnimalEntity) entity).func_213346_cF());
            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) entity.world)).withRandom(entity.world.rand).withParameter(LootParameters.THIS_ENTITY, entity).withParameter(LootParameters.POSITION, new BlockPos(entity)).withParameter(LootParameters.DAMAGE_SOURCE, dmgSource).withNullableParameter(LootParameters.KILLER_ENTITY, dmgSource.getTrueSource()).withNullableParameter(LootParameters.DIRECT_KILLER_ENTITY, dmgSource.getImmediateSource());
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
        int encVorpal = getEnchantmentLevel(VORPAL, player.getHeldItemMainhand());
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
    public void handleLivingHurtEvent(LivingHurtEvent event) {

        LivingEntity entity = event.getEntityLiving();
        DamageSource source = event.getSource();
        Entity attacker = event.getSource().getTrueSource();

        // region HORSE ARMOR
        if (entity instanceof HorseEntity) {
            ItemStack armor = ((HorseEntity) entity).horseChest.getStackInSlot(1);

            if (!armor.isEmpty()) {
                int totalProtection = 0;

                // PROTECTION
                int encProtection = getEnchantmentLevel(PROTECTION, armor);
                if (encProtection > 0) {
                    totalProtection += PROTECTION.calcModifierDamage(encProtection, source);
                }
                // FIRE PROTECTION
                int encProtectionFire = getEnchantmentLevel(FIRE_PROTECTION, armor);
                if (encProtectionFire > 0) {
                    totalProtection += FIRE_PROTECTION.calcModifierDamage(encProtection, source);
                }
                // FEATHER FALLING
                int encProtectionFall = getEnchantmentLevel(FEATHER_FALLING, armor);
                if (encProtectionFall > 0) {
                    totalProtection += FEATHER_FALLING.calcModifierDamage(encProtection, source);
                }
                // BLAST PROTECTION
                int encProtectionExplosion = getEnchantmentLevel(BLAST_PROTECTION, armor);
                if (encProtectionExplosion > 0) {
                    totalProtection += BLAST_PROTECTION.calcModifierDamage(encProtection, source);
                }
                // PROJECTILE PROTECTION
                int encProtectionProjectile = getEnchantmentLevel(PROJECTILE_PROTECTION, armor);
                if (encProtectionProjectile > 0) {
                    totalProtection += PROJECTILE_PROTECTION.calcModifierDamage(encProtection, source);
                }
                float damageReduction = Math.min(totalProtection * ProtectionEnchantmentImp.HORSE_MODIFIER, 20.0F);
                event.setAmount(event.getAmount() * (1.0F - damageReduction / 25.0F));

                if (attacker != null) {
                    // THORNS
                    int encThorns = getEnchantmentLevel(THORNS, armor);
                    if (ThornsEnchantment.shouldHit(encThorns, MathHelper.RANDOM)) {
                        attacker.attackEntityFrom(DamageSource.causeThornsDamage(entity), ThornsEnchantment.getDamage(encThorns, MathHelper.RANDOM));
                    }
                    // FIRE REBUKE
                    int encFireRebuke = getEnchantmentLevel(FIRE_REBUKE, armor);
                    if (encFireRebuke > 0) {
                        FireRebukeEnchantment.onHit(entity, attacker, encFireRebuke);
                    }
                    // FROST REBUKE
                    int encFrostRebuke = getEnchantmentLevel(FROST_REBUKE, armor);
                    if (encFrostRebuke > 0) {
                        FrostRebukeEnchantment.onHit(entity, attacker, encFireRebuke);
                    }
                }
            }
        }
        // endregion

        // region DAMAGE
        if (attacker instanceof LivingEntity) {
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
    }

    @SubscribeEvent
    public void handleLivingUpdateEvent(LivingUpdateEvent event) {

        LivingEntity entity = event.getEntityLiving();

        // region HORSE ARMOR
        if (entity instanceof HorseEntity) {
            ItemStack armor = ((HorseEntity) entity).horseChest.getStackInSlot(1);
            if (!armor.isEmpty()) {
                int encFrostWalker = getEnchantmentLevel(FROST_WALKER, armor);
                if (encFrostWalker > 0) {
                    FrostWalkerEnchantment.freezeNearby(entity, entity.world, new BlockPos(entity), encFrostWalker);
                    FrostWalkerEnchantmentImp.freezeNearby(entity, entity.world, new BlockPos(entity), encFrostWalker);
                }
            }
            return;
        }
        // endregion

        // FROST WALKER
        int encFrostWalker = getMaxEnchantmentLevel(FROST_WALKER, entity);
        if (encFrostWalker > 0) {
            FrostWalkerEnchantment.freezeNearby(entity, entity.world, new BlockPos(entity), encFrostWalker);
            FrostWalkerEnchantmentImp.freezeNearby(entity, entity.world, new BlockPos(entity), encFrostWalker);
        }
        if (entity instanceof PlayerEntity) {
            // REACH
            int encReach = getEnchantmentLevel(REACH, entity.getHeldItemMainhand());
            if (encReach > 0) {
                Multimap<String, AttributeModifier> attributes = HashMultimap.create();
                attributes.put(PlayerEntity.REACH_DISTANCE.getName(), new AttributeModifier(UUID_REACH_DISTANCE, "ensorcellment.reach", encReach, ADDITION).setSaved(false));
                entity.getAttributes().applyAttributeModifiers(attributes);
            } else {
                Multimap<String, AttributeModifier> attributes = HashMultimap.create();
                attributes.put(PlayerEntity.REACH_DISTANCE.getName(), new AttributeModifier(UUID_REACH_DISTANCE, "ensorcellment.reach", encReach, ADDITION).setSaved(false));
                entity.getAttributes().removeAttributeModifiers(attributes);
            }
            ItemStack activeStack = entity.getActiveItemStack();
            // SHIELD
            if (activeStack.getItem().isShield(activeStack, entity)) {
                int encBulwark = getEnchantmentLevel(BULWARK, activeStack);
                int encPhalanx = getEnchantmentLevel(PHALANX, activeStack);

                Multimap<String, AttributeModifier> attributes = HashMultimap.create();
                if (encBulwark > 0) {
                    attributes.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), new AttributeModifier(UUID_KNOCKBACK_RESISTANCE, "ensorcellment.bulwark", 1.0D, ADDITION).setSaved(false));
                }
                if (encPhalanx > 0) {
                    attributes.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(UUID_MOVEMENT_SPEED, "ensorcellment.phalanx", PhalanxEnchantment.SPEED * encPhalanx, MULTIPLY_TOTAL).setSaved(false));
                }
                if (!attributes.isEmpty()) {
                    entity.getAttributes().applyAttributeModifiers(attributes);
                }
            } else {
                Multimap<String, AttributeModifier> attributes = HashMultimap.create();
                attributes.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), new AttributeModifier(UUID_KNOCKBACK_RESISTANCE, "ensorcellment.bulwark", 1.0D, ADDITION).setSaved(false));
                attributes.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(UUID_MOVEMENT_SPEED, "ensorcellment.phalanx", PhalanxEnchantment.SPEED, MULTIPLY_TOTAL).setSaved(false));
                entity.getAttributes().removeAttributeModifiers(attributes);
            }
        }
    }

    @SubscribeEvent
    public void handleItemUseFinish(LivingEntityUseItemEvent.Finish event) {

        LivingEntity entity = event.getEntityLiving();
        if (!(entity instanceof PlayerEntity) || entity instanceof FakePlayer) {
            return;
        }
        Food food = event.getItem().getItem().getFood();
        if (food != null) {
            int encGourmand = getMaxEnchantmentLevel(GOURMAND, entity);
            if (encGourmand > 0) {
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
    public void handleItemFishedEvent(ItemFishedEvent event) {

        if (event.isCanceled()) {
            return;
        }
        FishingBobberEntity hook = event.getHookEntity();
        PlayerEntity player = hook.angler;
        if (player == null) {
            return;
        }
        int encAngler = getHeldEnchantmentLevel(player, ANGLER);
        if (encAngler > 0) {
            ItemStack fishingRod = event.getPlayer().getHeldItemMainhand();

            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) hook.world)).withParameter(LootParameters.POSITION, new BlockPos(hook)).withParameter(LootParameters.TOOL, fishingRod).withRandom(hook.world.rand).withLuck((float) hook.luck + hook.angler.getLuck());
            lootcontext$builder.withParameter(LootParameters.KILLER_ENTITY, hook.angler).withParameter(LootParameters.THIS_ENTITY, hook);
            LootTable loottable = hook.world.getServer().getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING);
            List<ItemStack> result = loottable.generate(lootcontext$builder.build(LootParameterSets.FISHING));

            for (int i = 0; i < encAngler; ++i) {
                if (player.getRNG().nextInt(100) < AnglerEnchantment.chance) {
                    result.addAll(loottable.generate(lootcontext$builder.build(LootParameterSets.FISHING)));
                }
            }
            for (ItemStack stack : result) {
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
    }
    // endregion

    // region BLOCK BREAKING
    @SubscribeEvent
    public void handleBlockBreakEvent(BlockEvent.BreakEvent event) {

        PlayerEntity player = event.getPlayer();
        if (player == null) {
            return;
        }
        if (event.getExpToDrop() > 0) {
            int encExpBoost = getEnchantmentLevel(EXP_BOOST, player.getHeldItemMainhand());
            if (encExpBoost > 0) {
                event.setExpToDrop(event.getExpToDrop() + encExpBoost + player.world.rand.nextInt(1 + encExpBoost * ExpBoostEnchantment.experience));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void handleBreakSpeedEvent(PlayerEvent.BreakSpeed event) {

        PlayerEntity player = event.getPlayer();
        if (!player.onGround && getMaxEnchantmentLevel(AIR_AFFINITY, player) > 0) {
            event.setNewSpeed(Math.max(event.getOriginalSpeed(), event.getNewSpeed() * 5.0F));
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    // TODO: Event does not fire yet.
    public void handleHarvestDropsEvent(BlockEvent.HarvestDropsEvent event) {

        // SMASHING / SMELTING / PROSPECTING
        PlayerEntity player = event.getHarvester();
        if (player == null || event.isSilkTouching()) {
            return;
        }
        ItemStack tool = player.getHeldItemMainhand();
        int encSmashing = getEnchantmentLevel(SMASHING, tool);
        int encSmelting = getEnchantmentLevel(SMELTING, tool);

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
    // endregion

    // region PRESERVATION
    @SubscribeEvent
    public void handleAnvilRepairEvent(AnvilRepairEvent event) {

        if (event.isCanceled()) {
            return;
        }
        if (!ConfigEnsorc.enableMendingOverride) {
            return;
        }
        ItemStack left = event.getItemInput();
        ItemStack output = event.getItemResult();

        if (getEnchantmentLevel(Enchantments.MENDING, left) <= 0) {
            return;
        }
        if (output.getDamage() < left.getDamage()) {
            event.setBreakChance(MendingEnchantmentAlt.anvilDamage);
        }
    }

    @SubscribeEvent
    public void handleAnvilUpdateEvent(AnvilUpdateEvent event) {

        if (event.isCanceled()) {
            return;
        }
        if (!ConfigEnsorc.enableMendingOverride) {
            return;
        }
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        ItemStack output = left.copy();

        if (getEnchantmentLevel(Enchantments.MENDING, left) <= 0) {
            return;
        }
        if (output.isDamageable() && output.getItem().getIsRepairable(left, right)) {
            int damageLeft = Math.min(output.getDamage(), output.getMaxDamage() / 4);
            if (damageLeft <= 0) {
                return;
            }
            int matCost;
            for (matCost = 0; damageLeft > 0 && matCost < right.getCount(); ++matCost) {
                int durability = output.getDamage() - damageLeft;
                output.setDamage(durability);
                damageLeft = Math.min(output.getDamage(), output.getMaxDamage() / 4);
            }
            event.setMaterialCost(matCost);
            // event.setCost(0);
            event.setOutput(output);
        } else if (output.isDamageable()) {
            int damageLeft = left.getMaxDamage() - left.getDamage();
            int damageRight = right.getMaxDamage() - right.getDamage();
            int damageRepair = damageLeft + damageRight + output.getMaxDamage() * 12 / 100;
            int damageOutput = output.getMaxDamage() - damageRepair;

            if (damageOutput < 0) {
                damageOutput = 0;
            }
            if (damageOutput < output.getDamage()) {
                output.setDamage(damageOutput);
            }
            // event.setCost(0);
            event.setOutput(output);
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void handlePlayerPickupXpEvent(PlayerPickupXpEvent event) {

        if (event.isCanceled()) {
            return;
        }
        if (!ConfigEnsorc.enableMendingOverride) {
            return;
        }
        PlayerEntity player = event.getPlayer();
        ExperienceOrbEntity orb = event.getOrb();

        player.xpCooldown = 2;
        player.onItemPickup(orb, 1);

        if (orb.xpValue > 0) {
            player.giveExperiencePoints(orb.xpValue);
        }
        orb.remove();
        event.setCanceled(true);
    }
    // endregion

    // region SOULBOUND
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void handlePlayerDropsEvent(LivingDropsEvent event) {

        if (event.getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            if (Utils.isFakePlayer(player)) {
                return;
            }
            if (player.world.getGameRules().getBoolean(KEEP_INVENTORY)) {
                return;
            }
            Iterator<ItemEntity> iter = event.getDrops().iterator();
            while (iter.hasNext()) {
                ItemEntity drop = iter.next();
                ItemStack stack = drop.getItem();
                if (getEnchantmentLevel(SOULBOUND, stack) > 0) {
                    if (addToPlayerInventory(player, stack)) {
                        iter.remove();
                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void handlePlayerCloneEvent(PlayerEvent.Clone event) {

        if (!event.isWasDeath()) {
            return;
        }
        PlayerEntity player = event.getPlayer();
        PlayerEntity oldPlayer = event.getOriginal();
        if (Utils.isFakePlayer(player)) {
            return;
        }
        if (player.world.getGameRules().getBoolean(KEEP_INVENTORY)) {
            return;
        }
        for (int i = 0; i < oldPlayer.inventory.armorInventory.size(); ++i) {
            ItemStack stack = oldPlayer.inventory.armorInventory.get(i);
            int encSoulbound = getEnchantmentLevel(SOULBOUND, stack);
            if (encSoulbound > 0) {
                if (SoulboundEnchantment.permanent) {
                    if (encSoulbound > 1) {
                        removeEnchantment(stack, SOULBOUND);
                        addEnchantment(stack, SOULBOUND, 1);
                    }
                } else if (player.world.rand.nextInt(1 + encSoulbound) == 0) {
                    removeEnchantment(stack, SOULBOUND);
                    if (encSoulbound > 1) {
                        addEnchantment(stack, SOULBOUND, encSoulbound - 1);
                    }
                }
                if (addToPlayerInventory(player, stack)) {
                    oldPlayer.inventory.armorInventory.set(i, ItemStack.EMPTY);
                }
            }
        }
        for (int i = 0; i < oldPlayer.inventory.mainInventory.size(); ++i) {
            ItemStack stack = oldPlayer.inventory.mainInventory.get(i);
            int encSoulbound = getEnchantmentLevel(SOULBOUND, stack);
            if (encSoulbound > 0) {
                if (player.world.rand.nextInt(1 + encSoulbound) == 0) {
                    removeEnchantment(stack, SOULBOUND);
                    if (encSoulbound > 1) {
                        addEnchantment(stack, SOULBOUND, encSoulbound - 1);
                    }
                }
                if (addToPlayerInventory(player, stack)) {
                    oldPlayer.inventory.mainInventory.set(i, ItemStack.EMPTY);
                }
            }
        }
    }
    // endregion

    // region TICK HANDLING
    @SubscribeEvent
    public void handleTickEndEvent(TickEvent.ServerTickEvent event) {

        if (event.phase == TickEvent.Phase.END) {
            FireRebukeEnchantment.setFireToMobs();
        }
    }
    // endregion

    // region HELPERS
    private static boolean canBlockDamageSource(LivingEntity living, DamageSource source) {

        Entity entity = source.getImmediateSource();
        if (entity instanceof AbstractArrowEntity) {
            AbstractArrowEntity arrow = (AbstractArrowEntity) entity;
            if (arrow.getPierceLevel() > 0) {
                return false;
            }
        }
        if (!source.isUnblockable() && living.isActiveItemStackBlocking()) {
            Vec3d vec3d2 = source.getDamageLocation();
            if (vec3d2 != null) {
                Vec3d vec3d = living.getLook(1.0F);
                Vec3d vec3d1 = vec3d2.subtractReverse(new Vec3d(living.posX, living.posY, living.posZ)).normalize();
                vec3d1 = new Vec3d(vec3d1.x, 0.0D, vec3d1.z);
                return vec3d1.dotProduct(vec3d) < 0.0D;
            }
        }
        return false;
    }
    // endregion
}
