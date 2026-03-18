package bstmap;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class BSTNode {
        private K key;
        private V value;
        private BSTNode leftNode;
        private BSTNode rightNode;

        BSTNode(K k, V v, BSTNode ln, BSTNode rn) {
            key = k;
            value = v;
            leftNode = ln;
            rightNode = rn;
        }
    }

    private BSTNode node;
    private int size;

    public BSTMap() {
        node = null;
        size = 0;
    }

    @Override
    public void clear() {
        node = null;
        size = 0;
    }

    private boolean containsKey(BSTNode n, K key) {
        if (n == null) {
            return false;
        }

        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            return containsKey(n.leftNode, key);
        } else if (cmp > 0){
            return containsKey(n.rightNode, key);
        } else {
            return true;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(node, key);
    }

    private V get(BSTNode n, K key) {
        if (n == null) {
            return null;
        }

        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            return get(n.leftNode, key);
        } else if (cmp > 0){
            return get(n.rightNode, key);
        } else {
            return n.value;
        }
    }

    @Override
    public V get(K key) {
        return get(node, key);
    }

    @Override
    public int size() {
        return size;
    }

    private void put(BSTNode n, K key, V value) {
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            if (n.leftNode != null) {
                put(n.leftNode, key, value);
            } else {
                BSTNode newNode = new BSTNode(key, value, null, null);
                n.leftNode = newNode;
                size++;
            }
        } else if (cmp > 0){
            if (n.rightNode != null) {
                put(n.rightNode, key, value);
            } else {
                BSTNode newNode = new BSTNode(key, value, null, null);
                n.rightNode = newNode;
                size++;
            }
        } else {
            n.value = value;
        }
    }

    @Override
    public void put(K key, V value) {
        if (node == null) {
            node = new BSTNode(key, value, null, null);
            size = 1;
            return;
        }
        put(node, key, value);
    }

    private Set<K> keySet(Set<K> keySet, BSTNode n) {
        if (n == null) {
            return keySet;
        }
        keySet.add(n.key);
        if (n.leftNode != null) {
            keySet(keySet, n.leftNode);
        }
        if (n.rightNode != null) {
            keySet(keySet, n.rightNode);
        }
        return keySet;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        return keySet(keySet, node);
    }

    private BSTNode findLeftMost (BSTNode n) {
        if (n.leftNode == null) {
            return n;
        } else {
            return findLeftMost(n.leftNode);
        }
    }

    private BSTNode remove(BSTNode n, K key) {
        if (n == null) {
            return null;
        }

        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.leftNode = remove(n.leftNode, key);
            return n;
        } else if (cmp > 0){
            n.rightNode = remove(n.rightNode, key);
            return n;
        } else {
            // case 1: Key with no Children
            // case 2: Key with one Child
            if (n.leftNode == null) {
                return n.rightNode;
            }
            if (n.rightNode == null) {
                return n.leftNode;
            }

            // case 3: Deletion with two Children (Hibbard)
            // find the left most
            BSTNode leftMost = findLeftMost(n.rightNode);

            n.key = leftMost.key;
            n.value = leftMost.value;
            n.rightNode = remove(n.rightNode, leftMost.key);

            return n;
        }
    }

    @Override
    public V remove(K key) {
        V removedValue = get(key);
        if (removedValue != null) {
            node = remove(node, key);
            size--;
        }
        return removedValue;
    }

    @Override
    public V remove(K key, V value) {
        V removedValue = get(key);
        if (containsKey(key) && Objects.equals(removedValue, value)) {
            node = remove(node, key);
            size--;
        }
        return removedValue;
    }

    private void inorder(BSTNode n, List<K> list){
        if (n == null) {
            return;
        }

        inorder(n.leftNode, list);
        list.add(n.key);
        inorder(n.rightNode, list);
    }

    @Override
    public Iterator<K> iterator() {
        List<K> list = new ArrayList<>();
        inorder(node, list);
        return list.iterator();
    }
}
