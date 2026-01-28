package files;

import org.example.City;
import org.example.CityArrayList;
import org.example.ICityBuilder;
import org.example.CityConcept;
import org.example.CityDirector;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Читает файлы с данными о городах в формате "Название | Население | Год".
 * Пример файла:
 * Москва | 12655050 | 1147
 * Санкт-Петербург | 5384342 | 1703
 */
public class FileReader {
    private final LineParser parser;
    private final LineCounter counter;
    private final CityArrayList<City> list;

    public FileReader() {
        this.parser = new LineParser();
        this.counter = new LineCounter();
        this.list = new CityArrayList<>();
    }

    /** Выводит статистику обработки в консоль */
    private void printResultMessage() {
        System.out.println();
        System.out.println("Чтение файла завершено!");
        System.out.println(this.counter);
        System.out.println("Объектов создано: " + this.list.size());
    }

    /** Создает объект класса City из распарсенных данных */
    private City createCityObjFromParsedLine(Map<String, String> parsedLine) {
        ICityBuilder concept = new CityConcept();
        CityDirector.converter(parsedLine.get(LineParser.KEY_NAME),
                parsedLine.get(LineParser.KEY_POPULATION),
                parsedLine.get(LineParser.KEY_YEAR),
                concept);

        return CityDirector.cityDevelopment(concept);
    }

    /** Логирует ошибку */
    private void logError(Exception e, String errorType, String line) {
        String message = errorType + e.getMessage();
        if (line != null) {
            String truncated = line.length() > 30 ? line.substring(0, 30) + "..." : line;
            message += " Строка: " + truncated;
        }
        System.err.println(message);
    }

    /** Обрабатывает поток строк, заполняя this.list */
    private void processLines(Stream<String> stream, LineCounter counter) {
        stream.forEach(line -> {
            counter.incrementLine();
            try {
                if (FileUtils.isValidLineFormat(line)) {
                    Map<String, String> parsedLine = this.parser.parseLine(line);
                    City city = createCityObjFromParsedLine(parsedLine);
                    this.list.add(city);
                } else counter.incrementErrorLine();
            } catch (IllegalArgumentException e) {
                counter.incrementErrorLine();
                logError(e, "Ошибка парсинга: ", line);
            } catch (RuntimeException e) {
                counter.incrementErrorLine();
                logError(e, "Ошибка создания объекта: ", line);
            }
        });
    }

    /** Копирует список (поверхностное копирование) */
    private CityArrayList<City> copyList(CityArrayList<City> source) {
        CityArrayList<City> copy = new CityArrayList<>();
        for (int i = 0; i < source.size(); i++) {
            copy.add(source.get(i));
        }
        return copy;
    }

    /**
     * Читает файл и возвращает список городов.
     *
     * @param filePath путь к файлу .txt
     * @param printResults выводить ли статистику в консоль
     * @return список объектов City
     * @throws IOException если файл не найден, недоступен или имеет неверный формат
     */
    public CityArrayList<City> readFile(Path filePath, boolean printResults) throws IOException {
        if (!FileUtils.isValidPath(filePath)) {
            throw new IOException("Некорректный путь к файлу: " + filePath);
        }
        try (Stream<String> stream = Files.lines(filePath, StandardCharsets.UTF_8)) {
            processLines(stream, this.counter);
            if (printResults) {
                printResultMessage();
            }

            // Копируем результат и очищаем this.list и this.counter
            CityArrayList<City> resultList = copyList(this.list);
            this.list.clear(); // возможна утечка памяти
            this.counter.reset();
            return resultList;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileReader that = (FileReader) o;
        return Objects.equals(parser, that.parser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parser);
    }

    @Override
    public String toString() {
        return String.format("FileReader{parser=%s, currentState=%s}",
                parser.getClass().getSimpleName(),
                counter);
    }
}
