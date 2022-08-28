
package com.gohkenytp.unusualbeasts.entity;

import com.gohkenytp.unusualbeasts.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

@Mod.EventBusSubscriber
public class Lemming extends Animal {

	public Lemming(EntityType<Lemming> type, Level world) {
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
		this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, TibetanFox.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(6, new AvoidEntityGoal<>(this, Fox.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(7, new AvoidEntityGoal<>(this, Cat.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(8, new AvoidEntityGoal<>(this, Ocelot.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(9, new AvoidEntityGoal<>(this, IronGolem.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(10, new AvoidEntityGoal<>(this, Player.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(11, new AvoidEntityGoal<>(this, Pillager.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(12, new AvoidEntityGoal<>(this, Villager.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(13, new AvoidEntityGoal<>(this, Evoker.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(14, new AvoidEntityGoal<>(this, Vindicator.class, (float) 6, 1, 1.2));
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.rabbit.ambient"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.rabbit.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.rabbit.death"));
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
		Lemming retval = ModEntities.LEMMING.get().create(serverWorld);
		retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null, null);
		return retval;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return List.of(Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, Items.MELON_SEEDS, Items.BEETROOT_SEEDS).contains(stack.getItem());
	}

	public static void init() {
		SpawnPlacements.register(ModEntities.LEMMING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos,
				 random) -> (world.getBlockState(pos.below()).getMaterial() == Material.SNOW));
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 10);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		return builder;
	}

	public static boolean checkLemmingSpawnRules(EntityType<Lemming> p_218207_, LevelAccessor p_218208_, MobSpawnType p_218209_, BlockPos p_218210_, RandomSource p_218211_) {
		return p_218211_.nextInt(3) != 0;
	}
}
