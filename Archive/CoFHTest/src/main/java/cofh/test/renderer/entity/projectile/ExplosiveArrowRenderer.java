package cofh.potions.renderer.entity.projectile;

import cofh.potions.entity.projectile.ExplosiveArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExplosiveArrowRenderer extends ArrowRenderer<ExplosiveArrowEntity> {

    public static final ResourceLocation RES_EXPLOSIVE_ARROW = new ResourceLocation("cofh_test:textures/entity/projectiles/explosive_arrow.png");

    public ExplosiveArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    protected ResourceLocation getEntityTexture(ExplosiveArrowEntity entity) {

        return RES_EXPLOSIVE_ARROW;
    }

}
