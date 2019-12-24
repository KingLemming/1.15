package cofh.lib.block;

import cofh.core.util.ChatHelper;
import cofh.lib.tileentity.TileCoFH;
import cofh.lib.util.Utils;
import cofh.lib.util.helpers.FluidHelper;
import cofh.lib.util.helpers.SecurityHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.ArrayList;
import java.util.function.Supplier;

public abstract class TileBlockCoFH extends Block implements IDismantleable {

    protected final Supplier<? extends TileEntity> supplier;

    public TileBlockCoFH(Properties builder, Supplier<? extends TileEntity> supplier) {

        super(builder);
        this.supplier = supplier;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {

        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {

        return supplier.get();
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {

        if (Utils.isClientWorld(world)) {
            return false;
        }
        TileEntity tile = world.getTileEntity(pos);
        if (!(tile instanceof TileCoFH) || tile.isRemoved()) {
            return false;
        }
        if (!((TileCoFH) tile).canPlayerChange(player) && SecurityHelper.hasSecurity(tile)) {
            ChatHelper.sendIndexedChatMessageToPlayer(player, new TranslationTextComponent("chat.cofh.secure_warning", SecurityHelper.getOwnerName(tile)));
            return false;
        }
        if (onBlockActivatedDelegate(world, pos, state, player, hand, result)) {
            return true;
        }
        if (tile instanceof INamedContainerProvider) {
            NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tile, tile.getPos());
            return true;
        }
        return false;
    }

    protected boolean onBlockActivatedDelegate(World world, BlockPos pos, BlockState state, PlayerEntity player, Hand hand, BlockRayTraceResult result) {

        TileCoFH tile = (TileCoFH) world.getTileEntity(pos);
        if (tile == null || !tile.canPlayerChange(player)) {
            return false;
        }
        tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).map(handler -> FluidHelper.interactWithHandler(player.getHeldItem(hand), handler, player, hand));
        return false;
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {

        TileCoFH tile = (TileCoFH) worldIn.getTileEntity(pos);
        if (tile != null) {
            tile.neighborChanged();
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {

        TileEntity tile = worldIn.getTileEntity(pos);
        return tile instanceof TileCoFH ? ((TileCoFH) tile).getComparatorInputOverride() : super.getComparatorInputOverride(blockState, worldIn, pos);
    }

    // region IDismantleable
    @Override
    public ArrayList<ItemStack> dismantleBlock(World world, BlockPos pos, BlockState state, PlayerEntity player, boolean returnDrops) {

        return null;
    }

    @Override
    public boolean canDismantle(World world, BlockPos pos, BlockState state, PlayerEntity player) {

        return false;
    }
    // endregion
}
