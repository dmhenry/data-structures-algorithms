package io.github.dmhenry.dsa.util;

import java.util.Objects;

public class Pair<T> {

    public final T x, y;

    private Pair(T x, T y) {
        Objects.requireNonNull(x);
        Objects.requireNonNull(y);

        this.x = x;
        this.y = y;
    }

    public static Pair<Integer> pair(int x, int y) {
        return new Pair<>(x, y);
    }

    public static Pair<Character> pair(char a, char b) {
        return new Pair<>(a, b);
    }

    @Override
    public String toString() {
        return "Pair{x=" + x + ",y=" + y + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Pair<?> pair = (Pair<?>) o;
        return Objects.equals(x, pair.x) && Objects.equals(y, pair.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
