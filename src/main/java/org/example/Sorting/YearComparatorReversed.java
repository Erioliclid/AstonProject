package org.example.Sorting;

import org.example.City;

public class YearComparatorReversed implements ComparatorStrategy {
    @Override
    public int compare(City city1, City city2) {
        return Integer.compare(city2.getYear(), city1.getYear());
    }

}
