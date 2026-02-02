package org.example.menu;

import org.example.City;
import org.example.CityArrayList.CityArrayList;
import org.example.appConfig.AppState;
import org.example.appConfig.CityGeneratorService;
import org.example.appConfig.CityInputService;

import java.util.Scanner;

import static org.example.menu.MenuPrint.*;

public class Menu {
    private final Scanner scanner;
    private final AppState appState;
    private final CityInputService cityInputService;
    private final CityGeneratorService cityGeneratorService;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.appState = new AppState();
        this.cityInputService = new CityInputService(appState, scanner);
        this.cityGeneratorService = new CityGeneratorService(appState, scanner);
    }

    public void mainMenu() {
        clear();
        System.out.println(MAIN_MENU_PRINT);
        if (appState.isCityLoaded()) {
            CityArrayList<City> cities = appState.getCurrentCities();
            System.out.println("Загружено городов: " + cities.size());
        }
        System.out.println("Выберите нужный пункт: ");
    }

    public boolean mainMenuChoice() {
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                dataInputMenu();
                break;
            case "2":
                if (!appState.isCityLoaded()) {
                    System.out.println("Для использования загрузите данные");
                    waitingForEnter();
                } else {
                    sortingMenu();
                }
                break;
            case "3":
                if (!appState.isCityLoaded()) {
                    System.out.println("Для использования загрузите данные");
                    waitingForEnter();
                } else {
                    saveMenu();
                }
                break;
            case "4":
                if (!appState.isCityLoaded()) {
                    System.out.println("Для использования загрузите данные");
                    waitingForEnter();
                } else {
                    threadCounting();
                }
                break;
            case "5":
                if (!appState.isCityLoaded()) {
                    System.out.println("Нет загруженных городов");
                    waitingForEnter();
                } else {
                    showAllCities();
                    waitingForEnter();
                }
                break;
            case "0":
                System.out.println("Выход из приложения");
                return false;
            default:
                System.out.println("Такого значения нет");
                waitingForEnter();
        }
        return true;
    }

    private void dataInputMenu() {
        clear();
        System.out.println(DATA_INPUT_PRINT);

        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                System.out.println("Выбран ввод вручную");
                //Вызов метода ввода
                cityInputService.inputLoop();
                break;
            case "2":
                System.out.println("Генерирую случайные данные");
                //Вызов метода рандома
                cityGeneratorService.generateRandomCities();
                break;
            case "3":
                System.out.println("Загрузка из файла..");
                //Вызов загрузки
                break;
            case "0":
                return;
            default:
                System.out.println("Такого значения нет");
        }
        waitingForEnter();
    }

    private void sortingMenu() {
        clear();
        System.out.println(SORT_MENU_PRINT);
        if (appState.isCityLoaded()) {
            CityArrayList<City> cities = appState.getCurrentCities();
            System.out.println("Количество городов: " + cities.size() + " дя сортировки");
        }
        System.out.println("Выберите нужный пункт: ");
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                System.out.println("Сортирую по названию..");
                //Впихнуть сортировку
                break;
            case "2":
                System.out.println("Сортирую по году основания..");
                //Впихнуть сортировку
                break;
            case "3":
                System.out.println("Сортирую по населению..");
                //Впихнуть сортировку
                break;
            case "4":
                System.out.println("Сортирую по чет/нечет..");
                //Впихнуть сортировку
            case "0":
                return;
            default:
                System.out.println("Такого значения нет");
        }
        System.out.println("\nСортировка завершена");
        waitingForEnter();
    }

    private void saveMenu() {
        clear();
        System.out.println(SAVE_MENU_PRINT);
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                System.out.println("Сохранение файла..");
                //Впихнуть метод сохранения
                break;
            case "2":
                System.out.println("Добавление данных в файл..");
                //Впихнуть метод добавления
                break;
            case "0":
                return;
            default:
                System.out.println("Такого значения нет");
        }
        waitingForEnter();
    }

    private void threadCounting() {
        System.out.println("Многопоточный подсчет");
        // ??
    }

    private void showAllCities() {
        clear();
        CityArrayList<City> cities = appState.getCurrentCities();
        System.out.println("Список городов");
        if (cities.isEmpty()) {
            System.out.println("Список пустой");
            waitingForEnter();
            return;
        }
        for (int i = 0; i < cities.size(); i++) {
            City city = cities.get(i);
            System.out.println(city.toString());
        }
    }


    private void waitingForEnter() {
        System.out.println("Нажмите Enter для продолжения");
        scanner.nextLine();
    }

    private void clear() {
        final String escConsoleClear = "\u001B[2J";

        System.out.println(escConsoleClear);
    }
}