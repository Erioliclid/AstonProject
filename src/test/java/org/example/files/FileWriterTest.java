package org.example.files;

import org.example.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

import org.example.CityArrayList.CityArrayList;
import org.example.exception.NotValidCityDataException;
import org.example.build.*;

/**
 * Основные тесты для класса FileWriter
 */
class FileWriterTest {

    @TempDir
    Path tempDir;

    private FileWriter fileWriter;

    @BeforeEach
    void setUp() {
        ErrorLogger logger = new ErrorLogger();
        fileWriter = new FileWriter(logger);
    }

    @Test
    @DisplayName("Запись в новый файл")
    void write_newFile_createsAndWrites() throws Exception {
        // Arrange
        Path testFile = tempDir.resolve("cities.txt");
        CityArrayList<City> cities = createTestCities(
                "Москва", 12655050, 1147,
                "Санкт-Петербург", 5384342, 1703
        );

        // Act
        fileWriter.write(testFile, cities, false);

        // Assert
        assertTrue(Files.exists(testFile));
        String content = Files.readString(testFile);
        assertTrue(content.contains("Москва | 12655050 | 1147"));
        assertTrue(content.contains("Санкт-Петербург | 5384342 | 1703"));
    }

    @Test
    @DisplayName("Добавление данных в существующий файл")
    void write_existingFile_appendsData() throws Exception {
        // Arrange
        Path testFile = tempDir.resolve("cities.txt");

        // Первая запись
        CityArrayList<City> cities1 = createTestCities("Москва", 12655050, 1147);
        fileWriter.write(testFile, cities1, false);

        // Вторая запись
        CityArrayList<City> cities2 = createTestCities("Санкт-Петербург", 5384342, 1703);

        // Act
        fileWriter.write(testFile, cities2, false);

        // Assert
        String content = Files.readString(testFile);
        assertTrue(content.contains("------Добавление данных-------"));
        assertTrue(content.contains("Москва | 12655050 | 1147"));
        assertTrue(content.contains("Санкт-Петербург | 5384342 | 1703"));
    }

    @Test
    @DisplayName("Неверное расширение файла вызывает исключение")
    void write_wrongExtension_throwsException() {
        // Arrange
        Path testFile = tempDir.resolve("cities.pdf");
        CityArrayList<City> cities = createTestCities("Москва", 12655050, 1147);

        // Act & Assert
        assertThrows(IOException.class, () ->
                fileWriter.write(testFile, cities, false)
        );
    }

    @Test
    @DisplayName("Пустой список городов")
    void write_emptyList_createsFile() throws Exception {
        // Arrange
        Path testFile = tempDir.resolve("cities.txt");
        CityArrayList<City> cities = new CityArrayList<>();

        // Act
        fileWriter.write(testFile, cities, false);

        // Assert
        assertTrue(Files.exists(testFile));
        assertTrue(Files.readString(testFile).isEmpty());
    }

    @Test
    @DisplayName("Создание директорий если их нет")
    void write_nestedDirectory_createsDirectories() throws Exception {
        // Arrange
        Path testFile = tempDir.resolve("data/cities/test.txt");
        CityArrayList<City> cities = createTestCities("Москва", 12655050, 1147);

        // Act
        fileWriter.write(testFile, cities, false);

        // Assert
        assertTrue(Files.exists(testFile));
        assertTrue(Files.isDirectory(testFile.getParent()));
    }

    @Test
    @DisplayName("Конструктор с null логгером бросает исключение")
    void constructor_nullLogger_throwsException() {
        // Act & Assert - просто проверяем что исключение бросается
        assertThrows(NullPointerException.class, () ->
                new FileWriter(null)
        );
    }

    @Test
    @DisplayName("UTF-8 символы записываются корректно")
    void write_utf8Characters_writesCorrectly() throws Exception {
        // Arrange
        Path testFile = tempDir.resolve("cities.txt");
        CityArrayList<City> cities = createTestCities("Йошкар-Ола", 281248, 1584);

        // Act
        fileWriter.write(testFile, cities, false);

        // Assert
        String content = Files.readString(testFile);
        assertTrue(content.contains("Йошкар-Ола | 281248 | 1584"));
    }

    // Вспомогательный метод для создания тестовых городов
    private CityArrayList<City> createTestCities(Object... data) {
        CityArrayList<City> cities = new CityArrayList<>();

        for (int i = 0; i < data.length; i += 3) {
            String name = (String) data[i];
            int population = (int) data[i + 1];
            int year = (int) data[i + 2];

            // Создаем город через builder
            try {
                ICityBuilder concept = new CityConcept();
                CityDirector.converter(name, population, year, concept);
                cities.add(CityDirector.cityDevelopment(concept));
            } catch (NotValidCityDataException e) {
                throw new RuntimeException(e);
            }
        }

        return cities;
    }
}
