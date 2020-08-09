package cofh.thermal.core.init;

import cofh.thermal.core.entity.monster.BasalzEntity;
import cofh.thermal.core.entity.monster.BlitzEntity;
import cofh.thermal.core.entity.monster.BlizzEntity;
import cofh.thermal.core.entity.projectile.BasalzProjectileEntity;
import cofh.thermal.core.entity.projectile.BlitzProjectileEntity;
import cofh.thermal.core.entity.projectile.BlizzProjectileEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

import static cofh.thermal.core.ThermalCore.ENTITIES;
import static cofh.thermal.core.init.TCoreReferences.*;

public class TCoreEntities {

    private TCoreEntities() {

    }

    public static void register() {

        ENTITIES.register(ID_BASALZ, () -> EntityType.Builder.create(BasalzEntity::new, EntityClassification.MONSTER).size(0.6F, 1.8F).immuneToFire().build(ID_BASALZ));
        ENTITIES.register(ID_BLITZ, () -> EntityType.Builder.create(BlitzEntity::new, EntityClassification.MONSTER).size(0.6F, 1.8F).build(ID_BLITZ));
        ENTITIES.register(ID_BLIZZ, () -> EntityType.Builder.create(BlizzEntity::new, EntityClassification.MONSTER).size(0.6F, 1.8F).build(ID_BLIZZ));

        ENTITIES.register(ID_BASALZ_PROJECTILE, () -> EntityType.Builder.<BasalzProjectileEntity>create(BasalzProjectileEntity::new, EntityClassification.MISC).size(0.3125F, 0.3125F).build(ID_BASALZ_PROJECTILE));
        ENTITIES.register(ID_BLITZ_PROJECTILE, () -> EntityType.Builder.<BlitzProjectileEntity>create(BlitzProjectileEntity::new, EntityClassification.MISC).size(0.3125F, 0.3125F).build(ID_BLITZ_PROJECTILE));
        ENTITIES.register(ID_BLIZZ_PROJECTILE, () -> EntityType.Builder.<BlizzProjectileEntity>create(BlizzProjectileEntity::new, EntityClassification.MISC).size(0.3125F, 0.3125F).build(ID_BLIZZ_PROJECTILE));
    }

}
