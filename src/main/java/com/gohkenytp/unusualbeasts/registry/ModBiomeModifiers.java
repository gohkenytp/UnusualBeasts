package com.gohkenytp.unusualbeasts.registry;

import com.gohkenytp.unusualbeasts.UnusualBeasts;
import com.gohkenytp.unusualbeasts.world.biome.UBBiomeModifier;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers {
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, UnusualBeasts.MODID);

	public static final RegistryObject<Codec<UBBiomeModifier>>  SPAWN_CODEC = BIOME_MODIFIER_SERIALIZERS.register("spawning", () ->
			RecordCodecBuilder.create(builder -> builder.group(
					Biome.LIST_CODEC.fieldOf("biomes").forGetter(UBBiomeModifier::biomes),
					MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawn").forGetter(UBBiomeModifier::spawn)
			).apply(builder, UBBiomeModifier::new)));

	public static void register(IEventBus eventBus) {
		BIOME_MODIFIER_SERIALIZERS.register(eventBus);
	}
}