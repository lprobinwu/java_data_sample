package com.robinwu.sf;

/**
 * @author Robin Wu (rwu@groupon.com)
 * @since 5/1/16.
 */
public class CdCommand extends AbstractCommand {
    private String parameter;
    private final static String NAME = "cd";

    public CdCommand(String parameter, Directory current) {
        this.parameter = parameter;
        this.current = current;
    }

    public String output() {
        if (parameter == null || parameter.isEmpty()) {
            return "not found";
        }
        for (Directory child: current.getChildren()) {
            if (parameter.equals(child.getName())) {
                current = child;
                return "";
            }
        }
        return "Subdirectory does not exist\n";
    }
}
