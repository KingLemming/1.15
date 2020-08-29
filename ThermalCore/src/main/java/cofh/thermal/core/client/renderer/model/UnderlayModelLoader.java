package cofh.thermal.core.client.renderer.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class UnderlayModelLoader implements IModelLoader<UnderlayModelGeometry> {

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public UnderlayModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {

        return new UnderlayModelGeometry(ModelLoaderRegistry.VanillaProxy.Loader.INSTANCE.read(deserializationContext, modelContents));
    }

}
