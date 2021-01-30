package com.blackgear.bgcore.common.world.biome;

import net.minecraft.particles.IParticleData;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;
import java.util.function.Supplier;

//<>

public class ParticleBiomeEffect {
    private final Supplier<IParticleData> particleSupplier;
    private final float probability;

    public ParticleBiomeEffect(Supplier<IParticleData> particleSupplier, float probability) {
        this.particleSupplier = particleSupplier;
        this.probability = probability;
    }

    @OnlyIn(Dist.CLIENT)
    public IParticleData getParticle() {
        return this.particleSupplier.get();
    }

    @OnlyIn(Dist.CLIENT)
    public boolean shouldAddParticles(Random rand) {
        return rand.nextFloat() <= this.probability;
    }
}