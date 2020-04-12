package cofh.archersparadox.client.renderer.entity;

import cofh.archersparadox.entity.projectile.QuartzArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class QuartzArrowRenderer extends ArrowRenderer<QuartzArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("archers_paradox:textures/entity/projectiles/quartz_arrow.png");

    public QuartzArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(QuartzArrowEntity entity) {

        return TEXTURE;
    }

}
