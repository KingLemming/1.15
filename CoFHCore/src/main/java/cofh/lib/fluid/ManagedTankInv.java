package cofh.lib.fluid;

import cofh.lib.block.ITileCallback;
import cofh.lib.util.StorageGroup;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.EmptyFluidHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManagedTankInv extends SimpleTankInv {

    protected List<FluidStorageCoFH> inputTanks = new ArrayList<>();
    protected List<FluidStorageCoFH> outputTanks = new ArrayList<>();
    protected List<FluidStorageCoFH> accessibleTanks = new ArrayList<>();
    protected List<FluidStorageCoFH> internalTanks = new ArrayList<>();

    public ManagedTankInv(ITileCallback tile) {

        super(tile);
    }

    public ManagedTankInv(ITileCallback tile, String tag) {

        super(tile, tag);
    }

    public void addTank(FluidStorageCoFH tank, StorageGroup group) {

        tanks.add(tank);
        switch (group) {
            case INPUT:
                inputTanks.add(tank);
                accessibleTanks.add(tank);
                break;
            case OUTPUT:
                outputTanks.add(tank);
                accessibleTanks.add(tank);
                break;
            case ACCESSIBLE:
                accessibleTanks.add(tank);
                break;
            case INTERNAL:
                internalTanks.add(tank);
                break;
            default:
        }
    }

    public List<FluidStorageCoFH> getInputTanks() {

        return inputTanks;
    }

    public List<FluidStorageCoFH> getOutputTanks() {

        return outputTanks;
    }

    public List<FluidStorageCoFH> getInternalTanks() {

        return internalTanks;
    }

    public IFluidHandler getHandler(StorageGroup group) {

        switch (group) {
            case INPUT:
                return new ManagedFluidHandler(tile, inputTanks, Collections.emptyList());
            case OUTPUT:
                return new ManagedFluidHandler(tile, Collections.emptyList(), outputTanks);
            case ACCESSIBLE:
                return new ManagedFluidHandler(tile, inputTanks, outputTanks);
            case INTERNAL:
                return new SimpleFluidHandler(tile, internalTanks);
            case ALL:
                return new SimpleFluidHandler(tile, tanks);
            default:
        }
        return EmptyFluidHandler.INSTANCE;
    }

}
