package cofh.ensorcellation.enchantment;

import cofh.lib.enchantment.DamageEnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class DamageIllagerEnchantment extends DamageEnchantmentCoFH {

    public DamageIllagerEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentType.WEAPON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 5;
    }

    // TODO: Revisit if Ravagers and Witches are reclassified in future.
    //    @Override
    //    public float calcDamageByCreature(int level, CreatureAttribute creatureType) {
    //
    //        return creatureType == CreatureAttribute.ILLAGER ? getExtraDamage(level) : 0.0F;
    //    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof SwordItem || item instanceof AxeItem || supportsEnchantment(stack));
    }

    public static boolean validTarget(Entity entity) {

        return entity instanceof AbstractRaiderEntity;
    }

}
