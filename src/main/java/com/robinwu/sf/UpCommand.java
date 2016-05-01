package com.robinwu.sf;

/**
 * @author Robin Wu (rwu@groupon.com)
 * @since 5/1/16.
 */
public class UpCommand extends AbstractCommand {
    private final static String NAME = "up";

    public UpCommand(Directory current) {
        this.current = current;
    }

    public String output() {
        if (current.getParent() == null) {
            return "Cannot move up from root directory\n";
        }
        current = current.getParent();
        return "";
    }
}
