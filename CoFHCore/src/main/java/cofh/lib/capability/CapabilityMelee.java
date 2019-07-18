package cofh.lib.capability;

import cofh.lib.capability.templates.MeleeShieldItem;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityMelee {

    private static boolean registered = false;

    @CapabilityInject(IMeleeShieldItem.class)
    public static Capability<IMeleeShieldItem> SHIELD_ITEM_CAPABILITY = null;

    public static MeleeShieldItem DEFAULT_SHIELD_CAPABILITY = new MeleeShieldItem();

    public static void register() {

        if (registered) {
            return;
        }
        registered = true;

        CapabilityManager.INSTANCE.register(IMeleeShieldItem.class, new IStorage<IMeleeShieldItem>() {

            @Override
            public INBT writeNBT(Capability<IMeleeShieldItem> capability, IMeleeShieldItem instance, Direction side) {

                return null;
            }

            @Override
            public void readNBT(Capability<IMeleeShieldItem> capability, IMeleeShieldItem instance, Direction side, INBT nbt) {

            }

        }, () -> DEFAULT_SHIELD_CAPABILITY);
    }

}
