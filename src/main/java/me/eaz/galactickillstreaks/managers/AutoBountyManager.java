package me.eaz.galactickillstreaks.managers;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import net.Indyuce.bountyhunters.BountyHunters;
import net.Indyuce.bountyhunters.api.Bounty;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class BountyManager {

    private final GalacticKillStreaks plugin;

    public BountyManager(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    /**
     * Adds a server bounty to the player's current bounty.
     */
    public void addServerBounty(Player target, double amount) {

        net.Indyuce.bountyhunters.manager.BountyManager bountyManager =
                BountyHunters.getBountyManager();

        OfflinePlayer server = Bukkit.getOfflinePlayer("Server");

        if (bountyManager.hasBounty(target)) {

            Bounty bounty = bountyManager.getBounty(target);

            // Add to the existing bounty instead of replacing it
            bounty.addToReward(server, amount);

            bountyManager.saveBounties();
            return;
        }

        // Create a brand-new server bounty
        Bounty bounty = new Bounty(server, target, amount);

        bountyManager.registerBounty(bounty);
        bountyManager.saveBounties();
    }

    /**
     * Returns the player's current bounty.
     */
    public double getCurrentBounty(Player player) {

        net.Indyuce.bountyhunters.manager.BountyManager bountyManager =
                BountyHunters.getBountyManager();

        if (!bountyManager.hasBounty(player)) {
            return 0;
        }

        return bountyManager.getBounty(player).getReward();
    }

    /**
     * Returns true if the player currently has a bounty.
     */
    public boolean hasBounty(Player player) {

        return BountyHunters.getBountyManager().hasBounty(player);
    }

    /**
     * Removes a player's bounty.
     */
    public void removeBounty(Player player) {

        net.Indyuce.bountyhunters.manager.BountyManager bountyManager =
                BountyHunters.getBountyManager();

        if (!bountyManager.hasBounty(player)) {
            return;
        }

        bountyManager.unregisterBounty(
                bountyManager.getBounty(player)
        );

        bountyManager.saveBounties();
    }
}
