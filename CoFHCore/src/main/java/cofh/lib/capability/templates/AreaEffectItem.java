package cofh.lib.capability.templates;

import cofh.lib.capability.IAreaEffectItem;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cofh.lib.capability.CapabilityAreaEffect.AOE_ITEM_CAPABILITY;

public class AreaEffectItem implements IAreaEffectItem, ICapabilityProvider {

    private final LazyOptional<IAreaEffectItem> holder = LazyOptional.of(() -> this);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable Direction side) {

        return AOE_ITEM_CAPABILITY.orEmpty(cap, this.holder);
    }

}
