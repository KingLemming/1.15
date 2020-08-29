package cofh.thermal.core.client.renderer.model;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraftforge.client.model.data.IDynamicBakedModel;

import java.util.List;
import java.util.Map;

public class DynamoBakedModel extends UnderlayBakedModel implements IDynamicBakedModel {

    protected static final Int2ObjectMap<BakedQuad[]> COIL_QUAD_CACHE = new Int2ObjectOpenHashMap<>();

    protected static final Int2ObjectMap<BakedQuad[]> ITEM_QUAD_CACHE = new Int2ObjectOpenHashMap<>();
    protected static final Map<List<Integer>, IBakedModel> MODEL_CACHE = new Object2ObjectOpenHashMap<>();

    public static void clearCache() {

        COIL_QUAD_CACHE.clear();

        ITEM_QUAD_CACHE.clear();
        MODEL_CACHE.clear();
    }

    public DynamoBakedModel(IBakedModel originalModel) {

        super(originalModel);
    }

    //    @Override
    //    @Nonnull
    //    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
    //
    //        List<BakedQuad> quads = super.getQuads(state, side, rand, extraData);
    //        if (side == null || quads.isEmpty()) {
    //            return quads;
    //        }
    //        byte[] sideConfigRaw = extraData.getData(ThermalTileBase.SIDES);
    //        if (sideConfigRaw == null) {
    //            // This shouldn't happen, but playing it safe.
    //            return quads;
    //        }
    //        int sideIndex = side.getIndex();
    //        // SIDES
    //        int configHash = Arrays.hashCode(sideConfigRaw);
    //        BakedQuad[] cachedSideQuads = COIL_QUAD_CACHE.get(configHash);
    //        if (cachedSideQuads == null || cachedSideQuads.length < 6) {
    //            cachedSideQuads = new BakedQuad[6];
    //        }
    //        if (cachedSideQuads[sideIndex] == null) {
    //            cachedSideQuads[sideIndex] = new BakedQuadRetextured(quads.get(0), getTextureRaw(sideConfigRaw[sideIndex]));
    //            COIL_QUAD_CACHE.put(configHash, cachedSideQuads);
    //        }
    //        quads.add(cachedSideQuads[sideIndex]);
    //        return quads;
    //    }
    //
    //    @Override
    //    public ItemOverrideList getOverrides() {
    //
    //        return overrideList;
    //    }
    //
    //    // region HELPERS
    //    static TextureAtlasSprite getTexture(SideConfig[] config, int side) {
    //
    //        return getTexture(config[side]);
    //    }
    //
    //    static TextureAtlasSprite getTexture(SideConfig side) {
    //
    //        switch (side) {
    //            case SIDE_INPUT:
    //                return ThermalTextures.MACHINE_CONFIG_INPUT;
    //            case SIDE_OUTPUT:
    //                return ThermalTextures.MACHINE_CONFIG_OUTPUT;
    //            case SIDE_BOTH:
    //                return ThermalTextures.MACHINE_CONFIG_BOTH;
    //            case SIDE_ACCESSIBLE:
    //                return ThermalTextures.MACHINE_CONFIG_ACCESSIBLE;
    //            default:
    //                return ThermalTextures.MACHINE_CONFIG_NONE;
    //        }
    //    }
    //
    //    static TextureAtlasSprite getTextureRaw(byte[] config, int side) {
    //
    //        return getTextureRaw(config[side]);
    //    }
    //
    //    static TextureAtlasSprite getTextureRaw(byte side) {
    //
    //        switch (side) {
    //            case 1:
    //                return ThermalTextures.MACHINE_CONFIG_INPUT;
    //            case 2:
    //                return ThermalTextures.MACHINE_CONFIG_OUTPUT;
    //            case 3:
    //                return ThermalTextures.MACHINE_CONFIG_BOTH;
    //            case 4:
    //                return ThermalTextures.MACHINE_CONFIG_ACCESSIBLE;
    //            default:
    //                return ThermalTextures.MACHINE_CONFIG_NONE;
    //        }
    //    }
    //
    //    static byte[] getSideConfigRaw(CompoundNBT tag) {
    //
    //        if (tag == null) {
    //            return DEFAULT_MACHINE_SIDES_RAW;
    //        }
    //        byte[] ret = tag.getByteArray(TAG_SIDES);
    //        return ret.length == 0 ? DEFAULT_MACHINE_SIDES_RAW : ret;
    //    }
    //    // endregion
    //
    //    private final ItemOverrideList overrideList = new ItemOverrideList() {
    //
    //        @Nullable
    //        @Override
    //        public IBakedModel getModelWithOverrides(IBakedModel model, ItemStack stack, @Nullable World worldIn, @Nullable LivingEntity entityIn) {
    //
    //            CompoundNBT tag = stack.getChildTag(TAG_BLOCK_ENTITY);
    //            byte[] sideConfigRaw = getSideConfigRaw(tag);
    //            int itemHash = new ComparableItemStack(stack).hashCode();
    //            int configHash = Arrays.hashCode(sideConfigRaw);
    //
    //            IBakedModel ret = MODEL_CACHE.get(Arrays.asList(itemHash, configHash));
    //            if (ret == null) {
    //                ModelUtils.WrappedBakedModelBuilder builder = new ModelUtils.WrappedBakedModelBuilder(model);
    //                BakedQuad[] cachedQuads = ITEM_QUAD_CACHE.get(configHash);
    //                if (cachedQuads == null || cachedQuads.length < 6) {
    //                    cachedQuads = new BakedQuad[6];
    //
    //                    cachedQuads[0] = new BakedQuadRetextured(builder.getQuads(DOWN).get(0), getTextureRaw(sideConfigRaw[0]));
    //                    cachedQuads[1] = new BakedQuadRetextured(builder.getQuads(UP).get(0), getTextureRaw(sideConfigRaw[1]));
    //                    cachedQuads[2] = new BakedQuadRetextured(builder.getQuads(NORTH).get(0), getTextureRaw(sideConfigRaw[2]));
    //                    cachedQuads[3] = new BakedQuadRetextured(builder.getQuads(SOUTH).get(0), getTextureRaw(sideConfigRaw[3]));
    //                    cachedQuads[4] = new BakedQuadRetextured(builder.getQuads(WEST).get(0), getTextureRaw(sideConfigRaw[4]));
    //                    cachedQuads[5] = new BakedQuadRetextured(builder.getQuads(EAST).get(0), getTextureRaw(sideConfigRaw[5]));
    //                    ITEM_QUAD_CACHE.put(configHash, cachedQuads);
    //                }
    //                builder.addFaceQuad(DOWN, cachedQuads[0]);
    //                builder.addFaceQuad(UP, cachedQuads[1]);
    //                builder.addFaceQuad(NORTH, cachedQuads[2]);
    //                builder.addFaceQuad(SOUTH, cachedQuads[3]);
    //                builder.addFaceQuad(WEST, cachedQuads[4]);
    //                builder.addFaceQuad(EAST, cachedQuads[5]);
    //
    //                ret = builder.build();
    //                MODEL_CACHE.put(Arrays.asList(itemHash, configHash), ret);
    //            }
    //            return ret;
    //        }
    //    };

}
