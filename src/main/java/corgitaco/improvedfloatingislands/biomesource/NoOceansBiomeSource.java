package corgitaco.improvedfloatingislands.biomesource;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import corgitaco.improvedfloatingislands.mixin.access.OverWorldBiomeSourceAccess;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.layer.Layer;
import net.minecraftforge.common.BiomeManager;

import java.util.stream.Stream;

public class NoOceansBiomeSource extends BiomeProvider {

    public static final Codec<NoOceansBiomeSource> CODEC = RecordCodecBuilder.create((p_235302_0_) -> {
        return p_235302_0_.group(Codec.LONG.fieldOf("seed").stable().forGetter((p_235304_0_) -> {
            return p_235304_0_.seed;
        }), Codec.INT.fieldOf("biome_size").orElse(4).stable().forGetter((p_235301_0_) -> {
            return p_235301_0_.biomeSize;
        }), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((p_242637_0_) -> {
            return p_242637_0_.biomeRegistry;
        })).apply(p_235302_0_, p_235302_0_.stable(NoOceansBiomeSource::new));
    });

    private final int biomeSize;
    private final Layer noOceansLayer;
    private final long seed;
    private final Registry<Biome> biomeRegistry;

    public NoOceansBiomeSource(long seed, int biomeSize, Registry<Biome> biomeRegistry) {
        super(Stream.concat(OverWorldBiomeSourceAccess.getPOSSIBLE_BIOMES().stream(), BiomeManager.getAdditionalOverworldBiomes().stream()).map(biomeRegistry::getOrThrow).filter(biome -> biome.getBiomeCategory() != Biome.Category.OCEAN && biome.getBiomeCategory() != Biome.Category.RIVER).map(biome -> () -> biome));
        this.biomeSize = biomeSize;
        this.noOceansLayer = NoOceanLayers.getNoOceansLayers(seed, false, biomeSize);
        this.seed = seed;
        this.biomeRegistry = biomeRegistry;
    }

    @Override
    protected Codec<? extends BiomeProvider> codec() {
        return CODEC;
    }

    @Override
    public BiomeProvider withSeed(long seed) {
        return new NoOceansBiomeSource(seed, this.biomeSize, this.biomeRegistry);
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return this.noOceansLayer.get(this.biomeRegistry, x, z);
    }
}
