package me.eaz.galactickillstreaks.data;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataManager {

    private final Map<UUID, PlayerData> playerDataMap = new HashMap<>();

    /**
     * Gets a player's data.
     * Creates new data if it doesn't exist.
     */
    public PlayerData getPlayerData(Player player) {
        return playerDataMap.computeIfAbsent(
                player.getUniqueId(),
                uuid -> new PlayerData()
        );
    }

    /**
     * Gets a player's data by UUID.
     */
    public PlayerData getPlayerData(UUID uuid) {
        return playerDataMap.computeIfAbsent(
                uuid,
                id -> new PlayerData()
        );
    }

    /**
     * Returns true if the player has stored data.
     */
    public boolean hasPlayerData(Player player) {
        return playerDataMap.containsKey(player.getUniqueId());
    }

    /**
     * Removes a player's data from memory.
     */
    public void removePlayerData(Player player) {
        playerDataMap.remove(player.getUniqueId());
    }

    /**
     * Clears all loaded player data.
     */
    public void clearData() {
        playerDataMap.clear();
    }

    /**
     * Returns all loaded player data.
     */
    public Map<UUID, PlayerData> getAllPlayerData() {
        return playerDataMap;
    }

    /**
     * Returns the number of loaded players.
     */
    public int getLoadedPlayerCount() {
        return playerDataMap.size();
    }
}
