package org.example.gen;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.example.City;
import org.example.exception.NotValidCityDataException;
import org.example.country.Alphabet;

class GenCityTest {

    @Test
    void name() {
        StringBuilder regExp = new StringBuilder();

        ArrayList<String> regExpList = new ArrayList<>();

        for (Alphabet a: Alphabet.values()) {
            regExpList.add(a.getRegExp());
        }

        regExp.append('(').append(String.join("|", regExpList))
                .append(")+");

        final String regExpString = regExp.toString();
        for (int i = 0; i < 1000; i++) {
            assertTrue(GenCity.name().matches(regExpString));
        }
    }

    @Test
    void population() {
        final int minPopulation = 250;
        final int maxPopulation = 100_000_000;
        int feedback;

        for (int i = 0; i < 1000; i++) {
            feedback = GenCity.population();
            assertTrue(feedback >= minPopulation && feedback < maxPopulation);
        }
    }

    @Test
    void year() {
        final int minYear = 1;
        final int maxYear = LocalDate.now().getYear();
        int feedback;

        for (int i = 0; i < 1000; i++) {
            feedback = GenCity.year();
            assertTrue(feedback >= minYear && feedback <= maxYear);
        }

    }

    @Test
    void get() {

        try {
            for (int i = 0; i < 1000; i++) {
                assertInstanceOf(City.class, GenCity.get());
            }
        } catch (NotValidCityDataException e) {
            fail("GenCity: get() to fail");
        }

    }
}