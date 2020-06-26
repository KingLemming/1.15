package cofh.thermal.core.client.renderer.model;

import cofh.lib.client.renderer.model.BakedQuadRetextured;
import cofh.lib.client.renderer.model.ModelUtils;
import cofh.lib.util.ComparableItemStack;
import cofh.lib.util.control.IReconfigurable.SideConfig;
import cofh.thermal.core.client.gui.ThermalTextures;
import cofh.thermal.core.tileentity.MachineTileReconfigurable;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

import static cofh.lib.util.constants.NBTTags.*;
import static cofh.thermal.core.common.ThermalConfig.DEFAULT_MACHINE_SIDES_RAW;
import static net.minecraft.util.Direction.*;

public class MachineBakedModel extends BakedModelWrapper<IBakedModel> implements IDynamicBakedModel {

    private static final Int2ObjectMap<BakedQuad[]> BLOCK_QUAD_CACHE = new Int2ObjectOpenHashMap<>();
    private static final Int2ObjectMap<BakedQuad[]> ITEM_QUAD_CACHE = new Int2ObjectOpenHashMap<>();
    private static final Map<List<Integer>, IBakedModel> MODEL_CACHE = new Object2ObjectOpenHashMap<>();

    public static void clearCache() {

        BLOCK_QUAD_CACHE.clear();
        ITEM_QUAD_CACHE.clear();
        MODEL_CACHE.clear();
    }

    public MachineBakedModel(IBakedModel originalModel) {

        super(originalModel);
    }

    @Override
    @Nonnull
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {

        List<BakedQuad> quads = new ArrayList<>(originalModel.getQuads(state, side, rand));
        if (quads.isEmpty()) {
            return quads;
        }
        byte[] sideConfigRaw = extraData.getData(MachineTileReconfigurable.SIDES);
        int configHash = Arrays.hashCode(sideConfigRaw);

        if (sideConfigRaw == null || side == null) {
            // This shouldn't happen, but playing it safe.
            return quads;
        }
        BakedQuad[] cachedQuads = BLOCK_QUAD_CACHE.get(configHash);

        if (cachedQuads == null || cachedQuads.length < 6) {
            cachedQuads = new BakedQuad[6];
        }
        int sideIndex = side.getIndex();

        if (cachedQuads[sideIndex] == null) {
            cachedQuads[sideIndex] = new BakedQuadRetextured(quads.get(0), getTextureRaw(sideConfigRaw[sideIndex]));
            BLOCK_QUAD_CACHE.put(configHash, cachedQuads);
        }
        quads.add(cachedQuads[sideIndex]);
        return quads;
    }

    @Override
    public ItemOverrideList getOverrides() {

        return overrideList;
    }

    // region HELPERS
    static TextureAtlasSprite getTexture(SideConfig[] config, int side) {

        return getTexture(config[side]);
    }

    static TextureAtlasSprite getTexture(SideConfig side) {

        switch (side) {
            case SIDE_INPUT:
                return ThermalTextures.MACHINE_CONFIG_INPUT;
            case SIDE_OUTPUT:
                return ThermalTextures.MACHINE_CONFIG_OUTPUT;
            case SIDE_BOTH:
                return ThermalTextures.MACHINE_CONFIG_BOTH;
            case SIDE_ACCESSIBLE:
                return ThermalTextures.MACHINE_CONFIG_ACCESSIBLE;
            default:
                return ThermalTextures.MACHINE_CONFIG_NONE;
        }
    }

    static TextureAtlasSprite getTextureRaw(byte[] config, int side) {

        return getTextureRaw(config[side]);
    }

    static TextureAtlasSprite getTextureRaw(byte side) {

        switch (side) {
            case 1:
                return ThermalTextures.MACHINE_CONFIG_INPUT;
            case 2:
                return ThermalTextures.MACHINE_CONFIG_OUTPUT;
            case 3:
                return ThermalTextures.MACHINE_CONFIG_BOTH;
            case 4:
                return ThermalTextures.MACHINE_CONFIG_ACCESSIBLE;
            default:
                return ThermalTextures.MACHINE_CONFIG_NONE;
        }
    }

    static byte[] getSideConfigRaw(CompoundNBT tag) {

        if (tag == null) {
            return DEFAULT_MACHINE_SIDES_RAW;
        }
        byte[] ret = tag.getCompound(TAG_SIDE_CONFIG).getByteArray(TAG_SIDES);
        return ret.length == 0 ? DEFAULT_MACHINE_SIDES_RAW : ret;
    }
    // endregion

    private final ItemOverrideList overrideList = new ItemOverrideList() {

        @Nullable
        @Override
        public IBakedModel getModelWithOverrides(IBakedModel model, ItemStack stack, @Nullable World worldIn, @Nullable LivingEntity entityIn) {

            CompoundNBT tag = stack.getChildTag(TAG_BLOCK_ENTITY);
            byte[] sideConfigRaw = getSideConfigRaw(tag);
            int itemHash = new ComparableItemStack(stack).hashCode();
            int configHash = Arrays.hashCode(sideConfigRaw);

            IBakedModel ret = MODEL_CACHE.get(Arrays.asList(itemHash, configHash));
            if (ret == null) {
                ModelUtils.WrappedBakedModelBuilder builder = new ModelUtils.WrappedBakedModelBuilder(model);
                BakedQuad[] cachedQuads = ITEM_QUAD_CACHE.get(configHash);
                if (cachedQuads == null || cachedQuads.length < 6) {
                    cachedQuads = new BakedQuad[6];

                    cachedQuads[0] = new BakedQuadRetextured(builder.getQuads(DOWN).get(0), MachineBakedModel.getTextureRaw(sideConfigRaw[0]));
                    cachedQuads[1] = new BakedQuadRetextured(builder.getQuads(UP).get(0), MachineBakedModel.getTextureRaw(sideConfigRaw[1]));
                    cachedQuads[2] = new BakedQuadRetextured(builder.getQuads(NORTH).get(0), MachineBakedModel.getTextureRaw(sideConfigRaw[2]));
                    cachedQuads[3] = new BakedQuadRetextured(builder.getQuads(SOUTH).get(0), MachineBakedModel.getTextureRaw(sideConfigRaw[3]));
                    cachedQuads[4] = new BakedQuadRetextured(builder.getQuads(WEST).get(0), MachineBakedModel.getTextureRaw(sideConfigRaw[4]));
                    cachedQuads[5] = new BakedQuadRetextured(builder.getQuads(EAST).get(0), MachineBakedModel.getTextureRaw(sideConfigRaw[5]));
                    ITEM_QUAD_CACHE.put(configHash, cachedQuads);
                }
                builder.addFaceQuad(DOWN, cachedQuads[0]);
                builder.addFaceQuad(UP, cachedQuads[1]);
                builder.addFaceQuad(NORTH, cachedQuads[2]);
                builder.addFaceQuad(SOUTH, cachedQuads[3]);
                builder.addFaceQuad(WEST, cachedQuads[4]);
                builder.addFaceQuad(EAST, cachedQuads[5]);

                ret = builder.build();
                MODEL_CACHE.put(Arrays.asList(itemHash, configHash), ret);
            }
            return ret;
        }
    };

}
