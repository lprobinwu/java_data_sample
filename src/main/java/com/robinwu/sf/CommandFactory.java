package com.robinwu.sf;

/**
 * @author Robin Wu (rwu@groupon.com)
 * @since 5/1/16.
 */
public class CommandFactory {
    public static Command createCommand(String name, String parameter, Directory current) {
        if ("cd".equals(name)) {
            return new CdCommand(parameter, current);
        } else if ("mkdir".equals(name)) {
            return new MkDirCommand(parameter, current);
        } else if ("up".equals(name)) {
            return new UpCommand(current);
        } else if ("dir".equals(name)) {
            return new DirCommand(current);
        }

        return null;
    }
}
