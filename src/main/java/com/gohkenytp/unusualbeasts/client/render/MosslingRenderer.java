
package com.gohkenytp.unusualbeasts.client.render;

import com.gohkenytp.unusualbeasts.client.ModModelLayers;
import com.gohkenytp.unusualbeasts.client.model.MosslingModel;
import com.gohkenytp.unusualbeasts.entity.Mossling;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MosslingRenderer extends MobRenderer<Mossling, MosslingModel<Mossling>> {
	public MosslingRenderer(EntityRendererProvider.Context context) {
		super(context, new MosslingModel<>(context.bakeLayer(ModModelLayers.MOSSLING)), 0.4f);
	}

	@Override
	public ResourceLocation getTextureLocation(Mossling entity) {
		return new ResourceLocation("unusualbeasts:textures/entity/mossling.png");
	}
}
