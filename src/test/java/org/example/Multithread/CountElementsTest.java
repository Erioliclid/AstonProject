package org.example.Multithread;

import org.example.City;
import org.example.CityArrayList.CityArrayList;
import org.example.build.CityConcept;
import org.example.build.CityDirector;
import org.example.build.ICityBuilder;
import org.example.exception.NotValidCityDataException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class CountElementsTest {

    CityArrayList<City> cityArrayList;
    City city1;
    City city2;
    City city3;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));

        this.cityArrayList = new CityArrayList<>();
        this.city1 = createTestCity("A", 100000, 1234);
        this.city2 = createTestCity("B", 200000, 1678);
        this.city3 = createTestCity("C", 300000, 1890);
        this.cityArrayList.add(city1);
        this.cityArrayList.add(city2);
        this.cityArrayList.add(city3);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void count() {
        CountElements.count(this.cityArrayList, city1);

        assertEquals(CountElements.getTotalCount(), 1);
    }

    private City createTestCity(Object... data) {
        String name = (String) data[0];
        int population = (int) data[1];
        int year = (int) data[2];

        // Создаем город через builder
        try {
            ICityBuilder concept = new CityConcept();
            CityDirector.converter(name, population, year, concept);
            return CityDirector.cityDevelopment(concept);
        } catch (NotValidCityDataException e) {
            throw new RuntimeException(e);
        }
    }
}