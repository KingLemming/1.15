package cofh.thermal.cultivation.tileentity;

import cofh.core.network.packet.client.TileControlPacket;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.tileentity.ThermalTileBase;
import cofh.thermal.cultivation.inventory.container.DeviceHiveExtractorContainer;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.BeehiveTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.OUTPUT;
import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.lib.util.helpers.ItemHelper.cloneStack;
import static cofh.lib.util.references.CoreReferences.FLUID_HONEY;
import static cofh.thermal.cultivation.init.TCulReferences.DEVICE_HIVE_EXTRACTOR_TILE;

public class DeviceHiveExtractorTile extends ThermalTileBase {

    private static final int COMB_AMOUNT = 2;
    private static final int HONEY_AMOUNT = 250;

    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();
    protected FluidStorageCoFH outputTank = new FluidStorageCoFH(TANK_MEDIUM);

    public DeviceHiveExtractorTile() {

        super(DEVICE_HIVE_EXTRACTOR_TILE);

        inventory.addSlot(outputSlot, OUTPUT);

        tankInv.addTank(outputTank, OUTPUT);
    }

    protected void extractProducts(BlockPos above) {

        if (world == null) {
            return;
        }
        BlockState hive = world.getBlockState(above);
        Block hiveBlock = hive.getBlock();
        if (hiveBlock instanceof BeehiveBlock && BeehiveTileEntity.getHoneyLevel(hive) >= 5) {
            world.setBlockState(above, hive.with(BeehiveBlock.HONEY_LEVEL, 0), 3);
            if (outputSlot.isEmpty()) {
                outputSlot.setItemStack(cloneStack(Items.HONEYCOMB, COMB_AMOUNT));
            } else {
                outputSlot.modify(Math.min(COMB_AMOUNT, outputSlot.getSpace()));
            }
            if (outputTank.isEmpty()) {
                outputTank.setFluidStack(new FluidStack(FLUID_HONEY, HONEY_AMOUNT));
            } else {
                outputTank.modify(Math.min(HONEY_AMOUNT, outputTank.getSpace()));
            }
        }
    }

    protected void updateActiveState() {

        boolean curActive = isActive;
        isActive = redstoneControl().getState() && world.getBlockState(pos.up()).getBlock() instanceof BeehiveBlock;
        updateActiveState(curActive);
    }

    @Override
    public void neighborChanged(Block blockIn, BlockPos fromPos) {

        super.neighborChanged(blockIn, fromPos);

        if (fromPos.equals(pos.up())) {
            if (redstoneControl.getState()) {
                extractProducts(pos.up());
            }
        }
        updateActiveState();
    }

    @Override
    public void onPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

        super.onPlacedBy(worldIn, pos, state, placer, stack);
        if (redstoneControl.getState()) {
            extractProducts(pos.up());
        }
        updateActiveState();
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new DeviceHiveExtractorContainer(i, world, pos, inventory, player);
    }

    @Override
    public void handleControlPacket(PacketBuffer buffer) {

        super.handleControlPacket(buffer);

        if (redstoneControl.getState()) {
            extractProducts(pos.up());
        }
    }

    @Override
    public void handleStatePacket(PacketBuffer buffer) {

        super.handleStatePacket(buffer);

        if (redstoneControl.getState()) {
            extractProducts(pos.up());
        }
    }

    // region ITileCallback
    @Override
    public void onControlUpdate() {

        updateActiveState();

        TileControlPacket.sendToClient(this);
    }
    // endregion
}
