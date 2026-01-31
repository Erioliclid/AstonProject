package org.example.CityArrayList;

import org.example.City;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CityArrayList<T> implements Iterable<T> {

    private final int INIT_CAPACITY = 12;

    private int capacity;
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

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T)array[index++];
            }
        };
    }

    public Stream<T> stream() {
        return StreamSupport.stream(
                Spliterators.spliterator(iterator(),size,Spliterator.ORDERED), false);
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

    public boolean isEmpty(){
        return size == 0;
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

    public boolean remove() {
        if (size == 0) {
            throw new NoSuchElementException("The list is empty");
        }
        array[size - 1] = null;
        size--;
        checkRemoveCapacity();
        return true;
    }

    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        checkRemoveCapacity();
        return true;
    }

    public boolean remove(String name) {
        boolean find = false;
        for (int i = 0; i < size; i++) {
           City city = (City) array[i];
           if (name.equals(city.getName())) {
               for (int j = i; j < size - 1; j++) {
                   array[j] = array[j + 1];
               }
               array[size - 1] = null;
               size--;
               checkRemoveCapacity();
               find = true;
           }
        }
        return find;
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
