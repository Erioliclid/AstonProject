package files;

import java.util.concurrent.atomic.LongAdder;

public final class LineCounter {
    /**
     * Кастомный счетчик прочитанных из файла строк
     */

    private final LongAdder lineCounter = new LongAdder();
    private final LongAdder errorLineCounter = new LongAdder();

    public void incrementLine() {
        this.lineCounter.increment();
    }

    public long getTotalLines() {
        return this.lineCounter.sum();
    }

    public void incrementErrorLine() {
        this.errorLineCounter.increment();
    }

    public long getErrorLines() {
        return this.errorLineCounter.sum();
    }

    public long getValidLines() {
        return getTotalLines() - getErrorLines();
    }

    public String toString() {
        return String.format("Всего строк: %d\nВалидный строк: %d\nОшибок формата: %d",
                getTotalLines(),
                getValidLines(),
                getErrorLines());
    }
}
