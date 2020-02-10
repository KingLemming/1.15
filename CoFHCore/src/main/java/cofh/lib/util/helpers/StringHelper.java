package cofh.lib.util.helpers;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.FluidStack;

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

    public static String getFluidName(FluidStack stack) {

        Fluid fluid = stack.getFluid();

        String name = "" + END;
        if (fluid.getAttributes().getRarity() == Rarity.UNCOMMON) {
            name += YELLOW;
        } else if (fluid.getAttributes().getRarity() == Rarity.RARE) {
            name += BRIGHT_BLUE;
        } else if (fluid.getAttributes().getRarity() == Rarity.EPIC) {
            name += PINK;
        }
        name += fluid.getAttributes().getDisplayName(stack).getUnformattedComponentText() + END;
        return name;
    }

    public static String getFluidName(FluidStack stack, String defaultName) {

        if (stack.isEmpty()) {
            return defaultName;
        }
        return getFluidName(stack);
    }

    public static String getItemName(ItemStack stack) {

        String name = "" + END;
        if (stack.getRarity() == Rarity.UNCOMMON) {
            name += YELLOW;
        } else if (stack.getRarity() == Rarity.RARE) {
            name += BRIGHT_BLUE;
        } else if (stack.getRarity() == Rarity.EPIC) {
            name += PINK;
        }
        name += stack.getDisplayName() + END;

        return name;
    }

    public static String getScaledNumber(long number) {

        if (number >= 1000000000) {
            return number / 1000000000 + "." + (number % 1000000000 / 100000000) + (number % 100000000 / 10000000) + "G";
        } else if (number >= 1000000) {
            return number / 1000000 + "." + (number % 1000000 / 100000) + (number % 100000 / 10000) + "M";
        } else if (number >= 1000) {
            return number / 1000 + "." + (number % 1000 / 100) + (number % 100 / 10) + "k";
        } else {
            return String.valueOf(number);
        }
    }

    // region TEXT COMPONENTS
    public static ITextComponent getChatComponent(Object object) {

        if (object instanceof ITextComponent) {
            return (ITextComponent) object;
        } else if (object instanceof String) {
            return new StringTextComponent((String) object);
        } else if (object instanceof ItemStack) {
            return ((ItemStack) object).getTextComponent();
        } else if (object instanceof Entity) {
            return ((Entity) object).getDisplayName();
        } else {
            return new StringTextComponent(String.valueOf(object));
        }
    }

    public static ITextComponent formChatComponent(Object... chats) {

        ITextComponent chat = getChatComponent(chats[0]);
        for (int i = 1, chatsLength = chats.length; i < chatsLength; ++i) {
            chat.appendSibling(getChatComponent(chats[i]));
        }
        return chat;
    }

    public static String toJSON(ITextComponent chatComponent) {

        return ITextComponent.Serializer.toJson(chatComponent);
    }

    public static ITextComponent fromJSON(String string) {

        return ITextComponent.Serializer.fromJsonLenient(string);
    }

    public static ITextComponent getEmptyLine() {

        return new StringTextComponent("");
    }

    public static ITextComponent getTextComponent(String key) {

        return canLocalize(key) ? new TranslationTextComponent(key) : new StringTextComponent(key);
    }

    public static ITextComponent getInfoTextComponent(String key) {

        return getTextComponent(key).applyTextStyle(TextFormatting.GREEN);
    }
    // endregion

    // region RESOURCE LOCATION
    public static String[] decompose(String resourceLoc, char delimiter) {

        return decompose("minecraft", resourceLoc, delimiter);
    }

    public static String[] decompose(String modid, String resourceLoc, char delimiter) {

        String[] decomposed = new String[]{modid, resourceLoc};
        int delIndex = resourceLoc.indexOf(delimiter);
        if (delIndex >= 0) {
            decomposed[1] = resourceLoc.substring(delIndex + 1);
            if (delIndex >= 1) {
                decomposed[0] = resourceLoc.substring(0, delIndex);
            }
        }
        return decomposed;
    }

    public static String namespace(String resourceLoc) {

        return decompose(resourceLoc, ':')[0];
    }

    public static String path(String resourceLoc) {

        return decompose(resourceLoc, ':')[1];
    }
    // endregion

    public static final String BLACK = (char) 167 + "0";
    public static final String BLUE = (char) 167 + "1";
    public static final String GREEN = (char) 167 + "2";
    public static final String TEAL = (char) 167 + "3";
    public static final String RED = (char) 167 + "4";
    public static final String PURPLE = (char) 167 + "5";
    public static final String ORANGE = (char) 167 + "6";
    public static final String LIGHT_GRAY = (char) 167 + "7";
    public static final String GRAY = (char) 167 + "8";
    public static final String LIGHT_BLUE = (char) 167 + "9";
    public static final String BRIGHT_GREEN = (char) 167 + "a";
    public static final String BRIGHT_BLUE = (char) 167 + "b";
    public static final String LIGHT_RED = (char) 167 + "c";
    public static final String PINK = (char) 167 + "d";
    public static final String YELLOW = (char) 167 + "e";
    public static final String WHITE = (char) 167 + "f";

    public static final String OBFUSCATED = (char) 167 + "k";
    public static final String BOLD = (char) 167 + "l";
    public static final String STRIKETHROUGH = (char) 167 + "m";
    public static final String UNDERLINE = (char) 167 + "n";
    public static final String ITALIC = (char) 167 + "o";
    public static final String END = (char) 167 + "r";

}
