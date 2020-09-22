/**
 * internal node class
 * 
 * @author omaralshikh ahmad mailk
 * @version 8/4/2020
 */
public class InternalNode extends TreeNode {

    private TreeNode leftChild;
    private TreeNode rightChild;
    private int width;
    private int height;
    private int yOrigin;
    private int xOrigin;


    /**
     * 
     * Constructor that initializes the internal node
     *
     * @param fw
     *            - the empty flyweight node in the tree
     * @param level
     *            - the level of the node
     * @param w
     *            width of the internal nodes box
     * @param h
     *            height of the internal nodes box
     * @param xO
     *            x origin of the internal nodes box
     * @param yO
     *            y origin of the internal nodes box
     */
    public InternalNode(
        FlyweightNode fw,
        int level,
        int w,
        int h,
        int xO,
        int yO) {
        leftChild = fw;
        rightChild = fw;
        width = w;
        height = h;
        xOrigin = xO;
        yOrigin = yO;
        setLevel(level);
    }


    /**
     * adds node to position
     * 
     * @param node
     *            node to be added
     * @param pos
     *            position to be added to
     */
    public void addNode(TreeNode node, char pos) {
        if (pos == 'L') {
            leftChild = node;
        }
        else if (pos == 'R') {
            rightChild = node;
        }
    }


    /**
     * getters for node
     * 
     * @param pos
     *            position of pointer
     * @return returns left child if going left and right otherwise;
     */
    public TreeNode getNode(char pos) {

        if (pos == 'L') {
            return leftChild;
        }
        else if (pos == 'R') {
            return rightChild;
        }
        return null;
    }


    /**
     * Method to determine the number of flyweight child nodes this internal
     * node
     * has. Used to check if removal is needed.
     * 
     * @return the number of flyweight nodes
     */
    public int getNumFlyNodes() {
        return (leftChild instanceof FlyweightNode ? 1 : 0)
            + (rightChild instanceof FlyweightNode ? 1 : 0);
    }


    /**
     * getter for width
     * 
     * @return the width
     */
    public int getWidth() {
        return this.width;
    }


    /**
     * gets the height
     * 
     * @return the height
     */
    public int getHeight() {
        return this.height;
    }


    /**
     * initializes x
     * 
     * @return x
     */
    public int xOrigin() {
        return this.xOrigin;
    }


    /**
     * initializes y
     * 
     * @return y
     */
    public int yOrigin() {
        return this.yOrigin;
    }


    /**
     * sets width
     * 
     * @param w
     *            width
     */
    public void setWidth(int w) {
        width = w;
    }


    /**
     * sets height
     * 
     * @param h
     *            height
     */
    public void setHeight(int h) {
        height = h;
    }


    /**
     * sets the x value
     * 
     * @param x
     *            x value
     */
    public void setXOrigin(int x) {
        xOrigin = x;
    }


    /**
     * sets the y value
     * 
     * @param y
     *            y value
     */
    public void setYOrigin(int y) {
        yOrigin = y;
    }


    /**
     * getter for left child
     * 
     * @return left child
     */
    public TreeNode getLeftChild() {
        return leftChild;

    }


    /**
     * gets the right child
     * 
     * @return right child
     */
    public TreeNode getRightChild() {
        return rightChild;
    }
}
