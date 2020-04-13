package cofh.thermal.cultivation.init;

import cofh.core.util.ProxyUtils;
import cofh.thermal.cultivation.inventory.container.DeviceHiveExtractorContainer;
import net.minecraftforge.common.extensions.IForgeContainerType;

import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static cofh.thermal.cultivation.init.TCulReferences.ID_DEVICE_HIVE_EXTRACTOR;

public class TCulContainers {

    private TCulContainers() {

    }

    public static void register() {

        CONTAINERS.register(ID_DEVICE_HIVE_EXTRACTOR, () -> IForgeContainerType.create((windowId, inv, data) -> new DeviceHiveExtractorContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
    }

}
