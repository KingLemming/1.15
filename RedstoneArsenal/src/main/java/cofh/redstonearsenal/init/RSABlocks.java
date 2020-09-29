package cofh.redstonearsenal.init;

import cofh.core.block.storage.MetalStorageBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

import static cofh.redstonearsenal.RedstoneArsenal.BLOCKS;
import static net.minecraft.block.Block.Properties.create;

public class RSABlocks {

    private RSABlocks() {

    }

    public static void register() {

        BLOCKS.register("flux_metal_block", () -> new MetalStorageBlock(create(Material.IRON, MaterialColor.YELLOW)));
        BLOCKS.register("flux_gem_block", () -> new MetalStorageBlock(create(Material.IRON, MaterialColor.RED)));
    }

}
