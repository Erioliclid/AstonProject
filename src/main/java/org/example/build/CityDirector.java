package org.example.build;

import org.example.City;
import org.example.exception.NotValidCityDataException;
import org.example.country.Rule;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Утилитарный класс CityDirector предназначен для: валидации корректности данных для формирования объекта City;
 * конвертирования из различных представлений полей объекта City в объект-концепт CityConcept; создание, "выпуск",
 * объекта City из объекта-концепта CityConcept.
 */
public class CityDirector {

    /**
     * Проверяет соответствие корректности существующий объект City. При проверке применяются правила Rule.RU,
     * характерные для России. Список допустимых правил для определенных стран находится в country.Rule.
     *
     * @param city объект City
     * @return true - проверка прошла успешно, false - проверка прошла неудачно
     */
    public static boolean validate(City city) {
        return validate(city, Rule.RU);
    }

    /**
     * Проверяет соответствие корректности существующий объект City. Требуется указать правило какой страны применить.
     * Список допустимых правил для определенных стран находится в country.Rule.
     *
     * @param city объект City
     * @param rule элемент перечисления Rule
     * @return true - проверка прошла успешно, false - проверка прошла неудачно
     */
    public static boolean validate(City city, Rule rule) {
        Objects.requireNonNull(city);
        Rule ruleCurrent = (rule == Rule.DEFAULT) ? Rule.UN : rule;

        return validator(city, ruleCurrent);
    }

    /**
     * Проверяет соответствие корректности для возможности создания объекта City.
     * При проверке применяются правила Rule.RU, характерные для России. Список
     * допустимых правил для определенных стран находится в country.Rule.
     *
     * @param name название города
     * @param population количество населения
     * @param year год основания
     * @return true - проверка прошла успешно, false - проверка прошла неудачно
     */
    public static boolean validate(String name, int population, int year) {
        return validator(name, population, year, Rule.RU);
    }

    /**
     * Проверяет соответствие корректности для возможности создания объекта City. Требуется указать правило какой
     * страны применить. Список допустимых правил для определенных стран находится в country.Rule.
     *
     * @param name название города
     * @param population количество населения
     * @param year год основания
     * @param rule элемент перечисления Rule
     * @return true - проверка прошла успешно, false - проверка прошла неудачно
     */
    public static boolean validate(String name, int population, int year, Rule rule) {
        Objects.requireNonNull(name);
        Rule ruleCurrent = (rule == Rule.DEFAULT) ? Rule.UN : rule;
        return validator(name, population, year, ruleCurrent);
    }

    /**
     * Вспомогательный метод для проверки корректности введенных данных, используется методами validate(City city) и validate(City city,
     * Rule rule).
     *
     * @param city объект City
     * @param rule элемент перечисления Rule
     * @return true - проверка прошла успешно, false - проверка прошла неудачно
     */
    private static boolean validator(City city, Rule rule) {
        return checkName(city.getName())
                && checkPopulation(city.getPopulation(), rule)
                && checkYear(city.getYear());
    }

    /**
     * Вспомогательный метод для проверки корректности введенных данных, используется метода validate(String name, int population, int year) и
     * validate(String name, int population, int year, Rule rule).
     *
     * @param name название города
     * @param population численность населения города
     * @param year год основания города
     * @param rule элемент перечисления Rule
     * @return true - проверка прошла успешно, false - проверка прошла неудачно
     */
    private static boolean validator(String name, int population, int year, Rule rule) {
        return checkName(name)
                && checkPopulation(population, rule)
                && checkYear(year);
    }

    /**
     * Проверяет переданную строку на соответствие определенному шаблону, который определяет определенную структуру и
     * набор символов для названия города.
     * @param name название города
     * @return true - проверка прошла успешно, false - проверка прошла неудачно
     */
    private static boolean checkName(String name) {
        final String nameRegExp =
                "^[a-zA-Zа-яёА-ЯЁ]+($|(\\s-\\s|-|\\s)[a-zA-Zа-яА-Я]+)*($|(\\s-\\s|-)\\d+$)";

        return !name.isEmpty() && name.matches(nameRegExp);
    }

    /**
     * Проверяет численность населения для получения статуса город. Нижний предел (минимальное условие) зависит от
     * применяемого правила, можно охарактеризовать как "в какой стране находится город". Стандартным правилом является
     * Rule.RU (Россия). Верхний предел ограничен значением 100_000_000.
     *
     * @param population численность населения
     * @param rule элемент перечисления Rule
     * @return true - проверка прошла успешно, false - проверка прошла неудачно
     */
    private static boolean checkPopulation(int population, Rule rule) {
        final int highLimit = 100_000_000;
        int lowLimit = rule.getMinPopulationForCityStatus();

        return population >= lowLimit && population < highLimit;
    }

    /**
     * Проверяет на корректность год основания города. Год до н.э. обозначается отрицательным значение числа, РХ - 0,
     * года в н.э. обозначается положительным числом. Нижний предел задания является -20_000, верхним пределом является
     * значение текущего года.
     *
     * @param year год основания города
     * @return true - проверка прошла успешно, false - проверка прошла неудачно
     */
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
