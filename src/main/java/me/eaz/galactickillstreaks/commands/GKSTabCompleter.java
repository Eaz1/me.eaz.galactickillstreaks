package me.eaz.galactickillstreaks.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GKSTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> completions = new ArrayList<>();

        if (args.length == 1) {

            if ("reload".startsWith(args[0].toLowerCase())) {
                completions.add("reload");
            }

            if ("reset".startsWith(args[0].toLowerCase())) {
                completions.add("reset");
            }

            if ("setstreak".startsWith(args[0].toLowerCase())) {
                completions.add("setstreak");
            }

            return completions;
        }

        if (args.length == 2) {

            if (args[0].equalsIgnoreCase("reset") ||
                    args[0].equalsIgnoreCase("setstreak")) {

                for (Player player : Bukkit.getOnlinePlayers()) {

                    if (player.getName().toLowerCase().startsWith(args[1].toLowerCase())) {
                        completions.add(player.getName());
                    }
                }
            }

            return completions;
        }

        if (args.length == 3) {

            if (args[0].equalsIgnoreCase("setstreak")) {

                completions.add("0");
                completions.add("5");
                completions.add("10");
                completions.add("25");
                completions.add("50");
                completions.add("100");
            }

            return completions;
        }

        return completions;
    }
}
