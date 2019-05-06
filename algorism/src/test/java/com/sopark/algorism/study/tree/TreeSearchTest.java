package com.sopark.algorism.study.tree;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.stream.IntStream;

public class TreeSearchTest {

    @Test
    public void search() {

        int[] data = IntStream.range(0, 10).toArray();
        TreeSearch treeSearch = new TreeSearch();
        treeSearch.makeTree(data);
        boolean search = treeSearch.search(3);
        Assertions.assertThat(search).isTrue();

        boolean search2 = treeSearch.search(11);
        Assertions.assertThat(search2).isFalse();
    }
}