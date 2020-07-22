package cofh.core.util;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.util.function.BooleanSupplier;

import static cofh.lib.util.constants.Constants.FALSE;
import static cofh.lib.util.constants.Constants.TRUE;

public class FeatureManager {

    private static final Object2ObjectOpenHashMap<String, BooleanSupplier> FEATURES = new Object2ObjectOpenHashMap<>();

    private BooleanSupplier getFeatureRaw(String flag) {

        FEATURES.putIfAbsent(flag, FALSE);
        return FEATURES.get(flag);
    }

    public void setFeature(String flag, boolean enable) {

        FEATURES.put(flag, enable ? TRUE : FALSE);
    }

    public void setFeature(String flag, BooleanSupplier condition) {

        FEATURES.put(flag, condition == null ? FALSE : condition);
    }

    public BooleanSupplier getFeature(String flag) {

        return getFeatureRaw(flag);
    }

}
