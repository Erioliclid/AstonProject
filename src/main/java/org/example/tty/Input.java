package org.example.tty;

import org.example.City;
import org.example.build.CityConcept;
import org.example.build.CityDirector;
import org.example.build.ICityBuilder;
import org.example.country.Rule;
import org.example.exception.NotValidCityDataException;

import java.util.*;

public class Input {
    private static final String regExpSeparator = "[,;.|]";
    private static final Scanner sc = new Scanner(System.in);
    private static final ICityBuilder concept = new CityConcept();

    public static City getCity() throws NotValidCityDataException {
        Rule rule = Rule.RU;
        String inputString = sc.nextLine();

        List<String> tokens = Arrays.stream(inputString.split(regExpSeparator)).map(String::trim).toList();

        int tokenQuantity = tokens.size();
        if (tokenQuantity == 3 || tokenQuantity == 4) {
            CityDirector.converter(tokens.get(0), tokens.get(1), tokens.get(2), concept);
            if (tokenQuantity == 4) {
                try {
                    rule = Rule.valueOf(tokens.get(3));
                }
                catch (IllegalArgumentException e) {
                    throw new NotValidCityDataException("tty.Input: getCity(): not correct two-letter country name code system, choice either RU or UN");
                }
            }
        }
        else
            throw new NotValidCityDataException("tty.Input: getCity(): input string not valid for create a City object");

        return CityDirector.cityDevelopment(concept, rule);
    }

    public static Optional<City> startOrder() throws NotValidCityDataException {
        HelpMessage.format();
        HelpMessage.invitation();
        return Optional.of(getCity());
    }

    public static Optional<City> continueOrder() throws NotValidCityDataException {
        HelpMessage.invitation();
        return Optional.of(getCity());
    }

/*    public static void main(String[] args) {
        Optional<City> ret = Optional.empty();
        try {
            ret = startOrder();
        } catch (NotValidCityDataException e) {
            System.out.println(e.getMessage());
        }
            if (ret.isPresent())
                System.out.println(ret.get());
            else
                System.out.println("Fail");
    }*/
}
