package com.mrmx.Barlious_Dream.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class EnderBagCurioRenderer implements ICurioRenderer {

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(
            ItemStack stack,
            SlotContext slotContext,
            PoseStack poseStack,
            RenderLayerParent<T, M> renderLayerParent,
            MultiBufferSource renderTypeBuffer,
            int light,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch) {

        if (renderLayerParent.getModel() instanceof HumanoidModel) {
            @SuppressWarnings("unchecked")
            HumanoidModel<LivingEntity> humanoidModel = (HumanoidModel<LivingEntity>) renderLayerParent.getModel();
            poseStack.pushPose();
            ICurioRenderer.followBodyRotations(slotContext.entity(), humanoidModel);

            humanoidModel.body.translateAndRotate(poseStack);
            poseStack.translate(-0.125D, 0.6125D, -0.145D);
            poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
            poseStack.scale(0.345F, 0.345F, 0.69F);

            Minecraft.getInstance().getItemRenderer().renderStatic(
                    stack,
                    ItemDisplayContext.FIXED,
                    light,
                    OverlayTexture.NO_OVERLAY,
                    poseStack,
                    renderTypeBuffer,
                    slotContext.entity().level(),
                    0
            ); poseStack.popPose();
        }
    }
}