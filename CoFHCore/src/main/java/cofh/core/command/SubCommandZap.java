package cofh.core.command;

import cofh.lib.util.Utils;
import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Collection;

import static cofh.lib.util.constants.Constants.TARGETS;

public class SubCommandZap {

    public static int permissionLevel = 2;

    static ArgumentBuilder<CommandSource, ?> register() {

        return Commands.literal("zap")
                .requires(source -> source.hasPermissionLevel(permissionLevel))
                // Self
                .executes(context -> zapEntities(context.getSource(), ImmutableList.of(context.getSource().assertIsEntity())))
                // Targets Specified
                .then(Commands.argument(TARGETS, EntityArgument.entities())
                        .executes(context -> zapEntities(context.getSource(), EntityArgument.getEntities(context, TARGETS))));
    }

    private static int zapEntities(CommandSource source, Collection<? extends Entity> targets) {

        int zappedEntities = 0;
        ServerPlayerEntity caster = source.getEntity() instanceof ServerPlayerEntity ? (ServerPlayerEntity) source.getEntity() : null;

        for (Entity entity : targets) {
            if (createLightningBolt(entity.world, entity.getPosition(), caster)) {
                ++zappedEntities;
            }
        }
        if (targets.size() == 1) {
            source.sendFeedback(new TranslationTextComponent("commands.cofh.zap.success.single", targets.iterator().next().getDisplayName()), true);
        } else {
            source.sendFeedback(new TranslationTextComponent("commands.cofh.zap.success.multiple", targets.size()), true);
        }
        return zappedEntities;
    }

    private static boolean createLightningBolt(World world, BlockPos pos, ServerPlayerEntity caster) {

        if (world.canSeeSky(pos)) {
            if (Utils.isServerWorld(world)) {
                LightningBoltEntity bolt = new LightningBoltEntity(world, (double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D, false);
                bolt.setCaster(caster);
                ((ServerWorld) world).addLightningBolt(bolt);
            }
            return true;
        }
        return false;
    }

}
