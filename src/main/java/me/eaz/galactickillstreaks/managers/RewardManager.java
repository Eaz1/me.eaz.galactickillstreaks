package me.eaz.galactickillstreaks.managers;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;

public class RewardManager {

    private final GalacticKillStreaks plugin;

    public RewardManager(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    public void checkRewards(Player player, int streak) {

        ConfigurationSection section = plugin.getConfig().getConfigurationSection("killstreaks");

        if (section == null) {
            return;
        }

        ConfigurationSection reward = null;

        // Normal milestone
        if (section.contains(String.valueOf(streak))) {
            reward = section.getConfigurationSection(String.valueOf(streak));
        }

        // Infinite repeating rewards
        if (reward == null && plugin.getConfig().getBoolean("repeat.enabled")) {

            int start = plugin.getConfig().getInt("repeat.start-at");
            int every = plugin.getConfig().getInt("repeat.every");

            if (streak >= start && ((streak - start) % every == 0)) {
                reward = section.getConfigurationSection(String.valueOf(start));
            }
        }

        if (reward == null) {
            return;
        }

        // Broadcast
        if (reward.getBoolean("broadcast")) {

            String message = plugin.getConfig()
                    .getString("messages.streak")
                    .replace("%player%", player.getName())
                    .replace("%streak%", String.valueOf(streak));

            Bukkit.broadcastMessage(color(message));
        }

        // Run commands
        List<String> commands = reward.getStringList("commands");

        for (String command : commands) {

            Bukkit.dispatchCommand(
                    Bukkit.getConsoleSender(),
                    command.replace("%player%", player.getName())
            );
        }

        // Automatic bounty (implemented later)
        int bounty = reward.getInt("bounty-add");

        if (bounty > 0) {
            // TODO:
            // Add bounty to player here.
        }
    }

    private String color(String text) {
        return text.replace("&", "§");
    }
}
