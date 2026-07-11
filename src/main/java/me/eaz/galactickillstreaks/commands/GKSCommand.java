package me.eaz.galactickillstreaks.commands;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GKSCommand implements CommandExecutor {

    private final GalacticKillStreaks plugin;

    public GKSCommand(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("galactickillstreaks.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.GOLD + "GalacticKillStreaks Admin");
            sender.sendMessage(ChatColor.YELLOW + "/gks reload");
            sender.sendMessage(ChatColor.YELLOW + "/gks reset <player>");
            sender.sendMessage(ChatColor.YELLOW + "/gks setstreak <player> <amount>");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {

            plugin.reloadConfig();

            sender.sendMessage(ChatColor.GREEN + "Configuration reloaded.");
            return true;
        }

        if (args[0].equalsIgnoreCase("reset")) {

            if (args.length != 2) {
                sender.sendMessage(ChatColor.RED + "Usage: /gks reset <player>");
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Player not found.");
                return true;
            }

            plugin.getKillStreakManager().reset(target);

            sender.sendMessage(ChatColor.GREEN + "Reset " + target.getName() + "'s killstreak.");
            return true;
        }

        if (args[0].equalsIgnoreCase("setstreak")) {

            if (args.length != 3) {
                sender.sendMessage(ChatColor.RED + "Usage: /gks setstreak <player> <amount>");
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Player not found.");
                return true;
            }

            int streak;

            try {
                streak = Integer.parseInt(args[2]);
            } catch (NumberFormatException ex) {
                sender.sendMessage(ChatColor.RED + "Invalid number.");
                return true;
            }

            plugin.getKillStreakManager().set(target, streak);

            sender.sendMessage(ChatColor.GREEN + "Set " + target.getName() + "'s killstreak to " + streak + ".");
            return true;
        }

        sender.sendMessage(ChatColor.RED + "Unknown subcommand.");
        return true;
    }
}
