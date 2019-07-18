package cofh.lib.util.helpers;

import net.minecraft.client.resources.I18n;

import java.text.NumberFormat;
import java.util.Locale;

public final class StringHelper {

    private StringHelper() {

    }

    public static String titleCase(String input) {

        return input.substring(0, 1).toUpperCase(Locale.ROOT) + input.substring(1);
    }

    public static String localize(String key) {

        return I18n.format(key);
    }

    public static String localize(String key, Object... format) {

        return I18n.format(key, format);
    }

    public static boolean canLocalize(String key) {

        return I18n.hasKey(key);
    }

    public static String format(long number) {

        return NumberFormat.getInstance().format(number);
    }

}
