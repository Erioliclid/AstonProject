package files;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;


/**
 * Утилитный класс для парсинга строк формата "часть1 | часть2 | часть3".
 * <p>
 * Пример использования:
 * <pre>
 * Map<String, String> parsed = LineParser.parseLine("Москва | 12655050 | 1147");
 * String cityName = parsed.get(LineParser.KEY_NAME);  // "Москва"
 * </pre>
 *
 * @see #parseLine(String)
 * @see #KEY_NAME
 * @see #KEY_POPULATION
 * @see #KEY_YEAR
 */
public final class LineParser {

    public static final String KEY_NAME = "name";
    public static final String KEY_POPULATION = "population";
    public static final String KEY_YEAR = "year";
    private static final int EXPECTED_PARTS_COUNT = 3;

    private LineParser() {}

    public static Map<String, String> parseLine(String line) {
        Objects.requireNonNull(line, "Строка не может быть null");
        String[] splitLine = line.split("\\|", -1);

        if (splitLine.length != EXPECTED_PARTS_COUNT) {
            throw new IllegalArgumentException(
                    String.format("Строка должна содержать %d части, разделенные '|'. Получено: %d. Строка: '%s'",
                            EXPECTED_PARTS_COUNT,
                            splitLine.length,
                            line
                    )
            );
        }

        Map<String, String> parsedLineMap = new HashMap<>();

        parsedLineMap.put(KEY_NAME, splitLine[0].trim());
        parsedLineMap.put(KEY_POPULATION, splitLine[1].trim());
        parsedLineMap.put(KEY_YEAR, splitLine[2].trim());

        return Map.copyOf(parsedLineMap);
    }
}
