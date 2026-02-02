package org.example.files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

/**
 * Утилиты для проверки файлов и строк.
 * Проверяет пути к .txt файлам и формат строк с данными о городах.
 */
public final class FileUtils {

    /** Паттерн для строк формата "часть1 | часть2 | часть3" */
    private static final Pattern LINE_FORMAT_PATTERN =
            Pattern.compile("^\\s*[^|]+\\s*\\|\\s*[^|]+\\s*\\|\\s*[^|]+\\s*$");

    /** Паттерн для имен .txt файлов */
    private static final Pattern FILENAME_PATTERN =
            Pattern.compile("^[^\\\\/:*?\"<>|]+\\.txt$", Pattern.CASE_INSENSITIVE);

    /**
     * Проверяет, что путь ведет к читаемому .txt файлу.
     *
     * @param path путь для проверки
     * @return true если файл существует, является обычным файлом,
     *         имеет расширение .txt и доступен для чтения
     */
    public static boolean isValidPath(Path path) {
        return path != null && Files.exists(path) && Files.isRegularFile(path) && isValidTxtExtension(path);
    }

    /**
     * Проверяет расширение файла.
     *
     * @param path путь к файлу
     * @return true если файл имеет расширение .txt
     */
    public static boolean isValidTxtExtension(Path path) {
        if (path == null || path.getFileName() == null) {
            return false;
        }

        return FILENAME_PATTERN.matcher(path.getFileName().toString().toLowerCase()).matches();
    }

    /**
     * Проверяет формат строки с данными о городе.
     *
     * @param line строка для проверки
     * @return true если строка соответствует формату "часть1 | часть2 | часть3"
     */
    public static boolean isValidLineFormat(String line) {
        if (line == null || line.trim().isEmpty()) {
            return false;
        }

        return LINE_FORMAT_PATTERN.matcher(line).matches();
    }
}
