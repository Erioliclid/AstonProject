//import org.example.City;
//import org.example.Sorting.PopulationComparatorReversed;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import org.junit.jupiter.api.Test;
//
//
//public class PopulationComparatorReversedTest {
//
//    @Test
//    public void testCompareCity1SmallerPopulation() {
//        City city1 = new City("City1", 50000, 5000);
//        City city2 = new City("City2", 100000, 3000);
//        PopulationComparatorReversed comparator = new PopulationComparatorReversed();
//        assertTrue(comparator.compare(city1, city2) > 0);
//    }
//
//    @Test
//    public void testCompareCity2SmallerPopulation() {
//        City city1 = new City("City1", 100000, 3000);
//        City city2 = new City("City2", 50000, 5000);
//        PopulationComparatorReversed comparator = new PopulationComparatorReversed();
//        assertTrue(comparator.compare(city1, city2) < 0);
//    }
//
//    @Test
//    public void testCompareEqualPopulation() {
//        City city1 = new City("City1", 75000, 4000);
//        City city2 = new City("City2", 75000, 4000);
//        PopulationComparatorReversed comparator = new PopulationComparatorReversed();
//        assertEquals(0, comparator.compare(city1, city2));
//    }
//
//    @Test
//    public void testCompareZeroPopulations() {
//        City city1 = new City("City1", 0, 0);
//        City city2 = new City("City2", 0, 0);
//        PopulationComparatorReversed comparator = new PopulationComparatorReversed();
//        assertEquals(0, comparator.compare(city1, city2));
//    }
//}
//
