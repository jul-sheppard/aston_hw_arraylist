package homework;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Resizable list with QuickSort based on array.
 * <p>
 * The objects is used to place elements in the list.
 * When filling an array, the size of the array is doubled
 * <p>
 *
 * @param <T> the Type of elements in this list
 * @author Julia Bazulina
 */
public class MyArrayList<T extends Comparable<T>> implements List<T> {
    private Object[] objects;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    /**
     * Creates an empty list with initial capacity of capacity
     *
     * @param capacity
     * @throws IllegalArgumentException if the specified initial capacity is wrong
     */
    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity is wrong " + capacity);
        } else {
            objects = new Object[capacity];
        }
    }

    /**
     *Creates a new list based on the array passed into it.
     * @param array array that is passed to the constructor
     */
    public MyArrayList(T[] array) {
        objects = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            objects[i] = array[i];
        }
        size = array.length;
    }

    /**
     * Creates an empty list with initial capacity of DEFAULT_CAPACITY
     */
    public MyArrayList() {
        objects = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    /**
     * Adds an element to the end of the list.
     * <p>
     * If the array is full, doubles its size and copies the elements to a new array
     *
     * @param element to be inserted
     */
    @Override
    public boolean add(T element) {
        if (size == objects.length) {
            objects = Arrays.copyOf(objects, 2 * objects.length);
        }
        objects[size++] = (T) element;
        return true;
    }

    /**
     * Inserts an element at the specified index, shifting existing elements to the right.
     * <p>
     * When the array is full, its capacity also doubles.
     *
     * @param index   position to insertion
     * @param element to be inserted
     * @throws IndexOutOfBoundsException if index is wrong
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index goes beyond the list");
        }
        if (size == objects.length) {
            objects = Arrays.copyOf(objects, 2 * objects.length);
        }
        for (int i = size; i > index; i--) {
            objects[i] = objects[i - 1];
        }
        objects[index] = element;
        size++;
    }

    /**
     * Returns an item by index in the specified list
     *
     * @param index
     * @return the element at the position in this list
     * @throws IndexOutOfBoundsException if index is wrong
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) objects[index];
    }

    /**
     * Removes the element at the specified position in this list.
     * <p>
     * Shifts any subsequent elements to the left.
     *
     * @param index the element to be deleted
     * @return the item that was removed from the list
     * @throws IndexOutOfBoundsException if index is wrong
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T element = (T) objects[index];
        if (index < size) {
            objects[index] = null;
            System.arraycopy(objects, index + 1, objects, index, size - index - 1);
            size--;
        }
        return element;
    }

    /**
     * Appends all elements from another List collection to the end of the current list, increasing its capacity if required.
     *
     * @param c the specified collection
     * @return true if all elements from c are successfully added
     */
    @Override
    public boolean addAll(List<? extends T> c) {
        ensureCapacity(size + c.size());
        for (T element : c) {
            add(element);
        }
        return true;
    }

    /**
     * Clears the list by setting all elements to null and resetting the size to 0.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            objects[i] = null;
        }
        size = 0;
    }

    /**
     * Increases the array's capacity if necessary to accommodate at least the minCapacity of elements.
     *
     * @param minCapacity minimum required array capacity, and increases capacity if it is insufficient.
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > objects.length) {
            objects = Arrays.copyOf(objects, size * 2);
        }
    }

    /**
     * @return the number of elements in the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Overriding the toString method
     *
     * @return Returns the resulting string with a list representation in the format "[element1, element2, ...]"
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(objects[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Uses the quickSort method to sort all elements of a list.
     */
    @Override
    public void sort() {
        quickSort(0, size - 1);
    }

    /**
     * Realisation of QuickSort
     *
     * @param low  bound of array
     * @param high bound of array
     */
    private void quickSort(int low, int high) {
        if (low < high) {
            int middleIndex = partition(low, high);
            quickSort(low, middleIndex - 1);
            quickSort(middleIndex + 1, high);
        }
    }

    /**
     * The partition method selects the element (middle) relative to which the division will occur.
     * All elements smaller than middle are moved to the left, and all larger ones are moved to the right.
     *
     * @param low bound of array
     * @param high bound of array
     * @return index of the reference element
     */
    private int partition(int low, int high) {
        T middle = (T) objects[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (0 >= ((T) objects[j]).compareTo(middle)) {
                i++;
                T temp = (T) objects[i];
                objects[i] = objects[j];
                objects[j] = temp;
            }
        }
        T temp = (T) objects[i + 1];
        objects[i + 1] = objects[high];
        objects[high] = temp;

        return i + 1;
    }

    /**
     * Overriding the method for comparing two elements
     * @param obj object being compared
     * @return if all checks are passed
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MyArrayList<?> other = (MyArrayList<?>) obj;
        if (size != other.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!objects[i].equals(other.objects[i])) {
                return false;
            }
        }
        return true;
    }


    /**
     * Allow you to iterate over a list using Iterator. The implementation of the Iterator interface includes
     * hasNext() and next() methods for iterating over elements.
     *
     * @return an object that can be used to get all the elements of MyArrayList one by one
     */
    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) objects[currentIndex++];
        }
    }
}
