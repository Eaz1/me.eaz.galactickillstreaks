package me.eaz.galactickillstreaks;

import me.clip.placeholderapi.PlaceholderAPI;
import me.eaz.galactickillstreaks.commands.GKSCommand;
import me.eaz.galactickillstreaks.commands.GKSTabCompleter;
import me.eaz.galactickillstreaks.commands.KillStreakCommand;
import me.eaz.galactickillstreaks.commands.RecordCommand;
import me.eaz.galactickillstreaks.commands.TopStreaksCommand;
import me.eaz.galactickillstreaks.data.DataManager;
import me.eaz.galactickillstreaks.data.StorageManager;
import me.eaz.galactickillstreaks.listeners.*;
import me.eaz.galactickillstreaks.managers.*;
import me.eaz.galactickillstreaks.placeholders.KillStreakPlaceholderExpansion;
import me.eaz.galactickillstreaks.utils.MessageUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class GalacticKillStreaks extends JavaPlugin {

    private static GalacticKillStreaks instance;

    private DataManager dataManager;
    private StorageManager storageManager;
    private ConfigManager configManager;
    private KillStreakManager killStreakManager;
    private RewardManager rewardManager;
    private AutoBountyManager autoBountyManager;
    private RecordManager recordManager;
    private BroadcastManager broadcastManager;
    private CombatManager combatManager;
    private RewardScheduler rewardScheduler;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        MessageUtil.initialize(this);

        dataManager = new DataManager();
        storageManager = new StorageManager(this);
        configManager = new ConfigManager(this);
        killStreakManager = new KillStreakManager();
        rewardManager = new RewardManager(this);
        autoBountyManager = new AutoBountyManager(this);
        recordManager = new RecordManager(this);
        broadcastManager = new BroadcastManager(this);
        combatManager = new CombatManager();

        rewardScheduler = new RewardScheduler(
                this,
                rewardManager,
                autoBountyManager,
                broadcastManager,
                recordManager
        );

        storageManager.loadPlayerData(dataManager);

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this, killStreakManager), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(this, combatManager), this);
        getServer().getPluginManager().registerEvents(new BountyClaimListener(this), this);

        getCommand("gks").setExecutor(new GKSCommand(this));
        getCommand("gks").setTabCompleter(new GKSTabCompleter());

        getCommand("killstreak").setExecutor(new KillStreakCommand(this));
        getCommand("topstreaks").setExecutor(new TopStreaksCommand(this));
        getCommand("record").setExecutor(new RecordCommand(this));

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new KillStreakPlaceholderExpansion(this).register();
        }

        getLogger().info("GalacticKillStreaks has been enabled!");
    }

    @Override
    public void onDisable() {

        storageManager.savePlayerData(dataManager);

        getLogger().info("GalacticKillStreaks has been disabled!");
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

    public ConfigManager getConfigManager() {
        return configManager;
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

    public RecordManager getRecordManager() {
        return recordManager;
    }

    public BroadcastManager getBroadcastManager() {
        return broadcastManager;
    }

    public CombatManager getCombatManager() {
        return combatManager;
    }

    public RewardScheduler getRewardScheduler() {
        return rewardScheduler;
    }
}
