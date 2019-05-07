package com.sopark.algorism.study.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    /**
     *
     *        4
     *     /    \
     *    2      6
     *   / \    / \
     *  1   3  5   7
     *
     */
    @Test
    public void insert() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(4);
        tree.insert(2);
        tree.insert(1);
        tree.insert(3);
        tree.insert(6);
        tree.insert(5);
        tree.insert(7);

        tree.inorder();

        tree.delete(6);
        tree.inorder();

        tree.insert(9);
        tree.inorder();
    }
}