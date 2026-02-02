import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.City;
import org.example.Sorting.PopulationComparator;
import org.example.build.CityConcept;
import org.example.build.CityDirector;
import org.example.build.ICityBuilder;
import org.example.exception.NotValidCityDataException;
import org.junit.jupiter.api.Test;


public class PopulationComparatorTest {

    private final PopulationComparator comparator = new PopulationComparator();

    @Test
    void whenFirstCityHasSmallerPopulation() {
        City city1 = createTestCity("SmallCity", 100000, 1900);
        City city2 = createTestCity("BigCity", 5000000, 1800);

        int result = comparator.compare(city1, city2);

        assertTrue(result < 0, "Когда население city1 меньше city2, результат должен быть < 0");
    }

    @Test
    void whenFirstCityHasLargerPopulation() {
        City city1 = createTestCity("MegaCity", 1000000, 1950);
        City city2 = createTestCity("MediumCity", 200000, 1850);

        int result = comparator.compare(city1, city2);

        assertTrue(result > 0, "Когда население city1 больше city2, результат должен быть > 0");
    }

    @Test
    void whenCitiesHaveEqualPopulation() {
        City city1 = createTestCity("CityA", 1500000, 1700);
        City city2 = createTestCity("CityB", 1500000, 1800);

        int result = comparator.compare(city1, city2);

        assertEquals(0, result, "При одинаковом населении должен возвращаться 0");
    }

    @Test
    void whenPopulationDifferenceIsMinimal() {
        City city1 = createTestCity("CityA", 100001, 1901);
        City city2 = createTestCity("CityB", 100000, 1901);

        int result = comparator.compare(city1, city2);

        assertTrue(result > 0, "При минимальной разнице в 1 человек результат должен быть > 0");
    }

    @Test
    void naturalOrderVerification() {
        City smallCity = createTestCity("Village", 40000, 1950);
        City mediumCity = createTestCity("Town", 50000, 1850);
        City largeCity = createTestCity("Metropolis", 500000, 1750);

        assertTrue(comparator.compare(smallCity, mediumCity) < 0);

        assertTrue(comparator.compare(mediumCity, largeCity) < 0);

        assertTrue(comparator.compare(smallCity, largeCity) < 0);
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
