package main.java.commands;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.AudioChannel;

import java.io.File;


/**
 * Created by Allin on 5/11/2016.
 */
public final class Music {

    private Music(){}

    public void play(IDiscordClient client, String channelID, String path) throws Exception {

        AudioChannel channel = client.getVoiceChannelByID(channelID).getAudioChannel();

        if (channel == null)
            throw new NullPointerException("Argument: 'channel'");

        File rawFile = new java.io.File(path);

        if (rawFile == null)
            return;

        channel.queueFile(rawFile);
    }
//
//    public void pause(){
//
//        // Interupts our bot's audio stream. It will
//        // continue where it left off.
//        channelID.pause();
//        // ^ This guy's partner
//        channel.isPaused();
//
//        // Resumes the bot's audio stream. Operates
//        // well with the #pause() function
//        channel.resume();
//
//        // Updates the volume at which this bot
//        // plays audio. This can go pretty darn
//        // high, so avoid killing anyone's ears.
//        channel.setVolume(4);
//        channel.setVolume(1000); // <-- Evil
//
//        // Jumps over the current playing file.
//        // Like it was never even there!
//        channel.skip();
//    }
//
//    }
//
//    public void resume(){
//
//    }
//
//    public void stop(){
//
//    }
}
