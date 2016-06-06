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

    /**
     * Gets the name of the command
     * @return the name of the command
     */
    @Override
    public String getName() {
        return "horn";
    }

    /**
     * Gets the role of the command
     * @return the role of the command
     */
    @Override
    public String getRole() {
        return "plays a quick audio file";
    }

    /**
     * Plays a horn audio clip to the voice chat
     * @param message the message that triggered the command
     * @param args the space separated values that followed the command
     */
    @Override
    public void handle(IMessage message, String[] args) {
        File[] files = new File("C:\\Users\\Allin\\IdeaProjects\\DiscordBotGit\\src\\main\\java\\commands\\horns").listFiles(); // Get a list of available horns
        Random rd = new Random();
        IVoiceChannel channel = message.getAuthor().getConnectedVoiceChannels().get(0);
        assert files != null;
        File file = files[rd.nextInt(files.length)]; // Set the file to be played to a random file
        File temp = file;

        if (args.length > 0) { // If there are arguments
            if (args[0].equals("horns")){ // If thr first argument is horns
                StringBuilder sb = new StringBuilder();
                sb.append("```"); // Append the opening tag for a code block
                for (File file1: files){ // For each file in the list of files
                    sb.append(file1.getName().substring(0, file1.getName().length()-4)); // Append the name of the file (strips the .mp3 at the end)
                    sb.append("\n"); // Append a line break
                }
                sb.append("```");// Append the closing tag for a code block
                try {
                    new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent(sb.toString()).build();
                } catch (HTTP429Exception | DiscordException | MissingPermissionsException e) {
                    e.printStackTrace();
                }
                file = null;
            } else { // If the first argument is something else
                for (File file1 : files) { // For each file in the list of files
                    // If the name of the file is the same as the arguments
                    if (file1.getName().toLowerCase().contains(Arrays.toString(args).replaceAll("\\]", "").replaceAll("\\[", "").replaceAll(",", "")))
                        file = file1; // Set the file to be played to this file
                }
            }
        }

        if (!channel.isConnected()) // If we are not connected to the voice channel
            channel.join(); // Connect
        try {
            if (file != null) { // If the file is not null
                System.out.println(file.getName()); // Print the name of the file
                AudioPlayer.getAudioPlayerForGuild(message.getGuild()).queue(file); // Queue the file in the audio player
            }
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
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
