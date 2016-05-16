package main.java.commands;

import main.java.CommandExecutionEvent;



/**
 * Created by Allin on 5/12/2016.
 */
public enum Commands {

    Delete() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event){
            try {
                new Delete(Integer.parseInt(args[0]));
            } catch(NumberFormatException ex) {
                new Delete(event.getBy());
            } catch(NullPointerException ignore) {}
        }
    },
    Filter() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    },
    Google() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    },
    Horn() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    },
    Lookup() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    },
    Meme() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    },
    Message() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    },
    Music() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    },
    WolframAlpha() {
        @Override public void runCommand(String[] args, CommandExecutionEvent event) {
            System.out.println("User selected command #1");
        }
    };

    public abstract void runCommand(String[] args, CommandExecutionEvent event);
}