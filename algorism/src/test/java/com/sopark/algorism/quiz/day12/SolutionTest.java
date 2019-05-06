package com.sopark.algorism.quiz.day12;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void buildOrder() {
        String[] projects = new String[]{"a","b","c","d","e","f","g"};
        String[][] dependencies = new String[][]{{"f","a"},{"f","b"}, {"f","c"}, {"b","a"},{"c","a"}, {"a","e"},{"b","e"}, {"d","g"}};
        Solution solution = new Solution(projects, dependencies);
        Solution.Project[] result = solution.buildOrder();

        Arrays.stream(result).map(project -> project.name).forEach(System.out::print);
    }
}