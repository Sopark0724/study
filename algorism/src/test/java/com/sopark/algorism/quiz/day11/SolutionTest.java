package com.sopark.algorism.quiz.day11;

import com.sopark.algorism.study.collection.LinkedList;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void solution() {
        Solution solution = new Solution();
        LinkedList linkedList = new LinkedList();
        linkedList.append(1);
        linkedList.append(2);
        linkedList.append(3);
        linkedList.append(4);
        int result = solution.solution(linkedList, 2);
        Assertions.assertThat(result).isEqualTo(3);
    }


    @Test
    public void solution2() {
        Solution solution = new Solution();
        LinkedList linkedList = new LinkedList();
        linkedList.append(1);
        linkedList.append(2);
        linkedList.append(3);
        linkedList.append(4);
        int result = solution.solution(linkedList, 1);
        Assertions.assertThat(result).isEqualTo(4);
    }
}