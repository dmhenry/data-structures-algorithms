package io.github.dmhenry.dsa.graph.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

interface IntAdjacencyListSearch {

    List<Integer> search(List<List<Integer>> adjList, int root);

    class RecursiveImpl implements IntAdjacencyListSearch { //{{{

        @Override
        public List<Integer> search(List<List<Integer>> adjList, int root) {
            List<Integer> result = new ArrayList<>();
            boolean[] visited = new boolean[adjList.size()];
            search(adjList, root, visited, result);
            return result;
        }

        private void search(List<List<Integer>> adjList, int root, boolean[] visited, List<Integer> result) {
            visited[root] = true;
            result.add(root);

            for (Integer neighbor : adjList.get(root)) {
                if (!visited[neighbor]) {
                    search(adjList, neighbor, visited, result);
                }
            }
        }

    } //}}}

    class IterativeStackImpl implements IntAdjacencyListSearch { //{{{

        @Override
        public List<Integer> search(List<List<Integer>> adjList, int root) {
            List<Integer> result = new ArrayList<>();
            boolean[] visited = new boolean[adjList.size()];
            Deque<Integer> stack = new ArrayDeque<>();

            stack.offerFirst(root);
            visited[root] = true;

            while (!stack.isEmpty()) {
                Integer popped = stack.pollFirst();
                result.add(popped);

                for (Integer neighbor : adjList.get(popped)) {
                    if (!visited[neighbor]) {
                        stack.offerFirst(neighbor);
                        visited[neighbor] = true;
                    }
                }
            }
            return result;
        }

    } //}}}

}
