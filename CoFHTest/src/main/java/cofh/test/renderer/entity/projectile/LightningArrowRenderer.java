package cofh.test.renderer.entity.projectile;

import cofh.test.entity.projectile.LightningArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LightningArrowRenderer extends ArrowRenderer<LightningArrowEntity> {

    public static final ResourceLocation RES_LIGHTNING_ARROW = new ResourceLocation("cofh_test:textures/entity/projectiles/lightning_arrow_entity.png");

    public LightningArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    protected ResourceLocation getEntityTexture(LightningArrowEntity entity) {

        return RES_LIGHTNING_ARROW;
    }

}
