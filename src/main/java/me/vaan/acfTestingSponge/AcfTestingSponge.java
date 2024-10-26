package me.vaan.acfTestingSponge;

import co.aikar.commands.SpongeCommandManager;
import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.LoadedGameEvent;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

/**
 * The main class of your Sponge plugin.
 *
 * <p>All methods are optional -- some common event registrations are included as a jumping-off point.</p>
 */
@Plugin("acf-testing-sponge")
public class AcfTestingSponge {

    private final PluginContainer container;
    private final Logger logger;

    @Inject
    AcfTestingSponge(final PluginContainer container, final Logger logger) {
        this.container = container;
        this.logger = logger;
    }

    @Listener
    public void onGameLoadEvent(final LoadedGameEvent event) {
        // Perform any one-time setup
        this.logger.info("Loading acf-testing-sponge");
        var scm = new SpongeCommandManager(container);
        scm.registerCommand(new ExampleCommand());
    }

    @Listener
    public void onRegisterCommands(final RegisterCommandEvent<Command.Parameterized> event) {
        // DON'T REGISTER COMMANDS HERE!
        // Some context resolvers like world require ResourceKeys
        // and other server related data to be initialized
    }
}
