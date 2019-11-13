package cofh.cofh_archery.renderer.entity.projectile;

import cofh.cofh_archery.entity.projectile.RedstoneArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RedstoneArrowRenderer extends ArrowRenderer<RedstoneArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("cofh_archery:textures/entity/projectiles/redstone_arrow.png");

    public RedstoneArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(RedstoneArrowEntity entity) {

        return TEXTURE;
    }

}
