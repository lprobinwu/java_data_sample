package com.robinwu.sf;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Robin Wu (rwu@groupon.com)
 * @since 5/1/16.
 */
public class AppMain {
    public static void main( String[] args ) throws IOException {
        System.out.println( "Problem 5 by team X" );

        Directory current = new Directory("root");

        BufferedReader br = new BufferedReader(new FileReader("/Users/rwu/Downloads/prog5.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                // Input command here
                sb.append(printInput(line));

                String commandName = null;
                String commandParam = null;

                if ("dir".equals(line) || "up".equals(line)) {
                    commandName = line;
                } else if (line.contains("mkdir")) {
                    commandName = "mkdir";
                    commandParam = findParameter(line, commandName);
                } else if (line.contains("cd")) {
                    commandName = "cd";
                    commandParam = findParameter(line, commandName);
                }

                CommandExecutor executor = new CommandExecutor(commandName, commandParam, current);

                sb.append(executor.output());
                current = executor.getCurrent();

                line = br.readLine();
            }


            System.out.print(sb.toString());
        } finally {
            br.close();
        }

        System.out.println( "End of problem 5 by team X" );
    }

    private static String printInput(String line) {
        return "Command: " + line + "\n";
    }

    private static String findParameter(String line, String key) {
        int pos = line.indexOf(key);
        return line.substring(pos + key.length() + 1).trim();
    }
}
