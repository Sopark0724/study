package com.sopark.algorism.study.tree;

/**
 * insert, delete
 */
public class BinarySearchTree {
    class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    Node root;

    private Node search(Node root, int value) {
        // 마지막 노드 찾기
        if(root == null || root.value == value) return root;

        if(root.value > value) return search(root.left, value);
        return search(root.right, value);
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node root, int value) {
        if(root == null) {
            root = new Node(value);
            return root;
        }

        if(value > root.value) {
            root.right = insert(root.right, value);
        }else if(value < root.value) {
            root.left = insert(root.left, value);
        }

        return root;
    }

    public void inorder(){
        this.inorder(this.root);
        System.out.println();
    }

    private void inorder(Node root) {
        if(root != null) {
            inorder(root.left);
            System.out.print(root.value + " ");
            inorder(root.right);
        }
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    private Node delete (Node root, int value) {
        if(value > root.value) {
            root.right = delete(root.right, value);
        }else if(value < root.value) {
            root.left = delete(root.left, value);
        }else {
            if(root.left == null && root.right == null) return null;
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;

            int data = findMinValue(root.right);
            root.right = delete(root.right, data);
        }

        return root;
    }

    private int findMinValue(Node root) {
        Node node = root;
        Node min = node;
        while (node != null) {
            min = node;
            node = node.left;
        }

        return min.value;
    }
}
