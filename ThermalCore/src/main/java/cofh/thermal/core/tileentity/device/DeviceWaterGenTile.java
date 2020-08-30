package cofh.thermal.core.tileentity.device;

import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.fluid.FluidStorageInfinite;
import cofh.lib.util.StorageGroup;
import cofh.thermal.core.tileentity.ThermalTileBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.wrapper.EmptyHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.OUTPUT;
import static cofh.lib.util.constants.Constants.TANK_LARGE;
import static cofh.thermal.core.init.TCoreReferences.DEVICE_WATER_GEN_TILE;

public class DeviceWaterGenTile extends ThermalTileBase {

    protected byte adjWaterSource;

    protected boolean valid;

    protected FluidStorageCoFH tank = new FluidStorageInfinite(TANK_LARGE, e -> false);

    public DeviceWaterGenTile() {

        super(DEVICE_WATER_GEN_TILE);

        tankInv.addTank(tank, OUTPUT);
    }

    protected void updateValidity() {

        if (world == null || !world.isAreaLoaded(pos, 1)) {
            return;
        }
        adjWaterSource = 0;
        valid = false;

        BlockPos[] cardinals = new BlockPos[]{
                pos.north(),
                pos.south(),
                pos.west(),
                pos.east(),
        };
        for (BlockPos adj : cardinals) {
            IFluidState state = world.getFluidState(adj);
            if (state.getFluid().equals(Fluids.WATER)) {
                ++adjWaterSource;
            }
        }
        if (adjWaterSource > 1) {
            tank.setFluidStack(new FluidStack(Fluids.WATER, FluidAttributes.BUCKET_VOLUME / 2 * adjWaterSource));
            valid = true;
        } else {
            tank.clear();
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

        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && tankInv.hasTanks()) {
            return LazyOptional.of(() -> isActive ? tankInv.getHandler(StorageGroup.OUTPUT) : EmptyHandler.INSTANCE).cast();
        }
        return super.getCapability(cap, side);
    }
    // endregion
}
