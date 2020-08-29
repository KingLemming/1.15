package cofh.thermal.core.tileentity;

import cofh.lib.tileentity.TileCoFH;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;

import javax.annotation.Nonnull;

import static cofh.lib.util.constants.Constants.*;
import static cofh.lib.util.constants.NBTTags.*;

public abstract class DynamoTileBase extends ThermalTileBase implements ITickableTileEntity {

    protected static final int BASE_PROCESS_TICK = 40;

    protected Direction facing;

    protected int fuel;
    protected int fuelMax;
    protected int coolant;
    protected int coolantMax;

    protected int baseProcessTick = getBaseProcessTick();
    protected int processTick = baseProcessTick;

    public DynamoTileBase(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
    }

    // region BASE PARAMETERS
    protected int getBaseProcessTick() {

        return BASE_PROCESS_TICK;
    }
    // endregion

    @Override
    public TileCoFH worldContext(BlockState state, IBlockReader world) {

        facing = state.get(FACING_ALL);

        return this;
    }

    @Override
    public void updateContainingBlockInfo() {

        super.updateContainingBlockInfo();
        updateFacing();
    }

    @Override
    public void neighborChanged(Block blockIn, BlockPos fromPos) {

        super.neighborChanged(blockIn, fromPos);

        // TODO: Handle caching of neighbor caps.
    }

    @Override
    public void tick() {

        boolean curActive = isActive;

        if (isActive) {
            processTick();
            if (canProcessFinish()) {
                processFinish();
                if (!redstoneControl.getState() || !canProcessStart()) {
                    processOff();
                } else {
                    processStart();
                }
            }
        } else if (redstoneControl.getState()) {
            if (timeCheck() && canProcessStart()) {
                processStart();
                processTick();
                isActive = true;
            }
        }
        updateActiveState(curActive);
    }

    @Nonnull
    @Override
    public IModelData getModelData() {

        return new ModelDataMap.Builder()
                .withInitial(FLUID, renderFluid)
                .build();
    }

    // region PROCESS
    protected abstract boolean canProcessStart();

    protected boolean canProcessFinish() {

        return fuel <= 0;
    }

    protected abstract void processStart();

    protected void processFinish() {

    }

    protected void processOff() {

        isActive = false;
        wasActive = true;
        if (world != null) {
            timeTracker.markTime(world);
        }
    }

    protected int processTick() {

        if (fuel <= 0) {
            return 0;
        }
        int energy = Math.min(fuel, processTick);
        fuel -= energy;
        transferEnergy(energy);
        return energy;
    }
    // endregion

    // region HELPERS
    protected void transferEnergy(int energy) {

        EnergyHelper.insertIntoAdjacent(this, energy, getFacing());
    }

    protected Direction getFacing() {

        if (facing == null) {
            updateFacing();
        }
        return facing;
    }

    protected void updateFacing() {

        facing = getBlockState().get(FACING_ALL);
    }

    // endregion

    // region GUI
    @Override
    public int getCurSpeed() {

        return isActive ? processTick : 0;
    }

    @Override
    public int getMaxSpeed() {

        return baseProcessTick;
    }

    @Override
    public double getEfficiency() {

        if (getEnergyMod() <= 0) {
            return Double.MAX_VALUE;
        }
        return 1.0D / getEnergyMod();
    }

    @Override
    public int getScaledDuration(int scale) {

        if (fuelMax <= 0 || fuel <= 0) {
            return 0;
        }
        return scale * fuel / fuelMax;
    }
    // endregion

    // region NETWORK
    @Override
    public PacketBuffer getGuiPacket(PacketBuffer buffer) {

        super.getGuiPacket(buffer);

        buffer.writeInt(fuelMax);
        buffer.writeInt(fuel);

        return buffer;
    }

    @Override
    public void handleGuiPacket(PacketBuffer buffer) {

        super.handleGuiPacket(buffer);

        fuelMax = buffer.readInt();
        fuel = buffer.readInt();
    }

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
    // endregion

    // region NBT
    @Override
    public void read(CompoundNBT nbt) {

        super.read(nbt);

        fuelMax = nbt.getInt(TAG_FUEL_MAX);
        fuel = nbt.getInt(TAG_FUEL);
        coolantMax = nbt.getInt(TAG_COOLANT_MAX);
        coolant = nbt.getInt(TAG_COOLANT);
        processTick = nbt.getInt(TAG_PROCESS_TICK);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {

        super.write(nbt);

        nbt.putInt(TAG_FUEL_MAX, fuelMax);
        nbt.putInt(TAG_FUEL, fuel);
        nbt.putInt(TAG_COOLANT_MAX, coolantMax);
        nbt.putInt(TAG_COOLANT, coolant);
        nbt.putInt(TAG_PROCESS_TICK, processTick);

        return nbt;
    }
    // endregion

    // region AUGMENTS
    protected float processMod = 1.0F;
    protected float energyMod = 1.0F;

    @Override
    protected void resetAttributes() {

        super.resetAttributes();

        processMod = 1.0F;
        energyMod = 1.0F;
    }

    @Override
    protected void setAttributesFromAugment(CompoundNBT augmentData) {

        super.setAttributesFromAugment(augmentData);

        processMod += getAttributeMod(augmentData, TAG_AUGMENT_DYNAMO_PRODUCTION);
        energyMod *= getAttributeModWithDefault(augmentData, TAG_AUGMENT_DYNAMO_EFFICIENCY, 1.0F);
    }

    @Override
    protected void finalizeAttributes() {

        super.finalizeAttributes();

        float scaleMin = AUG_SCALE_MIN;
        float scaleMax = AUG_SCALE_MAX;

        baseProcessTick = Math.round(getBaseProcessTick() * baseMod * processMod);
        energyMod = MathHelper.clamp(energyMod, scaleMin, scaleMax);

        processTick = baseProcessTick;
    }

    protected final float getEnergyMod() {

        return energyMod;
    }
    // endregion
}
