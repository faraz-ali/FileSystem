package FileSystemSim;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an individual directory in a File System.
 */
public class FileNode {
    private FileSystemElement data;
    private List<FileNode> children;
    private FileNode parent;

    public FileNode(FileSystemElement data) {
        this.data = data;
        this.children = new ArrayList<FileNode>();
    }

    public FileNode(FileNode fileNode) {
        this.data = (FileSystemElement) fileNode.getData();
        children = new ArrayList<FileNode>();
    }

    public void addChild(FileNode child) {
        child.setParent(this);
        children.add(child);
    }

    public void addChildAt(int index, FileNode child) {
        child.setParent(this);
        this.children.add(index, child);
    }

    public void setChildren(List<FileNode> children) {
        for (FileNode child : children)
            child.setParent(this);

        this.children = children;
    }

    public void removeChildren() {
        this.children.clear();
    }


    public FileNode removeChildAt(int index) {
        return children.remove(index);
    }


    public void removeThisIfItsAChild(FileNode childToBeDeleted)
    {
        List<FileNode> list = getChildren();
        list.remove(childToBeDeleted);
    }

    public FileSystemElement getData() {
        return this.data;
    }

    public void setData(FileSystemElement data) {
        this.data = data;
    }

    public FileNode getParent() {
        return this.parent;
    }

    public void setParent(FileNode parent) {
        this.parent = parent;
    }

    public List<FileNode> getChildren() {
        return this.children;
    }

    public FileNode getChildAt(int index) {
        return children.get(index);
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj)
            return false;

        if (obj instanceof FileNode) {
            if (((FileNode) obj).getData().equals(this.data))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }

}