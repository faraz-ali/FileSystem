package FileSystemSim;

import java.util.ArrayList;

/**
 * Represents the Root Directory in a File System
 * @author Faraz Ali
 */
public class VirtualDirectory {
    private FileNode root;

    public VirtualDirectory(FileNode root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public FileNode getRoot() {
        return root;
    }

    public void setRoot(FileNode root) {
        this.root = root;
    }

    public boolean exists(FileSystemElement key) {
        return find(root, key);
    }

    public FileNode findNode(String key) {
        return findNode(root, key);
    }

    private boolean find(FileNode FileNode, FileSystemElement keyNode) {
        boolean res = false;
        if (FileNode.getData().equals(keyNode))
            return true;

        else {
            for (FileNode child : FileNode.getChildren())
                if (find(child, keyNode))
                    res = true;
        }

        return res;
    }

    public FileNode findNode(FileNode FileNode, String keyNode) {
        if(FileNode == null)
            return null;
        if(FileNode.getData().getName().equals(keyNode))
            return FileNode;
        else {
            FileNode cnode = null;
            for (FileNode child : FileNode.getChildren())
                if ((cnode = findNode(child, keyNode)) != null)
                    return cnode;
        }
        return null;
    }

    public ArrayList<FileNode> getPreOrderTraversal() {
        ArrayList<FileNode> preOrder = new ArrayList<FileNode>();
        buildPreOrder(root, preOrder);
        return preOrder;
    }

    private void buildPreOrder(FileNode FileNode, ArrayList<FileNode> preOrder) {
        preOrder.add(FileNode);
        for (FileNode child : FileNode.getChildren()) {
            buildPreOrder(child, preOrder);
        }
    }
}
