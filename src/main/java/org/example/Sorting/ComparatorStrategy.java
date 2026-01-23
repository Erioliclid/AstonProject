package org.example.Sorting;

import java.util.Comparator;
import org.example.City;

public interface ComparatorStrategy extends Comparator<City> {
    int compare(City city1, City city2);
}
