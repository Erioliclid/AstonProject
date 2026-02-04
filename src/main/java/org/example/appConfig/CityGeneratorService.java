package org.example.appConfig;

import org.example.City;
import org.example.CityArrayList.CityArrayList;
import org.example.CityArrayList.CityArrayListCollector;
import org.example.exception.NotValidCityDataException;
import org.example.gen.GenCity;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CityGeneratorService {
    private final AppState appState;
    private final Scanner scanner;

    public CityGeneratorService(AppState appState, Scanner scanner) {
        this.appState = appState;
        this.scanner = scanner;
    }

    public void generateRandomCities() {
        System.out.println("Рандомная генерация городов");
        System.out.println("Сколько городов сгенирировать? Введите число: ");
        try {
            int count = Integer.parseInt(scanner.nextLine().trim());

            if (count <= 0) {
                System.out.println("Используйте число больше 0");
                return;
            }
            if (count > 1000) {
                System.out.println("Слишком большое число, введен максимум городов - 1000");
                count = 1000;
            }
            System.out.println("Генерирую города с использованием стрима");
            Stream<City> cityStream = IntStream.range(0, count)
                    .mapToObj(i -> {
                        try {
                            return GenCity.get();
                        } catch (NotValidCityDataException e) {
                            return null;
                        }
                    })
                    .filter(city -> city != null);
            CityArrayList<City> newCities = cityStream
                    .collect(CityArrayListCollector.toCityArrayList());

            CityArrayList<City> currentCities = appState.getCurrentCities();
            for (City city : newCities) {
                currentCities.add(city);
            }
            appState.setCurrentCities(currentCities);
            System.out.println("Генерация завершена. Сгенерировано " + newCities.size() + " городов");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка. Введите другое число");
        }
    }
}