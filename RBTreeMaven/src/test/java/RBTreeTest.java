import org.junit.Test;
import static org.junit.Assert.*;

public class RBTreeTest {

    private RBTree tree = new RBTree(Integer.MIN_VALUE);
    private int[] testValues = new int[] { 5, 12, -1, 67, 5, 4, 21, 45, 2, 3, 124, 300 };

    @Test
    public void checkInsert() {
        initialize();

        assertTrue(tree.search(-1));
        assertTrue(tree.search(4));
        assertTrue(tree.search(5));
        assertTrue(tree.search(124));
        assertTrue(tree.search(300));

        assertFalse(tree.search(6));
        assertFalse(tree.search(301));
        assertFalse(tree.search(1024));
    }


    @Test
    public void checkCountNodes() {
        initialize();

        assertEquals(tree.countNodes(),11);
    }


    @Test
    public void checkOnEmpty() {
        initialize();

        assertFalse(tree.isEmpty());
        tree.clear();
        assertTrue(tree.isEmpty());
    }


    private void initialize(){
        for (int value : testValues) {
            tree.insert(value);
        }
    }
}