package me.eaz.galactickillstreaks.utils;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtil {

    private static GalacticKillStreaks plugin;

    public static void initialize(GalacticKillStreaks instance) {
        plugin = instance;
    }

    /**
     * Gets the plugin prefix.
     */
    public static String getPrefix() {

        if (plugin == null) {
            return ColorUtil.color("&8[&bGalactic&3KS&8] ");
        }

        return ColorUtil.color(
                plugin.getConfig().getString(
                        "messages.prefix",
                        "&8[&bGalactic&3KS&8] "
                )
        );
    }

    /**
     * Sends a prefixed message.
     */
    public static void send(CommandSender sender, String message) {
        sender.sendMessage(getPrefix() + ColorUtil.color(message));
    }

    /**
     * Sends a message without a prefix.
     */
    public static void sendRaw(CommandSender sender, String message) {
        sender.sendMessage(ColorUtil.color(message));
    }

    /**
     * Sends a player message with placeholders.
     */
    public static void send(Player player, String message, int streak, double bounty) {

        message = message
                .replace("%player%", player.getName())
                .replace("%streak%", String.valueOf(streak))
                .replace("%bounty%", String.valueOf((int) bounty));

        send(player, message);
    }

    /**
     * Broadcasts a message with the plugin prefix.
     */
    public static void broadcast(String message) {

        plugin.getServer().broadcastMessage(
                getPrefix() + ColorUtil.color(message)
        );
    }
}
