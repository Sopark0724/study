package com.sopark.algorism.study.sort;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class InsertSortTest {

    @Test
    public void sort(){
        // Given
        int[] input = {3,2,1,5,6};

        // When
        InsertSort insertSort = new InsertSort();
        int[] result = insertSort.sort(input);

        // Then
        assertThat("{3,2,1,5,6}", new int[]{1,2,3,5,6}, CoreMatchers.is(result));
    }
}
