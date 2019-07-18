package cofh.lib.capability.templates;

import cofh.lib.capability.IArcheryBowItem;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cofh.lib.capability.CapabilityArchery.BOW_ITEM_CAPABILITY;

public class ArcheryBowItem implements IArcheryBowItem, ICapabilityProvider {

    private final LazyOptional<IArcheryBowItem> holder = LazyOptional.of(() -> this);

    private final float accuracyModifier;
    private final float damageModifier;
    private final float velocityModifier;

    public ArcheryBowItem() {

        accuracyModifier = 1.0F;
        damageModifier = 1.0F;
        velocityModifier = 1.0F;
    }

    public ArcheryBowItem(float accuracyModifier, float damageModifier, float velocityModifier) {

        this.accuracyModifier = MathHelper.clamp(accuracyModifier, 0.1F, 10.0F);
        this.damageModifier = MathHelper.clamp(damageModifier, 0.1F, 10.0F);
        this.velocityModifier = MathHelper.clamp(velocityModifier, 0.1F, 10.0F);
    }

    @Override
    public float getAccuracyModifier(ItemStack bow, ItemStack ammo, PlayerEntity shooter) {

        return accuracyModifier;
    }

    @Override
    public float getDamageModifier(ItemStack bow, ItemStack ammo, PlayerEntity shooter) {

        return damageModifier;
    }

    @Override
    public float getVelocityModifier(ItemStack bow, ItemStack ammo, PlayerEntity shooter) {

        return velocityModifier;
    }

    @Override
    public void onArrowLoosed(ItemStack weapon, ItemStack ammo, PlayerEntity shooter) {

        weapon.damageItem(1, shooter, (consumer) -> consumer.sendBreakAnimation(shooter.getActiveHand()));
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {

        return BOW_ITEM_CAPABILITY.orEmpty(capability, this.holder);
    }

}
