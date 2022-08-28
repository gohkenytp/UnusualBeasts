package com.gohkenytp.unusualbeasts.registry;

import com.gohkenytp.unusualbeasts.UnusualBeasts;
import com.gohkenytp.unusualbeasts.entity.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = UnusualBeasts.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, UnusualBeasts.MODID);
	public static final RegistryObject<EntityType<Mossling>> MOSSLING = ENTITIES.register("mossling", () -> EntityType.Builder.of(Mossling::new, MobCategory.CREATURE).sized(0.3F, 0.3F).clientTrackingRange(10).build(prefix("mossling")));
	public static final RegistryObject<EntityType<Lemming>> LEMMING = ENTITIES.register("lemming", () -> EntityType.Builder.of(Lemming::new, MobCategory.CREATURE).sized(0.3F, 0.3F).clientTrackingRange(10).build(prefix("lemming")));
	public static final RegistryObject<EntityType<TibetanFox>> TIBETAN_FOX = ENTITIES.register("tibetan_fox", () -> EntityType.Builder.of(TibetanFox::new, MobCategory.CREATURE).sized(0.6F, 0.6F).clientTrackingRange(10).build(prefix("tibetan_fox")));
	
	private static String prefix(String path) {
		return UnusualBeasts.MODID + "." + path;
	}

	public static void spawnPlacementSetup() {
		SpawnPlacements.register(MOSSLING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mossling::checkMosslingSpawnRules);
		SpawnPlacements.register(LEMMING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Lemming::checkLemmingSpawnRules);
		SpawnPlacements.register(TIBETAN_FOX.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TibetanFox::checkTibetanFoxSpawnRules);
	}

	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(MOSSLING.get(), Mossling.createAttributes().build());
		event.put(LEMMING.get(), Lemming.createAttributes().build());
		event.put(TIBETAN_FOX.get(), TibetanFox.createAttributes().build());
	}
}
