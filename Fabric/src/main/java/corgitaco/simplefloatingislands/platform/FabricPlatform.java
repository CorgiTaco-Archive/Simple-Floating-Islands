package corgitaco.simplefloatingislands.platform;

import com.google.auto.service.AutoService;
import corgitaco.simplefloatingislands.platform.services.ModPlatform;
import net.fabricmc.loader.api.FabricLoader;

@AutoService(ModPlatform.class)
public class FabricPlatform implements ModPlatform {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
