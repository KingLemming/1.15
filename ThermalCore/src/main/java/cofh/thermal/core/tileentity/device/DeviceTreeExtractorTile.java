package cofh.thermal.core.tileentity.device;

import cofh.core.fluid.FluidStorageCoFH;
import cofh.core.inventory.ItemStorageCoFH;
import cofh.core.network.packet.client.TileStatePacket;
import cofh.core.util.Utils;
import cofh.core.util.helpers.MathHelper;
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
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;
import java.util.stream.Collectors;

import static cofh.core.util.StorageGroup.INPUT;
import static cofh.core.util.StorageGroup.OUTPUT;
import static cofh.core.util.constants.Constants.TANK_MEDIUM;
import static cofh.core.util.constants.NBTTags.TAG_TIME_CONSTANT;
import static cofh.thermal.core.common.ThermalConfig.deviceAugments;
import static cofh.thermal.core.init.TCoreReferences.DEVICE_TREE_EXTRACTOR_TILE;
import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE;

public class DeviceTreeExtractorTile extends ThermalTileBase implements ITickableTileEntity {

    protected static final int NUM_LEAVES = 3;
    protected static final int TIME_CONSTANT = 500;

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(TreeExtractorManager.instance()::validBoost);
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
        for (int i = 0; i < NUM_LEAVES; ++i) {
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
                    if (leafSet.contains(world.getBlockState(leafPos[i]))) {
                        ++leafCount;
                    }
                }
                if (leafCount >= NUM_LEAVES) {
                    Iterable<BlockPos> area = BlockPos.getAllInBoxMutable(trunkPos, trunkPos.add(0, leafPos[0].getY() - trunkPos.getY(), 0));
                    for (BlockPos scan : area) {
                        Material material = world.getBlockState(scan).getMaterial();
                        if (material == Material.ORGANIC || material == Material.EARTH || material == Material.ROCK) {
                            valid = false;
                            cached = true;
                            return;
                        }
                    }
                    area = BlockPos.getAllInBoxMutable(pos.add(0, 1, 0), pos.add(0, leafPos[0].getY() - pos.getY(), 0));
                    for (BlockPos scan : area) {
                        if (isTreeExtractor(world.getBlockState(scan))) {
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
        Iterable<BlockPos> area = BlockPos.getAllInBox(pos.add(-1, 0, -1), pos.add(1, Math.min(256 - pos.getY(), 40), 1)).map(BlockPos::toImmutable).collect(Collectors.toList());
        for (BlockPos scan : area) {
            if (leafSet.contains(world.getBlockState(scan))) {
                leafPos[leafCount] = new BlockPos(scan);
                ++leafCount;
                if (leafCount >= NUM_LEAVES) {
                    break;
                }
            }
        }
        if (leafCount >= NUM_LEAVES) {
            area = BlockPos.getAllInBoxMutable(trunkPos, trunkPos.add(0, leafPos[0].getY() - trunkPos.getY(), 0));
            for (BlockPos scan : area) {
                Material material = world.getBlockState(scan).getMaterial();
                if (material == Material.ORGANIC || material == Material.EARTH || material == Material.ROCK) {
                    valid = false;
                    cached = true;
                    return;
                }
            }
            area = BlockPos.getAllInBoxMutable(pos.add(0, 1, 0), pos.add(0, leafPos[0].getY() - pos.getY(), 0));
            for (BlockPos scan : area) {
                if (isTreeExtractor(world.getBlockState(scan))) {
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
                } else if (!inputSlot.isEmpty()) {
                    boostMult = TreeExtractorManager.instance().getBoostMultiplier(inputSlot.getItemStack());
                    boostCycles = TreeExtractorManager.instance().getBoostCycles(inputSlot.getItemStack());
                    inputSlot.consume(1);
                } else {
                    boostMult = 1.0F;
                    boostCycles = 0;
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

    @Nonnull
    @Override
    public IModelData getModelData() {

        return new ModelDataMap.Builder()
                .withInitial(FLUID, renderFluid)
                .build();
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new DeviceTreeExtractorContainer(i, world, pos, inventory, player);
    }

    // region GUI
    @Override
    public int getScaledDuration(int scale) {

        return !isActive || boostCycles <= 0 ? 0 : scale;
    }
    // endregion

    // region NETWORK
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {

        super.onDataPacket(net, pkt);

        ModelDataManager.requestModelDataRefresh(this);
    }

    @Override
    public void handleControlPacket(PacketBuffer buffer) {

        super.handleControlPacket(buffer);

        ModelDataManager.requestModelDataRefresh(this);
    }

    @Override
    public void handleStatePacket(PacketBuffer buffer) {

        super.handleStatePacket(buffer);

        ModelDataManager.requestModelDataRefresh(this);
    }

    @Override
    public PacketBuffer getGuiPacket(PacketBuffer buffer) {

        super.getGuiPacket(buffer);

        buffer.writeInt(boostCycles);
        buffer.writeFloat(boostMult);

        return buffer;
    }

    @Override
    public void handleGuiPacket(PacketBuffer buffer) {

        super.handleGuiPacket(buffer);

        boostCycles = buffer.readInt();
        boostMult = buffer.readFloat();
    }
    // endregion

    // region NBT
    @Override
    public void read(CompoundNBT nbt) {

        super.read(nbt);

        boostMult = nbt.getFloat("BoostMult");
        boostCycles = nbt.getInt("BoostCycles");
        timeConstant = nbt.getInt(TAG_TIME_CONSTANT);

        if (timeConstant <= 0) {
            timeConstant = TIME_CONSTANT;
        }
        for (int i = 0; i < NUM_LEAVES; ++i) {
            leafPos[i] = NBTUtil.readBlockPos(nbt.getCompound("Leaf" + i));
        }
        trunkPos = NBTUtil.readBlockPos(nbt.getCompound("Trunk"));
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {

        super.write(nbt);

        nbt.putFloat("BoostMult", boostMult);
        nbt.putInt("BoostCycles", boostCycles);
        nbt.putInt(TAG_TIME_CONSTANT, timeConstant);

        for (int i = 0; i < NUM_LEAVES; ++i) {
            nbt.put("Leaf" + i, NBTUtil.writeBlockPos(leafPos[i]));
        }
        nbt.put("Trunk", NBTUtil.writeBlockPos(trunkPos));
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

        return state.getBlock() == this.getBlockState().getBlock();
    }
    // endregion
}
