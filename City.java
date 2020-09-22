
/**
 * City class
 * 
 * @author omaralshikh ahmad mailk
 * @version 8/4/2020
 */
public class City {
    /* Hold the x, y and name of the city */
    private int x;
    private int y;
    private String name;


    /**
     * constructor
     * 
     * @param x
     *            x value
     * @param y
     *            y value
     * @param name
     *            city name
     */
    public City(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }


    /**
     * to string method
     * 
     * @return String
     *         representing the city
     */
    public String toString() {
        return (this.x + " " + this.y + " " + this.name);
    }


    /**
     * getter method for x coordinate
     * 
     * @return x
     */
    public int getX() {
        return x;
    }


    /**
     * getter method for y cordinate
     * 
     * @return y
     */
    public int getY() {
        return y;
    }


    /**
     * getter for city name
     * 
     * @return city name
     */
    public String getName() {
        return name;
    }

}
