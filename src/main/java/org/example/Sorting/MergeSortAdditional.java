package org.example.Sorting;

import org.example.City;
import org.example.CityArrayList.CityArrayList;

public class MergeSortAdditional {
    public CityArrayList<City> sortEvenValues(CityArrayList<City> list, ComparatorStrategy comparator) {
        CityArrayList<City> evenCities = new CityArrayList<>();
        CityArrayList<City> oddCities = new CityArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            City city = list.get(i);
            if (city.getPopulation() % 2 == 0) {
                evenCities.add(city);
            } else {
                oddCities.add(city);
            }
        }

        evenCities = new MergeSort().sort(evenCities, comparator);

        CityArrayList<City> sortedList = new CityArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            sortedList.add(list.get(i));
        }

        int evenIndex = 0;
        for (int i = 0; i < sortedList.size(); i++) {
            if (sortedList.get(i).getPopulation() % 2 == 0) {
                sortedList.put(evenCities.get(evenIndex), i);
                evenIndex++;
            }
        }

        return sortedList;
    }
}