package files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public final class FileUtils {
    /**
     * Утилитный класс для работы с файлами
     */

    private static final Pattern LINE_FORMAT_PATTERN =
            Pattern.compile("^\\s*[^|]+\\s*\\|\\s*[^|]+\\s*\\|\\s*[^|]+\\s*$");
    private static final Pattern FILENAME_PATTERN =
            Pattern.compile("^[^\\\\/:*?\"<>|]+\\.txt$", Pattern.CASE_INSENSITIVE);

    public static boolean isValidPath(Path path) {
        // true если по указанному пути действительно что-то существует и это является файлом, иначе false
        return Files.exists(path) && Files.isRegularFile(path);
    }

    public static boolean isValidTxtExtension(Path path) {
        // true если у файла расширение .txt, иначе false
        if (path == null || path.getFileName() == null) {
            return false;
        }

        return FILENAME_PATTERN.matcher(path.getFileName().toString().toLowerCase()).matches();
    }

    public static boolean isValidLineFormat(String line) {
        // Проверка строки, что она не пустая и соответствует паттерну ("Название города | население | год основания")
        if (line == null || line.trim().isEmpty()) {
            return false;
        }

        return LINE_FORMAT_PATTERN.matcher(line).matches();
    }
}
