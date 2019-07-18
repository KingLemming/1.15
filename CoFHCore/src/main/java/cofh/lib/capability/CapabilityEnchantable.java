package cofh.lib.capability;

import cofh.lib.capability.templates.EnchantableItem;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import java.util.ArrayList;

public class CapabilityEnchantable {

    private static boolean registered = false;

    @CapabilityInject(IEnchantableItem.class)
    public static Capability<IEnchantableItem> ENCHANTABLE_ITEM_CAPABILITY = null;

    public static void register() {

        if (registered) {
            return;
        }
        registered = true;

        CapabilityManager.INSTANCE.register(IEnchantableItem.class, new IStorage<IEnchantableItem>() {

            @Override
            public INBT writeNBT(Capability<IEnchantableItem> capability, IEnchantableItem instance, Direction side) {

                return null;
            }

            @Override
            public void readNBT(Capability<IEnchantableItem> capability, IEnchantableItem instance, Direction side, INBT nbt) {

            }

        }, () -> new EnchantableItem(new ArrayList<>()));
    }

}
