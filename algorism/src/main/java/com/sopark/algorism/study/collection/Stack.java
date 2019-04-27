package com.sopark.algorism.study.collection;

/**
 * Node 간의 링크 정보는 변하지 않지만 TopNode 를 통해 현재 Top 의 위치를 저장
 * Pop 한다고 해서 기존 데이터가 바로 사라지지 않음. push 하여 기존 데이터의 연결정보를 끈을때 삭제가능성 있음.
 * @param <T>
 */
public class Stack<T> {
    Node top;

    class Node {
        private Node next;

        private T value;

        public Node(T value) {
            this.value = value;
        }
    }

    public void push(T t){
        Node node = new Node(t);
        node.next = top;
        top = node;
    }

    public T pop() {
        T value = top.value;
        top = top.next;
        return value;
    }

    public T peek() {
        return top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
