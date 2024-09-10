import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, answer;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        PriorityQueue<FF> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++) {
            pq.offer(new FF(n, Integer.parseInt(st.nextToken())));
            parent[n] = n;
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        while (!pq.isEmpty()) {
            FF ff = pq.poll();
            if (union(ff.i, 0)) {
                answer += ff.v;
                if (answer > K) {
                    System.out.println("Oh no");
                    return;
                }
            }
        }
        System.out.println(answer);
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB)
            return false;
        parent[rootA] = rootB;
        return true;
    }

    private static int find(int a) {
        if (a == parent[a])
            return a;
        return parent[a] = find(parent[a]);
    }

    private static class FF implements Comparable<FF> {
        int i, v;

        public FF(int i, int v) {
            this.i = i;
            this.v = v;
        }

        @Override
        public int compareTo(FF o) {
            return this.v - o.v;
        }
    }
}