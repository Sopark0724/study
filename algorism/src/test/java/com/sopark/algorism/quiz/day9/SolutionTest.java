package com.sopark.algorism.quiz.day9;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void solution() {
        // Given
        int[] input = new int[]{1, 5, 2, 6, 3, 7, 4};
        int[][] command = new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        // When
        Solution solution = new Solution();
        int[] result = solution.solution(input, command);

        // Then
        assertThat("결과값", result, CoreMatchers.is(new int[]{5, 6, 3}));
    }
}