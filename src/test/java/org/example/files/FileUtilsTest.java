package org.example.files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

import files.FileUtils;

/**
 * Тесты для класса FileUtils
 */
class FileUtilsTest {

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("isValidTxtExtension - корректные .txt файлы")
    void isValidTxtExtension_validFiles_returnsTrue() {
        assertTrue(FileUtils.isValidTxtExtension(Path.of("file.txt")));
        assertTrue(FileUtils.isValidTxtExtension(Path.of("FILE.TXT")));
        assertTrue(FileUtils.isValidTxtExtension(Path.of("test_file.txt")));
        assertTrue(FileUtils.isValidTxtExtension(Path.of("123.txt")));
        assertTrue(FileUtils.isValidTxtExtension(Path.of("cities_2024.txt")));
    }

    @Test
    @DisplayName("isValidTxtExtension - некорректные расширения")
    void isValidTxtExtension_invalidExtensions_returnsFalse() {
        assertFalse(FileUtils.isValidTxtExtension(Path.of("file.pdf")));
        assertFalse(FileUtils.isValidTxtExtension(Path.of("file.txt.bak")));
        assertFalse(FileUtils.isValidTxtExtension(Path.of("file")));
        assertFalse(FileUtils.isValidTxtExtension(Path.of(".txt")));
        assertFalse(FileUtils.isValidTxtExtension(Path.of("file.tx")));
        assertFalse(FileUtils.isValidTxtExtension(Path.of("file.doc")));
    }

    @Test
    @DisplayName("isValidTxtExtension - null и пустые пути")
    void isValidTxtExtension_nullOrEmpty_returnsFalse() {
        assertFalse(FileUtils.isValidTxtExtension(null));
        assertFalse(FileUtils.isValidTxtExtension(Path.of("")));
    }

    @Test
    @DisplayName("isValidLineFormat - корректные строки")
    void isValidLineFormat_validLines_returnsTrue() {
        assertTrue(FileUtils.isValidLineFormat("Москва | 12655050 | 1147"));
        assertTrue(FileUtils.isValidLineFormat("  Москва   |   12655050   |   1147   "));
        assertTrue(FileUtils.isValidLineFormat("Санкт-Петербург | 5384342 | 1703"));
        assertTrue(FileUtils.isValidLineFormat("A | 100 | 2000"));
        assertTrue(FileUtils.isValidLineFormat("Город с пробелами | 123456 | 1800"));
    }

    @Test
    @DisplayName("isValidLineFormat - некорректные строки")
    void isValidLineFormat_invalidLines_returnsFalse() {
        assertFalse(FileUtils.isValidLineFormat(null));
        assertFalse(FileUtils.isValidLineFormat(""));
        assertFalse(FileUtils.isValidLineFormat("   "));
        assertFalse(FileUtils.isValidLineFormat("Москва | 12655050")); // мало частей
        assertFalse(FileUtils.isValidLineFormat("Москва | 12655050 | 1147 | лишнее")); // много частей
        assertFalse(FileUtils.isValidLineFormat("||")); // мало частей (1 часть)
    }

    @Test
    @DisplayName("isValidPath - существующий .txt файл")
    void isValidPath_existingTxtFile_returnsTrue() throws Exception {
        Path testFile = tempDir.resolve("test.txt");
        Files.writeString(testFile, "content");
        assertTrue(FileUtils.isValidPath(testFile));
    }

    @Test
    @DisplayName("isValidPath - несуществующий файл")
    void isValidPath_nonexistentFile_returnsFalse() {
        Path nonExistent = tempDir.resolve("nonexistent.txt");
        assertFalse(FileUtils.isValidPath(nonExistent));
    }

    @Test
    @DisplayName("isValidPath - null путь")
    void isValidPath_nullPath_returnsFalse() {
        assertFalse(FileUtils.isValidPath(null));
    }

    @Test
    @DisplayName("isValidPath - директория вместо файла")
    void isValidPath_directory_returnsFalse() {
        assertFalse(FileUtils.isValidPath(tempDir));
    }

    @Test
    @DisplayName("isValidPath - файл не .txt")
    void isValidPath_nonTxtFile_returnsFalse() throws Exception {
        Path pdfFile = tempDir.resolve("test.pdf");
        Files.writeString(pdfFile, "content");
        assertFalse(FileUtils.isValidPath(pdfFile));
    }

    @Test
    @DisplayName("isValidPath - файл без прав на чтение")
    void isValidPath_unreadableFile_returnsFalse() throws Exception {
        Path testFile = tempDir.resolve("test.txt");
        Files.writeString(testFile, "content");

        // Пытаемся сделать файл недоступным для чтения (работает не на всех системах)
        if (testFile.toFile().setReadable(false)) {
            assertFalse(FileUtils.isValidPath(testFile));
            // Восстанавливаем права для очистки
            testFile.toFile().setReadable(true);
        }
    }

    @Test
    @DisplayName("isValidLineFormat - крайние случаи")
    void isValidLineFormat_edgeCases() {
        // Пустая строка между разделителями
        assertTrue(FileUtils.isValidLineFormat("Москва | | 1147"));
        assertTrue(FileUtils.isValidLineFormat(" | 12655050 | "));

        // Очень длинные строки
        String longLine = "Очень длинное название города " +
                "| 999999999999999 | " +
                "1000000000000000000";
        assertTrue(FileUtils.isValidLineFormat(longLine));

        // Специальные символы
        assertTrue(FileUtils.isValidLineFormat("City & Town | 1000 | 2000"));
        assertTrue(FileUtils.isValidLineFormat("Cité | 2000 | 1800"));
    }
}
