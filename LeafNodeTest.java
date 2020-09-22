
/**
 * 
 */
import student.TestCase;

/**
 * test class for leaf node
 * 
 * @author omaralshikh ahmadmalik
 * @version 8/4/2020
 * 
 */
public class LeafNodeTest extends TestCase {

    private LeafNode nd;


    /**
     * set up method
     */
    public void setUp() {
        nd = new LeafNode("bburg", 128, 128, 0);
    }


    /**
     * tests get name method
     */
    public void testGetName() {
        assertEquals("bburg", nd.getName());
    }


    /**
     * tests get x method
     */
    public void testGetX() {
        assertEquals(128, nd.getX());
    }


    /**
     * tests get y method
     */
    public void testGetY() {
        assertEquals(128, nd.getY());
    }


    /**
     * tests the to string methods
     */
    public void testToString() {
        assertEquals("bburg 128 128", nd.toString());

    }

}
