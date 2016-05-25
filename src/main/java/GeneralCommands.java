package main.java;

import com.github.kaioru.instructability.Instructables;
import com.github.kaioru.instructability.command.CommandRegistry;
import main.java.commands.Commands;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;

public class GeneralCommands implements IModule {

    public static IDiscordClient client;

    public void enable(IDiscordClient discordClient) {
        client = discordClient;
        Instructables.getRegistry().registerCommand(Commands.alias);
        Instructables.getRegistry().registerCommand(Commands.help);
        Instructables.getRegistry().registerCommand(Commands.shrug);
        Instructables.getRegistry().registerCommand(Commands.hype);
    }

    public void disable() {

    }

    public String getName() {
        return "GeneralCommands";public class GeneralCommands implements IModule {

            public static IDiscordClient client;

            public void enable(IDiscordClient discordClient) {
                client = discordClient;
                Instructables.getRegistry().registerCommand(Commands.alias);
                Instructables.getRegistry().registerCommand(Commands.help);
                Instructables.getRegistry().registerCommand(Commands.shrug);
                Instructables.getRegistry().registerCommand(Commands.hype);
            }

            public void disable() {

            }

            public String getName() {
                return "GeneralCommands";
            }

            public String getVersion() {
                return "1.0";
            }

            public String getAuthor() {
                return "Panda";
            }

            public String getMinimumDiscord4JVersion() {
                return "2.3.0";
            }
        }

    }

    public String getVersion() {
        return "1.0";
    }

    public String getAuthor() {
        return "Panda";
    }

    public String getMinimumDiscord4JVersion() {
        return "2.3.0";
    }
}