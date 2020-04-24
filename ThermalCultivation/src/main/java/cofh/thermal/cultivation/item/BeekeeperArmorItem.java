package cofh.thermal.cultivation.item;

import cofh.lib.item.ArmorItemCoFH;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

import java.util.UUID;

import static cofh.core.init.CoreAttributes.STING_RESISTANCE;

public class BeekeeperArmorItem extends ArmorItemCoFH {

    private static final UUID[] UUID_STING_RESISTANCE = new UUID[]{
            UUID.fromString("8CEB464F-F750-4A90-850E-E0D77A6C812E"),
            UUID.fromString("7356C745-0EB7-4266-B0E5-899967D45025"),
            UUID.fromString("EED777DD-5E25-4648-BE4b-12AF4430AA7D"),
            UUID.fromString("66785022-03BE-4987-BF53-BD392DB96DB0")
    };

    private static final double[] PROTECTION_AMOUNT = new double[]{0.10D, 0.25D, 0.40D, 0.25D};

    public BeekeeperArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {

        super(materialIn, slot, builder);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {

        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
        if (slot == this.slot) {
            multimap.put(STING_RESISTANCE.getName(), new AttributeModifier(UUID_STING_RESISTANCE[slot.getIndex()], "Sting Resistance", PROTECTION_AMOUNT[slot.getIndex()], AttributeModifier.Operation.ADDITION));
        }
        return multimap;
    }

}
