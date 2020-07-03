package cofh.thermal.core.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;

@OnlyIn(Dist.CLIENT)
public class BlizzModel<T extends Entity> extends SegmentedModel<T> {

    private final ModelRenderer[] blizzSticks;
    private final ModelRenderer blizzHead = new ModelRenderer(this, 0, 0);
    private final ImmutableList<ModelRenderer> field_228242_f_;

    public BlizzModel() {

        this.blizzHead.addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F);
        this.blizzSticks = new ModelRenderer[12];

        for (int i = 0; i < this.blizzSticks.length; ++i) {
            this.blizzSticks[i] = new ModelRenderer(this, 0, 16);
            this.blizzSticks[i].addBox(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F);
        }

        Builder<ModelRenderer> builder = ImmutableList.builder();
        builder.add(this.blizzHead);
        builder.addAll(Arrays.asList(this.blizzSticks));
        this.field_228242_f_ = builder.build();
    }

    public Iterable<ModelRenderer> getParts() {

        return this.field_228242_f_;
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        float f = ageInTicks * (float) Math.PI * -0.1F;

        for (int i = 0; i < 4; ++i) {
            this.blizzSticks[i].rotationPointY = -2.0F + MathHelper.cos(((float) (i * 2) + ageInTicks) * 0.25F);
            this.blizzSticks[i].rotationPointX = MathHelper.cos(f) * 9.0F;
            this.blizzSticks[i].rotationPointZ = MathHelper.sin(f) * 9.0F;
            ++f;
        }

        f = ((float) Math.PI / 4F) + ageInTicks * (float) Math.PI * 0.03F;

        for (int j = 4; j < 8; ++j) {
            this.blizzSticks[j].rotationPointY = 2.0F + MathHelper.cos(((float) (j * 2) + ageInTicks) * 0.25F);
            this.blizzSticks[j].rotationPointX = MathHelper.cos(f) * 7.0F;
            this.blizzSticks[j].rotationPointZ = MathHelper.sin(f) * 7.0F;
            ++f;
        }

        f = 0.47123894F + ageInTicks * (float) Math.PI * -0.05F;

        for (int k = 8; k < 12; ++k) {
            this.blizzSticks[k].rotationPointY = 11.0F + MathHelper.cos(((float) k * 1.5F + ageInTicks) * 0.5F);
            this.blizzSticks[k].rotationPointX = MathHelper.cos(f) * 5.0F;
            this.blizzSticks[k].rotationPointZ = MathHelper.sin(f) * 5.0F;
            ++f;
        }

        this.blizzHead.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.blizzHead.rotateAngleX = headPitch * ((float) Math.PI / 180F);
    }

}