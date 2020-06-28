package cofh.thermal.core.tileentity.workbench;

import cofh.lib.energy.EnergyStorageCoFH;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.AugmentHelper;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.FluidHelper;
import cofh.thermal.core.inventory.container.workbench.TinkerBenchContainer;
import cofh.thermal.core.tileentity.ThermalTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.INTERNAL;
import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.thermal.core.init.TCoreReferences.TINKER_BENCH_TILE;

public class TinkerBenchTile extends ThermalTileBase implements ITickableTileEntity {

    protected ItemStorageCoFH tinkerSlot = new ItemStorageCoFH(item -> AugmentHelper.isAugmentable(item) || EnergyHelper.hasEnergyHandlerCap(item) || FluidHelper.hasFluidHandlerCap(item));
    protected ItemStorageCoFH chargeSlot = new ItemStorageCoFH(1, EnergyHelper::hasEnergyHandlerCap);
    protected ItemStorageCoFH tankSlot = new ItemStorageCoFH(1, FluidHelper::hasFluidHandlerCap);

    protected FluidStorageCoFH tank = new FluidStorageCoFH(TANK_MEDIUM);

    protected static final int BASE_ENERGY = 200000;
    protected static final int BASE_TRANSFER = 1000;

    // TODO: Cap caching. Let's get it working first.
    //    protected LazyOptional<?> tinkerEnergyCap = LazyOptional.empty();
    //    protected LazyOptional<?> tinkerFluidCap = LazyOptional.empty();
    //    protected LazyOptional<?> chargeEnergyCap = LazyOptional.empty();
    //    protected LazyOptional<?> tankFluidCap = LazyOptional.empty();

    public TinkerBenchTile() {

        super(TINKER_BENCH_TILE);

        energyStorage = new EnergyStorageCoFH(BASE_ENERGY, BASE_TRANSFER);

        inventory.addSlot(tinkerSlot, INTERNAL);
        inventory.addSlot(chargeSlot, INTERNAL);
        inventory.addSlot(tankSlot, INTERNAL);

        tankInv.addTank(tank, INTERNAL);

        addAugmentSlots(2);
        initHandlers();
    }

    @Override
    public void tick() {

        if (redstoneControl.getState()) {
            chargeEnergy();
            fillFluid();
        }
    }

    protected void chargeEnergy() {

        if (!chargeSlot.isEmpty()) {
            chargeSlot.getItemStack()
                    .getCapability(CapabilityEnergy.ENERGY, null)
                    .ifPresent(c -> energyStorage.receiveEnergy(c.extractEnergy(Math.min(energyStorage.getMaxReceive(), energyStorage.getSpace()), false), false));
        }
        if (!tinkerSlot.isEmpty()) {
            tinkerSlot.getItemStack()
                    .getCapability(CapabilityEnergy.ENERGY, null)
                    .ifPresent(c -> energyStorage.extractEnergy(c.receiveEnergy(Math.min(energyStorage.getMaxExtract(), energyStorage.getEnergyStored()), false), false));
        }
    }

    protected void fillFluid() {

        if (!tankSlot.isEmpty()) {
            tankSlot.getItemStack()
                    .getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                    .ifPresent(c -> {
                        c.drain(tank.fill(c.getFluidInTank(0), IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
                        tankSlot.setItemStack(c.getContainer());
                    });
        }
        if (!tinkerSlot.isEmpty()) {
            tinkerSlot.getItemStack()
                    .getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)
                    .ifPresent(c -> {
                        tank.drain(c.fill(tank.getFluidStack(), IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
                        tinkerSlot.setItemStack(c.getContainer());
                    });
        }
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new TinkerBenchContainer(i, world, pos, inventory, player);
    }

}
