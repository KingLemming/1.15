package cofh.lib.capability.templates;

import cofh.lib.capability.IMeleeShieldItem;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cofh.lib.capability.CapabilityMelee.SHIELD_ITEM_CAPABILITY;

public class MeleeShieldItem implements IMeleeShieldItem, ICapabilityProvider {

    private final LazyOptional<IMeleeShieldItem> holder = LazyOptional.of(() -> this);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {

        return SHIELD_ITEM_CAPABILITY.orEmpty(capability, this.holder);
    }

}
