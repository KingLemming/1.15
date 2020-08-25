package cofh.thermal.locomotion.init;

import cofh.thermal.locomotion.entity.UnderwaterMinecartEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.locomotion.init.TLocIDs.ID_UNDERWATER_CART;

@ObjectHolder(ID_THERMAL)
public class TLocReferences {

    private TLocReferences() {

    }

    @ObjectHolder(ID_UNDERWATER_CART)
    public static final EntityType<UnderwaterMinecartEntity> UNDERWATER_CART_ENTITY = null;

    @ObjectHolder(ID_UNDERWATER_CART)
    public static final Item UNDERWATER_CART_ITEM = null;

}
