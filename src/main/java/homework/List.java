package homework;

import java.util.Iterator;

/**
 *The List<T> interface describes a set of standard methods for working with a collection of elements
 * @param <T> the Type of elements in this list
 */
public interface List<T> extends Iterable<T> {
    boolean add(T element);
    void add(int index, T element);
    T get(int index);
    T remove(int index);
    boolean addAll(List<? extends T> c);
    public void clear();
    public boolean equals(Object obj);
    public void sort();
    public int size();
    public Iterator<T> iterator();
}