package org.example.files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

import files.*;

/**
 * Основные тесты для класса FileReader
 */
class FileReaderTest {

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Чтение корректного файла с городами")
    void readFile_validFile_returnsCityList() throws Exception {
        // Arrange
        String content = "Москва | 12655050 | 1147\n" +
                "Санкт-Петербург | 5384342 | 1703\n" +
                "Новосибирск | 1620162 | 1893";

        Path testFile = tempDir.resolve("cities.txt");
        Files.writeString(testFile, content);

        FileReader reader = new FileReader();

        // Act
        var result = reader.readFile(testFile, false);

        // Assert
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

        FileReader reader = new FileReader();

        // Act
        var result = reader.readFile(testFile, false);

        // Assert
        // Только Москва и Новосибирск должны быть распарсены
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Несуществующий файл вызывает IOException")
    void readFile_nonexistentFile_throwsException() {
        // Arrange
        Path nonExistent = tempDir.resolve("nonexistent.txt");
        FileReader reader = new FileReader();

        // Act & Assert
        assertThrows(IOException.class, () ->
                reader.readFile(nonExistent, false)
        );
    }

    @Test
    @DisplayName("Файл с неверным расширением вызывает IOException")
    void readFile_wrongExtension_throwsException() throws Exception {
        // Arrange
        Path pdfFile = tempDir.resolve("cities.pdf");
        Files.writeString(pdfFile, "content");

        FileReader reader = new FileReader();

        // Act & Assert
        assertThrows(IOException.class, () ->
                reader.readFile(pdfFile, false)
        );
    }

    @Test
    @DisplayName("Пустой файл возвращает пустой список")
    void readFile_emptyFile_returnsEmptyList() throws Exception {
        // Arrange
        Path testFile = tempDir.resolve("empty.txt");
        Files.writeString(testFile, "");

        FileReader reader = new FileReader();

        // Act
        var result = reader.readFile(testFile, false);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Повторное использование FileReader")
    void readFile_reuseReader_worksCorrectly() throws Exception {
        // Arrange
        String content1 = "Москва | 12655050 | 1147\n" +
                "Санкт-Петербург | 5384342 | 1703";

        String content2 = "Новосибирск | 1620162 | 1893\n" +
                "Екатеринбург | 1493749 | 1723";

        Path file1 = tempDir.resolve("cities1.txt");
        Path file2 = tempDir.resolve("cities2.txt");

        Files.writeString(file1, content1);
        Files.writeString(file2, content2);

        FileReader reader = new FileReader();

        // Act
        var result1 = reader.readFile(file1, false);
        var result2 = reader.readFile(file2, false);

        // Assert
        assertEquals(2, result1.size());
        assertEquals(2, result2.size());
        // Убеждаемся, что состояние сброшено между вызовами
        // (второй результат не содержит данные из первого)
    }

    @Test
    @DisplayName("UTF-8 символы обрабатываются корректно")
    void readFile_utf8Characters() throws Exception {
        // Arrange
        String content = "Москва | 12655050 | 1147\n" +
                "Санкт-Петербург | 5384342 | 1703\n" +
                "Йошкар-Ола | 281248 | 1584";

        Path testFile = tempDir.resolve("cities.txt");
        Files.write(testFile, content.getBytes("UTF-8"));

        FileReader reader = new FileReader();

        // Act
        var result = reader.readFile(testFile, false);

        // Assert
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Файл с одной корректной строкой")
    void readFile_singleValidLine() throws Exception {
        // Arrange
        Path testFile = tempDir.resolve("single.txt");
        Files.writeString(testFile, "Москва | 12655050 | 1147");

        FileReader reader = new FileReader();

        // Act
        var result = reader.readFile(testFile, false);

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Equals и hashCode работают корректно")
    void equalsHashCode_worksCorrectly() {
        FileReader reader1 = new FileReader();
        FileReader reader2 = new FileReader();

        assertEquals(reader1, reader2);
        assertEquals(reader1.hashCode(), reader2.hashCode());
        assertTrue(reader1.toString().contains("FileReader"));
    }

    @Test
    @DisplayName("Null путь вызывает IOException")
    void readFile_nullPath_throwsException() {
        FileReader reader = new FileReader();

        // Косвенная проверка через isValidPath
        assertThrows(IOException.class, () ->
                reader.readFile(null, false)
        );
    }
}
