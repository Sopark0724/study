package com.sopark.algorism.study.sort;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class SelectSortTest {

    @Test
    public void sort() {
        // Given
        int[] input = {3,2,1,5,6};

        // When
        SelectSort selectSort = new SelectSort();
        int[] result = selectSort.sort(input);

        // Then
        assertThat("{3,2,1,5,6} 정렬된 결과 출력", new int[]{1,2,3,5,6}, CoreMatchers.is(result));
    }
}