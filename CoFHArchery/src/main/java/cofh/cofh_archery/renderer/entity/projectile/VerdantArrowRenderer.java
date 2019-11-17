package cofh.cofh_archery.renderer.entity.projectile;

import cofh.cofh_archery.entity.projectile.VerdantArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VerdantArrowRenderer extends ArrowRenderer<VerdantArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("cofh_archery:textures/entity/projectiles/verdant_arrow.png");

    public VerdantArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(VerdantArrowEntity entity) {

        return TEXTURE;
    }

}
