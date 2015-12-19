package FileSystemSim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Simulates a File System environment. A non-deletable, root directory ("/") is created
 * at the start of the program.
 * @author Faraz Ali
 */
public class VirtualFileSystem {
    private FileNode root = new FileNode(new FileSystemElement("/", "10 kb"));
    private VirtualDirectory dir = new VirtualDirectory(root);
    private FileNode currentNode = root;

    public static void main(String args[]) {
        VirtualFileSystem linux = new VirtualFileSystem();
        System.out.println("Welcome to the Linux File System simulator.\n type \"man\" for a list of commands");

        //  open up standard input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = null;

        do {
            try {
                System.out.print("> ");
                command = br.readLine();
            } catch (IOException ioe) {
                System.out.println("IO error trying to read your name!");
            }
            System.out.print("> ");
            linux.parser(command);


        } while(!command.equalsIgnoreCase("exit"));
    }


    void parser(String input) {
        String[] args = input.split(" ");
        String command = args[0];

        if(command.equals("man")) {
            man();
        }
        else if(command.equals("list")) {
            list();
        }
        else if(command.equals("cd")) {
            cd(args);
        }
        else if(command.equals("ls")) {
            ls(args);
        }
        else if(command.equals("mkdir")) {
            if (args.length <=1) {
                badCommnad();
            } else
                mkdir(args);
        }
        else if(command.equals("pwd")) {
            pwd(args);
        }
        else if(command.equals("rm")) {
            if (args.length <=1) {
                badCommnad();
            } else
                rm(args);
        }
        else if(command.equals("parent")) {
            if (args.length <=1) {
                badCommnad();
            } else
                parent(args);
        }
        else if(command.equals("exit")) {
            System.out.println("Bye.");
            System.exit(0);
        }
        else {
            invalidCommand(args);
        }
    }

    void badCommnad() {
        System.out.println("Insufficient arguments or bad command.\nRefer the man page and try again.");
    }
    void man() {
        System.out.println("[mkdir <name>] :creates a directory in the current directory. Duplicates not allowed");
        System.out.println("[cd <name>] : navigate to the given directory, if it exists.");
        System.out.println("[ls] : lists all directories in the file system in using Pre Order Traversal.");
        System.out.println("[ls] : lists all sub directories in the current directory.");
        System.out.println("[pwd] : print working directory");
        System.out.println("[rm <name>] :\t\t delete the current directory");
        System.out.println("[parent <name>] : prints the parent directory, without changing the current directory.");
        System.out.println("[exit] : exit the console.");
    }

    void list() {
        for (FileNode file: dir.getPreOrderTraversal()) {
            System.out.println(file.getData().getName());
        }
    }

    void cd(String[] args) {
        if (dir.findNode(args[1]) != null) {
            currentNode = dir.findNode(args[1]);
            System.out.println(currentNode.getData().getName());
        } else {
            System.out.println(args[1] + " no such directory found.");
        }
    }
    void mkdir(String[] args) {
        boolean exists = true;
        for ( FileNode file: currentNode.getChildren()) {
            if(file.getData().getName().equals(args[1])) {
                System.out.println(args[1] + " already exists.");
                exists = false;
                break;
            }
        }
        if(exists) {
            currentNode.addChild(new FileNode(new FileSystemElement(args[1], "10 KB")));
        }
    }
    void ls(String[] args) {
        for ( FileNode file: currentNode.getChildren()) {
            System.out.printf("%s\t%s\t%s", file.getData().getName(), file.getData().getSize(), file.getData().getTimeStamp());
            System.out.println(file.getData().getName());
        }
    }
    void parent(String[] args) {
        System.out.println(dir.findNode(args[1]).getParent().getData().getName());
    }
    void rm(String[] args) {
        if(dir.findNode(args[1]).equals(dir.getRoot())) {
            System.out.println("cannot delete root");
        } else {
            currentNode = dir.findNode(args[1]).getParent();

            if (dir.findNode(args[1]) != null) {
                currentNode.removeChildAt(getIndex(currentNode, dir.findNode(args[1])));

                System.out.println(args[1] + " deleted.");
            } else {
                System.out.println(args[1] + " no such directory found.");
            }
        }
    }

    private int getIndex(FileNode parentNode, FileNode target) {
        int index = 0;
        for(FileNode node: parentNode.getChildren()) {
            if(target.getData().equals(node.getData())) {
                break;
            } else {
                index++;
            }
        }
        return index;
    }

    void pwd(String[] args) {
        System.out.println (currentNode.getData().getName());
    }
    void invalidCommand(String[] args) {
        System.out.println( "illegal command: " + args[0]);
    }
}