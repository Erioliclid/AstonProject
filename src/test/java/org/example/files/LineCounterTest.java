package org.example.files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import files.LineCounter;

/**
 * Тесты для класса LineCounter
 */
class LineCounterTest {

    private LineCounter counter;

    @BeforeEach
    void setUp() {
        counter = new LineCounter();
    }

    @Test
    @DisplayName("Новый счетчик имеет нулевые значения")
    void newCounter_hasZeroCounts() {
        assertEquals(0, counter.getTotalLines());
        assertEquals(0, counter.getErrorLines());
        assertEquals(0, counter.getValidLines());
    }

    @Test
    @DisplayName("incrementLine увеличивает общий счетчик")
    void incrementLine_increasesTotal() {
        counter.incrementLine();
        assertEquals(1, counter.getTotalLines());
        assertEquals(0, counter.getErrorLines());
        assertEquals(1, counter.getValidLines());

        counter.incrementLine();
        assertEquals(2, counter.getTotalLines());
        assertEquals(0, counter.getErrorLines());
        assertEquals(2, counter.getValidLines());
    }

    @Test
    @DisplayName("incrementErrorLine увеличивает счетчик ошибок")
    void incrementErrorLine_increasesErrorCount() {
        counter.incrementErrorLine();
        assertEquals(0, counter.getTotalLines());
        assertEquals(1, counter.getErrorLines());
        assertEquals(-1, counter.getValidLines()); // 0 - 1 = -1
    }

    @Test
    @DisplayName("Комбинация incrementLine и incrementErrorLine")
    void mixedIncrements_calculatesCorrectly() {
        counter.incrementLine(); // строка 1 - валидная
        counter.incrementLine(); // строка 2 - валидная
        counter.incrementErrorLine(); // строка 3 - ошибка
        counter.incrementLine(); // строка 4 - валидная

        assertEquals(3, counter.getTotalLines());
        assertEquals(1, counter.getErrorLines());
        assertEquals(2, counter.getValidLines()); // 3 - 1 = 2
    }

    @Test
    @DisplayName("reset обнуляет счетчики")
    void reset_setsCountersToZero() {
        counter.incrementLine();
        counter.incrementLine();
        counter.incrementErrorLine();

        assertEquals(2, counter.getTotalLines());
        assertEquals(1, counter.getErrorLines());

        counter.reset();

        assertEquals(0, counter.getTotalLines());
        assertEquals(0, counter.getErrorLines());
        assertEquals(0, counter.getValidLines());
    }

    @Test
    @DisplayName("toString возвращает правильный формат")
    void toString_returnsCorrectFormat() {
        counter.incrementLine();
        counter.incrementLine();
        counter.incrementErrorLine();
        counter.incrementLine();

        String result = counter.toString();

        assertTrue(result.contains("Всего строк: 3"));
        assertTrue(result.contains("Валидных строк: 2"));
        assertTrue(result.contains("Ошибок формата: 1"));
    }

    @Test
    @DisplayName("Только ошибки (без incrementLine)")
    void onlyErrors_withoutIncrementLine() {
        counter.incrementErrorLine();
        counter.incrementErrorLine();

        assertEquals(0, counter.getTotalLines());
        assertEquals(2, counter.getErrorLines());
        assertEquals(-2, counter.getValidLines()); // 0 - 2 = -2
    }

    @Test
    @DisplayName("Только валидные строки")
    void onlyValidLines() {
        counter.incrementLine();
        counter.incrementLine();
        counter.incrementLine();

        assertEquals(3, counter.getTotalLines());
        assertEquals(0, counter.getErrorLines());
        assertEquals(3, counter.getValidLines());
    }

    @Test
    @DisplayName("Большое количество операций")
    void largeNumberOfOperations() {
        for (int i = 0; i < 1000; i++) {
            counter.incrementLine();
            if (i % 10 == 0) {
                counter.incrementErrorLine();
            }
        }

        assertEquals(1000, counter.getTotalLines());
        assertEquals(100, counter.getErrorLines()); // 1000 / 10 = 100
        assertEquals(900, counter.getValidLines());
    }

    @Test
    @DisplayName("reset после использования")
    void reset_afterUsage_worksCorrectly() {
        counter.incrementLine();
        counter.incrementErrorLine();
        counter.reset();

        // После reset можно снова использовать
        counter.incrementLine();
        counter.incrementLine();

        assertEquals(2, counter.getTotalLines());
        assertEquals(0, counter.getErrorLines());
        assertEquals(2, counter.getValidLines());
    }
}
