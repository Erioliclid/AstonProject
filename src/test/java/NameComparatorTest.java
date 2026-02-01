//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.example.City;
//import org.example.Sorting.NameComparator;
//import org.junit.jupiter.api.Test;
//
//public class NameComparatorTest {
//
//    @Test
//    void testCompareSameName() {
//        City city1 = new City("London", 0, 0);
//        City city2 = new City("London", 0, 0);
//        NameComparator comparator = new NameComparator();
//        assertEquals(0, comparator.compare(city1, city2));
//    }
//
//    @Test
//    void testCompareFirstNameBeforeSecond() {
//        City city1 = new City("Amsterdam", 0, 0);
//        City city2 = new City("Berlin", 0, 0);
//        NameComparator comparator = new NameComparator();
//        assertTrue(comparator.compare(city1, city2) < 0);
//    }
//
//    @Test
//    void testCompareFirstNameAfterSecond() {
//        City city1 = new City("Zurich", 0, 0);
//        City city2 = new City("Geneva", 0, 0);
//        NameComparator comparator = new NameComparator();
//        assertTrue(comparator.compare(city1, city2) > 0);
//    }
//
//    @Test
//    void testCompareCaseSensitivity() {
//        City city1 = new City("amsterdam", 0, 0);
//        City city2 = new City("Amsterdam", 0, 0);
//        NameComparator comparator = new NameComparator();
//        assertTrue(comparator.compare(city1, city2) > 0);
//    }
//
//    @Test
//    void testCompareEmptyNames() {
//        City city1 = new City("", 0, 0);
//        City city2 = new City("", 0, 0);
//        NameComparator comparator = new NameComparator();
//        assertEquals(0, comparator.compare(city1, city2));
//    }
//}
