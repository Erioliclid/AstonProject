
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.example.City;
import org.example.Sorting.MergeSort;
import org.junit.jupiter.api.Test;


public class MergeSortTest {

    private MergeSort mergeSort = new MergeSort();

    @Test
    public void testSortEmptyList() {
        ArrayList<City> list = new ArrayList<>();
        ArrayList<City> result = mergeSort.sort(list, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        assertEquals(0, result.size());
    }

    @Test
    public void testSortSingleElement() {
        ArrayList<City> list = new ArrayList<>();
        list.add(new City("New York", 8000000, 1624));
        ArrayList<City> result = mergeSort.sort(list, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        assertEquals(1, result.size());
        assertEquals("New York", result.get(0).getName());
    }

    @Test
    public void testSortMultipleElements() {
        ArrayList<City> list = new ArrayList<>();
        list.add(new City("Chicago", 2700000, 1837));
        list.add(new City("New York", 8000000, 1624));
        list.add(new City("Atlanta", 500000, 1788));
        ArrayList<City> result = mergeSort.sort(list, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        assertEquals(3, result.size());
        assertEquals("Atlanta", result.get(0).getName());
        assertEquals("Chicago", result.get(1).getName());
        assertEquals("New York", result.get(2).getName());
    }

    @Test
    public void testSortByPopulation() {
        ArrayList<City> list = new ArrayList<>();
        list.add(new City("Chicago", 2700000, 1837));
        list.add(new City("New York", 8000000, 1624));
        list.add(new City("Atlanta", 500000, 1788));
        ArrayList<City> result = mergeSort.sort(list, (c1, c2) -> Integer.compare(c1.getPopulation(), c2.getPopulation()));
        assertEquals(500000, result.get(0).getPopulation());
        assertEquals(2700000, result.get(1).getPopulation());
        assertEquals(8000000, result.get(2).getPopulation());
    }

    @Test
    public void testSortAlreadySorted() {
        ArrayList<City> list = new ArrayList<>();
        list.add(new City("Atlanta", 500000, 1788));
        list.add(new City("Chicago", 2700000, 1837));
        list.add(new City("New York", 8000000, 1624));
        ArrayList<City> result = mergeSort.sort(list, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        assertEquals(3, result.size());
        assertEquals("Atlanta", result.get(0).getName());
    }
}
