package corgitaco.simplefloatingislands.client;

import corgitaco.simplefloatingislands.world.FloatingIslandBiomeSource;
import corgitaco.simplefloatingislands.world.FloatingIslandNoiseGeneratorSettings;
import net.minecraft.client.gui.screens.worldselection.WorldPreset;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.Optional;

public class FloatingIslandWorldPresets {
    public static final WorldPreset HUGE_END = new WorldPreset("huge_end") {

        @Override
        protected ChunkGenerator generator(RegistryAccess registryAccess, long seed) {
            Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);
            Registry<NormalNoise.NoiseParameters> noiseParametersRegistry = registryAccess.registryOrThrow(Registry.NOISE_REGISTRY);
            FloatingIslandBiomeSource floatingIslandBiomeSource =
                    new FloatingIslandBiomeSource(
                    seed,
                    MultiNoiseBiomeSource.Preset.OVERWORLD.biomeSource(biomeRegistry, true),
                            new FixedBiomeSource(biomeRegistry.getHolderOrThrow(Biomes.THE_VOID)),
                            Optional.of(new FixedBiomeSource(biomeRegistry.getHolderOrThrow(Biomes.JUNGLE)))
                    );

            Registry<NoiseGeneratorSettings> noiseGeneratorSettings = registryAccess.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);
            return new NoiseBasedChunkGenerator(
                    registryAccess.registryOrThrow(Registry.STRUCTURE_SET_REGISTRY),
                    noiseParametersRegistry,
                    floatingIslandBiomeSource,
                    seed,
                    noiseGeneratorSettings.getHolderOrThrow(FloatingIslandNoiseGeneratorSettings.END_HUGE_FLOATING_ISLAND_SETTINGS_WITH_CAVES)
            );
        }
    };


    public static final WorldPreset HUGE_FLOATING_ISLAND = new WorldPreset("huge_floating_island") {

        @Override
        protected ChunkGenerator generator(RegistryAccess registryAccess, long seed) {
            Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);
            Registry<NormalNoise.NoiseParameters> noiseParametersRegistry = registryAccess.registryOrThrow(Registry.NOISE_REGISTRY);
            FloatingIslandBiomeSource floatingIslandBiomeSource =
                    new FloatingIslandBiomeSource(
                            seed,
                            MultiNoiseBiomeSource.Preset.OVERWORLD.biomeSource(biomeRegistry, true),
                            new FixedBiomeSource(biomeRegistry.getHolderOrThrow(Biomes.THE_VOID)),
                            Optional.of(new FixedBiomeSource(biomeRegistry.getHolderOrThrow(Biomes.JUNGLE)))
                    );

            Registry<NoiseGeneratorSettings> noiseGeneratorSettings = registryAccess.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);
            return new NoiseBasedChunkGenerator(
                    registryAccess.registryOrThrow(Registry.STRUCTURE_SET_REGISTRY),
                    noiseParametersRegistry,
                    floatingIslandBiomeSource,
                    seed,
                    noiseGeneratorSettings.getHolderOrThrow(FloatingIslandNoiseGeneratorSettings.HUGE_FLOATING_ISLAND_SETTINGS_WITH_CAVES)
            );
        }
    };
    public static final WorldPreset END = new WorldPreset("end") {

        @Override
        protected ChunkGenerator generator(RegistryAccess registryAccess, long seed) {
            Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);
            Registry<NormalNoise.NoiseParameters> noiseParametersRegistry = registryAccess.registryOrThrow(Registry.NOISE_REGISTRY);
            FloatingIslandBiomeSource floatingIslandBiomeSource =
                    new FloatingIslandBiomeSource(
                            seed,
                            MultiNoiseBiomeSource.Preset.OVERWORLD.biomeSource(biomeRegistry, true),
                            new FixedBiomeSource(biomeRegistry.getHolderOrThrow(Biomes.THE_VOID)),
                            Optional.of(new FixedBiomeSource(biomeRegistry.getHolderOrThrow(Biomes.JUNGLE)))
                    );

            Registry<NoiseGeneratorSettings> noiseGeneratorSettings = registryAccess.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);
            return new NoiseBasedChunkGenerator(
                    registryAccess.registryOrThrow(Registry.STRUCTURE_SET_REGISTRY),
                    noiseParametersRegistry,
                    floatingIslandBiomeSource,
                    seed,
                    noiseGeneratorSettings.getHolderOrThrow(FloatingIslandNoiseGeneratorSettings.END_FLOATING_ISLAND_SETTINGS_WITH_CAVES)
            );
        }
    };


    public static final WorldPreset FLOATING_ISLAND = new WorldPreset("floating_island") {

        @Override
        protected ChunkGenerator generator(RegistryAccess registryAccess, long seed) {
            Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);
            Registry<NormalNoise.NoiseParameters> noiseParametersRegistry = registryAccess.registryOrThrow(Registry.NOISE_REGISTRY);
            FloatingIslandBiomeSource floatingIslandBiomeSource =
                    new FloatingIslandBiomeSource(
                            seed,
                            MultiNoiseBiomeSource.Preset.OVERWORLD.biomeSource(biomeRegistry, true),
                            new FixedBiomeSource(biomeRegistry.getHolderOrThrow(Biomes.THE_VOID)),
                            Optional.of(new FixedBiomeSource(biomeRegistry.getHolderOrThrow(Biomes.JUNGLE)))
                    );

            Registry<NoiseGeneratorSettings> noiseGeneratorSettings = registryAccess.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);
            return new NoiseBasedChunkGenerator(
                    registryAccess.registryOrThrow(Registry.STRUCTURE_SET_REGISTRY),
                    noiseParametersRegistry,
                    floatingIslandBiomeSource,
                    seed,
                    noiseGeneratorSettings.getHolderOrThrow(FloatingIslandNoiseGeneratorSettings.FLOATING_ISLAND_SETTINGS_WITH_CAVES)
            );
        }
    };}
