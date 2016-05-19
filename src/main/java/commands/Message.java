package main.java.commands;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MissingPermissionsException;

/**
 * Created by Allin on 5/12/2016.
 */
public final class Message {

    public static void run(IDiscordClient client, IChannel channel, String message){
        try {
            client.getChannelByID(channel.getID()).sendMessage(message);
        } catch (MissingPermissionsException | HTTP429Exception | DiscordException e) {
            e.printStackTrace();
        }
    }
}
