package com.sopark.algorism.quiz.day10;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class SolutionTest {

    Solution solution;

    @Before
    public void setUp(){
        this.solution = new Solution();
    }

    @Test
    public void case1(){
        // Given

        // When
        String solution = this.solution.solution(new int[]{6, 10, 2});

        // Then
        Assertions.assertThat(solution).isEqualTo("6210");
    }

    @Test
    public void case2(){
        // Given

        // When
        String solution = this.solution.solution(new int[]{3, 30, 34, 5, 9});

        // Then
        Assertions.assertThat(solution).isEqualTo("9534330");
    }

    @Test
    public void case3(){
        // Given

        // When
        String solution = this.solution.solution(new int[]{0,0,0,0,0});

        // Then
        Assertions.assertThat(solution).isEqualTo("0");
    }
}