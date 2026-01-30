package org.example.tty;

import org.example.City;
import org.example.CityConcept;
import org.example.CityDirector;
import org.example.ICityBuilder;
import org.example.country.Rule;
import org.example.exception.NotValidCityDataException;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Input {
    private static final Scanner sc = new Scanner(System.in);
    private static final String regExpSeparator = "(,|;|\\.|\\|)";
//    private static final String w = "[\\w[^_]]";
//    private static final String regExpName = w + "(" + w
    private static final ICityBuilder concept = new CityConcept();

    public static City getCity() throws NotValidCityDataException {
        Rule rule = Rule.RU;

        List<String> tokens = sc.useDelimiter(regExpSeparator).tokens().collect(Collectors.toList());
        int tokenQuantity = tokens.size();
        if (tokenQuantity == 3 || tokenQuantity == 4) {
            tokens.stream().forEach((s) -> s.trim());
            CityDirector.converter(tokens.getFirst(), tokens.get(1), tokens.get(2), concept);
            if (tokenQuantity == 4) {
                try {
                    rule = Rule.valueOf(tokens.getLast());
                }
                catch (IllegalArgumentException e) {
                    throw new NotValidCityDataException(e);
                }
            }
        }
        else
            throw new NotValidCityDataException("con.Input: getCity()");

        return CityDirector.cityDevelopment(concept, rule);
    }
}
