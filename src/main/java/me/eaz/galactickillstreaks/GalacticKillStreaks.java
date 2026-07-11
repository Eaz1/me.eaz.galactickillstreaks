package me.eaz.galactickillstreaks;

import org.bukkit.plugin.java.JavaPlugin;

public class GalacticKillStreaks extends JavaPlugin {

    private static GalacticKillStreaks instance;

    @Override
    public void onEnable() {
        instance = this;

        // Create config if it doesn't exist
        saveDefaultConfig();

        getLogger().info("=================================");
        getLogger().info("GalacticKillStreaks Enabled!");
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("Made for GalacticPVP");
        getLogger().info("=================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("GalacticKillStreaks Disabled!");
    }

    public static GalacticKillStreaks getInstance() {
        return instance;
    }
}
