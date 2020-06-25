package cofh.thermal.locomotion.init;

import cofh.thermal.locomotion.entity.UnderwaterMinecartEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

import static cofh.thermal.core.ThermalCore.ENTITIES;
import static cofh.thermal.locomotion.init.TLocReferences.ID_UNDERWATER_CART;

public class TLocEntities {

    private TLocEntities() {

    }

    public static void register() {

        ENTITIES.register(ID_UNDERWATER_CART, () -> EntityType.Builder.<UnderwaterMinecartEntity>create(UnderwaterMinecartEntity::new, EntityClassification.MISC).size(0.98F, 0.7F).build(ID_UNDERWATER_CART));
    }

}
