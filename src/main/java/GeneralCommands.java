package main.java;

import main.java.commands.Commands;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;

public class GeneralCommands implements IModule {

    public static IDiscordClient client;

    /**
     * Adds all the commands to the command registry
     * @param discordClient the currently connected client
     * @return true when all commands have been added to the registry
     */
    @Override
    public boolean enable(IDiscordClient discordClient) {
        client = discordClient;
        CommandRegistry.registerCommand(Commands.delete);
        CommandRegistry.registerCommand(Commands.filter);
        CommandRegistry.registerCommand(Commands.google);
        CommandRegistry.registerCommand(Commands.horn);
        CommandRegistry.registerCommand(Commands.lookup);
        CommandRegistry.registerCommand(Commands.meme);
        CommandRegistry.registerCommand(Commands.music);
        CommandRegistry.registerCommand(Commands.wolframalpha);
        CommandRegistry.registerCommand(Commands.hype);
        CommandRegistry.registerCommand(Commands.help);
        return true;
    }

    /**
     * Disables the bot
     */
    @Override
    public void disable() {

    }

    /**
     * Gets the name of this Module
     * @return a string of the name
     */
    public String getName() {
        return "GeneralCommands";
    }

    /**
     * Gets the version number of this Module
     * @return a string of the version
     */
    public String getVersion() {
        return "1.0";
    }

    /**
     * Gets the name of the author
     * @return a string of the author's name
     */
    public String getAuthor() {
        return "Allin Demopolis";
    }

    /**
     * Gets the name of the minimum version of the API this module supports
     * @return a string of the minimum version
     */
    public String getMinimumDiscord4JVersion() {
        return "2.3.0";
    }
}