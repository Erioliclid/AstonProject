import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.City;
import org.example.Sorting.YearComparator;
import org.example.build.CityConcept;
import org.example.build.CityDirector;
import org.example.build.ICityBuilder;
import org.example.exception.NotValidCityDataException;
import org.junit.jupiter.api.Test;


public class YearComparatorTest {

    private final YearComparator comparator = new YearComparator();

    @Test
    void whenFirstCityIsOlder() {
        City city1 = createTestCity("AncientCity", 100000, 100);
        City city2 = createTestCity("ModernCity", 5000000, 2000);

        int result = comparator.compare(city1, city2);

        assertTrue(result < 0, "Когда city1 старше (год меньше), результат должен быть < 0");
    }

    @Test
    void whenFirstCityIsNewer() {
        City city1 = createTestCity("NewCity", 10000000, 2020);
        City city2 = createTestCity("OldCity", 2000000, 1500);

        int result = comparator.compare(city1, city2);

        assertTrue(result > 0, "Когда city1 новее (год больше), результат должен быть > 0");
    }

    @Test
    void whenCitiesFoundedInSameYear() {
        City city1 = createTestCity("CityA", 1500000, 1700);
        City city2 = createTestCity("CityB", 5000000, 1700);

        int result = comparator.compare(city1, city2);

        assertEquals(0, result, "При одинаковом годе основания должен возвращаться 0");
    }

    @Test
    void whenYearDifferenceIsMinimal() {
        City city1 = createTestCity("CityA", 1000000, 1901);
        City city2 = createTestCity("CityB", 1000000, 1900);

        int result = comparator.compare(city1, city2);

        assertTrue(result > 0, "При разнице в 1 год результат должен быть > 0");
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
