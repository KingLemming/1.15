package cofh.core.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import static cofh.lib.util.constants.Constants.PLAYER;

public class SubCommandEnderChest {

    public static int permissionLevel = 2;

    static final TranslationTextComponent TITLE = new TranslationTextComponent("container.enderchest");

    static ArgumentBuilder<CommandSource, ?> register() {

        return Commands.literal("enderchest")
                .requires(source -> source.hasPermissionLevel(permissionLevel))
                // Self
                .executes(context -> openContainer(context.getSource().asPlayer(), context.getSource().asPlayer()))
                // Target Specified
                .then(Commands.argument(PLAYER, EntityArgument.player())
                        .executes(context -> openContainer(context.getSource().asPlayer(), EntityArgument.getPlayer(context, PLAYER))));
    }

    private static int openContainer(PlayerEntity user, PlayerEntity target) {

        ITextComponent title = TITLE.deepCopy();

        if (user != target) {
            title.appendText(" - ").appendSibling(target.getDisplayName());
        }
        user.openContainer(new SimpleNamedContainerProvider((id, player, inv) -> ChestContainer.createGeneric9X3(id, player, target.getInventoryEnderChest()), title));
        user.addStat(Stats.OPEN_ENDERCHEST);
        return 1;
    }

}
