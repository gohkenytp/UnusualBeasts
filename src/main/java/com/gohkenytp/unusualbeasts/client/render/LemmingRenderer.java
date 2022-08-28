package com.gohkenytp.unusualbeasts.client.render;

import com.gohkenytp.unusualbeasts.client.ModModelLayers;
import com.gohkenytp.unusualbeasts.client.model.LemmingModel;
import com.gohkenytp.unusualbeasts.entity.Lemming;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LemmingRenderer extends MobRenderer<Lemming, LemmingModel<Lemming>> {
    public LemmingRenderer(EntityRendererProvider.Context context) {
        super(context, new LemmingModel<>(context.bakeLayer(ModModelLayers.LEMMING)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(Lemming entity) {
        return new ResourceLocation("unusualbeasts:textures/entity/lemming.png");
    }
}
