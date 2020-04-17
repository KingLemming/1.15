package cofh.thermal.essentials.init;

import cofh.core.util.ProxyUtils;
import cofh.thermal.essentials.inventory.container.BasicPulverizerContainer;
import cofh.thermal.essentials.inventory.container.BasicSawmillContainer;
import net.minecraftforge.common.extensions.IForgeContainerType;

import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static cofh.thermal.essentials.init.TEssReferences.ID_BASIC_PULVERIZER;
import static cofh.thermal.essentials.init.TEssReferences.ID_BASIC_SAWMILL;

public class TEssContainers {

    private TEssContainers() {

    }

    public static void register() {

        //        CONTAINERS.register(ID_MACHINE_FURNACE, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineFurnaceContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_BASIC_SAWMILL, () -> IForgeContainerType.create((windowId, inv, data) -> new BasicSawmillContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_BASIC_PULVERIZER, () -> IForgeContainerType.create((windowId, inv, data) -> new BasicPulverizerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        //        CONTAINERS.register(ID_MACHINE_INSOLATOR, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineInsolatorContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        //        CONTAINERS.register(ID_MACHINE_CENTRIFUGE, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineCentrifugeContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        //        CONTAINERS.register(ID_MACHINE_PRESS, () -> IForgeContainerType.create((windowId, inv, data) -> new MachinePressContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        //        CONTAINERS.register(ID_MACHINE_CRUCIBLE, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineCrucibleContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        //        CONTAINERS.register(ID_MACHINE_CHILLER, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineChillerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        //        CONTAINERS.register(ID_MACHINE_REFINERY, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineRefineryContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        //        CONTAINERS.register(ID_MACHINE_BREWER, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineBrewerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        //        CONTAINERS.register(ID_MACHINE_BOTTLER, () -> IForgeContainerType.create((windowId, inv, data) -> new MachineBottlerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
    }

}
