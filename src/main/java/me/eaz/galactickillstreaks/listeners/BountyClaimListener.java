package me.eaz.galactickillstreaks.listeners;

import me.eaz.galactickillstreaks.GalacticKillStreaks;
import me.eaz.galactickillstreaks.utils.MessageUtil;
import net.Indyuce.bountyhunters.api.event.BountyClaimEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BountyClaimListener implements Listener {

    private final GalacticKillStreaks plugin;

    public BountyClaimListener(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBountyClaim(BountyClaimEvent event) {

        Player hunter = event.getHunter();
        Player target = event.getTarget();

        if (hunter == null || target == null) {
            return;
        }

        double reward = event.getBounty().getReward();

        MessageUtil.broadcast(
                "&6&lBOUNTY CLAIMED! &e" +
                hunter.getName() +
                " &7claimed the bounty on &c" +
                target.getName() +
                " &7for &6$" +
                (int) reward +
                "&7!"
        );

        hunter.sendMessage(ChatColor.GREEN +
                "You claimed a bounty worth $" + (int) reward + "!");

        target.sendMessage(ChatColor.RED +
                "Your bounty has been claimed!");
    }
}
