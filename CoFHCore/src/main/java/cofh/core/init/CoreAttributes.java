package cofh.core.init;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class CoreAttributes {

    public static final IAttribute BEE_STING_RESISTANCE = new RangedAttribute(null, "cofh.beeStingResistance", 0.0D, 0.0D, 100.0D).setDescription("Bee Sting Resistance").setShouldWatch(true);

}
