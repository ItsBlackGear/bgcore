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
public class WardenBristleModel<T extends LivingEntity> extends AgeableModel<T> {
    private PlayerModel<AbstractClientPlayerEntity> playerModel;
    public ModelRenderer rightBristle;
    public ModelRenderer leftBristle;

    public WardenBristleModel(PlayerModel<AbstractClientPlayerEntity> playerModel) {
        this.playerModel = playerModel;
        this.textureWidth = 16;
        this.textureHeight = 12;

        this.rightBristle = new ModelRenderer(this, 0, 0);
        this.rightBristle.addBox(4.0F, -14.0F, 0.0F, 8.0F, 12.0F, 0.0F, 0.0F, false);
        this.leftBristle = new ModelRenderer(this, 0, 0);
        this.leftBristle.addBox(-12.0F, -14.0F, 0.0F, 8.0F, 12.0F, 0.0F, 0.0F, true);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(this.rightBristle, this.leftBristle);
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return null;
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}