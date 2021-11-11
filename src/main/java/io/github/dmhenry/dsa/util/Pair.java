package io.github.dmhenry.dsa.util;

public class Pair<T> {

    public final T x, y;

    private Pair(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public static Pair<Integer> pair(int x, int y) {
        return new Pair<>(x, y);
    }

}
