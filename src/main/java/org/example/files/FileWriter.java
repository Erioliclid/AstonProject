package org.example.files;

import org.example.City;
import org.example.CityArrayList.CityArrayList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.concurrent.atomic.LongAdder;

/**
 * Записывает данные о городах в текстовые файлы.
 * Добавляет новые данные в конец файла, сохраняя старые.
 */
public class FileWriter {
    private static final String DELIMITER = "\n------Добавление данных-------\n\n";

    private final ErrorLogger logger;

    /**
     * Создает FileWriter с указанным логгером ошибок.
     *
     * @param logger логгер для записи ошибок (не может быть null)
     */
    public FileWriter(ErrorLogger logger) {
        this.logger = Objects.requireNonNull(logger);
    }

    /** Проверяет расширение .txt и создает файл если его нет */
    private void checkAndCreateFile(Path filePath) throws IOException {
        if (!FileUtils.isValidTxtExtension(filePath)) {
            throw new IOException("Файл должен иметь расширение .txt: " + filePath);
        }
        if (filePath.getParent() != null) {
            Files.createDirectories(filePath.getParent());
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }

    /** Выводит статистику записи данных */
    private void printResultMessage(LongAdder count, long listSize) {
        System.out.println("Всего строк записано: " + count);
        long errorCount = listSize - count.sum();
        if (errorCount > 0) {
            System.out.println("Предупреждение: " + errorCount + " строк не записано из-за ошибок");
        }
    }

    /**
     * Записывает список городов в файл.
     * Если файла нет - создает его.
     * Если файл есть - добавляет данные в конец.
     *
     * @param filePath путь к файлу .txt
     * @param cityList список городов для записи
     * @param printResults выводить ли статистику в консоль
     * @throws IOException если путь некорректен или произошла ошибка записи
     */
    public void write(Path filePath, CityArrayList<City> cityList, boolean printResults) throws IOException {
        LongAdder count = new LongAdder();
        StandardOpenOption[] options = new StandardOpenOption[] {
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND,
                StandardOpenOption.WRITE
        };

        checkAndCreateFile(filePath);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, options)) {
            if (Files.size(filePath) > 0) {
                writer.write(DELIMITER);
            }

            cityList.stream()
                    .map(CitySerializer::serialize)
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            count.increment();
                        } catch (IOException e) {
                            logger.logError(e, "Ошибка записи строки в файл: ", line);
                        }
                    });
            if (printResults) {
                printResultMessage(count, cityList.size());
            }
        }
    }
}
