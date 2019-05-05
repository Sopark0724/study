package com.sopark.algorism.study.graph;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 *      0
 *      |
 *      1   --  3   -- 5 --7
 *      |    /  |       \
 *      |  /    |        \
 *      2   --  4         6 --  8
 */
public class BFSTest {

    @Test
    public void BFS_test() {
        BFS bfs = new BFS(9);
        bfs.addEdge(0,1);
        bfs.addEdge(1,2);
        bfs.addEdge(1,3);
        bfs.addEdge(2,4);
        bfs.addEdge(2,3);
        bfs.addEdge(3,4);
        bfs.addEdge(3,5);
        bfs.addEdge(5,6);
        bfs.addEdge(5,7);
        bfs.addEdge(6,8);
        List<Integer> result = bfs.search();
        List<Integer> expectedValue = Arrays.asList(0,1,2,3,4,5,6,7,8);
        Assertions.assertThat(result.toString()).isEqualTo(expectedValue.toString());
    }
}