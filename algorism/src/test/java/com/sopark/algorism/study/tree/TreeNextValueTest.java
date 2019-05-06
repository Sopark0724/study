package com.sopark.algorism.study.tree;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeNextValueTest {

    @Test
    public void nextValue() {
        TreeNextValue treeNextValue = new TreeNextValue(10);
        treeNextValue.makeNode();

        TreeNextValue.Node node = treeNextValue.nextValue(4);
        Assertions.assertThat(node.data).isEqualTo(5);

        TreeNextValue.Node node1 = treeNextValue.nextValue(6);
        Assertions.assertThat(node1.data).isEqualTo(7);

        TreeNextValue.Node node2 = treeNextValue.nextValue(3);
        Assertions.assertThat(node2.data).isEqualTo(4);
    }
}