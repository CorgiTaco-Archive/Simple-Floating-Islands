package corgitaco.improvedfloatingislands.biomesource;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.layer.*;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

import java.util.function.LongFunction;

import static net.minecraft.world.gen.layer.LayerUtil.zoom;

public class NoOceanLayers {

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> getDefaultLayer(boolean old, int biomeSize, LongFunction<C> contextProvider) {
        IAreaFactory<T> iareafactory = LandLayer.INSTANCE.run(contextProvider.apply(1L));
        iareafactory = ZoomLayer.FUZZY.run(contextProvider.apply(2000L), iareafactory);
        iareafactory = AddIslandLayer.INSTANCE.run(contextProvider.apply(1L), iareafactory);
        iareafactory = ZoomLayer.NORMAL.run(contextProvider.apply(2001L), iareafactory);
        iareafactory = AddIslandLayer.INSTANCE.run(contextProvider.apply(2L), iareafactory);
        iareafactory = AddIslandLayer.INSTANCE.run(contextProvider.apply(50L), iareafactory);
        iareafactory = AddIslandLayer.INSTANCE.run(contextProvider.apply(70L), iareafactory);
        iareafactory = AddSnowLayer.INSTANCE.run(contextProvider.apply(2L), iareafactory);
        iareafactory = AddIslandLayer.INSTANCE.run(contextProvider.apply(3L), iareafactory);
        iareafactory = EdgeLayer.CoolWarm.INSTANCE.run(contextProvider.apply(2L), iareafactory);
        iareafactory = EdgeLayer.HeatIce.INSTANCE.run(contextProvider.apply(2L), iareafactory);
        iareafactory = EdgeLayer.Special.INSTANCE.run(contextProvider.apply(3L), iareafactory);
        iareafactory = ZoomLayer.NORMAL.run(contextProvider.apply(2002L), iareafactory);
        iareafactory = ZoomLayer.NORMAL.run(contextProvider.apply(2003L), iareafactory);
        iareafactory = AddIslandLayer.INSTANCE.run(contextProvider.apply(4L), iareafactory);
        iareafactory = zoom(1000L, ZoomLayer.NORMAL, iareafactory, 0, contextProvider);
        IAreaFactory<T> lvt_7_1_ = (new BiomeLayer(old)).run(contextProvider.apply(200L), iareafactory);
        lvt_7_1_ = AddBambooForestLayer.INSTANCE.run(contextProvider.apply(1001L), lvt_7_1_);
        lvt_7_1_ = zoom(1000L, ZoomLayer.NORMAL, lvt_7_1_, 2, contextProvider);
        lvt_7_1_ = EdgeBiomeLayer.INSTANCE.run(contextProvider.apply(1000L), lvt_7_1_);
        IAreaFactory<T> lvt_8_1_ = zoom(1000L, ZoomLayer.NORMAL, lvt_7_1_, 2, contextProvider);
        lvt_7_1_ = HillsLayer.INSTANCE.run(contextProvider.apply(1000L), lvt_7_1_, lvt_8_1_);
        lvt_7_1_ = RareBiomeLayer.INSTANCE.run(contextProvider.apply(1001L), lvt_7_1_);

        for(int i = 0; i < biomeSize; ++i) {
            lvt_7_1_ = ZoomLayer.NORMAL.run(contextProvider.apply((long)(1000 + i)), lvt_7_1_);
            if (i == 0) {
                lvt_7_1_ = AddIslandLayer.INSTANCE.run(contextProvider.apply(3L), lvt_7_1_);
            }
        }

        return SmoothLayer.INSTANCE.run(contextProvider.apply(1000L), lvt_7_1_);
    }

    public static Layer getNoOceansLayers(long seed, boolean old, int biomeSize) {
        return new Layer(getDefaultLayer(old, biomeSize, (context) -> new LazyAreaLayerContext(25, seed, context)));
    }

    public enum LandLayer implements IAreaTransformer0 {
        INSTANCE;

        public int applyPixel(INoiseRandom rand, int x, int y) {
            return 1;
        }
    }
}


