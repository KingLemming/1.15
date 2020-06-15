package cofh.lib.capability;

import cofh.lib.capability.templates.ArcheryAmmoItem;
import cofh.lib.capability.templates.ArcheryBowItem;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityArchery {

    private static boolean registered = false;

    @CapabilityInject(IArcheryBowItem.class)
    public static Capability<IArcheryBowItem> BOW_ITEM_CAPABILITY = null;

    @CapabilityInject(IArcheryAmmoItem.class)
    public static Capability<IArcheryAmmoItem> AMMO_ITEM_CAPABILITY = null;

    public static ArcheryBowItem DEFAULT_BOW_CAPABILITY = new ArcheryBowItem();
    public static ArcheryAmmoItem DEFAULT_AMMO_CAPABILITY = new ArcheryAmmoItem();

    public static void register() {

        if (registered) {
            return;
        }
        registered = true;

        CapabilityManager.INSTANCE.register(IArcheryBowItem.class, new DefaultArcheryHandlerStorage<>(), () -> DEFAULT_BOW_CAPABILITY);
        CapabilityManager.INSTANCE.register(IArcheryAmmoItem.class, new DefaultArcheryHandlerStorage<>(), () -> DEFAULT_AMMO_CAPABILITY);
    }

    private static class DefaultArcheryHandlerStorage<T extends IArcheryItem> implements IStorage<T> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<T> capability, T instance, Direction side) {

            return null;
        }

        @Override
        public void readNBT(Capability<T> capability, T instance, Direction side, INBT nbt) {

        }

    }

}
