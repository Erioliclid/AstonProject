package files;

import java.util.concurrent.atomic.LongAdder;

/**
 * Счетчик для учета статистики обработки строк.
 * Потокобезопасен, использует LongAdder для высокой производительности.
 */
public class LineCounter {
    private final LongAdder totalLines = new LongAdder();
    private final LongAdder validLines = new LongAdder();
    private final LongAdder errorLineCounter = new LongAdder();

    /** Увеличивает счетчик обработанных строк на 1 */
    public void incrementLine() {
        this.totalLines.increment();
    }

    public void incrementValidLine() {this.validLines.increment();};

    /** Возвращает общее количество обработанных строк */
    public long getTotalLines() {
        return this.totalLines.sum();
    }

    /** Увеличивает счетчик строк с ошибками на 1 */
    public void incrementErrorLine() {
        this.errorLineCounter.increment();
    }

    /** Возвращает количество ошибок парсинга строк или создания объекта */
    public long getErrorLines() {
        return this.errorLineCounter.sum();
    }

    /** Возвращает количество валидных строк */
    public long getValidLines() {
        return this.validLines.sum();
    }

    /** Сбрасывает все счетчики в 0 */
    public void reset() {
        this.totalLines.reset();
        this.validLines.reset();
        this.errorLineCounter.reset();
    }

    public String toString() {
        return String.format("Всего строк: %d\nВалидных строк: %d\nОшибок парсинга или создания объекта: %d",
                getTotalLines(),
                getValidLines(),
                getErrorLines());
    }
}
