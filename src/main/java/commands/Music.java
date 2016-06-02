package main.java.commands;

import main.java.GeneralCommands;
import main.java.ICommand;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;

import java.util.Arrays;


/**
 * Created by Allin on 5/11/2016.
 */
public final class Music implements ICommand {

    @Override
    public String getName() {
        return "music";
    }

    @Override
    public String getRole() {
        return null;
    }

    @Override
    public void handle(IMessage message, String[] args) {
        if (args[0].toLowerCase().equals("play") || args[0].equals("queue")){
            System.out.println("play/queue");
            try {
                IVoiceChannel channel = GeneralCommands.client.getVoiceChannels().stream().filter(voiceChannel -> voiceChannel.getName().equalsIgnoreCase(args[1])).findFirst().orElse(null);
                if (channel != null) {
                    System.out.println(channel.getName());
                    channel.join();
                    channel.getAudioChannel().queueUrl(args[2]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].toLowerCase().equals("pause") || args[0].toLowerCase().equals("resume")) {
            System.out.println("resume");;
            try {
                IVoiceChannel channel = GeneralCommands.client.getVoiceChannels().stream().filter(voiceChannel -> voiceChannel.getName().equalsIgnoreCase(args[1]) && !voiceChannel.isConnected()).findFirst().orElse(null);
                if (channel != null) {
                    if (!channel.getAudioChannel().isPaused())
                        channel.getAudioChannel().pause();
                    else
                        channel.getAudioChannel().resume();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].toLowerCase().equals("stop")) {
            System.out.println("stop");
            if (args.length > 2) {
                try {
                    IVoiceChannel channel = GeneralCommands.client.getVoiceChannels().stream().filter(voiceChannel -> voiceChannel.getName().equalsIgnoreCase(args[1]) && !voiceChannel.isConnected()).findFirst().orElse(null);
                    if (channel != null) {
                        channel.getAudioChannel().clearQueue();
                        channel.leave();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    for (IVoiceChannel channel : GeneralCommands.client.getConnectedVoiceChannels()) {
                        channel.getAudioChannel().clearQueue();
                        channel.leave();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } else if (args[0].toLowerCase().equals("connectedto")) {
            try {
                new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent(Arrays.toString(GeneralCommands.client.getConnectedVoiceChannels().toArray())).build();
            } catch (HTTP429Exception | DiscordException | MissingPermissionsException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString(){
        return this.getName() + ": " + this.getRole();
    }
}
