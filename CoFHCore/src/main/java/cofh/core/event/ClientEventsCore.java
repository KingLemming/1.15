package cofh.core.event;

import cofh.core.init.ConfigCore;
import cofh.lib.util.helpers.StringHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import static cofh.lib.util.constants.Tags.TAG_STORED_ENCHANTMENTS;
import static cofh.lib.util.helpers.StringHelper.getInfoTextComponent;
import static net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND;

public class ClientEventsCore {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(ClientEventsCore.class);
        registered = true;
    }

    private ClientEventsCore() {

    }

    @SubscribeEvent
    public static void handleItemTooltipEvent(ItemTooltipEvent event) {

        if (!ConfigCore.enableEnchantmentDescriptions) {
            return;
        }
        ItemStack stack = event.getItemStack();
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
