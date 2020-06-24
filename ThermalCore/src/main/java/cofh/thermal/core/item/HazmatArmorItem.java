package cofh.thermal.core.item;

import cofh.core.item.ArmorItemCoFH;
import cofh.lib.util.Utils;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.World;

import static cofh.core.init.CoreAttributes.FALL_DISTANCE;
import static cofh.core.init.CoreAttributes.HAZARD_RESISTANCE;

public class HazmatArmorItem extends ArmorItemCoFH {

    public HazmatArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {

        super(materialIn, slot, builder);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {

        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
        if (slot == this.slot) {
            multimap.put(HAZARD_RESISTANCE.getName(), new AttributeModifier(UUID_HAZARD_RESISTANCE[slot.getIndex()], "Hazard Resistance", RESISTANCE_RATIO[slot.getIndex()], AttributeModifier.Operation.ADDITION));
            if (this.slot == EquipmentSlotType.FEET) {
                multimap.put(FALL_DISTANCE.getName(), new AttributeModifier(UUID_FALL_DISTANCE, "Fall Distance", 6, AttributeModifier.Operation.ADDITION));
            }
        }
        return multimap;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {

        if (this.slot == EquipmentSlotType.HEAD) {
            // TODO: Revisit
            //            if (player.getAir() < player.getMaxAir()) {
            //                // Random chance of extra air?
            //            }
            // Current method
            if (!player.areEyesInFluid(FluidTags.WATER)) {
                Utils.addPotionEffectNoEvent(player, new EffectInstance(Effects.WATER_BREATHING, 600, 0, false, false, true));
            }
        }
    }

}
