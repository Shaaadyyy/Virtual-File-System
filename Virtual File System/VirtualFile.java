import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public final class VirtualFile {

    int diskSize;
    File file;
    String currentDirectory = System.getProperty("user.dir");
    String filePath = currentDirectory + "\\VirtualFile.txt";
    ArrayList<Boolean> diskBlocks = new ArrayList<>();


    VirtualFile( boolean exist) throws IOException {
        file = new File(filePath);
        for (int i = 0; i < SpaceManager.spaces.size(); i++) {
            diskBlocks.add(SpaceManager.spaces.get(i));
        }
        diskSize = 0;
        if(exist)
            virtualReader();
        else
            virtualWriter();
    }

    void readBlocks() throws FileNotFoundException {
        ArrayList<Boolean> test = new ArrayList<>();
        Scanner reader = new Scanner(file);
        String blocks = reader.nextLine();
        for (int i = 0; i < blocks.length(); i++) {
            if (blocks.charAt(i) == '1') {
                test.add(true);
            } else {
                test.add(false);
            }
        }
        diskSize = test.size();
        Main.manager=new SpaceManager(diskSize);
        for (int i = 0; i < diskSize; i++) {
            SpaceManager.spaces.add(test.get(i));
        }
        System.out.println(diskSize);
    }

    void virtualReader() throws FileNotFoundException {
        readBlocks();

    }

    String tabPrint(int level) {
        String tabs = "";
        for (int i = 0; i < level; i++) {
            tabs += "    ";
        }
        return tabs;
    }

    void writeDirectoryFiles(int level, ArrayList<Files> files) throws IOException {
        String tabs = tabPrint(level);
        FileWriter Writer = new FileWriter(filePath, true);
        for (int g = 0; g < files.size(); g++) {
            Writer.write(tabs + files.get(g).getFileName());
            Writer.write("\r\n");
        }
        Writer.close();
    }

    void writeDirectory(int level, Directory directory) throws IOException {
        String tabs = tabPrint(level);
        FileWriter Writer = new FileWriter(filePath, true);
        Writer.write(tabs + "<" + directory.getDirectoryName() + ">");
        Writer.write("\r\n");
        Writer.close();
        level++;
        writeDirectoryFiles(level, directory.files);
        for (int g = 0; g < directory.subDirectories.size(); g++) {
            writeDirectory(level, directory.subDirectories.get(g));
        }
    }

    void virtualWriter() throws IOException {
        PrintWriter pw = new PrintWriter(filePath);
        pw.write("");
        FileWriter Writer = new FileWriter(filePath, true);
        for (int i = 0; i < diskBlocks.size(); i++) {
            if (diskBlocks.get(i)) {
                Writer.write('1');
            } else {
                Writer.write('0');
            }
        }
        Writer.write("\r\n");
        Writer.close();
        int level = 0;
        writeDirectory(level, Main.root);

    }

}
