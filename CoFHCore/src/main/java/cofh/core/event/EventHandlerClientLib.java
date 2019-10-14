//package cofh.core.event;
//
//import cofh.lib.item.IAreaEffectItem;
//import cofh.lib.item.IFOVChangeItem;
//import com.google.common.collect.ImmutableList;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.multiplayer.PlayerController;
//import net.minecraft.client.multiplayer.PlayerControllerMP;
//import net.minecraft.client.renderer.BufferBuilder;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.renderer.Tessellator;
//import net.minecraft.client.renderer.texture.AtlasTexture;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.client.renderer.texture.TextureManager;
//import net.minecraft.client.renderer.texture.TextureMap;
//import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
//import net.minecraft.client.resources.IReloadableResourceManager;
//import net.minecraft.client.resources.IResourceManager;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.RayTraceResult;
//import net.minecraft.util.math.Vec3d;
//import net.minecraft.world.World;
//import net.minecraftforge.client.event.FOVUpdateEvent;
//import net.minecraftforge.client.event.RenderWorldLastEvent;
//import net.minecraftforge.client.resource.IResourceType;
//import net.minecraftforge.client.resource.ISelectiveResourceReloadListener;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.fml.common.eventhandler.EventPriority;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.resource.ISelectiveResourceReloadListener;
//import org.lwjgl.opengl.GL11;
//
//import java.util.List;
//import java.util.function.Predicate;
//
//public class EventHandlerClientLib implements ISelectiveResourceReloadListener {
//
//	private static final EventHandlerClientLib INSTANCE = new EventHandlerClientLib();
//	private static boolean registered = false;
//
//	public static void register() {
//
//		if (registered) {
//			return;
//		}
//		MinecraftForge.EVENT_BUS.register(INSTANCE);
//
//		registered = true;
//	}
//
//	public static void registerReload() {
//
//		if (!registered) { // Yes, logic inversion here.
//			return;
//		}
//		((IReloadableResourceManager) Minecraft.getInstance().getResourceManager()).registerReloadListener(INSTANCE);
//	}
//
//	private EventHandlerClientLib() {
//
//	}
//
//	@SubscribeEvent (priority = EventPriority.LOW)
//	public void renderExtraBlockBreak(RenderWorldLastEvent event) {
//
//		PlayerController controller = Minecraft.getInstance().playerController;
//
//		if (controller == null) {
//			return;
//		}
//		PlayerEntity player = Minecraft.getInstance().player;
//		ItemStack stack = player.getHeldItemMainhand();
//
//		if (!stack.isEmpty() && stack.getItem() instanceof IAreaEffectItem) {
//			Entity renderEntity = Minecraft.getInstance().getRenderViewEntity();
//			if (renderEntity == null) {
//				return;
//			}
//			IAreaEffectItem aoeTool = (IAreaEffectItem) stack.getItem();
//
//			double distance = Math.max(controller.getBlockReachDistance(), aoeTool.getReachDistance(stack));
//			RayTraceResult traceResult = renderEntity.rayTrace(distance, event.getPartialTicks());
//			if (traceResult != null) {
//				ImmutableList<BlockPos> extraBlocks = aoeTool.getAreaEffectBlocks(stack, traceResult.getBlockPos(), player);
//				for (BlockPos pos : extraBlocks) {
//					event.getContext().drawSelectionBox(player, new RayTraceResult(new Vec3d(0, 0, 0), traceResult.sideHit, pos), 0, event.getPartialTicks());
//				}
//			}
//		}
//		if (controller.isHittingBlock) {
//			if (!stack.isEmpty() && stack.getItem() instanceof IAreaEffectItem) {
//				BlockPos pos = controller.currentBlock;
//				IAreaEffectItem aoeTool = (IAreaEffectItem) stack.getItem();
//				drawBlockDamageTexture(Tessellator.getInstance(), Tessellator.getInstance().getBuffer(), player, event.getPartialTicks(), player.getEntityWorld(), aoeTool.getAreaEffectBlocks(stack, pos, player));
//			}
//		}
//	}
//
//	@SubscribeEvent
//	public void handleFOVUpdateEvent(FOVUpdateEvent event) {
//
//		ItemStack stack = event.getEntity().getActiveItemStack();
//
//		if (stack.getItem() instanceof IFOVChangeItem) {
//			event.setNewfov(event.getFov() - ((IFOVChangeItem) stack.getItem()).getFOVMod(stack, event.getEntity()));
//		}
//	}
//
//	// region HELPERS
//	// Copy of RenderGlobal.drawBlockDamageTexture
//	public void drawBlockDamageTexture(Tessellator tessellatorIn, BufferBuilder bufferIn, Entity entityIn, float partialTicks, World world, List<BlockPos> blocks) {
//
//		double d0 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double) partialTicks;
//		double d1 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double) partialTicks;
//		double d2 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double) partialTicks;
//
//		TextureManager textureManager = Minecraft.getInstance().textureManager;
//		int progress = (int) (Minecraft.getInstance().playerController.curBlockDamageMP * 10.0F) - 1;
//
//		if (progress < 0) {
//			return;
//		}
//		textureManager.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
//		preRenderDamagedBlocks();
//
//		bufferIn.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
//		bufferIn.setTranslation(-d0, -d1, -d2);
//		bufferIn.noColor();
//
//		for (BlockPos pos : blocks) {
//			TileEntity tile = world.getTileEntity(pos);
//			boolean hasBreak = tile != null && tile.canRenderBreaking();
//
//			if (!hasBreak) {
//				BlockState state = world.getBlockState(pos);
//				if (state.getMaterial() != Material.AIR) {
//					Minecraft.getInstance().getBlockRendererDispatcher().renderBlockDamage(state, pos, blockDamageIcons[progress], world);
//				}
//			}
//		}
//		tessellatorIn.draw();
//		bufferIn.setTranslation(0.0D, 0.0D, 0.0D);
//		postRenderDamagedBlocks();
//	}
//
//	// Copy of RenderGlobal.preRenderDamagedBlocks
//	private void preRenderDamagedBlocks() {
//
//		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.DST_COLOR, GlStateManager.DestFactor.SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
//		GlStateManager.enableBlend();
//		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
//		GlStateManager.doPolygonOffset(-3.0F, -3.0F);
//		GlStateManager.enablePolygonOffset();
//		GlStateManager.alphaFunc(516, 0.1F);
//		GlStateManager.enableAlpha();
//		GlStateManager.pushMatrix();
//	}
//
//	// Copy of RenderGlobal.postRenderDamagedBlocks
//	private void postRenderDamagedBlocks() {
//
//		GlStateManager.disableAlpha();
//		GlStateManager.doPolygonOffset(0.0F, 0.0F);
//		GlStateManager.disablePolygonOffset();
//		GlStateManager.enableAlpha();
//		GlStateManager.depthMask(true);
//		GlStateManager.popMatrix();
//	}
//	// endregion
//
//	// region ISelectiveResourceReloadListener
//	@Override
//	public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
//
//		AtlasTexture texturemap = Minecraft.getInstance().getTextureMap();
//
//		for (int i = 0; i < this.blockDamageIcons.length; ++i) {
//			this.blockDamageIcons[i] = texturemap.getAtlasSprite("minecraft:blocks/destroy_stage_" + i);
//		}
//	}
//
//	private final TextureAtlasSprite[] blockDamageIcons = new TextureAtlasSprite[10];
//	// endregion
//}
