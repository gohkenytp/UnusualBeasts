package com.gohkenytp.unusualbeasts.client;

import com.gohkenytp.unusualbeasts.UnusualBeasts;
import com.gohkenytp.unusualbeasts.client.model.*;
import com.gohkenytp.unusualbeasts.client.render.*;
import com.gohkenytp.unusualbeasts.registry.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = UnusualBeasts.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistrar {
	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.MOSSLING.get(), MosslingRenderer::new);
		event.registerEntityRenderer(ModEntities.LEMMING.get(), LemmingRenderer::new);
		event.registerEntityRenderer(ModEntities.TIBETAN_FOX.get(), TibetanFoxRenderer::new);
		event.registerEntityRenderer(ModEntities.SOUL.get(), SoulRenderer::new);
		event.registerEntityRenderer(ModEntities.THE_INSATIABLE.get(), TheInsatiableRenderer::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModModelLayers.MOSSLING, MosslingModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.LEMMING, LemmingModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.TIBETAN_FOX, TibetanFoxModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.SOUL, SoulModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.THE_INSATIABLE, TheInsatiableModel::createBodyLayer);
	}

	public static void setup(FMLCommonSetupEvent event) {
	}
}
