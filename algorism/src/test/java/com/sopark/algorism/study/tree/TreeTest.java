package com.sopark.algorism.study.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *      (1)
 *      / \
 *    (2)  (3)
 *   /  \
 *  (4)  (5)
 *  inorder(left,root, right) : 4 2 5 1 3
 *  preorder (root, left, right) : 1 2 4 5 3
 *  postorder (left, right, root) : 4 5 2 3 1
 */
public class TreeTest {

    Tree tree;

    @Before
    public void init() {
        this.tree = new Tree();
        Node node4 = tree.makeNode(null, 4, null);
        Node node5 = tree.makeNode(null, 5, null);
        Node node2 = tree.makeNode(node4, 2, node5);
        Node node3 = tree.makeNode(null, 3, null);
        Node node1 = tree.makeNode(node2, 1, node3);
        tree.setRoot(node1);
    }

    @Test
    public void inorder() {
        tree.inorder(tree.getRoot());
    }

    @Test
    public void preorder() {
        tree.preorder(tree.getRoot());
    }

    @Test
    public void postorder() {
        tree.postorder(tree.getRoot());
    }
}