package cofh.archersparadox.renderer.entity.projectile;

import cofh.archersparadox.entity.projectile.PhantasmalArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PhantasmalArrowRenderer extends ArrowRenderer<PhantasmalArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("archers_paradox:textures/entity/projectiles/phantasmal_arrow.png");

    public PhantasmalArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(PhantasmalArrowEntity entity) {

        return TEXTURE;
    }

}
