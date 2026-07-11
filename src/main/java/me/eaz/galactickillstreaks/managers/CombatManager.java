package me.eaz.galactickillstreaks.managers;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatManager {

    // Player UUID -> Combat end time
    private final Map<UUID, Long> combatMap = new HashMap<>();

    // Default combat duration (15 seconds)
    private final long combatTime = 15 * 1000L;

    /**
     * Puts a player into combat.
     */
    public void tag(Player player) {
        combatMap.put(player.getUniqueId(),
                System.currentTimeMillis() + combatTime);
    }

    /**
     * Tags both players after PvP.
     */
    public void tag(Player attacker, Player victim) {
        tag(attacker);
        tag(victim);
    }

    /**
     * Returns true if the player is in combat.
     */
    public boolean isInCombat(Player player) {

        if (!combatMap.containsKey(player.getUniqueId())) {
            return false;
        }

        long endTime = combatMap.get(player.getUniqueId());

        if (System.currentTimeMillis() >= endTime) {
            combatMap.remove(player.getUniqueId());
            return false;
        }

        return true;
    }

    /**
     * Returns the remaining combat time in seconds.
     */
    public int getRemaining(Player player) {

        if (!isInCombat(player)) {
            return 0;
        }

        long remaining =
                combatMap.get(player.getUniqueId()) - System.currentTimeMillis();

        return (int) Math.ceil(remaining / 1000.0);
    }

    /**
     * Removes a player from combat.
     */
    public void remove(Player player) {
        combatMap.remove(player.getUniqueId());
    }

    /**
     * Clears all combat tags.
     */
    public void clear() {
        combatMap.clear();
    }
}
