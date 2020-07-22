package cofh.core.init;

import cofh.core.util.FeatureManager;

import java.util.function.BooleanSupplier;

public class CoreFeatures {

    private CoreFeatures() {

    }

    private static final FeatureManager FEATURE_MANAGER = new FeatureManager();

    public static FeatureManager manager() {

        return FEATURE_MANAGER;
    }

    public static void setFeature(String flag, boolean enable) {

        FEATURE_MANAGER.setFeature(flag, enable);
    }

    public static void setFeature(String flag, BooleanSupplier condition) {

        FEATURE_MANAGER.setFeature(flag, condition);
    }

    public static BooleanSupplier getFeature(String flag) {

        return FEATURE_MANAGER.getFeature(flag);
    }

    // region SPECIFIC FEATURES
    public static String FLAG_ECTOPLASM = "ectoplasm";
    // endregion
}
