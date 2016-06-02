package main.java.commands;

import main.java.GeneralCommands;
import main.java.ICommand;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IVoiceChannel;

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
                List<IVoiceChannel> channelsByName = GeneralCommands.client.getVoiceChannels().stream().filter(VoiceChannel -> VoiceChannel.getName().equals(args[1])).collect(Collectors.toList());
                for (IVoiceChannel channel: channelsByName){
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
                List<IVoiceChannel> channelsByName = GeneralCommands.client.getVoiceChannels().stream().filter(iChannel -> iChannel.getName().equals(args[1])).collect(Collectors.toList());
                for (IVoiceChannel channel : channelsByName) {
                    channel.join();
                    Thread.sleep(10000);
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
                    List<IVoiceChannel> channelsByName = GeneralCommands.client.getVoiceChannels().stream().filter(iChannel -> iChannel.getName().equals(args[1])).collect(Collectors.toList());
                    for (IVoiceChannel channel : channelsByName) {
                        channel.join();
                        Thread.sleep(10000);
                        channel.getAudioChannel().clearQueue();
                        channel.leave();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    for (IVoiceChannel channel : GeneralCommands.client.getConnectedVoiceChannels()) {
                        channel.join();
                        Thread.sleep(10000);
                        channel.getAudioChannel().clearQueue();
                        channel.leave();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String toString(){
        return this.getName() + ": " + this.getRole();
    }
}
