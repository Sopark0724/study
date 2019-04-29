package com.sopark.algorism.study.collection;

public class Queue<T> {
    private Node first;
    private Node last;

    private class Node {
        T data;
        Node next;

        public Node(T t) {
            this.data = t;
        }
    }

    public void push(T t) {
        Node node = new Node(t);

        if(first == null) {
            first = node;
            last = node;
        }

        last.next = node;
        last = node;
    }

    public T pop() {
        if(first == null) {
            throw new Error();
        }

        T data = first.data;
        first = first.next;
        return data;
    }

    public T peek() {
        return first.data;
    }
}
