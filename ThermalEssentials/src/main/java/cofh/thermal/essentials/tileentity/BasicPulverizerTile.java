package cofh.thermal.essentials.tileentity;

import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.FluidHelper;
import cofh.thermal.core.tileentity.MachineTileBasicProcess;
import cofh.thermal.core.util.managers.machine.PulverizerRecipeManager;
import cofh.thermal.essentials.inventory.container.BasicPulverizerContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.Constants.TANK_SMALL;
import static cofh.thermal.essentials.init.TEssReferences.BASIC_PULVERIZER_TILE;

public class BasicPulverizerTile extends MachineTileBasicProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(PulverizerRecipeManager.instance()::validRecipe);
    protected ItemStorageCoFH fuelSlot = new ItemStorageCoFH(EnergyHelper::validFurnaceFuel);
    protected FluidStorageCoFH waterTank = new FluidStorageCoFH(TANK_SMALL, FluidHelper.IS_WATER);

    public BasicPulverizerTile() {

        super(BASIC_PULVERIZER_TILE);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(fuelSlot, CATALYST);
        inventory.addSlots(OUTPUT, 4);

        tankInv.addTank(waterTank, INPUT);
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = PulverizerRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
        }
        return curRecipe != null;
    }

    @Override
    protected void resolveInputs() {

        // Input Items
        inputSlot.modify(-itemInputCounts.get(0));
    }

    @Override
    protected int processTick() {

        if (process <= 0) {
            return 0;
        }
        process -= ENERGY_USE;
        return ENERGY_USE;
    }

    @Override
    protected boolean hasFuel() {

        return fuel > 0 && waterTank.getAmount() >= drainAmount;
    }

    @Override
    protected boolean reFuel() {

        ItemStack fuelStack = fuelSlot.getItemStack();
        if (fuel <= 0 && !fuelStack.isEmpty() && EnergyHelper.validFurnaceFuel(fuelStack)) {
            fuel = fuelMax = EnergyHelper.getEnergyFurnaceFuel(fuelStack);
            fuelSlot.consume();
        }
        return hasFuel();
    }

    protected void consumeFuel() {

        if (fuel >= 0) {
            fuel -= ENERGY_USE;

            if (++fuelTicks >= ticksPerDrain) {
                if (!waterTank.isEmpty()) {
                    waterTank.modify(-drainAmount);
                }
                fuelTicks = 0;
            }
        }
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new BasicPulverizerContainer(i, world, pos, inventory, player);
    }

    // region OPTIMIZATION
    @Override
    protected boolean validateInputs() {

        if (!cacheRecipe()) {
            return false;
        }
        return inputSlot.getCount() >= itemInputCounts.get(0);
    }
    // endregion

}
