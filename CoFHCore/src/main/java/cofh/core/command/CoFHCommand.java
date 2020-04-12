package cofh.core.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;

public class CoFHCommand {

    private static CoFHCommand instance;

    public static void initialize(CommandDispatcher<CommandSource> dispatcher) {

        instance = new CoFHCommand(dispatcher);
    }

    private CoFHCommand(CommandDispatcher<CommandSource> dispatcher) {

        dispatcher.register(LiteralArgumentBuilder.<CommandSource>literal("cofh")
                // TODO: Figure out appropriate dummy calls for IWorldPosCallable
                //                .then(SubCommandAnvil.register())
                //                .then(SubCommandCrafting.register())
                //                .then(SubCommandCrafting.registerAlt())
                .then(SubCommandEnderChest.register())
                .then(SubCommandHeal.register())
                .then(SubCommandIgnite.register())
                .then(SubCommandRepair.register())
                .then(SubCommandZap.register())
        );
    }

}
