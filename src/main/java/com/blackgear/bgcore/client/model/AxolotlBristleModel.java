package com.blackgear.bgcore.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//<>

@OnlyIn(Dist.CLIENT)
public class AxolotlBristleModel<T extends LivingEntity> extends AgeableModel<T> {
    private PlayerModel<AbstractClientPlayerEntity> playerModel;
    public ModelRenderer bristles;

    public AxolotlBristleModel(PlayerModel<AbstractClientPlayerEntity> playerModel) {
        this.playerModel = playerModel;
        this.textureWidth = 28;
        this.textureHeight = 12;

        this.bristles = new ModelRenderer(this, 0, 2);
        this.bristles.addBox(-7.0F, -11.0F, 2.0F, 14.0F, 10.0F, 0.0F, 0.0F, false);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(this.bristles);
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return null;
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}