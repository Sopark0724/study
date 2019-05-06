package com.sopark.algorism.study.tree;

import java.util.Arrays;

public class TreeSearch {

    Node[] nodes;

    Node rootNode;

    class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public void makeTree(int[] data) {
        nodes = new Node[data.length];
        Arrays.stream(data).forEach(value -> nodes[value] = new Node(value));
        rootNode = this.makeTreeRecusive(0,nodes.length -1);
    }

    private Node makeTreeRecusive(int start, int end){
        if(start > end) return null;

        int middle = (start + end) / 2;
        Node node = nodes[middle];
        node.left = this.makeTreeRecusive(start, middle-1);
        node.right = this.makeTreeRecusive(middle + 1, end);

        return node;
    }

    public boolean search(int number) {
        Node node = rootNode;
        System.out.println();
        System.out.println("searching number : " + number);
        while (node != null) {
            if(node.data > number) {
                System.out.println("search number is small than " + node.data );
                node = node.left;
            }else if(node.data < number) {
                System.out.println("search number is bigger than " + node.data );
                node = node.right;
            }else {
                return true;
            }
        }

        return false;
    }
}
