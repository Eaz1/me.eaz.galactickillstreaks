package me.eaz.galactickillstreaks.managers;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KillStreakManager {

    // Current killstreaks
    private final Map<UUID, Integer> currentStreaks = new HashMap<>();

    // Highest killstreaks
    private final Map<UUID, Integer> highestStreaks = new HashMap<>();

    // Global record
    private int globalRecord = 0;
    private String globalRecordHolder = "None";

    /**
     * Gets a player's current killstreak.
     */
    public int getCurrentStreak(Player player) {
        return currentStreaks.getOrDefault(player.getUniqueId(), 0);
    }

    /**
     * Gets a player's highest killstreak.
     */
    public int getHighestStreak(Player player) {
        return highestStreaks.getOrDefault(player.getUniqueId(), 0);
    }

    /**
     * Adds one kill to the player's streak.
     */
    public int addKill(Player player) {

        UUID uuid = player.getUniqueId();

        int streak = currentStreaks.getOrDefault(uuid, 0) + 1;

        currentStreaks.put(uuid, streak);

        // Personal record
        if (streak > highestStreaks.getOrDefault(uuid, 0)) {
            highestStreaks.put(uuid, streak);
        }

        // Global record
        if (streak > globalRecord) {
            globalRecord = streak;
            globalRecordHolder = player.getName();
        }

        return streak;
    }

    /**
     * Resets a player's streak.
     */
    public void reset(Player player) {
        currentStreaks.put(player.getUniqueId(), 0);
    }

    /**
     * Sets a player's streak.
     */
    public void set(Player player, int amount) {

        currentStreaks.put(player.getUniqueId(), amount);

        if (amount > highestStreaks.getOrDefault(player.getUniqueId(), 0)) {
            highestStreaks.put(player.getUniqueId(), amount);
        }

        if (amount > globalRecord) {
            globalRecord = amount;
            globalRecordHolder = player.getName();
        }
    }

    public int getGlobalRecord() {
        return globalRecord;
    }

    public String getGlobalRecordHolder() {
        return globalRecordHolder;
    }

}
