package cofh.thermal.expansion.plugins.jei.machine;

import cofh.thermal.core.plugins.jei.ThermalCatalystCategory;
import cofh.thermal.expansion.util.recipes.machine.InsolatorCatalyst;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static cofh.core.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_INSOLATOR_BLOCK;

public class InsolatorCatalystCategory extends ThermalCatalystCategory<InsolatorCatalyst> {

    public InsolatorCatalystCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        name = getTextComponent(MACHINE_INSOLATOR_BLOCK.getTranslationKey()).appendText(": ").appendSibling(getTextComponent("info.thermal.catalysts"));
    }

    @Override
    public Class<? extends InsolatorCatalyst> getRecipeClass() {

        return InsolatorCatalyst.class;
    }

}
