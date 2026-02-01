package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator cmp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        cmp = c;

    }

    public T max() {
        if (size == 0) return null;
        int index = nextFirst + 1;
        T max = items[index++];
        while (index < nextLast) {
            if (index >= items.length) index = 0;
            if (cmp.compare(items[index], max) > 0) {
                max = items[index];
            }
            index++;
        }

        return max;
    }

    public T max(Comparator<T> c) {
        if (size == 0) return null;
        int index = nextFirst + 1;
        T max = items[index++];
        while (index < nextLast) {
            if (index >= items.length) index = 0;
            if (c.compare(items[index], max) > 0) {
                max = items[index];
            }
            index++;
        }

        return max;
    }
}

