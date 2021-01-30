package com.blackgear.bgcore.core;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

//<>

public class RegistryHelper {
    private final String modId;

    private final DeferredRegister<Item> itemDeferredRegister;
    private final DeferredRegister<Block> blockDeferredRegister;
    private final DeferredRegister<TileEntityType<?>> tileEntityTypeDeferredRegister;
    private final DeferredRegister<EntityType<?>> entityTypeDeferredRegister;
    private final DeferredRegister<SoundEvent> soundEventDeferredRegister;
    private final DeferredRegister<Biome> biomeDeferredRegister;
    private final DeferredRegister<Feature<?>> featureDeferredRegister;
    private final DeferredRegister<SurfaceBuilder<?>> surfaceBuilderDeferredRegister;
    private final DeferredRegister<WorldCarver<?>> worldCarverDeferredRegister;
    private final DeferredRegister<ParticleType<?>> particleTypeDeferredRegister;

    private final DeferredRegister<Item> vanillaItemDeferredRegister;
    private final DeferredRegister<Block> vanillaBlockDeferredRegister;

    public RegistryHelper(String modId) {
        this.modId = modId;
        this.itemDeferredRegister = DeferredRegister.create(ForgeRegistries.ITEMS, modId);
        this.blockDeferredRegister = DeferredRegister.create(ForgeRegistries.BLOCKS, modId);
        this.tileEntityTypeDeferredRegister = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, modId);
        this.entityTypeDeferredRegister = DeferredRegister.create(ForgeRegistries.ENTITIES, modId);
        this.soundEventDeferredRegister = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, modId);
        this.biomeDeferredRegister = DeferredRegister.create(ForgeRegistries.BIOMES, modId);
        this.featureDeferredRegister = DeferredRegister.create(ForgeRegistries.FEATURES, modId);
        this.surfaceBuilderDeferredRegister = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, modId);
        this.worldCarverDeferredRegister = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, modId);
        this.particleTypeDeferredRegister = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, modId);

        this.vanillaItemDeferredRegister = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");
        this.vanillaBlockDeferredRegister = DeferredRegister.create(ForgeRegistries.BLOCKS, "minecraft");
    }

    public String getModId() {
        return this.modId;
    }

    public DeferredRegister<Item> getItemDeferredRegister() {
        return this.itemDeferredRegister;
    }

    public DeferredRegister<Block> getBlockDeferredRegister() {
        return this.blockDeferredRegister;
    }

    public DeferredRegister<TileEntityType<?>> getTileEntityTypeDeferredRegister() {
        return this.tileEntityTypeDeferredRegister;
    }

    public DeferredRegister<EntityType<?>> getEntityTypeDeferredRegister() {
        return this.entityTypeDeferredRegister;
    }

    public DeferredRegister<SoundEvent> getSoundEventDeferredRegister() {
        return this.soundEventDeferredRegister;
    }

    public DeferredRegister<Biome> getBiomeDeferredRegister() {
        return this.biomeDeferredRegister;
    }

    public DeferredRegister<Feature<?>> getFeatureDeferredRegister() {
        return this.featureDeferredRegister;
    }

    public DeferredRegister<SurfaceBuilder<?>> getSurfaceBuilderDeferredRegister() {
        return this.surfaceBuilderDeferredRegister;
    }

    public DeferredRegister<WorldCarver<?>> getWorldCarverDeferredRegister() {
        return this.worldCarverDeferredRegister;
    }

    public DeferredRegister<ParticleType<?>> getParticleTypeDeferredRegister() {
        return this.particleTypeDeferredRegister;
    }

    public DeferredRegister<Item> getVanillaItemDeferredRegister() {
        return this.vanillaItemDeferredRegister;
    }

    public DeferredRegister<Block> getVanillaBlockDeferredRegister() {
        return this.vanillaBlockDeferredRegister;
    }

    public ResourceLocation getResourceLocation(String key) {
        return new ResourceLocation(this.modId, key);
    }

    /**
     * register a customized item
     *
     * @param key          the item's name
     * @param itemSupplier the supplied item
     * @return the customized item
     */
    public <I extends Item> RegistryObject<I> registerItem(String key, Supplier<? extends I> itemSupplier) {
        RegistryObject<I> item = this.itemDeferredRegister.register(key, itemSupplier);
        return item;
    }

    /**
     * register a basic customized item
     *
     * @param key   the item's name
     * @param group the item's group
     * @return the customized item
     */
    public RegistryObject<Item> registerBasicItem(String key, ItemGroup group) {
        RegistryObject<Item> item = this.itemDeferredRegister.register(key, () -> new Item(new Item.Properties().group(group)));
        return item;
    }

    //registerInjectedItem

    /**
     * register a customized block with their block item
     *
     * @param key           the block's name
     * @param blockSupplier the supplied block
     * @param group         the block's item group
     * @return the customized block
     */
    public <B extends Block> RegistryObject<B> registerBlock(String key, Supplier<? extends B> blockSupplier, ItemGroup group) {
        RegistryObject<B> block = this.blockDeferredRegister.register(key, blockSupplier);
        this.itemDeferredRegister.register(key, () -> new BlockItem(block.get(), new Item.Properties().group(group)));
        return block;
    }

    /**
     * register a customized block without block item
     *
     * @param key           the block's name
     * @param blockSupplier the supplied block
     * @return the customized block
     */
    public <B extends Block> RegistryObject<B> registerSingleBlock(String key, Supplier<? extends B> blockSupplier) {
        RegistryObject<B> block = this.blockDeferredRegister.register(key, blockSupplier);
        return block;
    }

    //registerInjectedBlock

    /**
     * register a customized tile entity
     *
     * @param key                the tile entity name
     * @param tileEntitySupplier the supplied tile entity
     * @param blockSupplier      the supplied block(s) for the tile entity
     * @return the customized tile entity
     */
    public <T extends TileEntity> RegistryObject<TileEntityType<T>> registerTileEntity(String key, Supplier<? extends T> tileEntitySupplier, Supplier<Block[]> blockSupplier) {
        RegistryObject<TileEntityType<T>> tileEntity = this.tileEntityTypeDeferredRegister.register(key, () -> new TileEntityType<>(tileEntitySupplier, Sets.newHashSet(blockSupplier.get()), null));
        return tileEntity;
    }

    /**
     * register a customized entity
     *
     * @param key           the entity name
     * @param entityBuilder the entity building parameters
     * @return              the customized entity
     */
    public <E extends Entity> RegistryObject<EntityType<E>> registerEntity(String key, EntityType.Builder<E> entityBuilder) {
        RegistryObject<EntityType<E>> entity = this.entityTypeDeferredRegister.register(key, () -> entityBuilder.build(getResourceLocation(key).toString()));
        return entity;
    }

    /**
     * register a customized sound event
     * @param key   the sound event name
     * @return      the customized sound event
     */
    public RegistryObject<SoundEvent> registerSoundEvent(String key) {
        RegistryObject<SoundEvent> soundEvent = this.soundEventDeferredRegister.register(key, () -> new SoundEvent(getResourceLocation(key)));
        return soundEvent;
    }

    /**
     * register a customized biome
     * @param key           the biomes name
     * @param biomeSupplier the supplied biome
     * @return              the customized biome
     */
    public <B extends Biome> RegistryObject<B> registerBiome(String key, Supplier<? extends B> biomeSupplier) {
        RegistryObject<B> biome = this.biomeDeferredRegister.register(key, biomeSupplier);
        return biome;
    }

    /**
     * register a customized feature
     * @param key               the feature's name
     * @param featureSupplier   the supplied feature
     * @return                  the customized feature
     */
    public <F extends Feature<?>> RegistryObject<F> registerFeature(String key, Supplier<? extends F> featureSupplier) {
        RegistryObject<F> feature = this.featureDeferredRegister.register(key, featureSupplier);
        return feature;
    }

    /**
     * register a customized surface builder
     * @param key                       the surface's builder name
     * @param surfaceBuilderSupplier    the supplied surface builder
     * @return                          the customized surface builder
     */
    public <S extends SurfaceBuilder<?>> RegistryObject<S> registerSurfaceBuilder(String key, Supplier<? extends S> surfaceBuilderSupplier) {
        RegistryObject<S> surfaceBuilder = this.surfaceBuilderDeferredRegister.register(key, surfaceBuilderSupplier);
        return surfaceBuilder;
    }

    /**
     * register a customized world carver
     * @param key                   the world's carver name
     * @param worldCarverSupplier   the supplied world carver
     * @return                      the customized world carver
     */
    public <W extends WorldCarver<?>> RegistryObject<W> registerWorldCarver(String key, Supplier<? extends W> worldCarverSupplier) {
        RegistryObject<W> worldCarver = this.worldCarverDeferredRegister.register(key, worldCarverSupplier);
        return worldCarver;
    }

    /**
     * register a customized particle
     * @param key               the particle's name
     * @param particleSupplier  the supplied particle
     * @return                  the customized particle
     */
    public <P extends ParticleType<?>> RegistryObject<P> registerParticle(String key, Supplier<? extends P> particleSupplier) {
        RegistryObject<P> particleType = this.particleTypeDeferredRegister.register(key, particleSupplier);
        return particleType;
    }

    /**
     * register a basic customized particle
     * @param key               the particle's name
     * @param shouldAlwaysShow  toggle if the particle should always be shown
     * @return                  the customized particle
     */
    public RegistryObject<BasicParticleType> registerBasicParticle(String key, boolean shouldAlwaysShow) {
        RegistryObject<BasicParticleType> particleType = this.particleTypeDeferredRegister.register(key, () -> new BasicParticleType(shouldAlwaysShow));
        return particleType;
    }

    /**
     * register a vanilla customized item - recommended for overrides
     * @param key          the vanilla item's name
     * @param itemSupplier the supplied item
     * @return the customized vanilla item
     */
    public <I extends Item> RegistryObject<I> registerVanillaItem(String key, Supplier<? extends I> itemSupplier) {
        RegistryObject<I> item = this.vanillaItemDeferredRegister.register(key, itemSupplier);
        return item;
    }

    /**
     * register a vanilla customized block - recommended for overrides
     * @param key           the vanilla block's name
     * @param blockSupplier the supplied block
     * @return the customized vanilla block
     */
    public <B extends Block> RegistryObject<B> registerVanillaBlock(String key, Supplier<? extends B> blockSupplier, ItemGroup group) {
        RegistryObject<B> block = this.vanillaBlockDeferredRegister.register(key, blockSupplier);
        this.vanillaItemDeferredRegister.register(key, () -> new BlockItem(block.get(), new Item.Properties().group(group)));
        return block;
    }

    /**
     * register a customized vanilla block without block item - recommended for overrides
     * @param key           the vanilla block's name
     * @param blockSupplier the supplied block
     * @return the customized vanilla block
     */
    public <B extends Block> RegistryObject<B> registerVanillaSingleBlock(String key, Supplier<? extends B> blockSupplier) {
        RegistryObject<B> block = this.vanillaBlockDeferredRegister.register(key, blockSupplier);
        return block;
    }

    /**
     * register a vanilla customized block that doesn't stack - recommended for overrides
     * @param key           the vanilla block's name
     * @param blockSupplier the supplied block
     * @return the customized vanilla block
     */
    public <B extends Block> RegistryObject<B> registerVanillaNonStackableBlock(String key, Supplier<? extends B> blockSupplier, ItemGroup group) {
        RegistryObject<B> block = this.vanillaBlockDeferredRegister.register(key, blockSupplier);
        this.vanillaItemDeferredRegister.register(key, () -> new BlockItem(block.get(), new Item.Properties().maxStackSize(1).group(group)));
        return block;
    }
}