package cofh.core.item;

import cofh.core.CoFHCore;
import cofh.lib.util.helpers.SecurityHelper;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static cofh.lib.util.constants.Constants.TRUE;

public class ArmorItemCoFH extends ArmorItem {

    protected static final double[] RESISTANCE_RATIO = new double[]{0.10D, 0.25D, 0.40D, 0.25D};

    protected static final UUID UUID_FALL_DISTANCE = UUID.fromString("3262A4A1-72DB-4A4E-8F67-8691f6E49C8B");

    protected static final UUID[] UUID_HAZARD_RESISTANCE = new UUID[]{
            UUID.fromString("301B15CD-450C-4D08-AD16-9B1F1F3322CC"),
            UUID.fromString("6060D3DF-0B01-40DB-9056-BA87E1779344"),
            UUID.fromString("DE8D2ABD-A382-43BF-8BAA-06965C3B7C19"),
            UUID.fromString("45ED4DDF-2AFD-40D3-8DD4-E82AD97DEBD6")
    };

    protected static final UUID[] UUID_STING_RESISTANCE = new UUID[]{
            UUID.fromString("8CEB464F-F750-4A90-850E-E0D77A6C812E"),
            UUID.fromString("7356C745-0EB7-4266-B0E5-899967D45025"),
            UUID.fromString("EED777DD-5E25-4648-BE4b-12AF4430AA7D"),
            UUID.fromString("66785022-03BE-4987-BF53-BD392DB96DB0")
    };

    protected BooleanSupplier showEnchantEffect = TRUE;
    protected BooleanSupplier showInItemGroup = TRUE;

    protected Supplier<ItemGroup> displayGroup;

    public ArmorItemCoFH(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {

        super(materialIn, slot, builder);
    }

    public ArmorItemCoFH setDisplayGroup(Supplier<ItemGroup> displayGroup) {

        this.displayGroup = displayGroup;
        return this;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {

        if (!showInItemGroup.getAsBoolean()) {
            return;
        }
        super.fillItemGroup(group, items);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean hasEffect(ItemStack stack) {

        return showEnchantEffect.getAsBoolean() && stack.isEnchanted();
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {

        return SecurityHelper.hasSecurity(stack);
    }

    @Override
    protected boolean isInGroup(ItemGroup group) {

        return group == ItemGroup.SEARCH || getCreativeTabs().stream().anyMatch(tab -> tab == group);
    }

    @Override
    public Collection<ItemGroup> getCreativeTabs() {

        return displayGroup != null && displayGroup.get() != null ? Collections.singletonList(displayGroup.get()) : super.getCreativeTabs();
    }

    @OnlyIn(Dist.CLIENT)
    @Nullable
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {

        return (A) CoFHCore.proxy.getModel(this.getRegistryName());
    }

}
