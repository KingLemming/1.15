package cofh.core.client.model.parsing;

import cofh.lib.client.model.IModelCoFH;
import cofh.lib.client.model.IModelParser;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import javax.annotation.Nonnull;

public class ModelParserV1 implements IModelParser {

    private static final Gson GSON = new Gson();

    // TODO: Finish

    @Nonnull
    @Override
    public IModelCoFH fromJson(JsonDeserializationContext ctx, JsonObject json) {

        //        BlockModel modelinfo = ctx.deserialize(json, BlockModel.class);
        //
        //        Map<String, JsonElement> parsed = GSON.fromJson(json.getAsJsonObject("ctm_overrides"), new TypeToken<Map<String, JsonElement>>() {}.getType());
        //        if (parsed == null) {
        //            parsed = Collections.emptyMap();
        //        }
        //        Int2ObjectMap<JsonElement> replacements = new Int2ObjectArrayMap<>(parsed.size());
        //        for (Map.Entry<String, JsonElement> e : parsed.entrySet()) {
        //            try {
        //                int index = Integer.parseInt(e.getKey());
        //                replacements.put(index, e.getValue());
        //            } catch (NumberFormatException ex) {
        //            }
        //        }
        //        return new ModelCTM(modelinfo, replacements);

        return null;
    }

}
