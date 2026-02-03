package org.example.appConfig;

import org.example.City;
import org.example.CityArrayList.CityArrayList;
import org.example.files.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CityFileService {
    private final AppState appState;
    private final Scanner scanner;
    private final FileReader fileReader;
    private final FileWriter fileWriter;

    public CityFileService(AppState appState, Scanner scanner) {
        this.appState = appState;
        this.scanner = scanner;

        LineParser parser = new LineParser();
        LineCounter counter = new LineCounter();
        ErrorLogger logger = new ErrorLogger();
        this.fileReader = new FileReader(parser, counter, logger);
        this.fileWriter = new FileWriter(logger);

    }

    public void loadFile() {
        System.out.println("Загурзка файла");
        System.out.println("Укажите путь и/или имя файла");
        String filePath = scanner.nextLine().trim();
        if (filePath.isEmpty()) {
            System.out.println("Укажите путь");
            return;
        }
        Path path = Paths.get(filePath);
        try {
            CityArrayList<City> loadedCities = fileReader.readFile(path, true);
            if (appState.isCityLoaded()) {
                System.out.println("Перезаписываю существующие города");
                System.out.println("Загружено: " + loadedCities.size() + " городов");
                appState.setCurrentCities(loadedCities);
            } else {
                appState.setCurrentCities(loadedCities);
                System.out.println("Загружено: " + loadedCities.size() + " городов");
            }
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public void saveToFile() {
        System.out.println("Введите путь сохарнения и/или имя файла");
        String filePath = scanner.nextLine().trim();

        if (filePath.isEmpty()) {
            System.out.println("Укажите путь");
            return;
        }
        CityArrayList<City> cities = appState.getCurrentCities();
        if (cities.isEmpty()) {
            System.out.println("Нет данных для сохранения");
            return;
        }
        Path path = Paths.get(filePath);
        try {
            fileWriter.write(path, cities, true);
            System.out.println("Данные сохранены");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
