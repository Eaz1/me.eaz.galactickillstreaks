package me.eaz.galactickillstreaks.listeners;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import me.eaz.galactickillstreaks.managers.CombatManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageListener implements Listener {

    private final GalacticKillStreaks plugin;
    private final CombatManager combatManager;

    public EntityDamageListener(GalacticKillStreaks plugin, CombatManager combatManager) {
        this.plugin = plugin;
        this.combatManager = combatManager;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player victim = (Player) event.getEntity();
        Player attacker = (Player) event.getDamager();

        // Put both players into combat
        combatManager.tag(attacker, victim);
    }
}
