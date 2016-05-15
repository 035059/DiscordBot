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

    public static void send(IDiscordClient client, IChannel channel, String message){
        try {
            client.getChannelByID(channel.getID()).sendMessage(message);
        } catch (MissingPermissionsException e) {
            e.printStackTrace();
        } catch (HTTP429Exception e) {
            e.printStackTrace();
        } catch (DiscordException e) {
            e.printStackTrace();
        }
    }
}
