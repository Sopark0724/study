package com.sopark.algorism.study.sort;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleSortTest {

    @Test
    public void sort() {
        // Given
        int[] input = {3,2,1,5,6};

        // When
        BubbleSort bubbleSort = new BubbleSort();
        int[] result = bubbleSort.sort(input);

        // Then
        assertThat("{3,2,1,5,6}", result, CoreMatchers.is(new int[]{1,2,3,5,6}));
    }
}