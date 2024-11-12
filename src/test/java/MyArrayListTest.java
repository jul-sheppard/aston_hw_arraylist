import homework.List;
import homework.MyArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MyArrayListTest {

    @Test
    void testAdd() {
        List<String> strArr = new MyArrayList<>();
        assertEquals(0, strArr.size());
        strArr.add("Hello");
        assertEquals(1, strArr.size());
    }

    @Test
    @DisplayName("Array extension")
    void testAddExpansion() {
        List<Integer> intArr = new MyArrayList<>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(10, intArr.size());
        intArr.add(11);
        assertEquals(11, intArr.size());
    }

    @Test
    @DisplayName("Index goes beyond the list")
    void testAdd2() {
        List<Integer> intArr = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> {intArr.add(43,1);});
    }

    @Test
    void remove() {
        List<String> strArr = new MyArrayList<>();
        strArr.add("a");
        strArr.add("b");
        strArr.add("c");
        strArr.remove(1);
        assertEquals(strArr.size(), 2);

        List<String> strArr2 = new MyArrayList<>();
        strArr2.add("a");
        strArr2.add("c");
        assertEquals(strArr2, strArr);
    }

    @Test
    void addAll() {
        List<String> strArr = new MyArrayList<>();
        strArr.add("a");
        strArr.add("b");
        List<String> strArr2 = new MyArrayList<>();
        strArr2.add("c");
        strArr2.add("d");
        strArr.addAll(strArr2);

        List<String> strArrExpected = new MyArrayList<>();
        strArrExpected.add("a");
        strArrExpected.add("b");
        strArrExpected.add("c");
        strArrExpected.add("d");
        assertEquals(strArrExpected, strArr);
    }

    @Test
    void get() {
        List<String> strArr = new MyArrayList<>();
        strArr.add("a");
        strArr.add("b");
        strArr.add("c");
        assertEquals(strArr.get(1), "b");
    }

    @Test
    void testSortInt() {
        List<Integer> intArr = new MyArrayList<>(new Integer[] {2, 3, 54, 1, 453});
        intArr.sort();
        List<Integer> intArr2 = new MyArrayList<>(new Integer[] {1, 2, 3, 54, 453});
        assertEquals(intArr2, intArr);
    }

    @Test
    void testSortString() {
        List<String> strArr = new MyArrayList<>(new String[] {"e", "a", "d", "g", "c", "b", "f" });
        strArr.sort();

        List<String> strArrExpected = new MyArrayList<>(new String[] {"a", "b", "c", "d", "e", "f" ,"g"});
        assertEquals(strArrExpected, strArr);
    }

}
