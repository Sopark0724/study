package com.sopark.algorism.study.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DFS {
    List<Node> nodes;

    public DFS(int nodeCount) {
        nodes = IntStream.range(0, nodeCount)
                .mapToObj(DFS.Node::new)
                .collect(Collectors.toList());
    }

    class Node {
        int data;
        List<Node> child = new LinkedList<>();
        boolean mark;
        public Node(int data) {
            this.data = data;
        }
    }

    public void addEdge(int nodeIndex1, int nodeIndex2) {
        Node node1 = nodes.get(nodeIndex1);
        Node node2 = nodes.get(nodeIndex2);

        if(!node1.child.contains(node2)){
            node1.child.add(node2);
        }

        if(!node2.child.contains(node1)){
            node2.child.add(node1);
        }
    }

    public List<Integer> searchStack() {
        Node root = nodes.get(0);
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if(node.mark) continue;

            result.add(node.data);
            node.mark = true;
            stack.addAll(node.child);
        }

        return result;
    }


    public List<Integer> searchRecusive() {
        return recursive(nodes.get(0), new ArrayList<>());
    }

    public List<Integer> searchRecusive(int startIndex) {
        return recursive(nodes.get(startIndex), new ArrayList<>());
    }

    private List<Integer> recursive(Node node, List<Integer> result) {
        if(node == null || node.mark) {
            return result;
        }

        result.add(node.data);
        node.mark = true;

        node.child.forEach(node1 -> this.recursive(node1, result));

        return result;
    }
}
