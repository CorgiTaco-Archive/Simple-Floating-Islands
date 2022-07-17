package corgitaco.simplefloatingislands.mixin.access;

import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.NoiseRouterWithOnlyNoises;
import net.minecraft.world.level.levelgen.NoiseSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(NoiseRouterData.class)
public interface NoiseRouterDataAccess {


    @Invoker("end")
    static NoiseRouterWithOnlyNoises sfi_getEnd(NoiseSettings $$0) {
        throw new Error("Mixin did not apply!");
    }

    @Invoker("overworldWithoutCaves")
    static NoiseRouterWithOnlyNoises sfi_getOverworldWithoutCaves(NoiseSettings $$0) {
        throw new Error("Mixin did not apply!");
    }

}
