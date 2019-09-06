package cofh.ensorcellment.init;

import cofh.ensorcellment.enchantment.*;
import cofh.ensorcellment.enchantment.override.*;
import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

import static cofh.ensorcellment.init.ConfigEnsorc.configurables;
import static cofh.lib.util.modhelpers.EnsorcellmentHelper.*;

public class EnchantmentsEnsorc {

    private EnchantmentsEnsorc() {

    }

    public static void createEnchantments() {

        EquipmentSlotType[] armorSlots = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};

        createEnchantment(new ProtectionEnchantmentImp(ID_PROTECTION, Enchantment.Rarity.COMMON, ProtectionEnchantment.Type.ALL, armorSlots, "Protection"));
        createEnchantment(new ProtectionEnchantmentImp(ID_PROTECTION_BLAST, Enchantment.Rarity.RARE, ProtectionEnchantment.Type.EXPLOSION, armorSlots, "Blast Protection"));
        createEnchantment(new ProtectionEnchantmentImp(ID_PROTECTION_FALL, Enchantment.Rarity.UNCOMMON, ProtectionEnchantment.Type.FALL, armorSlots, "Feather Falling"));
        createEnchantment(new ProtectionEnchantmentImp(ID_PROTECTION_FIRE, Enchantment.Rarity.UNCOMMON, ProtectionEnchantment.Type.FIRE, armorSlots, "Fire Protection"));
        createEnchantment(new ProtectionEnchantmentImp(ID_PROTECTION_PROJECTILE, Enchantment.Rarity.UNCOMMON, ProtectionEnchantment.Type.PROJECTILE, armorSlots, "Projectile Protection"));

        createEnchantment(new FireAspectEnchantmentImp(ID_FIRE_ASPECT));
        createEnchantment(new FrostWalkerEnchantmentImp(ID_FROST_WALKER));
        createEnchantment(new KnockbackEnchantmentImp(ID_KNOCKBACK));
        createEnchantment(new LootingEnchantmentImp(ID_LOOTING));
        createEnchantment(new ThornsEnchantmentImp(ID_THORNS));

        overrideMending = createEnchantment(new MendingEnchantmentAlt(ID_MENDING));

        createEnchantment(new HoldingEnchantment(ID_HOLDING));
        createEnchantment(new SoulboundEnchantment(ID_SOULBOUND));

        createEnchantment(new AirWorkerEnchantment(ID_AIR_WORKER));
        createEnchantment(new GourmandEnchantment(ID_GOURMAND));

        createEnchantment(new DisplacementEnchantment(ID_DISPLACEMENT));

        // createEnchantment(new SmashingEnchantment(ID_SMASHING));
        // createEnchantment(new SmeltingEnchantment(ID_SMELTING));

        createEnchantment(new VorpalEnchantment(ID_VORPAL));

        createEnchantment(new InsightEnchantment(ID_INSIGHT));
        createEnchantment(new LeechEnchantment(ID_LEECH));

        createEnchantment(new HunterEnchantment(ID_HUNTER));
        createEnchantment(new QuickdrawEnchantment(ID_QUICKDRAW));
        createEnchantment(new TrueshotEnchantment(ID_TRUESHOT));
        createEnchantment(new VolleyEnchantment(ID_VOLLEY));

        createEnchantment(new AnglerEnchantment(ID_ANGLER));

        createEnchantment(new BulwarkEnchantment(ID_BULWARK));
        createEnchantment(new PhalanxEnchantment(ID_PHALANX));
    }

    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {

        IForgeRegistry<Enchantment> registry = event.getRegistry();

        for (EnchantmentCoFH ench : enchantments) {
            registry.register(ench);
        }
    }

    private static EnchantmentCoFH createEnchantment(EnchantmentCoFH ench) {

        enchantments.add(ench);
        configurables.add(ench);
        return ench;
    }

    private static List<EnchantmentCoFH> enchantments = new ArrayList<>();

    public static EnchantmentCoFH overrideMending;

}
