package cofh.core.init;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class CoreAttributes {

    public static final IAttribute FALL_DISTANCE = new RangedAttribute(null, "cofh.fallDistance", 0.0D, -128.0D, 128.0D).setDescription("Fall Distance");
    public static final IAttribute HAZARD_RESISTANCE = new RangedAttribute(null, "cofh.hazardResistance", 0.0D, 0.0D, 1.0D).setDescription("Hazard Resistance");
    public static final IAttribute STING_RESISTANCE = new RangedAttribute(null, "cofh.stingResistance", 0.0D, 0.0D, 1.0D).setDescription("Sting Resistance");

}
