package files;

import java.util.concurrent.atomic.LongAdder;

/**
 * Счетчик для учета статистики обработки строк.
 * Потокобезопасен, использует LongAdder для высокой производительности.
 */
public final class LineCounter {
    private final LongAdder lineCounter = new LongAdder();
    private final LongAdder errorLineCounter = new LongAdder();

    /** Увеличивает счетчик обработанных строк на 1 */
    public void incrementLine() {
        this.lineCounter.increment();
    }

    /** Возвращает общее количество обработанных строк */
    public long getTotalLines() {
        return this.lineCounter.sum();
    }

    /** Увеличивает счетчик строк с ошибками на 1 */
    public void incrementErrorLine() {
        this.errorLineCounter.increment();
    }

    /** Возвращает количество строк с ошибками */
    public long getErrorLines() {
        return this.errorLineCounter.sum();
    }

    /** Возвращает количество валидных строк */
    public long getValidLines() {
        return getTotalLines() - getErrorLines();
    }

    /** Сбрасывает все счетчики в 0 */
    public void reset() {
        this.lineCounter.reset();
        this.errorLineCounter.reset();
    }

    public String toString() {
        return String.format("Всего строк: %d\nВалидных строк: %d\nОшибок формата: %d",
                getTotalLines(),
                getValidLines(),
                getErrorLines());
    }
}
