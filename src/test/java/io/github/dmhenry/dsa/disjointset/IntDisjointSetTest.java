package io.github.dmhenry.dsa.disjointset;

import io.github.dmhenry.dsa.util.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static io.github.dmhenry.dsa.util.Pair.pair;
import static org.junit.jupiter.api.Assertions.assertEquals;

public interface IntDisjointSetTest {

    int SIZE = 5;

    IntDisjointSet strategy();

    @ParameterizedTest
    @MethodSource("params")
    default void unionFind(List<Pair<Integer>> unionPairs) {
        IntDisjointSet disjointSetStrategy = strategy();

        Set<Integer> unseen = new HashSet<>();
        for (int i = 0; i < SIZE; i++) {
            unseen.add(i);
        }

        for (Pair<Integer> unionPair : unionPairs) {
            disjointSetStrategy.union(unionPair.x, unionPair.y);
            assertEquals(disjointSetStrategy.find(unionPair.x), disjointSetStrategy.find(unionPair.y));

            unseen.remove(unionPair.x);
            unseen.remove(unionPair.y);
        }

        for (Integer value : unseen) {
            assertEquals(value, disjointSetStrategy.find(value));
        }
    }

    static Stream<Arguments> params() {
        return Stream.of(
                Arguments.arguments(Collections.emptyList()),
                Arguments.arguments(List.of(pair(0, 0), pair(1, 1), pair(2, 2), pair(3, 3), pair(4, 4))),

                Arguments.arguments(List.of(pair(0, 1))),
                Arguments.arguments(List.of(pair(1, 0))),

                Arguments.arguments(List.of(pair(0, 1), pair(0, 2))),
                Arguments.arguments(List.of(pair(0, 1), pair(2, 0))),
                Arguments.arguments(List.of(pair(1, 0), pair(2, 0))),
                Arguments.arguments(List.of(pair(1, 0), pair(0, 2))),
                Arguments.arguments(List.of(pair(0, 2), pair(0, 1))),
                Arguments.arguments(List.of(pair(2, 0), pair(0, 1))),
                Arguments.arguments(List.of(pair(2, 0), pair(1, 0))),
                Arguments.arguments(List.of(pair(0, 2), pair(1, 0))),

                Arguments.arguments(List.of(pair(0, 1), pair(3, 4)), pair(2, 3), pair(4, 1)),
                Arguments.arguments(List.of(pair(0, 1), pair(3, 4)), pair(4, 3)),
                Arguments.arguments(List.of(pair(0, 1), pair(1, 4)), pair(4, 3)),
                Arguments.arguments(List.of(pair(0, 1), pair(0, 2)))
        );
    }

    @SuppressWarnings("unused")
    class NaiveImplTest implements IntDisjointSetTest {
        @Override
        public IntDisjointSet strategy() {
            return new IntDisjointSet.NaiveImpl(SIZE);
        }
    }

    @SuppressWarnings("unused")
    class QuickFindImplTest implements IntDisjointSetTest {
        @Override
        public IntDisjointSet strategy() {
            return new IntDisjointSet.QuickFindImpl(SIZE);
        }
    }

    @SuppressWarnings("unused")
    class QuickUnionImplTest implements IntDisjointSetTest {
        @Override
        public IntDisjointSet strategy() {
            return new IntDisjointSet.QuickUnionImpl(SIZE);
        }
    }

    @SuppressWarnings("unused")
    class UnionByRankImplTest implements IntDisjointSetTest {
        @Override
        public IntDisjointSet strategy() {
            return new IntDisjointSet.UnionByRankImpl(SIZE);
        }
    }

    @SuppressWarnings("unused")
    class PathCompressionImplTest implements IntDisjointSetTest {
        @Override
        public IntDisjointSet strategy() {
            return new IntDisjointSet.PathCompressionImpl(SIZE);
        }
    }

    @SuppressWarnings("unused")
    class OptimizedImplTest implements IntDisjointSetTest {
        @Override
        public IntDisjointSet strategy() {
            return new IntDisjointSet.OptimizedImpl(SIZE);
        }
    }

}
