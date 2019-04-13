package com.sopark.algorism.study.sort;

/**
 * 1. 배열을 반으로 나눈다.
 * 2. 나눠진 왼쪽과 오른쪽 배열을 다시 반으로 나눈다.
 * 3. 더이상 나눠지지 않을 경우 서로 병합하는 과정에서 첫번째 배열과 두번째 배열중 낮은 값을 신규 배열에 넣고
 *    낮은 값의 배열의 검색하는 인덱스를 1증가 시킨다.
 * 4. 한쪽 배열의 검사가 다 끝났을 경우 나머지 배열을 자동으로 신규 배열 마지막에 추가된다.
 * 5. 1~4 번 과정을 반복한다.
 */
public class MergeSort implements Sortable {

    @Override
    public int[] sort(int[] input) {
        this.mergeSort(input, 0, input.length -1);
        return input;
    }

    private void mergeSort(int[] input, int start, int end){
        if(end - start < 1) {
            return;
        }

        int middle = (start + end) / 2;
        this.mergeSort(input, start, middle);
        this.mergeSort(input, middle + 1, end);
        this.merge(input, start, middle, end);
    }

    private void merge(int[] input, int start, int middle, int end) {
        int temp[] = new int[end - start + 1];
        int firstArrIndex = start;
        int secondArrIndex = middle + 1;
        int tempIndex = 0;

        while (firstArrIndex <= middle && secondArrIndex <= end) {
            if(input[firstArrIndex] <= input[secondArrIndex]){
                temp[tempIndex++] = input[firstArrIndex++];
            }else {
                temp[tempIndex++] = input[secondArrIndex++];
            }
        }

        while (firstArrIndex <= middle) {
            temp[tempIndex++] = input[firstArrIndex++];
        }

        while (secondArrIndex <= end) {
            temp[tempIndex++] = input[secondArrIndex++];
        }

        for (int i = 0; i < temp.length ; i++) {
            input[start + i] = temp[i];
        }
    }
}
