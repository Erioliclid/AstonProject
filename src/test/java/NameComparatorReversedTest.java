//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.example.City;
//import org.example.Sorting.NameComparatorReversed;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//
//public class NameComparatorReversedTest {
//
//    private NameComparatorReversed comparator;
//    private City city1;
//    private City city2;
//
//    @BeforeEach
//    public void setUp() {
//        comparator = new NameComparatorReversed();
//    }
//
//    @Test
//    public void testCompareReversedOrder() {
//        city1 = new City("Apple", 100000, 250);
//        city2 = new City("Banana", 150000, 300);
//        assertTrue(comparator.compare(city1, city2) > 0);
//    }
//
//    @Test
//    public void testCompareEqual() {
//        city1 = new City("City", 100000, 250);
//        city2 = new City("City", 150000, 300);
//        assertEquals(0, comparator.compare(city1, city2));
//    }
//
//    @Test
//    public void testCompareNegative() {
//        city1 = new City("Zebra", 100000, 250);
//        city2 = new City("Apple", 150000, 300);
//        assertTrue(comparator.compare(city1, city2) < 0);
//    }
//
//    @Test
//    public void testCompareCaseSensitive() {
//        city1 = new City("apple", 100000, 250);
//        city2 = new City("Apple", 150000, 300);
//        assertNotEquals(0, comparator.compare(city1, city2));
//    }
//}
