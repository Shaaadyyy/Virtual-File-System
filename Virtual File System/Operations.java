import java.util.ArrayList;

public class Operations {
    public static Directory root;

    public void createFolder(String[] args, Directory root) {
        if (args.length == 2) {
            String[] path = args[1].split("/");
            Directory directory = getDirectoryFromPath(path, root);
            if (directory == null) {
                System.out.println("Path Not Found");
                return;
            }
            directory.createFolder(path[path.length - 1]);
        } else {
            System.out.println("Invalid Arguments");
        }
    }

    public void createFile(String[] args, Directory root) {
        if (args.length == 3) {
            String[] path = args[1].split("/");
            Directory directory = getDirectoryFromPath(path, root);
            if (directory == null) {
                System.out.println("Path Not Found");
                return;
            }
            if(directory.getDirectoryName()=="root")
            {

            }
            String size = args[args.length - 1]; // the size of the new file
            for (int i = 0; i < size.length(); i++) {
                if (!Character.isDigit(size.charAt(i))) { // size must only contain digits
                    System.out.println("Size is not valid");
                    return;
                }
            }
            directory.createFile(path[path.length - 1], Integer.parseInt(size)); // create the new file
        } else {
            System.out.println("Invalid Arguments");
        }
    }

    public void deleteFile(String[] args, Directory root) {
        if (args.length == 2) {
            String[] path = args[1].split("/");
            Directory directory = getDirectoryFromPath(path, root);
            if (directory == null) {
                System.out.println("Path Not Found");
                return;
            }
            directory.deleteFile(path[path.length - 1]); // delete the specified file
        } else {
            System.out.println("Invalid Arguments");
        }
    }

    public void deleteFolder(String[] args, Directory root) {
        if (args.length == 2) {
            String[] path = args[1].split("/");
            Directory directory = getDirectoryFromPath(path, root);
            if (directory == null) {
                System.out.println("Path Not Found");
                return;
            }
            directory.deleteFolder(path[path.length - 1]); // delete the specified folder
        } else {
            System.out.println("Invalid Arguments");
        }
    }

    private static Directory getDirectoryFromPath(String[] path, Directory root) {
        Directory cur = root;
        if (!path[0].equalsIgnoreCase("root")) {
            return null;
        }
        for (int i = 0; i < path.length - 2; i++) {
            if (path[i].equalsIgnoreCase(cur.getDirectoryName())) {
                if (cur.searchForSubDirectory(path[i + 1]) != null) {
                    cur = cur.searchForSubDirectory(path[i + 1]);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        return cur;
    }

    public void displayDiskStatus(String[] args, Directory root) {
        if (args.length == 1) {
            int empty_size = SpaceManager.freedblocks.size();
            int used_size = SpaceManager.diskSize - empty_size;
            System.out.println("1- Empty Space: " + empty_size);
            System.out.println("2- Allocated Space: "+ used_size);
            System.out.println("3- Empty Blocks in Disk: " );
            SpaceManager.displayEmptySpace();
            System.out.println();
            System.out.println("4- Allocated Blocks in Disk :- ");
            SpaceManager.displayAllocatedSpace();
            System.out.println();
            root.displayDiskStatus();
            System.out.println();
        } else {
            System.out.println("Invalid Arguments");
        }
    }

    public void displayDiskStructure(String[] args, Directory root) {
        if (args.length == 1) {
            root.displayDiskStructure(0);
        } else {
            System.out.println("Invalid Arguments");
        }
    }
}