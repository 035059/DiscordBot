package main.java;

import sx.blah.discord.api.EventSubscriber;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Created by Allin on 5/12/2016.
 */
public class CommandListener{

    final static String KEY = "->";

    static IDiscordClient client;

    CommandListener(IDiscordClient client)
    {
        client = client;
        client.getDispatcher().registerListener(this);
    }

    private IDiscordClient getClient() {
        return client;
    }

    @EventSubscriber
    public void watchForCommands(MessageReceivedEvent event) {
        try {
            IMessage message = event.getMessage();
            String content = message.getContent();

            if (!content.startsWith(KEY)) {
                System.out.println("no CMD");
                return;
            }

            String command = content.toLowerCase();
            String[] args = null;

            if (content.contains(" ")) {
                command = command.split(" ")[0];
                args = content.substring(content.indexOf(' ') + 1).split(" ");

            }

            CommandExecutionEvent _event = new CommandExecutionEvent(message, command, message.getAuthor(), args);
            getClient().getDispatcher().dispatch(_event);
        } catch (Exception ignore) {
        }
    }
}
