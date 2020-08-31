package cofh.core.client.renderer.model;

import cofh.core.util.helpers.MathHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelUtils {

    private ModelUtils() {

    }

    @OnlyIn(Dist.CLIENT)
    public static class WrappedBakedModelBuilder {

        private final List<BakedQuad> builderGeneralQuads = Lists.newArrayList();
        private final Map<Direction, List<BakedQuad>> builderFaceQuads = Maps.newEnumMap(Direction.class);
        private final ItemOverrideList builderItemOverrideList;
        private final boolean builderAmbientOcclusion;
        private TextureAtlasSprite builderTexture;
        private final boolean field_230187_f_;
        private final boolean builderGui3d;
        private final ItemCameraTransforms builderCameraTransforms;

        public WrappedBakedModelBuilder(IBakedModel model) {

            for (Direction direction : Direction.values()) {
                this.builderFaceQuads.put(direction, new ArrayList<>(model.getQuads(null, direction, MathHelper.RANDOM)));
            }
            builderItemOverrideList = model.getOverrides();
            builderAmbientOcclusion = model.isAmbientOcclusion();
            builderTexture = model.getParticleTexture();
            field_230187_f_ = model.func_230044_c_();
            builderGui3d = model.isGui3d();
            builderCameraTransforms = model.getItemCameraTransforms();
        }

        public WrappedBakedModelBuilder addQuads(Map<Direction, BakedQuad> quad) {

            return this;
        }

        public WrappedBakedModelBuilder addFaceQuad(Direction facing, BakedQuad quad) {

            this.builderFaceQuads.get(facing).add(quad);
            return this;
        }

        public WrappedBakedModelBuilder addRetexturedQuad(Direction facing, TextureAtlasSprite textureIn) {

            addFaceQuad(facing, new BakedQuadRetextured(getQuads(facing).get(0), textureIn));
            return this;
        }

        public WrappedBakedModelBuilder addGeneralQuad(BakedQuad quad) {

            this.builderGeneralQuads.add(quad);
            return this;
        }

        public List<BakedQuad> getQuads(Direction facing) {

            return facing == null ? builderGeneralQuads : builderFaceQuads.get(facing);
        }

        public WrappedBakedModelBuilder setTexture(TextureAtlasSprite texture) {

            this.builderTexture = texture;
            return this;
        }

        public IBakedModel build() {

            if (this.builderTexture == null) {
                throw new RuntimeException("Missing particle!");
            } else {
                return new SimpleBakedModel(this.builderGeneralQuads, this.builderFaceQuads, this.builderAmbientOcclusion, this.field_230187_f_, this.builderGui3d, this.builderTexture, this.builderCameraTransforms, this.builderItemOverrideList);
            }
        }

    }

}
