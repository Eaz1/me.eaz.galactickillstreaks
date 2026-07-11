package me.eaz.galactickillstreaks.utils;

public class TimeUtil {

    /**
     * Formats milliseconds into a readable time.
     * Example:
     * 900000 -> 15m
     * 65000 -> 1m 5s
     * 4500 -> 4s
     */
    public static String format(long millis) {

        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        seconds %= 60;
        minutes %= 60;
        hours %= 24;

        StringBuilder builder = new StringBuilder();

        if (days > 0) {
            builder.append(days).append("d ");
        }

        if (hours > 0) {
            builder.append(hours).append("h ");
        }

        if (minutes > 0) {
            builder.append(minutes).append("m ");
        }

        if (seconds > 0 || builder.length() == 0) {
            builder.append(seconds).append("s");
        }

        return builder.toString().trim();
    }

    /**
     * Returns true if a cooldown has expired.
     */
    public static boolean hasExpired(long lastTime, long cooldownMillis) {
        return System.currentTimeMillis() - lastTime >= cooldownMillis;
    }

    /**
     * Returns the remaining cooldown in milliseconds.
     */
    public static long getRemaining(long lastTime, long cooldownMillis) {

        long remaining = cooldownMillis - (System.currentTimeMillis() - lastTime);

        return Math.max(remaining, 0);
    }
}
