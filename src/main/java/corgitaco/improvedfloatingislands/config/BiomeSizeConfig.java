package corgitaco.improvedfloatingislands.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import corgitaco.improvedfloatingislands.ImprovedFloatingIslands;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class BiomeSizeConfig {

    public static void handleConfig(Path path) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.disableHtmlEscaping();
        Gson gson = gsonBuilder.create();

        final File CONFIG_FILE = new File(String.valueOf(path));

        if (!CONFIG_FILE.exists()) {
            createConfig(path);
        }
        try (Reader reader = new FileReader(path.toString())) {
            ConfigHolder configHolder = gson.fromJson(reader, ConfigHolder.class);
            if (configHolder != null) {
                ImprovedFloatingIslands.biomeSize = configHolder.getBiomeSize();
                ImprovedFloatingIslands.middleIsland = configHolder.getMiddleIsland();
            }
            else {
                ImprovedFloatingIslands.LOGGER.error("Config reading Failed");
            }

        } catch (IOException e) {
            ImprovedFloatingIslands.LOGGER.error("Config reading Failed");
        }
    }

    private static void createConfig(Path path) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.disableHtmlEscaping();
        Gson gson = gsonBuilder.create();

        String jsonString = gson.toJson(new ConfigHolder(ImprovedFloatingIslands.biomeSize, ImprovedFloatingIslands.middleIsland));

        try {
            if (!path.getParent().toFile().exists()) {
                Files.createDirectories(path.getParent());
            }
            Files.write(path, jsonString.getBytes());
        } catch (IOException e) {
            ImprovedFloatingIslands.LOGGER.error("Config creation Failed");
        }
    }
}
