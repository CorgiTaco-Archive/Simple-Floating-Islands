package corgitaco.improvedfloatingislands.client;

import corgitaco.improvedfloatingislands.ImprovedFloatingIslands;
import corgitaco.improvedfloatingislands.biomesource.NoOceansBiomeSource;
import corgitaco.improvedfloatingislands.biomesource.NoOceansEndStyleBiomeSource;
import corgitaco.improvedfloatingislands.config.BiomeSizeConfig;
import net.minecraft.client.gui.screen.BiomeGeneratorTypeScreens;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;

public class GenTypeScreen {
    public static final BiomeGeneratorTypeScreens IMPROVED_FLOATING_ISLANDS = new BiomeGeneratorTypeScreens(new TranslationTextComponent("improved_floating_islands")) {
        protected ChunkGenerator generator(Registry<Biome> biomeRegistry, Registry<DimensionSettings> settings, long seed) {
            BiomeSizeConfig.handleConfig(ImprovedFloatingIslands.CONFIG_PATH.resolve("settings.json"));
            return new NoiseChunkGenerator(new NoOceansBiomeSource(seed, ImprovedFloatingIslands.biomeSize, biomeRegistry), seed, () -> settings.getOrThrow(DimensionSettings.FLOATING_ISLANDS));
        }
    };

    public static final BiomeGeneratorTypeScreens IMPROVED_FLOATING_ISLANDS_END = new BiomeGeneratorTypeScreens(new TranslationTextComponent("improved_floating_islands_end")) {
        protected ChunkGenerator generator(Registry<Biome> biomeRegistry, Registry<DimensionSettings> settings, long seed) {
            BiomeSizeConfig.handleConfig(ImprovedFloatingIslands.CONFIG_PATH.resolve("settings.json"));
            return new NoiseChunkGenerator(new NoOceansEndStyleBiomeSource(seed, ImprovedFloatingIslands.biomeSize, biomeRegistry), seed, () -> settings.getOrThrow(NoOceansEndStyleBiomeSource.END_STYLE));
        }
    };
}
