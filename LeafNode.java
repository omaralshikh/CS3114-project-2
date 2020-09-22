/**
 * leaf node class
 * 
 * @author omaralshikh ahmad malik
 * @version 8/4/2020
 */
public class LeafNode extends TreeNode {

    private City city;


    /**
     * constructor
     * 
     * @param name
     *            name of city
     * @param x
     *            x value of city
     * @param y
     *            y value of city
     * @param level
     *            level of tree
     */
    public LeafNode(String name, int x, int y, int level) {
        city = new City(x, y, name);
        setLevel(level);
    }


    /**
     * getters for city name
     * 
     * @return city name
     */
    public String getName() {
        return this.city.getName();
    }


    /**
     * getter x coordinate
     * 
     * @return x coordinate
     */
    public int getX() {
        return this.city.getX();
    }


    /**
     * getter for y coordinate
     * 
     * @return y coordinate
     */
    public int getY() {
        return this.city.getY();
    }


    /**
     * to string method which prints the city held by this node
     * 
     * @return string
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(city.getName());
        result.append(" ");
        result.append(city.getX());
        result.append(" ");
        result.append(city.getY());

        return result.toString();

    }


    /**
     * to string method which prints the city held by this node with commas
     * 
     * @return string
     */
    public String toStringP() {
        StringBuilder result = new StringBuilder();
        result.append(city.getName());
        result.append(", ");
        result.append(city.getX());
        result.append(", ");
        result.append(city.getY());

        return result.toString();

    }
}
