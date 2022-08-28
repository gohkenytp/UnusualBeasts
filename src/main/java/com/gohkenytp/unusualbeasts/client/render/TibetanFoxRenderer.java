
package com.gohkenytp.unusualbeasts.client.render;

import com.gohkenytp.unusualbeasts.client.ModModelLayers;
import com.gohkenytp.unusualbeasts.client.model.TibetanFoxModel;
import com.gohkenytp.unusualbeasts.entity.TibetanFox;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TibetanFoxRenderer extends MobRenderer<TibetanFox, TibetanFoxModel<TibetanFox>> {
	public TibetanFoxRenderer(EntityRendererProvider.Context context) {
		super(context, new TibetanFoxModel<>(context.bakeLayer(ModModelLayers.TIBETAN_FOX)), 0.2f);
	}

	@Override
	public ResourceLocation getTextureLocation(TibetanFox entity) {
		return new ResourceLocation("unusualbeasts:textures/entity/tibetanfoxtexture.png");
	}
}
