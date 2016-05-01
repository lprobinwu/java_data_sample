package com.robinwu.sf;

/**
 * @author Robin Wu (rwu@groupon.com)
 * @since 5/1/16.
 */
public abstract class AbstractCommand implements Command {
    protected Directory current;

    public Directory getCurrent() {
        return current;
    }
}
