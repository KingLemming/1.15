//package cofh.lib.block;
//
//import cofh.core.network.PacketBufferCoFH;
//import cofh.core.network.packet.client.PacketTileGui;
//import cofh.lib.util.Utils;
//import cofh.lib.util.control.ISecurable;
//import net.minecraft.block.Block;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.player.EntityPlayerMP;
//import net.minecraft.entity.player.InventoryPlayer;
//import net.minecraft.inventory.Container;
//import net.minecraft.inventory.IContainerListener;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.network.NetworkManager;
//import net.minecraft.network.play.server.SPacketUpdateTileEntity;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.EnumSkyBlock;
//import net.minecraft.world.World;
//import net.minecraftforge.common.property.IExtendedBlockState;
//import net.minecraftforge.common.util.FakePlayer;
//
//import javax.annotation.Nullable;
//import java.util.HashMap;
//import java.util.Map;
//
//import static cofh.lib.util.Constants.MODEL_PROPERTIES;
//
//public abstract class TileCoFH2 extends TileEntity implements ITileCallback {
//
//	protected IBlockState blockState;
//
//	@Override
//	public void onChunkUnload() {
//
//		if (!tileEntityInvalid) {
//			invalidate();
//		}
//	}
//
//	@Override
//	public void onLoad() {
//
//		if (Utils.isClientWorld(world) && !hasClientUpdate()) {
//			world.tickableTileEntities.remove(this);
//		}
//		validate();
//	}
//
//	@Override
//	public void markDirty() {
//
//		if (this.world == null) {
//			return;
//		}
//		this.world.markChunkDirty(this.pos, this);
//		this.world.updateComparatorOutputLevel(this.pos, this.getBlockType());
//	}
//
//	@Override
//	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
//
//		if (oldState.getBlock() != newState.getBlock()) {
//			return true;
//		}
//		this.blockState = newState;
//		return false;
//	}
//
//	@Override
//	public void updateContainingBlockInfo() {
//
//		super.updateContainingBlockInfo();
//		this.blockState = getBlockState();
//	}
//
//	protected IBlockState getBlockState() {
//
//		if (this.blockState == null && this.world != null) {
//			updateBlockState();
//		}
//		return this.blockState;
//	}
//
//	protected void updateBlockState() {
//
//		this.blockState = this.world.getBlockState(this.pos);
//	}
//
//	// region PASSTHROUGHS
//	protected void onBlockBroken(World world, BlockPos pos, IBlockState state) {
//
//	}
//
//	protected void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
//
//	}
//
//	protected void onNeighborBlockChange() {
//
//	}
//
//	protected void onNeighborTileChange(BlockPos pos) {
//
//	}
//
//	protected boolean canDismantle(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
//
//		return canPlayerChange(player);
//	}
//
//	protected int getComparatorInputOverride() {
//
//		return 0;
//	}
//
//	protected int getLightValue() {
//
//		return 0;
//	}
//
//	protected float getBlockHardness(float blockHardness) {
//
//		return blockHardness;
//	}
//
//	protected float getExplosionResistance(float blockResistance) {
//
//		return blockResistance / 5.0F;
//	}
//
//	protected float getPlayerRelativeBlockHardness(float blockHardness, IBlockState state, EntityPlayer player) {
//
//		return canPlayerChange(player) ? blockHardness : -1.0F;
//	}
//
//	protected IBlockState getExtendedState(IBlockState state) {
//
//		if (state instanceof IExtendedBlockState) {
//			Map<String, String> map = new HashMap<>(); // TODO: Pool these.
//			buildModelProps(map);
//			return ((IExtendedBlockState) state).withProperty(MODEL_PROPERTIES, map);
//		}
//		return state;
//	}
//
//	protected NBTTagCompound getItemStackTag() {
//
//		return null;
//	}
//	// endregion
//
//	// region HELPERS
//	protected void updateLighting() {
//
//		int light = world.getLightFor(EnumSkyBlock.BLOCK, pos);
//		if (getLightValue() != light && world.checkLightFor(EnumSkyBlock.BLOCK, pos)) {
//			IBlockState state = world.getBlockState(pos);
//			world.notifyBlockUpdate(pos, state, state, 3);
//		}
//	}
//
//	protected boolean hasClientUpdate() {
//
//		return false;
//	}
//
//	public boolean canPlayerChange(EntityPlayer player) {
//
//		return !(this instanceof ISecurable) || ((ISecurable) this).canAccess(player);
//	}
//
//	public boolean playerWithinDistance(EntityPlayer player, double distanceSq) {
//
//		return player.getDistanceSq(pos) <= distanceSq && world.getTileEntity(pos) == this;
//	}
//
//	public boolean isUsableByPlayer(EntityPlayer player) {
//
//		return player.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D && world.getTileEntity(pos) == this;
//	}
//
//	public boolean onWrench(EntityPlayer player, EnumFacing side) {
//
//		return false;
//	}
//	// endregion
//
//	// region TIME CHECKS
//	public static final int TIME_CONSTANT = 32;
//	public static final int TIME_CONSTANT_HALF = TIME_CONSTANT / 2;
//	public static final int TIME_CONSTANT_QUARTER = TIME_CONSTANT / 4;
//	public static final int TIME_CONSTANT_EIGHTH = TIME_CONSTANT / 8;
//
//	protected final boolean timeCheck() {
//
//		return world.getTotalWorldTime() % TIME_CONSTANT == 0;
//	}
//
//	protected final boolean timeCheckHalf() {
//
//		return world.getTotalWorldTime() % TIME_CONSTANT_HALF == 0;
//	}
//
//	protected final boolean timeCheckQuarter() {
//
//		return world.getTotalWorldTime() % TIME_CONSTANT_QUARTER == 0;
//	}
//
//	protected final boolean timeCheckEighth() {
//
//		return world.getTotalWorldTime() % TIME_CONSTANT_EIGHTH == 0;
//	}
//	// endregion
//
//	// region GUI
//	public Object getGuiClient(InventoryPlayer inventory) {
//
//		return null;
//	}
//
//	public Object getGuiServer(InventoryPlayer inventory) {
//
//		return null;
//	}
//
//	public boolean openGui(EntityPlayer player) {
//
//		return false;
//	}
//
//	public void receiveGuiNetworkData(int id, int data) {
//
//	}
//
//	public void sendGuiNetworkData(Container container, IContainerListener player) {
//
//		if (player instanceof EntityPlayerMP && (!(player instanceof FakePlayer))) {
//			PacketTileGui.sendToClient(this, (EntityPlayerMP) player);
//		}
//	}
//	// endregion
//
//	// region NETWORK
//	@Nullable
//	@Override
//	public SPacketUpdateTileEntity getUpdatePacket() {
//
//		return new SPacketUpdateTileEntity(pos, 0, getUpdateTag());
//	}
//
//	@Override
//	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
//
//		readFromNBT(pkt.getNbtCompound());
//	}
//
//	@Override
//	public NBTTagCompound getUpdateTag() {
//
//		return this.writeToNBT(new NBTTagCompound());
//	}
//
//	public PacketBufferCoFH getControlPacket(PacketBufferCoFH buffer) {
//
//		return buffer;
//	}
//
//	public PacketBufferCoFH getGuiPacket(PacketBufferCoFH buffer) {
//
//		return buffer;
//	}
//
//	public PacketBufferCoFH getStatePacket(PacketBufferCoFH buffer) {
//
//		return buffer;
//	}
//
//	public void handleControlPacket(PacketBufferCoFH buffer) {
//
//	}
//
//	public void handleGuiPacket(PacketBufferCoFH buffer) {
//
//	}
//
//	public void handleStatePacket(PacketBufferCoFH buffer) {
//
//	}
//
//	public void setActive(boolean active) {
//
//	}
//	// endregion
//
//	// region MODELS
//	public void buildModelProps(Map<String, String> properties) {
//
//	}
//	// endregion
//
//	// region ITileCallback
//	@Override
//	public Block block() {
//
//		return blockType;
//	}
//
//	@Override
//	public BlockPos pos() {
//
//		return pos;
//	}
//
//	@Override
//	public World world() {
//
//		return world;
//	}
//
//	@Override
//	public int invSize() {
//
//		return 0;
//	}
//	// endregion
//}
