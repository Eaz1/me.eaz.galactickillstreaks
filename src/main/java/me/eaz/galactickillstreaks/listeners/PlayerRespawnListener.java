package me.eaz.galactickillstreaks.listeners;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import me.eaz.galactickillstreaks.data.PlayerData;
import me.eaz.galactickillstreaks.utils.MessageUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    private final GalacticKillStreaks plugin;

    public PlayerRespawnListener(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {

        PlayerData data = plugin.getDataManager().getPlayerData(event.getPlayer());

        if (data.getCurrentKillStreak() == 0) {

            MessageUtil.send(
                    event.getPlayer(),
                    "&cYour killstreak has ended. Good luck getting another one!"
            );

        } else {

            MessageUtil.send(
                    event.getPlayer(),
                    "&eCurrent Killstreak: &6" + data.getCurrentKillStreak()
            );
        }
    }
}
