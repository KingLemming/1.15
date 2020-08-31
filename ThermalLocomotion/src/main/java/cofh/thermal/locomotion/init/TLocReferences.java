package cofh.thermal.locomotion.init;

import cofh.thermal.locomotion.entity.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.core.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.locomotion.init.TLocIDs.*;

@ObjectHolder(ID_THERMAL)
public class TLocReferences {

    private TLocReferences() {

    }

    // region ENTITIES
    @ObjectHolder(ID_UNDERWATER_CART)
    public static final EntityType<UnderwaterMinecartEntity> UNDERWATER_CART_ENTITY = null;

    @ObjectHolder(ID_PHYTO_TNT_CART)
    public static final EntityType<PhytoTNTMinecartEntity> PHYTO_TNT_CART_ENTITY = null;

    @ObjectHolder(ID_FIRE_TNT_CART)
    public static final EntityType<FireTNTMinecartEntity> FIRE_TNT_CART_ENTITY = null;
    @ObjectHolder(ID_EARTH_TNT_CART)
    public static final EntityType<EarthTNTMinecartEntity> EARTH_TNT_CART_ENTITY = null;
    @ObjectHolder(ID_ICE_TNT_CART)
    public static final EntityType<IceTNTMinecartEntity> ICE_TNT_CART_ENTITY = null;
    @ObjectHolder(ID_LIGHTNING_TNT_CART)
    public static final EntityType<LightningTNTMinecartEntity> LIGHTNING_TNT_CART_ENTITY = null;

    @ObjectHolder(ID_NUKE_TNT_CART)
    public static final EntityType<NukeTNTMinecartEntity> NUKE_TNT_CART_ENTITY = null;
    // endregion

    // region ITEMS
    @ObjectHolder(ID_UNDERWATER_CART)
    public static final Item UNDERWATER_CART_ITEM = null;

    @ObjectHolder(ID_PHYTO_TNT_CART)
    public static final Item PHYTO_TNT_CART_ITEM = null;

    @ObjectHolder(ID_FIRE_TNT_CART)
    public static final Item FIRE_TNT_CART_ITEM = null;
    @ObjectHolder(ID_EARTH_TNT_CART)
    public static final Item EARTH_TNT_CART_ITEM = null;
    @ObjectHolder(ID_ICE_TNT_CART)
    public static final Item ICE_TNT_CART_ITEM = null;
    @ObjectHolder(ID_LIGHTNING_TNT_CART)
    public static final Item LIGHTNING_TNT_CART_ITEM = null;

    @ObjectHolder(ID_NUKE_TNT_CART)
    public static final Item NUKE_TNT_CART_ITEM = null;
    // endregion
}
