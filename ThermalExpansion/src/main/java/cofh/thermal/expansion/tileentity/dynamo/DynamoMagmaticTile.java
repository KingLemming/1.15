package cofh.thermal.expansion.tileentity.dynamo;

import cofh.core.fluid.FluidStorageCoFH;
import cofh.core.network.packet.client.TileStatePacket;
import cofh.core.util.helpers.FluidHelper;
import cofh.thermal.core.tileentity.DynamoTileBase;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoMagmaticContainer;
import cofh.thermal.expansion.util.managers.dynamo.MagmaticFuelManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

import static cofh.core.util.StorageGroup.INPUT;
import static cofh.core.util.constants.Constants.BUCKET_VOLUME;
import static cofh.core.util.constants.Constants.TANK_SMALL;
import static cofh.thermal.core.common.ThermalConfig.dynamoAugments;
import static cofh.thermal.core.util.managers.SingleFluidFuelManager.FLUID_FUEL_AMOUNT;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_MAGMATIC_TILE;

public class DynamoMagmaticTile extends DynamoTileBase {

    protected FluidStorageCoFH fuelTank = new FluidStorageCoFH(TANK_SMALL, MagmaticFuelManager.instance()::validFuel);

    public DynamoMagmaticTile() {

        super(DYNAMO_MAGMATIC_TILE);

        tankInv.addTank(fuelTank, INPUT);

        renderFluid = new FluidStack(Fluids.LAVA, BUCKET_VOLUME);

        addAugmentSlots(dynamoAugments);
        initHandlers();
    }

    // region PROCESS
    @Override
    protected boolean canProcessStart() {

        return MagmaticFuelManager.instance().getEnergy(fuelTank.getFluidStack()) > 0 && fuelTank.getAmount() >= FLUID_FUEL_AMOUNT;
    }

    @Override
    protected void processStart() {

        if (cacheRenderFluid()) {
            TileStatePacket.sendToClient(this);
        }
        fuel += fuelMax = Math.round(MagmaticFuelManager.instance().getEnergy(fuelTank.getFluidStack()) * energyMod);
        fuelTank.modify(-FLUID_FUEL_AMOUNT);
    }

    @Override
    protected boolean cacheRenderFluid() {

        FluidStack prevFluid = renderFluid;
        renderFluid = new FluidStack(fuelTank.getFluidStack(), BUCKET_VOLUME);
        return !FluidHelper.fluidsEqual(renderFluid, prevFluid);
    }
    // endregion

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new DynamoMagmaticContainer(i, world, pos, inventory, player);
    }

}
