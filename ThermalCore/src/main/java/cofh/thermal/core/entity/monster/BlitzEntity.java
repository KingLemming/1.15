package cofh.thermal.core.entity.monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.EnumSet;

public class BlitzEntity extends MonsterEntity {

    private float heightOffset = 0.5F;
    private int heightOffsetUpdateTime;
    private static final DataParameter<Byte> ANGRY = EntityDataManager.createKey(BlitzEntity.class, DataSerializers.BYTE);

    public BlitzEntity(EntityType<? extends BlitzEntity> type, World world) {

        super(type, world);
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
        this.experienceValue = 10;
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(4, new FireballAttackGoal(this));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    protected void registerAttributes() {

        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4F);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
    }

    @Override
    protected void registerData() {

        super.registerData();
        this.dataManager.register(ANGRY, (byte) 0);
    }

    @Override
    protected SoundEvent getAmbientSound() {

        return SoundEvents.ENTITY_BLAZE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {

        return SoundEvents.ENTITY_BLAZE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {

        return SoundEvents.ENTITY_BLAZE_DEATH;
    }

    @Override
    public void livingTick() {

        if (!this.onGround && this.getMotion().y < 0.0D) {
            this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
        }
        if (this.world.isRemote) {
            if (this.rand.nextInt(24) == 0 && !this.isSilent()) {
                this.world.playSound(this.getPosX() + 0.5D, this.getPosY() + 0.5D, this.getPosZ() + 0.5D, SoundEvents.ENTITY_BLAZE_BURN, this.getSoundCategory(), 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
            }
            if (this.rand.nextInt(6) == 0) {
                this.world.addParticle(ParticleTypes.CLOUD, this.getPosXRandom(0.5D), this.getPosYRandom(), this.getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
            }
        }
        super.livingTick();
    }

    @Override
    protected void updateAITasks() {

        --this.heightOffsetUpdateTime;
        if (this.heightOffsetUpdateTime <= 0) {
            this.heightOffsetUpdateTime = 100;
            this.heightOffset = 0.5F + (float) this.rand.nextGaussian() * 3.0F;
        }
        LivingEntity livingentity = this.getAttackTarget();
        if (livingentity != null && livingentity.getPosYEye() > this.getPosYEye() + (double) this.heightOffset && this.canAttack(livingentity)) {
            Vec3d vec3d = this.getMotion();
            this.setMotion(this.getMotion().add(0.0D, ((double) 0.3F - vec3d.y) * (double) 0.3F, 0.0D));
            this.isAirBorne = true;
        }
        super.updateAITasks();
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {

        return false;
    }

    // region ANGER MANAGEMENT
    public boolean isAngry() {

        return (this.dataManager.get(ANGRY) & 1) != 0;
    }

    protected void setAngry(boolean angry) {

        byte b0 = this.dataManager.get(ANGRY);
        if (angry) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 = (byte) (b0 & -2);
        }
        this.dataManager.set(ANGRY, b0);
    }
    // endregion

    static class FireballAttackGoal extends Goal {

        private final BlitzEntity blaze;
        private int attackStep;
        private int attackTime;
        private int field_223527_d;

        public FireballAttackGoal(BlitzEntity blazeIn) {

            this.blaze = blazeIn;
            this.setMutexFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {

            LivingEntity livingentity = this.blaze.getAttackTarget();
            return livingentity != null && livingentity.isAlive() && this.blaze.canAttack(livingentity);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {

            this.attackStep = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {

            this.blaze.setAngry(false);
            this.field_223527_d = 0;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {

            --this.attackTime;
            LivingEntity livingentity = this.blaze.getAttackTarget();
            if (livingentity != null) {
                boolean flag = this.blaze.getEntitySenses().canSee(livingentity);
                if (flag) {
                    this.field_223527_d = 0;
                } else {
                    ++this.field_223527_d;
                }
                double d0 = this.blaze.getDistanceSq(livingentity);
                if (d0 < 4.0D) {
                    if (!flag) {
                        return;
                    }
                    if (this.attackTime <= 0) {
                        this.attackTime = 20;
                        this.blaze.attackEntityAsMob(livingentity);
                    }
                    this.blaze.getMoveHelper().setMoveTo(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ(), 1.0D);
                } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
                    double d1 = livingentity.getPosX() - this.blaze.getPosX();
                    double d2 = livingentity.getPosYHeight(0.5D) - this.blaze.getPosYHeight(0.5D);
                    double d3 = livingentity.getPosZ() - this.blaze.getPosZ();
                    if (this.attackTime <= 0) {
                        ++this.attackStep;
                        if (this.attackStep == 1) {
                            this.attackTime = 60;
                            this.blaze.setAngry(true);
                        } else if (this.attackStep <= 4) {
                            this.attackTime = 6;
                        } else {
                            this.attackTime = 100;
                            this.attackStep = 0;
                            this.blaze.setAngry(false);
                        }

                        if (this.attackStep > 1) {
                            float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                            this.blaze.world.playEvent(null, 1018, new BlockPos(this.blaze), 0);

                            for (int i = 0; i < 1; ++i) {
                                SmallFireballEntity smallfireballentity = new SmallFireballEntity(this.blaze.world, this.blaze, d1 + this.blaze.getRNG().nextGaussian() * (double) f, d2, d3 + this.blaze.getRNG().nextGaussian() * (double) f);
                                smallfireballentity.setPosition(smallfireballentity.getPosX(), this.blaze.getPosYHeight(0.5D) + 0.5D, smallfireballentity.getPosZ());
                                this.blaze.world.addEntity(smallfireballentity);
                            }
                        }
                    }

                    this.blaze.getLookController().setLookPositionWithEntity(livingentity, 10.0F, 10.0F);
                } else if (this.field_223527_d < 5) {
                    this.blaze.getMoveHelper().setMoveTo(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ(), 1.0D);
                }
                super.tick();
            }
        }

        private double getFollowDistance() {

            return this.blaze.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getValue();
        }

    }

}
