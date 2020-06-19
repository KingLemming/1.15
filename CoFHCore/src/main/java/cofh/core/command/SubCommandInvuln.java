package cofh.core.command;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collection;

import static cofh.lib.util.constants.Constants.FLAG;
import static cofh.lib.util.constants.Constants.TARGETS;

public class SubCommandInvuln {

    public static int permissionLevel = 2;

    static final boolean DEFAULT_FLAG = true;

    static ArgumentBuilder<CommandSource, ?> register() {

        return Commands.literal("invuln")
                .requires(source -> source.hasPermissionLevel(permissionLevel))
                // Self
                .executes(context -> flagEntities(context.getSource(), ImmutableList.of(context.getSource().asPlayer()), DEFAULT_FLAG))
                // Flag Specified
                .then(Commands.argument(FLAG, BoolArgumentType.bool())
                        .executes(context -> flagEntities(context.getSource(), ImmutableList.of(context.getSource().asPlayer()), BoolArgumentType.getBool(context, FLAG))))
                // Targets Specified
                .then(Commands.argument(TARGETS, EntityArgument.players())
                        .executes(context -> flagEntities(context.getSource(), EntityArgument.getPlayers(context, TARGETS), DEFAULT_FLAG)))
                // Targets AND duration specified
                .then(Commands.argument(TARGETS, EntityArgument.entities())
                        .then(Commands.argument(FLAG, BoolArgumentType.bool())
                                .executes(context -> flagEntities(context.getSource(), EntityArgument.getPlayers(context, TARGETS), BoolArgumentType.getBool(context, FLAG)))));
    }

    static ArgumentBuilder<CommandSource, ?> registerAlt() {

        return Commands.literal("invulnerable")
                .requires(source -> source.hasPermissionLevel(permissionLevel))
                // Self
                .executes(context -> flagEntities(context.getSource(), ImmutableList.of(context.getSource().asPlayer()), DEFAULT_FLAG))
                // Flag Specified
                .then(Commands.argument(FLAG, BoolArgumentType.bool())
                        .executes(context -> flagEntities(context.getSource(), ImmutableList.of(context.getSource().asPlayer()), BoolArgumentType.getBool(context, FLAG))))
                // Targets Specified
                .then(Commands.argument(TARGETS, EntityArgument.players())
                        .executes(context -> flagEntities(context.getSource(), EntityArgument.getPlayers(context, TARGETS), DEFAULT_FLAG)))
                // Targets AND duration specified
                .then(Commands.argument(TARGETS, EntityArgument.entities())
                        .then(Commands.argument(FLAG, BoolArgumentType.bool())
                                .executes(context -> flagEntities(context.getSource(), EntityArgument.getPlayers(context, TARGETS), BoolArgumentType.getBool(context, FLAG)))));
    }

    private static int flagEntities(CommandSource source, Collection<? extends ServerPlayerEntity> targets, boolean flag) {

        for (ServerPlayerEntity entity : targets) {
            entity.setInvulnerable(flag);
        }
        if (flag) {
            if (targets.size() == 1) {
                source.sendFeedback(new TranslationTextComponent("commands.cofh.invuln.success.single", targets.iterator().next().getDisplayName()), true);
            } else {
                source.sendFeedback(new TranslationTextComponent("commands.cofh.invuln.success.multiple", targets.size()), true);
            }
        } else {
            if (targets.size() == 1) {
                source.sendFeedback(new TranslationTextComponent("commands.cofh.invuln.remove.single", targets.iterator().next().getDisplayName()), true);
            } else {
                source.sendFeedback(new TranslationTextComponent("commands.cofh.invuln.remove.multiple", targets.size()), true);
            }
        }
        return targets.size();
    }

}
