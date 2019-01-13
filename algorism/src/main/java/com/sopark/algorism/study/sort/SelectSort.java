package com.sopark.algorism.study.sort;

/**
 * index 0 ~ N 까지 매회 최소값의 index를 찾아서 정렬
 * 시간복잡도 : O(N^2)
 */

public class SelectSort {
    public int[] sort(int[] input) {
        for(int i = 0; i < input.length ; i++) {
            int minIndex = i;

            for(int j = i+1 ; j < input.length ; j++){
                if(input[minIndex] > input[j]){
                    minIndex = j;
                }
            }

            swap(i, minIndex, input);
        }

        return input;
    }

    private void swap(int sourceIdx, int destinyIdx, int[] input) {
        int temp = input[sourceIdx];
        input[sourceIdx] = input[destinyIdx];
        input[destinyIdx] = temp;
    }
}
