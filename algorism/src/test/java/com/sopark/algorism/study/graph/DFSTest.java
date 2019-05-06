package com.sopark.algorism.study.graph;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 *      0
 *      |
 *      1   --  3   -- 5 --7
 *      |    /  |       \
 *      |  /    |        \
 *      2   --  4         6 --  8
 */
public class DFSTest {

    @Test
    public void DFS_stack_test() {
        DFS bfs = new DFS(9);
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

        List<Integer> result = bfs.searchStack();
        List<Integer> expectedValue = Arrays.asList(0, 1, 3, 5, 7, 6, 8, 4, 2);
        Assertions.assertThat(result.toString()).isEqualTo(expectedValue.toString());
    }

    @Test
    public void DFS_recursive_test() {
        DFS bfs = new DFS(9);
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

        List<Integer> result = bfs.searchRecusive();
        List<Integer> expectedValue = Arrays.asList(0,1,2,4,3,5,6,8,7);
        Assertions.assertThat(result.toString()).isEqualTo(expectedValue.toString());
    }


    // 재귀
    @Test
    public void DFS_recursive_start3_test() {
        DFS bfs = new DFS(9);
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

        List<Integer> result = bfs.searchRecusive(3);
        List<Integer> expectedValue = Arrays.asList(3,1,0,2,4,5,6,8,7);
        Assertions.assertThat(result.toString()).isEqualTo(expectedValue.toString());
    }

    @Test
    public void test11(){
        // Given

        // When

        // Then
        System.out.println(whilereturn());
    }

    public int whilereturn(){
        int i = 10;
        while (i > 0) {

            if(i == 5) {
                return i;
            }

            i--;
        }

        System.out.println("testttt" + i);

        return i;
    }
}