package me.eaz.galactickillstreaks.listeners;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final GalacticKillStreaks plugin;

    public PlayerJoinListener(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        plugin.getDataManager().getPlayerData(event.getPlayer());

    }
}
