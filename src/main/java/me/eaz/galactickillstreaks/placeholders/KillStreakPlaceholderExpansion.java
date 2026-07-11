package me.eaz.galactickillstreaks.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.eaz.galactickillstreaks.GalacticKillStreaks;
import me.eaz.galactickillstreaks.data.PlayerData;
import org.bukkit.OfflinePlayer;

public class KillStreakPlaceholderExpansion extends PlaceholderExpansion {

    private final GalacticKillStreaks plugin;

    public KillStreakPlaceholderExpansion(GalacticKillStreaks plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "gks";
    }

    @Override
    public String getAuthor() {
        return "Eaz";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {

        if (player == null) {
            return "";
        }

        PlayerData data = plugin.getDataManager().getPlayerData(player.getUniqueId());

        switch (params.toLowerCase()) {

            case "current":
                return String.valueOf(data.getCurrentKillStreak());

            case "highest":
                return String.valueOf(data.getHighestKillStreak());

            case "record":
                return String.valueOf(plugin.getStorageManager().getGlobalRecord());

            case "record_holder":
                return plugin.getStorageManager().getGlobalRecordHolder();

            case "bounty":
                if (player.getPlayer() == null) {
                    return "0";
                }

                return String.valueOf(
                        (int) plugin.getAutoBountyManager()
                                .getCurrentBounty(player.getPlayer())
                );

            default:
                return null;
        }
    }
}
