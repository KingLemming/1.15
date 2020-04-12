package cofh.archersparadox.client.renderer.entity;

import cofh.archersparadox.entity.projectile.ShulkerArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShulkerArrowRenderer extends ArrowRenderer<ShulkerArrowEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("archers_paradox:textures/entity/projectiles/shulker_arrow.png");

    public ShulkerArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(ShulkerArrowEntity entity) {

        return TEXTURE;
    }

}
