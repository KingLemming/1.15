package cofh.thermal.core.client.renderer.model;

import cofh.core.client.renderer.model.BakedQuadRetextured;
import cofh.core.util.helpers.FluidHelper;
import cofh.core.util.helpers.RenderHelper;
import cofh.thermal.core.tileentity.ThermalTileBase;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

import static cofh.core.util.constants.Constants.BUCKET_VOLUME;

public class UnderlayBakedModel extends BakedModelWrapper<IBakedModel> implements IDynamicBakedModel {

    protected static final IdentityHashMap<FluidCacheWrapper, BakedQuad[]> FLUID_QUAD_CACHE = new IdentityHashMap<>();
    protected static final IdentityHashMap<BlockState, BakedQuad[]> UNDERLAY_QUAD_CACHE = new IdentityHashMap<>();

    public static void clearCache() {

        FLUID_QUAD_CACHE.clear();
        UNDERLAY_QUAD_CACHE.clear();
    }

    public UnderlayBakedModel(IBakedModel originalModel) {

        super(originalModel);
    }

    @Override
    @Nonnull
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {

        LinkedList<BakedQuad> quads = new LinkedList<>(originalModel.getQuads(state, side, rand, extraData));
        if (side == null || quads.isEmpty()) {
            return quads;
        }
        int sideIndex = side.getIndex();
        // FLUID
        if (extraData.hasProperty(ThermalTileBase.FLUID)) {
            FluidStack fluid = extraData.getData(ThermalTileBase.FLUID);
            if (fluid != null && !fluid.isEmpty()) {
                FluidCacheWrapper wrapper = new FluidCacheWrapper(state, fluid);
                BakedQuad[] cachedFluidQuads = FLUID_QUAD_CACHE.get(wrapper);
                if (cachedFluidQuads == null || cachedFluidQuads.length < 6) {
                    cachedFluidQuads = new BakedQuad[6];
                }
                if (cachedFluidQuads[sideIndex] == null) {
                    cachedFluidQuads[sideIndex] = new BakedQuadRetextured(RenderHelper.mulColor(quads.get(0), fluid.getFluid().getAttributes().getColor(fluid)), RenderHelper.getFluidTexture(fluid));
                    FLUID_QUAD_CACHE.put(wrapper, cachedFluidQuads);
                }
                quads.offerFirst(cachedFluidQuads[sideIndex]);
            }
        } else if (extraData.hasProperty(ThermalTileBase.UNDERLAY)) {
            ResourceLocation loc = extraData.getData(ThermalTileBase.UNDERLAY);
            BakedQuad[] cachedUnderlayQuads = UNDERLAY_QUAD_CACHE.get(state);
            if (cachedUnderlayQuads == null || cachedUnderlayQuads.length < 6) {
                cachedUnderlayQuads = new BakedQuad[6];
            }
            if (cachedUnderlayQuads[sideIndex] == null) {
                cachedUnderlayQuads[sideIndex] = new BakedQuadRetextured(quads.get(0), RenderHelper.getTexture(loc));
                UNDERLAY_QUAD_CACHE.put(state, cachedUnderlayQuads);
            }
            quads.offerFirst(cachedUnderlayQuads[sideIndex]);
        }
        return quads;
    }

    // region WRAPPER CLASS
    protected static class FluidCacheWrapper {

        BlockState state;
        FluidStack stack;

        FluidCacheWrapper(BlockState state, FluidStack stack) {

            this.state = state;
            this.stack = new FluidStack(stack, BUCKET_VOLUME);
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            FluidCacheWrapper that = (FluidCacheWrapper) o;
            return Objects.equals(state, that.state) && Objects.equals(stack, that.stack);
        }

        @Override
        public int hashCode() {

            return Objects.hash(state, FluidHelper.fluidHashcode(stack));
        }

    }
    // endregion
}
