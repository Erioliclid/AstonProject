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

    private static void finalMessage(LineCounter counter, int arrayLen) {
        System.out.println();
        System.out.println("Чтение файла завершено!");
        System.out.println(counter.toString());
        System.out.println("Объектов создано: " + arrayLen);
    }

    private static City createCityObjFromParsedLine(Map<String, String> parsedLine) {
        ICityBuilder concept = new CityConcept();

        CityDirector.converter(parsedLine.get(LineParser.KEY_NAME),
                parsedLine.get(LineParser.KEY_POPULATION),
                parsedLine.get(LineParser.KEY_YEAR),
                concept);

        return CityDirector.cityDevelopment(concept);
    }

    private static void errorHandle(Exception e, String line, String errorType) {
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
                    Map<String, String> parsedLine = LineParser.parseLine(line);
                    City city = createCityObjFromParsedLine(parsedLine);
                    cityList.add(city);
                } else counter.incrementErrorLine();
            } catch (IllegalArgumentException e) {
                counter.incrementErrorLine();
                errorHandle(e, line, "Ошибка парсинга: ");
            } catch (RuntimeException e) {
                counter.incrementErrorLine();
                errorHandle(e, line, "Ошибка создания объекта: ");
            }
        });
        return cityList;
    }

    public static CityArrayList<City> readFile(Path path) throws IOException {
        FileUtils.isValid(path);
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            LineCounter counter = new LineCounter();
            CityArrayList<City> cityList = processLines(stream, counter);
            finalMessage(counter, cityList.size());
            return cityList;
        }
    }

    public static void main(String[] args) throws IOException {
        Path path = Path.of("C:\\AndroidDevelopment\\maven_repo\\com\\google\\code\\gson\\gson\\AstonProject\\src\\main\\java\\files\\testFile.txt");
        readFile(path);
    }
}
