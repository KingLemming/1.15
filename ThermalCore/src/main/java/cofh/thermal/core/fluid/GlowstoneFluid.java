package cofh.thermal.core.fluid;

import cofh.core.fluid.FluidCoFH;
import cofh.thermal.core.common.ThermalItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import java.util.function.Supplier;

import static cofh.thermal.core.ThermalCore.*;

public class GlowstoneFluid extends FluidCoFH {

    public static GlowstoneFluid create(String key) {

        return new GlowstoneFluid(key, "thermal:block/fluids/glowstone_still", "thermal:block/fluids/glowstone_flow");
    }

    protected GlowstoneFluid(String key, String stillTexture, String flowTexture) {

        stillFluid = FLUIDS.register(key, () -> new ForgeFlowingFluid.Source(properties));
        flowingFluid = FLUIDS.register(flowing(key), () -> new ForgeFlowingFluid.Flowing(properties));

        block = BLOCKS.register(key, () -> new GlowstoneFluidBlock(stillFluid, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));
        bucket = ITEMS.register(bucket(key), () -> new BucketItem(stillFluid, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ThermalItemGroups.THERMAL_TOOLS).rarity(Rarity.UNCOMMON)));

        properties = new ForgeFlowingFluid.Properties(stillFluid, flowingFluid, FluidAttributes.builder(new ResourceLocation(stillTexture), new ResourceLocation(flowTexture)).luminosity(15).density(-500).viscosity(100).rarity(Rarity.UNCOMMON)).bucket(bucket).block(block).levelDecreasePerBlock(4);
    }

    public static class GlowstoneFluidBlock extends FlowingFluidBlock {

        public GlowstoneFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {

            super(supplier, properties);
        }

    }

}
