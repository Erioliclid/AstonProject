package org.example.Sorting;

import org.example.City;

public class NameComparator implements ComparatorStrategy {
    @Override
    public int compare(City city1, City city2) {
        return city1.getName().compareTo(city2.getName());
    }

}
