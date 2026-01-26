package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class FileReader {

    private static boolean checkPath(Path path) {
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

    public static void readFile(Path path) throws IOException {
        // Построчное чтение файла
        if (checkPath(path)) {
            try (Stream<String> stream = Files.lines(path)) {
                AtomicLong lineCounter = new AtomicLong(0);
                stream.forEach(line -> {
                    lineCounter.incrementAndGet();
                    System.out.println(line);
                });
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Path path = Path.of("C:\\AndroidDevelopment\\maven_repo\\com\\google\\code\\gson\\gson\\AstonProject\\src\\main\\java\\files\\testFile.txt");
        readFile(path);
    }
}
