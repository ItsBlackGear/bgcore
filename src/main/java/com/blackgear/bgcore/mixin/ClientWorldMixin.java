package com.blackgear.bgcore.mixin;

import com.blackgear.bgcore.common.world.biome.AbstractBiome;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.IParticleData;
import net.minecraft.profiler.IProfiler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.AbstractChunkProvider;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;
import java.util.function.BiFunction;

//<>

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin extends World {
    @Shadow public abstract void addParticle(IParticleData particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed);

    protected ClientWorldMixin(WorldInfo info, DimensionType dimType, BiFunction<World, Dimension, AbstractChunkProvider> provider, IProfiler profilerIn, boolean remote) {
        super(info, dimType, provider, profilerIn, remote);
    }

    @Inject(method = "animateTick(III)V", at = @At("RETURN"), cancellable = true)
    private void tick(int posX, int posY, int posZ, CallbackInfo info) {
        Random rand = new Random();

        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (int i = 0; i < 667; i++) {
            this.animate(posX, posY, posZ, 16, rand, pos);
            this.animate(posX, posY, posZ, 32, rand, pos);
        }
    }

    private void animate(int xCenter, int yCenter, int zCenter, int radius, Random rand, BlockPos.Mutable pos) {
        int x = xCenter + this.rand.nextInt(radius) - this.rand.nextInt(radius);
        int y = yCenter + this.rand.nextInt(radius) - this.rand.nextInt(radius);
        int z = zCenter + this.rand.nextInt(radius) - this.rand.nextInt(radius);
        pos.setPos(x, y, z);
        BlockState blockState = this.getBlockState(pos);

        if (!blockState.isCollisionShapeOpaque(this, pos)) {
            if (this.getBiome(pos) instanceof AbstractBiome) {
                ((AbstractBiome)this.getBiome(pos)).getParticles().ifPresent((particles) -> {
                    if (particles.shouldAddParticles(rand)) {
                        this.addParticle(particles.getParticle(), (double)pos.getX() + rand.nextDouble(), (double)pos.getY() + rand.nextDouble(), (double)pos.getZ() + rand.nextDouble(), 0.0d, 0.0d, 0.0d);
                    }
                });
            }
        }
    }
}