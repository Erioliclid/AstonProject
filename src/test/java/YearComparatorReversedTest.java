
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.City;
import org.example.Sorting.YearComparatorReversed;
import org.junit.jupiter.api.Test;


public class YearComparatorReversedTest {
  
    @Test
    public void testCompareFirstCityYearGreater() {
        City city1 = new City("City1",0, 200);
        City city2 = new City("City2",0, 100);
        YearComparatorReversed comparator = new YearComparatorReversed();
        assertTrue(comparator.compare(city1, city2) < 0);
    }
    
    @Test
    public void testCompareSecondCityYearGreater() {
        City city1 = new City("City1", 2010, 100);
        City city2 = new City("City2", 2020, 200);
        YearComparatorReversed comparator = new YearComparatorReversed();
        assertTrue(comparator.compare(city1, city2) > 0);
    }
    
    @Test
    public void testCompareEqualYears() {
        City city1 = new City("City1",0, 2015);
        City city2 = new City("City2",0, 2015);
        YearComparatorReversed comparator = new YearComparatorReversed();
        assertEquals(0, comparator.compare(city1, city2));
    }
}
