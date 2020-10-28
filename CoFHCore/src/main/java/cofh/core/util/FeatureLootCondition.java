package cofh.core.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

import javax.annotation.Nonnull;

/**
 * With thanks to Vazkii. :)
 */
public class FeatureLootCondition implements ILootCondition {

    private final FlagManager manager;
    private final String flag;

    public FeatureLootCondition(FlagManager manager, String flag) {

        this.manager = manager;
        this.flag = flag;
    }

    @Override
    public boolean test(LootContext lootContext) {

        return manager.getFlag(flag).getAsBoolean();
    }

    // region SERIALIZER
    public static class Serializer extends AbstractSerializer<FeatureLootCondition> {

        private final FlagManager manager;

        public Serializer(FlagManager manager, ResourceLocation location) {

            super(location, FeatureLootCondition.class);
            this.manager = manager;
        }

        @Override
        public void serialize(@Nonnull JsonObject json, @Nonnull FeatureLootCondition value, @Nonnull JsonSerializationContext context) {

            json.addProperty("flag", value.flag);
        }

        @Nonnull
        @Override
        public FeatureLootCondition deserialize(@Nonnull JsonObject json, @Nonnull JsonDeserializationContext context) {

            return new FeatureLootCondition(manager, json.getAsJsonPrimitive("flag").getAsString());
        }

    }
    // endregion
}