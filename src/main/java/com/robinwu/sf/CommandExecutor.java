package com.robinwu.sf;

/**
 * @author Robin Wu (rwu@groupon.com)
 * @since 5/1/16.
 */
public class CommandExecutor {

    private Command command = null;

    public CommandExecutor(String name, String parameter, Directory current) {
        this.command = CommandFactory.createCommand(name, parameter, current);
    }

    /**
     * print out the output of the command result
     * @return
     */
    public String output() {
        if (this.command != null) {
            return command.output();
        }

        return "Unknown Command";
    }

    public Directory getCurrent() {
        return this.command.getCurrent();
    }
}
