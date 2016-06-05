package main.java.commands;

import main.java.Bot;
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

    /**
     * Gets the name of the command
     * @return the name of the command
     */
    @Override
    public String getName() {
        return "delete";
    }

    /**
     * Gets the role of the command
     * @return the role of the command
     */
    @Override
    public String getRole() {
        return "Deletes a number of messages, or a number of messages specific to a user";
    }

    /**
     * Gets whether or not the triggering message is deleted
     * @return whether or not the triggering message is deleted
     */
    @Override
    public boolean deletesMessage() {
        return true;
    }

    /**
     * Deletes n messages from a user, or channel
     * @param message the message from the event that triggered this command
     * @param args the space separated values that followed the command
     */
    @Override
    public void handle(IMessage message, String[] args) {
        MessageList messages = message.getChannel().getMessages(); // Get a list of messages in the channel
        if (args.length < 1) { // If there are no args
            try {
                messages.get(1).delete(); // Delete the last message in the channel
            } catch (MissingPermissionsException | HTTP429Exception | DiscordException e) {
                e.printStackTrace();
            }
        } else { // If there are args
            try {
                for (int i = 0; i < Integer.valueOf(args[0]) + 1; i++) { // Try to get args[0] as an integer, and if it works, iterate for that number
                    try {
                        messages.get(i).delete(); // Delete the message at the i index
                    } catch (MissingPermissionsException | HTTP429Exception | DiscordException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NumberFormatException e) { // If args[0] is not a number
                if (args.length == 1) { // If there is one argument
                    for (IMessage iMessage : messages) { // For each message in the channel
                        try {
                            if (iMessage.getAuthor() == Bot.getDiscordClient().getUserByID(args[0])) { // If the message is by the same author as the trigger
                                iMessage.delete(); // Delete it
                                break; //  Break out of the loop
                            }
                        } catch (DiscordException | MissingPermissionsException | HTTP429Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                } else { // If there is more than one argument
                    int numDeleted = 0;
                    int counter = 0;

                    while (numDeleted < Integer.valueOf(args[1])) { // While the number of deleted messages is less than args[1]
                        if (messages.get(counter).getAuthor() == Bot.getDiscordClient().getUserByID(args[0])) { // If the message at index counter has the same author as the trigger
                            try {
                                messages.get(counter).delete(); // Delete the message
                            } catch (MissingPermissionsException | HTTP429Exception | DiscordException e1) {
                                e1.printStackTrace();
                            }
                            numDeleted++; // Increment the counter
                        }
                        counter++; // Increment the counter
                    }
                }
            }
        }
    }

    /**
     * Gets the name and role of the command
     * @return the name and role of the command
     */
    @Override
    public String toString() {
        return this.getName() + ": " + this.getRole();
    }
}
