package cofh.archersparadox.renderer.entity.projectile;

import cofh.archersparadox.entity.projectile.LightningArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static cofh.lib.util.constants.Constants.ID_ARCHERS_PARADOX;
import static cofh.lib.util.constants.Constants.ID_MINECRAFT;

@OnlyIn(Dist.CLIENT)
public class LightningArrowRenderer extends ArrowRenderer<LightningArrowEntity> {

    public static final ResourceLocation ARROW = new ResourceLocation(ID_MINECRAFT + ":textures/entity/projectiles/arrow.png");
    public static final ResourceLocation TEXTURE = new ResourceLocation(ID_ARCHERS_PARADOX + ":textures/entity/projectiles/lightning_arrow.png");

    public LightningArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(LightningArrowEntity entity) {

        return entity.discharged ? ARROW : TEXTURE;
    }

}
