package me.eaz.galactickillstreaks.managers;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import net.Indyuce.bountyhunters.BountyHunters;
import net.Indyuce.bountyhunters.api.Bounty;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class BountyManager {

    private final GalacticKillStreaks plugin;

    public BountyManager(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    /**
     * Adds a server bounty to the player's existing bounty.
     */
    public void addBounty(Player target, double amount) {

        net.Indyuce.bountyhunters.manager.BountyManager manager =
                BountyHunters.getBountyManager();

        OfflinePlayer console = null;

        // Player already has a bounty
        if (manager.hasBounty(target)) {

            Bounty bounty = manager.getBounty(target);
            bounty.setReward(bounty.getReward() + amount);

            return;
        }

        // Create a new automatic bounty
        Bounty bounty = new Bounty(console, target, amount);
        manager.registerBounty(bounty);
    }

    /**
     * Returns the player's current bounty.
     */
    public double getBounty(Player player) {

        net.Indyuce.bountyhunters.manager.BountyManager manager =
                BountyHunters.getBountyManager();

        if (!manager.hasBounty(player))
            return 0;

        return manager.getBounty(player).getReward();
    }

    /**
     * Returns true if the player has a bounty.
     */
    public boolean hasBounty(Player player) {
        return BountyHunters.getBountyManager().hasBounty(player);
    }
}
