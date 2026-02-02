import org.example.City;
import org.example.CityArrayList.CityArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CityArrayListTest {

    CityArrayList<City> cityArrayList;
    City city1;
    City city2;
    City city3;

    @BeforeEach
    public void setUp(){
        cityArrayList = new CityArrayList<>();
        city1 = new City();
        city2 = new City();
        city3 = new City();
        cityArrayList.add(city1);
        cityArrayList.add(city2);
        cityArrayList.add(city3);
    }

    @Test
    public void testGetCity() {
        Assertions.assertEquals(city2, cityArrayList.get(1));
    }

    @Test
    public void testAddCity() {
        Assertions.assertEquals(3, cityArrayList.size());
    }

    @Test
    public void testClearCity() {
        cityArrayList.clear();
        Assertions.assertEquals(0, cityArrayList.size());
    }

    @Test
    public void testIsEmptyCity() {
        Assertions.assertFalse(cityArrayList.isEmpty());
    }

    @Test
    public void testCapacityCity() {
        Assertions.assertEquals(12, cityArrayList.capacity());
    }

    @Test
    public void testPutCity() {
        City city4 = new City();
        cityArrayList.put(city4, 0);
        Assertions.assertEquals(city4, cityArrayList.get(0));
    }

    @Test
    public void testRemoveCity() {
        cityArrayList.remove();
        Assertions.assertEquals(2, cityArrayList.size());
    }

    @Test
    public void testRemoveByIndexCity() {
        cityArrayList.remove(0);
        Assertions.assertEquals(2, cityArrayList.size());
    }
}
