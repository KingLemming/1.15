package cofh.cofh_archery.renderer.entity.projectile;

import cofh.cofh_archery.entity.projectile.FrostArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static cofh.lib.util.constants.Constants.ID_COFH_ARCHERY;
import static cofh.lib.util.constants.Constants.ID_MINECRAFT;

@OnlyIn(Dist.CLIENT)
public class FrostArrowRenderer extends ArrowRenderer<FrostArrowEntity> {

    public static final ResourceLocation ARROW = new ResourceLocation(ID_MINECRAFT + ":textures/entity/projectiles/arrow.png");
    public static final ResourceLocation TEXTURE = new ResourceLocation(ID_COFH_ARCHERY + ":textures/entity/projectiles/frost_arrow.png");

    public FrostArrowRenderer(EntityRendererManager manager) {

        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(FrostArrowEntity entity) {

        return entity.discharged ? ARROW : TEXTURE;
    }

}
