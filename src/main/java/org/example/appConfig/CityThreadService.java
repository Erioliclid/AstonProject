package org.example.appConfig;

import org.example.City;
import org.example.CityArrayList.CityArrayList;
import org.example.Multithread.CountElements;
import org.example.build.CityConcept;
import org.example.build.CityDirector;
import org.example.build.ICityBuilder;
import org.example.exception.NotValidCityDataException;

import java.util.Scanner;

public class CityThreadService {
    private final AppState appState;
    private final Scanner scanner;

    public CityThreadService(AppState appState, Scanner scanner) {
        this.appState = appState;
        this.scanner = scanner;
    }

    public void countElement() {
        System.out.println("Многопоточный подсчет");
        CityArrayList<City> cities = appState.getCurrentCities();
        if (cities.isEmpty()) {
            System.out.println("Не загружены данные");
            return;
        }
        System.out.println("Введите данные через ' , . ; ': Название, численность население, год ");
        String input = scanner.nextLine().trim();

        try {
            City cityToFind = createCity(input);
            System.out.println("Запускаю многопоточный поиск");
            CountElements.count(cities, cityToFind);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private City createCity(String input) {
        String[] parts = input.split(",");
        if (parts.length < 3) {
            parts = input.split("[;.]");
        }
        if (parts.length < 3) {
            throw new IllegalArgumentException("Нужно 3 значения");
        }
        String name = parts[0].trim();
        String population = parts[1].trim();
        String year = parts[2].trim();

        ICityBuilder concept = new CityConcept();
        try {
            CityDirector.converter(name, population, year, concept);
            return CityDirector.cityDevelopment(concept);
        } catch (NotValidCityDataException e) {
            City city = new City();
            city.setName(name);

            try {
                city.setPopulation(Integer.parseInt(population));
            } catch (NumberFormatException ex) {
                city.setPopulation(0);
            }
            try {
                city.setYear(Integer.parseInt(year));
            } catch (NumberFormatException ex) {
                city.setYear(0);
            }
            return city;
        }
    }
}
