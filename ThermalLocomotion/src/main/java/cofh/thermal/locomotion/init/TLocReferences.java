package cofh.thermal.locomotion.init;

import cofh.thermal.locomotion.entity.UnderwaterMinecartEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class TLocReferences {

    private TLocReferences() {

    }

    // region CARTS
    public static final String ID_UNDERWATER_CART = ID_THERMAL + ":underwater_minecart";

    @ObjectHolder(ID_UNDERWATER_CART)
    public static final Item UNDERWATER_CART_ITEM = null;

    @ObjectHolder(ID_UNDERWATER_CART)
    public static final EntityType<UnderwaterMinecartEntity> UNDERWATER_CART_ENTITY = null;
    // endregion

    // region RAILS
    public static final String ID_CROSSOVER_RAIL = "crossover_rail";

    public static final String ID_LUMIUM_ACTIVATOR_RAIL = ID_THERMAL + ":lumium_activator_rail";
    public static final String ID_LUMIUM_CROSSOVER_RAIL = ID_THERMAL + ":lumium_crossover_rail";
    public static final String ID_LUMIUM_DETECTOR_RAIL = ID_THERMAL + ":lumium_detector_rail";
    public static final String ID_LUMIUM_POWERED_RAIL = ID_THERMAL + ":lumium_powered_rail";
    public static final String ID_LUMIUM_RAIL = ID_THERMAL + ":lumium_rail";

    public static final String ID_PRISMARINE_ACTIVATOR_RAIL = ID_THERMAL + ":prismarine_activator_rail";
    public static final String ID_PRISMARINE_CROSSOVER_RAIL = ID_THERMAL + ":prismarine_crossover_rail";
    public static final String ID_PRISMARINE_DETECTOR_RAIL = ID_THERMAL + ":prismarine_detector_rail";
    public static final String ID_PRISMARINE_POWERED_RAIL = ID_THERMAL + ":prismarine_powered_rail";
    public static final String ID_PRISMARINE_RAIL = ID_THERMAL + ":prismarine_rail";
    // endregion
}
