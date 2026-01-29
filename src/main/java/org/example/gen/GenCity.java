package org.example.gen;

import org.example.*;
import org.example.country.Alphabet;
import org.example.country.Rule;
import org.example.exception.NotValidCityDataException;

import java.time.LocalDate;
import java.util.Random;

public class GenCity {
    private static final Random rnd;
    private static final ICityBuilder defaultConcept;

    static {
        rnd = new Random();
        defaultConcept = new CityConcept();
    }

    public static String name() {
        final int maxLength = 20;
        final int minLength = 4;
        int choiceAlphabet = rnd.nextInt(Rule.getQuantity());
        Alphabet alphabet = Rule.values()[choiceAlphabet].getAlphabet();
        int letterQuantity = rnd.nextInt(maxLength - minLength) + minLength;
        int minCodeLetter = alphabet.firstLowercase();
        int alphabetSize = alphabet.lastLowercase() - alphabet.firstLowercase();
        StringBuilder name = new StringBuilder();

        name.append((char) (rnd.nextInt(alphabetSize) + alphabet.firstCapital()));
        letterQuantity--;
        for (int i = 0; i < letterQuantity; i++) {
            name.append((char) (rnd.nextInt(alphabetSize) + minCodeLetter));
        }

        return name.toString();
    }

    public static int population() {
        final int minPopulation = 30_000;
        final int maxPopulation = 100_000_000;
        final int range = maxPopulation - minPopulation;

        return rnd.nextInt(range) + minPopulation;
    }

    public static int year() {
        final int minYear = 1;
        final int maxYear = LocalDate.now().getYear();
        final int range = maxYear - minYear + 1;

        return rnd.nextInt(range) + minYear;
    }

    public static City get() throws NotValidCityDataException {
        return get(defaultConcept);
    }

    public static City get(ICityBuilder concept) throws NotValidCityDataException {
        concept.setName(GenCity.name()).setPopulation(GenCity.population()).setYear(GenCity.year());
        return CityDirector.cityDevelopment(concept);
    }
}
