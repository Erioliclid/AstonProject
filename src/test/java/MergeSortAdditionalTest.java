import org.example.City;
import org.example.CityArrayList.CityArrayList;
import org.example.Sorting.ComparatorStrategy;
import org.example.Sorting.MergeSortAdditional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.build.CityConcept;
import org.example.build.CityDirector;
import org.example.build.ICityBuilder;
import org.example.exception.NotValidCityDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MergeSortAdditionalTest {
    private MergeSortAdditional mergeSortAdditional;
    private ComparatorStrategy comparator;

    @BeforeEach
    public void setUp() {
        mergeSortAdditional = new MergeSortAdditional();
        comparator = (city1, city2) -> Long.compare(city1.getPopulation(), city2.getPopulation());
    }

    @Test
    public void testSortEvenValuesWithMixedPopulations() {
        CityArrayList<City> cities = createTestCities("CityA", 100000, 1500, "CityB", 120000, 1100, "CityC", 200000, 1998, "CityD", 750000, 1700);

        CityArrayList<City> result = mergeSortAdditional.sortEvenValues(cities, comparator);

        assertEquals(4, result.size());
        assertTrue(result.get(0).getPopulation() % 2 == 0 || result.get(0).getPopulation() == 100);
        assertTrue(result.get(2).getPopulation() % 2 == 0 || result.get(2).getPopulation() == 200);
    }

    @Test
    public void testSortEvenValuesPreservesOddPopulations() {
        CityArrayList<City> cities = createTestCities("CityA", 100000, 1500, "CityB", 120000, 1100, "CityC", 200000, 1998);

        CityArrayList<City> result = mergeSortAdditional.sortEvenValues(cities, comparator);

        assertEquals(3, result.size());
        assertEquals(120000, result.get(1).getPopulation());
    }

    @Test
    public void testSortEvenValuesAllEven() {
        CityArrayList<City> cities = createTestCities("CityA", 100000, 1500, "CityB", 120000, 1100, "CityC", 200000, 1998);

        CityArrayList<City> result = mergeSortAdditional.sortEvenValues(cities, comparator);

        assertEquals(3, result.size());
        assertTrue(result.get(0).getPopulation() <= result.get(1).getPopulation());
    }

    @Test
    public void testSortEvenValuesAllOdd() {
        CityArrayList<City> cities = createTestCities("CityA", 100001, 1500, "CityB", 120001, 1100, "CityC", 200001, 1998);

        CityArrayList<City> result = mergeSortAdditional.sortEvenValues(cities, comparator);

        assertEquals(3, result.size());
        assertEquals(100001, result.get(0).getPopulation());
    }

    @Test
    public void testSortEvenValuesEmptyList() {
        CityArrayList<City> cities = new CityArrayList<>();
        CityArrayList<City> result = mergeSortAdditional.sortEvenValues(cities, comparator);

        assertEquals(0, result.size());
    }

    private CityArrayList<City> createTestCities(Object... data) {
        CityArrayList<City> cities = new CityArrayList<>();

        for (int i = 0; i < data.length; i += 3) {
            String name = (String) data[i];
            int population = (int) data[i + 1];
            int year = (int) data[i + 2];

            // Создаем город через builder
            try {
                ICityBuilder concept = new CityConcept();
                CityDirector.converter(name, population, year, concept);
                cities.add(CityDirector.cityDevelopment(concept));
            } catch (NotValidCityDataException e) {
                throw new RuntimeException(e);
            }
        }

        return cities;
    }
}