package cofh.thermal.core.item;

import cofh.core.item.ArmorItemCoFH;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

import static cofh.core.init.CoreAttributes.STING_RESISTANCE;

public class BeekeeperArmorItem extends ArmorItemCoFH {

    public BeekeeperArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {

        super(materialIn, slot, builder);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {

        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
        if (slot == this.slot) {
            multimap.put(STING_RESISTANCE.getName(), new AttributeModifier(UUID_STING_RESISTANCE[slot.getIndex()], "Sting Resistance", RESISTANCE_RATIO[slot.getIndex()], AttributeModifier.Operation.ADDITION));
        }
        return multimap;
    }

}
