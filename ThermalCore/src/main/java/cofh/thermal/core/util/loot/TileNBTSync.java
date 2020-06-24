package cofh.thermal.core.util.loot;

import cofh.lib.util.control.IReconfigurableTile;
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

        TileEntity tile = context.get(BLOCK_ENTITY);
        if (tile instanceof ThermalTileBase) {
            CompoundNBT tag = new CompoundNBT();
            if (keepEnergy) {
                if (((ThermalTileBase) tile).getEnergyStorage().getEnergyStored() > 0) {
                    ((ThermalTileBase) tile).getEnergyStorage().writeToNBT(tag);
                }
            }
            if (keepItems) {
                ((ThermalTileBase) tile).getItemInv().writeToNBT(tag);
            } else if (keepAugments) {
                //                ((ThermalTileBase) tile).getAugments().writeToNBT(tag);
            }
            if (keepFluids) {
                ((ThermalTileBase) tile).getTankInv().writeToNBT(tag);
            }

            if (enableSecurity) {
                tag.put(TAG_SECURITY, ((ThermalTileBase) tile).securityControl().write(new CompoundNBT()));
            }
            if (keepRSControl) {
                tag.put(TAG_REDSTONE, ((ThermalTileBase) tile).redstoneControl().write(new CompoundNBT()));
            }
            if (keepSideConfig && tile instanceof IReconfigurableTile) {
                tag.put(TAG_SIDE_CONFIG, ((IReconfigurableTile) tile).reconfigControl().write(new CompoundNBT()));
            }
            if (keepTransferControl && tile instanceof ITransferControllableTile) {
                tag.put(TAG_TRANSFER, ((ITransferControllableTile) tile).transferControl().write(new CompoundNBT()));
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
