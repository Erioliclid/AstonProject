import org.example.City;
import org.example.CityArrayList.CityArrayList;
import org.example.Sorting.ComparatorStrategy;
import org.example.Sorting.MergeSortAdditional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        CityArrayList<City> cities = new CityArrayList<>();
        cities.add(new City("CityA", 100, 0));
        cities.add(new City("CityB", 55, 0));
        cities.add(new City("CityC", 200, 0));
        cities.add(new City("CityD", 75, 0));

        CityArrayList<City> result = mergeSortAdditional.sortEvenValues(cities, comparator);

        assertEquals(4, result.size());
        assertTrue(result.get(0).getPopulation() % 2 == 0 || result.get(0).getPopulation() == 100);
        assertTrue(result.get(2).getPopulation() % 2 == 0 || result.get(2).getPopulation() == 200);
    }

    @Test
    public void testSortEvenValuesPreservesOddPopulations() {
        CityArrayList<City> cities = new CityArrayList<>();
        cities.add(new City("CityA", 100, 0));
        cities.add(new City("CityB", 55, 0));
        cities.add(new City("CityC", 200, 0));

        CityArrayList<City> result = mergeSortAdditional.sortEvenValues(cities, comparator);

        assertEquals(3, result.size());
        assertEquals(55, result.get(1).getPopulation());
    }

    @Test
    public void testSortEvenValuesAllEven() {
        CityArrayList<City> cities = new CityArrayList<>();
        cities.add(new City("CityA", 200, 0));
        cities.add(new City("CityB", 100, 0));
        cities.add(new City("CityC", 150, 0));

        CityArrayList<City> result = mergeSortAdditional.sortEvenValues(cities, comparator);

        assertEquals(3, result.size());
        assertTrue(result.get(0).getPopulation() <= result.get(1).getPopulation());
    }

    @Test
    public void testSortEvenValuesAllOdd() {
        CityArrayList<City> cities = new CityArrayList<>();
        cities.add(new City("CityA", 201, 0));
        cities.add(new City("CityB", 101, 0));
        cities.add(new City("CityC", 151, 0));

        CityArrayList<City> result = mergeSortAdditional.sortEvenValues(cities, comparator);

        assertEquals(3, result.size());
        assertEquals(201, result.get(0).getPopulation());
    }

    @Test
    public void testSortEvenValuesEmptyList() {
        CityArrayList<City> cities = new CityArrayList<>();
        CityArrayList<City> result = mergeSortAdditional.sortEvenValues(cities, comparator);

        assertEquals(0, result.size());
    }
}