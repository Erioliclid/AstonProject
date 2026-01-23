package org.example;

import java.util.ArrayList;

public class MergeSort {
    public ArrayList<City> sort(ArrayList<City> list, ComparatorStrategy comparator) {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;
        ArrayList<City> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<City> right = new ArrayList<>(list.subList(mid, list.size()));

        left = sort(left, comparator);
        right = sort(right, comparator);

        return merge(left, right, comparator);
    }

    private ArrayList<City> merge(ArrayList<City> left, ArrayList<City> right, ComparatorStrategy comparator) {
        ArrayList<City> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (comparator.compare((org.example.City) left.get(i), (org.example.City) right.get(j)) <= 0) {
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
