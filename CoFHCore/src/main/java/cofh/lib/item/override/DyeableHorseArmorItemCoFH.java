package cofh.lib.item.override;

import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.util.ResourceLocation;

public class DyeableHorseArmorItemCoFH extends HorseArmorItemCoFH implements IDyeableArmorItem {

    public DyeableHorseArmorItemCoFH(int protection, String texture, Properties properties) {

        super(protection, texture, properties);
    }

    public DyeableHorseArmorItemCoFH(int protection, ResourceLocation texture, Properties properties) {

        super(protection, texture, properties);
    }

}
