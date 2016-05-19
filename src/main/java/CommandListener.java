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
        CommandListener.client = client;
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
            System.out.println(command); //TEST
            String[] args = null;

            if (content.contains(" ")) {
                command = command.trim().split(" ")[1];
            } else {
                command = command.trim().split(" ")[0].substring(2);
            }

            try {
                args = content.substring(content.indexOf(' ') + 1).split(" ");
            } catch (NullPointerException ignore){}

            if (args != null) {
                for (String arg : args){
                    System.out.println(arg);
                }
            } else {
                System.out.println("No args");
            }

            CommandExecutionEvent _event = new CommandExecutionEvent(message, command, message.getAuthor(), args);
            getClient().getDispatcher().dispatch(_event);
            System.out.println("Command Thrown: " + _event.getCommand());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
