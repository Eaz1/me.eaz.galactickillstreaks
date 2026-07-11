package me.eaz.galactickillstreaks.listeners;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final GalacticKillStreaks plugin;

    public PlayerQuitListener(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        // Save all player data
        plugin.getStorageManager().savePlayerData(plugin.getDataManager());

    }
}
