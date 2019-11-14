package cofh.core.potion;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectType;

import java.util.Collections;
import java.util.List;

public class TrainingEffect extends EffectCoFH {

    public TrainingEffect(EffectType typeIn, int liquidColorIn) {

        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {

        return false;
    }

    @Override
    public List<ItemStack> getCurativeItems() {

        return Collections.emptyList();
    }

}
