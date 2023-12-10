package com.zikriZulfaAzhimJBusRS;

import java.util.*;

public class Algorithm {
    private Algorithm() {
    }

    /**
     *
     * @param iterable
     * @param value
     * @return
     * @param <T>
     */
    public static <T> List<T> collect(Iterable<T> iterable, T value) {
        Iterator<T> i = iterable.iterator();
        return collect(i, value);
    }

    /**
     *
     * @param iterable
     * @param predicate
     * @return
     * @param <T>
     */
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> predicate) {
        Iterator<T> i = iterable.iterator();
        return collect(i, predicate);
    }

    /**
     *
     * @param array
     * @param value
     * @return
     * @param <T>
     */
    public static <T> List<T> collect(T[] array, T value) {
        Iterator<T> i = Arrays.stream(array).iterator();
        return collect(i, value);
    }

    /**
     *
     * @param iterator
     * @param value
     * @return
     * @param <T>
     */
    public static <T> List<T> collect(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> predicate = value::equals;
        return collect(iterator, predicate);
    }

    /**
     *
     * @param array
     * @param predicate
     * @return
     * @param <T>
     */
    public static <T> List<T> collect(T[] array, Predicate<T> predicate) {
        Iterator<T> i = Arrays.stream(array).iterator();
        return collect(i, predicate);
    }

    /**
     *
     * @param iterator
     * @param pred
     * @return
     * @param <T>
     */
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred) {
        List<T> list = new ArrayList();

        while(iterator.hasNext()) {
            T tempVar = iterator.next();
            if (pred.predicate(tempVar)) {
                list.add(tempVar);
            }
        }

        return list;
    }

    /**
     *
     * @param iterator
     * @param value
     * @return
     * @param <T>
     */
    public static <T> int count(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(iterator, pred);
    }

    /**
     *
     * @param iterable
     * @param value
     * @return
     * @param <T>
     */
    public static <T> int count(Iterable<T> iterable, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(iterable, pred);
    }

    /**
     *
     * @param array
     * @param pred
     * @return
     * @param <T>
     */
    public static <T> int count(T[] array, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, pred);
    }

    /**
     *
     * @param array
     * @param value
     * @return
     * @param <T>
     */
    public static <T> int count(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return count(it, value);
    }

    /**
     *
     * @param iterable
     * @param pred
     * @return
     * @param <T>
     */
    public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return count(it, pred);
    }

    /**
     *
     * @param iterator
     * @param pred
     * @return
     * @param <T>
     */
    public static <T> int count(Iterator<T> iterator, Predicate<T> pred) {
        int count = 0;

        while(iterator.hasNext()) {
            if (pred.predicate(iterator.next())) {
                ++count;
            }
        }

        return count;
    }

    /**
     *
     * @param iterator
     * @param number
     * @return
     * @param <T>
     */
    public static <T> T find(Iterator<T> iterator, T number) {
        Objects.requireNonNull(number);
        Predicate<T> pred = number::equals;
        return find(iterator, pred);
    }

    /**
     *
     * @param array
     * @param value
     * @return
     * @param <T>
     */
    public static <T> T find(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }

    /**
     *
     * @param iterable
     * @param pred
     * @return
     * @param <T>
     */
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return find(it, pred);
    }

    /**
     *
     * @param arr
     * @param pred
     * @return
     * @param <T>
     */
    public static <T> T find(T[] arr, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(arr).iterator();
        return find(it, pred);
    }

    /**
     *
     * @param iterator
     * @param pred
     * @return
     * @param <T>
     */
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
        while(true) {
            if (iterator.hasNext()) {
                T current = iterator.next();
                if (!pred.predicate(current)) {
                    continue;
                }

                return current;
            }

            return null;
        }
    }

    /**
     *
     * @param iterable
     * @param value
     * @return
     * @param <T>
     */
    public static <T> T find(Iterable<T> iterable, T value) {
        Iterator<T> it = iterable.iterator();
        return find(it, value);
    }

    /**
     *
     * @param array
     * @param value
     * @return
     * @param <T>
     */
    public static <T> boolean exists(T[] array, T value) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, value);
    }

    /**
     *
     * @param iterable
     * @param value
     * @return
     * @param <T>
     */
    public static <T> boolean exists(Iterable<T> iterable, T value) {
        Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }

    /**
     *
     * @param iterator
     * @param value
     * @return
     * @param <T>
     */
    public static <T> boolean exists(Iterator<T> iterator, T value) {
        Objects.requireNonNull(value);
        Predicate<T> pred = value::equals;
        return exists(iterator, pred);
    }

    /**
     *
     * @param iterable
     * @param pred
     * @return
     * @param <T>
     */
    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
        Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }

    /**
     *
     * @param array
     * @param pred
     * @return
     * @param <T>
     */
    public static <T> boolean exists(T[] array, Predicate<T> pred) {
        Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, pred);
    }

    /**
     *
     * @param iterator
     * @param pred
     * @return
     * @param <T>
     */
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
        while(true) {
            if (iterator.hasNext()) {
                T current = iterator.next();
                if (!pred.predicate(current)) {
                    continue;
                }

                return true;
            }

            return false;
        }
    }

    /**
     *
     * @param arr
     * @param page
     * @param pagesize
     * @param pred
     * @return
     * @param <T>
     */
    public static <T> List<T> paginate(T[] arr, int page, int pagesize, Predicate<T> pred) {
        Iterator<T> i = Arrays.stream(arr).iterator();
        return paginate(i, page, pagesize, pred);
    }

    /**
     *
     * @param iterable
     * @param page
     * @param pagesize
     * @param pred
     * @return
     * @param <T>
     */
    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pagesize, Predicate<T> pred) {
        Iterator<T> i = iterable.iterator();
        return paginate(i, page, pagesize, pred);
    }

    /**
     *
     * @param iterator
     * @param page
     * @param pagesize
     * @param pred
     * @return
     * @param <T>
     */
    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pagesize, Predicate<T> pred) {
        List<T> pageResult = new ArrayList();
        int count = 0;
        int startindex = page * pagesize;
        int endindex = startindex + pagesize;
        while(iterator.hasNext()) {
            T obj = iterator.next();
            if (pred.predicate(obj)) {
                if (count >= startindex && count < endindex) {
                    pageResult.add(obj);
                }

                ++count;
            }
        }

        return pageResult;
    }
}
