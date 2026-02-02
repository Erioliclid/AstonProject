package org.example.appConfig;

import org.example.City;
import org.example.CityArrayList.CityArrayList;
import org.example.tty.HelpMessage;
import org.example.tty.Input;
import org.example.files.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class FileReadService {
    private final AppState appState;
    private final Scanner scanner;
    private final FileReader fileReader;

    public FileReadService(AppState appState, Scanner scanner) {
        this.appState = appState;
        this.scanner = scanner;

        LineParser parser = new LineParser();
        LineCounter counter = new LineCounter();
        ErrorLogger logger = new ErrorLogger();
        this.fileReader = new FileReader(parser, counter, logger);
    }

    public void fileRead() {
        CityArrayList<City> citiesList = appState.getCurrentCities();
        CityArrayList<City> fileCitiesList;
        Path path = Input.fileName();
        boolean repeated = false;

        do {
            if (repeated) {
                path = Input.fileName();
                repeated = false;
            }

            try {
                fileCitiesList = fileReader.readFile(path, true);
                System.out.println("Успешно прочитано из файла городов: " + fileCitiesList.size());
                fileCitiesList.stream().forEach(citiesList::add);
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("""
                        Повторить запрос на чтение из файла?
                        1. Да
                        0. Вернуться в предыдущее меню
                        """);
                HelpMessage.invitation();
                String continueChoice = scanner.nextLine().trim();
                if (continueChoice.equals("1")) {
                    repeated = true;
                }
            }
        } while (repeated);
    }
}
