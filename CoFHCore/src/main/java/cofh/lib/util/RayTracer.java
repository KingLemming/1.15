package cofh.lib.util;

public class RayTracer {

    // TODO: Fix
    //	public static RayTraceResult retrace(PlayerEntity player) {
    //
    //		return retrace(player, getBlockReachDistance(player), true);
    //	}
    //
    //	public static RayTraceResult retrace(PlayerEntity player, double reach) {
    //
    //		return retrace(player, reach, true);
    //	}
    //
    //	public static RayTraceResult retrace(PlayerEntity player, boolean stopOnFluid) {
    //
    //		return retrace(player, stopOnFluid, false, true);
    //	}
    //
    //	public static RayTraceResult retrace(PlayerEntity player, double reach, boolean stopOnFluid) {
    //
    //		return retrace(player, reach, stopOnFluid, false, true);
    //	}
    //
    //	public static RayTraceResult retrace(PlayerEntity player, boolean stopOnFluid, boolean ignoreNoBoundingBox, boolean returnUncollidable) {
    //
    //		Vec3d startVec = getStartVec(player);
    //		Vec3d endVec = getEndVec(player);
    //		return player.world.rayTraceBlocks(startVec, endVec, stopOnFluid, ignoreNoBoundingBox, returnUncollidable);
    //	}
    //
    //	public static RayTraceResult retrace(PlayerEntity player, double reach, boolean stopOnFluid, boolean ignoreNoBoundingBox, boolean returnUncollidable) {
    //
    //		Vec3d startVec = getStartVec(player);
    //		Vec3d endVec = getEndVec(player, reach);
    //		return player.world.rayTraceBlocks(startVec, endVec, stopOnFluid, ignoreNoBoundingBox, returnUncollidable);
    //	}
    //
    //	public static RayTraceResult retraceBlock(World world, PlayerEntity player, BlockPos pos) {
    //
    //		Vec3d startVec = getStartVec(player);
    //		Vec3d endVec = getEndVec(player);
    //		return world.getBlockState(pos).collisionRayTrace(world, pos, startVec, endVec);
    //	}
    //
    //	public static Vec3d getStartVec(PlayerEntity player) {
    //
    //		return getCorrectedHeadVec(player);
    //	}
    //
    //	public static Vec3d getEndVec(PlayerEntity player) {
    //
    //		Vec3d headVec = getCorrectedHeadVec(player);
    //		Vec3d lookVec = player.getLook(1.0F);
    //		double reach = getBlockReachDistance(player);
    //		return headVec.add(lookVec.x * reach, lookVec.y * reach, lookVec.z * reach);
    //	}
    //
    //	public static Vec3d getEndVec(PlayerEntity player, double reach) {
    //
    //		Vec3d headVec = getCorrectedHeadVec(player);
    //		Vec3d lookVec = player.getLook(1.0F);
    //		return headVec.add(lookVec.x * reach, lookVec.y * reach, lookVec.z * reach);
    //	}
    //
    //	public static Vec3d getCorrectedHeadVec(PlayerEntity player) {
    //
    //		return new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
    //	}
    //
    //	public static double getBlockReachDistance(PlayerEntity player) {
    //
    //		return player.world.isRemote ? getBlockReachDistanceClient() : player instanceof ServerPlayerEntity ? getBlockReachDistanceServer((ServerPlayerEntity) player) : 5D;
    //	}
    //
    //	private static double getBlockReachDistanceServer(ServerPlayerEntity player) {
    //
    //		return player.interactionManager.getBlockReachDistance();
    //	}
    //
    //	@SideOnly (Side.CLIENT)
    //	private static double getBlockReachDistanceClient() {
    //
    //		return Minecraft.getMinecraft().playerController.getBlockReachDistance();
    //	}

}
