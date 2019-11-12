package cofh.test.renderer.entity.projectile;

import cofh.test.entity.projectile.TrainingArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TrainingArrowRenderer extends ArrowRenderer<TrainingArrowEntity> {

    public static final ResourceLocation RES_TRAINING_ARROW = new ResourceLocation("cofh_test:textures/entity/projectiles/training_arrow_entity.png");

    public TrainingArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    protected ResourceLocation getEntityTexture(TrainingArrowEntity entity) {

        return RES_TRAINING_ARROW;
    }

}
