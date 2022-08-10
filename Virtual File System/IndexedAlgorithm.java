import java.util.ArrayList;

public class IndexedAlgorithm implements Allocator {
    private final ArrayList<Boolean> space;
    private final int N;
    public IndexedAlgorithm(int N) {
        this.N = N;
        space = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            space.add(false);
        }
    }

    @Override
    public boolean allocate(Files file) {
        int size = file.getSize() + 1;
        ArrayList<Integer> temp = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < N&&count<size; i++) { // search for any empty blocks and allocate them and add them to the index block
            temp.add(SpaceManager.freedblocks.get(i));
            SpaceManager.spaces.set(SpaceManager.freedblocks.get(i), true);
            count++;
        }

        if (count < size) { // file cannot be allocated
            for(int i=0;i<temp.size();i++) {
                SpaceManager.spaces.set(temp.get(i), false);
            }
            return false;
        }
        file.setType("Indexed");
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
