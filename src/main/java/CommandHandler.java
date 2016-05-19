package main.java;

import main.java.commands.Commands;
import main.java.commands.Message;
import sx.blah.discord.api.EventSubscriber;

/**
 * Created by Allin on 5/12/2016.
 */
public class CommandHandler {

    @EventSubscriber
    public void onCommandEvent(CommandExecutionEvent event) {
        System.out.println("Command Caught: " + event.getCommand());
        try {
            System.out.println("Command: " + event.getCommand());
            Commands.valueOf(event.getCommand()).runCommand(event.getArgs(), event);
            System.out.println("Command executed");
        } catch (IllegalArgumentException ex) {
            Message.run(event.getClient(), event.getMessage().getChannel(), "@"+event.getBy().getName() + " " + event.getMessage().getContent() + " is not a command");
        }
    }
}
