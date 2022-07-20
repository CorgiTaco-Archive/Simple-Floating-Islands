package corgitaco.simplefloatingislands;

import corgitaco.simplefloatingislands.client.SFIClient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class ExampleModForge {

    public ExampleModForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        CommonClass.init();
    }


    private void commonSetup(FMLCommonSetupEvent event) {
    }

    private void clientSetup(FMLClientSetupEvent event) {
        SFIClient.init();
    }
}