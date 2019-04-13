package com.sopark.algorism.study.sort;


import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 이미 정렬된 숫자의 경우에는 속도가 선택 정렬보다는 빠르다.
 * 모두 정렬이 되어 있지 않다면, 최악의 경우 선택 정렬과 비슷.
 * 앞에서 부터 가장 작은 값들에 대한 정렬이라는 원칙을 가지고 왼쪽에서 오른쪽으로 검사.
 * 오른쪽으로 검사해서 더 큰값이 있을 경우 비교한 대상 값을 임시저장 후 찾은값 인덱스 부터 오른쪽으로 이동 후
 * 임시저장된 값을 찾은 값에 넣음.
 *
 * 시간복잡도 : O(N^2)
 */
public class InsertSort implements Sortable {

    @Override
    public int[] sort(int[] input){


        for(int i = 1, j ; i < input.length ; i++){

            // 기준 값보다 큰 값을 찾기 (j 가 큰 값의 인덱스로 됨)
            for(j = 0 ; j < i ; j++){
                if(input[j] > input[i]){
                    break;
                }
            }

            // 끼워넣을 값을 임시 저장
            int temp = input[i];

            // 원래 끼워 넣을 숫자가 있었던 인덱스 직전 부터 끼워넣을 위치까지 값들을 하나씩 뒤로 옮겨줌.
            for(int k = i ; k > j ; k--){
                input[k] = input[k-1];
            }

            // 임시저장 값을 끼워 넣을 위치로 넣음.
            input[j] = temp;

            System.out.println(Arrays.stream(input)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(", ")));

        }

        return input;
    }

}
