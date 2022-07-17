package corgitaco.simplefloatingislands.client;

import net.fabricmc.api.ClientModInitializer;

public class SFIFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SFIClient.init();
    }
}
