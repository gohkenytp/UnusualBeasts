
package com.gohkenytp.unusualbeasts.entity;

import com.gohkenytp.unusualbeasts.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

@Mod.EventBusSubscriber
public class TibetanFox extends Animal {

	public TibetanFox(EntityType<TibetanFox> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(3, new FloatGoal(this));
		this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, (float) 0.5));
		this.goalSelector.addGoal(5, new TibetanFox.TibetanFoxMeleeAttackGoal((double)1.2F, true));
		this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Chicken.class, false, false));
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Rabbit.class, false, false));
		this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, Lemming.class, false, false));
		this.goalSelector.addGoal(9, new AvoidEntityGoal<>(this, Player.class, (float) 6, 1, 0.4));
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
		TibetanFox retval = ModEntities.TIBETAN_FOX.get().create(serverWorld);
		retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null, null);
		return retval;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return List.of(Items.CHICKEN, Items.RABBIT).contains(stack.getItem());
	}

	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
		super.dropCustomDeathLoot(source, looting, recentlyHitIn);
		spawnAtLocation(new ItemStack((ItemLike) Blocks.WHITE_WOOL));
	}

	@Override
	public SoundEvent getAmbientSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.fox.ambient"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.fox.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.fox.death"));
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.4);
		builder = builder.add(Attributes.MAX_HEALTH, 10);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 5);
		return builder;
	}

	public static boolean checkTibetanFoxSpawnRules(EntityType<TibetanFox> p_218207_, LevelAccessor p_218208_, MobSpawnType p_218209_, BlockPos p_218210_, RandomSource p_218211_) {
		return p_218211_.nextInt(3) != 0;
	}

	class TibetanFoxMeleeAttackGoal extends MeleeAttackGoal {
		public TibetanFoxMeleeAttackGoal(double p_28720_, boolean p_28721_) {
			super(TibetanFox.this, p_28720_, p_28721_);
		}

		protected void checkAndPerformAttack(LivingEntity p_28724_, double p_28725_) {
			double d0 = this.getAttackReachSqr(p_28724_);
			if (p_28725_ <= d0 && this.isTimeToAttack()) {
				this.resetAttackCooldown();
				this.mob.doHurtTarget(p_28724_);
				TibetanFox.this.playSound(SoundEvents.FOX_BITE, 1.0F, 1.0F);
			}

		}
	}
}
