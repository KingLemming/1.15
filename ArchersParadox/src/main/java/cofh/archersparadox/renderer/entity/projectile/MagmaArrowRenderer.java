package cofh.archersparadox.renderer.entity.projectile;

import cofh.archersparadox.entity.projectile.MagmaArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagmaArrowRenderer extends ArrowRenderer<MagmaArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("archers_paradox:textures/entity/projectiles/magma_arrow.png");

    public MagmaArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(MagmaArrowEntity entity) {

        return TEXTURE;
    }

}
