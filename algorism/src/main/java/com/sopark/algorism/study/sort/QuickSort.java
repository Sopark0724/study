package com.sopark.algorism.study.sort;

/**
 * 변수
 *      i = 작은 값의 마지막 인덱스 (초기값은 start - 1의 값)
 *      j = 검색 하는 커서 위치
 *
 * 1. 기준값을 설정 한다. (처음, 마지막, 중간값으로 지정)
 * 2. 왼쪽 부터 비교하여
 *      - 기준값보다 클 경우에는 커서의 위치만 1증가 (j 값에 1증가)
 *      - 기준값보다 작을 경우 i 의 값을 1증가 시키고 i번째 값과 j번째 값을 교환
 * 3. 마지막 까지 검사가 끝나면 기준값과 i+1 에 위치한 값을 서로 교환함.
 * 4. 1~3 번까지의 작업을 기준값 인덱스 기준으로 왼쪽과 오른쪽을 나눠서 다시 작업(분할)
 *
 * 분할 : 1~3번
 * 정복 : 4번
 */
public class QuickSort implements Sortable {

    @Override
    public int[] sort(int[] input) {
        this.quickSort(input, 0, input.length-1);
        return input;
    }

    // 분할 / 퀵 소트

    private void quickSort(int[] input, int start, int end){

        if(start > end){
            return;
        }

        int pivotIndex = partition(input, start, end);
        quickSort(input, start, pivotIndex -1);
        quickSort(input, pivotIndex + 1, end);
    }

    private int partition(int[] input, int start, int end) {
        int i = start-1;
        int j = start;
        int pivot = input[end];

        for ( ; j < end ; j++){
            if(input[j] < pivot) {
                i++;
                int temp = input[i];
                input[i] = input[j];
                input[j] = temp;
            }
        }
        int temp = input[i+1];
        input[i+1] = pivot;
        input[end] = temp;
        return i+1;
    }
}
