package org.example;

import java.util.Objects;

public class CityHandler {
    public static String getName(City city) {
        Objects.requireNonNull(city);
        return city.name;
    }

    public static int getPopulation(City city) {
        Objects.requireNonNull(city);
        return city.population;
    }

    public static int getYear(City city) {
        Objects.requireNonNull(city);
        return city.year;
    }
}
