package com.sopark.algorism.study.collection;

public class LinkedList {
    public Node header;

    public LinkedList() {
        this.header = new Node();
    }

    public static class Node {
        public Node next;
        public int data;

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

    public void removeDupls(){
        Node node = header.next;

        while(node != null && node.next != null) {
            Node r = node;
            while (r.next != null) {
                if(node.data == r.next.data){
                    r.next = r.next.next;
                }else {
                    r = r.next;
                }
            }
            node = node.next;
        }
    }

    public static void main(String[] args) {
        LinkedList node = new LinkedList();
        node.append(2);
        node.append(3);
        node.append(4);
        node.print();
        node.delete(3);
        node.print();
        node.append(2);
        node.append(4);
        node.print();
        node.removeDupls();
        node.print();

    }
}
