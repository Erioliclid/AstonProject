package files;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
        // Построчное чтение файла
        LineCounter counter = new LineCounter();
        stream.forEach(line -> {
            counter.incrementLine();
            if (FileUtils.isValidLineFormat(line)) {
                System.out.println(line);
            }
            else counter.incrementErrorLine();
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
}
