package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileReader {
    private static final Pattern FORMAT_PATTERN =
            Pattern.compile("^\\s*[^|]+\\s*\\|\\s*[^|]+\\s*\\|\\s*[^|]+\\s*$");

    private static boolean isValidPath(Path path) {
        // Если по указанному пути действительно что-то существует и это является файлом
        // с расширением .txt вернет true, иначе false
        if (Files.exists(path)
                && Files.isRegularFile(path)
                && path.getFileName().toString().toLowerCase().matches("[\\w\\-]+\\.txt$")) {

            return true;
        }
        else {
            System.out.println("Путь к файлу некорректен или файл не имеет расширения .txt!");
            return false;
        }
    }

    private static boolean isValidLineFormat(String line) {
        // Проверка строки, что она не пустая и соответствует паттерну ("Название города | население | год основания")
        if (line == null || line.trim().isEmpty()) {
            return false;
        }

        return FORMAT_PATTERN.matcher(line).matches();
    }

    public static void readFile(Path path) throws IOException {
        // Построчное чтение файла
        if (isValidPath(path)) {
            try (Stream<String> stream = Files.lines(path)) {
                AtomicLong lineCounter = new AtomicLong(0);
                AtomicLong errorLineCounter = new AtomicLong(0);
                stream.forEach(line -> {
                    lineCounter.incrementAndGet();
                    if (isValidLineFormat(line)) {
                        System.out.println(line);
                    }
                    else errorLineCounter.incrementAndGet();
                });
                System.out.println();
                System.out.println("Всего строк прочитано: " + lineCounter);
                System.out.println("Выявлено строк, не подходящих по формату: " + errorLineCounter);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Path path = Path.of("C:\\AndroidDevelopment\\maven_repo\\com\\google\\code\\gson\\gson\\AstonProject\\src\\main\\java\\files\\testFile.txt");
        readFile(path);
    }
}
