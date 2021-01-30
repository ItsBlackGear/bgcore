package com.blackgear.bgcore.client.event;

import com.blackgear.bgcore.client.renderer.AxolotlBristleRenderer;
import com.blackgear.bgcore.client.renderer.WardenBristleRenderer;
import com.blackgear.bgcore.core.BGCore;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.Set;

//<>

@Mod.EventBusSubscriber(modid = BGCore.MOD_ID)
public class RewardRendererEvent {
    private static final Set<String> RENDERER           = Sets.newHashSet();
    private static final ResourceLocation STRIDER_CAPE  = new ResourceLocation(BGCore.MOD_ID, "textures/patreon/capes/strider.png");
    private static final ResourceLocation AXOLOTL_CAPE  = new ResourceLocation(BGCore.MOD_ID, "textures/patreon/capes/axolotl.png");
    private static final ResourceLocation WARDEN_CAPE   = new ResourceLocation(BGCore.MOD_ID, "textures/patreon/capes/warden.png");
    private static final ImmutableSet<String> STRIDER   = ImmutableSet.of(
            "78c330d0-edd6-48d3-8d38-69164f8d875f", //Canary_meow
            "73f7d96e-0593-4f58-ae45-2b7b7bf762e7"  //KKot1k
    );
    private static final ImmutableSet<String> AXOLOTL   = ImmutableSet.of(
    );
    private static final ImmutableSet<String> WARDEN    = ImmutableSet.of(
            "58c28704-b377-4080-b3cf-e53bc53eda0a"  //BlackGear27
    );

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void playerRendererEvent(RenderPlayerEvent.Post event) {
        PlayerEntity playerEntity = event.getPlayer();
        String uuid = PlayerEntity.getUUID(playerEntity.getGameProfile()).toString();
        if (playerEntity instanceof AbstractClientPlayerEntity && STRIDER.contains(uuid) && !RENDERER.contains(uuid)) {
            AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)playerEntity;
            if (abstractClientPlayerEntity.hasPlayerInfo()) {
                Map<MinecraftProfileTexture.Type, ResourceLocation> playerTextures = abstractClientPlayerEntity.playerInfo.playerTextures;
                playerTextures.put(MinecraftProfileTexture.Type.CAPE, STRIDER_CAPE);
                playerTextures.put(MinecraftProfileTexture.Type.ELYTRA, STRIDER_CAPE);
                RENDERER.add(uuid);
            }
        }
        if (playerEntity instanceof AbstractClientPlayerEntity && AXOLOTL.contains(uuid) && !RENDERER.contains(uuid)) {
            AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)playerEntity;
            if (abstractClientPlayerEntity.hasPlayerInfo()) {
                Map<MinecraftProfileTexture.Type, ResourceLocation> playerTextures = abstractClientPlayerEntity.playerInfo.playerTextures;
                playerTextures.put(MinecraftProfileTexture.Type.CAPE, AXOLOTL_CAPE);
                playerTextures.put(MinecraftProfileTexture.Type.ELYTRA, AXOLOTL_CAPE);
                for (PlayerRenderer renderer : Minecraft.getInstance().getRenderManager().getSkinMap().values()) {
                    renderer.addLayer(new AxolotlBristleRenderer(renderer));
                }
                RENDERER.add(uuid);
            }
        }
        if (playerEntity instanceof AbstractClientPlayerEntity && WARDEN.contains(uuid) && !RENDERER.contains(uuid)) {
            AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)playerEntity;
            if (abstractClientPlayerEntity.hasPlayerInfo()) {
                Map<MinecraftProfileTexture.Type, ResourceLocation> playerTextures = abstractClientPlayerEntity.playerInfo.playerTextures;
                playerTextures.put(MinecraftProfileTexture.Type.CAPE, WARDEN_CAPE);
                playerTextures.put(MinecraftProfileTexture.Type.ELYTRA, WARDEN_CAPE);
                for (PlayerRenderer renderer : Minecraft.getInstance().getRenderManager().getSkinMap().values()) {
                    renderer.addLayer(new WardenBristleRenderer(renderer));
                }
                RENDERER.add(uuid);
            }
        }
    }
}