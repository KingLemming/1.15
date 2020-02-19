//package cofh.thermal.expansion.tileentity;
//
//import cofh.lib.inventory.ItemStorageCoFH;
//import cofh.thermal.core.tileentity.DynamoTileBase;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.inventory.container.Container;
//
//import javax.annotation.Nullable;
//
//public class DynamoStirlingTile extends DynamoTileBase {
//
//    protected ItemStorageCoFH fuelSlot = new ItemStorageCoFH(StirlingFuelManager.instance()::validFuel);
//
//    public TileDynamoStirling() {
//
//        super(DynamosTE.STIRLING);
//
//        inventory.addSlot(fuelSlot, INPUT);
//    }
//
//    // region PROCESS
//    protected boolean canProcessStart() {
//
//        return StirlingFuelManager.instance().getEnergy(fuelSlot.getItemStack()) > 0;
//    }
//
//    protected void processStart() {
//
//        fuel += StirlingFuelManager.instance().getEnergy(fuelSlot.getItemStack());
//        fuelSlot.consume();
//    }
//    // endregion
//
//    @Nullable
//    @Override
//    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
//
//        return null;
//    }
//
//}
