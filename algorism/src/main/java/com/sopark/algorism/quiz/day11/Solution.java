package com.sopark.algorism.quiz.day11;

import com.sopark.algorism.study.collection.LinkedList;

/**
 * 단방향 LinkedList 의 뒤에서 N 번째 값을 구하시오.
 */

public class Solution {

    public int solution(LinkedList data, int k) {
        return this.findLastIndex(data.header, k, new RerfereceCount()).data;
    }

    class RerfereceCount {
        private int count;

        public void addCount() {
            this.count++;
        }

        public int getCount() {
            return count;
        }
    }

    public LinkedList.Node findLastIndex(LinkedList.Node data, int k, RerfereceCount r){

        if(data == null) {
            return null;
        }

        LinkedList.Node findNode = findLastIndex(data.next, k, r);
        r.addCount();
        if(r.getCount() == k) {
            return data;
        }

        return findNode;
    }
}
