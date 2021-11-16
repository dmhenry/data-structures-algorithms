package io.github.dmhenry.dsa.graph.disjointset;

import io.github.dmhenry.dsa.util.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static io.github.dmhenry.dsa.util.Pair.pair;
import static org.junit.jupiter.api.Assertions.assertEquals;

public interface DisjointSetTest {

    List<Character> CHAR_ELEMENTS = List.of('a', 'b', 'c', 'd', 'e', 'f', 'g');

    DisjointSet<Character> strategy();

    @ParameterizedTest
    @MethodSource("params")
    default void unionFind(List<Pair<Character>> unionPairs) {
        DisjointSet<Character> disjointSetStrategy = strategy();

        Set<Character> unseen = new HashSet<>(CHAR_ELEMENTS);

        for (Pair<Character> unionPair : unionPairs) {
            disjointSetStrategy.union(unionPair.x, unionPair.y);
            assertEquals(disjointSetStrategy.find(unionPair.x), disjointSetStrategy.find(unionPair.y));

            unseen.remove(unionPair.x);
            unseen.remove(unionPair.y);
        }

        for (Character value : unseen) {
            assertEquals(value, disjointSetStrategy.find(value));
        }
    }

    static Stream<Arguments> params() {
        return Stream.of(Arguments.arguments(List.of(pair('b', 'c'), pair('a', 'd'), pair('d', 'c'), pair('e', 'f'), pair('e', 'a'))));
    }

    @SuppressWarnings("unused")
    class AssocDisjointSetImplTest implements DisjointSetTest { //{{{
        @Override
        public DisjointSet<Character> strategy() {
            return new DisjointSet.AssocDisjointSetImpl<>(CHAR_ELEMENTS);
        }
    } //}}}

}
