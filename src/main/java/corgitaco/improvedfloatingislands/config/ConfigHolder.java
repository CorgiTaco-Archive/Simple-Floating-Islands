package corgitaco.improvedfloatingislands.config;

public class ConfigHolder {


    private final int biomeSize;
    private final String middleIsland;

    public ConfigHolder(int biomeSize, String middleIsland) {
        this.biomeSize = biomeSize;
        this.middleIsland = middleIsland;
    }

    public int getBiomeSize() {
        return biomeSize;
    }

    public String getMiddleIsland() {
        return middleIsland;
    }
}