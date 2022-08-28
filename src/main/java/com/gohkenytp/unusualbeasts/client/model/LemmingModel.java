package com.gohkenytp.unusualbeasts.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class LemmingModel<T extends Entity> extends EntityModel<T> {
	public final ModelPart rearFootLeft;
	public final ModelPart rearFootRight;
	public final ModelPart body;
	public final ModelPart frontLegLeft;
	public final ModelPart frontLegRight;
	public final ModelPart head;
	public final ModelPart tail;

	public LemmingModel(ModelPart root) {
		this.rearFootLeft = root.getChild("rearFootLeft");
		this.rearFootRight = root.getChild("rearFootRight");
		this.body = root.getChild("body");
		this.frontLegLeft = root.getChild("frontLegLeft");
		this.frontLegRight = root.getChild("frontLegRight");
		this.head = root.getChild("head");
		this.tail = root.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition rearFootLeft = partdefinition.addOrReplaceChild("rearFootLeft",
				CubeListBuilder.create().texOffs(18, 0).addBox(0.0F, 5.5F, 0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-3.0F, 17.5F, 3.7F));
		PartDefinition rearFootRight = partdefinition.addOrReplaceChild("rearFootRight",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 5.5F, 0.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(3.0F, 17.5F, 3.7F));
		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 19.0F, 0.0F));
		PartDefinition frontLegLeft = partdefinition.addOrReplaceChild("frontLegLeft",
				CubeListBuilder.create().texOffs(18, 18).addBox(-6.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(3.0F, 24.0F, 0.0F));
		PartDefinition frontLegRight = partdefinition.addOrReplaceChild("frontLegRight",
				CubeListBuilder.create().texOffs(0, 19).addBox(-1.0F, 6.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(3.0F, 17.0F, -1.0F));
		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 10).addBox(-2.5F, 2.0F, -3.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 16.0F, -1.0F));
		PartDefinition tail = partdefinition.addOrReplaceChild("tail",
				CubeListBuilder.create().texOffs(15, 10).addBox(-0.4F, -3.6F, 5.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 22.0F, -1.0F, -0.3491F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green,
			float blue, float alpha) {
		rearFootLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rearFootRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		frontLegLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		frontLegRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
	}
}
