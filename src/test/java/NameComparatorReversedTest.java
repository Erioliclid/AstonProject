
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.City;
import org.example.Sorting.NameComparatorReversed;
import org.example.build.CityConcept;
import org.example.build.CityDirector;
import org.example.build.ICityBuilder;
import org.example.exception.NotValidCityDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class NameComparatorReversedTest {

    private NameComparatorReversed comparator;
    private City city1;
    private City city2;

    @BeforeEach
    public void setUp() {
        comparator = new NameComparatorReversed();
    }

    @Test
    public void testCompareReversedOrder() {
        city1 = createTestCity("Apple", 100000, 250);
        city2 = createTestCity("Banana", 150000, 300);
        assertTrue(comparator.compare(city1, city2) > 0);
    }

    @Test
    public void testCompareEqual() {
        city1 = createTestCity("City", 100000, 250);
        city2 = createTestCity("City", 150000, 300);
        assertEquals(0, comparator.compare(city1, city2));
    }

    @Test
    public void testCompareNegative() {
        city1 = createTestCity("Zebra", 100000, 250);
        city2 = createTestCity("Apple", 150000, 300);
        assertTrue(comparator.compare(city1, city2) < 0);
    }

    @Test
    public void testCompareCaseSensitive() {
        city1 = createTestCity("apple", 100000, 250);
        city2 = createTestCity("Apple", 150000, 300);
        assertNotEquals(0, comparator.compare(city1, city2));
    }

    private City createTestCity(Object... data) {
        String name = (String) data[0];
        int population = (int) data[1];
        int year = (int) data[2];

        // Создаем город через builder
        try {
            ICityBuilder concept = new CityConcept();
            CityDirector.converter(name, population, year, concept);
            return CityDirector.cityDevelopment(concept);
        } catch (NotValidCityDataException e) {
            throw new RuntimeException(e);
        }
    }
}
