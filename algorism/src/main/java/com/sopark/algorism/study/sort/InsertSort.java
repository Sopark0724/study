package com.sopark.algorism.study.sort;


/**
 * 이미 정렬된 숫자의 경우에는 속도가 선택 정렬보다는 빠르다.
 * 모두 정렬이 되어 있지 않다면, 최악의 경우 선택 정렬과 비슷.
 *
 * 시간복잡도 : O(N^2)
 */
public class InsertSort {
    public int[] sort(int[] input){


        for(int i = 1, j ; i < input.length ; i++){

            // 바꿀 위치 찾기 (처음 부터 검사하여 자기 보다 큰값의 index 찾음)
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
/*
            Arrays.stream(input)
                    .forEach(System.out::print);

            System.out.println();*/

        }

        return input;
    }

}
