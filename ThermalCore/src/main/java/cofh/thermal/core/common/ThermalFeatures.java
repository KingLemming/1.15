package cofh.thermal.core.common;

import java.util.function.BooleanSupplier;

import static cofh.lib.item.ICoFHItem.HIDE;
import static cofh.lib.item.ICoFHItem.SHOW;

public class ThermalFeatures {

    private ThermalFeatures() {

    }

    public static void featureVanillaBlocks(boolean enable) {

        enableVanillaBlocks = enable ? SHOW : HIDE;
    }

    public static void featureTinkerBench(boolean enable) {

        enableTinkerBench = enable ? SHOW : HIDE;
    }

    public static void enableAreaAugments(boolean enable) {

        enableAreaAugments = enable ? SHOW : HIDE;
    }

    public static void enableDynamoAugments(boolean enable) {

        enableDynamoAugments = enable ? SHOW : HIDE;
    }

    public static void enableMachineAugments(boolean enable) {

        enableMachineAugments = enable ? SHOW : HIDE;
    }

    public static void enablePotionAugments(boolean enable) {

        enablePotionAugments = enable ? SHOW : HIDE;
    }

    public static void enableStorageAugments(boolean enable) {

        enableStorageAugments = enable ? SHOW : HIDE;
    }

    public static void enableUpgradeAugments(boolean enable) {

        enableUpgradeAugments = enable ? SHOW : HIDE;
    }

    public static BooleanSupplier enableVanillaBlocks = HIDE;

    public static BooleanSupplier enableTinkerBench = HIDE;

    public static BooleanSupplier enableAreaAugments = HIDE;
    public static BooleanSupplier enableDynamoAugments = HIDE;
    public static BooleanSupplier enableMachineAugments = HIDE;
    public static BooleanSupplier enablePotionAugments = HIDE;
    public static BooleanSupplier enableStorageAugments = HIDE;
    public static BooleanSupplier enableUpgradeAugments = HIDE;

}
