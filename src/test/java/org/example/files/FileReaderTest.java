package org.example.files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

import files.*;

/**
 * Основные тесты для класса FileReader с JUnit 6
 */
class FileReaderTest {

    @TempDir
    Path tempDir;

    private LineParser parser;
    private LineCounter counter;
    private ErrorLogger logger;
    private FileReader fileReader;

    @BeforeEach
    void setUp() {
        parser = new LineParser();
        counter = new LineCounter();
        logger = new ErrorLogger();
        fileReader = new FileReader(parser, counter, logger);
    }

    @Test
    @DisplayName("Чтение корректного файла с городами")
    void readFile_validFile_returnsCityList() throws Exception {
        // Arrange
        String content = "Москва | 12655050 | 1147\n" +
                "Санкт-Петербург | 5384342 | 1703\n" +
                "Новосибирск | 1620162 | 1893";

        Path testFile = tempDir.resolve("cities.txt");
        Files.writeString(testFile, content);

        // Act
        var result = fileReader.readFile(testFile, false);

        // Assert - проверяем только результат, а не состояние счетчика
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Файл с некорректными строками пропускает ошибки")
    void readFile_invalidLines_skipsThem() throws Exception {
        // Arrange
        String content = "Москва | 12655050 | 1147\n" +
                "Санкт-Петербург | 5384342\n" + // мало частей
                "Новосибирск | 1620162 | 1893\n" +
                "| 1493749 | 1723\n" + // пустое название
                "Казань | | 1005"; // пустое население

        Path testFile = tempDir.resolve("cities.txt");
        Files.writeString(testFile, content);

        // Act
        var result = fileReader.readFile(testFile, false);

        // Assert - только Москва и Новосибирск должны быть распарсены
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Несуществующий файл вызывает IOException")
    void readFile_nonexistentFile_throwsException() {
        // Arrange
        Path nonExistent = tempDir.resolve("nonexistent.txt");

        // Act & Assert
        assertThrows(IOException.class, () ->
                fileReader.readFile(nonExistent, false)
        );
    }

    @Test
    @DisplayName("Файл с неверным расширением вызывает IOException")
    void readFile_wrongExtension_throwsException() throws Exception {
        // Arrange
        Path pdfFile = tempDir.resolve("cities.pdf");
        Files.writeString(pdfFile, "content");

        // Act & Assert
        assertThrows(IOException.class, () ->
                fileReader.readFile(pdfFile, false)
        );
    }

    @Test
    @DisplayName("Пустой файл возвращает пустой список")
    void readFile_emptyFile_returnsEmptyList() throws Exception {
        // Arrange
        Path testFile = tempDir.resolve("empty.txt");
        Files.writeString(testFile, "");

        // Act
        var result = fileReader.readFile(testFile, false);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Повторное использование FileReader")
    void readFile_reuseReader_worksCorrectly() throws Exception {
        // Arrange
        String content1 = "Москва | 12655050 | 1147";
        String content2 = "Санкт-Петербург | 5384342 | 1703";

        Path file1 = tempDir.resolve("cities1.txt");
        Path file2 = tempDir.resolve("cities2.txt");

        Files.writeString(file1, content1);
        Files.writeString(file2, content2);

        // Act
        var result1 = fileReader.readFile(file1, false);
        var result2 = fileReader.readFile(file2, false);

        // Assert
        assertEquals(1, result1.size());
        assertEquals(1, result2.size());
    }

    @Test
    @DisplayName("UTF-8 символы обрабатываются корректно")
    void readFile_utf8Characters() throws Exception {
        // Arrange
        String content = "Йошкар-Ола | 281248 | 1584\n" +
                "Санкт-Петербург | 5384342 | 1703";

        Path testFile = tempDir.resolve("cities.txt");
        Files.write(testFile, content.getBytes("UTF-8"));

        // Act
        var result = fileReader.readFile(testFile, false);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Файл с одной корректной строкой")
    void readFile_singleValidLine() throws Exception {
        // Arrange
        Path testFile = tempDir.resolve("single.txt");
        Files.writeString(testFile, "Москва | 12655050 | 1147");

        // Act
        var result = fileReader.readFile(testFile, false);

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Equals и hashCode работают корректно")
    void equalsHashCode_worksCorrectly() {
        // Arrange
        LineParser parser1 = new LineParser();
        LineCounter counter1 = new LineCounter();
        ErrorLogger logger1 = new ErrorLogger();

        FileReader reader1 = new FileReader(parser1, counter1, logger1);
        FileReader reader2 = new FileReader(parser1, counter1, logger1);

        // Act & Assert
        assertEquals(reader1, reader2);
        assertEquals(reader1.hashCode(), reader2.hashCode());
        assertTrue(reader1.toString().contains("FileReader"));
    }

    @Test
    @DisplayName("Null путь вызывает IOException")
    void readFile_nullPath_throwsException() {
        // Act & Assert
        assertThrows(IOException.class, () ->
                fileReader.readFile(null, false)
        );
    }

    @Test
    @DisplayName("Строки с пробелами и табуляциями")
    void readFile_withWhitespaces() throws Exception {
        // Arrange
        String content = "  Москва   |   12655050   |   1147  \n" +
                "\tСанкт-Петербург\t|\t5384342\t|\t1703\t";

        Path testFile = tempDir.resolve("cities.txt");
        Files.writeString(testFile, content);

        // Act
        var result = fileReader.readFile(testFile, false);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Файл с пустыми строками")
    void readFile_withEmptyLines() throws Exception {
        // Arrange
        String content = "\n" +
                "Москва | 12655050 | 1147\n" +
                "\n" +
                "\n" +
                "Санкт-Петербург | 5384342 | 1703\n" +
                "\n";

        Path testFile = tempDir.resolve("cities.txt");
        Files.writeString(testFile, content);

        // Act
        var result = fileReader.readFile(testFile, false);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Ошибка создания объекта города - отрицательное население")
    void readFile_cityCreationError_negativePopulation() throws Exception {
        // Arrange
        String content = "Москва | -1000 | 1147\n" + // отрицательное население
                "Санкт-Петербург | 5384342 | 1703";

        Path testFile = tempDir.resolve("cities.txt");
        Files.writeString(testFile, content);

        // Act
        var result = fileReader.readFile(testFile, false);

        // Assert
        assertEquals(1, result.size()); // только Санкт-Петербург
    }

    @Test
    @DisplayName("Ошибка создания объекта города - невалидный год")
    void readFile_cityCreationError_invalidYear() throws Exception {
        // Arrange
        String content = "Москва | 12655050 | не_число\n" + // невалидный год
                "Санкт-Петербург | 5384342 | 1703";

        Path testFile = tempDir.resolve("cities.txt");
        Files.writeString(testFile, content);

        // Act
        var result = fileReader.readFile(testFile, false);

        // Assert
        assertEquals(1, result.size()); // только Санкт-Петербург
    }

    @Test
    @DisplayName("printResults = true не вызывает исключений")
    void readFile_withPrintResults() throws Exception {
        // Arrange
        String content = "Москва | 12655050 | 1147";
        Path testFile = tempDir.resolve("cities.txt");
        Files.writeString(testFile, content);

        // Act & Assert - не должно быть исключений
        assertDoesNotThrow(() -> {
            var result = fileReader.readFile(testFile, true);
            assertEquals(1, result.size());
        });
    }

    @Test
    @DisplayName("FileReader с null параметрами в конструкторе")
    void constructor_withNullParameters_throwsException() {
        // Act & Assert
        assertThrows(NullPointerException.class, () ->
                new FileReader(null, new LineCounter(), new ErrorLogger())
        );

        assertThrows(NullPointerException.class, () ->
                new FileReader(new LineParser(), null, new ErrorLogger())
        );

        assertThrows(NullPointerException.class, () ->
                new FileReader(new LineParser(), new LineCounter(), null)
        );
    }

    @Test
    @DisplayName("Содержимое toString")
    void toString_containsExpectedInfo() {
        // Arrange
        FileReader reader = new FileReader(parser, counter, logger);

        // Act
        String result = reader.toString();

        // Assert
        assertTrue(result.contains("FileReader"));
        assertTrue(result.contains("parser="));
        assertTrue(result.contains("currentState="));
    }

    @Test
    @DisplayName("Файл только с некорректными строками возвращает пустой список")
    void readFile_allInvalidLines_returnsEmptyList() throws Exception {
        // Arrange
        String content = "Санкт-Петербург | 5384342\n" + // мало частей
                "| 1493749 | 1723\n" + // пустое название
                "Казань | | 1005"; // пустое население

        Path testFile = tempDir.resolve("invalid.txt");
        Files.writeString(testFile, content);

        // Act
        var result = fileReader.readFile(testFile, false);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Файл со строками, где есть только разделители")
    void readFile_onlyDelimiters_returnsEmptyList() throws Exception {
        // Arrange
        String content = "| |\n" +
                " || \n" +
                "  |  |  ";

        Path testFile = tempDir.resolve("delimiters.txt");
        Files.writeString(testFile, content);

        // Act
        var result = fileReader.readFile(testFile, false);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}