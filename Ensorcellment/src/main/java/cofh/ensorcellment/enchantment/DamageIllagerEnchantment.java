package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.DamageEnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.CreatureAttribute;
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

    @Override
    public float calcDamageByCreature(int level, CreatureAttribute creatureType) {

        return creatureType == CreatureAttribute.ILLAGER ? getExtraDamage(level) : 0.0F;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 10 + (level - 1) * 8;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 20;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof SwordItem || item instanceof AxeItem || supportsEnchantment(stack));
    }

    // TODO: Revisit
//    public static boolean validTarget(Entity entity) {
//
//        return entity instanceof AbstractRaiderEntity;
//    }

}
