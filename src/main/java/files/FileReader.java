package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileReader {
    private static boolean isValid(Path path) throws IOException {
        if (!FileUtils.isValidPath(path) || !FileUtils.isValidTxtExtension(path)) {
            throw new IOException("Некорректный путь к файлу или его расширение");
        }
        return true;
    }

    private static void processLine(String line) {}

    private static void finalMessage(LineCounter counter) {
        System.out.println();
        System.out.println("Чтение файла завершено!");
        System.out.println(counter.toString());
    }

    public static void readFile(Path path) throws IOException {
        // Построчное чтение файла
        if (FileUtils.isValidPath(path) && FileUtils.isValidTxtExtension(path)) {
            try (Stream<String> stream = Files.lines(path)) {
                LineCounter counter = new LineCounter();
                stream.forEach(line -> {
                    counter.incrementLine();
                    if (FileUtils.isValidLineFormat(line)) {
                        System.out.println(line);
                    }
                    else counter.incrementErrorLine();
                });
                finalMessage(counter);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Path path = Path.of("C:\\AndroidDevelopment\\maven_repo\\com\\google\\code\\gson\\gson\\AstonProject\\src\\main\\java\\files\\testFile.txt");
        readFile(path);
    }
}
