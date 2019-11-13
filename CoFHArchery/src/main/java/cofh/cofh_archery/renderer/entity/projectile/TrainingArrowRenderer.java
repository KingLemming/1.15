package cofh.cofh_archery.renderer.entity.projectile;

import cofh.cofh_archery.entity.projectile.TrainingArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TrainingArrowRenderer extends ArrowRenderer<TrainingArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("cofh_archery:textures/entity/projectiles/training_arrow.png");

    public TrainingArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(TrainingArrowEntity entity) {

        return TEXTURE;
    }

}
