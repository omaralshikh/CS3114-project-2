/**
 * class that handles all the recursion
 * 
 * @author omaralshikh ahamdmalik
 * @version 8/4/2020
 *
 */
public class BinTree {

    private TreeNode root; // root node of the tree
    private FlyweightNode fw; // flyweight node of the tree
    private int numNodes; // variable used by various functions to count the num
                          // nodes visited by that function.


    /**
     * constructor for BinTree
     */
    public BinTree() {
        fw = new FlyweightNode();
        root = fw;
        numNodes = 1;
    }


    /**
     * j
     * 
     * @param x
     *            x coordinate value to be inserted
     * @param y
     *            y coordinate value to be inserted
     * @param name
     *            name of city inserted
     * @return a recursive call so it keeps inserting into the tree
     */
    public int insert(int x, int y, String name) {
        /* Check if the root is flyweight (empty tree) */
        if (root instanceof FlyweightNode) {
            /* If so make the root node a leaf node and hold the city */
            root = new LeafNode(name, x, y, 0);
            return 0;
        }
        /* Check if there is only one node in the tree */
        if (root instanceof LeafNode) {
            /* Replace leaf node with internal node and downshift */
            /* This will be the new internal node inserted */
            InternalNode temp = new InternalNode(fw, 0, 1024, 1024, 0, 0);
            // Determine weather to insert the previous root node into left or
            // right node of the new internal node
            /* Then add it into the correct position */
            if (((LeafNode)root).getX() >= temp.getWidth() / 2) {
                temp.addNode(root, 'R');
            }
            else {
                temp.addNode(root, 'L');
            }
            /* Correct the level of the root */
            root.setLevel(1);
            /* Update the root pointer to the new internal node */
            root = temp;
        }
        /* Call the insert helper if for recursive insert */
        return insertHelper(x, y, name, (InternalNode)root);
    }


    /**
     * helper method for insertion
     * 
     * @param x
     *            x coordinate value
     * @param y
     *            y coordinate value
     * @param name
     *            city name inserted
     * @param node
     *            internal node
     * @return int level at which the node was inserted;
     */
    private int insertHelper(int x, int y, String name, InternalNode node) {
        // Variable to store the position i.e Left or Right where the method
        // should try storing node
        Character position;

        /* Calculate the position where node will be inserted */
        if ((node.getLevel()) % 2 == 0) {
            position = (x >= node.getWidth() / 2 + node.xOrigin()) ? 'R' : 'L';
        }
        else {
            position = (y >= node.getHeight() / 2 + node.yOrigin()) ? 'R' : 'L';
        }
        /* Get the child node at the calculated position */
        TreeNode child = node.getNode(position);

        // If the child node is a fly weight insert the node at the calculated
        // position
        if (child instanceof FlyweightNode) {
            node.addNode(new LeafNode(name, x, y, (node.getLevel() + 1)),
                position);
            return (node.getLevel() + 1);
        }
        // this position variable will hold another calculation of Left or Right
        char position2;
        /* Of the child nofe is a leaf node we must insert an internal node */
        if (child instanceof LeafNode) {

            // Checks to see if a duplicate city has been entered. If so it
            // rejects entering the duplicate city
            if (((LeafNode)child).getX() == x && ((LeafNode)child)
                .getY() == y) {
                return (node.getLevel() + 1);
            }
            /* Store the xOrigin and yOrigin of the node */
            int xOrig = node.xOrigin();
            int yOrig = node.yOrigin();
            /*
             * Adjust values of xOrigin and yOrigin based on the level being
             * worked on
             */
            if (position == 'R' && (node.getLevel()) % 2 == 0) {
                xOrig = xOrig + (node.getWidth() / 2);
            }
            else if (position == 'R' && (node.getLevel()) % 2 == 1) {
                yOrig = yOrig + (node.getHeight() / 2);
            }
            /* Create a new internal node using the calculated values */
            InternalNode temp = new InternalNode(fw, child.getLevel(), node
                .getWidth(), node.getHeight(), xOrig, yOrig);

            // Adjust the values of the new internal node temps width and height
            // if necessary
            if ((temp.getLevel() + 1) % 2 == 0) {
                temp.setWidth((temp.getWidth() / 2));
            }
            else {
                temp.setHeight((temp.getHeight() / 2));
            }
            // Calculate the position where the child will be inserted based on
            // the level working on

            if ((temp.getLevel()) % 2 == 0) {
                position2 = ((LeafNode)child).getX() >= ((temp.getWidth() / 2)
                    + temp.xOrigin()) ? 'R' : 'L';
            }
            else {
                position2 = ((LeafNode)child).getY() >= ((temp.getHeight() / 2)
                    + temp.yOrigin()) ? 'R' : 'L';
            }

            /* Add child to the new position2 */
            temp.addNode(child, position2);
            /* Update level values */
            child.setLevel(child.getLevel() + 1);
            /* Add the internal node at the calculated position */
            node.addNode(temp, position);
            /* Adjust pointer */
            child = temp;
        }
        /* Call to recursion to insert the child */
        return

        insertHelper(x, y, name, (InternalNode)child);

    }


