package com.avehlies.Rectangles.Entities;

import java.util.Objects;

/**
 * Used to group two items of the same type together in a pair. This is a simple version of the Apache Commons Pair
 * @param <T> the type of object to have in the pair
 */
public class Pair<T> {

    private final T left;
    private final T right;

    public Pair(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public T getRight() {
        return right;
    }
}
