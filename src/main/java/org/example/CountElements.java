package org.example;

public class CountElements{

    public static <T> void count(CityArrayList<T> collection, T elementToFind) {
        final int size = collection.size();
        final int threadsAmount = Math.min(size, 8);
        Counter<T>[] threads = new Counter[threadsAmount];
        final int checkSize = (int) Math.ceil((double) size / threadsAmount);

        for (int i = 0; i < threadsAmount; i++) {
            int start = i * checkSize;
            int end = Math.min(start + checkSize, size);

            threads[i] = new Counter<>(collection, elementToFind, start, end);
            threads[i].start();
        }

        int totalCount = 0;
        try {
            for (Counter<T> curThread : threads) {
                if (curThread != null) {
                    curThread.join();
                    totalCount += curThread.getCount();
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted");
        }

        System.out.println("Element " + elementToFind + " have been found " + totalCount + " times");
    }


    private static class Counter<T> extends Thread {
        private final CityArrayList<T> collection;
        private final T elementToFind;
        private final int start;
        private final int end;
        private int count = 0;

        public Counter(CityArrayList<T> collection, T elementToFind, int start, int end) {
            this.collection = collection;
            this.elementToFind = elementToFind;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                if (elementToFind.equals(collection.get(i))) {
                    count++;
                }
            }
        }

        public int getCount() {
            return count;
        }
    }
}