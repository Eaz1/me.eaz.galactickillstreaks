package me.eaz.galactickillstreaks.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class ColorUtil {

    /**
     * Colorizes a single string using '&' color codes.
     */
    public static String color(String message) {

        if (message == null) {
            return "";
        }

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Colorizes every string in a list.
     */
    public static List<String> color(List<String> messages) {

        return messages.stream()
                .map(ColorUtil::color)
                .collect(Collectors.toList());
    }

    /**
     * Removes color codes.
     */
    public static String strip(String message) {

        if (message == null) {
            return "";
        }

        return ChatColor.stripColor(color(message));
    }
}
