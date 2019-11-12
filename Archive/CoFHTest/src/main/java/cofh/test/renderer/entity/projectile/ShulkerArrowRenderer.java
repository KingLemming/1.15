package cofh.test.renderer.entity.projectile;

import cofh.test.entity.projectile.ShulkerArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShulkerArrowRenderer extends ArrowRenderer<ShulkerArrowEntity> {

    public static final ResourceLocation RES_SHULKER_ARROW = new ResourceLocation("cofh_test:textures/entity/projectiles/shulker_arrow_entity.png");

    public ShulkerArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    protected ResourceLocation getEntityTexture(ShulkerArrowEntity entity) {

        return RES_SHULKER_ARROW;
    }

}
