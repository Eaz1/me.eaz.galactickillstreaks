package me.eaz.galactickillstreaks;

import me.eaz.galactickillstreaks.data.DataManager;
import me.eaz.galactickillstreaks.data.StorageManager;
import me.eaz.galactickillstreaks.listeners.PlayerDeathListener;
import me.eaz.galactickillstreaks.managers.AutoBountyManager;
import me.eaz.galactickillstreaks.managers.KillStreakManager;
import me.eaz.galactickillstreaks.managers.RewardManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GalacticKillStreaks extends JavaPlugin {

    private static GalacticKillStreaks instance;

    private DataManager dataManager;
    private StorageManager storageManager;
    private KillStreakManager killStreakManager;
    private RewardManager rewardManager;
    private AutoBountyManager autoBountyManager;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        // Managers
        dataManager = new DataManager();
        storageManager = new StorageManager(this);
        killStreakManager = new KillStreakManager();
        rewardManager = new RewardManager(this);
        autoBountyManager = new AutoBountyManager(this);

        // Load saved player data
        storageManager.loadPlayerData(dataManager);

        // Register listeners
        getServer().getPluginManager().registerEvents(
                new PlayerDeathListener(this, killStreakManager),
                this
        );

        getLogger().info("--------------------------------");
        getLogger().info("GalacticKillStreaks Enabled!");
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("--------------------------------");
    }

    @Override
    public void onDisable() {

        storageManager.savePlayerData(dataManager);

        getLogger().info("GalacticKillStreaks Disabled!");
    }

    public static GalacticKillStreaks getInstance() {
        return instance;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public StorageManager getStorageManager() {
        return storageManager;
    }

    public KillStreakManager getKillStreakManager() {
        return killStreakManager;
    }

    public RewardManager getRewardManager() {
        return rewardManager;
    }

    public AutoBountyManager getAutoBountyManager() {
        return autoBountyManager;
    }
}
