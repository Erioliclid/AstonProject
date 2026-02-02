import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.City;
import org.example.Sorting.NameComparator;
import org.example.build.CityConcept;
import org.example.build.CityDirector;
import org.example.build.ICityBuilder;
import org.example.exception.NotValidCityDataException;
import org.junit.jupiter.api.Test;

public class NameComparatorTest {

    private final NameComparator comparator = new NameComparator();

    @Test
    void testCompare_WhenFirstCityNameComesBeforeSecond() {
        City city1 = createTestCity("Berlin", 3500000, 1237);
        City city2 = createTestCity("Paris", 2100000, 300);

        int result = comparator.compare(city1, city2);

        assertTrue(result < 0, "Berlin должно быть меньше Paris в алфавитном порядке");
    }

    @Test
    void testCompare_WhenFirstCityNameComesAfterSecond() {
        City city1 = createTestCity("Tokyo", 14000000, 1457);
        City city2 = createTestCity("Berlin", 3500000, 1237);

        int result = comparator.compare(city1, city2);

        assertTrue(result > 0, "Tokyo должно быть больше Berlin в алфавитном порядке");
    }
    @Test
    void testCompare_WhenCityNamesAreEqual() {
        City city1 = createTestCity("London", 8900000, 43);
        City city2 = createTestCity("London", 8000000, 50);

        int result = comparator.compare(city1, city2);

        assertEquals(0, result, "При одинаковых названиях должен возвращаться 0, независимо от других полей");
    }

    @Test
    void testCompare_WhenNamesDifferOnlyByCase() {
        City city1 = createTestCity("berlin", 3500000, 1237);
        City city2 = createTestCity("Berlin", 3500000, 1237);

        int result = comparator.compare(city1, city2);

        assertTrue(result > 0, "Сравнение должно учитывать регистр: 'berlin' > 'Berlin'");
    }

    @Test
    void testCompare_WhenNamesWithSpacesAndSpecialCharacters() {
        City city1 = createTestCity("New York", 8400000, 1624);
        City city2 = createTestCity("Paris", 2100000, 300);

        int result = comparator.compare(city1, city2);

        assertTrue(result < 0, "New York должно быть меньше Paris (N < P)");
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
