package main.java;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;


public class Bot {
    public static IDiscordClient discordClient;

    public static void main(String[] args) throws Exception {
        if(args.length < 1)
            System.out.print("You need to specify a token before continuing");

        discordClient = login(args[0]);

        discordClient.getDispatcher().registerListener(new EventHandler());
        discordClient.getDispatcher().registerListener(new ReadyEventListener());
        new CommandListener(discordClient);
        discordClient.getDispatcher().registerListener(new CommandHandler());

    }

    public static IDiscordClient login(String token) throws DiscordException {
        return new ClientBuilder().withToken(token).login();
    }

    public static IDiscordClient getDiscordClient() throws DiscordException {
        return discordClient;
    }
}