package cofh.lib.capability;

import cofh.lib.capability.templates.AOEItem;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityAOE {

    private static boolean registered = false;

    @CapabilityInject(IAOEItem.class)
    public static Capability<IAOEItem> AOE_ITEM_CAPABILITY = null;

    public static AOEItem DEFAULT_AOE_CAPABILITY = new AOEItem(false);

    public static void register() {

        if (registered) {
            return;
        }
        registered = true;

        CapabilityManager.INSTANCE.register(IAOEItem.class, new IStorage<IAOEItem>() {

            @Override
            public INBT writeNBT(Capability<IAOEItem> capability, IAOEItem instance, Direction side) {

                return null;
            }

            @Override
            public void readNBT(Capability<IAOEItem> capability, IAOEItem instance, Direction side, INBT nbt) {

            }

        }, () -> DEFAULT_AOE_CAPABILITY);
    }

}
