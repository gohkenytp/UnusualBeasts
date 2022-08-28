package com.gohkenytp.unusualbeasts;

import com.gohkenytp.unusualbeasts.client.ClientRegistrar;
import com.gohkenytp.unusualbeasts.registry.ModBiomeModifiers;
import com.gohkenytp.unusualbeasts.registry.ModEntities;
import com.gohkenytp.unusualbeasts.registry.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(UnusualBeasts.MODID)
public class UnusualBeasts {
	public static final String MODID = "unusualbeasts";

	public UnusualBeasts() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModEntities.ENTITIES.register(modBus);
		ModItems.ITEMS.register(modBus);
		ModBiomeModifiers.register(modBus);

		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientRegistrar::setup));
	}

	private void setup(final FMLCommonSetupEvent event) {
		ModEntities.spawnPlacementSetup();
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {

	}

	private void processIMC(final InterModProcessEvent event) {
	}
}
