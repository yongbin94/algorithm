import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Node[] nodes = new Node[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Node head = null, prev = null;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            Node newNode = new Node(num);
            nodes[num] = newNode;

            if (i == 0) {
                head = newNode;
            } else {
                prev.next = newNode;
                newNode.prev = prev;
            }
            prev = newNode;
        }
        prev.next = head;
        head.prev = prev;

        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            Node node = nodes[Integer.parseInt(st.nextToken())], newNode;
            switch (type) {
                case "BN":
                    newNode = new Node(Integer.parseInt(st.nextToken()));
                    nodes[newNode.num] = newNode;
                    sb.append(node.next.num).append("\n");
                    node.next.prev = newNode;
                    newNode.next = node.next;
                    node.next = newNode;
                    newNode.prev = node;
                    break;
                case "BP":
                    newNode = new Node(Integer.parseInt(st.nextToken()));
                    nodes[newNode.num] = newNode;
                    sb.append(node.prev.num).append("\n");
                    node.prev.next = newNode;
                    newNode.prev = node.prev;
                    node.prev = newNode;
                    newNode.next = node;
                    break;
                case "CN":
                    sb.append(node.next.num).append("\n");
                    nodes[node.next.num] = null;
                    node.next = node.next.next;
                    node.next.prev = node;
                    break;
                case "CP":
                    sb.append(node.prev.num).append("\n");
                    nodes[node.prev.num] = null;
                    node.prev = node.prev.prev;
                    node.prev.next = node;
            }
        }
        System.out.println(sb);
    }

    private static class Node {
        int num;
        Node prev, next;

        public Node(int num) {
            this.num = num;
        }
    }
}