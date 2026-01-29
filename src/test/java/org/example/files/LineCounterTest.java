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
        assertEquals(0, counter.getValidLines()); // Важно: valid не увеличивается автоматически

        counter.incrementLine();
        assertEquals(2, counter.getTotalLines());
        assertEquals(0, counter.getErrorLines());
        assertEquals(0, counter.getValidLines());
    }

    @Test
    @DisplayName("incrementValidLine увеличивает счетчик валидных строк")
    void incrementValidLine_increasesValidCount() {
        counter.incrementValidLine();
        assertEquals(0, counter.getTotalLines());
        assertEquals(0, counter.getErrorLines());
        assertEquals(1, counter.getValidLines());
    }

    @Test
    @DisplayName("incrementErrorLine увеличивает счетчик ошибок")
    void incrementErrorLine_increasesErrorCount() {
        counter.incrementErrorLine();
        assertEquals(0, counter.getTotalLines());
        assertEquals(1, counter.getErrorLines());
        assertEquals(0, counter.getValidLines());
    }

    @Test
    @DisplayName("Комбинация всех инкрементов")
    void mixedIncrements_calculatesCorrectly() {
        counter.incrementLine();      // total: 1, valid: 0, error: 0
        counter.incrementValidLine(); // total: 1, valid: 1, error: 0
        counter.incrementLine();      // total: 2, valid: 1, error: 0
        counter.incrementErrorLine(); // total: 2, valid: 1, error: 1

        assertEquals(2, counter.getTotalLines());
        assertEquals(1, counter.getErrorLines());
        assertEquals(1, counter.getValidLines());
    }

    @Test
    @DisplayName("reset обнуляет все счетчики")
    void reset_setsCountersToZero() {
        counter.incrementLine();
        counter.incrementValidLine();
        counter.incrementErrorLine();

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
        counter.incrementValidLine();
        counter.incrementErrorLine();

        String result = counter.toString();

        assertTrue(result.contains("Всего строк: 2"));
        assertTrue(result.contains("Валидных строк: 1"));
        assertTrue(result.contains("Ошибок парсинга или создания объекта: 1"));
    }

    @Test
    @DisplayName("Только валидные строки")
    void onlyValidLines() {
        counter.incrementValidLine();
        counter.incrementValidLine();
        counter.incrementValidLine();

        assertEquals(0, counter.getTotalLines());
        assertEquals(0, counter.getErrorLines());
        assertEquals(3, counter.getValidLines());
    }

    @Test
    @DisplayName("Только ошибки")
    void onlyErrors() {
        counter.incrementErrorLine();
        counter.incrementErrorLine();

        assertEquals(0, counter.getTotalLines());
        assertEquals(2, counter.getErrorLines());
        assertEquals(0, counter.getValidLines());
    }

    @Test
    @DisplayName("Большое количество операций")
    void largeNumberOfOperations() {
        for (int i = 0; i < 1000; i++) {
            counter.incrementLine(); // 1000 total
            counter.incrementValidLine(); // 1000 valid
            if (i % 10 == 0) {
                counter.incrementErrorLine(); // 100 errors
            }
        }

        assertEquals(1000, counter.getTotalLines());
        assertEquals(100, counter.getErrorLines());
        assertEquals(1000, counter.getValidLines());
    }

    @Test
    @DisplayName("reset после использования работает корректно")
    void reset_afterUsage_worksCorrectly() {
        // Первое использование
        counter.incrementLine();
        counter.incrementValidLine();
        counter.incrementErrorLine();
        counter.reset();

        // Второе использование после reset
        counter.incrementLine();
        counter.incrementValidLine();

        assertEquals(1, counter.getTotalLines());
        assertEquals(0, counter.getErrorLines());
        assertEquals(1, counter.getValidLines());
    }

    @Test
    @DisplayName("Независимость счетчиков")
    void countersAreIndependent() {
        counter.incrementLine(); // влияет только на total
        assertEquals(1, counter.getTotalLines());
        assertEquals(0, counter.getValidLines());
        assertEquals(0, counter.getErrorLines());

        counter.incrementValidLine(); // влияет только на valid
        assertEquals(1, counter.getTotalLines());
        assertEquals(1, counter.getValidLines());
        assertEquals(0, counter.getErrorLines());

        counter.incrementErrorLine(); // влияет только на error
        assertEquals(1, counter.getTotalLines());
        assertEquals(1, counter.getValidLines());
        assertEquals(1, counter.getErrorLines());
    }
}
