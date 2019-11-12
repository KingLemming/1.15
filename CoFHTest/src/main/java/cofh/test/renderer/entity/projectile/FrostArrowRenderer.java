package cofh.test.renderer.entity.projectile;

import cofh.test.entity.projectile.FrostArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FrostArrowRenderer extends ArrowRenderer<FrostArrowEntity> {

    public static final ResourceLocation RES_FROST_ARROW = new ResourceLocation("cofh_test:textures/entity/projectiles/frost_arrow_entity.png");

    public FrostArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    protected ResourceLocation getEntityTexture(FrostArrowEntity entity) {

        return RES_FROST_ARROW;
    }

}
