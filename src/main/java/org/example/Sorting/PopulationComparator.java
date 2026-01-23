package org.example.Sorting;

import org.example.City;

public class PopulationComparator implements ComparatorStrategy {
    @Override
    public int compare(City city1, City city2) {
        return Integer.compare(city1.getPopulation(), city2.getPopulation());
    }

}
