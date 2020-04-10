package com.mj.algorithms.day02;

/**
 * Created by andy
 * 2020/4/10.
 * Email: 1239604859@qq.com
 */

public class DynamicArray<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    private int size;
    private Object[] elements;

    public DynamicArray(int capacity) {
        capacity = capacity < 0 ? DEFAULT_CAPACITY : capacity;
        elements = new Object[capacity];
    }

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public void clear() {
        size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public void add(T element) {

    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    /**
     * 设置新的元素, 返回原来的元素
     */
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T old = (T) elements[index];
        elements[index] = element;
        return old;
    }

    public void remove(int index) {

    }

    public int indexOf(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
