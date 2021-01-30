package com.blackgear.bgcore.common.world.biome;

import java.util.Optional;

//<>

public class BiomeEffects {
    private final Optional<ParticleBiomeEffect> particles;
    
    public BiomeEffects(Optional<ParticleBiomeEffect> particles) {
        this.particles = particles;
    }

    public Optional<ParticleBiomeEffect> getParticles() {
        return this.particles;
    }

    public static class Builder {
        private Optional<ParticleBiomeEffect> particle = Optional.empty();

        public BiomeEffects.Builder setParticles(ParticleBiomeEffect particle) {
            this.particle = Optional.of(particle);
            return this;
        }

        public BiomeEffects build() {
            return new BiomeEffects(this.particle);
        }
    }
}