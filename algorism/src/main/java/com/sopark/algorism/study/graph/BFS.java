package com.sopark.algorism.study.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BFS {
    List<Node> nodes;

    public BFS(int nodeCount) {
        nodes = IntStream.range(0, nodeCount)
                .mapToObj(Node::new)
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

    public List<Integer> search() {
        List<Integer> result = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        Node rootNode = nodes.get(0);
        q.add(rootNode);
        while (!q.isEmpty()){
            Node node = q.poll();
            if(node.mark){
                continue;
            }
            result.add(node.data);
            q.addAll(node.child);
            node.mark = true;
        }

        return result;
    }
}
