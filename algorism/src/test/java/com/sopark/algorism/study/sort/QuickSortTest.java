package com.sopark.algorism.study.sort;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class QuickSortTest {

    @Test
    public void sort() {
        // Given
        int[] input = {3,2,1,5,6};

        // When
        Sort quickSort = new QuickSort();
        int[] result = quickSort.sort(input);

        // Then
        assertThat("{3,2,1,5,6}", result, CoreMatchers.is(new int[]{1,2,3,5,6}));
    }


    @Test
    public void sort2() {
        // Given
        int[] input = {7,9,8};

        // When
        Sort quickSort = new QuickSort();
        int[] result = quickSort.sort(input);

        // Then
        assertThat("{3,2,1,5,6}", result, CoreMatchers.is(new int[]{7,8,9}));
    }
}