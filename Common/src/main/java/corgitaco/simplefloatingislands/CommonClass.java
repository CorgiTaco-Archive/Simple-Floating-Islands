package corgitaco.simplefloatingislands;

import corgitaco.simplefloatingislands.world.FloatingIslandBiomeSource;
import corgitaco.simplefloatingislands.world.FloatingIslandNoiseGeneratorSettings;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.Map;

public class CommonClass {

    public static final Map<ResourceKey<Biome>, ResourceKey<Biome>> BIOME_REPLACEMENTS = Util.make(new Object2ObjectOpenHashMap<>(), map -> {
        map.put(Biomes.DEEP_FROZEN_OCEAN, Biomes.SNOWY_PLAINS);
        map.put(Biomes.FROZEN_OCEAN, Biomes.SNOWY_TAIGA);
        map.put(Biomes.WARM_OCEAN, Biomes.DESERT);
        map.put(Biomes.DEEP_LUKEWARM_OCEAN, Biomes.FOREST);
        map.put(Biomes.LUKEWARM_OCEAN, Biomes.PLAINS);
        map.put(Biomes.COLD_OCEAN, Biomes.TAIGA);
        map.put(Biomes.DEEP_COLD_OCEAN, Biomes.DARK_FOREST);
        map.put(Biomes.OCEAN, Biomes.PLAINS);
        map.put(Biomes.RIVER, Biomes.BIRCH_FOREST);
        map.put(Biomes.FROZEN_RIVER, Biomes.ICE_SPIKES);
    });

    public static void init() {
        FloatingIslandNoiseGeneratorSettings.init();
        FloatingIslandBiomeSource.init();
    }
}