    /**
     * removes the value from tree
     * 
     * @param x
     *            x-coordinate of value to be removed
     * @param y
     *            y-coordinate of value to be removed
     * @return recursive call that traverses the tree and removes the value.
     */
    public boolean remove(int x, int y) {
        /*
         * Check if root is flyweight (empty tree) then there is nothinf to
         * remove
         */
        if (root instanceof FlyweightNode) {
            return false;
        }
        /*
         * If root itself holds a city check for a match and remove if possible
         */
        if (root instanceof LeafNode) {
            if (((LeafNode)root).getX() == x && ((LeafNode)root).getY() == y) {
                root = fw;
                return true;
            }
            return false;
        }
        /* Else call the helper method which may recurse */
        return findAndRemove(x, y, (InternalNode)root);
    }


    /**
     * helper method for Remove
     * 
     * @param x
     *            x-coordinate of value to be removed
     * @param y
     *            y-coordinate of value to be removed
     * @param node
     *            internal Node in tree
     * @return recursive call to find and remove value from tree
     */
    private boolean findAndRemove(int x, int y, InternalNode node) {
        /*
         * the char will determine where the remove method should look to remove
         * the
         * city
         */
        Character position;
        /* Calculate the position */
        if (node.getLevel() % 2 == 0) {
            position = (x >= ((node.getWidth() / 2) + node.xOrigin()))
                ? 'R'
                : 'L';
        }
        else {
            position = (y >= ((node.getHeight() / 2) + node.yOrigin()))
                ? 'R'
                : 'L';
        }
        /* Get the node at the calculatedc position */
        TreeNode nextNode = node.getNode(position);

        // return false if the nextNode is a flyweight because city wasnt found
        if (nextNode instanceof FlyweightNode) {
            return false;
        }
        // If a city is found then check to see if correct city and remove
        // else return false
        if (nextNode instanceof LeafNode) {
            if (((LeafNode)nextNode).getX() == x && ((LeafNode)nextNode)
                .getY() == y) {
                node.addNode(fw, position);
                return true;
            }
            return false;
        }

        // make a recursive call to check the nextNodes childs to see if city
        // can be found
        // If removal is successful jump into the if branch to make an
        // fix on the tree so that there are not extra internal nodes
        if (findAndRemove(x, y, (InternalNode)nextNode)) {
            // Handle cases with internal node with only one child
            // This is when an internal node is no longer needed,
            // since it can just be replaced by its only non-empty child
            if (((InternalNode)nextNode).getNumFlyNodes() == 1) {
                char[] chars = { 'L', 'R' };
                /* Check both left and right child */
                for (char currentChar : chars) {
                    TreeNode child = ((InternalNode)nextNode).getNode(
                        currentChar);
                    /*
                     * Get rid of the extra internal node and adjust variables
                     */
                    if (child instanceof LeafNode) {
                        node.addNode(child, position);
                        child.setLevel(child.getLevel() - 1);
                        if (node == root) {
                            if (node.getLeftChild() instanceof LeafNode) {
                                root = node.getLeftChild();
                                root.setLevel(0);
                            }
                            else {
                                root = node.getRightChild();
                                root.setLevel(0);
                            }
                        }
                        return true;
                    }
                }
            }
            return true;
        }
        return false;
    }


    /**
     * prints the message if record is not found
     */
    public void printMsg() {
        System.out.println("Record could not be removed. It does not exist.");
    }


