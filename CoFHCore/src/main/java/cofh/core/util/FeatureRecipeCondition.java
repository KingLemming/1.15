package cofh.core.util;

import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

/**
 * With thanks to Vazkii. :)
 */
public class FeatureRecipeCondition implements ICondition {

    private static final ResourceLocation NAME = new ResourceLocation("cofh", "flag");

    private final FeatureManager manager;
    private final String flag;

    public FeatureRecipeCondition(FeatureManager manager, String flag) {

        this.manager = manager;
        this.flag = flag;
    }

    @Override
    public ResourceLocation getID() {

        return NAME;
    }

    @Override
    public boolean test() {

        return manager.getFeature(flag).getAsBoolean();
    }

    // region SERIALIZER
    public static class Serializer implements IConditionSerializer<FeatureRecipeCondition> {

        private final FeatureManager manager;
        private final ResourceLocation location;

        public Serializer(FeatureManager manager, ResourceLocation location) {

            this.manager = manager;
            this.location = location;
        }

        @Override
        public void write(JsonObject json, FeatureRecipeCondition value) {

            json.addProperty("flag", value.flag);
        }

        @Override
        public FeatureRecipeCondition read(JsonObject json) {

            return new FeatureRecipeCondition(manager, json.getAsJsonPrimitive("flag").getAsString());
        }

        @Override
        public ResourceLocation getID() {

            return location;
        }

    }
    // endregion
}
