package cofh.thermal.core.client.renderer.entity;

import cofh.thermal.core.client.renderer.entity.model.BlizzModel;
import cofh.thermal.core.entity.monster.BlizzEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlizzRenderer extends MobRenderer<BlizzEntity, BlizzModel<BlizzEntity>> {

    private static final ResourceLocation BLIZZ_TEXTURES = new ResourceLocation("textures/entity/blaze.png");

    public BlizzRenderer(EntityRendererManager renderManagerIn) {

        super(renderManagerIn, new BlizzModel<>(), 0.5F);
    }

    protected int getBlockLight(BlizzEntity entityIn, float partialTicks) {

        return 15;
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getEntityTexture(BlizzEntity entity) {

        return BLIZZ_TEXTURES;
    }

}