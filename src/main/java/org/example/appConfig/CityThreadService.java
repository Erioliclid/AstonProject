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
        int quantity = cities.size();
        if (cities.isEmpty()) {
            System.out.println("Не загружены данные");
            return;
        } else {
            System.out.println("Доступно для подсчета " + quantity + " городов.");
            System.out.println("Можете выбрать город по его индексу (от 0 до " + (quantity - 1) + ") или ");
        }
        System.out.println("Введите данные через ' , . ; ': Название, численность население, год ");
        String input = scanner.nextLine().trim();

        String[] parts = input.split("[,.;|]");
        City cityToFind = null;
        int cityIndex;

        if (parts.length == 1 && parts[0].trim().matches("-?\\d+")) {
            cityIndex = Integer.parseInt(parts[0]);
            if (cityIndex < 0 || cityIndex >= cities.size()) {
                System.out.println("Введен неверный индекс города для многопоточного подсчета");
            } else {
                cityToFind = cities.get(cityIndex);
                System.out.println("Выбран город " + cityToFind.getName()
                        + " с численностью " + cityToFind.getPopulation()
                        + ", " + cityToFind.getYear() + " год основания");
            }
        } else if (parts.length != 3) {
            System.out.println("Нужно 3 значения");
            return;
        } else {
            ICityBuilder concept = new CityConcept();
            try {
                CityDirector.converter(parts[0].trim(), parts[1].trim(), parts[2].trim(), concept);
                cityToFind = CityDirector.cityDevelopment(concept);
            } catch (NotValidCityDataException e) {
                System.out.println("Ошибка: " + e.getMessage());
                return;
            }
        }
        if (cityToFind != null) {
            System.out.println("Запускаю многопоточный поиск");
            CountElements.count(cities, cityToFind);
        }
    }
}