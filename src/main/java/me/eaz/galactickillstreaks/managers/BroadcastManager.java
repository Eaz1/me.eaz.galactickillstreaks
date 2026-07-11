package me.eaz.galactickillstreaks.managers;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BroadcastManager {

    private final GalacticKillStreaks plugin;

    public BroadcastManager(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    /**
     * Broadcasts a killstreak milestone.
     */
    public void broadcastKillStreak(Player player, int streak) {

        String message = plugin.getConfig()
                .getString("messages.streak",
                        "&6%player% &ehas reached a &c%streak% &ekillstreak!");

        message = message
                .replace("%player%", player.getName())
                .replace("%streak%", String.valueOf(streak));

        Bukkit.broadcastMessage(color(message));
    }

    /**
     * Broadcasts an automatic bounty.
     */
    public void broadcastBounty(Player player, double amount) {

        String message = plugin.getConfig()
                .getString("messages.bounty",
                        "&cThe server added &6$%bounty% &cto %player%'s bounty!");

        message = message
                .replace("%player%", player.getName())
                .replace("%bounty%", String.format("%.0f", amount));

        Bukkit.broadcastMessage(color(message));
    }

    /**
     * Broadcasts a new server record.
     */
    public void broadcastRecord(Player player, int streak) {

        String message = plugin.getConfig()
                .getString("messages.new-record",
                        "&6🏆 NEW SERVER RECORD! &e%player% reached %record% kills!");

        message = message
                .replace("%player%", player.getName())
                .replace("%record%", String.valueOf(streak));

        Bukkit.broadcastMessage(color(message));
    }

    /**
     * Broadcasts any custom message.
     */
    public void broadcast(String message) {
        Bukkit.broadcastMessage(color(message));
    }

    /**
     * Colorizes '&' color codes.
     */
    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
