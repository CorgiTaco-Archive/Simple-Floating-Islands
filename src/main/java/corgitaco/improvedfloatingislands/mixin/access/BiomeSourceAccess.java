package corgitaco.improvedfloatingislands.mixin.access;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(BiomeProvider.class)
public interface BiomeSourceAccess {


    @Accessor List<Biome> getPossibleBiomes();
}
