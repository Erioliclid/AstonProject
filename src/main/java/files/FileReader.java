package files;

import org.example.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Stream;

public final class FileReader {
    /**
     * Утилитный класс для чтения текстовых файлов формата "часть1 | часть2 | часть3".
     * Пример использования:
     * FileReader.readFile(Path.of("data.txt"));
     */

    private FileReader() {}

    private static void isValid(Path path) throws IOException {
        if (!FileUtils.isValidPath(path) || !FileUtils.isValidTxtExtension(path)) {
            throw new IOException("Некорректный путь к файлу или его расширение");
        }
        if (!Files.isReadable(path)) {
            throw new IOException("Нет прав на чтение файла: " + path);
        }
    }

    private static void finalMessage(LineCounter counter, int arrayLen) {
        System.out.println();
        System.out.println("Чтение файла завершено!");
        System.out.println(counter.toString());
        System.out.println("Объектов создано: " + arrayLen);
    }

    private static City createCityObjFromParsedLine(Map<String, Object> parsedLine) {
        ICityBuilder concept = new CityConcept();

        String name = (String) parsedLine.get(LineParser.KEY_NAME);
        int population = (int) parsedLine.get(LineParser.KEY_POPULATION);
        int year = (int) parsedLine.get(LineParser.KEY_YEAR);

        CityDirector.converter(name, population, year, concept);

        return CityDirector.cityDevelopment(concept);
    }

    private static void errorHandle(LineCounter counter, Exception e, String line, String errorType) {
        counter.incrementErrorLine();
        System.err.println(errorType + e.getMessage() +
                " Строка: " + (line.length() > 30 ? line.substring(0, 30) + "..." : line));
    }

    private static CityArrayList<City> processLines(Stream<String> stream, LineCounter counter) {
        // Построчное чтение файла, проверка валидности строк, парсинг строк
        CityArrayList<City> cityList = new CityArrayList<>();
        stream.forEach(line -> {
            counter.incrementLine();
            try {
                if (FileUtils.isValidLineFormat(line)) {
                    Map<String, Object> parsedLine = LineParser.parseLine(line);
                    City city = createCityObjFromParsedLine(parsedLine);
                    cityList.add(city);
                } else counter.incrementErrorLine();
            } catch (IllegalArgumentException e) {
                errorHandle(counter, e, line, "Ошибка парсинга: ");
            } catch (RuntimeException e) {
                errorHandle(counter, e, line, "Ошибка создания объекта: ");
            }
        });
        return cityList;
    }

    public static CityArrayList<City> readFile(Path path) throws IOException {
        isValid(path);
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            LineCounter counter = new LineCounter();
            CityArrayList<City> cityList = processLines(stream, counter);
            finalMessage(counter, cityList.size());
            return cityList;
        }
    }
}
