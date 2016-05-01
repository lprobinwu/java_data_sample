package com.robinwu.sf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Robin Wu (rwu@groupon.com)
 * @since 5/1/16.
 */
public class Directory {
    /**
     * the name of the directory
     */
    private String name;

    /**
     * the path of this directory
     */
    private String path;

    /**
     * the parent directory
     */
    private Directory parent = null;

    /**
     * the list of sub directories
     */
    private List<Directory> children = new ArrayList<Directory>();

    private Set<String> subDirNames = new HashSet<String>();

    public Directory(String name) {
        this.name = name;
    }

    public Directory(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Directory> getChildren() {
        return children;
    }

    public void setChildren(List<Directory> children) {
        this.children = children;
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public void addChild(Directory child) {
        this.children.add(child);
        this.subDirNames.add(child.getName());
        child.parent = this;
    }

    public Set<String> getSubDirNames() {
        return subDirNames;
    }

    public void setSubDirNames(Set<String> subDirNames) {
        this.subDirNames = subDirNames;
    }

    public String getFullPath() {
        if (this.path == null) {
            return this.name;
        }
        return this.path + "\\" + this.name;
    }
}
