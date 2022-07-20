package corgitaco.simplefloatingislands.world;

import corgitaco.simplefloatingisland.RegistrationProvider;
import corgitaco.simplefloatingislands.Constants;
import corgitaco.simplefloatingislands.mixin.access.NoiseRouterDataAccess;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.*;

public class FloatingIslandNoiseGeneratorSettings {

//    static final NoiseSettings HUGE_END_NOISE_SETTINGS = NoiseSettings.create(-48, 128, new NoiseSamplingSettings(2.0D, 1.0D, 80.0D, 160.0D), new NoiseSlider(-23.4375D, 64, -46), new NoiseSlider(-0.234375D, 7, 1), 2, 1, TerrainProvider.end());


    static final NoiseSettings FLOATING_ISLAND_SETTINGS = NoiseSettings.create(-48, 256, new NoiseSamplingSettings(2.0D, 1.0D, 80.0D, 160.0D), new NoiseSlider(-23.4375D, 64, -46), new NoiseSlider(-0.234375D, 7, 1), 2, 1, TerrainProvider.floatingIslands());

    public static RegistrationProvider<NoiseGeneratorSettings> REGISTRY = RegistrationProvider.get(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, Constants.MOD_ID);

//    public static final ResourceKey<NoiseGeneratorSettings> END_HUGE_FLOATING_ISLAND_SETTINGS_WITH_CAVES = createSettings("end_huge_floating_island_settings_with_caves", overworld(HUGE_END_NOISE_SETTINGS, NoiseRouterDataAccess.sfi_getEnd(HUGE_END_NOISE_SETTINGS)));
    public static final ResourceKey<NoiseGeneratorSettings> FLOATING_ISLANDS = createSettings("floating_island_settings", overworld(FLOATING_ISLAND_SETTINGS, NoiseRouterDataAccess.sfi_getOverworldWithoutCaves(FLOATING_ISLAND_SETTINGS)));

//    public static final ResourceKey<NoiseGeneratorSettings> END_FLOATING_ISLAND_SETTINGS_WITH_CAVES = createSettings("end_floating_island_settings_with_caves", overworld(NoiseSettingsAccess.sfi_getEND_NOISE_SETTINGS(), NoiseRouterDataAccess.sfi_getEnd(NoiseSettingsAccess.sfi_getEND_NOISE_SETTINGS())));
//    public static final ResourceKey<NoiseGeneratorSettings> FLOATING_ISLAND_SETTINGS_WITH_CAVES = createSettings("floating_island_settings_with_caves", overworld(NoiseSettingsAccess.sfi_getFLOATING_ISLANDS_NOISE_SETTINGS(), NoiseRouterDataAccess.sfi_getOverworldWithoutCaves(NoiseSettingsAccess.sfi_getFLOATING_ISLANDS_NOISE_SETTINGS())));



    private static NoiseGeneratorSettings overworld(NoiseSettings settings, NoiseRouterWithOnlyNoises noiseRouterData) {
        return new NoiseGeneratorSettings(settings, Blocks.STONE.defaultBlockState(), Blocks.AIR.defaultBlockState(), noiseRouterData, SurfaceRuleData.overworldLike(false, false, false), 0, true, false, false, true);
    }

    public static ResourceKey<NoiseGeneratorSettings> createSettings(String id, NoiseGeneratorSettings settings) {
        return REGISTRY.register(id, () -> settings).getResourceKey();
    }

    public static void init() {
    }

}
