package cofh.lib.event;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.util.List;

import static cofh.lib.capability.CapabilityAOE.AOE_ITEM_CAPABILITY;
import static cofh.lib.capability.CapabilityAOE.DEFAULT_AOE_CAPABILITY;
import static cofh.lib.util.helpers.AOEHelper.validAOEBreakItem;
import static cofh.lib.util.helpers.AOEHelper.validAOEItem;

public class AOEClientEvents {

    private static boolean registered = false;

    public static void register() {

        if (registered) {
            return;
        }
        MinecraftForge.EVENT_BUS.register(AOEClientEvents.class);
        registered = true;
    }

    private AOEClientEvents() {

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void renderExtraBlockBreak(RenderWorldLastEvent event) {

        PlayerController controller = Minecraft.getInstance().playerController;

        if (controller == null) {
            return;
        }
        PlayerEntity player = Minecraft.getInstance().player;
        ItemStack stack = player.getHeldItemMainhand();

        if (!validAOEItem(stack)) {
            return;
        }
        Entity renderEntity = Minecraft.getInstance().getRenderViewEntity();
        if (renderEntity == null) {
            return;
        }
        RayTraceResult traceResult = renderEntity.func_213324_a(controller.getBlockReachDistance(), event.getPartialTicks(), false);
        if (traceResult.getType() != RayTraceResult.Type.BLOCK) {
            return;
        }
        BlockRayTraceResult blockResult = (BlockRayTraceResult) traceResult;
        ImmutableList<BlockPos> areaBlocks = stack.getCapability(AOE_ITEM_CAPABILITY).orElse(DEFAULT_AOE_CAPABILITY).getAOEBlocks(stack, blockResult.getPos(), player);
        for (BlockPos pos : areaBlocks) {
            event.getContext().drawSelectionBox(Minecraft.getInstance().gameRenderer.getActiveRenderInfo(), new BlockRayTraceResult(DUMMY_VEC, blockResult.getFace(), pos, false), 0);
        }
        if (!validAOEBreakItem(stack)) {
            return;
        }
        if (controller.isHittingBlock) {
            drawBlockDamageTexture(Tessellator.getInstance(), Tessellator.getInstance().getBuffer(), Minecraft.getInstance().gameRenderer.getActiveRenderInfo(), player.getEntityWorld(), areaBlocks);
        }
    }

    // region HELPERS
    private static void drawBlockDamageTexture(Tessellator tessellatorIn, BufferBuilder bufferIn, ActiveRenderInfo renderInfo, World world, List<BlockPos> blocks) {

        double d0 = renderInfo.getProjectedView().x;
        double d1 = renderInfo.getProjectedView().y;
        double d2 = renderInfo.getProjectedView().z;

        TextureManager textureManager = Minecraft.getInstance().textureManager;
        int progress = (int) (Minecraft.getInstance().playerController.curBlockDamageMP * 10.0F) - 1;

        if (progress < 0) {
            return;
        }
        textureManager.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
        preRenderDamagedBlocks();

        bufferIn.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        bufferIn.setTranslation(-d0, -d1, -d2);
        bufferIn.noColor();
        for (BlockPos pos : blocks) {
            TileEntity tile = world.getTileEntity(pos);
            boolean hasBreak = tile != null && tile.canRenderBreaking();
            if (!hasBreak) {
                BlockState state = world.getBlockState(pos);
                if (state.getMaterial() != Material.AIR) {
                    Minecraft.getInstance().getBlockRendererDispatcher().renderBlockDamage(state, pos, Minecraft.getInstance().worldRenderer.destroyBlockIcons[progress], world);
                }
            }
        }
        tessellatorIn.draw();
        bufferIn.setTranslation(0.0D, 0.0D, 0.0D);
        postRenderDamagedBlocks();
    }

    private static void preRenderDamagedBlocks() {

        GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.DST_COLOR, GlStateManager.DestFactor.SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.enableBlend();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
        GlStateManager.polygonOffset(-1.0F, -10.0F);
        GlStateManager.enablePolygonOffset();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableAlphaTest();
        GlStateManager.pushMatrix();
    }

    private static void postRenderDamagedBlocks() {

        GlStateManager.disableAlphaTest();
        GlStateManager.polygonOffset(0.0F, 0.0F);
        GlStateManager.disablePolygonOffset();
        GlStateManager.enableAlphaTest();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
    }

    private static final Vec3d DUMMY_VEC = new Vec3d(0, 0, 0);
    // endregion
}
