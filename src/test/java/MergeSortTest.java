import org.example.City;
import org.example.CityArrayList.CityArrayList;
import org.example.Sorting.MergeSort;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.build.CityConcept;
import org.example.build.CityDirector;
import org.example.build.ICityBuilder;
import org.example.exception.NotValidCityDataException;
import org.junit.jupiter.api.Test;

public class MergeSortTest {

    private final MergeSort mergeSort = new MergeSort();

    @Test
    public void testSortEmptyList() {
        CityArrayList<City> list = new CityArrayList<>();
        CityArrayList<City> result = mergeSort.sort(list, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        assertEquals(0, result.size());
    }

    @Test
    public void testSortSingleElement() {
        CityArrayList<City> list = createTestCities("New York", 8000000, 1624);
        CityArrayList<City> result = mergeSort.sort(list, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        assertEquals(1, result.size());
        assertEquals("New York", result.get(0).getName());
    }

    @Test
    public void testSortMultipleElements() {
        CityArrayList<City> list = createTestCities("Atlanta", 500000, 1788, "Chicago", 2700000, 1837, "New York", 8000000, 1624);
        CityArrayList<City> result = mergeSort.sort(list, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        assertEquals(3, result.size());
        assertEquals("Atlanta", result.get(0).getName());
        assertEquals("Chicago", result.get(1).getName());
        assertEquals("New York", result.get(2).getName());
    }

    @Test
    public void testSortByPopulation() {
        CityArrayList<City> list = createTestCities("Atlanta", 500000, 1788, "Chicago", 2700000, 1837, "New York", 8000000, 1624);
        CityArrayList<City> result = mergeSort.sort(list, (c1, c2) -> Integer.compare(c1.getPopulation(), c2.getPopulation()));
        assertEquals(500000, result.get(0).getPopulation());
        assertEquals(2700000, result.get(1).getPopulation());
        assertEquals(8000000, result.get(2).getPopulation());
    }

    @Test
    public void testSortAlreadySorted() {
        CityArrayList<City> list = createTestCities("Atlanta", 500000, 1788, "Chicago", 2700000, 1837, "New York", 8000000, 1624);
        CityArrayList<City> result = mergeSort.sort(list, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        assertEquals(3, result.size());
        assertEquals("Atlanta", result.get(0).getName());
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