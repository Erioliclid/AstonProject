package org.example.appConfig;

import org.example.City;
import org.example.exception.NotValidCityDataException;
import org.example.tty.Input;

import java.util.*;

public class CityInputService {
    private final AppState appState;
    private final Scanner scanner;

    public CityInputService(AppState appState, Scanner scanner) {
        this.appState = appState;
        this.scanner = scanner;
    }

    public void inputLoop() {
        List<City> citiesList = new ArrayList<>();
        if (appState.isCityLoaded()) {
            citiesList.addAll(Arrays.asList(appState.getCurrentCities()));
        }
        boolean isFirstCity = true;
        boolean continueInput = true;

        while (continueInput) {
            System.out.println("Ручной ввод городов");
            System.out.println("Текущее количество городов: " + citiesList.size());
            try {
                Optional<City> city;
                if (isFirstCity) {
                    city = Input.startOrder();
                    isFirstCity = false;
                } else {
                    city = Input.continueOrder();
                }
                if (city.isPresent()) {
                    citiesList.add(city.get());
                    System.out.println("Город '" + city.get().getName() + "' добавлен");
                    System.out.println("""
                            Добавить еще один город?
                            1. Добавить город
                            0. Вернуться в меню
                            Выберите пункт:
                            """);
                    String continueChoice = scanner.nextLine().trim();
                    if (!continueChoice.equals("1")) {
                        continueInput = false;
                    }
                }
            } catch (NotValidCityDataException e) {
                System.out.println("Ошибка ввода: " + e.getMessage());
                System.out.println("""
                        Желаете повторить ввод?
                        1. Да
                        0. Обратно в меню
                        Выберите пункт:
                        """);

                String retryChoice = scanner.nextLine().trim();
                if (!retryChoice.equals("1")) {
                    continueInput = false;
                }
            }
        }
        if (!citiesList.isEmpty()) {
            City[] citiesArray = citiesList.toArray(new City[0]);
            appState.setCurrentCities(citiesArray);
            System.out.println("Загружено городов: " + citiesArray.length);
        } else {
            System.out.println("Список городов пуст");
        }
    }
}