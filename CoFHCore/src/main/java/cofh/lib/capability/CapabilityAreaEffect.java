package cofh.lib.capability;

import cofh.lib.capability.templates.AreaEffectItem;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityAreaEffect {

    private static boolean registered = false;

    @CapabilityInject(IAreaEffectItem.class)
    public static Capability<IAreaEffectItem> AOE_ITEM_CAPABILITY = null;

    public static AreaEffectItem DEFAULT_AOE_CAPABILITY = new AreaEffectItem();

    public static void register() {

        if (registered) {
            return;
        }
        registered = true;

        CapabilityManager.INSTANCE.register(IAreaEffectItem.class, new IStorage<IAreaEffectItem>() {

            @Override
            public INBT writeNBT(Capability<IAreaEffectItem> capability, IAreaEffectItem instance, Direction side) {

                return null;
            }

            @Override
            public void readNBT(Capability<IAreaEffectItem> capability, IAreaEffectItem instance, Direction side, INBT nbt) {

            }

        }, () -> DEFAULT_AOE_CAPABILITY);
    }

}
