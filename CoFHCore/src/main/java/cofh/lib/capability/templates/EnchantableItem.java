package cofh.lib.capability.templates;

import cofh.lib.capability.CapabilityEnchantable;
import cofh.lib.capability.IEnchantableItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnchantableItem implements IEnchantableItem, ICapabilityProvider {

    private final LazyOptional<IEnchantableItem> holder = LazyOptional.of(() -> this);

    final Set<Enchantment> validEnchantments = new HashSet<>();

    public EnchantableItem(List<Enchantment> enchantments) {

        validEnchantments.addAll(enchantments);
    }

    public EnchantableItem addEnchantment(Enchantment ench) {

        validEnchantments.add(ench);
        return this;
    }

    public EnchantableItem removeEnchantment(Enchantment ench) {

        validEnchantments.remove(ench);
        return this;
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Enchantment ench) {

        return validEnchantments.contains(ench);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable Direction side) {

        return CapabilityEnchantable.ENCHANTABLE_ITEM_CAPABILITY.orEmpty(cap, this.holder);
    }

}
