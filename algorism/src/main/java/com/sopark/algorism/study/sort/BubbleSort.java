package com.sopark.algorism.study.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 매번 돌면서 뒷쪽 부터 맞춤
 * 시간 복잡도 : O(N^2)
 *
 */
public class BubbleSort implements Sort{

    @Override
    public int[] sort(int[] input) {

        for (int i = input.length ; i > 0 ; i --){
            for (int j = 0 ; j < i - 1 ; j++){
                if (input[j] > input[j+1]) {
                    int temp = input[j];
                    input[j] = input[j+1];
                    input[j+1] = temp;
                }
            }

            System.out.println(Arrays.stream(input)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(", ")));
        }

        return input;
    }
}
