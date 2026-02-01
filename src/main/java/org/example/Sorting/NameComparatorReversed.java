package org.example.Sorting;

import org.example.City;

public class NameComparatorReversed implements ComparatorStrategy {
    @Override
    public int compare(City city1, City city2) {
        return city2.getName().compareTo(city1.getName());
    }

}
