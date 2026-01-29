package org.example;

import java.time.LocalDate;
import java.util.Objects;

public class CityDirector {

    public enum RULE {
        UN, // ООН
        RU, // Россия
        UA, // Украина
        UZ, // Узбекистан
        MD, // Республика Молдова
        KG, // Киргизия
        TJ, // Таджикистан
        AU, // Австралия
        IN, // Индия
        JP, // Япония
        DEFAULT // Без указания правил страны, равносильно выбору UN
    }
    public static boolean validate(City city) {
        return validate(city, RULE.RU);
    }

    public static boolean validate(City city, RULE rule) {
        Objects.requireNonNull(city);
        RULE ruleCurrent = (rule == RULE.DEFAULT) ? RULE.UN : rule;

        return validator(city, ruleCurrent);
    }

    public static boolean validate(String name, int population, int year) {
        return validator(name, population, year, RULE.RU);
    }

    public static boolean validate(String name, int population, int year, RULE rule) {
        Objects.requireNonNull(name);
        RULE ruleCurrent = (rule == RULE.DEFAULT) ? RULE.UN : rule;
        return validator(name, population, year, ruleCurrent);
    }

    private static boolean validator(City city, RULE rule) {
        return checkName(city.getName(), rule)
                && checkPopulation(city.getPopulation(), rule)
                && checkYear(city.getYear());
    }

    private static boolean validator(String name, int population, int year, RULE rule) {
        return checkName(name, rule)
                && checkPopulation(population, rule)
                && checkYear(year);
    }

    private static boolean checkName(String name, RULE rule) {
        final String nameRegExp =
                "^[a-zA-Zа-яА-Я]+($|(\\s\\-\\s|\\-)[a-zA-Zа-яА-Я]+)*($|(\\s\\-\\s|\\-)\\d+$)";

        return !name.isEmpty() && name.matches(nameRegExp);
    }

    private static boolean checkPopulation(int population, RULE rule) {
        final int highLimit = 100_000_000;
        int lowLimit = switch (rule) {
            case AU -> 250;
            case UZ -> 7_000;
            case UA, MD, KG, TJ -> 10_000;
            case RU -> 12_000;
            case JP -> 30_000;
            default -> 20_000;
        };

        return population >= lowLimit && population < highLimit;
    }

    private static boolean checkYear(int year) {
        final int lowLimit = -20_000;
        final int highLimit = LocalDate.now().getYear();

        return year > lowLimit && year <= highLimit;
    }

    public static ICityBuilder converter(String name, int population, int year,  ICityBuilder concept) {
        concept
                .setName(name)
                .setPopulation(population)
                .setYear(year);

        return concept;
    }

    public static ICityBuilder converter(String name, String population, String year, ICityBuilder concept) {
        String[] inputData = {name, population, year};
        return converter(inputData, concept);
    }

    public static ICityBuilder converter(String[] inputData, ICityBuilder concept) {
        Objects.requireNonNull(inputData);
        Objects.requireNonNull(inputData[0]);
        Objects.requireNonNull(inputData[1]);
        Objects.requireNonNull(inputData[2]);

        String name = inputData[0];
        int population = Integer.parseInt(inputData[1]);
        int year = Integer.parseInt(inputData[2]);

        return converter(name, population, year, concept);
    }

    public static City cityDevelopment(ICityBuilder concept) throws NotValidCityDataException {
        return cityDevelopment(concept, RULE.RU);
    }

    public static City cityDevelopment(ICityBuilder concept, RULE country) throws NotValidCityDataException {
        City newCity = concept.getCityAfterBuild();
        if (!validate(newCity, country))
            throw new NotValidCityDataException("cityDevelopment: input data for create object type City not valid");
        return newCity;
    }
}
