package com.gohkenytp.unusualbeasts.world.biome;

import com.gohkenytp.unusualbeasts.registry.ModBiomeModifiers;
import com.gohkenytp.unusualbeasts.registry.ModEntities;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record UBBiomeModifier(HolderSet<Biome> biomes, MobSpawnSettings.SpawnerData spawn) implements BiomeModifier {

	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase == Phase.ADD && this.biomes.contains(biome)) {

			if (biome.is(Biomes.LUSH_CAVES)) {
				builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.MOSSLING.get(), 7, 1, 2));
			}

			if (biome.is(Biomes.SNOWY_SLOPES) || biome.is(Biomes.FROZEN_PEAKS) || biome.is(Biomes.JAGGED_PEAKS)) {
				builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.LEMMING.get(), 15, 1, 5));
				builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.TIBETAN_FOX.get(), 8, 1, 1));
			}

			if (biome.is(Biomes.SOUL_SAND_VALLEY)) {
				builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.SOUL.get(), 18, 1, 3));
				builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.THE_INSATIABLE.get(), 5, 1, 1));
			}

		}
	}

	@Override
	public Codec<? extends BiomeModifier> codec() {
		return ModBiomeModifiers.SPAWN_CODEC.get();
	}
}