package corgitaco.improvedfloatingislands.biomesource;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import corgitaco.improvedfloatingislands.ImprovedFloatingIslands;
import corgitaco.improvedfloatingislands.mixin.access.BiomeSourceAccess;
import corgitaco.improvedfloatingislands.mixin.access.DimensionSettingsAccess;
import corgitaco.improvedfloatingislands.mixin.access.OverWorldBiomeSourceAccess;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.EndBiomeProvider;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.SimplexNoiseGenerator;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraftforge.common.BiomeManager;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class NoOceansEndStyleBiomeSource extends BiomeProvider {

    public static final RegistryKey<DimensionSettings> END_STYLE = RegistryKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, new ResourceLocation(ImprovedFloatingIslands.MOD_ID, "floating_island_end"));

    public static final Codec<NoOceansEndStyleBiomeSource> CODEC = RecordCodecBuilder.create((p_235302_0_) -> {
        return p_235302_0_.group(Codec.LONG.fieldOf("seed").stable().forGetter((p_235304_0_) -> {
            return p_235304_0_.seed;
        }), Codec.INT.fieldOf("biome_size").orElse(4).stable().forGetter((p_235301_0_) -> {
            return p_235301_0_.biomeSize;
        }), ResourceLocation.CODEC.optionalFieldOf("centerBiome").stable().forGetter((p_235301_0_) -> {
            return p_235301_0_.centerBiomeLocation;
        }), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((p_242637_0_) -> {
            return p_242637_0_.biomeRegistry;
        })).apply(p_235302_0_, p_235302_0_.stable(NoOceansEndStyleBiomeSource::new));
    });

    static {
        DimensionSettingsAccess.invokeRegister(END_STYLE, DimensionSettingsAccess.invokeEnd(new DimensionStructuresSettings(true), Blocks.STONE.defaultBlockState(), Blocks.AIR.defaultBlockState(), END_STYLE.location(), true, true));
    }

    public static Optional<ResourceLocation> getCenter(String parsed) {
        ResourceLocation resourceLocation = ResourceLocation.tryParse(parsed);
        if (resourceLocation != null) {
            return Optional.of(resourceLocation);
        } else {
            ImprovedFloatingIslands.LOGGER.error("\"" + parsed + "\" is not a valid resource location");
            return Optional.empty();
        }
    }


    private final int biomeSize;
    private final Layer noOceansLayer;
    private final long seed;
    private final Optional<ResourceLocation> centerBiomeLocation;
    private final Biome centerBiome;
    private final Registry<Biome> biomeRegistry;
    private final SimplexNoiseGenerator islandNoise;
    private final Biome voidBiome;

    public NoOceansEndStyleBiomeSource(long seed, int biomeSize, Registry<Biome> biomeRegistry) {
        this(seed, biomeSize, getCenter(ImprovedFloatingIslands.middleIsland), biomeRegistry);
    }

    public NoOceansEndStyleBiomeSource(long seed, int biomeSize, Optional<ResourceLocation> centerBiomeLocation, Registry<Biome> biomeRegistry) {
        super(Stream.concat(OverWorldBiomeSourceAccess.getPOSSIBLE_BIOMES().stream(), BiomeManager.getAdditionalOverworldBiomes().stream()).map(biomeRegistry::getOrThrow).filter(biome -> biome.getBiomeCategory() != Biome.Category.OCEAN && biome.getBiomeCategory() != Biome.Category.RIVER).map(biome -> () -> biome));
        this.biomeSize = biomeSize;
        this.noOceansLayer = NoOceanLayers.getNoOceansLayers(seed, false, biomeSize);
        this.seed = seed;
        this.biomeRegistry = biomeRegistry;
        this.centerBiomeLocation = centerBiomeLocation;
        Biome randBiome = ((BiomeSourceAccess) this).getPossibleBiomes().get(new Random(seed).nextInt(((BiomeSourceAccess) this).getPossibleBiomes().size()));
        if (!centerBiomeLocation.isPresent()) {
            this.centerBiome = randBiome;
        } else {
            this.centerBiome = biomeRegistry.getOptional(centerBiomeLocation.get()).orElse(randBiome);
        }

        SharedSeedRandom sharedseedrandom = new SharedSeedRandom(seed);
        sharedseedrandom.consumeCount(17292);
        this.islandNoise = new SimplexNoiseGenerator(sharedseedrandom);
        this.voidBiome = biomeRegistry.get(Biomes.THE_VOID);
    }

    @Override
    protected Codec<? extends BiomeProvider> codec() {
        return CODEC;
    }

    @Override
    public BiomeProvider withSeed(long seed) {
        return new NoOceansEndStyleBiomeSource(seed, this.biomeSize, this.centerBiomeLocation, this.biomeRegistry);
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        int i = x >> 2;
        int j = z >> 2;
        long range = (long) i * (long) i + (long) j * (long) j;
        if (range <= 512L) {
            return this.centerBiome;
        } else if (range <= 4096L) {
            return this.voidBiome;
        } else {
            float f = EndBiomeProvider.getHeightValue(this.islandNoise, i * 2 + 1, j * 2 + 1);
            if (f >= 0.0F) {
                return this.noOceansLayer.get(biomeRegistry, x, z);
            } else {
                return f < -20.0F ? this.voidBiome : this.noOceansLayer.get(biomeRegistry, x, z);
            }
        }
    }
}
