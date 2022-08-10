import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static Directory root;
    private static Operations op=new Operations();
    private static final Scanner in = new Scanner(System.in);
    public static SpaceManager manager;

    public static void main(String[] arg) throws IOException {
        VirtualFile vf;
        root = new Directory("root");
        System.out.println("1-New");
        System.out.println("2-Load");
        int Choice1 = in.nextInt();
        if(Choice1==1) {
            System.out.println("Enter the disk size (in KB): ");
            int N = in.nextInt();
            manager=new SpaceManager(N);

            while(true) {
                System.out.println("Enter the allocation type:");
                System.out.println("1- Contiguous Allocation" +
                        "\n2- Linked Allocation" +
                        "\n3- Indexed Allocation"+
                        "\n4- exit");
                int allocationType = in.nextInt();
                if (allocationType == 1) {
                    root.setAllocationType(new ContiguousAlgorithm());
                } else if (allocationType == 2) {
                    root.setAllocationType( new LinkedAlgorithm(N));
                } else if (allocationType == 3) {
                    root.setAllocationType(new IndexedAlgorithm(N));
                }
                else
                {
                    break;
                }
                in.nextLine();
                while (true) {
                    System.out.print("Enter command: ");
                    String command = in.nextLine();
                    String[] args = command.split(" ");
                    if (command.equalsIgnoreCase("exit"))
                        break;
                    else if (args[0].equalsIgnoreCase("CreateFile"))
                    {
                        op.createFile(args, root);
                    }
                    else if (args[0].equalsIgnoreCase("CreateFolder"))
                    {
                        op.createFolder(args, root);
                    }
                    else if (args[0].equalsIgnoreCase("DeleteFile"))
                        op.deleteFile(args, root);
                    else if (args[0].equalsIgnoreCase("DeleteFolder"))
                        op.deleteFolder(args, root);
                    else if (args[0].equalsIgnoreCase("DisplayDiskStatus"))
                        op.displayDiskStatus(args, root);
                    else if (args[0].equalsIgnoreCase("DisplayDiskStructure")) {
                        op.displayDiskStructure(args, root);
                        vf=new VirtualFile(false);
                    }
                    else if (args[0].equalsIgnoreCase("changeAlloction"))
                    {
                        break;
                    }
                    else
                        System.out.println("Invalid Option!");

                }

            }


        }





    }



}