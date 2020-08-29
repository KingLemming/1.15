package cofh.thermal.expansion.tileentity.device;

import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.inventory.ItemStorageInfinite;
import cofh.thermal.core.tileentity.ThermalTileBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.EmptyHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.OUTPUT;
import static cofh.lib.util.helpers.ItemHelper.cloneStack;
import static cofh.thermal.expansion.init.TExpReferences.DEVICE_ROCK_GEN_TILE;

public class DeviceRockGenTile extends ThermalTileBase {

    protected static ItemStack COBBLESTONE = new ItemStack(Blocks.COBBLESTONE);
    protected static ItemStack BASALT = new ItemStack(Blocks.OBSIDIAN);

    protected byte adjLavaSource;
    protected byte adjWaterSource;

    protected byte adjBlueIce;
    protected byte adjSoulSand;

    protected boolean valid;

    protected ItemStorageCoFH slot = new ItemStorageInfinite(e -> false);

    public DeviceRockGenTile() {

        super(DEVICE_ROCK_GEN_TILE);

        inventory.addSlot(slot, OUTPUT);
    }

    protected void updateValidity() {

        if (world == null || !world.isAreaLoaded(pos, 1)) {
            return;
        }
        adjLavaSource = 0;
        adjWaterSource = 0;
        adjBlueIce = 0;
        adjSoulSand = 0;
        valid = false;

        BlockPos[] cardinals = new BlockPos[]{
                pos.north(),
                pos.south(),
                pos.west(),
                pos.east(),
        };
        for (BlockPos adj : cardinals) {
            BlockState blockState = world.getBlockState(adj);
            IFluidState fluidState = world.getFluidState(adj);
            if (fluidState.getFluid().equals(Fluids.LAVA)) {
                ++adjLavaSource;
            }
            if (fluidState.getFluid().equals(Fluids.WATER)) {
                ++adjWaterSource;
            }
            if (blockState.getBlock().equals(Blocks.BLUE_ICE)) {
                ++adjBlueIce;
            }
        }
        // TODO: Add in 1.16.
        //        BlockPos below = pos.down();
        //        BlockState blockState = world.getBlockState(below);
        //        if (blockState.getBlock().equals(Blocks.SOUL_SAND)) {
        //            ++adjSoulSand;
        //        }
        //        if (adjSoulSand > 0 && adjLavaSource > 0 && adjBlueIce > 0) {
        //            slot.setItemStack(cloneStack(BASALT, Math.min(adjLavaSource, adjBlueIce)));
        //            valid = true;
        //        } else
        if (adjLavaSource > 0 && adjWaterSource > 0) {
            slot.setItemStack(cloneStack(COBBLESTONE, Math.min(adjLavaSource, adjWaterSource)));
            valid = true;
        } else {
            slot.clear();
        }
    }

    protected void updateActiveState() {

        updateValidity();
        boolean curActive = isActive;
        isActive = redstoneControl().getState() && valid;
        updateActiveState(curActive);
    }

    @Override
    public void neighborChanged(Block blockIn, BlockPos fromPos) {

        super.neighborChanged(blockIn, fromPos);
        updateActiveState();
    }

    @Override
    public boolean onActivatedDelegate(World world, BlockPos pos, BlockState state, PlayerEntity player, Hand hand, BlockRayTraceResult result) {

        return player.inventory.addItemStackToInventory(cloneStack(slot.getItemStack()));
    }

    @Override
    public void onPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

        super.onPlacedBy(worldIn, pos, state, placer, stack);
        updateActiveState();
    }

    @Override
    public boolean hasGui() {

        return false;
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return null;
    }

    // region CAPABILITIES
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && inventory.hasSlots()) {
            return LazyOptional.of(() -> isActive ? inventory.getHandler(OUTPUT) : EmptyHandler.INSTANCE).cast();
        }
        return super.getCapability(cap, side);
    }
    // endregion

}
