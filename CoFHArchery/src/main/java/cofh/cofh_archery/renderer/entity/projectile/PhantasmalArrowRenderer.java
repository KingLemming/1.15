package cofh.cofh_archery.renderer.entity.projectile;

import cofh.cofh_archery.entity.projectile.PhantasmalArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PhantasmalArrowRenderer extends ArrowRenderer<PhantasmalArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("cofh_archery:textures/entity/projectiles/phantasmal_arrow.png");

    public PhantasmalArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(PhantasmalArrowEntity entity) {

        return TEXTURE;
    }

}
