package cofh.thermal.core.util.parsers;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;

public class ConstantParser extends AbstractContentParser {

    private static final ConstantParser INSTANCE = new ConstantParser();

    public static ConstantParser instance() {

        return INSTANCE;
    }

    private ConstantParser() {

    }

    @Override
    protected void parseObject(JsonObject object) {

        if (object.has(COMMENT) || object.has(ENABLE) && !object.get(ENABLE).getAsBoolean()) {
            return;
        }
        String name;
        ItemStack entry;

        /* ORE */
        name = object.get(CONSTANT).getAsString();

        /* ENTRY */
        entry = parseItemStack(object.get(ENTRY));

        if (!CONSTANTS.containsKey(name)) {
            CONSTANTS.put(name, entry);
        }
    }

}
