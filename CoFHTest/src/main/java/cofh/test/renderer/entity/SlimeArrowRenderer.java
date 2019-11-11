package cofh.test.renderer.entity;

import cofh.test.entity.projectile.ExplosiveArrowEntity;
import cofh.test.entity.projectile.SlimeArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlimeArrowRenderer extends ArrowRenderer<SlimeArrowEntity> {

    public static final ResourceLocation RES_SLIME_ARROW = new ResourceLocation("cofh_test:textures/entity/projectiles/slime_arrow_entity.png");

    public SlimeArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    protected ResourceLocation getEntityTexture(SlimeArrowEntity entity) {

        return RES_SLIME_ARROW;
    }

}
