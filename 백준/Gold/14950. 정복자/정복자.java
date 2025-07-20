import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T, answer;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        for (int n = 1; n <= N; n++) {
            parents[n] = n;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        while (M-- > 0) {
            pq.offer(new Edge(new StringTokenizer(br.readLine())));
        }
        for (int cnt = 1; cnt < N; ) {
            if (pq.poll().union(cnt - 1)) cnt++;
        }
        System.out.println(answer);
    }


    private static class Edge implements Comparable<Edge> {
        int a, b, c;

        public Edge(StringTokenizer st) {
            this.a = Integer.parseInt(st.nextToken());
            this.b = Integer.parseInt(st.nextToken());
            this.c = Integer.parseInt(st.nextToken());
        }

        private boolean union(int t) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) return false;
            parents[rootB] = rootA;
            answer += c + T * t;
            return true;
        }

        private int find(int i) {
            if (parents[i] == i) return i;
            return parents[i] = find(parents[i]);
        }

        @Override
        public int compareTo(Edge o) {
            return this.c - o.c;
        }
    }
}