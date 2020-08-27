package cofh.thermal.core.tileentity.device;

import cofh.core.network.packet.client.TileStatePacket;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.Utils;
import cofh.lib.util.helpers.MathHelper;
import cofh.thermal.core.inventory.container.device.DeviceTreeExtractorContainer;
import cofh.thermal.core.tileentity.ThermalTileBase;
import cofh.thermal.core.util.managers.device.TreeExtractorManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.Set;

import static cofh.lib.util.StorageGroup.INPUT;
import static cofh.lib.util.StorageGroup.OUTPUT;
import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.lib.util.constants.NBTTags.TAG_TIME_CONSTANT;
import static cofh.thermal.core.common.ThermalConfig.deviceAugments;
import static cofh.thermal.core.init.TCoreReferences.DEVICE_TREE_EXTRACTOR_TILE;
import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE;

public class DeviceTreeExtractorTile extends ThermalTileBase implements ITickableTileEntity {

    protected static final int NUM_LEAVES = 3;
    protected static final int TIME_CONSTANT = 500;

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH();
    protected FluidStorageCoFH outputTank = new FluidStorageCoFH(TANK_MEDIUM);

    private boolean cached;
    private boolean valid;

    private BlockPos trunkPos;
    private final BlockPos[] leafPos = new BlockPos[NUM_LEAVES];

    private int timeConstant = TIME_CONSTANT;
    private final int timeOffset;

    private float boostMult;
    private int boostCycles;

    public DeviceTreeExtractorTile() {

        super(DEVICE_TREE_EXTRACTOR_TILE);
        timeOffset = MathHelper.RANDOM.nextInt(TIME_CONSTANT);

        inventory.addSlot(inputSlot, INPUT);

        tankInv.addTank(outputTank, OUTPUT);

        addAugmentSlots(deviceAugments);
        initHandlers();

        trunkPos = new BlockPos(pos);
        for (int i = 0; i < NUM_LEAVES; i++) {
            leafPos[i] = new BlockPos(pos);
        }
    }

    protected void updateValidity() {

        if (world == null || !world.isAreaLoaded(pos, 1) || Utils.isClientWorld(world)) {
            return;
        }
        timeConstant = getTimeConstant();

        if (valid) {
            if (isTrunkBase(trunkPos)) {
                Set<BlockState> leafSet = TreeExtractorManager.instance().getMatchingLeaves(world.getBlockState(trunkPos));
                int leafCount = 0;
                for (int i = 0; i < NUM_LEAVES; ++i) {
                    BlockState state = world.getBlockState(leafPos[i]);
                    if (leafSet.contains(state)) {
                        ++leafCount;
                    }
                }
                if (leafCount >= NUM_LEAVES) {
                    Iterable<BlockPos> area = BlockPos.getAllInBoxMutable(trunkPos, trunkPos.add(0, leafPos[0].getY() - trunkPos.getY(), 0));
                    for (BlockPos scan : area) {
                        BlockState state = world.getBlockState(scan);
                        Material material = state.getMaterial();

                        if (material == Material.ORGANIC || material == Material.EARTH || material == Material.ROCK) {
                            valid = false;
                            cached = true;
                            return;
                        }
                    }
                    area = BlockPos.getAllInBoxMutable(pos.add(0, 1, 0), pos.add(0, leafPos[0].getY() - pos.getY(), 0));
                    for (BlockPos scan : area) {
                        BlockState state = world.getBlockState(scan);
                        if (state == this.getBlockState()) {
                            valid = false;
                            cached = true;
                            return;
                        }
                    }
                    cached = true;
                    renderFluid = TreeExtractorManager.instance().getFluid(world.getBlockState(trunkPos));
                    return;
                }
            }
            valid = false;
        }
        if (isTrunkBase(pos.west())) {
            trunkPos = pos.west();
        } else if (isTrunkBase(pos.east())) {
            trunkPos = pos.east();
        } else if (isTrunkBase(pos.north())) {
            trunkPos = pos.north();
        } else if (isTrunkBase(pos.south())) {
            trunkPos = pos.south();
        }
        if (!isTrunkBase(trunkPos)) {
            valid = false;
            cached = true;
            return;
        }
        Set<BlockState> leafSet = TreeExtractorManager.instance().getMatchingLeaves(world.getBlockState(trunkPos));
        int leafCount = 0;
        for (int i = 0; i < NUM_LEAVES; ++i) {
            BlockState state = world.getBlockState(leafPos[i]);
            if (leafSet.contains(state)) {
                ++leafCount;
            }
        }
        if (leafCount >= NUM_LEAVES) {
            Iterable<BlockPos> area = BlockPos.getAllInBoxMutable(trunkPos, trunkPos.add(0, leafPos[0].getY() - trunkPos.getY(), 0));
            for (BlockPos scan : area) {
                BlockState state = world.getBlockState(scan);
                Material material = state.getMaterial();

                if (material == Material.ORGANIC || material == Material.EARTH || material == Material.ROCK) {
                    valid = false;
                    cached = true;
                    return;
                }
            }
            area = BlockPos.getAllInBoxMutable(pos.add(0, 1, 0), pos.add(0, leafPos[0].getY() - pos.getY(), 0));
            for (BlockPos scan : area) {
                BlockState state = world.getBlockState(scan);
                if (state == this.getBlockState()) {
                    valid = false;
                    cached = true;
                    return;
                }
            }
            valid = true;
            renderFluid = TreeExtractorManager.instance().getFluid(world.getBlockState(trunkPos));
        }
        cached = true;
    }

