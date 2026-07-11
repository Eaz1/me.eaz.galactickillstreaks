package me.eaz.galactickillstreaks.listeners;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import me.eaz.galactickillstreaks.utils.MessageUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandListener implements Listener {

    private final GalacticKillStreaks plugin;

    public PlayerCommandListener(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {

        String command = event.getMessage().toLowerCase();

        // Block reload while players are online
        if (command.equals("/reload") || command.equals("/rl")) {

            if (!event.getPlayer().hasPermission("galactickillstreaks.admin")) {

                event.setCancelled(true);

                MessageUtil.send(
                        event.getPlayer(),
                        "&cThis command is disabled."
                );
            }
        }

        // Future command checks can be added here
        // Example:
        // - Block commands during combat
        // - Restrict bounty commands
        // - Add cooldowns
    }
}
