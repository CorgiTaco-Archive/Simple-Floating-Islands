package corgitaco.improvedfloatingislands.mixin.access;

import net.minecraft.block.BlockState;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DimensionSettings.class)
public interface DimensionSettingsAccess {


    @Invoker
    static DimensionSettings invokeRegister(RegistryKey<DimensionSettings> key, DimensionSettings settings) {
        throw new Error("Mixin did not apply!");
    }

    @Invoker
    static DimensionSettings invokeEnd(DimensionStructuresSettings structuresSettings, BlockState stone, BlockState liquid, ResourceLocation location, boolean p_242742_4_, boolean p_242742_5_) {
        throw new Error("Mixin did not apply!");
    }
}
