package corgitaco.simplefloatingislands.mixin.access;

import net.minecraft.world.level.levelgen.NoiseSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NoiseSettings.class)
public interface NoiseSettingsAccess {

    @Accessor("END_NOISE_SETTINGS")
    static NoiseSettings sfi_getEND_NOISE_SETTINGS() {
        throw new Error("Mixin did not apply!");
    }

    @Accessor("FLOATING_ISLANDS_NOISE_SETTINGS")
    static NoiseSettings sfi_getFLOATING_ISLANDS_NOISE_SETTINGS() {
        throw new Error("Mixin did not apply!");
    }
}
