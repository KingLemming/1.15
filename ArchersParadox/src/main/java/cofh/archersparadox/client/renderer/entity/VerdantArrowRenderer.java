package cofh.archersparadox.client.renderer.entity;

import cofh.archersparadox.entity.projectile.VerdantArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VerdantArrowRenderer extends ArrowRenderer<VerdantArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("archers_paradox:textures/entity/projectiles/verdant_arrow.png");

    public VerdantArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(VerdantArrowEntity entity) {

        return TEXTURE;
    }

}
