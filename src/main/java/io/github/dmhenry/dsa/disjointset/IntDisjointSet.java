package io.github.dmhenry.dsa.disjointset;

interface IntDisjointSet {

    void union(int x, int y);

    int find(int key);

    default boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    class NaiveImpl implements IntDisjointSet { //{{{

        private final int[] parent;

        NaiveImpl(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        @Override
        public void union(int first, int second) {
            parent[second] = parent[first];
        }

        @Override
        public int find(int key) {
            int value = key;
            while (value != parent[value]) {
                value = parent[value];
            }
            return value;
        }

    } //}}}

    /**
     * O(1) find at the cost of O(N) union
     */
    class QuickFindImpl implements IntDisjointSet { //{{{

        private final int[] root;

        QuickFindImpl(int SIZE) {
            root = new int[SIZE];
            for (int i = 0; i < SIZE; i++) {
                root[i] = i;
            }
        }

        @Override
        public void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if (xRoot != yRoot) {
                for (int i = 0; i < root.length; i++) {
                    if (root[i] == yRoot) {
                        root[i] = xRoot;
                    }
                }
            }
        }

        @Override
        public int find(int key) {
            return root[key];
        }


    } //}}}

    class QuickUnionImpl implements IntDisjointSet { //{{{

        private final int[] parent;

        QuickUnionImpl(int SIZE) {
            parent = new int[SIZE];
            for (int i = 0; i < SIZE; i++) {
                parent[i] = i;
            }
        }

        @Override
        public void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if (xRoot != yRoot) {
                parent[yRoot] = xRoot;
            }
        }

        @Override
        public int find(int key) {
            int value = key;
            while (value != parent[value]) {
                value = parent[value];
            }
            return value;
        }


    } //}}}

    /*
     * Quick union by rank
     */
    @SuppressWarnings("unused")
    class UnionByRankImpl implements IntDisjointSet { //{{{

        private final int[] parent;
        private final int[] rank;

        UnionByRankImpl(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
            rank = new int[size];
        }

        @Override
        public void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if (xRoot != yRoot) {
                if (rank[xRoot] > rank[yRoot]) {
                    parent[yRoot] = xRoot;
                } else if (rank[xRoot] < rank[yRoot]) {
                    parent[xRoot] = yRoot;
                } else {
                    parent[yRoot] = xRoot;
                    rank[xRoot]++;
                }
            }
        }

        @Override
        public int find(int key) {
            int value = key;
            while (value != parent[value]) {
                value = parent[value];
            }
            return value;
        }

    } //}}}

    /*
     * Quick union with path compression optimization.
     */
    class PathCompressionImpl implements IntDisjointSet { //{{{

        private final int[] parent;

        PathCompressionImpl(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        @Override
        public void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if (xRoot != yRoot) {
                parent[yRoot] = xRoot;
            }
        }

        @Override
        public int find(int key) {
            if (key != parent[key]) {
                parent[key] = find(parent[key]);
            }
            return parent[key];
        }

    } ///}}}

    /*
     * Quick union with path compression optimization.
     */
    class OptimizedImpl implements IntDisjointSet { //{{{

        private int[] parent;
        private int[] rank;

        OptimizedImpl(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
            rank = new int[size];
        }

        @Override
        public void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if (xRoot != yRoot) {
                if (rank[xRoot] > rank[yRoot]) {
                    parent[yRoot] = xRoot;
                } else if (rank[xRoot] < rank[yRoot]) {
                    parent[xRoot] = yRoot;
                } else {
                    parent[yRoot] = xRoot;
                    rank[xRoot]++;
                }
            }
        }

        @Override
        public int find(int key) {
            if (key != parent[key]) {
                parent[key] = find(parent[key]);
            }
            return parent[key];
        }
    } //}}}

}
