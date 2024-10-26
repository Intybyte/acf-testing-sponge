package me.vaan.acfTestingSponge;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.SpongeCommandSource;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import net.kyori.adventure.identity.Identified;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;

import static net.kyori.adventure.text.Component.text;

@CommandAlias("test")
public class ExampleCommand extends BaseCommand {

    @Default
    public static void def(SpongeCommandSource cause) {
        if (cause.subject() instanceof Player player) {
            player.sendMessage(text("A player ran the command"));
        }

        if (cause.subject() instanceof Identified identity) {
            cause.sendMessage(identity, text("Identified source"));
        }

        Sponge.systemSubject().sendMessage(text("No hope in this case"));
    }

    @Subcommand("normal")
    public static void normal(SpongeCommandSource cause) {
        if (cause.subject() instanceof Player player) {
            player.sendMessage(text("Simple sub command test"));
        }
    }

    @Subcommand("string")
    public static void string(SpongeCommandSource cause, String string) {
        if (cause.subject() instanceof Player player) {
            player.sendMessage(text("Read String: " + string));
        }
    }

    @Subcommand("player")
    public static void player(SpongeCommandSource cause, User user) {
        if (cause.subject() instanceof Player player) {
            var receiver = user.player();
            receiver.ifPresent(serverPlayer ->
                    serverPlayer.sendMessage(text("Hello from: " + player.name()))
            );
        }
    }

    @Subcommand("number")
    public static void number(SpongeCommandSource ignored, Integer i) {
        Sponge.systemSubject().sendMessage(text("Number read: " + i));
    }
}
