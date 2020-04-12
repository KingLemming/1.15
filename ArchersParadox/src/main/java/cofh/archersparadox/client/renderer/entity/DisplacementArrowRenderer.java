package cofh.archersparadox.client.renderer.entity;

import cofh.archersparadox.entity.projectile.DisplacementArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DisplacementArrowRenderer extends ArrowRenderer<DisplacementArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("archers_paradox:textures/entity/projectiles/displacement_arrow.png");

    public DisplacementArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(DisplacementArrowEntity entity) {

        return TEXTURE;
    }

}
