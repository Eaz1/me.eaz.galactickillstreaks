package me.eaz.galactickillstreaks.commands;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import me.eaz.galactickillstreaks.data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillStreakCommand implements CommandExecutor {

    private final GalacticKillStreaks plugin;

    public KillStreakCommand(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        PlayerData data = plugin.getDataManager().getPlayerData(player);

        player.sendMessage(ChatColor.GOLD + "==============================");
        player.sendMessage(ChatColor.YELLOW + "Current Killstreak: "
                + ChatColor.RED + data.getCurrentKillStreak());

        player.sendMessage(ChatColor.YELLOW + "Highest Killstreak: "
                + ChatColor.GREEN + data.getHighestKillStreak());

        player.sendMessage(ChatColor.YELLOW + "Current Bounty: "
                + ChatColor.GOLD + "$"
                + plugin.getAutoBountyManager().getCurrentBounty(player));

        player.sendMessage(ChatColor.YELLOW + "Server Record: "
                + ChatColor.AQUA
                + plugin.getStorageManager().getGlobalRecordHolder()
                + ChatColor.GRAY + " ("
                + plugin.getStorageManager().getGlobalRecord()
                + ")");

        player.sendMessage(ChatColor.GOLD + "==============================");

        return true;
    }
}
