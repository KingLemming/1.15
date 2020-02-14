package cofh.lib.event;

import cofh.lib.util.RayTracer;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.vertex.MatrixApplyingVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawHighlightEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static cofh.lib.capability.CapabilityAOE.AOE_ITEM_CAPABILITY;
import static cofh.lib.capability.CapabilityAOE.DEFAULT_AOE_CAPABILITY;
import static cofh.lib.util.helpers.AOEHelper.validAOEItem;
import static cofh.lib.util.helpers.AOEHelper.validAOEMiningItem;

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
    public static void renderBlockHighlights(DrawHighlightEvent.HighlightBlock event) {

        if (event.isCanceled()) {
            return;
        }
        PlayerEntity player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        ItemStack stack = player.getHeldItemMainhand();
        if (!validAOEItem(stack)) {
            return;
        }
        ActiveRenderInfo renderInfo = Minecraft.getInstance().gameRenderer.getActiveRenderInfo();
        ImmutableList<BlockPos> areaBlocks = stack.getCapability(AOE_ITEM_CAPABILITY).orElse(DEFAULT_AOE_CAPABILITY).getAOEBlocks(stack, event.getTarget().getPos(), player);

        WorldRenderer worldRender = event.getContext();
        MatrixStack matrix = event.getMatrix();
        IVertexBuilder vertexBuilder = worldRender.renderTypeTextures.getBufferSource().getBuffer(RenderType.lines());
        Entity viewEntity = renderInfo.getRenderViewEntity();
        World world = player.world;

        Vec3d vec3d = renderInfo.getProjectedView();
        double d0 = vec3d.getX();
        double d1 = vec3d.getY();
        double d2 = vec3d.getZ();

        for (BlockPos pos : areaBlocks) {
            if (world.getWorldBorder().contains(pos)) {
                worldRender.drawSelectionBox(matrix, vertexBuilder, viewEntity, d0, d1, d2, pos, world.getBlockState(pos));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void renderBlockDamageProgress(RenderWorldLastEvent event) {

        PlayerController controller = Minecraft.getInstance().playerController;
        if (controller == null) {
            return;
        }
        PlayerEntity player = Minecraft.getInstance().player;
        ItemStack stack = player.getHeldItemMainhand();

        if (!validAOEMiningItem(stack)) {
            return;
        }
        Entity renderEntity = Minecraft.getInstance().getRenderViewEntity();
        if (renderEntity == null) {
            return;
        }
        BlockRayTraceResult traceResult = RayTracer.retrace(player, RayTraceContext.FluidMode.NONE);
        if (traceResult.getType() != RayTraceResult.Type.BLOCK) {
            return;
        }
        ImmutableList<BlockPos> areaBlocks = stack.getCapability(AOE_ITEM_CAPABILITY).orElse(DEFAULT_AOE_CAPABILITY).getAOEBlocks(stack, traceResult.getPos(), player);
        if (controller.isHittingBlock) {
            System.out.println("called");
            drawBlockDamageTexture(event.getContext(), event.getMatrixStack(), Minecraft.getInstance().gameRenderer.getActiveRenderInfo(), player.getEntityWorld(), areaBlocks);
        }
    }

    // region HELPERS
    private static void drawBlockDamageTexture(WorldRenderer worldRender, MatrixStack matrixStackIn, ActiveRenderInfo renderInfo, World world, List<BlockPos> areaBlocks) {

        double d0 = renderInfo.getProjectedView().x;
        double d1 = renderInfo.getProjectedView().y;
        double d2 = renderInfo.getProjectedView().z;

        int progress = (int) (Minecraft.getInstance().playerController.curBlockDamageMP * 10.0F) - 1;
        if (progress < 0) {
            return;
        }
        BlockRendererDispatcher dispatcher = Minecraft.getInstance().getBlockRendererDispatcher();
        IVertexBuilder vertexBuilder = worldRender.renderTypeTextures.getCrumblingBufferSource().getBuffer(ModelBakery.DESTROY_RENDER_TYPES.get(progress + 1));

        double scale = 0.95D;

        for (BlockPos pos : areaBlocks) {
            matrixStackIn.push();
            matrixStackIn.translate(scale * (pos.getX() - d0), scale * (pos.getY() - d1), scale * (pos.getZ() - d2));
            IVertexBuilder matrixBuilder = new MatrixApplyingVertexBuilder(vertexBuilder, matrixStackIn.getLast());
            dispatcher.renderBlockDamage(world.getBlockState(pos), pos, world, matrixStackIn, matrixBuilder);
            matrixStackIn.pop();
        }
    }
    // endregion

    // region TUTORIAL
    //    @SubscribeEvent
    //    public static void render(RenderWorldLastEvent event) {
    //
    //        ClientPlayerEntity player = Minecraft.getInstance().player;
    //
    //        if (player.getHeldItemMainhand().getItem() == Items.NETHER_STAR) {
    //            locateTileEntities(player, event.getMatrixStack());
    //        }
    //    }
    //
    //    private static void blueLine(IVertexBuilder builder, Matrix4f positionMatrix, BlockPos pos, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2) {
    //
    //        builder.pos(positionMatrix, pos.getX() + dx1, pos.getY() + dy1, pos.getZ() + dz1).color(0.0f, 0.0f, 1.0f, 1.0f).endVertex();
    //        builder.pos(positionMatrix, pos.getX() + dx2, pos.getY() + dy2, pos.getZ() + dz2).color(0.0f, 0.0f, 1.0f, 1.0f).endVertex();
    //    }
    //
    //    private static void locateTileEntities(ClientPlayerEntity player, MatrixStack matrixStack) {
    //
    //        IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
    //        IVertexBuilder builder = buffer.getBuffer(MyRenderType.OVERLAY_LINES);
    //
    //        BlockPos playerPos = player.getPosition();
    //        int px = playerPos.getX();
    //        int py = playerPos.getY();
    //        int pz = playerPos.getZ();
    //        World world = player.getEntityWorld();
    //
    //        matrixStack.push();
    //
    //        Vec3d projectedView = Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getProjectedView();
    //        matrixStack.translate(-projectedView.x, -projectedView.y, -projectedView.z);
    //
    //        Matrix4f positionMatrix = matrixStack.getLast().getPositionMatrix();
    //
    //        BlockPos.Mutable pos = new BlockPos.Mutable();
    //        for (int dx = -10; dx <= 10; dx++) {
    //            for (int dy = -10; dy <= 10; dy++) {
    //                for (int dz = -10; dz <= 10; dz++) {
    //                    pos.setPos(px + dx, py + dy, pz + dz);
    //                    if (world.getTileEntity(pos) != null) {
    //                        blueLine(builder, positionMatrix, pos, 0, 0, 0, 1, 0, 0);
    //                        blueLine(builder, positionMatrix, pos, 0, 1, 0, 1, 1, 0);
    //                        blueLine(builder, positionMatrix, pos, 0, 0, 1, 1, 0, 1);
    //                        blueLine(builder, positionMatrix, pos, 0, 1, 1, 1, 1, 1);
    //
    //                        blueLine(builder, positionMatrix, pos, 0, 0, 0, 0, 0, 1);
    //                        blueLine(builder, positionMatrix, pos, 1, 0, 0, 1, 0, 1);
    //                        blueLine(builder, positionMatrix, pos, 0, 1, 0, 0, 1, 1);
    //                        blueLine(builder, positionMatrix, pos, 1, 1, 0, 1, 1, 1);
    //
    //                        blueLine(builder, positionMatrix, pos, 0, 0, 0, 0, 1, 0);
    //                        blueLine(builder, positionMatrix, pos, 1, 0, 0, 1, 1, 0);
    //                        blueLine(builder, positionMatrix, pos, 0, 0, 1, 0, 1, 1);
    //                        blueLine(builder, positionMatrix, pos, 1, 0, 1, 1, 1, 1);
    //                    }
    //                }
    //            }
    //        }
    //
    //        matrixStack.pop();
    //
    //        //RenderSystem.disableDepthTest();
    //        buffer.finish(MyRenderType.OVERLAY_LINES);
    //    }
    // endregion
}
