// TODO: Re-implement?
//package cofh.lib.inventory.container;
//
//import cofh.lib.tileentity.TileCoFH;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.inventory.IContainerListener;
//import net.minecraft.tileentity.TileEntity;
//
//public class ContainerAugmentableTile extends ContainerCoFH {
//
//    protected final TileCoFH baseTile;
//
//    public ContainerAugmentableTile(PlayerInventory inventory, TileEntity tile) {
//
//        baseTile = tile instanceof TileCoFH ? (TileCoFH) tile : null;
//    }
//
//    @Override
//    protected int getPlayerInventoryVerticalOffset() {
//
//        return 84;
//    }
//
//    @Override
//    protected int getSizeInventory() {
//
//        return baseTile == null ? 0 : baseTile.invSize();
//    }
//
//    @Override
//    public boolean canInteractWith(PlayerEntity player) {
//
//        return baseTile == null || baseTile.playerWithinDistance(player, 64D);
//    }
//
//    @Override
//    public void detectAndSendChanges() {
//
//        super.detectAndSendChanges();
//        if (baseTile == null) {
//            return;
//        }
//        for (IContainerListener listener : listeners) {
//            baseTile.sendGuiNetworkData(this, listener);
//        }
//    }
//
//    @Override
//    public void updateProgressBar(int i, int j) {
//
//        if (baseTile == null) {
//            return;
//        }
//        baseTile.receiveGuiNetworkData(i, j);
//    }
//
//}
