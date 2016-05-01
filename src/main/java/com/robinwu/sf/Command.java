package com.robinwu.sf;

/**
 * @author Robin Wu (rwu@groupon.com)
 * @since 5/1/16.
 */
public interface Command {
    public String output();
    public Directory getCurrent();
}
