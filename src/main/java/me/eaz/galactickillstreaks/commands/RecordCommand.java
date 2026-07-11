package me.eaz.galactickillstreaks.commands;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RecordCommand implements CommandExecutor {

    private final GalacticKillStreaks plugin;

    public RecordCommand(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String holder = plugin.getStorageManager().getGlobalRecordHolder();
        int streak = plugin.getStorageManager().getGlobalRecord();

        sender.sendMessage(ChatColor.GOLD + "========== Server Record ==========");
        sender.sendMessage(ChatColor.YELLOW + "Player: " + ChatColor.AQUA + holder);
        sender.sendMessage(ChatColor.YELLOW + "Highest Killstreak: " + ChatColor.RED + streak);
        sender.sendMessage(ChatColor.GOLD + "===================================");

        return true;
    }
}
