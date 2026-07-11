package me.eaz.galactickillstreaks.data;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class StorageManager {

    private final GalacticKillStreaks plugin;

    private File dataFile;
    private FileConfiguration dataConfig;

    public StorageManager(GalacticKillStreaks plugin) {
        this.plugin = plugin;
        setup();
    }

    private void setup() {

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        dataFile = new File(plugin.getDataFolder(), "playerdata.yml");

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public void savePlayerData(DataManager dataManager) {

        for (Map.Entry<UUID, PlayerData> entry : dataManager.getAllPlayerData().entrySet()) {

            String path = "players." + entry.getKey();

            PlayerData data = entry.getValue();

            dataConfig.set(path + ".current", data.getCurrentKillStreak());
            dataConfig.set(path + ".highest", data.getHighestKillStreak());
        }

        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPlayerData(DataManager dataManager) {

        if (!dataConfig.contains("players")) {
            return;
        }

        for (String uuidString : dataConfig.getConfigurationSection("players").getKeys(false)) {

            UUID uuid = UUID.fromString(uuidString);

            PlayerData data = dataManager.getPlayerData(uuid);

            data.setCurrentKillStreak(
                    dataConfig.getInt("players." + uuidString + ".current")
            );

            data.setHighestKillStreak(
                    dataConfig.getInt("players." + uuidString + ".highest")
            );
        }
    }

    public void saveGlobalRecord(String player, int streak) {

        dataConfig.set("global.player", player);
        dataConfig.set("global.streak", streak);

        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getGlobalRecordHolder() {
        return dataConfig.getString("global.player", "None");
    }

    public int getGlobalRecord() {
        return dataConfig.getInt("global.streak", 0);
    }

    public void save() {
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
