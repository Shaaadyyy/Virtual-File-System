import java.util.ArrayList;

public class SpaceManager {
    public static ArrayList<Boolean> spaces;
    public static ArrayList<Integer> freedblocks ;
    static int emptySpace ;
    static int diskSize;
    SpaceManager(int size){
        spaces = new ArrayList<Boolean>();
        freedblocks = new ArrayList<Integer>();
        for(int i=0;i<size;i++)
        {
            freedblocks.add(i);
            spaces.add(false);
        }
        this.diskSize=size;
    }
    public static void displayEmptySpace()
    {
        for (Integer block : freedblocks) {
            System.out.print(block + " ");
        }
    }
    public static void getEmptySpace()
    {
        freedblocks.clear();
        emptySpace=0;
        for (int i=0;i<spaces.size();i++) {
            if (!spaces.get(i)) {
                emptySpace++;
                freedblocks.add(i);
            }
        }
    }
    public static void displayAllocatedSpace()
    {
        for(int i=0;i<spaces.size();i++)
        {
            if(spaces.get(i))
            {
                System.out.print(i+" ");
            }
        }
    }


}
