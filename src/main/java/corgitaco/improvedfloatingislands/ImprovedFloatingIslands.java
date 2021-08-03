package corgitaco.improvedfloatingislands;

import corgitaco.improvedfloatingislands.biomesource.NoOceansBiomeSource;
import corgitaco.improvedfloatingislands.biomesource.NoOceansEndStyleBiomeSource;
import corgitaco.improvedfloatingislands.client.GenTypeScreen;
import corgitaco.improvedfloatingislands.mixin.access.BiomeGeneratorTypeScreenAccess;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

@Mod(ImprovedFloatingIslands.MOD_ID)
public class ImprovedFloatingIslands {
    public static final String MOD_ID = "improvedfloatingislands";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve(MOD_ID);
    public static int biomeSize = 4;
    public static String middleIsland = "";

    public ImprovedFloatingIslands() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(MOD_ID, "improved_floating_islands"), NoOceansBiomeSource.CODEC);
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(MOD_ID, "improved_floating_islands_end"), NoOceansEndStyleBiomeSource.CODEC);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        BiomeGeneratorTypeScreenAccess.getPRESETS().add(GenTypeScreen.IMPROVED_FLOATING_ISLANDS);
        BiomeGeneratorTypeScreenAccess.getPRESETS().add(GenTypeScreen.IMPROVED_FLOATING_ISLANDS_END);
    }
}
