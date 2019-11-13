package cofh.cofh_archery.renderer.entity.projectile;

import cofh.cofh_archery.entity.projectile.ExplosiveArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExplosiveArrowRenderer extends ArrowRenderer<ExplosiveArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("cofh_archery:textures/entity/projectiles/explosive_arrow.png");

    public ExplosiveArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(ExplosiveArrowEntity entity) {

        return TEXTURE;
    }

}
