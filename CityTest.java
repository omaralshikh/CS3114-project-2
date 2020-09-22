import student.TestCase;

/**
 * test class for city
 * 
 * @author omaralshikh ahmadmalik
 * @version 8/4/2020
 */
public class CityTest extends TestCase {

    private City city;


    /**
     * set up method
     */
    public void setUp() {
        city = new City(120, 100, "BB");
    }


    /**
     * tests to string method
     */
    public void testToString() {
        assertEquals("120 100 BB", city.toString());
    }


    /**
     * tests getters
     */
    public void testgetters() {
        assertEquals("BB", city.getName());
        assertEquals(120, city.getX());
        assertEquals(100, city.getY());

    }

}
