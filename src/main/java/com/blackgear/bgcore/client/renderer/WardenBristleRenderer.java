package com.blackgear.bgcore.client.renderer;

import com.blackgear.bgcore.client.model.WardenBristleModel;
import com.blackgear.bgcore.core.BGCore;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//<>

@OnlyIn(Dist.CLIENT)
public class WardenBristleRenderer extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {
    private static final ResourceLocation WARDEN_BRISTLES = new ResourceLocation(BGCore.MOD_ID, "textures/patreon/misc/warden_bristles.png");
    private final WardenBristleModel<AbstractClientPlayerEntity> bristleModel;

    public WardenBristleRenderer(IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> entityRendererIn) {
        super(entityRendererIn);
        this.bristleModel = new WardenBristleModel<>(entityRendererIn.getEntityModel());
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, AbstractClientPlayerEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.isInvisible()) {
            return;
        }

        ModelRenderer rightBristle = this.bristleModel.rightBristle;
        ModelRenderer leftBristle = this.bristleModel.leftBristle;

        rightBristle.copyModelAngles(this.getEntityModel().bipedHead);
        rightBristle.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntityCutout(WARDEN_BRISTLES)), packedLightIn, OverlayTexture.NO_OVERLAY);
        leftBristle.copyModelAngles(this.getEntityModel().bipedHead);
        leftBristle.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntityCutout(WARDEN_BRISTLES)), packedLightIn, OverlayTexture.NO_OVERLAY);
    }
}