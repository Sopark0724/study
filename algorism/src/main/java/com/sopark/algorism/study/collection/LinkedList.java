package com.sopark.algorism.study.collection;

public class LinkedList {
    private Node header;

    public LinkedList() {
        this.header = new Node();
    }

    static class Node {
        Node next;
        int data;

        Node() {
        }

        public Node(int data) {
            this.data = data;
        }
    }


    public void append(int data){
        Node node = new Node(data);
        Node n = header;

        while (n.next != null) {
            n = n.next;
        }

        n.next = node;
    }

    public void delete(int data) {
        Node node = header;
        while (node.next != null) {
            if(node.next.data == data) {
                node.next = node.next.next;
            }else {
                node = node.next;
            }
        }
    }

    public void print(){
        Node node = header.next;

        while(node.next != null){
            System.out.print(node.data + " -> ");
            node = node.next;
        }
        System.out.println(node.data);
    }

    public static void main(String[] args) {
        LinkedList node = new LinkedList();
        node.append(2);
        node.append(3);
        node.append(4);
        node.print();
        node.delete(3);
        node.print();
    }
}
