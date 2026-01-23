package org.example;

public class YearComparator implements ComparatorStrategy {
    @Override
    public int compare(City city1, City city2) {
        return Integer.compare(city1.getYear(), city2.getYear());
    }

}
