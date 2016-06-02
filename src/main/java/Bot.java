package main.java;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

import java.util.Optional;


public class Bot {
    public static IDiscordClient discordClient;

    /**
     * @param args the token
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 1)
            System.out.print("You need to specify a token before continuing");

        discordClient = new ClientBuilder()
                .withToken(args[0])
                .login();

        discordClient.getModuleLoader().loadModule(new GeneralCommands());
        discordClient.getModuleLoader().loadModule(new CommandModule());

        while (!discordClient.isReady()) {
            Thread.sleep(100);
        }
        discordClient.updatePresence(true, Optional.of("Discord4j"));
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