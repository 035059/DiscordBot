package commands;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.AudioChannel;

import java.io.File;


/**
 * Created by Allin on 5/11/2016.
 */
public class Music {

    public void playFile(IDiscordClient client, String channelID, String path) throws Exception {

        AudioChannel channel = client.getVoiceChannelByID(channelID).getAudioChannel();

        if (channel == null)
            throw new NullPointerException("Argument: 'channel'");

        File rawFile = new java.io.File(path);

        if (rawFile == null)
            return;

        channel.queueFile(rawFile);
    }

    public void pause(){

    }

    public void resume(){

    }

    public void stop(){

    }
}
