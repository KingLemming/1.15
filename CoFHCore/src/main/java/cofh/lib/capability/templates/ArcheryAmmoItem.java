package cofh.lib.capability.templates;

import cofh.lib.capability.IArcheryAmmoItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cofh.lib.capability.CapabilityArchery.AMMO_ITEM_CAPABILITY;

public class ArcheryAmmoItem implements IArcheryAmmoItem, ICapabilityProvider {

    private final LazyOptional<IArcheryAmmoItem> holder = LazyOptional.of(() -> this);

    public ArcheryAmmoItem() {

    }

    @Override
    public void onArrowLoosed(ItemStack weapon, ItemStack ammo, PlayerEntity shooter) {

        ammo.shrink(1);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable Direction side) {

        return AMMO_ITEM_CAPABILITY.orEmpty(cap, this.holder);
    }

}
