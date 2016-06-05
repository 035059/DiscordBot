package main.java;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.Status;
import sx.blah.discord.util.DiscordException;


public class Bot {
    public static IDiscordClient discordClient;

    /**
     * Main function
     * @param args the token
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 1) // If no args are passed
            System.out.print("You need to specify a token before continuing"); // Tell the user

        discordClient = new ClientBuilder() // Make a new client instance
                .withToken(args[0]) // with the token passed in the build arguments
                .login(); // and login

        discordClient.getModuleLoader().loadModule(new GeneralCommands()); // Add the GeneralCommands Module
        discordClient.getModuleLoader().loadModule(new CommandModule()); // Add the CommandModule Module

        // Wait until the bit is fully connected
        while (!discordClient.isReady()) {
            Thread.sleep(100);
        }

        discordClient.changeStatus(Status.stream("Discord4J", "https://austinv11.github.io/Discord4J/")); // Change the bot's presence
    }

    /**
     * @param token the unique ID of a bot
     * @return a new client instance
     * @throws DiscordException
     */
    public static IDiscordClient login(String token) throws DiscordException {
        return new ClientBuilder().withToken(token).login();
    }

    /**
     * @return The connected Discord client
     */
    public static IDiscordClient getDiscordClient() {
        return discordClient;
    }
}