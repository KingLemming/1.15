package cofh.lib.util.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.energy.CapabilityEnergy;

import static cofh.lib.util.constants.Constants.RF_PER_FURNACE_UNIT;
import static cofh.lib.util.constants.NBTTags.TAG_ENERGY;

/**
 * This class contains helper functions related to Redstone Flux, aka the Forge Energy system.
 *
 * @author King Lemming
 */
public class EnergyHelper {

    private EnergyHelper() {

    }

    public static boolean validFurnaceFuel(ItemStack input) {

        return getEnergyFurnaceFuel(input) > 0;
    }

    public static int getEnergyFurnaceFuel(ItemStack stack) {

        return ForgeHooks.getBurnTime(stack) * RF_PER_FURNACE_UNIT;
    }

    public static boolean hasEnergyHandlerCap(ItemStack item) {

        return !item.isEmpty() && item.getCapability(CapabilityEnergy.ENERGY).isPresent();
    }

    // TODO: Finish
    //    public static int insertEnergyIntoAdjacentEnergyHandler(TileEntity tile, Direction side, int energy, boolean simulate) {
    //
    //        TileEntity handler = BlockHelper.getAdjacentTileEntity(tile, side);
    //
    //        if (handler != null && handler.hasCapability(ENERGY_HANDLER, side.getOpposite())) {
    //            return handler.getCapability(CapabilityEnergy.ENERGY, side.).receiveEnergy(energy, simulate);
    //        }
    //        return 0;
    //    }

    public static ItemStack setDefaultEnergyTag(ItemStack container, int energy) {

        if (!container.hasTag()) {
            container.setTag(new CompoundNBT());
        }
        container.getTag().putInt(TAG_ENERGY, energy);

        return container;
    }

    //    public static int attemptItemCharge(ItemStack stack, int maxReceive, boolean simulate) {
    //
    //        IEnergyStorage handler = getEnergyHandler(stack);
    //        return handler == null ? 0 : handler.receiveEnergy(maxReceive, simulate);
    //    }
    //
    //    public static boolean isEnergyHandler(ItemStack stack) {
    //
    //        return !stack.isEmpty() && stack.hasCapability(CapabilityEnergy.ENERGY, null);
    //    }
    //
    //    public static IEnergyStorage getEnergyHandler(ItemStack stack) {
    //
    //        return stack.getCapability(CapabilityEnergy.ENERGY, null);
    //    }

}
