package com.sopark.algorism.study.tree;

import java.util.stream.IntStream;

/**
 * 자신의 오른쪽 노드가 없다는건 찾고자 하는 값이 위에 존재. 위쪽으로 찾다가 처음 오른쪽으로 꺽이는 부분이 다음값.
 * 자신의 오른쪽 노드가 있다는건 처음 오른쪽 값에서 왼쪽으로 찾다 마지막 값이 다음값.
 */
public class TreeNextValue {
    class Node {
        int data;
        Node leftNode;
        Node rightNode;
        Node parent;

        public Node(int data) {
            this.data = data;
        }
    }
    Node[] nodes;
    Node root;

    public TreeNextValue(int size) {
        this.nodes = new Node[size];
        IntStream.range(0, size).forEach(value -> this.nodes[value] = new Node(value));
    }

    public void makeNode(){
        this.root = this.makeNodeR(0, nodes.length-1, null);
    }

    private Node makeNodeR(int start, int end, Node parnet){
        if(start > end) return null;
        int middle = (start + end) / 2;
        Node node = this.nodes[middle];
        node.leftNode = this.makeNodeR(start, middle -1, node);
        node.rightNode = this.makeNodeR(middle+1, end, node);
        node.parent = parnet;
        return node;
    }

    public Node nextValue(int value) {
        Node node = this.nodes[value];
        if(node.rightNode == null) {
            return aboveFind(node.parent, node);
        }else {
            return blewFind(node.rightNode);
        }
    }

    private Node aboveFind(Node parent, Node child) {
        if(parent == null) return null;
        if(parent.leftNode == child) return parent;
        return aboveFind(parent.parent, parent);
    }

    private Node blewFind(Node rightNode) {
        if(rightNode.leftNode == null) return rightNode;
        return blewFind(rightNode.leftNode);
    }
}
