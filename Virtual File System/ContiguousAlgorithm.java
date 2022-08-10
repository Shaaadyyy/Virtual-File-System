
import java.util.ArrayList;

public class ContiguousAlgorithm implements Allocator {


    @Override
    public boolean allocate(Files file) {
        int min = 5555;
        int minIdx = -1;
        int difference = 0;
        int currSize = 0;
        int i = 0;
        while(i < SpaceManager.freedblocks.size())
        {
            for (int j = i ; j < SpaceManager.freedblocks.size()-1; j++)
            {
                difference = SpaceManager.freedblocks.get(j+1)-SpaceManager.freedblocks.get(j);
                if(difference == 1)
                {
                    currSize++;
                }
                else
                {
                    break;
                }

            }
            if(currSize == file.getSize())
            {
                break;
            }
            if(currSize < min && file.getSize() < currSize)
            {
                min = currSize;
                minIdx = SpaceManager.freedblocks.get(i);
            }
            i+=currSize;
        }

        if(min>=file.getSize())
        {
            for (int g = minIdx; g <= minIdx + file.getSize()-1; g++)
            {
                SpaceManager.spaces.set(g, true);
            }
            file.setType("Contiguous");
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(minIdx);
            temp.add(minIdx + file.getSize()-1);
            file.setData(temp);
            SpaceManager.getEmptySpace();
            return true;
        }
        return false;
    }

    @Override
    public void deAllocate(Files file) {
        int idx = file.getData().get(0);
        int size = file.getSize();
        for (int i = idx; i < idx + size; i++) {
            SpaceManager.spaces.set(i, false);
            SpaceManager.getEmptySpace();
        }
    }


}