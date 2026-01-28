package files;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

/**
 * Парсер строк формата "Название | Население | Год".
 * Пример строки:
 * Москва | 12655050 | 1147
 */
public class LineParser {

    public static final String KEY_NAME = "name";
    public static final String KEY_POPULATION = "population";
    public static final String KEY_YEAR = "year";
    private static final int EXPECTED_PARTS_COUNT = 3;

    /**
     * Разбирает строку на три компонента, разделенные '|'.
     *
     * @param line строка в формате "часть1 | часть2 | часть3"
     * @return неизменяемая Map с ключами KEY_NAME, KEY_POPULATION, KEY_YEAR
     * @throws IllegalArgumentException если строка не содержит ровно три части
     * @throws NullPointerException если строка равна null
     */
    public Map<String, String> parseLine(String line) {
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

    @Override
    public boolean equals(Object o) {
        return this == o || (o != null && getClass() == o.getClass());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "LineParser{}";
    }
}
