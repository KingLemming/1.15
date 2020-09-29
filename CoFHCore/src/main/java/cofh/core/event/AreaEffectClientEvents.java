package cofh.core.event;

import cofh.core.capability.templates.AreaEffectItemWrapper;
import cofh.core.util.RayTracer;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.DrawHighlightEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static cofh.core.capability.CapabilityAreaEffect.AREA_EFFECT_ITEM_CAPABILITY;
import static cofh.core.init.CoreConfig.enableAreaEffectBlockBreaking;
import static cofh.core.util.constants.Constants.ID_COFH_CORE;
import static cofh.core.util.helpers.AreaEffectHelper.validAreaEffectItem;
import static cofh.core.util.helpers.AreaEffectHelper.validAreaEffectMiningItem;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ID_COFH_CORE)
public class AreaEffectClientEvents {

    private AreaEffectClientEvents() {

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
        if (!validAreaEffectItem(stack)) {
            return;
        }
        ActiveRenderInfo renderInfo = Minecraft.getInstance().gameRenderer.getActiveRenderInfo();
        ImmutableList<BlockPos> areaBlocks = stack.getCapability(AREA_EFFECT_ITEM_CAPABILITY).orElse(new AreaEffectItemWrapper(stack)).getAreaEffectBlocks(event.getTarget().getPos(), player);

        WorldRenderer worldRender = event.getContext();
        MatrixStack matrix = event.getMatrix();
        IVertexBuilder vertexBuilder = worldRender.renderTypeTextures.getBufferSource().getBuffer(RenderType.getLines());
        Entity viewEntity = renderInfo.getRenderViewEntity();
        World world = player.world;

        Vec3d vec3d = renderInfo.getProjectedView();
        double d0 = vec3d.getX();
        double d1 = vec3d.getY();
        double d2 = vec3d.getZ();

        matrix.push();
        for (BlockPos pos : areaBlocks) {
            if (world.getWorldBorder().contains(pos)) {
                worldRender.drawSelectionBox(matrix, vertexBuilder, viewEntity, d0, d1, d2, pos, world.getBlockState(pos));
            }
        }
        matrix.pop();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void renderBlockDamageProgress(RenderWorldLastEvent event) {

        if (!enableAreaEffectBlockBreaking) {
            return;
        }
        PlayerController controller = Minecraft.getInstance().playerController;
        if (controller == null) {
            return;
        }
        PlayerEntity player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        ItemStack stack = player.getHeldItemMainhand();

        if (!validAreaEffectMiningItem(stack)) {
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
        ImmutableList<BlockPos> areaBlocks = stack.getCapability(AREA_EFFECT_ITEM_CAPABILITY).orElse(new AreaEffectItemWrapper(stack)).getAreaEffectBlocks(traceResult.getPos(), player);
        if (controller.isHittingBlock) {
            drawBlockDamageTexture(event.getContext(), event.getMatrixStack(), Minecraft.getInstance().gameRenderer.getActiveRenderInfo(), player.getEntityWorld(), areaBlocks);
        }
    }

    // region HELPERS
    private static void drawBlockDamageTexture(WorldRenderer worldRender, MatrixStack matrixStackIn, ActiveRenderInfo renderInfo, World world, List<BlockPos> areaBlocks) {

        double d0 = renderInfo.getProjectedView().x;
        double d1 = renderInfo.getProjectedView().y;
        double d2 = renderInfo.getProjectedView().z;

        if (Minecraft.getInstance().playerController == null) {
            return;
        }
        int progress = (int) (Minecraft.getInstance().playerController.curBlockDamageMP * 10.0F) - 1;
        if (progress < 0) {
            return;
        }
        progress = Math.min(progress, 9); // Ensure that for whatever reason the progress level doesn't go OOB.

        BlockRendererDispatcher dispatcher = Minecraft.getInstance().getBlockRendererDispatcher();
        IVertexBuilder vertexBuilder = worldRender.renderTypeTextures.getCrumblingBufferSource().getBuffer(ModelBakery.DESTROY_RENDER_TYPES.get(progress + 1));

        double scale = 0.95D;

        for (BlockPos pos : areaBlocks) {
            matrixStackIn.push();
            matrixStackIn.translate(scale * (pos.getX() - d0), scale * (pos.getY() - d1), scale * (pos.getZ() - d2));
            IVertexBuilder matrixBuilder = new MatrixApplyingVertexBuilder(vertexBuilder, matrixStackIn.getLast());
            dispatcher.renderModel(world.getBlockState(pos), pos, world, matrixStackIn, matrixBuilder, EmptyModelData.INSTANCE);
            matrixStackIn.pop();
        }
    }
    // endregion

    // TODO: Make an item that uses this.
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
