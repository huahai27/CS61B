package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T>{
    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

//    public LinkedListDeque(T x) {
//        sentinel = new Node(null, null, null);
//        sentinel.next = new Node(x, sentinel, sentinel);
//        sentinel.prev = sentinel.next;
//        size = 1;
//    }

    @Override
    public void addFirst(T item) {
        Node preFirst = sentinel.next;
        sentinel.next = new Node(item, sentinel, preFirst);
        preFirst.prev = sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node preLast = sentinel.prev;
        sentinel.prev = new Node(item, preLast, sentinel);
        preLast.next = sentinel.prev;
        size += 1;
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
        Node n = sentinel.next;
        while (n.next != sentinel.next) {
            System.out.print(n.item + " ");
            n = n.next;
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;
        Node remove = sentinel.next;
        T item = remove.item;
        Node newFirst = remove.next;
        sentinel.next = newFirst;
        newFirst.prev = sentinel;
        remove.item = null;
        remove.prev = null;
        remove.next = null;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;
        Node remove = sentinel.prev;
        T item = remove.item;
        Node newLast = remove.prev;
        sentinel.prev = newLast;
        newLast.next = sentinel;
        remove.item = null;
        remove.prev = null;
        remove.next = null;
        size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(
                    String.format("索引 %d 超出数组大小 %d", index, size)
            );
        } else {
            Node n = sentinel.next;
            int cnter = 0;

            while (cnter != index) {
                n = n.next;
                cnter++;
            }

            T item = n.item;
            return item;
        }
    }

    private T getRecursiveHelper(Node l, int index) {
        if (index == 0) {
            return l.item;
        } else {
            return getRecursiveHelper(l.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException(
                    String.format("索引 %d 超出数组大小 %d", index, size)
            );
        } else {
            Node n = sentinel.next;
            return getRecursiveHelper(n, index);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node cursor;

        public LinkedListDequeIterator() {
            cursor = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return cursor != sentinel;
        }

        @Override
        public T next() {
            T returnItem = cursor.item;
            cursor = cursor.next;
            return returnItem;
        }
    }

    public boolean contains(T item) {
        Node n = sentinel.next;
        while (n != sentinel) {
            if (n.item == item) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        // cs61b 2026
        // if (o instanceof LinkedListDeque uddalld)
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        for (T item : this) {
            if (!other.contains(item)) return false;
        }

        return true;
    }
}
