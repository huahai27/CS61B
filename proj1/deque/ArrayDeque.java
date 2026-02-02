package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8]; // 别设太大
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private int proceed(int index) {
        return (index + 1) % items.length;
    }

    private int backward(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private void resize(int capacity) {
        int start = proceed(nextFirst);
        T[] a = (T[]) new Object[capacity];
        nextFirst = capacity / 4;
        nextLast = (nextFirst + 1 + size) % capacity;
        int cursor = nextFirst + 1;

        for (int i = 0; i < size; i++) {
            a[cursor] = items[start];
            cursor = (cursor + 1) % capacity;
            start = proceed(start);
        }

        items = a;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = backward(nextFirst);
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = proceed(nextLast);
        size++;
    }

//    @Override
//    public boolean isEmpty() {
//        return size == 0;
//    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int cursor = proceed(nextFirst);

        while (cursor != nextLast) {
            System.out.print(items[cursor] + " ");
            cursor = proceed(cursor);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = proceed(nextFirst);
        T remove = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (items.length >= 16 && (float) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        return remove;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = backward(nextLast);
        T remove = items[nextLast];
        items[nextLast] = null;
        size--;
        if (items.length >= 16 && (float) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        return remove;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(
                    String.format("索引 %d 超出数组大小 %d", index, size)
            );
        } else {
            int actualIndex = (nextFirst + 1 + index) % items.length;
            return items[actualIndex];
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int cursor;

        ArrayDequeIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            T returnItem = get(cursor);
            cursor++;
            return  returnItem;
        }
    }

    private boolean contains(T item) {
        for (T x : this) {
            if (x == item) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        // cs61b 2026
        // if (o instanceof ArrayDeque uddaad)
        if (o == null) {
            return false;
        }
        // 满足跨类比较
        if (!(o instanceof Deque<?>)) {
            return false;
        }

        Deque<?> other = (Deque<?>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(other.get(i))) {
                return false;
            }
        }

        return true;
    }
}
