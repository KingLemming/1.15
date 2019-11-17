package cofh.cofh_archery.renderer.entity.projectile;

import cofh.cofh_archery.entity.projectile.QuartzArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class QuartzArrowRenderer extends ArrowRenderer<QuartzArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("cofh_archery:textures/entity/projectiles/quartz_arrow.png");

    public QuartzArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(QuartzArrowEntity entity) {

        return TEXTURE;
    }

}
