package cofh.thermal.core.init;

import cofh.lib.block.storage.MetalStorageBlock;
import cofh.lib.item.BlockItemCoFH;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import static cofh.thermal.core.ThermalSeries.BLOCKS;
import static cofh.thermal.core.ThermalSeries.ITEMS;
import static net.minecraft.block.Block.Properties.create;

public class BlocksTSeries {

    private BlocksTSeries() {

    }

    public static void register() {

        blockSignalum = BLOCKS.register(ID_SIGNALUM_BLOCK, () -> new MetalStorageBlock(create(Material.IRON, MaterialColor.RED).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE).lightValue(7)) {

            @Override
            public boolean canProvidePower(BlockState state) {

                return true;
            }

            @Override
            public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {

                return 15;
            }
        });
        blockLumium = BLOCKS.register(ID_LUMIUM_BLOCK, () -> new MetalStorageBlock(create(Material.IRON, MaterialColor.YELLOW).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE).lightValue(15)));
        blockEnderium = BLOCKS.register(ID_ENDERIUM_BLOCK, () -> new MetalStorageBlock(create(Material.IRON, MaterialColor.CYAN).hardnessAndResistance(25.0F, 30.0F).sound(SoundType.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE).lightValue(3)));

        ItemGroup group = ItemGroupsTSeries.THERMAL_BLOCKS;

        ITEMS.register(ID_SIGNALUM_BLOCK, () -> new BlockItemCoFH(blockSignalum.get(), new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register(ID_LUMIUM_BLOCK, () -> new BlockItemCoFH(blockLumium.get(), new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
        ITEMS.register(ID_ENDERIUM_BLOCK, () -> new BlockItemCoFH(blockEnderium.get(), new Item.Properties().group(group).rarity(Rarity.UNCOMMON)));
    }

    private static RegistryObject<Block> blockSignalum;
    private static RegistryObject<Block> blockLumium;
    private static RegistryObject<Block> blockEnderium;

    private static final String ID_SIGNALUM_BLOCK = "block_signalum";
    private static final String ID_LUMIUM_BLOCK = "block_lumium";
    private static final String ID_ENDERIUM_BLOCK = "block_enderium";

}
