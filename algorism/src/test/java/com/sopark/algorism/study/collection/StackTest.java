package com.sopark.algorism.study.collection;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class StackTest {

    @Test
    public void 스택테스트(){
        // Given
        Stack<Integer> stack = new Stack<>();

        // When
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // Then
        Integer value = stack.pop();
        Assertions.assertThat(value).isEqualTo(3);
        Assertions.assertThat(stack.peek()).isEqualTo(2);
        Assertions.assertThat(stack.isEmpty()).isFalse();
        stack.pop();
        stack.pop();
        Assertions.assertThat(stack.isEmpty()).isTrue();
    }
}