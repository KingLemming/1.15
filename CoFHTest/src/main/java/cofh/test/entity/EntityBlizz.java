//package cofh.test.entity;
//
//import cofh.thermal.core.ThermalSeries;
//import cofh.thermal.core.init.SoundsTSeries;
//import cofh.thermal.foundation.entity.projectile.EntityBlizzBolt;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.EnumCreatureType;
//import net.minecraft.entity.SharedMonsterAttributes;
//import net.minecraft.entity.ai.attributes.IAttributeInstance;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.util.EnumParticleTypes;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.storage.loot.LootTableList;
//import net.minecraftforge.common.BiomeDictionary;
//import net.minecraftforge.common.BiomeDictionary.Type;
//import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
//import net.minecraftforge.fml.common.registry.ForgeRegistries;
//
//import javax.annotation.Nullable;
//import java.util.HashSet;
//import java.util.Set;
//
//import static cofh.lib.util.Constants.ENTITY_TRACKING_DISTANCE;
//import static cofh.lib.util.Constants.ID_THERMAL_SERIES;
//import static cofh.thermal.core.init.ConfigTSeries.disableAllHostileMobSpawns;
//
//public class EntityBlizz extends EntityElemental {
//
//	public static final String NAME = "blizz";
//	protected static ResourceLocation lootTable;
//
//	protected static boolean enableSpawn = true;
//	protected static boolean enableEgg = true;
//
//	protected static int spawnLightLevel = 8;
//	protected static int spawnWeight = 10;
//	protected static int spawnMin = 1;
//	protected static int spawnMax = 4;
//
//	public static boolean effect = true;
//
//	public static void initialize(int id) {
//
//		config();
//
//		lootTable = LootTableList.register(new ResourceLocation(ID_THERMAL_SERIES, "entities/" + NAME));
//
//		Set<Biome> validBiomes = new HashSet<>();
//
//		validBiomes.addAll(BiomeDictionary.getBiomes(Type.COLD));
//		validBiomes.addAll(BiomeDictionary.getBiomes(Type.SNOWY));
//		validBiomes.removeAll(BiomeDictionary.getBiomes(Type.NETHER));
//		validBiomes.removeAll(BiomeDictionary.getBiomes(Type.END));
//
//		EntityEntryBuilder builder = EntityEntryBuilder.create()//
//				.entity(EntityBlizz.class)//
//				.id(new ResourceLocation(ID_THERMAL_SERIES + ":" + NAME), id)//
//				.name(ID_THERMAL_SERIES + "." + NAME)//
//				.tracker(ENTITY_TRACKING_DISTANCE, 1, true);
//
//		if (enableEgg) {
//			builder.egg(0xE0FBFF, 0x6BE6FF);
//		}
//		if (enableSpawn && !disableAllHostileMobSpawns) {
//			builder.spawn(EnumCreatureType.MONSTER, spawnWeight, spawnMin, spawnMax, validBiomes);
//		}
//		ForgeRegistries.ENTITIES.register(builder.build());
//	}
//
//	public static void config() {
//
//		String category = "Mobs.Blizz";
//		String comment;
//
//		comment = "If TRUE, Blizzes will appear naturally.";
//		enableSpawn = ThermalSeries.config.getBoolean("Enable Spawn", category, enableSpawn, comment);
//
//		comment = "If TRUE, Blizzes will have a spawn egg item created.";
//		enableEgg = ThermalSeries.config.getBoolean("Enable Egg", category, enableEgg, comment);
//
//		comment = "This sets the maximum light level Blizzes can spawn at.";
//		spawnLightLevel = ThermalSeries.config.getInt("Light Level", category, spawnLightLevel, 0, 15, comment);
//
//		comment = "This sets the minimum number of Blizzes that spawn in a group.";
//		spawnMin = ThermalSeries.config.getInt("Min Group Size", category, spawnMin, 1, 10, comment);
//
//		comment = "This sets the maximum number of Blizzes that spawn in a group.";
//		spawnMax = ThermalSeries.config.getInt("Max Group Size", category, spawnMax, spawnMin, 24, comment);
//
//		comment = "This sets the relative spawn weight for Blizzes.";
//		spawnWeight = ThermalSeries.config.getInt("Spawn Weight", category, spawnWeight, 1, 100, comment);
//
//		comment = "If TRUE, Blizz attacks will inflict Slowness.";
//		effect = ThermalSeries.config.getBoolean("Effect", category, effect, comment);
//	}
//
//	public EntityBlizz(World world) {
//
//		super(world);
//
//		ambientParticle = EnumParticleTypes.SNOWBALL;
//		ambientSound = SoundsTSeries.blizzAmbient;
//	}
//
//	@Nullable
//	protected ResourceLocation getLootTable() {
//
//		return lootTable;
//	}
//
//	@Override
//	protected void initEntityAI() {
//
//		tasks.addTask(4, new AIBlizzboltAttack(this));
//		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
//		tasks.addTask(7, new EntityAIWander(this, 1.0D));
//		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
//		tasks.addTask(8, new EntityAILookIdle(this));
//		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
//		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
//	}
//
//	@Override
//	protected int getSpawnLightLevel() {
//
//		return spawnLightLevel;
//	}
//
//	/* ATTACK */
//	static class AIBlizzboltAttack extends EntityAIBase {
//
//		private final EntityBlizz blizz;
//		private int attackStep;
//		private int attackTime;
//
//		public AIBlizzboltAttack(EntityBlizz entity) {
//
//			blizz = entity;
//			setMutexBits(3);
//		}
//
//		@Override
//		public boolean shouldExecute() {
//
//			EntityLivingBase target = blizz.getAttackTarget();
//			return target != null && target.isEntityAlive();
//		}
//
//		@Override
//		public void startExecuting() {
//
//			attackStep = 0;
//		}
//
//		@Override
//		public void resetTask() {
//
//			blizz.setInAttackMode(false);
//		}
//
//		@Override
//		public void updateTask() {
//
//			--attackTime;
//			EntityLivingBase target = blizz.getAttackTarget();
//			double d0 = blizz.getDistanceSq(target);
//
//			if (d0 < 4.0D) {
//				if (attackTime <= 0) {
//					attackTime = 20;
//					blizz.attackEntityAsMob(target);
//				}
//
//				blizz.getMoveHelper().setMoveTo(target.posX, target.posY, target.posZ, 1.0D);
//			} else if (d0 < getFollowDistance() * getFollowDistance()) {
//
//				if (attackTime <= 0) {
//					++attackStep;
//
//					if (attackStep == 1) {
//						attackTime = 60;
//						blizz.setInAttackMode(true);
//					} else if (attackStep <= 4) {
//						attackTime = 6;
//					} else {
//						attackTime = 100;
//						attackStep = 0;
//						blizz.setInAttackMode(false);
//					}
//
//					if (attackStep > 1) {
//						blizz.world.playEvent(null, 1009, new BlockPos((int) blizz.posX, (int) blizz.posY, (int) blizz.posZ), 0);
//
//						for (int i = 0; i < 1; ++i) {
//							EntityBlizzBolt bolt = new EntityBlizzBolt(blizz.world, blizz);
//							bolt.posY = blizz.posY + blizz.height / 2.0F + 0.5D;
//							bolt.shoot(target.posX - blizz.posX, target.posY - blizz.posY, target.posZ - blizz.posZ, 1.0F, 1.0F);
//							blizz.playSound(SoundsTSeries.blizzAttack, 2.0F, (blizz.rand.nextFloat() - blizz.rand.nextFloat()) * 0.2F + 1.0F);
//							blizz.world.spawnEntity(bolt);
//						}
//					}
//				}
//				blizz.getLookHelper().setLookPositionWithEntity(target, 10.0F, 10.0F);
//			} else {
//				blizz.getNavigator().clearPath();
//				blizz.getMoveHelper().setMoveTo(target.posX, target.posY, target.posZ, 1.0D);
//			}
//			super.updateTask();
//		}
//
//		private double getFollowDistance() {
//
//			IAttributeInstance attribute = this.blizz.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
//			return attribute == null ? 16.0D : attribute.getAttributeValue();
//		}
//	}
//
//}
