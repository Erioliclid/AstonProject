package files;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public final class LineParser {
    /**
     * Класс для парсинга строки формата "Название | Население | Год" в Map.
     */

    private static final String KEY_NAME = "name";
    private static final String KEY_POPULATION = "population";
    private static final String KEY_YEAR = "year";
    private static final int EXPECTED_PARTS_COUNT = 3;

    private LineParser() {}

    private static String separateName(String[] splitLine) {
        return splitLine[0].trim();
    }

    private static int separatePopulation(String[] splitLine) {
        try {
            return Integer.parseInt(splitLine[1].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                    String.format("Неверный формат населения: '%s'. Ожидается целое число.", splitLine[1].trim())
            );
        }
    }

    private static int separateYear(String[] splitLine) {
        try {
            return Integer.parseInt(splitLine[2].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                    String.format("Неверный формат года основания: '%s'. Ожидается целое число.", splitLine[2].trim())
            );
        }
    }

    public static Map<String, Object> parseLine(String line) {
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

        Map<String, Object> parsedLineMap = new HashMap<>();
        parsedLineMap.put(KEY_NAME, separateName(splitLine));
        parsedLineMap.put(KEY_POPULATION, separatePopulation(splitLine));
        parsedLineMap.put(KEY_YEAR, separateYear(splitLine));

        return Map.copyOf(parsedLineMap);
    }
}
