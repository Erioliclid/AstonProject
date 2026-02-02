package org.example.gen;

import org.example.City;
import org.example.CityArrayList.CityArrayList;
import org.example.CityArrayListCollector;
import org.example.exception.NotValidCityDataException;

import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

public class GenerateCityList {
    private final Random rnd = new Random();

    public CityArrayList<City> get() {
        int quantity = rnd.nextInt(5_000);

        return get(quantity);
    }

    public CityArrayList<City> get(int quantity) {
        CityArrayList<City> cityList;

        Function<Integer, City> task2 = new Function<Integer, City>() {
            @Override
            public City apply(Integer i) {
                City newGeneratedCity = null;
                try {
                    newGeneratedCity = GenCity.get();
                } catch (NotValidCityDataException e) {
                    System.err.println("GenerateCityList: get(int quantity): " + e.getMessage());
                }
                Objects.requireNonNull(newGeneratedCity);

                return newGeneratedCity;
            }
        };


            cityList = IntStream.range(0, quantity)
                    .mapToObj(task2::apply)
                    .collect(CityArrayListCollector.toCityArrayList());

        return cityList;
    }
}
