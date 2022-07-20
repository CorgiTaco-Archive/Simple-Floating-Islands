package corgitaco.simplefloatingislands.mixin;

import corgitaco.simplefloatingislands.world.FloatingIslandBiomeSource;
import corgitaco.simplefloatingislands.world.FloatingIslandNoiseGeneratorSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.dedicated.DedicatedServerProperties;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

import static corgitaco.simplefloatingislands.CommonClass.BIOME_REPLACEMENTS;

@Mixin(WorldGenSettings.class)
public class MixinWorldGenSettings {


    @Shadow
    @Final
    private long seed;

    @Inject(method = "create", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/dedicated/DedicatedServerProperties$WorldGenProperties;levelType()Ljava/lang/String;"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private static void injectFloatingIslands(RegistryAccess registryAccess, DedicatedServerProperties.WorldGenProperties properties, CallbackInfoReturnable<WorldGenSettings> cir, long seed, Registry<DimensionType> registry, Registry<Biome> registry2, Registry<StructureSet> registry3,
                                              Registry<LevelStem> registry4) {
        String levelType = properties.levelType();
        if (levelType.equalsIgnoreCase("floating_island") || levelType.equalsIgnoreCase("floating_islands") || levelType.equalsIgnoreCase("floatingisland") || levelType.equalsIgnoreCase("floatingislands")) {
            Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);
            Registry<NormalNoise.NoiseParameters> noiseParametersRegistry = registryAccess.registryOrThrow(Registry.NOISE_REGISTRY);
            FloatingIslandBiomeSource floatingIslandBiomeSource =
                    new FloatingIslandBiomeSource(
                            seed,
                            biomeRegistry,
                            MultiNoiseBiomeSource.Preset.OVERWORLD.biomeSource(biomeRegistry, true),
                            BIOME_REPLACEMENTS,
                            new FixedBiomeSource(biomeRegistry.getHolderOrThrow(Biomes.THE_VOID)),
                            Optional.empty()
                    );

            Registry<NoiseGeneratorSettings> noiseGeneratorSettings = registryAccess.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);


            cir.setReturnValue(new WorldGenSettings(seed, properties.generateStructures(), false, WorldGenSettings.withOverworld(registry, registry4, new NoiseBasedChunkGenerator(
                    registryAccess.registryOrThrow(Registry.STRUCTURE_SET_REGISTRY),
                    noiseParametersRegistry,
                    floatingIslandBiomeSource,
                    seed,
                    noiseGeneratorSettings.getHolderOrThrow(FloatingIslandNoiseGeneratorSettings.FLOATING_ISLANDS)
            ))));
        }
    }
}
