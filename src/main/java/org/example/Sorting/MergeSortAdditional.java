package org.example.Sorting;

import java.util.ArrayList;
import org.example.City;

public class MergeSortAdditional {
    public ArrayList<City> sortEvenValues(ArrayList<City> list, ComparatorStrategy comparator) {
        ArrayList<City> evenCities = new ArrayList<>();
        ArrayList<City> oddCities = new ArrayList<>();

        for (City city : list) {
            if (city.getPopulation() % 2 == 0) {
                evenCities.add(city);
            } else {
                oddCities.add(city);
            }
        }

        evenCities = new MergeSort().sort(evenCities, comparator);

        ArrayList<City> sortedList = new ArrayList<>(list);
        int evenIndex = 0;
        for (int i = 0; i < sortedList.size(); i++) {
            if (sortedList.get(i).getPopulation() % 2 == 0) {
                sortedList.set(i, evenCities.get(evenIndex++));
            }
        }

        return sortedList;
    }
}
