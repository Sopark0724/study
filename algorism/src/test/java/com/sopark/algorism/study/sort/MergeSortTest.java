package com.sopark.algorism.study.sort;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class MergeSortTest {

    @Test
    public void sort() {
        // Given
        int[] input = {3,2,1,5,6};

        // When
        Sortable quickSort = new MergeSort();
        int[] result = quickSort.sort(input);

        // Then
        assertThat("{3,2,1,5,6}", result, CoreMatchers.is(new int[]{1,2,3,5,6}));
    }

}