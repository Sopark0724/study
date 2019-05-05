package com.sopark.algorism.study.collection;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void queue테스트() {
        Queue<Integer> q = new Queue<>();
        q.push(1);
        q.push(2);
        q.push(3);

        assertThat(q.peek()).isEqualTo(1);
        assertThat(q.pop()).isEqualTo(1);
        assertThat(q.pop()).isEqualTo(2);
        q.push(4);
        assertThat(q.pop()).isEqualTo(3);
        assertThat(q.pop()).isEqualTo(4);
    }
}