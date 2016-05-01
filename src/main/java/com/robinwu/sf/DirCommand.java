package com.robinwu.sf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Robin Wu (rwu@groupon.com)
 * @since 5/1/16.
 */
public class DirCommand extends AbstractCommand {
    private final static String NAME = "dir";

    public DirCommand(Directory current) {
        this.current = current;
    }

    public String output() {
        StringBuilder sb = new StringBuilder();
        sb.append("Directory of ");
        sb.append(current.getFullPath());
        sb.append(":\n");

        if (current.getSubDirNames().isEmpty()) {
            sb.append("No subdirectories\n");
            return sb.toString();
        }

        // ensure the sub directories are ordered.
        List<String> directories = new ArrayList<String>();
        for (Directory dir: current.getChildren()) {
            directories.add(dir.getName());
        }
        Collections.sort(directories);

        int i = 0;
        for (String item: directories) {
            sb.append(item).append("\t");
            // control change line every 10 sub directories.
            i++;
            if (i % 10 == 0) {
                sb.append("\n");
            }
        }
        sb.append("\n");

        return sb.toString();
    }

}

