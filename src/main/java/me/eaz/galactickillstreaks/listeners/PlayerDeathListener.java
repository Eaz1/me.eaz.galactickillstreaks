package me.eaz.galactickillstreaks.listeners;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import me.eaz.galactickillstreaks.managers.KillStreakManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.Map;

public class PlayerDeathListener implements Listener {

    private final GalacticKillStreaks plugin;
    private final KillStreakManager killStreakManager;

    // Anti-farm cooldown (killerUUID:victimUUID -> last kill time)
    private final Map<String, Long> killCooldowns = new HashMap<>();

    public PlayerDeathListener(GalacticKillStreaks plugin, KillStreakManager killStreakManager) {
        this.plugin = plugin;
        this.killStreakManager = killStreakManager;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        // Reset victim's streak
        killStreakManager.reset(victim);

        // Ignore non-player kills
        if (killer == null || killer.equals(victim)) {
            return;
        }

        // Anti-farm cooldown
        int cooldownMinutes = plugin.getConfig().getInt("anti-farm.cooldown-minutes");
        long cooldownMillis = cooldownMinutes * 60L * 1000L;

        String key = killer.getUniqueId().toString() + ":" + victim.getUniqueId().toString();
        long currentTime = System.currentTimeMillis();

        if (killCooldowns.containsKey(key)) {
            long lastKill = killCooldowns.get(key);

            if ((currentTime - lastKill) < cooldownMillis) {
                killer.sendMessage("§cThis kill doesn't count because you recently killed this player.");
                return;
            }
        }

        killCooldowns.put(key, currentTime);

        // Increase killer's streak
        int streak = killStreakManager.addKill(killer);

        Bukkit.broadcastMessage("§6" + killer.getName() + " is now on a §c" + streak + "§6 kill streak!");

        // TODO:
        // - RewardManager.checkRewards(killer, streak);
        // - BountyManager.addAutomaticBounty(killer, streak);
        // - RecordManager.checkGlobalRecord(killer, streak);
    }
}
