package cofh.thermal.core.client.model;

import cofh.lib.client.model.BakedQuadRetextured;
import cofh.lib.util.control.IReconfigurable;
import cofh.thermal.core.client.gui.ThermalTextures;
import cofh.thermal.core.tileentity.MachineTileReconfigurable;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static cofh.lib.util.control.IReconfigurable.SideConfig.SIDE_NONE;

public class MachineBakedModel extends BakedModelWrapper<IBakedModel> implements IDynamicBakedModel {

    IReconfigurable.SideConfig[] DEFAULT_SIDES = new IReconfigurable.SideConfig[]{SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE, SIDE_NONE};

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
        IReconfigurable.SideConfig[] config = extraData.getData(MachineTileReconfigurable.SIDES);
        if (config == null || config.length < 6) {
            config = DEFAULT_SIDES;
        }
        if (side != null) {
            quads.add(new BakedQuadRetextured(quads.get(0), getTexture(config, side.ordinal())));
        }
        return quads;
    }

    // region HELPERS
    private TextureAtlasSprite getTexture(IReconfigurable.SideConfig[] config, int side) {

        return getTexture(config[side]);
    }

    private TextureAtlasSprite getTexture(IReconfigurable.SideConfig side) {

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
    // endregion
}
