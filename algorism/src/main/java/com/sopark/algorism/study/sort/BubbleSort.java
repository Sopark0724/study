package com.sopark.algorism.study.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 1. 앞부터 진행하며 뒤에 수보다 앞에 수가 더 크다면 서로 위치를 바꿈. 그렇게 반복하여 가장 큰수를 찾아 제일 마지막 인덱스에 위치
 * 2. 1번 작업을 반복 하지만 가장 큰수를 마지막 인덱스 바로 앞에 위치(N-1).
 * 시간 복잡도 : O(N^2)
 *
 */
public class BubbleSort implements Sortable {

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
