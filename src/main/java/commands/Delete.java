package main.java.commands;

import main.java.ICommand;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MessageList;
import sx.blah.discord.util.MissingPermissionsException;

/**
 * Created by Allin on 5/11/2016.
 */
public class Delete implements ICommand {

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public void handle(IMessage message, String[] args) {
        MessageList messages = message.getChannel().getMessages();
        try {
            for (int i = 0; i < Integer.valueOf(args[0]) + 1; i++) {
                try {
                    messages.get(i).delete();
                } catch (MissingPermissionsException | HTTP429Exception | DiscordException e) {
                    e.printStackTrace();
                }
            }
        } catch (NumberFormatException e) {
            int numSelected = 0;
            int counter = 0;
            while(numSelected < Integer.valueOf(args[1])){
                if (messages.get(counter).getAuthor() == message.getAuthor()){
                    try {
                        messages.get(counter).delete();
                    } catch (MissingPermissionsException | HTTP429Exception | DiscordException e1) {
                        e1.printStackTrace();
                    }
                    numSelected++;
                }
                counter ++;
            }
        }
    }

    @Override
    public boolean deletesMessage() {
        return true;
    }
}
