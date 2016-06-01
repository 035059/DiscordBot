package main.java.commands;

import main.java.GeneralCommands;
import main.java.ICommand;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IVoiceChannel;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;


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
                List<IVoiceChannel> channelsByName = GeneralCommands.client.getVoiceChannels().stream().filter(iChannel -> iChannel.getName().equals(args[1])).collect(Collectors.toList());
                for (IVoiceChannel channel: channelsByName){
                    queue(channel, new URL(args[2]));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].toLowerCase().equals("pause") || args[0].toLowerCase().equals("resume")) {
            System.out.println("resume");;
            try {
                List<IVoiceChannel> channelsByName = GeneralCommands.client.getVoiceChannels().stream().filter(iChannel -> iChannel.getName().equals(args[1])).collect(Collectors.toList());
                for (IVoiceChannel channel: channelsByName)
                    toggleState(channel);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].toLowerCase().equals("stop")) {
            System.out.println("stop");
            try {
                List<IVoiceChannel> channelsByName = GeneralCommands.client.getVoiceChannels().stream().filter(iChannel -> iChannel.getName().equals(args[1])).collect(Collectors.toList());
                for (IVoiceChannel channel: channelsByName)
                    stop(channel);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void queue (IVoiceChannel channel, URL url)throws Exception{
        channel.join();
        channel.getAudioChannel().queueUrl(url);
    }

    private void toggleState(IVoiceChannel channel) throws Exception {
        if (!channel.getAudioChannel().isPaused())
            channel.getAudioChannel().pause();
        else
            channel.getAudioChannel().resume();
    }

    private void stop(IVoiceChannel channel) throws Exception {
        channel.getAudioChannel().clearQueue();
        channel.leave();
    }

    @Override
    public String toString(){
        return this.getName() + ": " + this.getRole();
    }
}
