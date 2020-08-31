package cofh.thermal.core.common;

import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static cofh.core.util.constants.NBTTags.*;

public class ThermalAugmentRules {

    private ThermalAugmentRules() {

    }

    // region PROPERTIES
    private static final Set<String> ATTR_ADD = new HashSet<>();
    private static final Set<String> ATTR_MAX = new HashSet<>();
    private static final Set<String> ATTR_MULT = new HashSet<>();
    private static final Set<String> ATTR_INV = new HashSet<>();
    private static final Set<String> ATTR_INT = new HashSet<>();

    private static final Set<String> TYPE_EXC = new HashSet<>();

    // Sets storing if an attribute is multiplicative or additive, and also if the calculation is maximized, or higher values are bad.
    static {
        // Additive
        ATTR_ADD.addAll(Arrays.asList(
                TAG_AUGMENT_AREA_RADIUS,

                TAG_AUGMENT_DYNAMO_PRODUCTION,

                TAG_AUGMENT_MACHINE_POWER,
                TAG_AUGMENT_MACHINE_PRIMARY,
                TAG_AUGMENT_MACHINE_SECONDARY,

                TAG_AUGMENT_POTION_AMPLIFIER,
                TAG_AUGMENT_POTION_DURATION
        ));
        // Multiplicative
        ATTR_MULT.addAll(Arrays.asList(
                TAG_AUGMENT_BASE_MOD,

                TAG_AUGMENT_ENERGY_STORAGE,
                TAG_AUGMENT_ENERGY_XFER,

                TAG_AUGMENT_FLUID_STORAGE,

                TAG_AUGMENT_DYNAMO_EFFICIENCY,

                TAG_AUGMENT_MACHINE_CATALYST,
                TAG_AUGMENT_MACHINE_ENERGY,
                TAG_AUGMENT_MACHINE_XP
        ));
        // Maximized (Not exclusive with other sets)
        ATTR_MAX.addAll(Arrays.asList(
                TAG_AUGMENT_BASE_MOD,

                TAG_AUGMENT_ENERGY_STORAGE,
                TAG_AUGMENT_ENERGY_XFER,

                TAG_AUGMENT_FLUID_STORAGE,

                TAG_AUGMENT_MACHINE_MIN_OUTPUT
        ));
        // Inverse - HIGHER = WORSE (Not exclusive with other sets)
        ATTR_INV.addAll(Arrays.asList(
                TAG_AUGMENT_MACHINE_CATALYST,
                TAG_AUGMENT_MACHINE_ENERGY
        ));
        // Integer - Mod is NOT a % (Not exclusive with other sets)
        ATTR_INT.addAll(Arrays.asList(
                TAG_AUGMENT_AREA_RADIUS,

                TAG_AUGMENT_POTION_AMPLIFIER
        ));
        // Type Exclusive
        TYPE_EXC.addAll(Arrays.asList(
                TAG_AUGMENT_TYPE_FLUID,
                TAG_AUGMENT_TYPE_RF,
                TAG_AUGMENT_TYPE_UPGRADE
        ));
    }

    public static boolean isTypeExclusive(String type) {

        return TYPE_EXC.contains(type);
    }

    public static boolean isAdditive(String mod) {

        return ATTR_ADD.contains(mod);
    }

    public static boolean isMultiplicative(String mod) {

        return ATTR_MULT.contains(mod);
    }

    public static boolean isMaximized(String mod) {

        return ATTR_MAX.contains(mod);
    }

    public static boolean isInverse(String mod) {

        return ATTR_INV.contains(mod);
    }

    public static boolean isInteger(String mod) {

        return ATTR_INT.contains(mod);
    }
    // endregion

    // region VALIDATORS
    public static final Predicate<ItemStack> DEVICE_VALIDATOR = (e) -> true;

    public static final Predicate<ItemStack> DYNAMO_VALIDATOR = (e) -> true;

    public static final Predicate<ItemStack> MACHINE_VALIDATOR = (e) -> true;

    public static final Predicate<ItemStack> STORAGE_VALIDATOR = (e) -> true;
    // endregion
}
