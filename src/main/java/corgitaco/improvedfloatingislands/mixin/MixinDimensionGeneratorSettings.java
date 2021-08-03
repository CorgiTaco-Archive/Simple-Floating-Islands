package corgitaco.improvedfloatingislands.mixin;

import corgitaco.improvedfloatingislands.ImprovedFloatingIslands;
import corgitaco.improvedfloatingislands.biomesource.NoOceansBiomeSource;
import corgitaco.improvedfloatingislands.biomesource.NoOceansEndStyleBiomeSource;
import corgitaco.improvedfloatingislands.config.BiomeSizeConfig;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Properties;

@Mixin(DimensionGeneratorSettings.class)
public abstract class MixinDimensionGeneratorSettings {


    @Shadow
    public static SimpleRegistry<Dimension> withOverworld(Registry<DimensionType> p_242749_0_, SimpleRegistry<Dimension> p_242749_1_, ChunkGenerator p_242749_2_) {
        throw new Error("Mixin did not apply!");
    }

    @Inject(method = "create", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/DimensionType;defaultDimensions(Lnet/minecraft/util/registry/Registry;Lnet/minecraft/util/registry/Registry;Lnet/minecraft/util/registry/Registry;J)Lnet/minecraft/util/registry/SimpleRegistry;"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private static void addWorldTypes(DynamicRegistries registries, Properties p_242753_1_, CallbackInfoReturnable<DimensionGeneratorSettings> cir, String s, String s1, String s2, boolean flag, String s3, String s4, long i, Registry<DimensionType> registry2, Registry<Biome> registry, Registry<DimensionSettings> registry1, SimpleRegistry<Dimension> simpleregistry) {
        if (s4.equals("improvedIslands")) {
            BiomeSizeConfig.handleConfig(ImprovedFloatingIslands.CONFIG_PATH.resolve("settings.json"));
            cir.setReturnValue(new DimensionGeneratorSettings(i, flag, false, withOverworld(registry2, simpleregistry, new NoiseChunkGenerator(new NoOceansBiomeSource(i, ImprovedFloatingIslands.biomeSize, registry), i, () -> registry1.get(DimensionSettings.FLOATING_ISLANDS)))));
        } else if (s4.equals("improvedIslandsEnd")) {
            BiomeSizeConfig.handleConfig(ImprovedFloatingIslands.CONFIG_PATH.resolve("settings.json"));
            cir.setReturnValue(new DimensionGeneratorSettings(i, flag, false, withOverworld(registry2, simpleregistry, new NoiseChunkGenerator(new NoOceansEndStyleBiomeSource(i, ImprovedFloatingIslands.biomeSize, registry), i, () -> registry1.get(NoOceansEndStyleBiomeSource.END_STYLE)))));
        }
    }
}
