package com.blackgear.bgcore.client.renderer;

import com.blackgear.bgcore.client.model.AxolotlBristleModel;
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
public class AxolotlBristleRenderer extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {
    private static final ResourceLocation AXOLOTL_BRISTLES = new ResourceLocation(BGCore.MOD_ID, "textures/patreon/misc/axolotl_bristles.png");
    private final AxolotlBristleModel<AbstractClientPlayerEntity> bristleModel;

    public AxolotlBristleRenderer(IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> entityRendererIn) {
        super(entityRendererIn);
        this.bristleModel = new AxolotlBristleModel<>(entityRendererIn.getEntityModel());
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, AbstractClientPlayerEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.isInvisible()) {
            return;
        }

        ModelRenderer bristles = this.bristleModel.bristles;

        bristles.copyModelAngles(this.getEntityModel().bipedHead);
        bristles.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntityCutout(AXOLOTL_BRISTLES)), packedLightIn, OverlayTexture.NO_OVERLAY);
    }
}