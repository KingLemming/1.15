package cofh.cofh_archery.renderer.entity.projectile;

import cofh.cofh_archery.entity.projectile.SlimeArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlimeArrowRenderer extends ArrowRenderer<SlimeArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("cofh_archery:textures/entity/projectiles/slime_arrow.png");

    public SlimeArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(SlimeArrowEntity entity) {

        return TEXTURE;
    }

}
