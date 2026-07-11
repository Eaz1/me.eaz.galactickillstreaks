package me.eaz.galactickillstreaks.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData {

    // Current killstreak
    private int currentKillStreak;

    // Highest killstreak ever
    private int highestKillStreak;

    // Anti-farm cooldowns
    // Key = Victim UUID
    // Value = Last kill timestamp
    private final Map<UUID, Long> lastKills;

    public PlayerData() {
        this.currentKillStreak = 0;
        this.highestKillStreak = 0;
        this.lastKills = new HashMap<>();
    }

    /**
     * Gets the current killstreak.
     */
    public int getCurrentKillStreak() {
        return currentKillStreak;
    }

    /**
     * Sets the current killstreak.
     */
    public void setCurrentKillStreak(int currentKillStreak) {
        this.currentKillStreak = currentKillStreak;
    }

    /**
     * Adds one to the killstreak.
     */
    public void incrementKillStreak() {
        this.currentKillStreak++;

        if (currentKillStreak > highestKillStreak) {
            highestKillStreak = currentKillStreak;
        }
    }

    /**
     * Resets the killstreak.
     */
    public void resetKillStreak() {
        this.currentKillStreak = 0;
    }

    /**
     * Gets the highest killstreak.
     */
    public int getHighestKillStreak() {
        return highestKillStreak;
    }

    /**
     * Sets the highest killstreak.
     */
    public void setHighestKillStreak(int highestKillStreak) {
        this.highestKillStreak = highestKillStreak;
    }

    /**
     * Gets the last kill map used for anti-farm.
     */
    public Map<UUID, Long> getLastKills() {
        return lastKills;
    }

    /**
     * Returns the last time this player killed the specified victim.
     */
    public long getLastKill(UUID victim) {
        return lastKills.getOrDefault(victim, 0L);
    }

    /**
     * Updates the last kill time for the specified victim.
     */
    public void setLastKill(UUID victim, long time) {
        lastKills.put(victim, time);
    }

    /**
     * Clears all anti-farm cooldowns.
     */
    public void clearLastKills() {
        lastKills.clear();
    }
}
