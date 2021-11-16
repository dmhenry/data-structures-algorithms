package io.github.dmhenry.dsa.graph.dfs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.opentest4j.AssertionFailedError;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

interface IntAdjacencyListSearchTest {

    IntAdjacencyListSearch strategy();

    @ParameterizedTest
    @MethodSource("params")
    default void search(List<List<Integer>> adjList, int root, List<List<Integer>> possibleExpected) {
        IntAdjacencyListSearch strategy = strategy();
        boolean success = false;

        for (List<Integer> expected : possibleExpected) {
            try {
                assertEquals(expected, strategy.search(adjList, root));
                success = true;
                break;
            } catch (AssertionFailedError ignored) {

            }
        }
        if (!success) {
            fail();
        }
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> params() {
        return Stream.of(
                Arguments.arguments(
                        List.of(List.of(1, 2), List.of(0, 2, 3), List.of(0, 1), List.of(1)),
                        0, List.of(List.of(0, 1, 2, 3), List.of(0, 2, 1, 3))
                )
        );
    }

    class RecursiveImpl implements IntAdjacencyListSearchTest { //{{{
        @Override
        public IntAdjacencyListSearch strategy() {
            return new IntAdjacencyListSearch.RecursiveImpl();
        }
    }

    class IterativeStackImpl implements IntAdjacencyListSearchTest { //{{{
        @Override
        public IntAdjacencyListSearch strategy() {
            return new IntAdjacencyListSearch.IterativeStackImpl();
        }
    }

}