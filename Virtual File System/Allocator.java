import java.util.ArrayList;

public interface Allocator {
    boolean allocate(Files file);

    void deAllocate(Files file);
}
