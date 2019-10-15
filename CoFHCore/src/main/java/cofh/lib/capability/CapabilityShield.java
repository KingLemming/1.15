package cofh.lib.capability;

import cofh.lib.capability.templates.ShieldItem;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityShield {

    private static boolean registered = false;

    @CapabilityInject(IShieldItem.class)
    public static Capability<IShieldItem> SHIELD_ITEM_CAPABILITY = null;

    public static ShieldItem DEFAULT_SHIELD_CAPABILITY = new ShieldItem();

    public static void register() {

        if (registered) {
            return;
        }
        registered = true;

        CapabilityManager.INSTANCE.register(IShieldItem.class, new IStorage<IShieldItem>() {

            @Override
            public INBT writeNBT(Capability<IShieldItem> capability, IShieldItem instance, Direction side) {

                return null;
            }

            @Override
            public void readNBT(Capability<IShieldItem> capability, IShieldItem instance, Direction side, INBT nbt) {

            }

        }, () -> DEFAULT_SHIELD_CAPABILITY);
    }

}
