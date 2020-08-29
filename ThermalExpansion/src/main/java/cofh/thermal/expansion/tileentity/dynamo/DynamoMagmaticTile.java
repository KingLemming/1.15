package cofh.thermal.expansion.tileentity.dynamo;

import cofh.core.network.packet.client.TileStatePacket;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.util.helpers.FluidHelper;
import cofh.thermal.core.tileentity.DynamoTileBase;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoMagmaticContainer;
import cofh.thermal.expansion.util.managers.dynamo.MagmaticFuelManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.INPUT;
import static cofh.lib.util.constants.Constants.TANK_SMALL;
import static cofh.thermal.core.common.ThermalConfig.dynamoAugments;
import static cofh.thermal.core.util.managers.SingleFluidFuelManager.FLUID_FUEL_AMOUNT;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_MAGMATIC_TILE;

public class DynamoMagmaticTile extends DynamoTileBase {

    protected FluidStorageCoFH fuelTank = new FluidStorageCoFH(TANK_SMALL, MagmaticFuelManager.instance()::validFuel);

    public DynamoMagmaticTile() {

        super(DYNAMO_MAGMATIC_TILE);

        tankInv.addTank(fuelTank, INPUT);

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

        fuel += fuelMax = Math.round(MagmaticFuelManager.instance().getEnergy(fuelTank.getFluidStack()) * energyMod);
        fuelTank.modify(-FLUID_FUEL_AMOUNT);
        if (cacheRenderFluid()) {
            TileStatePacket.sendToClient(this);
        }
    }

    @Override
    protected boolean cacheRenderFluid() {

        FluidStack prevFluid = renderFluid;
        renderFluid = new FluidStack(fuelTank.getFluidStack(), fuelTank.isEmpty() ? 0 : FluidAttributes.BUCKET_VOLUME);
        return !FluidHelper.fluidsEqual(renderFluid, prevFluid);
    }
    // endregion

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new DynamoMagmaticContainer(i, world, pos, inventory, player);
    }

}
