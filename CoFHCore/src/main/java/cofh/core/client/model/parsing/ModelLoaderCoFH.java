package cofh.core.client.model.parsing;

import cofh.lib.client.model.IModelCoFH;
import cofh.lib.client.model.IModelParser;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

import java.util.Map;

/**
 * Basically a copy of tterrag's brilliant design in CTM.
 */
public class ModelLoaderCoFH implements IModelLoader<IModelCoFH> {

    private static final Map<Integer, IModelParser> PARSER_VERSIONS = ImmutableMap.of(1, new ModelParserV1());

    @Override
    public IModelCoFH read(JsonDeserializationContext ctx, JsonObject json) {

        IModelParser parser = PARSER_VERSIONS.get(json.get("cofh_version").getAsInt());
        if (parser == null) {
            throw new IllegalArgumentException("Invalid \"cofh_version\" in model " + json);
        }
        json.remove("loader"); // Prevent reentrant parsing
        return parser.fromJson(ctx, json);
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

}
