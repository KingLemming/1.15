//package cofh.test.item;
//
//import baubles.api.BaubleType;
//import baubles.api.IBauble;
//import cofh.core.key.KeyMultiModeItem;
//import cofh.core.util.ChatHelper;
//import cofh.lib.item.IFilterContainerItem;
//import cofh.lib.item.IMultiModeItem;
//import cofh.lib.util.RayTracer;
//import cofh.lib.util.Utils;
//import cofh.lib.util.filter.ItemFilterWrapper;
//import cofh.lib.util.helpers.ColorHelper;
//import cofh.thermal.core.init.SoundsTSeries;
//import cofh.thermal.core.item.ItemRFContainer;
//import cofh.thermal.innovation.ThermalInnovation;
//import net.minecraft.client.renderer.block.model.ModelBakery;
//import net.minecraft.client.renderer.block.model.ModelResourceLocation;
//import net.minecraft.client.util.ITooltipFlag;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.item.EntityItem;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.SoundCategory;
//import net.minecraft.util.math.AxisAlignedBB;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.RayTraceResult;
//import net.minecraft.util.text.TextComponentTranslation;
//import net.minecraft.world.World;
//import net.minecraftforge.client.model.ModelLoader;
//import net.minecraftforge.fml.common.Optional;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.apache.commons.io.FileUtils;
//
//import javax.annotation.Nullable;
//import java.io.File;
//import java.nio.charset.Charset;
//import java.util.List;
//
//import static cofh.core.util.CoreUtils.configDir;
//import static cofh.lib.util.Constants.TAG_CONVEYOR_COMPAT;
//import static cofh.lib.util.Constants.TINT_INDEX_2;
//import static cofh.lib.util.helpers.StringHelper.*;
//import static cofh.thermal.innovation.gui.GuiHandler.GUI_MAGNET_FILTER;
//
//@Optional.Interface (iface = "baubles.api.IBauble", modid = "baubles")
//public class ItemRFMagnet extends ItemRFContainer implements IFilterContainerItem, IMultiModeItem, IBauble {
//
//	protected static final int ENERGY_PER_ITEM = 25;
//	protected static final int ENERGY_PER_USE = 250;
//	protected static final int TIME_CONSTANT = 8;
//
//	protected int radius;
//	protected int filterSize;
//
//	public ItemRFMagnet(int maxEnergy, int maxReceive, int radius, int filterSize) {
//
//		super(maxEnergy, maxReceive);
//		this.radius = radius;
//		this.filterSize = filterSize;
//	}
//
//	@Override
//	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
//
//		tooltip.add(getInfoText("info.thermal.rf_magnet.0"));
//		if (displayShiftForDetail && !isShiftKeyDown()) {
//			tooltip.add(shiftForDetails());
//		}
//		if (!isShiftKeyDown()) {
//			return;
//		}
//		tooltip.add(localize("info.thermal.rf_magnet.1"));
//		tooltip.add(getNoticeText("info.thermal.rf_magnet.2"));
//		tooltip.add(localizeFormat("info.thermal.rf_magnet.a." + getMode(stack), getKeyName(KeyMultiModeItem.INSTANCE.getKey())));
//		tooltip.add(ORANGE + localize("info.cofh.radius") + ": " + radius + END);
//
//		super.addInformation(stack, worldIn, tooltip, flagIn);
//	}
//
//	@Override
//	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
//
//		if (world.getTotalWorldTime() % TIME_CONSTANT != 0) {
//			return;
//		}
//		if (!(entity instanceof EntityLivingBase) || Utils.isFakePlayer(entity) || getMode(stack) == 0) {
//			return;
//		}
//		EntityPlayer player = (EntityPlayer) entity;
//
//		if (getEnergyStored(stack) < ENERGY_PER_ITEM && !player.capabilities.isCreativeMode) {
//			return;
//		}
//		int radSq = radius * radius;
//
//		AxisAlignedBB area = new AxisAlignedBB(player.getPosition().add(-radius, -radius, -radius), player.getPosition().add(1 + radius, 1 + radius, 1 + radius));
//		List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, area, EntitySelectors.IS_ALIVE);
//		ItemFilterWrapper wrapper = new ItemFilterWrapper(stack, getSizeFilter(stack));
//
//		if (Utils.isClientWorld(world)) {
//			for (EntityItem item : items) {
//				if (item.cannotPickup() || item.getEntityData().getBoolean(TAG_CONVEYOR_COMPAT)) {
//					continue;
//				}
//				if (item.getPositionVector().squareDistanceTo(player.getPositionVector()) <= radSq && wrapper.getFilter().matches(item.getItem())) {
//					world.spawnParticle(EnumParticleTypes.REDSTONE, item.posX, item.posY, item.posZ, 0, 0, 0, 0);
//				}
//			}
//		} else {
//			int itemCount = 0;
//			for (EntityItem item : items) {
//				if (item.cannotPickup() || item.getEntityData().getBoolean(TAG_CONVEYOR_COMPAT)) {
//					continue;
//				}
//				if (item.getThrower() == null || !item.getThrower().equals(player.getName()) || item.age >= 8 * TIME_CONSTANT) {
//					if (item.getPositionVector().squareDistanceTo(player.getPositionVector()) <= radSq && wrapper.getFilter().matches(item.getItem())) {
//						item.setPosition(player.posX, player.posY, player.posZ);
//						item.setPickupDelay(0);
//						itemCount++;
//					}
//				}
//			}
//			if (!player.capabilities.isCreativeMode) {
//				extractEnergy(stack, ENERGY_PER_ITEM * itemCount, false);
//			}
//		}
//	}
//
//	@Override
//	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
//
//		ItemStack stack = player.getHeldItem(hand);
//		if (Utils.isFakePlayer(player)) {
//			return new ActionResult<>(EnumActionResult.FAIL, stack);
//		}
//		if (player.isSneaking()) {
//			player.openGui(ThermalInnovation.instance, GUI_MAGNET_FILTER, world, 0, 0, 0);
//		} else if (getEnergyStored(stack) >= ENERGY_PER_USE || player.capabilities.isCreativeMode) {
//			RayTraceResult traceResult = RayTracer.retrace(player, 64);
//			if (traceResult == null || traceResult.typeOfHit != RayTraceResult.Type.BLOCK) {
//				return ActionResult.newResult(EnumActionResult.PASS, stack);
//			}
//			int radSq = radius * radius;
//
//			AxisAlignedBB area = new AxisAlignedBB(traceResult.getBlockPos().add(-radius, -radius, -radius), traceResult.getBlockPos().add(1 + radius, 1 + radius, 1 + radius));
//			List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, area, EntitySelectors.IS_ALIVE);
//			ItemFilterWrapper wrapper = new ItemFilterWrapper(stack, getSizeFilter(stack));
//
//			if (Utils.isClientWorld(world)) {
//				for (EntityItem item : items) {
//					if (item.getPositionVector().squareDistanceTo(traceResult.hitVec) <= radSq && wrapper.getFilter().matches(item.getItem())) {
//						world.spawnParticle(EnumParticleTypes.PORTAL, item.posX, item.posY, item.posZ, 0, 0, 0, 0);
//					}
//				}
//			} else {
//				int itemCount = 0;
//				for (EntityItem item : items) {
//					if (item.getPositionVector().squareDistanceTo(traceResult.hitVec) <= radSq && wrapper.getFilter().matches(item.getItem())) {
//						item.setPosition(player.posX, player.posY, player.posZ);
//						item.setPickupDelay(0);
//						itemCount++;
//					}
//				}
//				if (!player.capabilities.isCreativeMode) {
//					extractEnergy(stack, ENERGY_PER_USE + ENERGY_PER_ITEM * itemCount, false);
//				}
//			}
//			player.swingArm(hand);
//			stack.setAnimationsToGo(5);
//			player.world.playSound(null, player.getPosition(), SoundsTSeries.magnetUse, SoundCategory.PLAYERS, 0.4F, 1.0F);
//		}
//		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
//	}
//
//	@Override
//	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
//
//		return EnumActionResult.FAIL;
//	}
//
//	// region IFilterContainerItem
//	public int getSizeFilter(ItemStack stack) {
//
//		return filterSize;
//	}
//	// endregion
//
//	// region IMultiModeItem
//	@Override
//	public void onModeChange(EntityPlayer player, ItemStack stack) {
//
//		player.world.playSound(null, player.getPosition(), SoundsTSeries.magnetUse, SoundCategory.PLAYERS, 0.4F, 0.8F + 0.4F * getMode(stack));
//		ChatHelper.sendIndexedChatMessageToPlayer(player, new TextComponentTranslation("info.thermal.rf_magnet.b." + getMode(stack)));
//	}
//	// endregion
//
//	// region IBauble
//	@Override
//	public BaubleType getBaubleType(ItemStack stack) {
//
//		return BaubleType.TRINKET;
//	}
//
//	@Override
//	public void onWornTick(ItemStack stack, EntityLivingBase player) {
//
//		World world = player.world;
//		if (world.getTotalWorldTime() % TIME_CONSTANT != 0) {
//			return;
//		}
//		if (!(player instanceof EntityPlayer) || Utils.isFakePlayer(player) || player.isSneaking() || getMode(stack) <= 0) {
//			return;
//		}
//		EntityPlayer castPlayer = (EntityPlayer) player;
//		if (getEnergyStored(stack) < ENERGY_PER_ITEM && !castPlayer.capabilities.isCreativeMode) {
//			return;
//		}
//		int radSq = radius * radius;
//
//		AxisAlignedBB area = new AxisAlignedBB(player.getPosition().add(-radius, -radius, -radius), player.getPosition().add(1 + radius, 1 + radius, 1 + radius));
//		List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, area, EntitySelectors.IS_ALIVE);
//		ItemFilterWrapper wrapper = new ItemFilterWrapper(stack, getSizeFilter(stack));
//
//		if (Utils.isClientWorld(world)) {
//			for (EntityItem item : items) {
//				if (item.getEntityData().getBoolean(TAG_CONVEYOR_COMPAT)) {
//					continue;
//				}
//				if (item.getPositionVector().squareDistanceTo(player.getPositionVector()) <= radSq && wrapper.getFilter().matches(item.getItem())) {
//					world.spawnParticle(EnumParticleTypes.REDSTONE, item.posX, item.posY, item.posZ, 0, 0, 0, 0);
//				}
//			}
//		} else {
//			int itemCount = 0;
//			for (EntityItem item : items) {
//				if (item.getEntityData().getBoolean(TAG_CONVEYOR_COMPAT)) {
//					continue;
//				}
//				if (item.getThrower() == null || !item.getThrower().equals(player.getName()) || item.age >= 8 * TIME_CONSTANT) {
//					if (item.getPositionVector().squareDistanceTo(player.getPositionVector()) <= radSq && wrapper.getFilter().matches(item.getItem())) {
//						item.setPosition(player.posX, player.posY, player.posZ);
//						item.setPickupDelay(0);
//						itemCount++;
//					}
//				}
//			}
//			if (!castPlayer.capabilities.isCreativeMode) {
//				extractEnergy(stack, ENERGY_PER_ITEM * itemCount, false);
//			}
//		}
//	}
//
//	@Override
//	public boolean willAutoSync(ItemStack stack, EntityLivingBase player) {
//
//		return true;
//	}
//	// endregion
//
//	// region IModelRegister
//	@SideOnly (Side.CLIENT)
//	public void registerModel() {
//
//		ModelLoader.setCustomMeshDefinition(this, stack -> new ModelResourceLocation(getRegistryName(), String.format("color=%s,state=%s", ColorHelper.hasColor(stack, TINT_INDEX_2) ? 1 : 0, this.getEnergyStored(stack) > 0 ? getMode(stack) == 1 ? "active" : "charged" : "drained")));
//		String[] states = { "charged", "active", "drained" };
//
//		for (int color = 0; color < 2; color++) {
//			for (int state = 0; state < 3; state++) {
//				ModelBakery.registerItemVariants(this, new ModelResourceLocation(getRegistryName(), String.format("color=%s,state=%s", color, states[state])));
//			}
//		}
//	}
//
//	@Override
//	public void generateModelFiles() {
//
//		String domain = getRegistryName().getResourceDomain();
//		String path = getRegistryName().getResourcePath();
//		String model = "{\"forge_marker\":1,\"defaults\":{\"model\":\"builtin/generated\",\"textures\":{\"layer0\":\"" + domain + ":items/utils/rf_magnet/" + path + "\",\"layer1\":\"" + domain + ":items/utils/rf_magnet/rf_magnet_drained\",\"layer2\":\"" + domain + ":items/blank\"},\"transform\":{\"thirdperson_righthand\":{\"rotation\":[{\"y\":-90},{\"z\":-45}],\"translation\":[0,0.35,0],\"scale\":[0.85,0.85,0.85]},\"thirdperson_lefthand\":{\"rotation\":[{\"y\":90},{\"z\":45}],\"translation\":[0,0.35,0],\"scale\":[0.85,0.85,0.85]},\"firstperson_righthand\":{\"rotation\":[{\"y\":-90},{\"z\":25}],\"translation\":[0,0.15,0.12],\"scale\":[0.68,0.68,0.68]},\"firstperson_lefthand\":{\"rotation\":[{\"y\":90},{\"z\":-25}],\"translation\":[0,0.15,0.12],\"scale\":[0.68,0.68,0.68]},\"ground\":{\"translation\":[0,0.15,0],\"scale\":[0.5,0.5,0.5]}}},\"variants\":{\"color\":{\"0\":{},\"1\":{\"textures\":{\"layer2\":\"" + domain + ":items/utils/rf_magnet/rf_magnet_color\"}}},\"state\":{\"charged\":{\"textures\":{\"layer1\":\"" + domain + ":items/utils/rf_magnet/rf_magnet_charged\"}},\"active\":{\"textures\":{\"layer1\":\"" + domain + ":items/utils/rf_magnet/rf_magnet_active\"}},\"drained\":{}}}}";
//
//		try {
//			File blockState = new File(configDir, "/dev/" + domain + "/blockstates/" + path + ".json");
//			FileUtils.writeStringToFile(blockState, Utils.createPrettyJSON(model), Charset.forName("UTF-8"));
//		} catch (Throwable t) {
//			// pokemon!
//		}
//	}
//	// endregion
//}
