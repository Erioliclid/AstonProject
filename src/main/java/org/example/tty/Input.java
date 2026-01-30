package org.example.tty;

import org.example.City;
import org.example.CityConcept;
import org.example.CityDirector;
import org.example.ICityBuilder;
import org.example.country.Rule;
import org.example.exception.NotValidCityDataException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Input {
    private static final String regExpSeparator = "[,;.|]";
    private static final Scanner sc = new Scanner(System.in);
    private static final ICityBuilder concept = new CityConcept();

    public static City getCity() throws NotValidCityDataException {
        Rule rule = Rule.RU;
        List<String> tokens = new ArrayList<>();
        int tokenQuantity = 3;
        String inputString = sc.nextLine();

        tokens = Arrays.stream(inputString.split(regExpSeparator)).map(String::trim).toList();

        tokens.forEach(System.out::println);

        tokenQuantity = tokens.size();
        if (tokenQuantity == 3 || tokenQuantity == 4) {
            CityDirector.converter(tokens.get(0), tokens.get(1), tokens.get(2), concept);
            if (tokenQuantity == 4) {
                try {
                    rule = Rule.valueOf(tokens.get(3));
                }
                catch (IllegalArgumentException e) {
                    throw new NotValidCityDataException(e);
                }
            }
        }
        else
            throw new NotValidCityDataException("tty.Input: getCity()");

        return CityDirector.cityDevelopment(concept, rule);
    }
}
