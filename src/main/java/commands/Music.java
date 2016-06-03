package main.java.commands;

import main.java.GeneralCommands;
import main.java.ICommand;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.audio.AudioPlayer;

import java.net.URL;


/**
 * Created by Allin on 5/11/2016.
 */
public final class Music implements ICommand {

    /**
     * Gets the name of the command
     * @return
     */
    @Override
    public String getName() {
        return "music";
    }

    /**
     *
     * @return
     */
    @Override
    public String getRole() {
        return "plays a youtube or media audio file";
    }

    /**
     *
     * @param message
     * @param args
     */
    @Override
    public void handle(IMessage message, String[] args) {
        if (args[0].toLowerCase().equals("play") || args[0].equals("queue")) {
            System.out.println("play/queue");
            try {
                IVoiceChannel channel = GeneralCommands.client.getVoiceChannels().stream().filter(voiceChannel -> voiceChannel.getName().equalsIgnoreCase(args[1])).findFirst().orElse(null);
                if (channel != null && !channel.isConnected())
                    channel.join();

                URL url;

                if (args[2].toLowerCase().contains("youtube")) {
                    Process process = new ProcessBuilder("C:\\Users\\Allin\\IdeaProjects\\DiscordBotGit\\src\\main\\resources\\youtube-dl.exe", args[2], "--skip-download", "-g").start();
                    url = new URL(process.getOutputStream().toString());
                } else {
                    url = new URL(args[2]);
                }

                AudioPlayer.getAudioPlayerForGuild(message.getGuild()).queue(url);
            } catch (Exception e) {
                e.printStackTrace();
                new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent("Invalid URL");
            }
        } else if (args[0].toLowerCase().equals("pause") || args[0].toLowerCase().equals("resume")) {
            System.out.println("resume");
            AudioPlayer.getAudioPlayerForGuild(message.getGuild()).setPaused(!AudioPlayer.getAudioPlayerForGuild(message.getGuild()).isPaused());

        } else if (args[0].toLowerCase().equals("stop")) {
            System.out.println("stop");
            AudioPlayer.getAudioPlayerForGuild(message.getGuild()).clean();
        }
    }

    @Override
    public String toString() {
        return this.getName() + ": " + this.getRole();
    }
}
