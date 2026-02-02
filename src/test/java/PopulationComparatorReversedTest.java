import org.example.City;
import org.example.Sorting.PopulationComparatorReversed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.build.CityConcept;
import org.example.build.CityDirector;
import org.example.build.ICityBuilder;
import org.example.exception.NotValidCityDataException;
import org.junit.jupiter.api.Test;


public class PopulationComparatorReversedTest {

    private final PopulationComparatorReversed comparator = new PopulationComparatorReversed();

    @Test
    public void testCompareCity1SmallerPopulation() {
        City city1 = createTestCity("MegaCity", 10000000, 1950);
        City city2 = createTestCity("MediumCity", 2000000, 1850);

        int result = comparator.compare(city1, city2);

        assertTrue(result < 0, "Когда население city1 больше, результат должен быть < 0");
    }

    @Test
    public void testCompareCity2SmallerPopulation() {
        City city1 = createTestCity("MegaCity", 10000000, 1950);
        City city2 = createTestCity("MediumCity", 2000000, 1850);

        int result = comparator.compare(city1, city2);

        assertTrue(result < 0, "Когда население city1 больше, результат должен быть < 0");
    }

    @Test
    public void testCompareEqualPopulation() {
        City city1 = createTestCity("CityA", 1500000, 1700);
        City city2 = createTestCity("CityB", 1500000, 1800);

        int result = comparator.compare(city1, city2);

        assertEquals(0, result, "При одинаковом населении должен возвращаться 0");
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

