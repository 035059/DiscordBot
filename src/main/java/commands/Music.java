package main.java.commands;

import main.java.GeneralCommands;
import main.java.ICommand;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.audio.AudioPlayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


/**
 * Created by Allin on 5/11/2016.
 */
public final class Music implements ICommand {

    /**
     * Gets the name of the command
     * @return the name of the command
     */
    @Override
    public String getName() {
        return "music";
    }

    /**
     * Gets the role of the command
     * @return the role of the command
     */
    @Override
    public String getRole() {
        return "plays a youtube or media audio file";
    }

    /**
     * Plays music to a voice channel
     * @param message
     * @param args
     */
    @Override
    public void handle(IMessage message, String[] args) {
        if (args[0].toLowerCase().equals("play") || args[0].equals("queue")) { // If the command is to play a new song
            System.out.println("play/queue"); // Print that to the console
            try {
                // Get a channel object from args[1]
                IVoiceChannel channel = GeneralCommands.client.getVoiceChannels().stream().filter(voiceChannel -> voiceChannel.getName().equalsIgnoreCase(args[1])).findFirst().orElse(null);
                if (channel != null && !channel.isConnected()) // If the channel exists and we are not connected
                    channel.join(); // connect

                URL url;

                if (args[2].toLowerCase().contains("youtube")) { // If the link is to a youtube video
                    // Create a new process to run the youtube-dl executable, with flags to not download the file, and return an accessible url
                    ProcessBuilder processBuilder = new ProcessBuilder("C:\\Users\\Allin\\IdeaProjects\\DiscordBotGit\\src\\main\\resources\\youtube-dl.exe", args[2], "--skip-download", "-g");
                    processBuilder.redirectOutput(); // Catch the output
                    Process process = processBuilder.start(); // Start the process

                    // Create a BufferedReader to catch the output, and a StringBuilder to organise it
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    StringBuilder builder = new StringBuilder();
                    String line = null;
                    while ( (line = reader.readLine()) != null) { // While the next line from the buffered reader is not null
                        builder.append(line); // Append the line
                        builder.append(System.getProperty("line.separator")); // Append a line separator
                    }
                    url = new URL(builder.toString()); // Assign the URL to be played to the accessible youtube URL
                    System.out.println(url); // Print the URL to the console

                } else { // If the URL is not from youtube
                    url = new URL(args[2]); // Set it to args[2]
                }
                // Queue the URL
                AudioPlayer.getAudioPlayerForGuild(message.getGuild()).queue(url);
            } catch (Exception e) { // If the URL is invalid
                e.printStackTrace();
                // Tell the user in the chat
                new MessageBuilder(GeneralCommands.client).withChannel(message.getChannel()).withContent("Invalid URL");
            }
        } else if (args[0].toLowerCase().equals("pause") || args[0].toLowerCase().equals("resume")) { // If the command is to pause or resume
            System.out.println("pause/resume");
            // Toggle the paused state
            AudioPlayer.getAudioPlayerForGuild(message.getGuild()).setPaused(!AudioPlayer.getAudioPlayerForGuild(message.getGuild()).isPaused());

        } else if (args[0].toLowerCase().equals("stop")) { // If the command is to stop
            System.out.println("stop"); // Print it to the console
            AudioPlayer.getAudioPlayerForGuild(message.getGuild()).clean(); // Clear the queue
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
