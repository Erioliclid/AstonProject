package org.example.files;

import org.example.*;
import org.example.CityArrayList.CityArrayList;
import org.example.exception.NotValidCityDataException;
import org.example.build.*;

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
    private final ErrorLogger logger;

    public FileReader(LineParser parser, LineCounter counter, ErrorLogger logger) {
        this.parser = Objects.requireNonNull(parser);
        this.counter = Objects.requireNonNull(counter);
        this.logger = Objects.requireNonNull(logger);
    }

    /** Выводит статистику обработки в консоль */
    private void printResultMessage(long listSize) {
        System.out.println();
        System.out.println("Чтение файла завершено!");
        System.out.println(this.counter);
        System.out.println("Объектов создано: " + listSize);
    }

    /** Создает объект класса City из распарсенных данных */
    private City createCityObjFromParsedLine(Map<String, String> parsedLine) throws NotValidCityDataException {
        ICityBuilder concept = new CityConcept();
        CityDirector.converter(parsedLine.get(LineParser.KEY_NAME),
                parsedLine.get(LineParser.KEY_POPULATION),
                parsedLine.get(LineParser.KEY_YEAR),
                concept);

        return CityDirector.cityDevelopment(concept);
    }

    /** Обрабатывает поток строк, заполняя this.list */
    private CityArrayList<City> processLines(Stream<String> stream, LineCounter counter) {
        return stream
                .peek(line -> counter.incrementLine())
                .filter(FileUtils::isValidLineFormat)
                .peek(line -> counter.incrementValidLine())
                .map(line -> {
                    try {
                        Map<String, String> parsedLine = this.parser.parseLine(line);
                        return createCityObjFromParsedLine(parsedLine);
                    } catch (NotValidCityDataException e) {
                        counter.incrementErrorLine();
                        this.logger.logError(e, "Ошибка создания объекта: ", line);
                        return null;
                    } catch (IllegalArgumentException e) {
                        counter.incrementErrorLine();
                        this.logger.logError(e, "Ошибка парсинга: ", line);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(CityArrayListCollector.toCityArrayList());
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
            CityArrayList<City> createdCityList = processLines(stream, this.counter);
            if (printResults) {
                printResultMessage(createdCityList.size());
            }
            return createdCityList;
        }
        finally {
            this.counter.reset();
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
