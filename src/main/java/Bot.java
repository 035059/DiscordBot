import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

import java.util.concurrent.TimeUnit;


public class Bot
{
    public static IDiscordClient discordClient;

    public static void main(String[] args) throws Exception
    {
        if(args.length < 1)
            System.out.print("You need to specify a token before continuing");

        discordClient = getClient(args[0]);

        discordClient.getDispatcher().registerListener(new EventHandler());
        discordClient.getDispatcher().registerListener(new ReadyEventListener());
        discordClient.getDispatcher().registerListener(new CommandListener());
        TimeUnit.SECONDS.sleep(10);

    }

    public static IDiscordClient getClient(String token) throws DiscordException
    {
        return new ClientBuilder().withToken(token).login();
    }
}