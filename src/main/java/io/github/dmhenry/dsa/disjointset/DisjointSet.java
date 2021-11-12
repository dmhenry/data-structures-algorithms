package io.github.dmhenry.dsa.disjointset;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

interface DisjointSet<T> {

    void union(T x, T y);

    T find(T key);

    class AssocDisjointSetImpl<T> implements DisjointSet<T> { //{{{

        private final Map<T, T> parents = new HashMap<>();
        private final Map<T, Integer> rank = new HashMap<>();

        AssocDisjointSetImpl(T... elements) {
            for (T t : elements) {
                parents.put(t, t);
                rank.put(t, 0);
            }
        }

        @Override
        public void union(T x, T y) {
            T xRoot = find(x);
            T yRoot = find(y);

            if (!xRoot.equals(yRoot)) {
                if (rank.get(xRoot) > rank.get(yRoot)) {
                    parents.put(yRoot, xRoot);
                } else if (rank.get(xRoot) < rank.get(yRoot)) {
                    parents.put(xRoot, yRoot);
                } else {
                    parents.put(yRoot, xRoot);
                    rank.put(xRoot, rank.get(xRoot) + 1);
                }
            }
        }

        @Override
        public T find(T key) {
            T parent = parents.get(key);
            if (!Objects.equals(key, parent)) {
                parent = find(parents.get(key));
                parents.put(key, parent);
            }
            return parent;
        }
    } //}}}

}
