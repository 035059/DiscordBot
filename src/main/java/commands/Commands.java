package main.java.commands;

import main.java.Bot;
import main.java.CommandExecutionEvent;
import sx.blah.discord.util.DiscordException;


/**
 * Created by Allin on 5/12/2016.
 */
public enum Commands {

    delete() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            if (args.length == 1) {
                try {
                    Delete.run(Integer.parseInt(args[0]));
                } catch (NumberFormatException ex) {
                    try {
                        Delete.run(Bot.getDiscordClient().getUserByID(args[0]));
                    } catch (DiscordException ignore){};
                }
            } else if (args.length == 0){
                Delete.run();
            } else {
                throw new IllegalArgumentException();
            }
        }
    },
    filter() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    },
    google() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    },
    horn() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    },
    lookup() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    },
    meme() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    },
    message() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    },
    music() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    },
    wolframalpha() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    };

    public abstract void runCommand(String[] args, CommandExecutionEvent event);
}