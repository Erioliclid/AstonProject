package org.example.appConfig;

import org.example.City;
import org.example.CityArrayList.CityArrayList;
import org.example.Sorting.*;

import java.util.Scanner;

public class CitySortingService {
    private final AppState appState;
    private final Scanner scanner;
    private final MergeSort mergeSort;
    private final MergeSortAdditional mergeSortAdditional;

    public CitySortingService(AppState appState, Scanner scanner) {
        this.appState = appState;
        this.scanner = scanner;
        this.mergeSort = new MergeSort();
        this.mergeSortAdditional = new MergeSortAdditional();
    }

    public void sortByName() {
        CityArrayList<City> cities = appState.getCurrentCities();
        while (true) {
            System.out.println("""
                    Сортировка по названию
                    Варианты сортировки:
                    1. от "А" до "Я"
                    2. от "Я" до "А"
                    0. Вернуться в меню
                    Выберите нужный пункт
                    """);
            String choice = scanner.nextLine().trim();
            ComparatorStrategy comparatorStrategy;

            switch (choice) {
                case "0" -> {
                    System.out.println("Сортировка отменена");
                    return;
                }
                case "1" -> comparatorStrategy = new NameComparator();
                case "2" -> comparatorStrategy = new NameComparatorReversed();
                default -> {
                    System.out.println("Такого варианта нет, повторите ввод");
                    continue;
                }
            }

            CityArrayList<City> sortedCities = mergeSort.sort(cities, comparatorStrategy);
            appState.setCurrentCities(sortedCities);
            System.out.println("Сортировка завершена");
        }
    }

    public void sortByYear() {
        CityArrayList<City> cities = appState.getCurrentCities();
        while (true) {
            System.out.println("""
                    Сортировка по году
                    Варианты сортировки:
                    1. от старых к новым
                    2. от новых к старым
                    0. Вернуться в меню
                    Выберите нужный пункт
                    """);
            String choice = scanner.nextLine().trim();
            ComparatorStrategy comparatorStrategy;
            switch (choice) {
                case "0" -> {
                    System.out.println("Сортировка отменена");
                    return;
                }
                case "1" -> comparatorStrategy = new YearComparator();
                case "2" -> comparatorStrategy = new YearComparatorReversed();
                default -> {
                    System.out.println("Такого варианта нет, повторите ввод");
                    continue;
                }
            }

            CityArrayList<City> sortedCities = mergeSort.sort(cities, comparatorStrategy);
            appState.setCurrentCities(sortedCities);
            System.out.println("Сортировка завершена");

        }
    }

    public void sortByPopulation() {
        CityArrayList<City> cities = appState.getCurrentCities();
        while (true) {
            System.out.println("""
                    Сортировка по населению
                    Варианты сортировки:
                    1. от меньшего к большему
                    2. от большего к меньшему
                    0. Вернуться в меню
                    Выберите нужный пункт
                    """);


            String choice = scanner.nextLine().trim();
            ComparatorStrategy comparatorStrategy;
            switch (choice) {
                case "0" -> {
                    System.out.println("Сортировка отменена");
                    return;
                }
                case "1" -> comparatorStrategy = new PopulationComparator();
                case "2" -> comparatorStrategy = new PopulationComparatorReversed();
                default -> {
                    System.out.println("Такого варианта нет, повторите ввод");
                    continue;
                }
            }

            CityArrayList<City> sortedCities = mergeSort.sort(cities, comparatorStrategy);
            appState.setCurrentCities(sortedCities);
            System.out.println("Сортировка завершена");
        }
    }

    public void sortByEven() {
        CityArrayList<City> cities = appState.getCurrentCities();
        while (true) {
            System.out.println("""
                    Сортировка по четности населения
                    Варианты сортировки:
                    1. Сначала четные по названию от "А" до "Я"
                    2. Сначала четные по названию от "Я" до "А"
                    0. Вернуться в меню
                    Выберите нужный пункт
                    """);

            String choice = scanner.nextLine().trim();
            ComparatorStrategy comparatorStrategy;
            switch (choice) {
                case "0" -> {
                    System.out.println("Сортировка отменена");
                    return;
                }
                case "1" -> comparatorStrategy = new NameComparator();
                case "2" -> comparatorStrategy = new NameComparatorReversed();
                default -> {
                    System.out.println("Такого варианта нет, повторите ввод");
                    continue;
                }
            }

            CityArrayList<City> sortedCities = mergeSortAdditional.sortEvenValues(cities, comparatorStrategy);
            appState.setCurrentCities(sortedCities);
            System.out.println("Сортировка завершена");
        }
    }
}
