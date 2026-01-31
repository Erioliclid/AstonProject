package org.example.build;

import org.example.City;
import org.example.exception.NotValidCityDataException;
import org.example.country.Rule;

import java.time.LocalDate;
import java.util.Objects;

public class CityDirector {

     public static boolean validate(City city) {
        return validate(city, Rule.RU);
    }

    public static boolean validate(City city, Rule rule) {
        Objects.requireNonNull(city);
        Rule ruleCurrent = (rule == Rule.DEFAULT) ? Rule.UN : rule;

        return validator(city, ruleCurrent);
    }

    public static boolean validate(String name, int population, int year) {
        return validator(name, population, year, Rule.RU);
    }

    public static boolean validate(String name, int population, int year, Rule rule) {
        Objects.requireNonNull(name);
        Rule ruleCurrent = (rule == Rule.DEFAULT) ? Rule.UN : rule;
        return validator(name, population, year, ruleCurrent);
    }

    private static boolean validator(City city, Rule rule) {
        return checkName(city.getName())
                && checkPopulation(city.getPopulation(), rule)
                && checkYear(city.getYear());
    }

    private static boolean validator(String name, int population, int year, Rule rule) {
        return checkName(name)
                && checkPopulation(population, rule)
                && checkYear(year);
    }

    private static boolean checkName(String name) {
        final String nameRegExp =
                "^[a-zA-Zа-яёА-ЯЁ]+($|(\\s-\\s|-|\\s)[a-zA-Zа-яА-Я]+)*($|(\\s-\\s|-)\\d+$)";

        return !name.isEmpty() && name.matches(nameRegExp);
    }

    private static boolean checkPopulation(int population, Rule rule) {
        final int highLimit = 100_000_000;
        int lowLimit = rule.getMinPopulationForCityStatus();

        return population >= lowLimit && population < highLimit;
    }

    private static boolean checkYear(int year) {
        final int lowLimit = -20_000;
        final int highLimit = LocalDate.now().getYear();

        return year > lowLimit && year <= highLimit;
    }

    public static ICityBuilder converter(String name, int population, int year, ICityBuilder concept) {
        concept
                .setName(name)
                .setPopulation(population)
                .setYear(year);

        return concept;
    }

    public static ICityBuilder converter(String name, String population, String year, ICityBuilder concept) throws NotValidCityDataException {
        String[] inputData = {name, population, year};
        return converter(inputData, concept);
    }

    public static ICityBuilder converter(String[] inputData, ICityBuilder concept) throws NotValidCityDataException {
        Objects.requireNonNull(inputData);
        Objects.requireNonNull(inputData[0]);
        Objects.requireNonNull(inputData[1]);
        Objects.requireNonNull(inputData[2]);

        String name = inputData[0];
        if (!inputData[1].matches("\\d+") || !inputData[2].matches("-?\\d+"))
            throw new NotValidCityDataException("CityDirector: converter(String[], ICityBuilder)");

        int population = Integer.parseInt(inputData[1]);
        int year = Integer.parseInt(inputData[2]);

        return converter(name, population, year, concept);
    }

    public static City cityDevelopment(ICityBuilder concept) throws NotValidCityDataException {
        return cityDevelopment(concept, Rule.RU);
    }

    public static City cityDevelopment(ICityBuilder concept, Rule country) throws NotValidCityDataException {
        City newCity = concept.getCityAfterBuild();
        if (!validate(newCity, country))
            throw new NotValidCityDataException("cityDevelopment: input data for create object type City not valid");
        return newCity;
    }
}
