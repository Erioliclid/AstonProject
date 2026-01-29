package org.example;

import java.util.stream.Collector;

public class CityArrayListCollector {
    public static Collector<City, ?, CityArrayList<City>> toCityArrayList() {
        return Collector.of(
                CityArrayList::new,
                CityArrayList::add,
                (left, right) -> {
                    for (City city : right) {
                        left.add(city);
                    }
                    return left;
                },
                Collector.Characteristics.IDENTITY_FINISH
        );
    }
}
