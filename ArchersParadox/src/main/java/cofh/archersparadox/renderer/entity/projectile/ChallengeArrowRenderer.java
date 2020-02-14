package cofh.archersparadox.renderer.entity.projectile;

import cofh.archersparadox.entity.projectile.ChallengeArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChallengeArrowRenderer extends ArrowRenderer<ChallengeArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("archers_paradox:textures/entity/projectiles/challenge_arrow.png");

    public ChallengeArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(ChallengeArrowEntity entity) {

        return entity.discharged ? TrainingArrowRenderer.TEXTURE : TEXTURE;
    }

}
