package org.example.Sorting;

import org.example.City;

public class PopulationComparatorReversed implements ComparatorStrategy {
    @Override
    public int compare(City city1, City city2) {
        return Integer.compare(city2.getPopulation(), city1.getPopulation());
    }

}
