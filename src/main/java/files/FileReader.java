package files;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Stream;

public final class FileReader {
    /**
     * Утилитный класс для чтения текстовых файлов формата "часть1 | часть2 | часть3".
     * Пример использования:
     * FileReader.readFile(Path.of("data.txt"));
     */

    private FileReader() {}

    private static void isValid(Path path) throws IOException {
        if (!FileUtils.isValidPath(path) || !FileUtils.isValidTxtExtension(path)) {
            throw new IOException("Некорректный путь к файлу или его расширение");
        }
        if (!Files.isReadable(path)) {
            throw new IOException("Нет прав на чтение файла: " + path);
        }
    }

    private static void finalMessage(LineCounter counter) {
        System.out.println();
        System.out.println("Чтение файла завершено!");
        System.out.println(counter.toString());
    }

    private static LineCounter processLines(Stream<String> stream) {
        // Построчное чтение файла, проверка валидности строк, парсинг строк
        LineCounter counter = new LineCounter();
        stream.forEach(line -> {
            counter.incrementLine();
            try {
                if (FileUtils.isValidLineFormat(line)) {
                    Map<String, Object> parsedLine = LineParser.parseLine(line);
                    // тут должен создаваться объект City
                    System.out.println(line);
                    System.out.println(parsedLine.get("name"));
                } else counter.incrementErrorLine();
            } catch (IllegalArgumentException e) {
                counter.incrementErrorLine(); //можно вынести в отдельный метод
                System.err.println("Ошибка парсинга: " + e.getMessage() +
                        " Строка: " + (line.length() > 30 ? line.substring(0, 30) + "..." : line));

            }
        });
        return counter;
    }

    public static void readFile(Path path) throws IOException {
        isValid(path);
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            LineCounter counter = processLines(stream);
            finalMessage(counter);
        }
    }

    public static void main(String[] args) {
        try {
            Path path = Path.of("C:\\AndroidDevelopment\\maven_repo\\com\\google\\code\\gson\\gson\\AstonProject\\src\\main\\java\\files\\testFile.txt");
            readFile(path);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
