package org.example;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class CityArrayList<T> {

    private final int INIT_CAPACITY = 12;

    private int capacity = 0;
    private Object[] array;
    private int size = 0;

    public CityArrayList() {
        array = new Object[INIT_CAPACITY];
        capacity = INIT_CAPACITY;

    }

    public CityArrayList(int capacity) {
        if (capacity > 0) {
            array = new Object[capacity];
            this.capacity = capacity;
        } else {
            new CityArrayList();
        }
    }

    private void checkAddCapacity() {
        if (size == capacity) {
            array = Arrays.copyOf(array, capacity * 2);
            capacity *= 2;
        }
    }

    private void checkRemoveCapacity() {
        if (size <= capacity / 4 && capacity > INIT_CAPACITY) {
            array = Arrays.copyOf(array, capacity / 2);
            capacity /= 2;
        }
    }

    public void add(T element) {
        checkAddCapacity();
        array[size++] = element;
    }

    public void add(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        checkAddCapacity();
        if (index < size) {
            for (int i = size - 1; i > index; i--) {
                array[i + 1] = array[i];
            }
        }
        array[index] = element;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) array[index];
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public int capacity() {
        return capacity;
    }

    public void put(T element, int index) {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[index] = element;
    }

    public Object remove() {
        if (size == 0) {
            throw new NoSuchElementException("The list is empty");
        }
        Object removed = array[size - 1];
        array[size - 1] = null;
        size--;
        checkRemoveCapacity();
        return removed;
    }

    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Object removed = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        checkRemoveCapacity();
        return removed;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(array[i]).append("\n");
        }
        return result.toString();
    }
}
