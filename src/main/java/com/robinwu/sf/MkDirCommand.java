package com.robinwu.sf;

/**
 * @author Robin Wu (rwu@groupon.com)
 * @since 5/1/16.
 */
public class MkDirCommand extends AbstractCommand {
    private String parameter;
    private final static String NAME = "mkdir";

    public MkDirCommand(String parameter, Directory current) {
        this.parameter = parameter;
        this.current = current;
    }

    public String output() {
        if (current.getSubDirNames().size() >= 5000) {
            return "Subdirectory reaches to the limit of 5000\n";
        }
        if (current.getSubDirNames().contains(parameter)) {
            return "Subdirectory already exists\n";
        }
        if (!isValid(parameter)) {
            return "Subdirectory is invalid\n";
        }

        Directory childDir = new Directory(parameter, current.getFullPath());
        current.addChild(childDir);
        return "";
    }

    private boolean isValid(String parameter) {
        if (parameter == null || parameter.isEmpty() || parameter.length() > 6) {
            return false;
        }
        char[] chars = parameter.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!(Character.isLetterOrDigit(chars[i]) || '_' == chars[i])) {
                return false;
            }
        }
        return true;
    }

}
