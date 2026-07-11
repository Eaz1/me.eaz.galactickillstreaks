package me.eaz.galactickillstreaks.commands;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import me.eaz.galactickillstreaks.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Comparator;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class TopStreaksCommand implements CommandExecutor {

    private final GalacticKillStreaks plugin;

    public TopStreaksCommand(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage(ChatColor.GOLD + "====== Top Killstreaks ======");

        int position = 1;

        for (Map.Entry<UUID, PlayerData> entry : plugin.getDataManager()
                .getAllPlayerData()
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(
                        e -> -e.getValue().getHighestKillStreak()))
                .limit(10)
                .collect(Collectors.toList())) {

            OfflinePlayer player = Bukkit.getOfflinePlayer(entry.getKey());

            sender.sendMessage(
                    ChatColor.YELLOW + "" + position + ". " +
                    ChatColor.RED + player.getName() +
                    ChatColor.GRAY + " - " +
                    ChatColor.GREEN + entry.getValue().getHighestKillStreak()
            );

            position++;
        }

        return true;
    }
}
