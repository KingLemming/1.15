package cofh.thermal.core.util.loot;

import cofh.lib.util.control.IReconfigurableTile;
import cofh.lib.util.control.ISecurableTile;
import cofh.lib.util.control.ITransferControllableTile;
import cofh.thermal.core.tileentity.ThermalTileBase;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.lib.util.constants.NBTTags.*;
import static cofh.thermal.core.common.ThermalConfig.*;
import static net.minecraft.world.storage.loot.LootParameters.BLOCK_ENTITY;

public class TileNBTSync extends LootFunction {

    protected TileNBTSync(ILootCondition[] conditionsIn) {

        super(conditionsIn);
    }

    @Override
    protected ItemStack doApply(ItemStack stack, LootContext context) {

        return applyToStack(stack, context.get(BLOCK_ENTITY));
    }

    public static ItemStack applyToStack(ItemStack stack, TileEntity tile) {

        if (tile instanceof ThermalTileBase) {
            ThermalTileBase castedTile = (ThermalTileBase) tile;

            CompoundNBT tag = new CompoundNBT();
            if (keepEnergy.get()) {
                castedTile.getEnergyStorage().writeToNBT(tag);
            }
            if (keepItems.get()) {
                castedTile.getItemInv().writeSlotsToNBT(tag, 0, castedTile.invSize() - castedTile.augSize());
            }
            if (keepAugments.get() && castedTile.augSize() > 0) {
                castedTile.getItemInv().writeSlotsToNBTUnordered(tag, TAG_AUGMENTS, castedTile.invSize() - castedTile.augSize());
            }
            if (keepFluids.get()) {
                castedTile.getTankInv().writeToNBT(tag);
            }
            if (keepRSControl.get()) {
                tag.put(TAG_REDSTONE, castedTile.redstoneControl().write(new CompoundNBT()));
            }
            if (keepSideConfig.get() && tile instanceof IReconfigurableTile) {
                tag.put(TAG_SIDE_CONFIG, ((IReconfigurableTile) tile).reconfigControl().write(new CompoundNBT()));
            }
            if (keepTransferControl.get() && tile instanceof ITransferControllableTile) {
                tag.put(TAG_TRANSFER, ((ITransferControllableTile) tile).transferControl().write(new CompoundNBT()));
            }
            if (((ISecurableTile) tile).hasSecurity()) {
                tag.put(TAG_SECURITY, castedTile.securityControl().write(new CompoundNBT()));
            }
            if (!tag.isEmpty()) {
                stack.setTagInfo(TAG_BLOCK_ENTITY, tag);
            }
        }
        return stack;
    }

    public static class Serializer extends LootFunction.Serializer<TileNBTSync> {

        public Serializer() {

            super(new ResourceLocation(ID_THERMAL + ":nbt_sync"), TileNBTSync.class);
        }

        public TileNBTSync deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {

            return new TileNBTSync(conditionsIn);
        }

    }

}
