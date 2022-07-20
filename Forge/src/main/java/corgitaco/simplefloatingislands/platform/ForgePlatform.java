package corgitaco.simplefloatingislands.platform;

import com.google.auto.service.AutoService;
import corgitaco.simplefloatingislands.platform.services.ModPlatform;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

@AutoService(ModPlatform.class)
public class ForgePlatform implements ModPlatform {

    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }
}
