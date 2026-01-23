package org.example;

import java.util.Comparator;

public interface ComparatorStrategy extends Comparator<City> {
    int compare(City city1, City city2);
}
