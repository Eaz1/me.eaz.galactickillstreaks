package me.eaz.galactickillstreaks.managers;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private final GalacticKillStreaks plugin;
    private FileConfiguration config;

    public ConfigManager(GalacticKillStreaks plugin) {
        this.plugin = plugin;
        load();
    }

    /**
     * Loads the config.
     */
    public void load() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    /**
     * Reloads the config.
     */
    public void reload() {
        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    /**
     * Returns the config.
     */
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * Gets an int from the config.
     */
    public int getInt(String path) {
        return config.getInt(path);
    }

    /**
     * Gets a double from the config.
     */
    public double getDouble(String path) {
        return config.getDouble(path);
    }

    /**
     * Gets a boolean from the config.
     */
    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    /**
     * Gets a string from the config.
     */
    public String getString(String path) {
        return config.getString(path);
    }
}
