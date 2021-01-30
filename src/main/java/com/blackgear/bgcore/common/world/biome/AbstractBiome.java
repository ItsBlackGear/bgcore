package com.blackgear.bgcore.common.world.biome;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

//<>

public abstract class AbstractBiome extends Biome {
    private final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilderSupplier;
    private final BiomeEffects biomeEffects;

    /**
     * creates a biome with a customized surface builder
     * @param biomeBuilder              the biome building parameters
     * @param surfaceBuilderSupplier    the supplied surface builder
     */
    public AbstractBiome(Builder biomeBuilder, Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilderSupplier) {
        this(biomeBuilder, surfaceBuilderSupplier, new AbstractBiome.EffectBuilder().getEffects(new BiomeEffects.Builder().build()));
    }

    /**
     * creates a biome with a customized surface builder and biome effects (particles)
     * @param biomeBuilder              the biome building parameters
     * @param surfaceBuilderSupplier    the supplied surface builder
     * @param effectBuilder             the biome effects building parameters
     */
    public AbstractBiome(Builder biomeBuilder, Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilderSupplier, EffectBuilder effectBuilder) {
        super(biomeBuilder);
        this.surfaceBuilderSupplier = surfaceBuilderSupplier;
        this.biomeEffects = effectBuilder.biomeEffects;
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed) {
        this.surfaceBuilderSupplier.get().setSeed(seed);
        this.surfaceBuilderSupplier.get().buildSurface(random, chunkIn, this, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed);
    }

    @Override
    public ConfiguredSurfaceBuilder<?> getSurfaceBuilder() {
        return this.surfaceBuilderSupplier.get();
    }

    @Override
    public ISurfaceBuilderConfig getSurfaceBuilderConfig() {
        return this.surfaceBuilderSupplier.get().getConfig();
    }

    public BiomeEffects getBiomeEffects() {
        return this.biomeEffects;
    }

    public Optional<ParticleBiomeEffect> getParticles() {
        return this.biomeEffects.getParticles();
    }

    public static class EffectBuilder {
        private BiomeEffects biomeEffects;

        public EffectBuilder getEffects(BiomeEffects effects) {
            this.biomeEffects = effects;
            return this;
        }
    }
}