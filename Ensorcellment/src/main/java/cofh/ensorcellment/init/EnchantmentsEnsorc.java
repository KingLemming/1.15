package cofh.ensorcellment.init;

import cofh.ensorcellment.enchantment.*;
import cofh.ensorcellment.enchantment.override.*;
import net.minecraft.inventory.EquipmentSlotType;

import static cofh.ensorcellment.Ensorcellment.ENCHANTMENTS;
import static cofh.lib.util.modhelpers.EnsorcellmentHelper.*;
import static net.minecraft.enchantment.Enchantment.Rarity.*;
import static net.minecraft.enchantment.ProtectionEnchantment.Type.*;
import static net.minecraft.inventory.EquipmentSlotType.*;

public class EnchantmentsEnsorc {

    private EnchantmentsEnsorc() {

    }

    public static void register() {

        EquipmentSlotType[] armorSlots = new EquipmentSlotType[]{HEAD, CHEST, LEGS, FEET};

        ENCHANTMENTS.registerSpec(ID_PROTECTION, () -> new ProtectionEnchantmentImp(COMMON, ALL, armorSlots));
        ENCHANTMENTS.registerSpec(ID_PROTECTION_BLAST, () -> new ProtectionEnchantmentImp(RARE, EXPLOSION, armorSlots));
        ENCHANTMENTS.registerSpec(ID_PROTECTION_FALL, () -> new ProtectionEnchantmentImp(UNCOMMON, FALL, armorSlots));
        ENCHANTMENTS.registerSpec(ID_PROTECTION_FIRE, () -> new ProtectionEnchantmentImp(UNCOMMON, FIRE, armorSlots));
        ENCHANTMENTS.registerSpec(ID_PROTECTION_PROJECTILE, () -> new ProtectionEnchantmentImp(UNCOMMON, PROJECTILE, armorSlots));

        ENCHANTMENTS.registerSpec(ID_FIRE_ASPECT, FireAspectEnchantmentImp::new);
        ENCHANTMENTS.registerSpec(ID_FROST_WALKER, FrostWalkerEnchantmentImp::new);
        ENCHANTMENTS.registerSpec(ID_KNOCKBACK, KnockbackEnchantmentImp::new);
        ENCHANTMENTS.registerSpec(ID_LOOTING, LootingEnchantmentImp::new);
        ENCHANTMENTS.registerSpec(ID_THORNS, ThornsEnchantmentImp::new);

        ENCHANTMENTS.registerSpec(ID_MENDING, MendingEnchantmentAlt::new);

        ENCHANTMENTS.registerSpec(ID_AIR_WORKER, AirWorkerEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_ANGLER, AnglerEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_BULWARK, BulwarkEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_DAMAGE_ENDER, DamageEnderEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_DAMAGE_ILLAGER, DamageIllagerEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_DAMAGE_VILLAGER, DamageVillagerEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_DISPLACEMENT, DisplacementEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_GOURMAND, GourmandEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_HOLDING, HoldingEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_HUNTER, HunterEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_EXP_BOOST, ExpBoostEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_LEECH, LeechEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_PHALANX, PhalanxEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_QUICKDRAW, QuickdrawEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_SOULBOUND, SoulboundEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_TRUESHOT, TrueshotEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_VOLLEY, VolleyEnchantment::new);
        ENCHANTMENTS.registerSpec(ID_VORPAL, VorpalEnchantment::new);

        ENCHANTMENTS.registerSpec(ID_CURSE_MERCY, CurseMercyEnchantment::new);

        // ENCHANTMENTS.registerSpec(ID_PROSPECTOR, ProspectorEnchantment::new);
        // ENCHANTMENTS.registerSpec(ID_SMASHING, SmashingEnchantment::new);
        // ENCHANTMENTS.registerSpec(ID_SMELTING, SmeltingEnchantment::new);
    }

}
