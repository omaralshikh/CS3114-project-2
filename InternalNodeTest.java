
/**
 * 
 */
import student.TestCase;

/**
 * @author omaralshikh, ahmadmalik
 * @version 8/4/2020
 */
public class InternalNodeTest extends TestCase {

    private InternalNode n;
    private FlyweightNode fw;


    /**
     * set up method
     */
    public void setUp() {

        fw = new FlyweightNode();
        n = new InternalNode(fw, 0, 128, 128, 0, 0);
    }


    /**
     * tests add node
     */
    public void testAddNode() {
        InternalNode node = new InternalNode(fw, 0, 0, 0, 0, 0);
        n.addNode(node, 'R');
        assertEquals(1, n.getNumFlyNodes());
        n.addNode(node, 'L');
        assertEquals(0, n.getNumFlyNodes());
        n.addNode(node, 'M');
        assertEquals(0, n.getNumFlyNodes());
    }


    /**
     * tests get Node
     */
    public void testGetNode() {
        assertEquals(fw, n.getNode('R'));
        assertEquals(fw, n.getNode('L'));
        assertEquals(null, n.getNode('M'));
    }


    /**
     * tests the getter from number of flyweight
     */
    public void testGetNumFlyNodes() {
        assertEquals(2, n.getNumFlyNodes());
        InternalNode node = new InternalNode(fw, 0, 0, 0, 0, 0);
        n.addNode(node, 'R');
        assertEquals(1, n.getNumFlyNodes());
        n.addNode(node, 'L');
        assertEquals(0, n.getNumFlyNodes());
    }


    /**
     * tests getter for width
     */
    public void testGetWidth() {
        assertEquals(128, n.getWidth());
        n.setWidth(100);
        assertEquals(100, n.getWidth());

    }


    /**
     * test getter for height
     */
    public void testGetHeight() {
        assertEquals(128, n.getHeight());
        n.setHeight(100);
        assertEquals(100, n.getHeight());

    }


    /**
     * '
     * tests origin methods
     */
    public void testOrigin() {
        assertEquals(0, n.xOrigin());
        n.setXOrigin(5);
        assertEquals(5, n.xOrigin());
        assertEquals(0, n.yOrigin());
        n.setYOrigin(5);
        assertEquals(5, n.yOrigin());

    }


    /**
     * tests the value of left and right child
     */
    public void testChild() {
        assertEquals(fw, n.getLeftChild());
        assertEquals(fw, n.getRightChild());

    }

}
