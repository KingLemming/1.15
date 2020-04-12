package cofh.lib.client.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import javax.annotation.Nonnull;

public interface IModelParser {

    @Nonnull
    IModelCoFH fromJson(JsonDeserializationContext ctx, JsonObject json);

}
