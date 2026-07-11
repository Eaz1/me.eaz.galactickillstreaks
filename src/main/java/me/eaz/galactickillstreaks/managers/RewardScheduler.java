package me.eaz.galactickillstreaks.managers;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;

public class RewardScheduler {

    private final GalacticKillStreaks plugin;
    private final RewardManager rewardManager;
    private final AutoBountyManager autoBountyManager;
    private final BroadcastManager broadcastManager;
    private final RecordManager recordManager;

    public RewardScheduler(GalacticKillStreaks plugin,
                           RewardManager rewardManager,
                           AutoBountyManager autoBountyManager,
                           BroadcastManager broadcastManager,
                           RecordManager recordManager) {

        this.plugin = plugin;
        this.rewardManager = rewardManager;
        this.autoBountyManager = autoBountyManager;
        this.broadcastManager = broadcastManager;
        this.recordManager = recordManager;
    }

    /**
     * Called whenever a player's killstreak increases.
     */
    public void handleKill(Player player, int streak) {

        // Update server record
        recordManager.checkRecord(player, streak);

        ConfigurationSection section =
                plugin.getConfig().getConfigurationSection("killstreaks");

        if (section == null) {
            return;
        }

        ConfigurationSection reward = null;

        // Exact milestone
        if (section.contains(String.valueOf(streak))) {
            reward = section.getConfigurationSection(String.valueOf(streak));
        }

        // Infinite milestones (50, 60, 70, 80...)
        if (reward == null) {

            int repeatStart = plugin.getConfig().getInt("repeat.start-at", 50);
            int repeatEvery = plugin.getConfig().getInt("repeat.every", 10);

            if (streak >= repeatStart &&
                    (streak - repeatStart) % repeatEvery == 0) {

                reward = section.getConfigurationSection(
                        String.valueOf(repeatStart)
                );
            }
        }

        if (reward == null) {
            return;
        }

        // Broadcast
        broadcastManager.broadcastKillStreak(player, streak);

        // Commands
        List<String> commands = reward.getStringList("commands");

        for (String command : commands) {

            Bukkit.dispatchCommand(
                    Bukkit.getConsoleSender(),
                    command.replace("%player%", player.getName())
            );
        }

        // Automatic bounty
        double bounty = reward.getDouble("bounty-add");

        if (bounty > 0) {

            autoBountyManager.addServerBounty(player, bounty);

            broadcastManager.broadcastBounty(player, bounty);
        }
    }
}
