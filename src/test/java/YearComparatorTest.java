import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.City;
import org.example.Sorting.YearComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class YearComparatorTest {

    private YearComparator comparator;
    private City city1;
    private City city2;

    @BeforeEach
    public void setUp() {
        comparator = new YearComparator();
    }

    @Test
    public void testCompareCity1EarlierYear() {
        city1 = new City("City1", 0, 1000);
        city2 = new City("City2", 0, 1500);
        assertTrue(comparator.compare(city1, city2) < 0);
    }

    @Test
    public void testCompareCity1LaterYear() {
        city1 = new City("City1", 0, 1500);
        city2 = new City("City2", 0, 1000);
        assertTrue(comparator.compare(city1, city2) > 0);
    }

    @Test
    public void testCompareEqualYears() {
        city1 = new City("City1", 0, 1000);
        city2 = new City("City2", 0, 1000);
        assertEquals(0, comparator.compare(city1, city2));
    }
}
