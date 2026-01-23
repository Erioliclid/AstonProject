package org.example;

public class PopulationComparator implements ComparatorStrategy {
    @Override
    public int compare(City city1, City city2) {
        return Integer.compare(city1.getPopulation(), city2.getPopulation());
    }

}
