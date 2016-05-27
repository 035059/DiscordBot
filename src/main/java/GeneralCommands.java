package main.java;

import main.java.commands.Commands;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;

public class GeneralCommands implements IModule {

    public static IDiscordClient client;

    @Override
    public boolean enable(IDiscordClient discordClient) {
        client = discordClient;
        CommandRegistry.registerCommand(Commands.delete);
        CommandRegistry.registerCommand(Commands.filter);
        CommandRegistry.registerCommand(Commands.google);
        CommandRegistry.registerCommand(Commands.horn);
        CommandRegistry.registerCommand(Commands.lookup);
        CommandRegistry.registerCommand(Commands.meme);
        CommandRegistry.registerCommand(Commands.message);
        CommandRegistry.registerCommand(Commands.music);
        CommandRegistry.registerCommand(Commands.wolframalpha);
        CommandRegistry.registerCommand(Commands.hype);
        CommandRegistry.registerCommand(Commands.help);
        return true;
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
        return "Allin Demopolis";
    }

    public String getMinimumDiscord4JVersion() {
        return "2.3.0";
    }
}