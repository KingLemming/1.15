package cofh.ensorcellment.event;

import cofh.ensorcellment.enchantment.PhalanxEnchantment;
import cofh.ensorcellment.init.ConfigEnsorc;
import cofh.lib.util.helpers.MathHelper;
import cofh.lib.util.helpers.StringHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import static cofh.lib.util.Constants.TAG_STORED_ENCHANTMENTS;
import static cofh.lib.util.modhelpers.EnsorcellmentHelper.PHALANX;

public class ClientEventsEnsorc {

    private static final ClientEventsEnsorc INSTANCE = new ClientEventsEnsorc();
    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        registered = true;
    }

    private ClientEventsEnsorc() {

    }

    private boolean hadPhalanx;
    private double modPhalanx;
    private long timePhalanx;

    @SubscribeEvent
    public void handleFOVUpdateEvent(FOVUpdateEvent event) {

        PlayerEntity entity = event.getEntity();
        ItemStack stack = event.getEntity().getActiveItemStack();

        if (stack.getItem().isShield(stack, entity)) {
            int encPhalanx = EnchantmentHelper.getEnchantmentLevel(PHALANX, stack);
            if (encPhalanx > 0) {
                modPhalanx = encPhalanx * PhalanxEnchantment.SPEED / 2D;
                hadPhalanx = true;
                timePhalanx = entity.world.getGameTime();
            }
            event.setNewfov((float) MathHelper.clamp(event.getFov() - modPhalanx, 1.0D, 2.5D));
        } else if (hadPhalanx) {
            if (entity.world.getGameTime() - 20 > timePhalanx) {
                hadPhalanx = false;
                modPhalanx = 0;
            }
            event.setNewfov((float) MathHelper.clamp(event.getFov() - modPhalanx, 1.0D, 2.5D));
        }
    }

    @SubscribeEvent
    public void handleItemTooltipEvent(ItemTooltipEvent event) {

        if (!ConfigEnsorc.showEnchDescriptions) {
            return;
        }
        ItemStack stack = event.getItemStack();
        if (stack.getTag() != null) {
            ListNBT list = stack.getTag().getList(TAG_STORED_ENCHANTMENTS, 10);
            if (list.size() == 1) {
                Enchantment ench = ForgeRegistries.ENCHANTMENTS.getValue(ResourceLocation.tryCreate(list.getCompound(0).getString("id")));
                if (ench != null && ench.getRegistryName() != null) {
                    String enchKey = "enchantment." + ench.getRegistryName().getNamespace() + "." + ench.getRegistryName().getPath() + ".desc";
                    if (StringHelper.canLocalize(enchKey)) {
                        event.getToolTip().add(new StringTextComponent(StringHelper.localize(enchKey)).applyTextStyle(TextFormatting.GREEN));
                    }
                }
            }
        }
    }

}
