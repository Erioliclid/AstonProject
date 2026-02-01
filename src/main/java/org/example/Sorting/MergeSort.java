package org.example.Sorting;

import org.example.City;
import org.example.CityArrayList.CityArrayList;


public class MergeSort {
    public CityArrayList<City> sort(CityArrayList<City> list, ComparatorStrategy comparator) {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;
        
        CityArrayList<City> left = new CityArrayList<>(mid);
        CityArrayList<City> right = new CityArrayList<>(list.size() - mid);
        
        for (int i = 0; i < mid; i++) {
            left.add(list.get(i));
        }
        
        for (int i = mid; i < list.size(); i++) {
            right.add(list.get(i));
        }

        left = sort(left, comparator);
        right = sort(right, comparator);

        return merge(left, right, comparator);
    }

    private CityArrayList<City> merge(CityArrayList<City> left, CityArrayList<City> right, ComparatorStrategy comparator) {
        CityArrayList<City> merged = new CityArrayList<>(left.size() + right.size());
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }

        return merged;
    }
}
