package corgitaco.simplefloatingislands.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

import java.util.Optional;
import java.util.stream.Stream;

public class FloatingIslandBiomeSource extends BiomeSource {

    public static final Codec<FloatingIslandBiomeSource> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.LONG.fieldOf("seed").forGetter(floatingIslandBiomeSource -> floatingIslandBiomeSource.seed),
                    BiomeSource.CODEC.fieldOf("terrain_source").forGetter(floatingIslandBiomeSource -> floatingIslandBiomeSource.terrainSource),
                    BiomeSource.CODEC.fieldOf("void_source").forGetter(floatingIslandBiomeSource -> floatingIslandBiomeSource.voidSource),
                    BiomeSource.CODEC.optionalFieldOf("center_biome_source").forGetter(floatingIslandBiomeSource -> floatingIslandBiomeSource.centerBiomeSource)
            ).apply(builder, FloatingIslandBiomeSource::new)
    );
    private final long seed;
    private final BiomeSource terrainSource;
    private final BiomeSource voidSource;
    private final Optional<BiomeSource> centerBiomeSource;

    private final BiomeResolver biomeResolver;

    private final SimplexNoise islandNoise;

    public FloatingIslandBiomeSource(long seed, BiomeSource terrainSource, BiomeSource voidSource, Optional<BiomeSource> centerBiomeSource) {
        super(Stream.concat(Stream.concat(terrainSource.possibleBiomes().stream(), voidSource.possibleBiomes().stream()), centerBiomeSource.isPresent() ? centerBiomeSource.orElseThrow().possibleBiomes().stream() : Stream.empty()));
        this.seed = seed;
        this.terrainSource = terrainSource;
        this.voidSource = voidSource;
        this.centerBiomeSource = centerBiomeSource;
        WorldgenRandom worldgenRandom = new WorldgenRandom(new LegacyRandomSource(seed));
        worldgenRandom.consumeCount(17292);
        this.islandNoise = new SimplexNoise(worldgenRandom);

        if (centerBiomeSource.isPresent()) {
            BiomeSource center = centerBiomeSource.get();

            biomeResolver = (x, y, z, sampler) -> {
                int i = x >> 2;
                int j = z >> 2;
                long range = (long) i * (long) i + (long) j * (long) j;
                if (range <= 512L) {
                    return center.getNoiseBiome(x, y, z, sampler);
                } else if (range <= 4096L) {
                    return voidSource.getNoiseBiome(x, y, z, sampler);
                } else {
                    float heightValue = TheEndBiomeSource.getHeightValue(this.islandNoise, i * 2 + 1, j * 2 + 1);
                    if (heightValue >= 0.0F) {
                        return terrainSource.getNoiseBiome(x, y, z, sampler);
                    } else {
                        return heightValue < -20.0F ? voidSource.getNoiseBiome(x, y, z, sampler) : terrainSource.getNoiseBiome(x, y, z, sampler);
                    }
                }
            };
        } else {
            biomeResolver = (x, y, z, sampler) -> {
                int i = x >> 2;
                int j = z >> 2;
                float heightValue = TheEndBiomeSource.getHeightValue(this.islandNoise, i * 2 + 1, j * 2 + 1);
                if (heightValue >= 0.0F) {
                    return terrainSource.getNoiseBiome(x, y, z, sampler);
                } else {
                    return heightValue < -20.0F ? voidSource.getNoiseBiome(x, y, z, sampler) : terrainSource.getNoiseBiome(x, y, z, sampler);
                }
            };
        }
    }

    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    public BiomeSource withSeed(long seed) {
        return new FloatingIslandBiomeSource(seed, this.terrainSource, this.voidSource, this.centerBiomeSource);
    }

    @Override
    public Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.Sampler sampler) {
        return this.biomeResolver.getNoiseBiome(x, y, z, sampler);
    }
}
