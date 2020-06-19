package cofh.core.event;

import cofh.core.init.CoreConfig;
import cofh.lib.util.helpers.StringHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

import static cofh.lib.util.constants.NBTTags.TAG_STORED_ENCHANTMENTS;
import static cofh.lib.util.helpers.StringHelper.getInfoTextComponent;
import static net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND;

public class CoreClientEvents {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(CoreClientEvents.class);
        registered = true;
    }

    private CoreClientEvents() {

    }

    @SubscribeEvent
    public static void handleItemTooltipEvent(ItemTooltipEvent event) {

        List<ITextComponent> tooltip = event.getToolTip();
        if (tooltip.isEmpty()) {
            return;
        }
        ItemStack stack = event.getItemStack();
        if (CoreConfig.enableItemDescriptions) {
            String infoKey = stack.getItem().getTranslationKey(stack) + ".desc";
            if (StringHelper.canLocalize(infoKey)) {
                event.getToolTip().add(getInfoTextComponent(infoKey));
            }
        }
        if (CoreConfig.enableEnchantmentDescriptions) {
            if (stack.getTag() == null) {
                return;
            }
            ListNBT list = stack.getTag().getList(TAG_STORED_ENCHANTMENTS, TAG_COMPOUND);
            if (list.size() == 1) {
                Enchantment ench = ForgeRegistries.ENCHANTMENTS.getValue(ResourceLocation.tryCreate(list.getCompound(0).getString("id")));
                if (ench != null && ench.getRegistryName() != null) {
                    String enchKey = ench.getName() + ".desc";
                    if (StringHelper.canLocalize(enchKey)) {
                        event.getToolTip().add(getInfoTextComponent(enchKey));
                    }
                }
            }
        }
    }

}
