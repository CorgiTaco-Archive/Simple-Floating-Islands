package corgitaco.improvedfloatingislands.mixin.access;

import net.minecraft.client.gui.screen.BiomeGeneratorTypeScreens;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(BiomeGeneratorTypeScreens.class)
public interface BiomeGeneratorTypeScreenAccess {

    @Accessor
    static List<BiomeGeneratorTypeScreens> getPRESETS() {
        throw new Error("Mixin did not apply");
    }

}
