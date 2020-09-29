package cofh.redstonearsenal.item;

import cofh.core.capability.IArcheryAmmoItem;
import cofh.core.energy.EnergyContainerItemWrapper;
import cofh.core.energy.IEnergyContainerItem;
import cofh.core.fluid.IFluidContainerItem;
import cofh.core.item.EnergyContainerItem;
import cofh.core.item.IMultiModeItem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import java.util.List;

public class FluxQuiverItem extends EnergyContainerItem implements IMultiModeItem {

    public FluxQuiverItem(Properties builder, int maxEnergy, int maxTransfer) {

        super(builder, maxEnergy, maxTransfer);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if (Screen.hasShiftDown()) {
            return;
        }
    }

    // region CAPABILITY WRAPPER
    protected class FluxQuiverItemWrapper extends EnergyContainerItemWrapper implements IArcheryAmmoItem {

        private final LazyOptional<IArcheryAmmoItem> holder = LazyOptional.of(() -> this);

        FluxQuiverItemWrapper(ItemStack containerIn, IEnergyContainerItem itemIn) {

            super(containerIn, itemIn);
        }

        @Override
        public void onArrowLoosed(PlayerEntity shooter) {

        }

        @Override
        public AbstractArrowEntity createArrowEntity(World world, PlayerEntity shooter) {

            return null;
        }

        @Override
        public boolean isEmpty(PlayerEntity shooter) {

            return false;
        }

        @Override
        public boolean isInfinite(ItemStack bow, PlayerEntity shooter) {

            return false;
        }

    }
    // endregion
}