    /**
     * finds any values that are in the tree
     * 
     * @param x
     *            x-coordinate of value to be found
     * @param y
     *            y-coordinate of value to be found
     */
    public void find(int x, int y) {
        /* Make sure tree is not empty */
        if (!(root instanceof FlyweightNode)) {
            /*
             * Call findHelper to recursively find the node which holds the city
             */
            LeafNode temp = findHelper(root, x, y);
            if (temp != null) {
                System.out.println(((LeafNode)temp).toString());
            }
            /* If tree is empty print this */
            else {
                System.out.println(
                    "Record could not be printed.  It does not exist.");
            }
        }
    }


    /**
     * helper method for find
     * 
     * @param node
     *            Tree node to traverse through
     * @param x
     *            x-coordinate of value to be found
     * @param y
     *            y-coordinate of value to be found
     * @return null if not found
     */
    private LeafNode findHelper(TreeNode node, int x, int y) {
        /* Temp will hold the found leaf node which holds the city */
        TreeNode temp = null;
        /* check to see if node is a leaf node and compare city values */
        if (node instanceof LeafNode) {
            if (((LeafNode)node).getX() == x && ((LeafNode)node).getY() == y) {
                return (LeafNode)node;
            }
        }
        /* If node is Internal node then make a recursive call */
        if (node instanceof InternalNode) {
            temp = findHelper(((InternalNode)node).getLeftChild(), x, y);
            if (temp != null) {
                return (LeafNode)temp;
            }
            temp = findHelper(((InternalNode)node).getRightChild(), x, y);
            if (temp != null) {
                return (LeafNode)temp;
            }
        }
        return null;
    }


    /**
     * searches the parameters within the boundaries and returns the values if
     * found
     * 
     * @param x
     *            x-coordinate of value to be searched
     * @param y
     *            y-coordinate of value to be searched
     * @param width
     *            width expansion
     * @param height
     *            height expansion of search
     */
    public void regionSearch(int x, int y, int width, int height) {
        int xMin = x - 1;
        int yMin = y - 1;
        int xMax = x + width;
        int yMax = y + height;

        /* Call the overlap */

        if (checkOverlap(xMin, yMin, xMax, yMax, 0, 0, 1024, 1024)) {
            /*
             * If so call the helper function to recursively find the cites in
             * the region
             */
            regionSearchHelper(root, xMin, yMin, xMax, yMax);
            /* Print the number of nodes visited */
            System.out.println(numNodes + " Nodes visited.");
            /* Reset numNodes for next regionsearch */
            numNodes = 1;
        }
        else {
            System.out.println(
                "The specified region is outside the known world.");
        }
    }


    /**
     * checks if 2 rectangles intersect or not
     * 
     * @param xMin
     *            min value of x for first rectangle
     * @param yMin
     *            min value of y for first rectangle
     * @param xMax
     *            max value of x for first rectangle
     * @param yMax
     *            max value of y for first rectangle
     * @param xMin2
     *            min value of x for second rectangle
     * @param yMin2
     *            min value of y for second rectangle
     * @param xMax2
     *            max value of x for second rectangle
     * @param yMax2
     *            max value of y for second rectangle
     * @return true if they overlaps
     */
    private boolean checkOverlap(
        int xMin,
        int yMin,
        int xMax,
        int yMax,
        int xMin2,
        int yMin2,
        int xMax2,
        int yMax2) {
        // If one rectangle is on left side of other
        if (xMin2 > (xMax + 1) || xMax2 <= (xMin + 1)) {
            return false;
        }
        return !(yMin2 > (yMax + 1) || yMax2 <= (yMin + 1));
    }


