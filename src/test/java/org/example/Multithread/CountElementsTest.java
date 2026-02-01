package org.example.Multithread;

import org.example.City;
import org.example.CityArrayList.CityArrayList;
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
        this.city1 = new City("A", 1, 1234);
        this.city2 = new City("B", 2, 5678);
        this.city3 = new City("C", 3, 7890);
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
        CountElements.count(cityArrayList, city1);

        assertTrue(outContent.toString().contains("Element City{name='A', population=1, year=1234} have been found 1 times"));
    }
}