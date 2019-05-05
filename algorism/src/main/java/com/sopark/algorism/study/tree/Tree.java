package com.sopark.algorism.study.tree;


class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }
}
public class Tree {
    public Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node makeNode(Node left, int data, Node right) {
        Node node = new Node(data);
        node.left = left;
        node.right = right;

        return node;
    }

    public void inorder(Node node) {
        if(node == null) return;

        inorder(node.left);
        System.out.println(node.data);
        inorder(node.right);
    }

    public void preorder(Node node) {
        if(node == null) return;

        System.out.println(node.data);
        preorder(node.left);
        preorder(node.right);
    }

    public void postorder(Node node) {
        if(node == null) return;

        preorder(node.left);
        preorder(node.right);
        System.out.println(node.data);
    }
}
