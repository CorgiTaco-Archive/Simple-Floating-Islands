package corgitaco.simplefloatingislands.client;

import corgitaco.simplefloatingislands.mixin.access.WorldPresetAccess;

import java.util.Arrays;

public class SFIClient {


    public static void init() {
        WorldPresetAccess.sfi_getPRESETS().addAll(Arrays.asList(FloatingIslandWorldPresets.HUGE_END, FloatingIslandWorldPresets.HUGE_FLOATING_ISLAND, FloatingIslandWorldPresets.END, FloatingIslandWorldPresets.FLOATING_ISLAND));
    }
}
