package cofh.thermal.expansion.tileentity;

import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.tileentity.MachineTileProcess;
import cofh.thermal.expansion.inventory.container.MachinePulverizerContainer;
import cofh.thermal.expansion.util.managers.machine.PulverizerRecipeManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PULVERIZER_TILE;

public class MachinePulverizerTile extends MachineTileProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(PulverizerRecipeManager.instance()::validRecipe);
    protected ItemStorageCoFH catalystSlot = new ItemStorageCoFH(PulverizerRecipeManager.instance()::validCatalyst);

    public MachinePulverizerTile() {

        super(MACHINE_PULVERIZER_TILE);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(catalystSlot, CATALYST);
        inventory.addSlots(OUTPUT, 4);
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = PulverizerRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
        }
        return curRecipe != null;
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new MachinePulverizerContainer(i, world, pos, inventory, player);
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

    // TODO: Determine WHY.
    //    @Override
    //    public void tick() {
    //
    //        energyStorage.modify(10);
    //
    //        boolean curActive = isActive;
    //
    //        if (isActive) {
    //            processTick();
    //            if (canProcessFinish()) {
    //                processFinish();
    //                transferOutput();
    //                transferInput();
    //                if (!redstoneControl.getState() || !canProcessStart()) {
    //                    processOff();
    //                } else {
    //                    processStart();
    //                }
    //            } else if (energyStorage.isEmpty()) {
    //                processOff();
    //            }
    //        } else if (redstoneControl.getState()) {
    //            if (timeCheck()) {
    //                transferOutput();
    //                transferInput();
    //            }
    //            if (timeCheckQuarter() && canProcessStart()) {
    //                processStart();
    //                processTick();
    //                isActive = true;
    //            }
    //        }
    //        updateActiveState(curActive);
    //        // chargeEnergy();
    //    }
    //
    //    @Override
    //    public ITextComponent getDisplayName() {
    //
    //        return new StringTextComponent(getType().getRegistryName().getPath());
    //    }

}