    /**
     * helper method for region search
     * 
     * @param node
     *            TreeNode to search through
     * @param xMin
     *            x-coordinate of value to be searched
     * @param yMin
     *            y-coordinate of value to be searched
     * @param xMax
     *            x-coordinate of value to be searched
     * @param yMax
     *            y-coordinate of value to be searched
     */
    private void regionSearchHelper(
        TreeNode node,
        int xMin,
        int yMin,
        int xMax,
        int yMax) {

        /* If leaf node is found check the city and print it */
        if (node instanceof LeafNode) {
            numNodes++;
            if (((LeafNode)node).getX() > xMin && ((LeafNode)node)
                .getX() < xMax) {

                if (((LeafNode)node).getY() > yMin && ((LeafNode)node)
                    .getY() < yMax) {

                    System.out.println(((LeafNode)node).toString());
                }
            }
        }
        // Internal node is found check to see if should be searched in and make
        // a recursive call

        if (node instanceof InternalNode) {

            /* Get the rectangle edge values of the internal node */
            int xOr = ((InternalNode)node).xOrigin();
            int yOr = ((InternalNode)node).yOrigin();
            int xOrMax = ((InternalNode)node).xOrigin() + ((InternalNode)node)
                .getWidth();
            int yOrMax = ((InternalNode)node).yOrigin() + ((InternalNode)node)
                .getHeight();
            /*
             * Check if the internal nodes recangle intersects the region to be
             * searched
             */
            if (checkOverlap(xOr, yOr, xOrMax, yOrMax, xMin, yMin, xMax,
                yMax)) {
                /* If so make recursive calls to the left and right nodes */
                numNodes++;
                regionSearchHelper(((InternalNode)node).getLeftChild(), xMin,
                    yMin, xMax, yMax);
                regionSearchHelper(((InternalNode)node).getRightChild(), xMin,
                    yMin, xMax, yMax);
            }

        }
    }


    /**
     * prints the tree
     */
    public void printTree() {
        /* If tree is empty print this message */
        if ((root instanceof FlyweightNode)) {
            System.out.println("E, 0, 0, 1024, 1024");
        }
        else {
            printHelper(root, 0); /* Else call the print helper */
        }
    }


    /**
     * helper method for printing the tree
     * 
     * @param node
     *            Tree Node of tree
     * @param level
     *            level of tree or height. root starts at level 0
     */
    private void printHelper(TreeNode node, int level) {
        /* Print the white space offset based on the level of the node */
        for (int i = 0; i < (node.getLevel()); i++) {
            System.out.print("  ");
        }
        /* If node holds a city */
        if (node instanceof LeafNode) {
            /* Print details of the city */
            System.out.println(((LeafNode)node).toStringP());
        }
        /* If node is internal node */
        if (node instanceof InternalNode) {
            /* Get the values of the rectangle of the internal node */
            int xOr = ((InternalNode)node).xOrigin();
            int yOr = ((InternalNode)node).yOrigin();
            int wid = ((InternalNode)node).getWidth();
            int hei = ((InternalNode)node).getHeight();
            /* Print message */
            System.out.println("I, " + xOr + ", " + yOr + ", " + wid + ", "
                + hei);
            /* Check if internal node has any flyweights */
            if (((InternalNode)node).getLeftChild() instanceof FlyweightNode) {
                /*
                 * Print the white space offset based on the level of the node
                 */
                for (int i = 0; i < node.getLevel() + 1; i++) {
                    System.out.print("  ");
                }
                /* Print flyweight description */
                System.out.print("E, " + xOr + ", " + yOr + ", ");

                /* Adjust width of flyweight and print */
                if (((InternalNode)node).getLevel() % 2 == 1) {
                    hei = (((InternalNode)node).getHeight() / 2);
                    System.out.println(wid + ", " + hei);
                }
                /* Adjust height of flyweight and print */
                else {
                    wid = (((InternalNode)node).getWidth() / 2);
                    System.out.println(wid + ", " + hei);
                }
            }
            /* Other wise make a recursive call */
            else {
                printHelper(((InternalNode)node).getLeftChild(), node
                    .getLevel());
            }
            /* Check if internal node has any flyweights */
            if (((InternalNode)node).getRightChild() instanceof FlyweightNode) {
                /*
                 * Print the white space offset based on the level of the node
                 */
                for (int i = 0; i < node.getLevel() + 1; i++) {
                    System.out.print("  ");
                }
                /* Start to print flyweight description */
                System.out.print("E, ");
                /* Adjust height of flyweight and print */
                if (((InternalNode)node).getLevel() % 2 == 1) {
                    hei = ((InternalNode)node).getHeight() / 2;
                    System.out.println(xOr + ", " + hei + ", " + wid + ", "
                        + hei);
                }
                /* Adjust width of flyweight and print */
                else {
                    wid = ((InternalNode)node).getWidth() / 2;

                    System.out.println(wid + ", " + yOr + ", " + wid + ", "
                        + hei);
                }
            }
            /* Other wise make a recursive call */
            else {
                printHelper(((InternalNode)node).getRightChild(), node
                    .getLevel());
            }
        }

    }
}