    protected void updateActiveState() {

        boolean curActive = isActive;
        isActive = redstoneControl.getState() && valid;
        updateActiveState(curActive);
    }

    @Override
    public void tick() {

        if (!timeCheckOffset()) {
            return;
        }
        Fluid curFluid = renderFluid.getFluid();

        if (isActive) {
            if (valid) {
                if (boostCycles > 0) {
                    --boostCycles;
                } else if (false) { // TODO: Fertilizer;
                    boostMult = 1.0F; // get FertilizerMult
                    boostCycles = 0; // get Boost Cycles
                    inputSlot.consume();
                } else {
                    boostMult = 1.0F; // get FertilizerMult
                    boostCycles = 0; // get Boost Cycles
                }
                outputTank.fill(new FluidStack(renderFluid, (int) (renderFluid.getAmount() * baseMod * boostMult)), EXECUTE);
                updateValidity();
            }
        }
        if (!cached) {
            updateValidity();
        }
        if (curFluid != renderFluid.getFluid()) {
            TileStatePacket.sendToClient(this);
        }
        updateActiveState();
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new DeviceTreeExtractorContainer(i, world, pos, inventory, player);
    }

    // region NBT
    @Override
    public void read(CompoundNBT nbt) {

        super.read(nbt);

        boostMult = nbt.getFloat("BoostMult");
        boostCycles = nbt.getInt("BoostTime");
        timeConstant = nbt.getInt(TAG_TIME_CONSTANT);

        if (timeConstant <= 0) {
            timeConstant = TIME_CONSTANT;
        }
        for (int i = 0; i < NUM_LEAVES; i++) {
            leafPos[i] = new BlockPos(nbt.getInt("LeafX" + i), nbt.getInt("LeafY" + i), nbt.getInt("LeafZ" + i));
        }
        trunkPos = new BlockPos(nbt.getInt("TrunkX"), nbt.getInt("TrunkY"), nbt.getInt("TrunkZ"));
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {

        super.write(nbt);

        nbt.putFloat("BoostMult", boostMult);
        nbt.putInt("BoostTime", boostCycles);
        nbt.putInt(TAG_TIME_CONSTANT, timeConstant);

        for (int i = 0; i < NUM_LEAVES; i++) {
            nbt.putInt("LeafX" + i, leafPos[i].getX());
            nbt.putInt("LeafY" + i, leafPos[i].getY());
            nbt.putInt("LeafZ" + i, leafPos[i].getZ());
        }
        nbt.putInt("TrunkX", trunkPos.getX());
        nbt.putInt("TrunkY", trunkPos.getY());
        nbt.putInt("TrunkZ", trunkPos.getZ());

        return nbt;
    }
    // endregion

    // region HELPERS
    protected boolean timeCheckOffset() {

        return (world.getGameTime() + timeOffset) % timeConstant == 0;
    }

    protected int getTimeConstant() {

        int constant = TIME_CONSTANT / 2;
        Iterable<BlockPos> area = BlockPos.getAllInBoxMutable(trunkPos.add(-1, 0, -1), trunkPos.add(1, 0, 1));
        for (BlockPos scan : area) {
            if (isTreeExtractor(world.getBlockState(scan))) {
                constant += TIME_CONSTANT / 2;
            }
        }
        return MathHelper.clamp(constant, TIME_CONSTANT, TIME_CONSTANT * 2);
    }

    protected boolean isTrunkBase(BlockPos checkPos) {

        BlockState state = world.getBlockState(checkPos.down());
        Material material = state.getMaterial();
        if (material != Material.ORGANIC && material != Material.EARTH && material != Material.ROCK) {
            return false;
        }
        return TreeExtractorManager.instance().validTrunk(world.getBlockState(checkPos))
                && TreeExtractorManager.instance().validTrunk(world.getBlockState(checkPos.up()))
                && TreeExtractorManager.instance().validTrunk(world.getBlockState(checkPos.up(2)));
    }

    protected boolean isTreeExtractor(BlockState state) {

        return state == this.getBlockState();
    }
    // endregion
}
