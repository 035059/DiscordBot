package main.java;

import com.github.kaioru.instructability.Instructables;
import com.github.kaioru.instructability.discord4j.InstructabilityModule;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.util.DiscordException;

import static main.java.CommandListener.client;


public class Bot {
    public static IDiscordClient discordClient;

    public static void main(String[] args) throws Exception {
        if(args.length < 1)
            System.out.print("You need to specify a token before continuing");

        discordClient = new ClientBuilder()
                .withToken(args[0])
                .login();

        discordClient.getModuleLoader().loadModule(new InstructabilityModule());

        discordClient.getDispatcher().registerListener((IListener<ReadyEvent>) event -> // Ensures 'getOurUser()' is not null
                Instructables.getRegistry().setPrefix(client.getOurUser().mention() + " ")
        );
    }

    public static IDiscordClient login(String token) throws DiscordException {
        return new ClientBuilder().withToken(token).login();
    }

    public static IDiscordClient getDiscordClient() throws DiscordException {
        return discordClient;
    }
}