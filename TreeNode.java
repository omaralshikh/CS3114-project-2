/**
 * This class represents a general node in the tree. It is abstract, and cannot
 * be initialized.
 * 
 * @author omaral shikh, ahmad mailk
 * @version 8/4/2020
 */
public abstract class TreeNode {
    /**
     * Represents the level of the node. Used for print string formating.
     */
    private int level;


    /**
     * Returns the level of the node.
     * 
     * @return - the level of the node
     */
    public int getLevel() {
        return level;
    }


    /**
     * Sets the level of the node to a new value.
     * 
     * @param l
     *            - the new level for the node
     */
    public void setLevel(int l) {
        level = l;
    }
}
