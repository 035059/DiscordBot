package main.java.commands;

import main.java.GeneralCommands;
import main.java.ICommand;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.audio.AudioPlayer;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Allin on 5/11/2016.
 */
public final class Horn implements ICommand {


    @Override
    public String getName() {
        return "horn";
    }

    @Override
    public String getRole() {
        return "plays a quick audio file";
    }

    @Override
    public void handle(IMessage message, String[] args) {
        File[] files = new File("C:\\Users\\Allin\\IdeaProjects\\DiscordBotGit\\src\\main\\java\\commands\\horns").listFiles();
        Random rd = new Random();
        IVoiceChannel channel = message.getAuthor().getConnectedVoiceChannels().get(0);
        assert files != null;
        File file = files[rd.nextInt(files.length)];
        File temp = file;

        if (args.length > 0) {
            if (args[0].equals("horns")){
                StringBuilder sb = new StringBuilder();
                sb.append("```");
                for (File file1: files){
                    sb.append(file1.getName().substring(0, file1.getName().length()-4));
                    sb.append("\n");
                }
                sb.append("```");
                try {
                    new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent(sb.toString()).build();
                } catch (HTTP429Exception | DiscordException | MissingPermissionsException e) {
                    e.printStackTrace();
                }
                file = null;
            } else {
                System.out.println(Arrays.toString(args).replaceAll("\\]", "").replaceAll("\\[", "").replaceAll(",", ""));
                for (File file1 : files) {
                    if (file1.getName().toLowerCase().contains(Arrays.toString(args).replaceAll("\\]", "").replaceAll("\\[", "").replaceAll(",", "")))
                        file = file1;
                }
            }
        }

        if (!channel.isConnected())
            channel.join();
        try {
            if (file != null) {
                System.out.println(file.getName());
            AudioPlayer.getAudioPlayerForGuild(message.getGuild()).queue(file);
            }
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.getName() + ": " + this.getRole();
    }
}
