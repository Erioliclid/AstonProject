package org.example.files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import files.LineParser;

/**
 * Основные тесты для класса LineParser
 */
class LineParserTest {

    private final LineParser parser = new LineParser();

    @Test
    @DisplayName("Корректная строка парсится успешно")
    void parseLine_validLine_returnsCorrectMap() {
        String line = "Москва | 12655050 | 1147";
        Map<String, String> result = parser.parseLine(line);

        assertEquals("Москва", result.get(LineParser.KEY_NAME));
        assertEquals("12655050", result.get(LineParser.KEY_POPULATION));
        assertEquals("1147", result.get(LineParser.KEY_YEAR));
    }

    @Test
    @DisplayName("Пробелы удаляются")
    void parseLine_withSpaces_trimsValues() {
        String line = "  Москва   |   12655050   |   1147   ";
        Map<String, String> result = parser.parseLine(line);

        assertEquals("Москва", result.get(LineParser.KEY_NAME));
        assertEquals("12655050", result.get(LineParser.KEY_POPULATION));
        assertEquals("1147", result.get(LineParser.KEY_YEAR));
    }

    @Test
    @DisplayName("Неверное количество частей вызывает исключение")
    void parseLine_invalidPartCount_throwsException() {
        // Тест 1: слишком мало частей
        assertThrows(IllegalArgumentException.class, () ->
                parser.parseLine("Москва | 12655050")
        );

        // Тест 2: слишком много частей
        assertThrows(IllegalArgumentException.class, () ->
                parser.parseLine("Москва | 12655050 | 1147 | лишнее")
        );
    }

    @Test
    @DisplayName("Пустое название города вызывает исключение")
    void parseLine_emptyCityName_throwsException() {
        // Пустое название после тримминга
        assertThrows(IllegalArgumentException.class, () ->
                parser.parseLine("| 12655050 | 1147")
        );

        // Пробелы вместо названия
        assertThrows(IllegalArgumentException.class, () ->
                parser.parseLine("   | 12655050 | 1147")
        );
    }

    @Test
    @DisplayName("Null строка вызывает исключение")
    void parseLine_nullString_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parseLine(null));
    }

    @Test
    @DisplayName("Результат неизменяем")
    void parseLine_returnsImmutableMap() {
        String line = "Москва | 12655050 | 1147";
        Map<String, String> result = parser.parseLine(line);

        assertThrows(UnsupportedOperationException.class, () -> {
            result.put("test", "value");
        });
    }

    @Test
    @DisplayName("Пустое население или год обрабатываются")
    void parseLine_emptyPopulationOrYear_parsesEmptyStrings() {
        // Название есть, остальное пустое
        String line = "Москва | |";
        Map<String, String> result = parser.parseLine(line);

        assertEquals("Москва", result.get(LineParser.KEY_NAME));
        assertEquals("", result.get(LineParser.KEY_POPULATION));
        assertEquals("", result.get(LineParser.KEY_YEAR));

        // Название и население есть, год пустой
        String line2 = "Санкт-Петербург | 5384342 |";
        Map<String, String> result2 = parser.parseLine(line2);

        assertEquals("Санкт-Петербург", result2.get(LineParser.KEY_NAME));
        assertEquals("5384342", result2.get(LineParser.KEY_POPULATION));
        assertEquals("", result2.get(LineParser.KEY_YEAR));
    }

    @Test
    @DisplayName("Equals и hashCode работают корректно")
    void equalsHashCode_worksCorrectly() {
        LineParser parser1 = new LineParser();
        LineParser parser2 = new LineParser();

        assertEquals(parser1, parser2);
        assertEquals(parser1.hashCode(), parser2.hashCode());
        assertEquals("LineParser{}", parser1.toString());
    }

    @Test
    @DisplayName("UTF-8 символы обрабатываются")
    void parseLine_utf8Characters_parsesCorrectly() {
        String line = "Йошкар-Ола | 281248 | 1584";
        Map<String, String> result = parser.parseLine(line);

        assertEquals("Йошкар-Ола", result.get(LineParser.KEY_NAME));
        assertEquals("281248", result.get(LineParser.KEY_POPULATION));
        assertEquals("1584", result.get(LineParser.KEY_YEAR));
    }
}