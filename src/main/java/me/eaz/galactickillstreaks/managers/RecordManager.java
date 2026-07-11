package me.eaz.galactickillstreaks.managers;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RecordManager {

    private final GalacticKillStreaks plugin;

    public RecordManager(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    /**
     * Checks if the player has broken the server record.
     */
    public void checkRecord(Player player, int streak) {

        int currentRecord = plugin.getStorageManager().getGlobalRecord();

        if (streak <= currentRecord) {
            return;
        }

        plugin.getStorageManager().saveGlobalRecord(
                player.getName(),
                streak
        );

        Bukkit.broadcastMessage(ChatColor.GOLD + "================================");
        Bukkit.broadcastMessage(ChatColor.YELLOW + "🏆 NEW SERVER RECORD!");
        Bukkit.broadcastMessage(ChatColor.AQUA + player.getName()
                + ChatColor.YELLOW + " reached a killstreak of "
                + ChatColor.RED + streak + ChatColor.YELLOW + "!");
        Bukkit.broadcastMessage(ChatColor.GOLD + "================================");
    }

    /**
     * Returns the current server record.
     */
    public int getRecord() {
        return plugin.getStorageManager().getGlobalRecord();
    }

    /**
     * Returns the record holder.
     */
    public String getRecordHolder() {
        return plugin.getStorageManager().getGlobalRecordHolder();
    }
}
