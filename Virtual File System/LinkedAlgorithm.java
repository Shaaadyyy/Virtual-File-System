import java.util.ArrayList;

public class LinkedAlgorithm implements Allocator {
    private final int N;
    public LinkedAlgorithm(int N) {
        this.N = N;
    }
    @Override
    public boolean allocate(Files file) {
        ArrayList<Integer> temp = new ArrayList<>();
        int size=file.getSize();
        int count=0;
        for(int i=0; i < N && count < size;i++)
        {
            temp.add(SpaceManager.freedblocks.get(i));
            SpaceManager.spaces.set(SpaceManager.freedblocks.get(i), true);
            count++;
        }
        if (count < size)
        {
            return false;
        }
        file.setType("Linked");
        file.setData(temp);
        SpaceManager.getEmptySpace();
        return true;
    }

    @Override
    public void deAllocate(Files file) {
        for(int i=0;i<file.getData().size();i++)
        {
            SpaceManager.spaces.set(file.getData().get(i), false);
            SpaceManager.getEmptySpace();
        }
    }
}