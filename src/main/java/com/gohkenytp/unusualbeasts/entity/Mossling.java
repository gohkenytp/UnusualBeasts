
package com.gohkenytp.unusualbeasts.entity;

import com.gohkenytp.unusualbeasts.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber
public class Mossling extends Animal {

	public Mossling(EntityType<Mossling> type, Level world) {
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
		this.goalSelector.addGoal(5, new PanicGoal(this, 1.2));
		this.goalSelector.addGoal(6, new AvoidEntityGoal<>(this, Player.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(7, new AvoidEntityGoal<>(this, ServerPlayer.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(8, new TemptGoal(this, 1, Ingredient.of(Items.SLIME_BALL), false));
		this.goalSelector.addGoal(9, new RemoveBlockGoal(Blocks.BIG_DRIPLEAF, this, 1, (int) 3));
		this.goalSelector.addGoal(10, new RemoveBlockGoal(Blocks.BIG_DRIPLEAF_STEM, this, 1, (int) 3));
		this.goalSelector.addGoal(11, new RemoveBlockGoal(Blocks.SMALL_DRIPLEAF, this, 1, (int) 3));
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
		super.dropCustomDeathLoot(source, looting, recentlyHitIn);
		this.spawnAtLocation(new ItemStack(Blocks.MOSS_BLOCK));
	}

	@Override
	public SoundEvent getAmbientSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zoglin.ambient"));
	}

	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.cow.step"))), 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.moss.hit"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.moss.break"));
	}

	public boolean hurt(DamageSource source, float amount) {
		boolean prev = super.hurt(source, amount);
		if(prev && source.getDirectEntity() instanceof LivingEntity){
			LivingEntity entity = (LivingEntity)source.getDirectEntity();
			entity.addEffect(new MobEffectInstance(MobEffects.POISON, 100));
		}
		return prev;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
		Mossling retval = ModEntities.MOSSLING.get().create(serverWorld);
		retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null, null);
		return retval;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return List.of(Items.CLAY_BALL, Items.SLIME_BALL).contains(stack.getItem());
	}

	public void aiStep() {
		super.aiStep();
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Entity entity = this;
		Level world = this.level;
		for (int l = 0; l < 4; ++l) {
			double x0 = x + random.nextFloat();
			double y0 = y + random.nextFloat();
			double z0 = z + random.nextFloat();
			double dx = (random.nextFloat() - 0.5D) * 0.5D;
			double dy = (random.nextFloat() - 0.5D) * 0.5D;
			double dz = (random.nextFloat() - 0.5D) * 0.5D;
			world.addParticle(ParticleTypes.SPORE_BLOSSOM_AIR, x0, y0, z0, dx, dy, dz);
		}
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.2);
		builder = builder.add(Attributes.MAX_HEALTH, 6);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		return builder;
	}

	public static boolean checkMosslingSpawnRules(EntityType<Mossling> p_218207_, LevelAccessor p_218208_, MobSpawnType p_218209_, BlockPos p_218210_, RandomSource p_218211_) {
		return p_218211_.nextInt(3) != 0;
	}
}
