package cofh.test.renderer.entity.projectile;

import cofh.test.entity.projectile.RedstoneArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RedstoneArrowRenderer extends ArrowRenderer<RedstoneArrowEntity> {

    public static final ResourceLocation RES_REDSTONE_ARROW = new ResourceLocation("cofh_test:textures/entity/projectiles/redstone_arrow_entity.png");

    public RedstoneArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    protected ResourceLocation getEntityTexture(RedstoneArrowEntity entity) {

        return RES_REDSTONE_ARROW;
    }

}
