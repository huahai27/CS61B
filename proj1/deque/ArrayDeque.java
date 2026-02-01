package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    protected T[] items;
    protected int size;
    protected int nextFirst;
    protected int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[100];
        size = 0;
        nextFirst = 20;
        nextLast = 21;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int start = nextFirst + 1;
        int end = nextLast - 1;
        nextFirst = capacity / 4;
        nextLast = nextLast + 1;
        int cursor = nextFirst;

        while (start != end) {
            if (start > size - 1) start = 0;
            a[cursor] = items[start];
            cursor++;
            start++;
        }

        items = a;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) resize(size * 2);
        items[nextFirst] = item;
        nextFirst--;
        if (nextFirst < 0) nextFirst = items.length - 1;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) resize(size * 2);
        items[nextLast] = item;
        nextLast++;
        if (nextLast > items.length - 1) nextLast = 0;
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
        int cursor = nextFirst + 1;

        while (cursor != nextLast) {
            if (cursor == items.length) cursor = 0;

            System.out.print(items[cursor] + " ");
            cursor++;
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;
        T remove = items[++nextFirst];
        if (nextFirst > items.length - 1) nextFirst = 0;
        size--;
        return remove;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;
        T remove = items[--nextLast];
        if (nextLast < 0) nextLast = items.length - 1;
        size--;
        return remove;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(
                    String.format("索引 %d 超出数组大小 %d", index, size)
            );
        } else {
            int cnter = 0;
            int cursor = nextFirst + 1;

            while (cnter != index) {
                if (cursor > items.length - 1) cursor = 0;
                cnter++;
                cursor++;
            }

            T item = items[cursor];
            return item;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int cursor;

        public ArrayDequeIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            T returnItem = items[cursor];
            cursor++;
            return  returnItem;
        }
    }

    public boolean contains(T item) {
        for (T x : this) {
            if (x == item) return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        // cs61b 2026
        // if (o instanceof ArrayDeque uddaad)
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        for (T item : this) {
            if (!other.contains(item)) return false;
        }

        return true;
    }
}
