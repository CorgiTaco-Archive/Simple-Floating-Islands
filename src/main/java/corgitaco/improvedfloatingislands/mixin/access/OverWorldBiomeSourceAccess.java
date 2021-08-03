package corgitaco.improvedfloatingislands.mixin.access;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(OverworldBiomeProvider.class)
public interface OverWorldBiomeSourceAccess {

    @Accessor
    static List<RegistryKey<Biome>> getPOSSIBLE_BIOMES() {
        throw new Error("Mixin did not apply");
    }
}
